import { Component, inject, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html'
})
export class App {
  private translate = inject(TranslateService);

  constructor() {
    const storedLang = localStorage.getItem('examen-lang') || 'es';
    this.translate.setDefaultLang('es');
    this.translate.use(storedLang);
  }
}
