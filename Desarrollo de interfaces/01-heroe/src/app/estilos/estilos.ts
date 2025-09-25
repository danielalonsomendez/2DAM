import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-estilos',
  imports: [CommonModule],
  templateUrl: './estilos.html',
  styleUrl: './estilos.css'
})
export class Estilos {
  exExito: boolean = true;
}
