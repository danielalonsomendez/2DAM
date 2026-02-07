import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user';
import { RouterModule } from '@angular/router';
import { Servicio } from '../../../services/servicio';
import { Horarios } from '../../../interfaces/horarios';
import { User } from '../../../interfaces/user';
import { HorariosComponent } from '../../horarios/horarios';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-alumnos',
  imports: [RouterModule, HorariosComponent, TranslateModule],
  templateUrl: './alumnos.html',
  styleUrl: './alumnos.css',
})
export class Alumnos implements OnInit {
  /**
   * @param userService Servicio para obtener datos del usuario actual.
   * @param adminService Servicio para obtener datos del backend.
   */
  constructor(public userService: UserService, private adminService: Servicio) { }

  /**
   * Saludo personalizado para el alumno.
   */
  get greeting(): string {
    const name = this.userService.getUser()?.nombre || '';
    return name ? `Hola, ${name} 👋` : 'Alumno';
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
