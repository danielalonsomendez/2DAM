import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('bases');
  public titulo = "Bases de Angular";
  nombre = "Jorge";
  edad = 18;
  email = "infjgonzalez@ee.com"
  sueldos = [4000, 12000, 125]
  sitio = "https://www.google.com";
  activo = true;
  esActivo() {
    if (this.activo) {
      return "Trabajador activo"
    } else {
      return "Trabajador inactivo"
    }
  }
  sumaSueldos(){
    let suma=0 // let local a la funcion sumaSueldos;
    // calcular mediante un for la suma de los sueldos
    for (let i = 0; i < this.sueldos.length; i++) {
      suma += this.sueldos[i];
    }
    return suma;
  }
  sumaSueldos2(){
    let suma=0 // let local a la funcion sumaSueldos;
    // calcular mediante un for each la suma de los sueldos
    this.sueldos.forEach(sueldo => suma += sueldo);
    return suma;
  }
}
