import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Detallehotel } from './detallehotel/detallehotel';

export const routes: Routes = [
{
    path: "home",
    component: Home
},
{
    path: "hotel/:id",
    component: Detallehotel
},
{
    path: "",
    redirectTo: "/home",
    pathMatch: "full"
}
];
