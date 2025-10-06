import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coche } from '../interfaces/car.interface';

@Component({
  selector: 'app-coches',
  imports: [CommonModule,FormsModule],
  templateUrl: './coches.html'
})
export class Coches {
  @Input() coches: Coche[] = [];
}
