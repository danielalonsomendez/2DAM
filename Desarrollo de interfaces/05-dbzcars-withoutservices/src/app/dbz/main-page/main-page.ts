import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coches } from '../coches/coches';
import { Agregar } from "../agregar/agregar";
import { Coche } from '../interfaces/car.interface';

@Component({
  selector: 'app-main-page',
  imports: [FormsModule, CommonModule, Coches, Agregar],
  templateUrl: './main-page.html'
})

export class MainPage {
  coches: Coche[] = [
    { modelo: 'Seat', precio: 15000 },
    { modelo: 'Renault', precio: 12000 }
  ];
}
