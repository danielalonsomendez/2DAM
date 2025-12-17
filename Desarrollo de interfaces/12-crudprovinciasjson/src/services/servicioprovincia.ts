import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Provincia } from '../interfaces/provincia';

@Injectable({
  providedIn: 'root',
})
export class ServicioProvincia {
  http: HttpClient = inject(HttpClient);
  private apiUrl = 'http://localhost:3000/provincias';

  getProvincias() {
    return this.http.get<Provincia[]>(this.apiUrl);
  }

  agregarProvincia(miprovincia: Provincia) {
    return this.http.post<Provincia>(this.apiUrl, miprovincia);
  }

  actualizarProvincia(miprovincia: Provincia) {
    const url = this.apiUrl + '/' + miprovincia.id;
    return this.http.put<Provincia>(url, miprovincia);
  }

  borrarProvincia(id?: string) {
    const url = this.apiUrl + '/' + id;
    return this.http.delete<void>(url);
  }

}
