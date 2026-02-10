import { Component, inject, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { UserService } from '../../services/userService';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-main-page',
  imports: [RouterOutlet, TranslateModule],
  templateUrl: './main-page.html',
  styleUrl: './main-page.css',
})
export class MainPage {
  protected readonly title = signal('Eval1Daniel');
  router: Router = inject(Router);
  private userservice = inject(UserService);
  private translate = inject(TranslateService);
  idiomaActual: string = 'es';
 private langSub?: Subscription;

  protected get currentLanguage(): string {
    return this.translate.currentLang || this.translate.defaultLang || 'es';
  }
  constructor() {
    this.idiomaActual= this.currentLanguage;
    this.langSub = this.translate.onLangChange.subscribe(() => {
this.idiomaActual= this.currentLanguage;
    });
  }

  cambiarIdioma(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.idiomaActual = select.value;
    localStorage.setItem('examen-lang', select.value);
    this.translate.use(this.idiomaActual);
  }

  logout() {
    this.userservice.logout();
  }
}
