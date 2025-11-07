import {Injectable, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HousingLocationInfo} from './housinglocation';
@Injectable({
  providedIn: 'root',
})
export class HousingService {
  private httpClient = inject(HttpClient);
  readonly baseUrl = 'http://localhost:3000';

  getAllHousingLocations(): Observable<HousingLocationInfo[]> {
    return this.httpClient.get<HousingLocationInfo[]>(`${this.baseUrl}/locations`);
  }

  getHousingLocationById(id: number): Observable<HousingLocationInfo | undefined> {
    return this.httpClient.get<HousingLocationInfo>(`${this.baseUrl}/locations/${id}`);
  }
}
