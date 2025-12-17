import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { Provincia } from './interface/provincia';
import { ProvinciasService } from './services/provincia';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('frontend');
  items: Provincia[] = [];
  unamigo = { ID_PROVINCIA: 0, NOMBRE: '' };
  mierror!: string;
  itemservice: ProvinciasService = inject(ProvinciasService);


  ngOnInit(): void {
    this.loadItems();
  }


  loadItems() {
    this.unamigo = { ID_PROVINCIA: 0, NOMBRE: '' };
    this.itemservice.getItems().subscribe({
      next: (data) => { this.items = data; },
      error: (e) => { this.mierror = 'error en load' },
      complete: () => { }
    });
  }

  selectItem(item: Provincia) { this.unamigo.ID_PROVINCIA = item.ID_PROVINCIA; this.unamigo.NOMBRE = item.NOMBRE; }

  
  deleteItem(id: string) {
    this.mierror = "";
    const exists = this.items.some(aaa => aaa.ID_PROVINCIA == this.unamigo.ID_PROVINCIA);
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
    if (!this.unamigo.ID_PROVINCIA || this.unamigo.ID_PROVINCIA < 1 || !this.unamigo.NOMBRE.trim()) {
      this.mierror = "El ID no puede ser 0 o negativo ni estar vacío, y el nombre es obligatorio.";
      return;
    }


    const exists = this.items.some(aaa => aaa.ID_PROVINCIA == this.unamigo.ID_PROVINCIA);
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
    const exists = this.items.some(aaa => aaa.ID_PROVINCIA == this.unamigo.ID_PROVINCIA);
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
