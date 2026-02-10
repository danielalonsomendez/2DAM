import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { map, filter, take } from 'rxjs';
import { UserService } from '../services/userService';

/**
 * Guard que permite el acceso a la página de login solo si NO hay un usuario autenticado.
 * Si ya existe sesión, redirige al root ('/').
 * @param route Ruta solicitada (no usada).
 * @param state Estado de navegación (no usado).
 * @returns Observable<boolean> que indica si se permite la navegación.
 */
export const loginGuard: CanActivateFn = (route, state) => {
  const userManagerService = inject(UserService);
  const router = inject(Router);
  return userManagerService.initialCheckComplete$.pipe(
    filter(complete => complete), 
    take(1),
    map(() => {
      if (!userManagerService.isCurrentlyLogged()) {
        return true;
      } else {
        router.navigate(['/libros']);
        return false;
      }
    })
  );
};
