import { Component } from '@angular/core';

@Component({
  selector: 'app-heroe',
  imports: [],
  templateUrl: './heroe.html',
  styleUrl: './heroe.css'
})
export class Heroe {
  nombre: string = 'Iron Man';
  edad: number = 45;

  obtenerNombre(): String {
    return this.nombre + '-' + this.edad;
  }

  //UTILIZAMOS LA PROPIEDAD GET
  get nombreCapitalizado(): String {
    return this.nombre.toUpperCase();
  }

  cambiarNombre(): void {
    this.nombre = 'Spiderman';
  }

  cambiarEdad():void{
    this.edad=30;
    console.log('Key!!!');
  }
}
