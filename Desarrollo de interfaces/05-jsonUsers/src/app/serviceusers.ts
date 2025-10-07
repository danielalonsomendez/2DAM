import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { User } from './idata';

@Injectable({
  providedIn: 'root'
})
export class Serviceusers {
  private HttpClient = inject(HttpClient);
  readonly API_URL: string = 'https://jsonplaceholder.typicode.com/users';

  getUsers() {
    return this.HttpClient.get<User[]>(this.API_URL);
  }
}
