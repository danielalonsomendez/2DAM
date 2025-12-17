import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Producto } from '../interfaces/producto';
import { Servicioproducto } from '../services/servicioproducto';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-productolista',
  imports: [FormsModule,CurrencyPipe],
  templateUrl: './productolista.html',
  styleUrl: './productolista.css',
})
export class Productolista {
  productos: Producto[] = [];
  nuevoProducto: Producto = { nombre: '', precio: 0 };
  productoSeleccionado: Producto | null = null;
  productoService: Servicioproducto = inject(Servicioproducto);

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.productoService.getProductos().subscribe({
      next: (data) => {
        this.productos = data;
        console.log(this.productos);
      },
      error: (err) => {
        console.error('Error al cargar productos', err);
      },
      complete: () => { }
    });
  }

  seleccionarProducto(producto: Producto) {
    this.productoSeleccionado = producto;
  }

  borrarProducto(id?: string) {
    this.productoService.borrarProducto(id).subscribe({
      next: () => {
        this.productos = this.productos.filter(p => p.id !== id);
      },
      error: (err) => {
        console.error('Error al borrar producto', err);
      }
    });
  }

  actualizarProducto() {
    if (this.productoSeleccionado) {
      this.productoService.actualizarProducto(this.productoSeleccionado).subscribe({
        next: (productoActualizado) => {
          //productoActualizado, es el dato que el servidor devuelve (el producto modificado)
          const index = this.productos.findIndex(p => p.id === productoActualizado.id);
          if (index !== -1) {
            this.productos[index] = productoActualizado; // si encuentra el producto en la lista
          }
          this.productoSeleccionado = null; // Cerrar el formulario de edición
        },
        error: (err) => {
          console.error('Error al actualizar producto', err);
        }
      });
    }
  }

  crearProducto() {
    this.productoService.agregarProducto(this.nuevoProducto).subscribe({
      next: (producto) => {
        this.productos.push(producto);
        this.nuevoProducto = { nombre: '', precio: 0 }; // Limpiar formulario
      },
      error: (err) => {
        console.error('Error al crear producto', err);
      }
    });
  }

}
