import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Provincia } from '../interface/provincia';

@Injectable({
  providedIn: 'root',
})
export class ProvinciasService {
   private apiUrl = 'http://localhost:3000/provincias';  
   http: HttpClient = inject(HttpClient);


  getItems() {
    return this.http.get<Provincia[]>(this.apiUrl);
  }


  addItem(item: Provincia) {
    return this.http.post<Provincia>(this.apiUrl, item);
  }


  updateItem(item: Provincia) {
    return this.http.put<Provincia>(this.apiUrl + '/' + item.ID_PROVINCIA, item);
  }


  deleteItem(id: string) {
    return this.http.delete<Provincia>(this.apiUrl + '/' + id);
  }
}

