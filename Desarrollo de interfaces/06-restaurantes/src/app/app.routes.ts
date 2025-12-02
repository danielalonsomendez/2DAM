import { Routes } from '@angular/router';
import { Home } from '../home/home';
import { Detallerestaurante } from './detallerestaurante/detallerestaurante';

export const routes: Routes = [
{
    path: "home",
    component: Home
},
{
    path: "restaurante/:id",
    component: Detallerestaurante
},
{
    path: "",
    redirectTo: "/home",
    pathMatch: "full"
}
];
