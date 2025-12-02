import { Component, inject } from '@angular/core';
import { Restaurante } from '../app/restaurante';
import { Restaurantesservice } from '../restaurantesservice';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-home',
  imports: [RouterLink,FormsModule,NgxPaginationModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  servicioresta : Restaurantesservice = inject(Restaurantesservice);
  restauranteList : Restaurante[] = [];
  cantidad : number = 0;
  restauranteFilter: Restaurante[] = [];
  localidadesUnicas: string[] | undefined;
  localidadSeleccionada: string = "";
  territoriosUnicos: string[] | undefined;
  territorioSeleccionado: string = "";
  currentPage = 1; // Página actual
  localidadesporterritorio: string[] | undefined;
  localidadesporterritoriounicas: string[] | undefined;

  constructor() {
    this.servicioresta.getAllRestaurantes().subscribe({
      next: item => {
        this.restauranteList = item;
        this.restauranteFilter = this.restauranteList
        this.cantidad = this.restauranteFilter.length;
        this.localidadesDistintas();
        this.territoriosDistintos();
      },
      error: err => console.error(err),
    });
  }
  //FILTRAR POR LOCALIDAD
  filtrarPorLocalidad(text:String){
    
    if (!text) {
      this.restauranteFilter = this.restauranteList
      this.cantidad = this.restauranteFilter.length;
      return;
    }
    this.restauranteFilter = this.restauranteList.filter((item =>
      item?.locality.toLowerCase().includes(text.toLowerCase())));
      this.cantidad = this.restauranteFilter.length;
  }

  localidadesDistintas(){
    const localidadesTodas = this.restauranteList.map(item => item.locality);
    this.localidadesUnicas = Array.from (new Set (localidadesTodas)).sort ();

  }

  onlocalidadchange(){
    console.log ("Localidad seleccionada: " + this.localidadSeleccionada);
    this.filtrarPorLocalidad(this.localidadSeleccionada)
  }


  //FILTRO POR TERRITORIO
  filtrarPorTerritorio(text:String){
    
    if (!text) {
      this.restauranteFilter = this.restauranteList
      this.cantidad = this.restauranteFilter.length;
      return;
    }
    this.restauranteFilter = this.restauranteList.filter((item =>
      item?.territory.toLowerCase().includes(text.toLowerCase())));
      this.cantidad = this.restauranteFilter.length;
  }

  territoriosDistintos(){
    const territorioTodas = this.restauranteList.map(item => item.territory);
    this.territoriosUnicos = Array.from (new Set (territorioTodas)).sort ();

  }

  onterritoriochange(){
    console.log ("Territorio seleccionado: " + this.territorioSeleccionado);
    this.filtrarPorTerritorio(this.territorioSeleccionado)
  }
}
