import { Component, Input } from '@angular/core';
import { Personaje } from '../interfaces/dbz.interface';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-agregar',
  imports: [CommonModule,FormsModule],
  templateUrl: './agregar.html',
  styleUrl: './agregar.css'
})
export class Agregar {
  @Input() personajes: Personaje[] = [];
  @Input() nuevo: Personaje = { nombre: '', poder: 0 };
  agregar() {
    if (this.nuevo.nombre.trim().length === 0) {
      return;
    }
    console.log('agregar', this.nuevo);
    this.personajes.push(this.nuevo);
    this.nuevo = { nombre: '', poder: 0 };
  }
}
