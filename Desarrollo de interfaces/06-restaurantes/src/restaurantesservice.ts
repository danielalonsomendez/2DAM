import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Restaurante } from './app/restaurante';

@Injectable({
  providedIn: 'root',
})
export class Restaurantesservice {
    private httpClient = inject(HttpClient);
  readonly baseUrl = 'http://localhost:3000';

  getAllRestaurantes(): Observable<Restaurante[]> {
    return this.httpClient.get<Restaurante[]>(`${this.baseUrl}/locations`);
  }

  getRestauranteById(id: string): Observable<Restaurante | undefined> {
    return this.httpClient.get<Restaurante[]>(`${this.baseUrl}/locations/`).pipe(
      map(restaurantes=>restaurantes.find(restaurante=> restaurante.id == id))
    );
  }
  
  getRestauranteByIdcorto(id: string): Observable<Restaurante | undefined> {
    return this.httpClient.get<Restaurante>(`${this.baseUrl}/locations/${id}`)
  }
}
