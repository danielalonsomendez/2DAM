import { Component, inject } from '@angular/core';

import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { Libro } from '../../interfaces/libro';
import { LibrosService } from '../../services/librosService';
import { Datoslibro } from '../../interfaces/datoslibro';

@Component({
  selector: 'app-tabla',
  imports: [CommonModule, FormsModule, TranslateModule, NgxPaginationModule],
  templateUrl: './tabla.html',
  styleUrl: './tabla.css',
})
export class TablaComponent {
  // Arrays de libros
  data: null | Datoslibro = null;
  listaFiltrada: Libro[] = [];
  generos: string[] = [];
  idiomas: string[] = [];
  anoActual: number = 0;

  // Strings de los input de filtros
  filtroIdioma: String = "";
  filtroGeneros: String = "";
  // CRUD
  libroSeleccionado: Libro = {
    id: "",
    title: "",
    author: "",
    year: null,
    genre: "",
    language: "",
    price: null,
  };
  modoEdicion: boolean = false;
  // Paginación
  paginaActual: number = 1;
  elementosPorPagina: number = 8;

  router: Router = inject(Router);
  // Servicios
  libroService: LibrosService = inject(LibrosService);

  // Cargas los libros
  constructor() {
    this.loadLibros();
    this.anoActual = new Date().getFullYear();
  }

  loadLibros(filtrotipo1: String | null = null, filtrotexto1: String | null = null, filtrotipo2: String | null = null, filtrotexto2: String | null = null) {
    this.libroService.todosLosLibros(filtrotipo1!, filtrotexto1!, filtrotipo2!, filtrotexto2!).subscribe(data => {
      this.listaFiltrada = data.libros;
      if (filtrotipo1 == null) {
        this.data = data;
        this.generos = [...new Set(data.libros.map(r => r.genre))];
        this.idiomas = [...new Set(data.libros.map(r => r.language))];
      }
    })
  }

  // Boton aplicar filtro 
  filtrar() {
    this.paginaActual = 1;
    if (!this.filtroIdioma || this.filtroIdioma == "") {
      if (this.filtroGeneros && this.filtroGeneros != "") {
        this.loadLibros("genre", this.filtroGeneros)
        return;
      }
      this.listaFiltrada = this.data?.libros!;
      return;
    }
    if (!this.filtroGeneros || this.filtroGeneros == "") {
      if (this.filtroIdioma && this.filtroIdioma != "") {
        this.loadLibros("language", this.filtroIdioma)
        return;
      }
      this.listaFiltrada = this.data?.libros!;
      return;
    }
    this.loadLibros("language", this.filtroIdioma, "genre", this.filtroGeneros)

  }

  // CRUD - Seleccionar receta para editar
  seleccionarLibro(receta: Libro) {
    this.libroSeleccionado = { ...receta };
    this.modoEdicion = true;
    setTimeout(() => {
      document.getElementById('formulario-receta')?.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }, 100);
  }

  // CRUD - Guardar receta (crear o actualizar)
  guardarLibro() {
    if (this.libroSeleccionado.title.length == 0 || this.libroSeleccionado.author.length == 0) {
      window.alert("Titulo y autor son obligatorios")
      return;

    }
    if (this.libroSeleccionado.price! > 0 == false) {
      window.alert("Precio tiene que ser superior a 1")
      return;

    }
    if (!(this.libroSeleccionado.year! >= 1500 && this.libroSeleccionado.year! <= this.anoActual)) {
      window.alert("Año tiene que ser entre 1500 y " + this.anoActual)
      return;
    }
    if (this.libroSeleccionado.genre == "Distopía" && this.libroSeleccionado.price! > 20) {
      window.alert("¡Libro caro de distopía!")
    }
    if (this.modoEdicion) {
      // Actualizar
      this.libroService.editarLibro(this.libroSeleccionado.id, this.libroSeleccionado).subscribe({
        next: (recetaActualizada) => {
          this.loadLibros();

          this.cancelar();
        },
        error: (err) => {
          console.error('Error al actualizar receta', err);
        }
      });
    } else {
      // Crear - eliminar el id vacío para que json-server genere uno automáticamente
      const { id, ...librosinId } = this.libroSeleccionado;
      this.libroService.agregarLibro(librosinId as Libro).subscribe({
        next: (receta) => {
          this.loadLibros();
          this.cancelar();
        },
        error: (err) => {
          console.error('Error al crear receta', err);
        }
      });
    }
  }

  // CRUD - Cancelar y resetear formulario
  cancelar() {
    this.libroSeleccionado = {
      id: "",
      title: "",
      author: "",
      year: null,
      genre: "",
      language: "",
      price: null
    };
    this.modoEdicion = false;
  }

  // CRUD - Borrar receta
  borrarLibro(id: string) {
    this.libroService.eliminarLibro(id).subscribe({
      next: () => {
        this.loadLibros();
      },
      error: (err) => {
        console.error('Error al borrar receta', err);
      }
    });
  }
}
