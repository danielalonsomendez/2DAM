import { Component, inject } from '@angular/core';
import { Hotel} from '../interfaces/hotel';
import { HotelesService } from '../services/hotelservice';
import { Visualizacionesservice } from '../services/visualizacionesservice';
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
  servicioresta : HotelesService = inject(HotelesService);
  visualizacionesService: Visualizacionesservice = inject(Visualizacionesservice);
  hotelList : Hotel[] = [];
  cantidad : number = 0;
  hotelFilter: Hotel[] = [];
  localidadesUnicas: string[] | undefined;
  localidadSeleccionada: string = "";
  territoriosUnicos: string[] | undefined;
  territorioSeleccionado: string = "";
  currentPage = 1; // Página actual
  localidadesporterritorio: string[] | undefined;
  localidadesporterritoriounicas: string[] | undefined;

  constructor() {
    this.servicioresta.getAllHoteles().subscribe({
      next: item => {
        this.hotelList = item;
        this.hotelFilter = this.hotelList
        this.cantidad = this.hotelFilter.length;
        this.localidadesDistintas();
        this.territoriosDistintos();
      },
      error: err => console.error(err),
    });
  }
  //FILTRAR POR LOCALIDAD
  filtrarPorLocalidad(text:String){
    
    if (!text) {
      this.hotelFilter = this.hotelList
      this.cantidad = this.hotelFilter.length;
      return;
    }
    this.hotelFilter = this.hotelList.filter((item =>
      item?.locality.toLowerCase().includes(text.toLowerCase())));
      this.cantidad = this.hotelFilter.length;
  }

  localidadesDistintas(){
    const localidadesTodas = this.hotelList.map(item => item.locality);
    this.localidadesUnicas = Array.from (new Set (localidadesTodas)).sort ();

  }

  onlocalidadchange(){
    console.log ("Localidad seleccionada: " + this.localidadSeleccionada);
    this.filtrarPorLocalidad(this.localidadSeleccionada)
  }


  //FILTRO POR TERRITORIO
  filtrarPorTerritorio(text:String){
    
    if (!text) {
      this.hotelFilter = this.hotelList
      this.cantidad = this.hotelFilter.length;
      return;
    }
    this.hotelFilter = this.hotelList.filter((item =>
      item?.territory.toLowerCase().includes(text.toLowerCase())));
      this.cantidad = this.hotelFilter.length;
  }

  territoriosDistintos(){
    const territorioTodas = this.hotelList.map(item => item.territory);
    this.territoriosUnicos = Array.from (new Set (territorioTodas)).sort ();

  }

  onterritoriochange(){
    console.log ("Territorio seleccionado: " + this.territorioSeleccionado);
    this.filtrarPorTerritorio(this.territorioSeleccionado)
  }

  obtenerVisitasHotel(hotelId: string): number {
    return this.visualizacionesService.visualizacionesPorHotel(hotelId);
  }

  tieneVisitas(hotelId: string): boolean {
    return this.obtenerVisitasHotel(hotelId) > 0;
  }
}
