import { CommonModule } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coche } from '../interfaces/car.interface';
import { CarService } from '../carservice';

@Component({
  selector: 'app-agregar',
  imports: [CommonModule, FormsModule],
  templateUrl: './agregar.html'
})
export class Agregar {
  dbzService: CarService = inject(CarService);
  nuevo: Coche = {
    modelo: 'Fiesta',
    precio: 1400
  }
  agregar() {
    if (this.nuevo.modelo.trim().length == 0) { return; }
    console.log(this.nuevo);
    this.dbzService.agregarNuevoCoche(this.nuevo);
    this.nuevo = { modelo: '', precio: 0 };
  }
}
