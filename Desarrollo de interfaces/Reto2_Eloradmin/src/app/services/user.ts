import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../interfaces/user';
import { BehaviorSubject, catchError, map, of } from 'rxjs';
import { Servicio } from './servicio';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  /** Usuario actualmente logueado o null */
  private user:User | null = null;
  /**
   * Servicio de gestión de usuario en la aplicación.
   * @param apiService Servicio para llamadas HTTP.
   * @param router Router de Angular para redirecciones.
   */
  constructor(private apiService: Servicio, private router: Router) {
    this.checkUser().subscribe();
  }
  private initialCheckCompleteSubject = new BehaviorSubject<boolean>(false);
  /** Observable que indica cuando la comprobación inicial de sesión ha finalizado */
  public initialCheckComplete$ = this.initialCheckCompleteSubject.asObservable();

  /**
   * Comprueba en localStorage si hay un usuario identificado y solicita sus datos al backend.
   * Marca `initialCheckComplete$` como completado cuando finaliza la comprobación.
   * @returns Observable con el `User` o `null` si no hay usuario.
   */
  checkUser() {
    const userIdStr = localStorage.getItem('userId');
    if (userIdStr) {
      const userId = Number(userIdStr);
      if (!isNaN(userId) && userId > 0) {
        return this.apiService.getUsuarioPorID(userId).pipe(
          map(user => {
            this.setUser(user);
            this.initialCheckCompleteSubject.next(true);
            return user;
          }),
          catchError(error => {
            console.error('Error in initial login check:', error);
            this.initialCheckCompleteSubject.next(true);
            return of(null);
          })
        );
      }
    }
    this.initialCheckCompleteSubject.next(true);
    return of(null);
  }
  /**
   * Asigna el usuario actual en memoria.
   * @param user Usuario a establecer.
   */
  setUser(user: User) {
    this.user = user;
  }
  
  /**
   * Devuelve el usuario actual o null.
   * @returns `User` o `null`.
   */
  getUser(): User | null {
    return this.user;
  }
  /**
   * Indica si hay un usuario logueado en memoria.
   * @returns `true` si hay usuario, `false` en caso contrario.
   */
  isCurrentlyLogged(): boolean {
    return this.user !== null;
  }
  /**
   * Cierra la sesión local: limpia usuario, storage y redirige a login.
   */
  logout() {
    this.user = null;
    localStorage.removeItem('userId');
    
    this.router.navigate(['/login']);
  }
}
