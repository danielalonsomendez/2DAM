import { Component, inject } from '@angular/core';
import { Recetaservice } from '../../services/recetaservice';
import { Receta } from '../../interfaces/receta';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-recetas',
  imports: [CommonModule, FormsModule],
  templateUrl: './recetas.html',
  styleUrl: './recetas.css',
})
export class Recetas {
  // Arrays de recetas
  recetas: Receta[] = [];
  listaFiltrada: Receta[] = [];
  // Strings de los input de filtros
  filtroNombre: String = "";
  filtroChef: String = "";

  router: Router = inject(Router);
  // Servicios
  recetaService: Recetaservice = inject(Recetaservice);

  // Cargas las recetas
  constructor() {
    this.recetaService.todasLasRecetas().subscribe(data => {
      this.recetas = data;
      this.listaFiltrada = data;
    })
  }
  // Redirigir a la pagina de detalle
  irReceta(id: String) {
    this.router.navigate(["/receta", id],)
  }
  // Boton aplicar filtro nombre
  filtrarNombre() {
    const search = this.filtroNombre.toLowerCase().trim();
    if (!search || search == "") {
      this.listaFiltrada = this.recetas;
      return;
    }
    this.listaFiltrada = this.recetas.filter(item =>
      item.nombre.toLowerCase().includes(search)
    );
  }  
  // Boton aplicar filtro chef
  filtrarChef() {
    const search = this.filtroChef.toLowerCase().trim();
    if (!search || search == "") {
      this.listaFiltrada = this.recetas;
      return;
    }
    this.listaFiltrada = this.recetas.filter(item =>
      item.chef.toLowerCase().includes(search)
    );
  }
}
