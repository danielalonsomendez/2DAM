import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Heroe } from './heroe/heroe';
import { Listado } from './listado/listado';
import { Estilos } from './estilos/estilos';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Heroe, Listado, Estilos],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('heroe');
}
