import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { User } from '../../../interfaces/user';
import { Servicio } from '../../../services/servicio';
import { UserService } from '../../../services/user';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admins.html',
  styleUrl: './admins.css',
  standalone: true,
  imports: [CommonModule, RouterLink, TranslateModule]
})
export class AdminsUsersComponent implements OnInit {

  users: User[] = [];
  totalAlumnos = 0;
  totalProfesores = 0;
  reunionesHoy = 0;
  /**
   * @param adminService Servicio para obtener datos del backend.
   * @param userService Servicio de usuario (info de sesión).
   */
  constructor(private adminService: Servicio, public userService: UserService) {}

  /**
   * Inicializa el componente cargando usuarios y reuniones del día.
   */
  ngOnInit(): void {
    this.loadUsers();
    this.loadReuniones();
  }

  loadUsers(): void {
    this.adminService.getUsuarios().subscribe(users => {
        this.totalAlumnos = users.filter(u => u.tipos.id === 4).length;
        this.totalProfesores = users.filter(u => u.tipos.id === 3).length;
    });
  }
  loadReuniones(): void {
    const today = new Date();
    const start = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    const end = new Date(start);
    end.setDate(end.getDate() + 1);

    this.adminService.getReuniones().subscribe(reuniones => {
      this.reunionesHoy = reuniones.filter(r => {
        const fecha = r.fecha ? new Date(r.fecha) : null;
        return fecha && fecha >= start && fecha < end;
      }).length;
    });
  }

  get summary(): Array<{ labelKey: string; value: number; accent: string; description?: string; icon: string }> {
    return [
      { labelKey: 'dashboard.summary.students', value: this.totalAlumnos, accent: '#3057ff', icon: 'pi pi-graduation-cap'},
      { labelKey: 'dashboard.summary.teachers', value: this.totalProfesores, accent: '#34a853', icon: 'pi pi-briefcase' },
      { labelKey: 'dashboard.summary.meetingsToday', value: this.reunionesHoy, accent: '#fbbc05', icon: 'pi pi-calendar' }
    ];
  }
  
}
