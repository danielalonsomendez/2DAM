import { Routes } from '@angular/router';
import { Resultadosmunicipio } from './resultadosmunicipio/resultadosmunicipio';
import { Resultadoscomunidad } from './resultadoscomunidad/resultadosmunicipio';

export const routes: Routes = [
    {
        path: 'municipio',
        component: Resultadosmunicipio
    },
    {
        path: 'comunidad',
        component: Resultadoscomunidad
    },
    { path: '', redirectTo: '/municipio', pathMatch: 'full' }
];
