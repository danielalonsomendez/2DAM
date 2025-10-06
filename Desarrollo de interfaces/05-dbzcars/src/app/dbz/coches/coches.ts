import { CommonModule } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coche } from '../interfaces/car.interface';
import { CarService } from '../carservice';

@Component({
  selector: 'app-coches',
  imports: [CommonModule,FormsModule],
  templateUrl: './coches.html'
})
export class Coches {

  dbzService = inject(CarService);
  get coches(): Coche[] {
    return this.dbzService.coches;
  }
}
