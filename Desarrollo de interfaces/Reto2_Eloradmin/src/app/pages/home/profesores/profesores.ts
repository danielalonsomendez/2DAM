import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserService } from '../../../services/user';
import { HorariosComponent } from '../../horarios/horarios';
import { Horarios } from '../../../interfaces/horarios';
import { Servicio } from '../../../services/servicio';
import { User } from '../../../interfaces/user';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-profesores',
  imports: [CommonModule, RouterLink, HorariosComponent, TranslateModule],
  templateUrl: './profesores.html',
  styleUrl: './profesores.css',
})
export class Profesores implements OnInit {
  /**
   * @param userService Servicio para obtener datos del usuario actual.
   * @param adminService Servicio para obtener datos del backend.
   */
  constructor(public userService: UserService, private adminService: Servicio) { }

  /**
   * Saludo personalizado para el profesor.
   */
  get greeting(): string {
    const name = this.userService.getUser()?.nombre || '';
    return name ? `Hola, ${name} 👋` : 'Profesor';
  }
  horarios: Horarios[] = [];
  user: User | null = null;

  /**
   * Carga los horarios del usuario al inicializar.
   */
  ngOnInit(): void {
    const userId = this.userService.getUser()!.id;
    if (userId === null) {
      return;
    }

    this.adminService.getHorariosByUserID(userId).subscribe(data => {
      this.user = this.userService.getUser();
      if (this.user?.tipos.id === 1 || this.user?.tipos.id === 2) {
        return;
      }
      this.horarios = data;
    });
  }
  
}
