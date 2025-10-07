import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { User } from './idata';
import { Serviceusers } from './serviceusers';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('jsonUsers');
  usuarios: User[] = [];
  constructor(private serviceusers: Serviceusers) { }
  ngOnInit(): void {
    this.getUsuarios();
  }

  getUsuarios() {
    this.serviceusers.getUsers()
      .subscribe({
        next: (data: User[]) => {
          this.usuarios = data;
          console.log(this.usuarios);
        },
        error: (error) => {
          console.error('Error fetching users:', error);
        },
        complete: () => {
          console.log('Finished fetching users');
        }
      });
  }

}
