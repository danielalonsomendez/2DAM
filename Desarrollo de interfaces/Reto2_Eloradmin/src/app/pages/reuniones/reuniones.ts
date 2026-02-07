import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PaginatorModule } from 'primeng/paginator';
import { Table, TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Servicio } from '../../services/servicio';
import { Reuniones } from '../../interfaces/reuniones';
import { UserService } from '../../services/user';
import { User } from '../../interfaces/user';
import { DialogModule } from 'primeng/dialog';
import { Centros } from '../../interfaces/centros';
import { SelectModule } from 'primeng/select';
import { MapaComponent } from '../mapa/mapa';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';


type CentroFilterKey = 'ownership' | 'territory' | 'municipality';

@Component({
  selector: 'app-reuniones',
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
  standalone: true,
  templateUrl: './reuniones.html',
  styleUrl: './reuniones.css',
})
export class ReunionesComponent implements OnInit, OnDestroy {
  mapaVisible: boolean = false;
  centroParaMapa: Centros | null = null;
  selectedCentro: Centros | null = null; // El centro que pasaremos al mapa
  selectedReunion: Reuniones | null = null;

  crearVisible = false;
  draftReunion: Reuniones = this.createEmptyReunion();
  saving = false;

  profesores: User[] = [];
  alumnos: User[] = [];
  reuniones: Reuniones[] = [];
  filteredReuniones: Reuniones[] = [];
  selectedDTITUC: string | null = null;
  selectedDTERRE: string | null = null;
  selectedDMUNIC: string | null = null;

  selectedStatus: 'all' | 'pendiente' | 'aceptada' | 'denegada' | 'conflicto' = 'all';
  showSearch = false;
  globalFilterValue = '';

  @ViewChild('dt') table?: Table;

  centros: Centros[] = [];

  dtitucOptions: any[] = []; 
  dterreOptions: any[] = []; 
  dmunicOptions: any[] = [];
  activeFilter: CentroFilterKey | null = null;


  // Estado del filtro
selectedFilterType: CentroFilterKey | null = null;
selectedFilterValue: string | null = null;

  private langChangeSub?: Subscription;

  constructor(private adminService: Servicio, private userService: UserService, private translate: TranslateService) {}

  ngOnInit(): void {
    this.loadReuniones();

    this.adminService.getUsuarios().subscribe(users => {
      this.profesores = users.filter(u => u.tipos.id === 3);
      this.alumnos = users.filter(u => u.tipos.id === 4);
    });

    this.adminService.getAllCentros().subscribe(centros => {
      this.centros = centros;
      this.generateFilterOptions();
    });

    this.langChangeSub = this.translate.onLangChange.subscribe(() => this.onLanguageChange());
  }

  ngOnDestroy(): void {
    this.langChangeSub?.unsubscribe();
  }

  // -------------------------------
  // CARGA DE REUNIONES
  // -------------------------------
  /**
   * Carga las reuniones desde el servicio y aplica un filtrado básico según el rol del usuario.
   * Si el usuario es admin (id 1 o 2) muestra todas; si no, muestra solo las relacionadas con él.
   */
  loadReuniones(): void {
    this.adminService.getReuniones().subscribe(reuniones => {
      const usuarioActual = this.userService.getUser();

      if (usuarioActual?.tipos.id === 1 || usuarioActual?.tipos.id === 2) {
        this.reuniones = reuniones;
      } else {
        this.reuniones = reuniones.filter(r =>
          r.usersByProfesorId?.id === usuarioActual?.id ||
          r.usersByAlumnoId?.id === usuarioActual?.id
        );
      }

      this.filteredReuniones = [...this.reuniones];
    });
  }

  // -------------------------------
  // GENERAR OPCIONES ÚNICAS
  // -------------------------------


onSelectDTITUC(value: string) {
  this.selectedDTITUC = value;

  // limpiar niveles inferiores
  this.selectedDTERRE = null;
  this.selectedDMUNIC = null;

  // aplicar filtro
  this.applyFilter('ownership', value);
}

onSelectDTERRE(value: string) {
  this.selectedDTERRE = value;

  // limpiar nivel inferior
  this.selectedDMUNIC = null;

  this.applyFilter('territory', value);
}

onSelectDMUNIC(value: string) {
  this.selectedDMUNIC = value;
  this.applyFilter('municipality', value);
}


generateFilterOptions() {
  this.dtitucOptions = this.buildOptions(this.ownershipField);
  this.dterreOptions = this.buildOptions(this.territoryField);
  this.dmunicOptions = this.buildOptions(this.municipalityField);
}

/**
 * Construye opciones únicas para selectores a partir de la lista de centros.
 * @param field Campo de `Centros` a utilizar.
 * @returns Array de objetos `{label, value}`.
 */
private buildOptions(field: keyof Centros): { label: string; value: string }[] {
  const values = this.centros
    .map(centro => this.getFieldValue(centro, field))
    .filter(value => !!value);
  return [...new Set(values)].map(value => ({ label: value, value }));
}

/**
 * Aplica un filtro por tipo/valor y refresca la lista filtrada.
 * @param type Tipo de filtro ('ownership'|'territory'|'municipality').
 * @param value Valor a filtrar.
 */
applyFilter(type: CentroFilterKey, value: string) {
  this.selectedFilterType = type;
  this.selectedFilterValue = value;
  this.filterReuniones();
}

/**
 * Aplica el filtro seleccionado sobre las reuniones usando el campo correspondiente del centro.
 */
filterReuniones() {
  const type = this.selectedFilterType;
  const value = this.selectedFilterValue;

  const field = type ? this.getFieldForFilter(type) : null;

  if (!field || !value) {
    this.filteredReuniones = [...this.reuniones];
    return;
  }

  this.filteredReuniones = this.reuniones.filter(r => {
    const centro = this.centros.find(c => String(c.CCEN) === String(r.idCentro));
    if (!centro) return false;
    return this.getFieldValue(centro, field) === value;
  });

}

/**
 * Activa/desactiva el filtro visual.
 * @param type Tipo de filtro a alternar.
 */
toggleFilter(type: CentroFilterKey) {
  this.activeFilter = this.activeFilter === type ? null : type;
}
/**
 * Resetea todos los filtros y limpia la búsqueda global.
 */
clearFilters() {
  this.selectedDTITUC = null;
  this.selectedDTERRE = null;
  this.selectedDMUNIC = null;

  this.selectedFilterType = null;
  this.selectedFilterValue = null;

  this.filteredReuniones = [...this.reuniones];

  this.globalFilterValue = '';
  this.table?.filterGlobal('', 'contains');
}





  // -------------------------------
  // BUSCADOR GLOBAL
  // -------------------------------
  toggleSearch(): void {
    this.showSearch = !this.showSearch;
    if (!this.showSearch) {
      this.globalFilterValue = '';
      this.table?.filterGlobal('', 'contains');
    }
  }

  protected getStatusKey(estado?: string | null): string {
    const normalized = (estado || '').toLowerCase();
    const allowed = ['pendiente', 'aceptada', 'denegada', 'conflicto'];
    return allowed.includes(normalized) ? `meetings.status.${normalized}` : 'meetings.status.default';
  }

  onGlobalFilter(event: Event): void {
    const value = (event.target as HTMLInputElement).value;
    this.table?.filterGlobal(value, 'contains');
    this.globalFilterValue = value;
  }

  // -------------------------------
  // ICONOS DE ESTADO
  // -------------------------------
  estadoIcon(estado?: string): string {
    switch ((estado || '').toLowerCase()) {
      case 'pendiente': return 'pi pi-question';
      case 'aceptada': return 'pi pi-check';
      case 'conflicto': return 'pi pi-exclamation-triangle';
      case 'denegada': return 'pi pi-times';
      default: return 'pi pi-circle';
    }
  }

  // -------------------------------
  // CREAR / GUARDAR REUNIÓN
  // -------------------------------
  crearReunion(): void {
    this.draftReunion = this.createEmptyReunion();
    this.crearVisible = true;
  }

  guardarReunion(): void {
    if (this.saving) return;

    this.saving = true;
    const usuarioActual = this.userService.getUser();

    if (!usuarioActual) {
      console.error("No hay usuario logueado");
      return;
    }

    const newReunion: Reuniones = {
      idReunion: 0,
      fecha: this.draftReunion.fecha,
      asunto: this.draftReunion.asunto,
      titulo: this.draftReunion.titulo,
      aula: this.draftReunion.aula,
      idCentro: this.draftReunion.idCentro,
      estado: 'pendiente',
      estadoEus: '',
      createdAt: '',
      updatedAt: '',
      usersByAlumnoId: usuarioActual,
      usersByProfesorId: usuarioActual,
      centro: undefined
    };

    this.adminService.crearReunion(newReunion).subscribe({
      next: () => {
        this.saving = false;
        this.crearVisible = false;
        this.draftReunion = this.createEmptyReunion();
        this.loadReuniones();
      },
      error: err => {
        console.error(err);
        this.saving = false;
      }
    });
  }

  private createEmptyReunion(): Reuniones {
    return {
      idReunion: 0,
      fecha: '',
      asunto: '',
      titulo: '',
      aula: '',
      idCentro: 0,
      estado: 'pendiente',
      estadoEus: '',
      createdAt: '',
      updatedAt: '',
      usersByAlumnoId: {} as User,
      usersByProfesorId: {} as User,
      centro: undefined
    };
  }

  onDialogHide(): void {
    this.draftReunion = this.createEmptyReunion();
  }

  get isProfessor(): boolean {
    return this.userService.getUser()?.tipos?.id === 3;
  }
  get isAdmin(): boolean {
    const tipoId = this.userService.getUser()?.tipos?.id;
    return tipoId === 1 || tipoId === 2;
  }
mostrarMapa(): void {
    if (this.selectedReunion) {
      // 1. Si hay reunión seleccionada, buscamos su centro en la lista de centros
      const centroEncontrado = this.centros.find(
        c => String(c.CCEN) === String(this.selectedReunion!.idCentro)
      );

      if (centroEncontrado) {
        this.centroParaMapa = centroEncontrado;
      } else {
        // Fallback por si no encuentra el centro (ej. datos incompletos)
        this.centroParaMapa = null;
        console.warn('Centro no encontrado para la reunión seleccionada');
      }
    } else {
      // 2. Si NO hay reunión seleccionada, mostramos el mapa global (sin zoom específico)
      this.centroParaMapa = null;
    }

    // Abrimos el dialog
    this.mapaVisible = true;
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

  protected getCenterName(centro: Centros | null | undefined): string {
    if (!centro) return '';
    return `${centro.DGENRC || ''} ${centro.NOM || ''} ${centro.DGENRE || ''}`.trim();
  }
}