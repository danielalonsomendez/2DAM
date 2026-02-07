import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild, inject, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Table, TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DialogModule } from 'primeng/dialog';
import { SelectModule } from 'primeng/select';

import { Centros } from '../../interfaces/centros';
import { Servicio } from '../../services/servicio';
import { MapaComponent, ColorMode } from "../mapa/mapa";
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';

type CentroFilterKey = 'ownership' | 'territory' | 'municipality';

@Component({
  selector: 'app-centros',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    PaginatorModule,
    ButtonModule,
    InputTextModule,
    FormsModule,
    DialogModule,
    SelectModule,
    MapaComponent,
    TranslateModule
],
  templateUrl: './centros.html',
  styleUrl: './centros.css'
})
export class CentrosComponent implements OnInit, OnDestroy {
selectedCentro: Centros | null = null;
detalleVisible = false;

  centros: Centros[] = [];
  filteredCentros: Centros[] = [];

  // Filtros
  selectedDTITUC: string | null = null;
  selectedDTERRE: string | null = null;
  selectedDMUNIC: string | null = null;

  dtitucOptions: any[] = [];
  dterreOptions: any[] = [];
  dmunicOptions: any[] = [];

  selectedFilterType: CentroFilterKey | null = null;
  selectedFilterValue: string | null = null;

  activeFilter: CentroFilterKey | null = null;

  // Buscador global
  showSearch = false;
  globalFilterValue = '';

  @ViewChild('dt') table?: Table;
  @ViewChild(MapaComponent) mapaComponent?: MapaComponent;
  private langChangeSub?: Subscription;
  protected readonly globalFilterFields = ['NOM', 'NOME', 'DMUNIC', 'DMUNIE', 'DTERRE', 'DTERRC', 'DTITUC', 'DTITUE'];

  /**
   * @param adminService Servicio para obtener centros y otros datos.
   * @param translate Servicio de traducción para texto dinámico.
   */
  constructor(private adminService: Servicio, private translate: TranslateService) {}

  /**
   * Inicializa la lista de centros y configura el listener de cambios de idioma.
   */
  ngOnInit(): void {
    this.loadCentros();
    this.langChangeSub = this.translate.onLangChange.subscribe(() => this.onLanguageChange());
  }

  ngOnDestroy(): void {
    this.langChangeSub?.unsubscribe();
  }

  // -------------------------------
  // CARGA DE CENTROS
  // -------------------------------
  /**
   * Carga todos los centros desde el servicio y prepara filtros.
   */
  loadCentros(): void {
    this.adminService.getAllCentros().subscribe(centros => {
      this.centros = centros;
      this.filteredCentros = [...centros];
      this.generateFilterOptions();
    });
  }

  // -------------------------------
  // GENERAR OPCIONES ÚNICAS
  // -------------------------------
  /**
   * Reconstruye las opciones de filtro a partir de los datos actuales.
   */
  private generateFilterOptions(): void {
    this.dtitucOptions = this.buildOptions(this.ownershipField);
    this.dterreOptions = this.buildOptions(this.territoryField);
    this.dmunicOptions = this.buildOptions(this.municipalityField);
  }

  /**
   * Crea una lista única de {label,value} para un campo dado.
   */
  private buildOptions(field: keyof Centros): { label: string; value: string }[] {
    const values = this.centros
      .map(centro => this.getFieldValue(centro, field))
      .filter(value => !!value);
    return [...new Set(values)].map(value => ({ label: value, value }));
  }

  // -------------------------------
  // SELECTORES DE FILTRO
  // -------------------------------
  /**
   * Maneja la selección del campo de titularidad (DTITUC/DTITUE).
   * - Limpia los filtros de niveles inferiores (territory y municipality).
   * - Aplica el filtro y actualiza el modo de color del mapa.
   * @param value Valor seleccionado o `null` para limpiar la selección.
   */
  onSelectDTITUC(value: string | null) {
    this.selectedDTITUC = value;
    this.selectedDTERRE = null;
    this.selectedDMUNIC = null;
    this.applyFilter('ownership', value);
    this.refreshMapaColorMode();
  }

  /**
   * Maneja la selección del campo de territorio (DTERRE/DTERRC).
   * - Limpia el filtro de municipio.
   * - Aplica el filtro y actualiza el modo de color del mapa.
   * @param value Valor seleccionado o `null` para limpiar la selección.
   */
  onSelectDTERRE(value: string | null) {
    this.selectedDTERRE = value;
    this.selectedDMUNIC = null;
    this.applyFilter('territory', value);
    this.refreshMapaColorMode();
  }

  /**
   * Maneja la selección del campo de municipio (DMUNIC/DMUNIE).
   * - Aplica el filtro y actualiza el modo de color del mapa.
   * @param value Valor seleccionado o `null` para limpiar la selección.
   */
  onSelectDMUNIC(value: string | null) {
    this.selectedDMUNIC = value;
    this.applyFilter('municipality', value);
    this.refreshMapaColorMode();
  }

  /**
   * Aplica el filtro seleccionado (o elimina el filtro si `value` es null).
   */
  applyFilter(type: CentroFilterKey, value: string | null) {
    this.selectedFilterType = value ? type : null;
    this.selectedFilterValue = value;
    this.filterCentros();
  }

  /**
   * Filtra la lista de centros usando el campo correspondiente.
   */
  filterCentros() {
    const type = this.selectedFilterType;
    const value = this.selectedFilterValue;
    const field = type ? this.getFieldForFilter(type) : null;

    if (!field || !value) {
      this.filteredCentros = [...this.centros];
      return;
    }

    this.filteredCentros = this.centros.filter(c => this.getFieldValue(c, field) === value);
  }

  /**
   * Alterna el filtro activo para mostrar u ocultar opciones visuales.
   */
  toggleFilter(type: CentroFilterKey) {
    this.activeFilter = this.activeFilter === type ? null : type;
  }

  /**
   * Restablece todos los filtros y actualiza el mapa en consecuencia.
   */
  clearFilters() {
    this.selectedDTITUC = null;
    this.selectedDTERRE = null;
    this.selectedDMUNIC = null;

    this.selectedFilterType = null;
    this.selectedFilterValue = null;

    this.filteredCentros = [...this.centros];

    this.refreshMapaColorMode();

    this.globalFilterValue = '';
    this.table?.filterGlobal('', 'contains');
  }

  // -------------------------------
  // BUSCADOR GLOBAL
  // -------------------------------
  /**
   * Muestra/oculta el buscador global.
   */
  toggleSearch(): void {
    this.showSearch = !this.showSearch;
    if (!this.showSearch) {
      this.globalFilterValue = '';
      this.table?.filterGlobal('', 'contains');
    }
  }

  /**
   * Filtra globalmente la tabla por texto.
   */
  onGlobalFilter(event: Event): void {
    const value = (event.target as HTMLInputElement).value;
    this.table?.filterGlobal(value, 'contains');
    this.globalFilterValue = value;
  }
  /**
   * Abre el diálogo de detalle para el centro seleccionado.
   */
abrirDetalle(): void {
  if (!this.selectedCentro) return;
  this.detalleVisible = true;
}

/**
 * Oculta el detalle y resetea la selección.
 */
onDetalleHide(): void {
  this.detalleVisible = false;
  this.selectedCentro = null;
}

  protected get ownershipColumn(): string {
    return this.ownershipField;
  }

  protected get territoryColumn(): string {
    return this.territoryField;
  }

  protected get municipalityColumn(): string {
    return this.municipalityField;
  }

  protected getOwnershipValue(centro: Centros | null): string {
    return this.getFieldValue(centro, this.ownershipField);
  }

  protected getTerritoryValue(centro: Centros | null): string {
    return this.getFieldValue(centro, this.territoryField);
  }

  protected getMunicipalityValue(centro: Centros | null): string {
    return this.getFieldValue(centro, this.municipalityField);
  }

  protected getCenterName(centro: Centros | null | undefined): string {
    if (!centro) return '';
    return `${centro.DGENRC || ''} ${centro.NOM || ''} ${centro.DGENRE || ''}`.trim();
  }

  protected get activeFiltersCount(): number {
    return [this.selectedDTITUC, this.selectedDTERRE, this.selectedDMUNIC].filter(value => !!value)
      .length;
  }

  protected get activeFiltersLabel(): string {
    const count = this.activeFiltersCount;
    if (!count) {
      return '';
    }
    if (count === 1) {
      return this.translate.instant('centers.filters.activeOne');
    }
    return this.translate.instant('centers.filters.activeMany', { count });
  }

  private onLanguageChange(): void {
    if (!this.centros.length) {
      return;
    }
    this.generateFilterOptions();
    this.clearFilters();
  }

  private getFieldForFilter(type: CentroFilterKey): keyof Centros {
    switch (type) {
      case 'ownership':
        return this.ownershipField;
      case 'territory':
        return this.territoryField;
      case 'municipality':
        return this.municipalityField;
    }
    return this.ownershipField;
  }

  private get ownershipField(): keyof Centros {
    return this.isBasque ? 'DTITUE' : 'DTITUC';
  }

  private get territoryField(): keyof Centros {
    return this.isBasque ? 'DTERRC' : 'DTERRE';
  }

  private get municipalityField(): keyof Centros {
    return this.isBasque ? 'DMUNIE' : 'DMUNIC';
  }

  private get isBasque(): boolean {
    return (this.translate.currentLang || this.translate.defaultLang || 'es') === 'eu';
  }

  private getFieldValue(centro: Centros | null, field: keyof Centros): string {
    if (!centro) {
      return '';
    }
    const value = centro[field];
    return value === null || value === undefined ? '' : String(value);
  }

  /**
   * Determina y aplica el modo de color más específico para el mapa según los filtros activos.
   * Prioridad: municipality > territory > ownership. Si no hay componente de mapa, no hace nada.
   * @private
   */
  private refreshMapaColorMode(): void {
    if (!this.mapaComponent) {
      return;
    }

    let nextMode: ColorMode = 'ownership';

    if (this.selectedDMUNIC) {
      nextMode = 'municipality';
    } else if (this.selectedDTERRE) {
      nextMode = 'municipality';
    } else if (this.selectedDTITUC) {
      nextMode = 'territory';
    }

    this.mapaComponent.setColorMode(nextMode, { force: true });
  }

  /**
   * Limpia la selección de un tipo de filtro concreto llamando al selector correspondiente con `null`.
   * @param type Tipo de filtro a limpiar ('ownership' | 'territory' | 'municipality').
   */
  onClearSelect(type: CentroFilterKey): void {
    if (type === 'ownership') {
      this.onSelectDTITUC(null);
      return;
    }

    if (type === 'territory') {
      this.onSelectDTERRE(null);
      return;
    }

    if (type === 'municipality') {
      this.onSelectDMUNIC(null);
    }
  }

}