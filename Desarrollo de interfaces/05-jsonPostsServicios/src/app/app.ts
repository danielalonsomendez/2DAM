import { HttpClient } from '@angular/common/http';
import { afterNextRender, Component, inject, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Servicearticulos } from './servicearticulos';
import { Idata } from './idata';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('jsonPosts');
  constructor(private servicearticulos: Servicearticulos) { }
  articulos: Idata[] = [];
  ngOnInit(): void {
    this.getArticulos();
  }
  getArticulos() {
    this.servicearticulos.getArticulos()
      .subscribe({
        next: (data: any) => {
          this.articulos = data;
          console.log(this.articulos);
        },
        error: (error) => {
          console.error('Error fetching posts:', error);
        },
        complete: () => {
          console.log('Finished fetching posts');
        }
      });
  }

}

