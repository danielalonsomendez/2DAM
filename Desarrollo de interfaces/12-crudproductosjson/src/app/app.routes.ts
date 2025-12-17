import { Routes } from '@angular/router';
import { Productolista } from '../productolista/productolista';

export const routes: Routes = [
  { path: 'home', component: Productolista},        // Ruta para la lista de tareas
  { path: '', redirectTo: '/home', pathMatch: 'full' } // Redirección a 'home' si la URL está vacía

];
