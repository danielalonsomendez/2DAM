import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";  
import { Router } from '@angular/router';
import { UserService } from '../../services/userService';
import { TranslateModule } from '@ngx-translate/core';


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
    private router: Router,
    private userService: UserService,
  ) {}

  /**
   * Intenta autenticar al usuario con las credenciales del formulario.
   * En caso de éxito guarda el `userId` en localStorage y redirige al home.
   */
  login() {
    this.error = '';
    this.userService.iniciarSesion(this.username, this.password).subscribe({
      next: (user) => {
        localStorage.setItem('username', user.username.toString());
        this.userService.setUser(user);
        this.router.navigate(['/libros']); 
      },
      error: (err) => {
        this.error = 'Login incorrecto. Por favor, verifica tus credenciales e inténtalo de nuevo.';
      }
    });
  }
}
