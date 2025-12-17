import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { Amigo } from './interface/amigo';
import { Amigos } from './services/amigos';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('frontend');
  items: Amigo[] = [];
  unamigo = { id: '', nombre: '' };
  mierror!: string;
  itemservice: Amigos = inject(Amigos);


  ngOnInit(): void {
    this.loadItems();
  }


  loadItems() {
    this.unamigo = { id: '', nombre: '' };
    this.itemservice.getItems().subscribe({
      next: (data) => { this.items = data; },
      error: (e) => { this.mierror = 'error en load' },
      complete: () => { }
    });
  }

  selectItem(item: Amigo) { this.unamigo.id = item.id; this.unamigo.nombre = item.nombre; }

  
  deleteItem(id: string) {
    this.mierror = "";
    const exists = this.items.some(aaa => aaa.id == this.unamigo.id);
    // C# tiene el método .any;  JavaScript/TypeScript tiene el método .some
    if (exists) {
      this.itemservice.deleteItem(id).subscribe({
        next: (data) => { },
        error: (e) => { this.mierror = 'error en delete' },
        complete: () => { this.loadItems(); }
      });
    }
    else {
      this.mierror = "no existe el id";
      this.loadItems();
    }
  }


  addItem() {
    this.mierror = "";
    if (!this.unamigo.id.trim() || this.unamigo.id.trim() === "0" || this.unamigo.id.trim() < "0" || !this.unamigo.nombre.trim()) {
      this.mierror = "El ID no puede ser 0 o negativo ni estar vacío, y el nombre es obligatorio.";
      return;
    }


    const exists = this.items.some(aaa => aaa.id == this.unamigo.id);
    if (!exists) {
      this.itemservice.addItem(this.unamigo).subscribe({
        next: (data) => { },
        error: (e) => { this.mierror = 'error en add' },
        complete: () => { this.loadItems(); }
      });
    }
    else {
      this.mierror = "ya existe el id";
      this.loadItems();
    }
  }


  updateItem() {
    this.mierror = "";
    const exists = this.items.some(aaa => aaa.id == this.unamigo.id);
    if (exists) {
      this.itemservice.updateItem(this.unamigo).subscribe({
        next: (data) => { },
        error: (e) => { this.mierror = 'error en update' },
        complete: () => { this.loadItems(); }
      });
    } else {
      this.mierror = "no existe el id";
      this.loadItems();
    }
  }
//Si trabajas con un array de números o strings, se usa includes: if (items.includes(itemToFind))
//Si trabajas con un array de objetos se usa el método some



}
