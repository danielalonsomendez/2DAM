import { Component, inject } from '@angular/core';
import { Recetaservice } from '../../services/recetaservice';
import { Receta } from '../../interfaces/receta';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Visualizacionesservice } from '../../services/visualizacionesservice';

@Component({
  selector: 'app-tabla-visualizaciones',
  imports: [CommonModule, FormsModule],
  templateUrl: './tabla-visualizaciones.html',
  styleUrl: './tabla-visualizaciones.css',
})
export class TablaVisualizaciones {
  // Array de recetas
  recetas: Receta[] = [];
  router: Router = inject(Router);
  // Servicios
  visualizacionesService: Visualizacionesservice = inject(Visualizacionesservice);
  recetaService: Recetaservice = inject(Recetaservice);
  // Cargas las recetas
  constructor() {
    this.recetaService.todasLasRecetas().subscribe(data => {
      this.recetas = data;
    })
  }
  // Redirigir a la pagina de detalle
  irReceta(id: String) {
    this.router.navigate(["/receta", id],)
  }

}
