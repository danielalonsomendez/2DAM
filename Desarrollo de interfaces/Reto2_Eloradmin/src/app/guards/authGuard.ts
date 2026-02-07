import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { map, filter, take } from 'rxjs';
import { UserService } from '../services/user';

/**
 * Guard que permite el acceso solo si el usuario está autenticado.
 * Espera a que se complete la comprobación inicial y redirige a `/login` si no hay sesión.
 * @param route Ruta solicitada por Angular Router (no utilizada directamente aquí).
 * @param state Estado de navegación (no utilizado directamente aquí).
 * @returns Observable<boolean> que resuelve si se permite la navegación.
 */
export const authGuard: CanActivateFn = (route, state) => {
  const userManagerService = inject(UserService);
  const router = inject(Router);
  return userManagerService.initialCheckComplete$.pipe(
    filter(complete => complete), 
    take(1),
    map(() => {
      if (userManagerService.isCurrentlyLogged()) {
        return true;
      } else {
        console.log('Access denied. User is not authenticated.');
        router.navigate(['/login']);
        return false;
      }
    })
  );
};
