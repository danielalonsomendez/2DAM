import { Component } from '@angular/core';
import { Header } from '../../statics/header/header';
import { RouterOutlet } from '@angular/router';
import { Footer } from '../../statics/footer/footer';

@Component({
  selector: 'app-main-page',
  imports: [Header,RouterOutlet,Footer],
  templateUrl: './main-page.html',
  styleUrl: './main-page.css',
})
export class MainPage {

}
