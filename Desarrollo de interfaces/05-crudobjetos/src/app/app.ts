import { Component, signal } from '@angular/core';
import { Articulo } from './interfaces/articulo';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [ FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('05-crudobjetos');
  articulos = [{ codigo: 1, descripcion: 'papas', precio: 10.55 },
  { codigo: 2, descripcion: 'manzanas', precio: 12.10 },
  { codigo: 3, descripcion: 'melon', precio: 52.30 },
  { codigo: 4, descripcion: 'cebollas', precio: 17 },
  { codigo: 5, descripcion: 'calabaza', precio: 20 },
  ];
  articulo: Articulo = { codigo: 0, precio: 0, descripcion: '' };
  borrarArticulo(articulo: Articulo) {
    this.articulos = this.articulos.filter(a => a !== articulo);
  }
  agregarArticulo() {
    this.articulos.push(this.articulo);
    this.articulo = { codigo: 0, precio: 0, descripcion: '' };
  }

  seleccionarArticulo(articulo: Articulo) {
    this.articulo = { ...articulo };
  }
  modificarArticulo() {
    const index = this.articulos.findIndex(a => a.codigo === this.articulo.codigo);
    if (index !== -1) {
      this.articulos[index] = { ...this.articulo };
    }

  }

}