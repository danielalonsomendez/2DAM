import { Component, Input } from '@angular/core';
import { Personaje } from '../interfaces/dbz.interface';
@Component({
  selector: 'app-personajes',
  imports: [],
  templateUrl: './personajes.html',
  styleUrl: './personajes.css'
})
export class Personajes {
  @Input() personajes: Personaje[] = [];
}
