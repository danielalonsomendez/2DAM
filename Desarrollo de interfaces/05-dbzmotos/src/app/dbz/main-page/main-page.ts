import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Agregar } from "../agregar/agregar";
import { Motos } from '../motos/motos';

@Component({
  selector: 'app-main-page',
  imports: [FormsModule, CommonModule, Agregar, Motos],
  templateUrl: './main-page.html'
})

export class MainPage {
}
