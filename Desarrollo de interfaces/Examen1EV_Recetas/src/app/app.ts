import { Component, inject, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Eval1Daniel');
  router: Router = inject(Router)
  // Redirigir a la pagina de lista de recetas 
  mainPage() {
    this.router.navigate(['']);
  }
    // Redirigir a la pagina de lista de recetas con visualizaciones
  tablaVisualizaciones() {
    this.router.navigate(['tablavisualizaciones']);
  }
}
