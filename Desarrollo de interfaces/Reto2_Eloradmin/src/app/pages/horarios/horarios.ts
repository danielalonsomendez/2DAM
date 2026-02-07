import { Component, Input, OnInit, OnChanges, SimpleChanges, inject } from '@angular/core';
import { HorarioFila } from '../../interfaces/horario-fila';
import { Horarios } from '../../interfaces/horarios';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-horarios',
  standalone: true,
  imports: [CommonModule, TableModule, TranslateModule],
  templateUrl: './horarios.html',
  styleUrl: './horarios.css',
})
export class HorariosComponent implements OnInit, OnChanges {
  @Input() horarios: Horarios[] = [];
  horarioTabla: HorarioFila[] = [];

  /**
   * Inicializa el componente.
   * Llama a `armarTabla` para construir la representación de la tabla a partir de `horarios` recibidos.
   */
  ngOnInit(): void {
    this.armarTabla(this.horarios);
  }

  /**
   * Detecta cambios en las propiedades de entrada.
   * Si `horarios` cambia, reconstruye la tabla llamando a `armarTabla`.
   * @param changes Cambios detectados por Angular en las propiedades de entrada.
   */
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['horarios']) {
      this.armarTabla(this.horarios);
    }
  }

  /**
   * Construye `horarioTabla` a partir de un array de `Horarios`.
   * - Crea filas por hora (1..6) y columnas por día (LUNES..VIERNES).
   * - Para cada celda, selecciona el horario que coincide con hora y día.
   * - Si hay múltiples coincidencias (alumnos), aplica prioridad:
   *   1. aula que incluye '5.012'
   *   2. la primera coincidencia
   *   3. null si no hay coincidencias
   * @param datos Lista de entradas de tipo `Horarios` desde la que se construye la tabla.
   */
  // 🔥 Funciona para profesores y alumnos
  private armarTabla(datos: Horarios[]) {
    const horas = [1, 2, 3, 4, 5, 6];
    const dias = ['LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES'];

    this.horarioTabla = horas.map(h => {
      const fila: any = { hora: h };

      dias.forEach(d => {
        const matches = datos.filter(item => {
          const diaDB = item.dia?.trim().toUpperCase();
          return item.hora === h && diaDB === d;
        });

        // PROFESOR → matches.length === 1 → perfecto
        // ALUMNO → matches.length > 1 → elegimos la correcta
        fila[d.toLowerCase()] =
          matches.find(c => c.aula?.includes('5.012')) || // prioridad opcional
          matches[0] ||                                   // si no, la primera
          null;                                           // si no hay nada
      });

      return fila;
    });
  }
  /**
   * Devuelve la clase CSS de color correspondiente a un nombre de módulo.
   * Normaliza el nombre (sin acentos, en minúsculas) y busca en un mapeo predefinido.
   * @param nombre Nombre del módulo/actividad (opcional).
   * @returns Nombre de la clase CSS de color; retorna 'color-default' si no hay coincidencia.
   */
  getColorClass(nombre?: string): string {
    if (!nombre) return 'color-default';

    const key = nombre
      .trim()
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '');

    const map: { [key: string]: string } = {
      'tutoria': 'color-1',
      'guardia': 'color-2',
      'sistemas informaticos': 'color-3',
      'bases de datos': 'color-4',
      'programacion': 'color-5',
      'lenguajes de marcas': 'color-6',
      'entornos de desarrollo': 'color-7',
      'acceso a datos': 'color-8',
      'desarrollo de interfaces': 'color-9',
      'programacion multimedia y dispositivos moviles': 'color-10',
      'programacion de servicios y procesos': 'color-11',
      'sistemas de gestion empresarial': 'color-12',
      'empresa e iniciativa emprendedora': 'color-13',
      'opt.moviles': 'color-14',
      'linq': 'color-15',
      'itinerarios personales': 'color-16',
      'ingles tecnico': 'color-17',
      'digitalizacion': 'color-18',
      'sostenibilidad': 'color-19'
    };

    return map[key] ?? 'color-default';
  }
}
