import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Personajes } from '../personajes/personajes';
import { Personaje } from '../interfaces/dbz.interface';
import { Agregar } from '../agregar/agregar';

@Component({
  selector: 'app-main-page',
  imports: [FormsModule, CommonModule,Personajes,Agregar],
  templateUrl: './main-page.html',
  styleUrl: './main-page.css'
})
export class MainPage {
  personajes: Personaje[] = [
    { nombre: 'Goku', poder: 15000 },
    { nombre: 'Vegeta', poder: 8500 },
    { nombre: 'Krillin', poder: 700 }
  ];
  nuevo: Personaje = {
    nombre: 'Maestro Roshi',
    poder: 1000
  };
}
