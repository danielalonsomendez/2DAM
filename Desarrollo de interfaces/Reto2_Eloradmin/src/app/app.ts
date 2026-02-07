import { Component, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule } from '@angular/forms';
import { Login } from './pages/login/login';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, NgxPaginationModule, ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('ElorAdmin');

  constructor(private translate: TranslateService) {
    const storedLang = localStorage.getItem('elor-lang') || 'es';
    this.translate.setDefaultLang('es');
    this.translate.use(storedLang);
  }
}

