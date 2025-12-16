import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Auth {
  // Variable para simular el estado de logueo
  private isLoggedIn = false; // Empezamos como no logueado (false).

  constructor() { }
  
  public isAuthenticated(): boolean {
    return this.isLoggedIn; // Para leer el valor de la variable
  }
  public login() {
    this.isLoggedIn = true;
    console.log('Usuario logueado');
  }

  public logout() {
    this.isLoggedIn = false;
    console.log('Usuario deslogueado');
  }
}
