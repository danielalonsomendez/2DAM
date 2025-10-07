import { HttpClient } from '@angular/common/http';
import { afterNextRender, Component, inject, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  ngOnInit(): void {
    this.http.get("https://jsonplaceholder.typicode.com/posts")
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
  protected readonly title = signal('jsonPosts');
  // constructor(private http:HttpClient) {} forma antigua
  // inject
  private http: HttpClient = inject(HttpClient);
  articulos: any[] = [];

}

