import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Album } from './idata';

@Injectable({
  providedIn: 'root'
})
export class Servicealbums {
  private HttpClient = inject(HttpClient);
  readonly API_URL: string = 'https://jsonplaceholder.typicode.com/albums';

  getAlbums() {
    return this.HttpClient.get<Album[]>(this.API_URL);
  }
}
