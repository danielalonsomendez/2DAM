import { CommonModule } from '@angular/common';
import { Component, inject, input, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coche } from '../interfaces/car.interface';

@Component({
  selector: 'app-agregar',
  imports: [CommonModule, FormsModule],
  templateUrl: './agregar.html'
})
export class Agregar {
  @Input() coches: Coche[] = [];
  nuevo: Coche = {
    modelo: 'Fiesta',
    precio: 1400
  }
  agregar() {
    if (this.nuevo.modelo.trim().length == 0) { return; }
    console.log(this.nuevo);
    this.coches.push(this.nuevo);
    this.nuevo = { modelo: '', precio: 0 };
  }
}
