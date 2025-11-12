import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Receta } from '../interfaces/receta';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Recetaservice {
  private httpClient = inject(HttpClient);
  readonly baseUrl = 'http://localhost:3000';
  // Lista de todas las recetas
  todasLasRecetas(): Observable<Receta[]> {
    return this.httpClient.get<Receta[]>(`${this.baseUrl}/recetas`);
  }
  //Receta de un  ID
  todasLasRecetasPorID(id: string): Observable<Receta> {
    return this.httpClient.get<Receta>(`${this.baseUrl}/recetas/${id}`);
  }
}
