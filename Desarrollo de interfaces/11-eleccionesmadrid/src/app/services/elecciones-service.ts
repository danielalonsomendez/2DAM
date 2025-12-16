import { Injectable } from '@angular/core';
import { Resultado } from '../interfaces/resultado';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EleccionesService {
  apiUrl: string = 'http://localhost:3000/data';

  constructor(private http: HttpClient) { }

  getAllNombreMunicipios(): Observable<string[]> {
    return this.http.get<any[]>(`${this.apiUrl}`).pipe(
      map((data: any[]) => {
        const municipios: string[] = data.map((item: any) => item.Municipio);
        return Array.from(new Set(municipios)).sort();
      })
    );
  }

  getAllMesasSumado(): Observable<Resultado> {
    return this.http.get<any[]>(`${this.apiUrl}`).pipe(
      map((data: any[]) => {
        return data.reduce((suma: Resultado, mesa: any) => {
          Object.keys(mesa).forEach(key => {
            (suma as any)[key] = typeof mesa[key] === 'number' && key !== 'Mesa'

              ? ((suma as any)[key] || 0) + mesa[key]
              : (suma as any)[key] || mesa[key];
          });
          return suma;
        }, {} as Resultado);
      })
    );
  }
  getMesasMunicipioSumado(municipio: string): Observable<Resultado> {
    return this.http.get<any[]>(`${this.apiUrl}`).pipe(
      map((data: any[]) => {
        const mesasMunicipio = data.filter((item: any) => item.Municipio === municipio);

        return mesasMunicipio.reduce((suma: Resultado, mesa: any) => {
          Object.keys(mesa).forEach(key => {
            (suma as any)[key] = typeof mesa[key] === 'number' && key !== 'Mesa'
              ? ((suma as any)[key] || 0) + mesa[key]
              : (suma as any)[key] || mesa[key];
          });
          return suma;
        }, {} as Resultado);
      })
    );
  }
}
