import { Component, inject, OnInit } from '@angular/core';
import { Receta } from '../../interfaces/receta';
import { ActivatedRoute } from '@angular/router';
import { Recetaservice } from '../../services/recetaservice';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Ayudantesservice } from '../../services/ayudantesservice';
import { Visualizacionesservice } from '../../services/visualizacionesservice';

@Component({
  selector: 'app-detallereceta',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './detallereceta.html',
  styleUrl: './detallereceta.css',
})
export class Detallereceta implements OnInit {
  // Receta vacia para despues rellenar
  receta: Receta = {
    id: "",
    nombre: "",
    chef: "",
    dificultad: "",
    tiempoMin: "",
    imagenUrl: ""
  };
  // Formulario Ayudante
  ayudanteForm = new FormGroup({
    nombre: new FormControl(''),
    email: new FormControl(''),
  });
  // Servicios
  route: ActivatedRoute = inject(ActivatedRoute);
  recetasService: Recetaservice = inject(Recetaservice);
  ayudantesService: Ayudantesservice = inject(Ayudantesservice);
  visualizacionesService: Visualizacionesservice= inject(Visualizacionesservice);

  // Al iniciar el componente
  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    // Cargar receta
    this.recetasService.todasLasRecetasPorID(id).subscribe((receta: Receta) => {
      this.receta = receta;
    });
    // Hacer +1 a las visualizaciones
    this.visualizacionesService.anadirCuenta(id);
  }
  // Boton añadir del formulario de ayudantes
  anadir() {
    this.ayudantesService.enviarFormulario(this.ayudanteForm.value.nombre ?? '',
      this.ayudanteForm.value.email ?? '',
      this.receta?.id ?? '0')
  }
}
