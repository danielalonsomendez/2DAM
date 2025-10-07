import { Component, signal } from '@angular/core';
import { Articulo } from './interfaces/articulo';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [CommonModule,FormsModule],
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
  borrarArticulo(codigo: number){
    for(let i = 0; i < this.articulos.length; i++){
      if (this.articulos[i].codigo == codigo) {
        if (confirm('Esta seguro que desea borrar esta linea?')){
          this.articulos.splice(i,1);
          return;
        } else {
          alert('Cancelado.');
        }
      }
    }
    alert('No existe el codigo');
  }
  agregarArticulo(){
    if (this.articulo.codigo < 0){
      alert('Cancelado');
      return;
    }
    //EL CODIGO NO DEBE EXISTIR
    for(let i = 0; i < this.articulos.length; i++){
      if (this.articulos[i].codigo == this.articulo.codigo) {
        alert ('Ingrese un codigo valido.');
        return;
      }
    }

    this.articulos.push({ codigo: this.articulo.codigo, descripcion: this.articulo.descripcion, precio: this.articulo.precio });

    //this.articulos.push(this.art);
    this.articulo.codigo = 0;
    this.articulo.descripcion = '';
    this.articulo.precio = 0;
  }

  seleccionarArticulo(articulo : Articulo){
    this.articulo = {...articulo};
  }
  modificarArticulo(){
    for(let i = 0; i < this.articulos.length; i++){
      if (this.articulos[i].codigo == this.articulo.codigo) {
        this.articulos[i] = {...this.articulo};
        return;
      }
    }
    alert('No existe el codigo');
  }

}