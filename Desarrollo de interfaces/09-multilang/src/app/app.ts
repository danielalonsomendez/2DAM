import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TranslateModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {
  title = 'ng-multi-lang';
  mitranslate: TranslateService = inject(TranslateService)
//podemos utilizar el constructor o injectar el servicio


  translatetext(lang: string) {
    this.mitranslate.use(lang);
  }
}
