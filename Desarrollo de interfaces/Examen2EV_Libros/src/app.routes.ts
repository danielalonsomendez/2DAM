import { Routes } from '@angular/router';

import { MainPage } from './layouts/main-page/main-page';
import { Login } from './pages/login/login';
import { authGuard } from './guards/authGuard';
import { loginGuard } from './guards/loginGuard';
import { TablaComponent } from './pages/tabla/tabla';

export const routes: Routes = [
    { path: '', redirectTo: '/libros', pathMatch: 'full' },
    {
        path: '',
        component: MainPage,
        canActivate: [authGuard],
        children: [
            { path: "libros", component: TablaComponent }
        ]
    },
    { path: "login", component: Login, canActivate: [loginGuard] },
];
