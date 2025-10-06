import { Injectable } from '@angular/core';
import { Coche } from './interfaces/car.interface';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  
  private _coches: Coche[] = [
    { modelo: 'Seat', precio: 15000 },
    { modelo: 'Renault', precio: 12000 }
  ];

  get coches(): Coche[] {
    return [...this._coches];
  }
  agregarNuevoCoche(coche: Coche) {
    this._coches.push(coche);
  }
}
