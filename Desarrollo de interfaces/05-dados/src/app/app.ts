import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Dado } from './dado/dado';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Dado],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('dados');
  valor1: number = 1;
  valor2: number = 2;
  valor3: number = 3;
  resultado: string = '';


  constructor() {
    this.valor1 = this.retornarnumeroaleatorio();
    this.valor2 = this.retornarnumeroaleatorio();
    this.valor3 = this.retornarnumeroaleatorio();
  }
  tirar(){
    this.resultado="";
    this.valor1 = this.retornarnumeroaleatorio();
    this.valor2 = this.retornarnumeroaleatorio();
    this.valor3 = this.retornarnumeroaleatorio();
    if (this.valor1 == this.valor2 || this.valor1 == this.valor3){
      this.resultado = "Has ganado";
    }
  }
  retornarnumeroaleatorio(){
    return Math.trunc(Math.random()*6)+1;

  }
}
