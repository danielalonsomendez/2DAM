import { Component } from '@angular/core';
import { UserService } from '../../services/user';
import { GodUsersComponent } from "./god/god";
import { AdminsUsersComponent } from "./admins/admins";
import { Profesores } from "./profesores/profesores";
import { Alumnos } from "./alumnos/alumnos";

@Component({
  selector: 'app-home',
  imports: [GodUsersComponent, AdminsUsersComponent, Profesores, Alumnos, AdminsUsersComponent],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  /**
   * Componente principal del área privada; expone información de usuario.
   * @param userService Servicio de usuario para obtener estado de sesión.
   */
  constructor(public userService: UserService) {}  

  /**
   * Tipo (rol) del usuario actual.
   */
  get userType(): number | undefined {
    return this.userService.getUser()?.tipos?.id;
  }
}
