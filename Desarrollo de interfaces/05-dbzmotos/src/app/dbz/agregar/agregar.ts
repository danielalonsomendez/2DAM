import { CommonModule } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MotoService } from '../motoservice';
import { Moto } from '../interfaces/moto.interface';

@Component({
  selector: 'app-agregar',
  imports: [CommonModule, FormsModule],
  templateUrl: './agregar.html'
})
export class Agregar {
  dbzService: MotoService = inject(MotoService);
  nuevo: Moto = {
    modelo: 'Fiestra',
    precio: 1400
  }
  agregar() {
    if (this.nuevo.modelo.trim().length == 0) { return; }
    console.log(this.nuevo);
    this.dbzService.agregarNuevoMoto(this.nuevo);
    this.nuevo = { modelo: '', precio: 0 };
  }
}
