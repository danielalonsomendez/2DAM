import { Component, inject, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Table, TableModule, TableRowSelectEvent, TableRowUnSelectEvent } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { Servicio } from '../../services/servicio';
import { User } from '../../interfaces/user';
import { DialogModule } from 'primeng/dialog';
import { Tipos } from '../../interfaces/tipos';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { of, Subscription, Subject } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, map, switchMap } from 'rxjs/operators';
import { UserService } from '../../services/user';
import { Profesores } from '../home/profesores/profesores';
import { Horarios } from '../../interfaces/horarios';
import { HorariosComponent } from '../horarios/horarios';
import { TranslateModule, TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.html',
  styleUrl: './usuarios.css',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, PaginatorModule, DialogModule, FormsModule, InputTextModule, SelectModule, HorariosComponent, TranslateModule]
})
export class UsuariosComponent implements OnInit, OnDestroy {
  filteredUsers: User[] = [];
  horarioVisible = false;
  horarios: Horarios[] = [];
  users: User[] = [];
  selectedFilter: 'all' | 'alumnos' | 'profesores' = 'all';
  visible = false;
  editMode = false;
  selectedUser: User | null = null;
  usernameAvailable: boolean | null = null;
  checkingUsername = false;
  private usernameSub?: Subscription;
  globalFilterValue = '';
  @ViewChild('dt') table?: Table;
  showSearch = false;
  draftUser: User = this.createEmptyUser();
  passwordRequired = true;
  private usernameInput$ = new Subject<string>();
  roleOptions: { label: string; value: number; icon: string }[] = [];
  private langChangeSub?: Subscription;

  /**
   * Constructor del componente de gestión de usuarios.
   * Inicializa el watcher de username y las opciones de role.
   */
  constructor(private adminService: Servicio, private userService: UserService, private translate: TranslateService) {
    this.setupUsernameWatcher();
    this.roleOptions = this.buildRoleOptions();
    this.langChangeSub = this.translate.onLangChange.subscribe(() => {
      this.roleOptions = this.buildRoleOptions();
    });
  }

 

  /**
   * Carga inicial: obtiene lista de usuarios.
   */
  ngOnInit(): void {
    this.loadUsers();
  }

  ngOnDestroy(): void {
    this.usernameSub?.unsubscribe();
    this.langChangeSub?.unsubscribe();
  }


/**
 * Solicita la lista de usuarios y aplica el filtrado inicial según rol.
 */
loadUsers(): void {
  this.adminService.getUsuarios().subscribe(alumnos => {
    if (this.isProfessor) {
      this.users = alumnos.filter(u => u.tipos.id === 4 || u.tipos.id === 3);
    } else {
      this.users = alumnos;
    }
    this.applyFilter(); // aplicar filtro inicial
  });
}

/**
 * Cambia el filtro de la lista (all/alumnos/profesores) y resetea paginación.
 */
changeFilter(filter: 'all' | 'alumnos' | 'profesores'): void {
  this.selectedFilter = filter;
  this.applyFilter();

  if (this.table) {
    this.table.first = 0; // resetear paginación
  }
}

applyFilter(): void {
  if (this.selectedFilter === 'all') {
    this.filteredUsers = [...this.users];
  } else {
    this.filteredUsers = this.users.filter(u =>
      (this.selectedFilter === 'alumnos' && u.tipos.id === 4) ||
      (this.selectedFilter === 'profesores' && u.tipos.id === 3)
    );
  }
}


  /**
   * Formatea una cadena de rol capitalizando la primera letra.
   * @param role Nombre del rol (opcional).
   * @returns Cadena formateada o cadena vacía si no se proporciona.
   */
  formatRole(role?: string | null): string {
    if (!role) {
      return '';
    }

    const lower = role.toLowerCase();
    return lower.charAt(0).toUpperCase() + lower.slice(1);
  }
  /**
   * Abre el diálogo para crear un nuevo usuario (resetea estado interno).
   * Solo accesible si el usuario tiene permisos de gestión.
   */
  showDialog() { 
    if (!this.canManageUsers) {
      return;
    }
    this.editMode = false;
    this.passwordRequired = true;
    this.draftUser = this.createEmptyUser();
    this.resetUsernameValidation();
    this.visible = true; 
  }
  
  /**
   * Valida y envía la creación o actualización de un usuario.
   * - Si `editMode` actualiza el usuario seleccionado.
   * - Si no, crea un nuevo usuario con los campos del formulario.
   */
  saveUser() {
    if (!this.canManageUsers) {
      return;
    }
    if (this.isFormInvalid) {
      console.log('Formulario inválido');
      return;
    }

    if (this.usernameAvailable === false) {
      console.warn('Nombre de usuario no disponible');
      return;
    }

    const rawPassword = (this.draftUser.password || '').trim();
    const userPayload: User = structuredClone(this.draftUser);
    userPayload.username = userPayload.username.trim();
    userPayload.nombre = userPayload.nombre.trim();
    userPayload.apellidos = userPayload.apellidos.trim();
    userPayload.email = userPayload.email.trim();
    userPayload.dni = userPayload.dni.trim();
    userPayload.telefono1 = (userPayload.telefono1 || '').trim();
    userPayload.telefono2 = (userPayload.telefono2 || '').trim();
    userPayload.direccion = (userPayload.direccion || '').trim();
    userPayload.tipos = {
      ...(userPayload.tipos ?? { id: 4 }),
      id: userPayload.tipos?.id ?? 4
    } as Tipos;

    if (this.editMode && this.selectedUser) {
      const shouldChangePassword = !!rawPassword;
      const userToUpdate: User = {
        ...userPayload,
        id: this.selectedUser.id,
        password: this.selectedUser.password,
      };

      this.adminService.actualizarUsuario(this.selectedUser.id, userToUpdate).subscribe({
        next: () => {
          if (shouldChangePassword) {
            this.adminService.cambiarContrasena(this.selectedUser!.id, rawPassword).subscribe({
              next: () => this.onSaveComplete(),
              error: err => console.error(err)
            });
          } else {
            this.onSaveComplete();
          }
        },
        error: err => console.error(err)
      });
    } else {
      const newUser: User = {
        ...userPayload,
        id: 0,
        password: rawPassword,
      };
      this.adminService.crearUsuario(newUser).subscribe({
        next: () => this.onSaveComplete(),
        error: err => {
  console.error("STATUS:", err.status);
  console.error("BODY:", err.error);
}

      });
    }
  }




  /**
   * Maneja la selección de fila en la tabla de usuarios.
   * @param event Evento de selección que contiene el `User` seleccionado.
   */
  onRowSelect(event: TableRowSelectEvent<User>): void {
    this.selectedUser = event.data as User;
  }

  /**
   * Maneja la des-selección de una fila de usuarios.
   */
  onRowUnselect(event?: TableRowUnSelectEvent<User>): void {
    this.selectedUser = null;
  }

  /**
   * Maneja la búsqueda global sobre la tabla de usuarios.
   */
  onGlobalFilter(event: Event): void {
    const value = (event.target as HTMLInputElement).value;
    this.table?.filterGlobal(value, 'contains');
    this.globalFilterValue = value;
  }

  /**
   * Muestra u oculta el control de búsqueda global y limpia el filtro si se cierra.
   */
  toggleSearch(): void {
    this.showSearch = !this.showSearch;
    if (!this.showSearch) {
      this.globalFilterValue = '';
      this.table?.filterGlobal('', 'contains');
    }
  }

  /**
   * Elimina el usuario seleccionado tras confirmación. Respetando restricciones de rol.
   */
  deleteUser(): void {
    if (!this.canManageUsers) {
      return;
    }
    if (this.selectedUser?.tipos.id === 1) {
      return;
    }
    if(this.isAdmin && this.selectedUser?.tipos.id === 2){
      return;
    }
    if (!this.selectedUser) return;

    const confirmation = this.translate.instant('users.confirmDelete', {
      name: this.selectedUser.nombre,
      surname: this.selectedUser.apellidos
    });

    if (confirm(confirmation)) {
      this.adminService.borrarUsuario(this.selectedUser.id).subscribe(() => {
        this.selectedUser = null;
        this.loadUsers();
      });
    }
  }

  /**
   * Construye las opciones de rol para el select del formulario.
   * @returns Array de objetos `{label, value, icon}`
   */
  private buildRoleOptions() {
    return [
      { label: this.translate.instant('roles.admin'), value: 2, icon: 'pi pi-shield' },
      { label: this.translate.instant('roles.teacher'), value: 3, icon: 'pi pi-clipboard' },
      { label: this.translate.instant('roles.student'), value: 4, icon: 'pi pi-graduation-cap' }
    ];
  }

  /**
   * Prepara el formulario para editar el usuario seleccionado.
   */
  editUser(): void {
    if (!this.selectedUser) return;
    if(this.selectedUser?.tipos.id === 1){
      return;
    }
    if (!this.canManageUsers) {
      return;
    }
    this.editMode = true;
    this.passwordRequired = false;
    this.resetUsernameValidation();
    this.draftUser = structuredClone(this.selectedUser);
    this.draftUser.password = '';
    this.visible = true;
  }

  /**
   * Muestra el horario del usuario seleccionado consultando el servicio.
   */
  verHorario(): void {
  if (!this.selectedUser) return;

  this.horarioVisible = true;

  this.adminService.getHorariosByUserID(this.selectedUser.id).subscribe({
    next: (data) => {
      this.horarios = data; 
    },
    error: (err) => {
      console.error(err);
    }
  });
}




  /**
   * Finaliza el flujo de guardado: restablece estado y recarga la lista.
   */
  private onSaveComplete(): void {
    this.visible = false;
    this.editMode = false;
    this.draftUser = this.createEmptyUser();
    this.selectedUser = null;
    this.resetUsernameValidation();
    this.passwordRequired = true;
    this.loadUsers();
  }

  /**
   * Maneja el evento de ocultar diálogo (cancelar/ocultar).
   */
  handleDialogHide(): void {
    this.visible = false;
    this.editMode = false;
    this.passwordRequired = true;
    this.draftUser = this.createEmptyUser();
    this.resetUsernameValidation();
  }

  /**
   * Reinicia el estado de validación de username.
   */
  private resetUsernameValidation(): void {
    this.usernameAvailable = null;
    this.checkingUsername = false;
  }

  /**
   * Configura un observable para validar el username con debounce y manejo de errores.
   */
  private setupUsernameWatcher(): void {
    this.usernameSub = this.usernameInput$
      .pipe(
        debounceTime(400),
        distinctUntilChanged(),
        switchMap((value: string) => {
          const username = (value || '').trim();
          if (!username) {
            this.usernameAvailable = null;
            this.checkingUsername = false;
            return of(null);
          }

          this.checkingUsername = true;
          const userId = this.editMode ? this.selectedUser?.id : undefined;
          return this.adminService
            .verificarUsername(username, userId)
            .pipe(
              map((exists: boolean) => !exists),
              catchError(() => {
                this.usernameAvailable = false;
                return of(null);
              })
            );
        })
      )
      .subscribe((result: boolean | null) => {
        if (result === null) {
          this.checkingUsername = false;
          return;
        }

        this.usernameAvailable = result;
        this.checkingUsername = false;
      });
  }

  /**
   * Emite el nuevo valor del username para validación.
   */
  onUsernameChange(value: string): void {
    this.usernameInput$.next(value);
  }

  /**
   * Valida si el formulario de usuario es válido para envío.
   */
  get isFormInvalid(): boolean {
    const requiredFieldsValid = [
      this.draftUser.username,
      this.draftUser.nombre,
      this.draftUser.apellidos,
      this.draftUser.email,
      this.draftUser.dni
    ].every(value => !!(value && value.trim()));
    const roleSelected = !!this.draftUser.tipos?.id;
    const passwordValid = !this.passwordRequired || !!this.draftUser.password?.trim();
    return !(requiredFieldsValid && roleSelected && passwordValid);
  }

  /**
   * Crea un objeto `User` vacío con valores por defecto.
   */
  private createEmptyUser(): User {
    return {
      id: 0,
      username: '',
      password: '',
      nombre: '',
      apellidos: '',
      email: '',
      dni: '',
      tipos: { id: 4 },
      telefono1: '',
      telefono2: '',
      direccion: ''
    } as User;
  }

  /**
   * Indica si el usuario actual tiene permisos para gestionar usuarios.
   */
  get canManageUsers(): boolean {
    const roleId = this.userService.getUser()?.tipos?.id;
    return roleId === 1 || roleId === 2;
  }

  /**
   * Indica si el usuario actual es admin (tipo 2).
   */
  get isAdmin(): boolean {
    return this.userService.getUser()?.tipos?.id === 2;
  }

  /**
   * Indica si el usuario actual es profesor (tipo 3).
   */
  get isProfessor(): boolean {
    return this.userService.getUser()?.tipos?.id === 3;
  }
}
