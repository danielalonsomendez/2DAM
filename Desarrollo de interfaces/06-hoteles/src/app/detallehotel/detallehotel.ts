import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HotelesService } from '../services/hotelservice';
import { Hotel} from '../interfaces/hotel';
import { Visualizacionesservice } from '../services/visualizacionesservice';

@Component({
  selector: 'app-detallehotel',
  imports: [],
  templateUrl: './detallehotel.html',
  styleUrl: './detallehotel.css',
})
export class Detallehotel {
  hotelId: string = '';
  hotel : Hotel| undefined;
  servicioresta : HotelesService = inject(HotelesService);
  visualizacionesService: Visualizacionesservice= inject(Visualizacionesservice);
  
  ahora: Date = new Date();
  fechaHoraActual: string = '';
  numeroPersonasInteresadas: number = 0;

  // Al iniciar el componente
  ngOnInit() {
    this.actualizarFechaHora();
    
    this.numeroPersonasInteresadas = this.visualizacionesService.visualizacionesPorHotel(this.hotelId);
    
    this.visualizacionesService.anadirCuenta(this.hotelId);
    
    this.numeroPersonasInteresadas = this.visualizacionesService.visualizacionesPorHotel(this.hotelId);
  }

  private actualizarFechaHora(): void {
    this.ahora = new Date();
    this.fechaHoraActual = 
      `${this.ahora.getFullYear()}-${(this.ahora.getMonth() + 1).toString().padStart(2, '0')}-${this.ahora.getDate().toString().padStart(2, '0')} ` +
      `${this.ahora.getHours().toString().padStart(2, '0')}:${this.ahora.getMinutes().toString().padStart(2, '0')}:${this.ahora.getSeconds().toString().padStart(2, '0')}`;
  }

  constructor(private route : ActivatedRoute) {
    this.hotelId = this.route.snapshot.params['id'];
    this.servicioresta.getHotelByIdcorto(this.hotelId).subscribe(
      item => { this.hotel = item }
    )
  }
  abrirpaginaweb(){
    window.open(this.hotel?.web);
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
