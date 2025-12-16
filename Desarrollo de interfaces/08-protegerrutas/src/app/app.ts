import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { Auth } from '../services/authservice';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  authService:Auth=inject(Auth);
  router:Router= inject(Router); 
  
  login() {
    this.authService.login();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/home']);
  }
}
