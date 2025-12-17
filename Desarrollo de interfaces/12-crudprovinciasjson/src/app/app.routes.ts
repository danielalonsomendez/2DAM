import { Routes } from '@angular/router';
import { Provincialista } from '../provincialista/provincialista';

export const routes: Routes = [
  { path: 'home', component: Provincialista},        // Ruta para la lista de tareas
  { path: '', redirectTo: '/home', pathMatch: 'full' } // Redirección a 'home' si la URL está vacía

];
