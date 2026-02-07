import { AfterViewInit, Component, Input, OnChanges, OnDestroy, SimpleChanges } from '@angular/core';
import { NgFor } from '@angular/common';
import { Centros } from '../../interfaces/centros';
import mapboxgl from 'mapbox-gl';
import { TranslateModule } from '@ngx-translate/core';
import type { FeatureCollection, Feature, Point } from 'geojson';

export type ColorMode = 'none' | 'ownership' | 'territory' | 'municipality';
type LngLatTuple = [number, number];

interface CentroFeatureProperties {
  id: number;
  name: string;
  municipality: string;
  ownership: string;
  territory: string;
  categoryValue?: string;
  iconId?: string;
}

interface BaseMapOption {
  labelKey: string;
  style: string;
}

interface ColorModeOption {
  labelKey: string;
  value: ColorMode;
}

interface LegendEntry {
  label: string;
  color: string;
}

@Component({
  selector: 'app-mapa',
  imports: [TranslateModule, NgFor],
  templateUrl: './mapa.html',
  styleUrl: './mapa.css',
})
/**
 * Componente de mapa que renderiza los `centros` usando Mapbox.
 * Provee controles para cambiar el estilo base y el modo de color (ownership/territory/municipality).
 */
export class MapaComponent implements AfterViewInit, OnChanges, OnDestroy {

  @Input() centros: Centros[] = [];
  @Input() centroSeleccionado: Centros | null = null;
  @Input() markerColor = '#E53935';
  @Input() mostrarColorControls = true;
  @Input() soloCentroSeleccionado = false;

  private map?: mapboxgl.Map;
  private popup?: mapboxgl.Popup;
  mapReady = false;
  legendEntries: LegendEntry[] = [];
  readonly baseMapOptions: BaseMapOption[] = [
    { labelKey: 'map.baseLayers.satellite', style: 'mapbox://styles/mapbox/satellite-streets-v12' },
    { labelKey: 'map.baseLayers.light', style: 'mapbox://styles/mapbox/light-v11' },
    { labelKey: 'map.baseLayers.streets', style: 'mapbox://styles/mapbox/streets-v12' },
    { labelKey: 'map.baseLayers.outdoors', style: 'mapbox://styles/mapbox/outdoors-v12' }
  ];
  readonly colorModeOptions: ColorModeOption[] = [
    { labelKey: 'map.colorModes.ownership', value: 'ownership' },
    { labelKey: 'map.colorModes.territory', value: 'territory' },
    { labelKey: 'map.colorModes.municipality', value: 'municipality' },
    { labelKey: 'map.colorModes.none', value: 'none' }
  ];
  selectedBaseMapStyle = this.baseMapOptions[0].style;
  selectedColorMode: ColorMode = 'ownership';

  private readonly defaultCenter: LngLatTuple = [-2.64, 43.23];
  private readonly defaultZoom = 8;
  private readonly sourceId = 'centros-source';
  private readonly layerId = 'centros-layer';
  private readonly highlightLayerId = 'centro-highlight';
  private readonly mapContainerId = 'map-centros';
  private readonly markerImageId = 'centro-marker';
  private readonly colorPalette = [
    '#FF6B6B', '#F4A261', '#2EC4B6', '#7F5AF0', '#FFB703', '#06D6A0', '#EE6055', '#8ECAE6', '#E0AAFF', '#6EE7B7',
    '#F15BB5', '#4CC9F0', '#84A59D', '#F28482', '#90BE6D'
  ];
  private colorAssignments = new Map<string, string>();

  private get markerImageUrl(): string {
    return this.construirSvgDataUrl(this.markerColor);
  }

  private construirSvgDataUrl(color: string): string {
    const svg = `<?xml version="1.0" encoding="utf-8"?>
    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 365 560">
      <g>
        <path fill="${color}" d="M182.9,551.7c0,0.1,0.2,0.3,0.2,0.3S358.3,283,358.3,194.6c0-130.1-88.8-186.7-175.4-186.9 C96.3,7.9,7.5,64.5,7.5,194.6c0,88.4,175.3,357.4,175.3,357.4S182.9,551.7,182.9,551.7z" />
        <circle fill="#ffffff" cx="182.5" cy="187.5" r="64" />
      </g>
    </svg>`;

    return `data:image/svg+xml;charset=utf-8,${encodeURIComponent(svg)}`;
  }

  /**
   * URL data‐URI del SVG usado como vista previa del marcador.
   */
  get markerPreviewUrl(): string {
    return this.markerImageUrl;
  }

  /**
   * Maneja el cambio del estilo base seleccionado por el usuario.
   */
  cambiarBaseMap(event: Event): void {
    const select = event.target as HTMLSelectElement | null;
    if (!select) {
      return;
    }

    this.aplicarBaseMap(select.value);
  }

  /**
   * Maneja el cambio del modo de color desde la UI.
   */
  cambiarColorMode(event: Event): void {
    const select = event.target as HTMLSelectElement | null;
    if (!select) {
      return;
    }

    this.setColorMode(select.value as ColorMode);
  }

  /**
   * Establece el modo de color programáticamente.
   * @param mode Modo de color deseado.
   * @param options Opciones adicionales (ej. force para forzar recarga).
   */
  setColorMode(mode: ColorMode, options?: { force?: boolean }): void {
    this.aplicarColorMode(mode, options?.force ?? false);
  }

  /**
   * Aplica internamente un modo de color, reiniciando asignaciones y actualizando la fuente.
   * @private
   */
  private aplicarColorMode(mode: ColorMode, force = false): void {
    if (!mode) {
      return;
    }

    if (!force && this.selectedColorMode === mode) {
      return;
    }

    this.selectedColorMode = mode;
    this.colorAssignments = new Map<string, string>();
    this.actualizarFuente();
  }

  /**
   * Inicializa Mapbox y programa la creación del mapa cuando el DOM está listo.
   */
  ngAfterViewInit(): void {
    (mapboxgl as any).accessToken = 'pk.eyJ1IjoiaW5mamdvbnphbGV6IiwiYSI6ImNsODcyMXF6cDE0ZDI0MmxoODFqMzR3OGIifQ.VAuTB3faQPTx7P6bRnEZjg';

    // Usamos setTimeout para asegurar que el HTML del Dialog ya existe y tiene dimensiones
    setTimeout(() => {
      this.inicializarMapa();
    }, 100);
  }
  /**
   * Reacciona a cambios de entradas (`centros`, `centroSeleccionado`, `markerColor`, `soloCentroSeleccionado`).
   */
  ngOnChanges(changes: SimpleChanges): void {
    if (!this.mapReady) {
      return;
    }

    if (changes['centros']) {
      this.actualizarFuente();
    }

    if (changes['centroSeleccionado']) {
      this.enfocarCentroSeleccionado();
      this.actualizarFuente();
    }

    if (changes['markerColor'] && !changes['markerColor'].firstChange) {
      this.actualizarIconoBase();
    }

    if (changes['soloCentroSeleccionado'] && !changes['soloCentroSeleccionado'].firstChange) {
      this.actualizarFuente();
    }
  }

  /**
   * Libera recursos del mapa cuando el componente se destruye.
   */
  ngOnDestroy(): void {
    this.popup?.remove();
    this.map?.remove();
    this.map = undefined;
    this.mapReady = false;
  }

  /**
   * Crea e inicializa la instancia de Mapbox en el contenedor.
   */
  private inicializarMapa(): void {
    if (this.map || !document.getElementById(this.mapContainerId)) {
      return;
    }

    this.map = new mapboxgl.Map({
      container: this.mapContainerId,
      style: this.selectedBaseMapStyle,
      center: this.defaultCenter,
      zoom: this.defaultZoom,
      attributionControl: false
    });

    this.map.addControl(new mapboxgl.NavigationControl(), 'top-right');

    this.map.on('load', async () => {
      this.mapReady = true;
      this.map?.resize();
      await this.prepararIconosIniciales();
      this.crearFuenteYCapa();
      this.registrarEventos();
      this.actualizarFuente();
      this.enfocarCentroSeleccionado();
    });
  }

  /**
   * Añade la fuente GeoJSON y las capas necesarias al mapa.
   */
  private crearFuenteYCapa(): void {
    if (!this.map || this.map.getSource(this.sourceId)) {
      return;
    }

    this.map.addSource(this.sourceId, {
      type: 'geojson',
      data: this.construirGeoJSON()
    });

    this.actualizarLeyenda();
    this.agregarCapaHighlight();
    this.agregarCapaMarcadores();
  }

  private agregarCapaMarcadores(): void {
    if (!this.map || this.map.getLayer(this.layerId)) {
      return;
    }

    this.map.addLayer({
      id: this.layerId,
      type: 'symbol',
      source: this.sourceId,
      layout: {
        'icon-image': ['coalesce', ['get', 'iconId'], this.markerImageId],
        'icon-size': 0.5,
        'icon-anchor': 'bottom',
        'icon-allow-overlap': true
      }
    });
  }

  private agregarCapaHighlight(): void {
    if (!this.map || this.map.getLayer(this.highlightLayerId)) {
      return;
    }

    this.map.addLayer({
      id: this.highlightLayerId,
      type: 'circle',
      source: this.sourceId,
      paint: {
        'circle-radius': 11,
        'circle-color': '#1c6f92',
        'circle-stroke-color': '#ffffff',
        'circle-stroke-width': 3,
        'circle-opacity': 0.45
      },
      filter: ['==', ['get', 'id'], -1]
    });
  }

  /**
   * Registra eventos de interacción sobre la capa de marcadores (hover, click, etc.).
   */
  private registrarEventos(): void {
    if (!this.map) {
      return;
    }

    this.map.on('mouseenter', this.layerId, () => {
      this.map?.getCanvas().style.setProperty('cursor', 'pointer');
    });

    this.map.on('mouseleave', this.layerId, () => {
      this.map?.getCanvas().style.removeProperty('cursor');
    });

    this.map.on('click', this.layerId, event => {
      const feature = event.features?.[0];
      if (!feature || feature.geometry.type !== 'Point') {
        return;
      }

      const coordinates = (feature.geometry.coordinates as LngLatTuple).slice() as LngLatTuple;
      const props = (feature.properties ?? {}) as CentroFeatureProperties;

      this.popup?.remove();
      this.popup = new mapboxgl.Popup({ closeButton: false })
        .setLngLat(coordinates)
        .setHTML(`
          <strong>${props.name}</strong><br>
          ${props.municipality}<br>
          ${props.ownership}
        `)
        .addTo(this.map!);
    });
  }

  /**
   * Actualiza la fuente GeoJSON en el mapa y la leyenda; ajusta la vista si procede.
   */
  private actualizarFuente(): void {
    if (!this.map || !this.mapReady) {
      return;
    }

    const source = this.map.getSource(this.sourceId) as mapboxgl.GeoJSONSource | undefined;
    if (source) {
      source.setData(this.construirGeoJSON());
    }

    this.actualizarLeyenda();
    if (!this.centroSeleccionado) {
      this.ajustarVistaGeneral();
    }
  }

  /**
   * Construye y devuelve un FeatureCollection GeoJSON a partir de los centros seleccionados.
   */
  private construirGeoJSON(): FeatureCollection<Point, CentroFeatureProperties> {
    const propiedadColor = this.obtenerPropiedadColor();
    const features = this.obtenerCentrosParaFuente()
      .map(centro => this.convertirACaracteristica(centro, propiedadColor))
      .filter((feature): feature is Feature<Point, CentroFeatureProperties> => !!feature);

    return {
      type: 'FeatureCollection',
      features
    };
  }

  /**
   * Convierte un `Centro` en una Feature GeoJSON con propiedades necesarias para la capa.
   */
  private convertirACaracteristica(centro: Centros, propiedadColor: ColorMode): Feature<Point, CentroFeatureProperties> | null {
    const coords = this.obtenerCoordenadas(centro);
    if (!coords) {
      return null;
    }

    const categoryValue = this.obtenerValorCategoria(centro, propiedadColor);
    const colorHex = this.obtenerColorParaValor(categoryValue);
    const iconId = this.obtenerIconoId(colorHex);

    return {
      type: 'Feature',
      geometry: {
        type: 'Point',
        coordinates: coords
      },
      properties: {
        id: centro.CCEN ?? -1,
        name: `${centro.DGENRC ?? ''} ${centro.NOM ?? ''} ${centro.DGENRE ?? ''}`.trim(),
        municipality: centro.DMUNIC ?? centro.DMUNIE ?? '',
        ownership: centro.DTITUC ?? centro.DTITUE ?? '',
        territory: centro.DTERRC ?? centro.DTERRE ?? '',
        categoryValue,
        iconId
      }
    };
  }

  /**
   * Convierte un SVG (data-URL) a PNG (data-URL) usando un canvas.
   */
  private convertirSvgAPng(svgDataUrl: string): Promise<string> {
    return new Promise((resolve, reject) => {
      const image = new Image();
      image.decoding = 'async';
      image.onload = () => {
        const ratio = image.naturalWidth > 0 ? image.naturalHeight / image.naturalWidth : 560 / 365;
        const width = 48;
        const height = Math.round(width * ratio);
        const canvas = document.createElement('canvas');
        canvas.width = width;
        canvas.height = height;
        const context = canvas.getContext('2d');
        if (!context) {
          reject(new Error('Canvas no disponible'));
          return;
        }

        context.clearRect(0, 0, width, height);
        context.drawImage(image, 0, 0, width, height);
        resolve(canvas.toDataURL('image/png'));
      };

      image.onerror = event => reject(event);
      image.src = svgDataUrl;
    });
  }

  /**
   * Prepara y registra los iconos de marcador necesarios en Mapbox.
   */
  private prepararIconosIniciales(): Promise<void> {
    if (!this.map) {
      return Promise.resolve();
    }

    const tareas: Promise<void>[] = [];
    tareas.push(this.registrarIconoColor(this.markerColor, this.markerImageId));

    this.colorPalette.forEach(color => {
      const iconId = this.obtenerIconoId(color);
      if (iconId === this.markerImageId && color === this.markerColor) {
        return;
      }
      tareas.push(this.registrarIconoColor(color, iconId));
    });

    return Promise.all(tareas).then(() => undefined);
  }

  /**
   * Registra una imagen en Mapbox a partir de un color (SVG -> PNG) si no existe.
   */
  private registrarIconoColor(color: string, imageId: string): Promise<void> {
    if (!this.map) {
      return Promise.resolve();
    }

    if (this.map.hasImage(imageId)) {
      return Promise.resolve();
    }

    const dataUrl = this.construirSvgDataUrl(color);
    return this.convertirSvgAPng(dataUrl)
      .then(pngDataUrl => new Promise<void>((resolve, reject) => {
        if (!this.map) {
          reject(new Error('El mapa no está disponible')); 
          return;
        }

        this.map.loadImage(pngDataUrl, (error, image) => {
          if (error || !image || !this.map) {
            reject(error ?? new Error('Imagen inválida'));
            return;
          }

          this.map.addImage(imageId, image);
          resolve();
        });
      }))
      .catch(error => {
        console.error('No se pudo registrar el icono del marcador', error);
      });
  }

  /**
   * Genera un id seguro para el icono a partir del color.
   */
  private obtenerIconoId(color: string): string {
    if (!color || color.toLowerCase() === this.markerColor.toLowerCase()) {
      return this.markerImageId;
    }

    const sanitized = color.replace(/[^a-zA-Z0-9]/g, '').toLowerCase();
    return `${this.markerImageId}-${sanitized}`;
  }

  /**
   * Asigna (y reutiliza) colores para valores categóricos según la paleta.
   */
  private obtenerColorParaValor(valor: string): string {
    if (!valor) {
      return this.markerColor;
    }

    const key = `${this.selectedColorMode}:${valor}`;
    let color = this.colorAssignments.get(key);
    if (!color) {
      const index = this.colorAssignments.size % this.colorPalette.length;
      color = this.colorPalette[index];
      this.colorAssignments.set(key, color);
    }

    return color;
  }

  /**
   * Vuelve a registrar el icono base cuando su color cambia.
   */
  private actualizarIconoBase(): void {
    if (!this.map) {
      return;
    }

    if (this.map.hasImage(this.markerImageId)) {
      this.map.removeImage(this.markerImageId);
    }

    this.registrarIconoColor(this.markerColor, this.markerImageId)
      .then(() => this.actualizarFuente())
      .catch(() => undefined);
  }

  private obtenerPropiedadColor(): ColorMode {
    return this.selectedColorMode;
  }

  /**
   * Devuelve el valor categórico que corresponde al modo de color solicitado.
   */
  private obtenerValorCategoria(centro: Centros, property: ColorMode): string {
    if (property === 'none') {
      return '';
    }

    if (property === 'ownership') {
      return centro.DTITUC ?? centro.DTITUE ?? '';
    }

    if (property === 'territory') {
      return centro.DTERRC ?? centro.DTERRE ?? '';
    }

    return centro.DMUNIC ?? centro.DMUNIE ?? '';
  }

  /**
   * Construye la leyenda visible a partir de los valores actuales y el modo de color.
   */
  private actualizarLeyenda(): void {
    if (!this.mostrarColorControls || this.selectedColorMode === 'municipality' || this.selectedColorMode === 'none') {
      this.legendEntries = [];
      return;
    }

    const property = this.obtenerPropiedadColor();
    const valores = new Set<string>();
    this.obtenerCentrosParaFuente().forEach(centro => {
      const valor = (this.obtenerValorCategoria(centro, property) || '').trim();
      if (valor) {
        valores.add(valor);
      }
    });

    const entries = Array.from(valores)
      .sort((a, b) => a.localeCompare(b, 'es', { sensitivity: 'base' }))
      .map(valor => ({
        label: valor,
        color: this.obtenerColorParaValor(valor)
      }));

    this.legendEntries = entries;
  }

  /**
   * Enfoca (flyTo) el centro seleccionado y resalta su círculo.
   */
  private enfocarCentroSeleccionado(): void {
    if (!this.mapReady || !this.map) {
      return;
    }

    if (!this.centroSeleccionado) {
      this.map.setFilter(this.highlightLayerId, ['==', ['get', 'id'], -1]);
      return;
    }

    const coords = this.obtenerCoordenadas(this.centroSeleccionado);
    if (!coords) {
      return;
    }

    this.map.setFilter(this.highlightLayerId, ['==', ['get', 'id'], this.centroSeleccionado.CCEN ?? -1]);
    this.map.flyTo({
      center: coords,
      zoom: 12,
      speed: 1.2,
      curve: 1,
      essential: true
    });
  }

  /**
   * Ajusta el viewport del mapa para incluir todos los centros visibles.
   */
  private ajustarVistaGeneral(): void {
    if (!this.map) {
      return;
    }

    const centrosFuente = this.obtenerCentrosParaFuente();
    if (!centrosFuente.length) {
      return;
    }

    const bounds = centrosFuente.reduce((acc, centro) => {
      const coords = this.obtenerCoordenadas(centro);
      if (coords) {
        acc.extend(coords);
      }
      return acc;
    }, new mapboxgl.LngLatBounds(this.defaultCenter, this.defaultCenter));

    if (bounds.isEmpty()) {
      return;
    }

    this.map.fitBounds(bounds, {
      padding: 40,
      maxZoom: 11,
      duration: 700
    });
  }

  /**
   * Intenta extraer coordenadas válidas (lon, lat) del centro probando diferentes campos y ordenamientos.
   */
  private obtenerCoordenadas(centro: Centros): LngLatTuple | null {
    const candidatos: LngLatTuple[] = [];

    const lon = Number(centro.LONGITUD);
    const lat = Number(centro.LATITUD);
    if (Number.isFinite(lon) && Number.isFinite(lat)) {
      candidatos.push([lon, lat]);
      candidatos.push([lat, lon] as LngLatTuple);
    }

    const alternativoLon = Number(centro.COOR_X);
    const alternativoLat = Number(centro.COOR_Y);
    if (Number.isFinite(alternativoLon) && Number.isFinite(alternativoLat)) {
      candidatos.push([alternativoLon, alternativoLat]);
      candidatos.push([alternativoLat, alternativoLon] as LngLatTuple);
    }

    for (const [candLon, candLat] of candidatos) {
      if (this.coordenadasEnRango(candLon, candLat)) {
        return [candLon, candLat];
      }
    }

    return null;
  }

  /**
   * Valida que las coordenadas estén dentro de un rango razonable para la zona.
   */
  private coordenadasEnRango(lon: number, lat: number): boolean {
    return lon >= -10 && lon <= 5 && lat >= 35 && lat <= 50;
  }

  private aplicarBaseMap(style: string): void {
    if (!this.map || !style || style === this.selectedBaseMapStyle) {
      return;
    }

    this.selectedBaseMapStyle = style;

    const reattachLayers = async () => {
      if (!this.map) {
        return;
      }

      await this.prepararIconosIniciales();
      this.crearFuenteYCapa();
      this.actualizarFuente();
      this.enfocarCentroSeleccionado();
    };

    this.map.once('style.load', reattachLayers);
    this.map.setStyle(style);
  }

  private obtenerCentrosParaFuente(): Centros[] {
    if (this.soloCentroSeleccionado) {
      return this.centroSeleccionado ? [this.centroSeleccionado] : [];
    }

    return this.centros ?? [];
  }
}
