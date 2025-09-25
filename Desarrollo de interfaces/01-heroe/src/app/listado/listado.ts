import { Component, OnInit } from '@angular/core';
import { Heroe } from '../heroe/heroe';

@Component({
  selector: 'app-listado',
  imports: [],
  templateUrl: './listado.html',
  styleUrl: './listado.css'
})
export class Listado {
  heroes: string[] = ['aaa', 'bbb', 'ccc', 'ddd'];
  heroeBorrado = '';
  borrarTodo(){
    this.heroes= [];
  }
  borrarPrimero(){
    
    this.heroeBorrado=this.heroes.shift() || "";
  }
  borrarUltimo(){
    this.heroeBorrado=this.heroes.pop() || "";
  }

  // constructor(){
  //   console.log('constructor');
  // }
  // ngOnInit(): void {
  //   console.log('onInit');
  //   throw new Error('Method not implemented.');
  // }
}
