import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";  
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Servicio } from '../../services/servicio';
import { UserService } from '../../services/user';
import { TranslateModule, TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, TranslateModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  username = '';
  password = '';
  error = '';

  constructor(
    private authService: Servicio,
    private router: Router,
    private userService: UserService,
    private translate: TranslateService
  ) {}

  /**
   * Intenta autenticar al usuario con las credenciales del formulario.
   * En caso de éxito guarda el `userId` en localStorage y redirige al home.
   */
  login() {
    this.error = '';
    this.authService.iniciarSesion(this.username, this.password).subscribe({
      next: (user) => {
        localStorage.setItem('userId', user.id.toString());
        this.userService.setUser(user);
        this.router.navigate(['/']); 
      },
      error: (err) => {
        this.error = this.extractErrorMessage(err);
      }
    });
  }

  /**
   * Extrae un mensaje de error legible a partir de la respuesta HTTP o devuelve un texto genérico traducido.
   * @param err Error recibido.
   * @returns Mensaje de error para mostrar en UI.
   */
  private extractErrorMessage(err: unknown): string {
    if (err instanceof HttpErrorResponse) {
      if (typeof err.error === 'string' && err.error.trim()) {
        return err.error;
      }
      if (err.error?.message) {
        return err.error.message;
      }
      if (err.message) {
        return err.message;
      }
    }
    return this.translate.instant('login.genericError');
  }
}
