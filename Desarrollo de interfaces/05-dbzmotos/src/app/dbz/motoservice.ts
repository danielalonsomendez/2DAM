import { Injectable } from '@angular/core';
import { Moto } from './interfaces/moto.interface';

@Injectable({
  providedIn: 'root'
})
export class MotoService {

  private _motos: Moto[] = [
    { modelo: 'Seat', precio: 15000 },
    { modelo: 'Renault', precio: 12000 }
  ];

  get motos(): Moto[] {
    return [...this._motos];
  }
  agregarNuevoMoto(moto: Moto) {
    this._motos.push(moto);
  }
}
