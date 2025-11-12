import { Routes } from '@angular/router';
import { Recetas } from '../components/recetas/recetas';
import { Detallereceta } from '../components/detallereceta/detallereceta';
import { TablaVisualizaciones } from '../components/tabla-visualizaciones/tabla-visualizaciones';

export const routes: Routes = [
    // Ruta tabla con visualizaciones
    { path: 'tablavisualizaciones', component: TablaVisualizaciones },
    // Ruta tabla receta
    { path: "receta/:id", component: Detallereceta },
    // Ruta tabla principal
    { path: "", component: Recetas  }
  
];
