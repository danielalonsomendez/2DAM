import { Routes } from '@angular/router';
import { Admin } from './admin/admin';

export const routes: Routes = [
  {
    path: 'admin',
    component: Admin,
    children: [
      {
        path: 'usuarios',
        loadComponent: () => import('./usuarios/usuarios').then(m => m.Usuarios),
      },
      {
        path: 'configuracion',
        loadComponent: () => import('./configuracion/configuracion').then(m => m.Configuracion),
      },
      // Ruta comodín: cualquier URL dentro de /admin irá a usuarios
      {
        path: '**', redirectTo: 'usuarios',
      },
    ],
  },
  // Si quieres que cualquier URL fuera de /admin también vaya a usuarios:
  {
    path: '**', redirectTo: 'admin/usuarios',
  },

];
