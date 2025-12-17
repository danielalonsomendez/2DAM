import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { Provincia } from '../interfaces/provincia';
import { ServicioProvincia } from '../services/servicioprovincia';

@Component({
  selector: 'app-provincialista',
  imports: [FormsModule],
  templateUrl: './provincialista.html',
  styleUrl: './provincialista.css',
})
export class Provincialista {
  provincias: Provincia[] = [];
  nuevaProvincia: Provincia = { NOMBRE: '' };
  provinciaSeleccionada: Provincia | null = null;
  provinciaService: ServicioProvincia = inject(ServicioProvincia);

  ngOnInit(): void {
    this.cargarProvincias();
  }

  cargarProvincias(): void {
    this.provinciaService.getProvincias().subscribe({
      next: (data) => {
        this.provincias = data;
        console.log(this.provincias);
      },
      error: (err) => {
        console.error('Error al cargar provincias', err);
      },
      complete: () => { }
    });
  }

  seleccionarProvincia(provincia: Provincia) {
    this.provinciaSeleccionada = provincia;
  }

  borrarProvincia(id?: string) {
    this.provinciaService.borrarProvincia(id).subscribe({
      next: () => {
        this.provincias = this.provincias.filter(p => p.ID_PROVINCIA !== id);
      },
      error: (err) => {
        console.error('Error al borrar provincia', err);
      }
    });
  }

  actualizarProvincia() {
    if (this.provinciaSeleccionada) {
      this.provinciaService.actualizarProvincia(this.provinciaSeleccionada).subscribe({
        next: (provinciaActualizada) => {
          //provinciaActualizada, es el dato que el servidor devuelve (la provincia modificada)
          const index = this.provincias.findIndex(p => p.ID_PROVINCIA === provinciaActualizada.ID_PROVINCIA);
          if (index !== -1) {
            this.provincias[index] = provinciaActualizada; // si encuentra la provincia en la lista
          }
          this.provinciaSeleccionada = null; // Cerrar el formulario de edición
        },
        error: (err) => {
          console.error('Error al actualizar provincia', err);
        }
      });
    }
  }

  crearProvincia() {
    this.provinciaService.agregarProvincia(this.nuevaProvincia).subscribe({
      next: (provincia) => {
        this.provincias.push(provincia);
        this.nuevaProvincia = { NOMBRE: '' }; // Limpiar formulario
      },
      error: (err) => {
        console.error('Error al crear provincia', err);
      }
    });
  }

}
