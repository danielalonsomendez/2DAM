import { Injectable } from '@angular/core';
import { Personaje } from './interfaces/dbz.interface';

@Injectable({
  providedIn: 'root'
})
export class Miservicio {
  private nuevo: Personaje = { nombre: '', poder: 0 };

  agregarNuevoPersonaje(personajes: Personaje[], nuevo: Personaje) {
      personajes.push(nuevo);
  }
}
