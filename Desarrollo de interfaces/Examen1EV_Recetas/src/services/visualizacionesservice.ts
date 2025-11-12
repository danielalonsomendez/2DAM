import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Visualizacionesservice {
  // Lista de todas las visitas
  listaVisualizaciones(): String[] {
    const visualizacionesJson = localStorage.getItem('visualizaciones');
    if (visualizacionesJson) {
      return JSON.parse(visualizacionesJson) as String[];
    }
    return [];
  }
  // Añadir id al array
  anadirCuenta(id: String): void {
    const visualizaciones = this.listaVisualizaciones();
    visualizaciones.push(id);
    localStorage.setItem('visualizaciones', JSON.stringify(visualizaciones));
  }
  // Cuenta de visualizaciones de una receta
  visualizacionesPorReceta(id: string): number | undefined {
    const visualizaciones = this.listaVisualizaciones();
    return visualizaciones.filter(visualizacion => visualizacion == id).length;
  }
}
