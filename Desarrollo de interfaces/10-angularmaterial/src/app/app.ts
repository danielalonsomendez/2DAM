import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [    CommonModule, 
    FormsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatCheckboxModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('10-angularmaterial');
  nombreUsuario:string='';
  saludar() {
    // alert(`Hola, ${this.nombreUsuario || 'Invitado'}!`);
    let nombreAMostrar: string ='';
    // var variable global, let variable de bloque
    if (this.nombreUsuario) {
        nombreAMostrar = this.nombreUsuario;
    } else {
        nombreAMostrar = 'Invitado';
    }
    alert("Hola, " + nombreAMostrar + "!");
  }

}
