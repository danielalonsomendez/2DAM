import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Amigo } from '../interface/amigo';

@Injectable({
  providedIn: 'root',
})
export class Amigos {
   private apiUrl = 'http://localhost:3000/amigos';  
   http: HttpClient = inject(HttpClient);


  getItems() {
    return this.http.get<Amigo[]>(this.apiUrl);
  }


  addItem(item: Amigo) {
    return this.http.post<Amigo>(this.apiUrl, item);
  }


  updateItem(item: Amigo) {
    return this.http.put<Amigo>(this.apiUrl + '/' + item.id, item);
  }


  deleteItem(id: string) {
    return this.http.delete<Amigo>(this.apiUrl + '/' + id);
  }
}

