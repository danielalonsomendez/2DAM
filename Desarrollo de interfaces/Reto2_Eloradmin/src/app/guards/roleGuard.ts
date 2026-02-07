import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { filter, map, take } from 'rxjs';
import { UserService } from '../services/user';

/**
 * Guard que verifica si el usuario tiene uno de los roles permitidos.
 * Si `route.data.roles` no está definido devuelve `true`.
 * @param route Ruta solicitada que puede contener `data.roles`.
 * @param state Estado de navegación (no usado).
 * @returns Observable<boolean> que indica si se permite la navegación.
 */
export const roleGuard: CanActivateFn = (route, state) => {
  const userService = inject(UserService);
  const router = inject(Router);
  const allowedRoles = route.data?.['roles'] as number[] | undefined;

  if (!allowedRoles || allowedRoles.length === 0) {
    return true;
  }

  return userService.initialCheckComplete$.pipe(
    filter(complete => complete),
    take(1),
    map(() => {
      const roleId = userService.getUser()?.tipos?.id;
      if (roleId && allowedRoles.includes(roleId)) {
        return true;
      }
      router.navigate(['/']);
      return false;
    })
  );
};
