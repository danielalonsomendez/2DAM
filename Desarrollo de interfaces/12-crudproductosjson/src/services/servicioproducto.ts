import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Producto } from '../interfaces/producto';

@Injectable({
  providedIn: 'root',
})
export class Servicioproducto {
  http: HttpClient = inject(HttpClient);
  private apiUrl = 'http://localhost:3000/productos';

  getProductos() {
    return this.http.get<Producto[]>(this.apiUrl);
  }

  agregarProducto(miproducto: Producto) {
    return this.http.post<Producto>(this.apiUrl, miproducto);
  }

  actualizarProducto(miproducto: Producto) {
    const url = this.apiUrl + '/' + miproducto.id;
    return this.http.put<Producto>(url, miproducto);
  }

  borrarProducto(id?: string) {
    const url = this.apiUrl + '/' + id;
    return this.http.delete<void>(url);
  }

}
