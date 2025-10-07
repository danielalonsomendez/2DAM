import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Idata } from './idata';

@Injectable({
  providedIn: 'root'
})
export class Servicearticulos {
  private HttpClient = inject(HttpClient);
  readonly API_URL: string = 'https://jsonplaceholder.typicode.com/posts';

  getArticulos() {
    return this.HttpClient.get<Idata[]>(this.API_URL);
  }
}
