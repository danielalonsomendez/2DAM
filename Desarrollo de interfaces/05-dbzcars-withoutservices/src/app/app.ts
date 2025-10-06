import { Component, signal } from '@angular/core';
import { MainPage } from './dbz/main-page/main-page';
import { Coche } from './dbz/interfaces/car.interface';

@Component({
  selector: 'app-root',
  imports: [MainPage],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('dbz');
}
