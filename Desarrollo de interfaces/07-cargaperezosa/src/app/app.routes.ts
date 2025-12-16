import { Routes } from '@angular/router';
import { Home } from './home/home';

export const routes: Routes = [
  {
    path: '',
    component: Home,
  },
  {
    path: 'lazy',
    loadComponent: () => import('./lazy/lazy').then(m => m.Lazy),
  }
];
