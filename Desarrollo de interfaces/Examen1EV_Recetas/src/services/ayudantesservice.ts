import { Injectable } from '@angular/core';
import { Ayudante } from '../interfaces/ayudante';

@Injectable({
  providedIn: 'root',
})
export class Ayudantesservice {
  // Lista de todos los ayudantes
  todosAyudantes(): Ayudante[] {
    const ayudantesJson = localStorage.getItem('ayudantes');
    if (ayudantesJson) {
      return JSON.parse(ayudantesJson) as Ayudante[];
    }
    return [];
  }
  // Añadir al array  un ayudante
  anadirAyudante(user: Ayudante): void {
    const ayudantes = this.todosAyudantes();
    ayudantes.push(user);
    localStorage.setItem('ayudantes', JSON.stringify(ayudantes));
  }
  // Leer datos del formulario y añadir ayudante
  enviarFormulario(nombre: string,  email: string,id: string): void {
    const nuevo: Ayudante = {
      nombre,
      email,
      id
    };
    this.anadirAyudante(nuevo);
  }
  // Lista de ayudantes por recetas
  ayudantesPorReceta(id: string): Ayudante[] | undefined {
    const ayudantes = this.todosAyudantes();
    return ayudantes.filter(ayudante => ayudante.id=== id);
  }
}
