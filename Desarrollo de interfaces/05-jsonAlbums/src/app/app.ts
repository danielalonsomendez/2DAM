import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Album } from './idata';
import { Servicealbums } from './servicealbum';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('jsonAlbums');
  albums: Album[] = [];
  constructor(private servicealbums: Servicealbums) { }
  ngOnInit(): void {
    this.getAlbums();
  }

  getAlbums() {
    this.servicealbums.getAlbums()
      .subscribe({
        next: (data: Album[]) => {
          this.albums = data;
          console.log(this.albums);
        },
        error: (error) => {
          console.error('Error fetching albums:', error);
        },
        complete: () => {
          console.log('Finished fetching albums');
        }
      });
  }

}
