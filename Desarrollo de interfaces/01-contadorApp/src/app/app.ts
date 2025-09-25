import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = "ContadorApp";

  numero: number = 10;
  base: number = 5;

  sumar(){this.numero = this.numero + 1;}

  restar(){this.numero = this.numero - 1;}

  acumular(num: number){this.numero = this.numero + num;}

}
