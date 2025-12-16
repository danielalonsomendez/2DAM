import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Hotel} from '../interfaces/hotel';

@Injectable({
  providedIn: 'root',
})
export class HotelesService {
    private httpClient = inject(HttpClient);
  readonly baseUrl = 'http://localhost:3000';

  getAllHoteles(): Observable<Hotel[]> {
    return this.httpClient.get<Hotel[]>(`${this.baseUrl}/locations`);
  }

  getHotelById(id: string): Observable<Hotel| undefined> {
    return this.httpClient.get<Hotel[]>(`${this.baseUrl}/locations/`).pipe(
      map(hoteles=>hoteles.find(hotel=> hotel.id == id))
    );
  }

  getHotelByIdcorto(id: string): Observable<Hotel| undefined> {
    return this.httpClient.get<Hotel>(`${this.baseUrl}/locations/${id}`)
  }
}
