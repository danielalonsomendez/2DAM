import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coches } from '../coches/coches';
import { Agregar } from "../agregar/agregar";
import { CarService } from '../carservice';

@Component({
  selector: 'app-main-page',
  imports: [FormsModule, CommonModule, Coches, Agregar],
  templateUrl: './main-page.html'
})

export class MainPage {
  dbzService: CarService = inject(CarService);
}
