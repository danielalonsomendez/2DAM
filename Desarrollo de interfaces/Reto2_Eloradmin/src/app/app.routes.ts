import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { MainPage } from './pages/main-page/main-page';
import { Home } from './pages/home/home';
import { UsuariosComponent } from './pages/usuarios/usuarios';
import { authGuard } from './guards/authGuard';
import { loginGuard } from './guards/loginGuard';
import { ReunionesComponent } from './pages/reuniones/reuniones';
import { roleGuard } from './guards/roleGuard';
import { CentrosComponent } from './pages/centros/centros';

export const routes: Routes = [
  {
    // Ruta raíz: layout principal que requiere autenticación.
    path: '',
    component: MainPage,
    canActivate: [authGuard],
    children: [
      // Página principal dentro del layout (dashboard).
      { path: '', component: Home },
      // Gestión de usuarios. Requiere roleGuard: roles permitidos [1,2,3].
      { path: 'usuarios', component: UsuariosComponent, canActivate: [roleGuard], data: { roles: [1, 2, 3] } },
      // Listado y gestión de reuniones.
      { path: 'reuniones', component: ReunionesComponent },
      // Listado/mapa de centros.
      { path: 'centros', component: CentrosComponent }
    ]
  },
  // Login: solo accesible si NO hay sesión activa.
  { path: 'login', component: Login, canActivate: [loginGuard] },
  // Redirige a la raíz
  { path: '', redirectTo: '/', pathMatch: 'full' },
];

