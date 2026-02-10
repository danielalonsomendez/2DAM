import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Libro } from '../interfaces/libro';
import { Datoslibro } from '../interfaces/datoslibro';

@Injectable({
  providedIn: 'root',
})
export class LibrosService {
  private httpClient = inject(HttpClient);
  readonly baseUrl = 'http://localhost:3000';
  // Lista de todos los libros
  todosLosLibros(filtrotipo1: String | null = null, filtrotexto1: String | null = null, filtrotipo2: String | null = null, filtrotexto2: String | null = null): Observable<Datoslibro> {
    var url = `${this.baseUrl}/books?`;
    if (filtrotipo1 != null) {
      url = url + `${filtrotipo1}=${filtrotexto1}`
    }
    if (filtrotipo2 != null) {
      url = url + `&&${filtrotipo2}=${filtrotexto2}`
    }
    return this.httpClient.get<Libro[]>(url).pipe(
      map((data: Libro[]) => {
        var response :Datoslibro= { libros: data, precioPromedio: 0, sumaPrecios: 0, numeroLibros: 0, decadas: [], generos: [] }
        for (var i = 0; i < data.length; i++) {
          data[i].anosPublicacion = new Date().getFullYear() - data[i].year!
          data[i].esClasico = (data[i].anosPublicacion! > 50) ? "Clásico" : "";
          data[i].precioDescuento = (data[i].price! > 20 && data[i].genre == "Fantasía") ? (data[i].price! - (data[i].price! * 0.1)) : data[i].price!;
          response.sumaPrecios = response.sumaPrecios + data[i].precioDescuento!;
          var decada = data[i].year! / 10;
         var existeDecada = response.decadas.find(r => r.ano == Math.floor(decada) * 10);
          if (existeDecada == null) {
            response.decadas.push({ ano: Math.floor(decada) * 10, libros: 1 });
          } else {
            existeDecada.libros = existeDecada.libros + 1;
          }
          if(data[i].esClasico == "Clásico"){
          var existeGenero = response.generos.find(r => r.genero == data[i].genre);
          if (existeGenero == null) {
            response.generos.push({ genero: data[i].genre, libros: 1 });
          } else {
            existeGenero.libros = existeGenero.libros + 1;
          }
        }}
        response.precioPromedio = parseFloat((response.sumaPrecios / data.length).toFixed(2));
        response.numeroLibros = data.length;
        response.libros = data;
        return response;
      })
    );
  }

  //Libros de un  ID
  todosLasLibrosPorID(id: string): Observable<Libro> {
    return this.httpClient.get<Libro>(`${this.baseUrl}/books/${id}`);
  }

  agregarLibro(libro: Libro): Observable<Libro> {
    return this.httpClient.post<Libro>(`${this.baseUrl}/books`, libro);
  }
  editarLibro(id: string, libro: Libro): Observable<Libro> {
    return this.httpClient.put<Libro>(`${this.baseUrl}/books/${id}`, libro);
  }
  eliminarLibro(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/books/${id}`);
  }
}
