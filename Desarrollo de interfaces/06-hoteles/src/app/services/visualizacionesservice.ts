import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Visualizacionesservice {
  listaVisualizaciones(): { hotelId: string; fecha: string }[] {
    const visualizacionesJson = localStorage.getItem('visualizaciones');
    if (visualizacionesJson) {
      return JSON.parse(visualizacionesJson) as Visualizacion[];
    }
    return [];
  }

  anadirCuenta(id: string): void {
    const visualizaciones = this.listaVisualizaciones();
    const ahora = new Date();
    const fechaHoraActual = 
      `${ahora.getFullYear()}-${(ahora.getMonth() + 1).toString().padStart(2, '0')}-${ahora.getDate().toString().padStart(2, '0')} ` +
      `${ahora.getHours().toString().padStart(2, '0')}:${ahora.getMinutes().toString().padStart(2, '0')}:${ahora.getSeconds().toString().padStart(2, '0')}`;
    
    const nuevaVisualizacion: Visualizacion = {
      hotelId: id,
      fecha: fechaHoraActual
    };
    
    visualizaciones.push(nuevaVisualizacion);
    localStorage.setItem('visualizaciones', JSON.stringify(visualizaciones));
  }

  visualizacionesPorHotel(id: string): number {
    const visualizaciones = this.listaVisualizaciones();
    return visualizaciones.filter(visualizacion => visualizacion.hotelId === id).length;
  }

  ultimaVisualizacionHotel(id: string): string | null {
    const visualizaciones = this.listaVisualizaciones();
    const visualizacionesHotel = visualizaciones.filter(v => v.hotelId === id);
    if (visualizacionesHotel.length > 0) {
      return visualizacionesHotel[visualizacionesHotel.length - 1].fecha;
    }
    return null;
  }

  visualizacionesPorReceta(id: string): number {
    return this.visualizacionesPorHotel(id);
  }
}
