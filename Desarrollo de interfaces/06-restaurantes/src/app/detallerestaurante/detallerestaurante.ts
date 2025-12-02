import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Restaurantesservice } from '../../restaurantesservice';
import { Restaurante } from '../restaurante';

@Component({
  selector: 'app-detallerestaurante',
  imports: [],
  templateUrl: './detallerestaurante.html',
  styleUrl: './detallerestaurante.css',
})
export class Detallerestaurante {
  restauranteId: string = '';
  restaurante : Restaurante | undefined;
  servicioresta : Restaurantesservice = inject(Restaurantesservice);


  constructor(private route : ActivatedRoute) {
    this.restauranteId = this.route.snapshot.params['id'];
    this.servicioresta.getRestauranteByIdcorto(this.restauranteId).subscribe(
      item => { this.restaurante = item }
    )
  }
  abrirpaginaweb(){
    window.open(this.restaurante?.web);
}
  abrirMapa1(direccion?: string){
    if (direccion != null){
      const url = `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(direccion)}`;
    window.open(url, '_blank');

    }
  }

  
    abrirMapa2(latitudLongitud?: string): void {
    if (!latitudLongitud) {
    console.error("Las coordenadas no están disponibles.");
    return;
    }
    const [latitud, longitud] = latitudLongitud.split(',');
    const zoom = 15;
    const url = `https://www.google.com/maps/@${latitud},${longitud},${zoom}z`;
    window.open(url, '_blank');
  }
  }
