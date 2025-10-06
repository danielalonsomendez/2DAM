import { CommonModule } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MotoService } from '../motoservice';


@Component({
  selector: 'app-motos',
  imports: [CommonModule,FormsModule],
  templateUrl: './motos.html'
})
export class Motos {

  dbzService = inject(MotoService);
  get motos() {
    return this.dbzService.motos;
  }
}
