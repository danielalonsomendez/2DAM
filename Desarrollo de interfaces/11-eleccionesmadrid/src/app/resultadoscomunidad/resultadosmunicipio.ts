import { Component, inject } from '@angular/core';
import { EleccionesService } from '../services/elecciones-service';
import { Resultado } from '../interfaces/resultado';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-resultadoscomunidad',
  imports: [FormsModule,CommonModule],
  templateUrl: './resultadoscomunidad.html',
  styleUrl: './resultadoscomunidad.css',
})
export class Resultadoscomunidad {
  municipios: string[] = [];
  eleccionesService: EleccionesService = inject(EleccionesService);
  resultados: Resultado | null = null;


  ngOnInit() {
     this.eleccionesService.getAllMesasSumado().subscribe((resultado: Resultado) => {
      this.resultados = resultado;
    });
  }
  getTotalVotos(): number {
    if (!this.resultados) {
      return 0;
    }
    return this.resultados.Votos_total;
  }

  getPartidos(): string[] {
    if (!this.resultados) {
      return [];
    }
        const excludeKeys = [
      'Electores', 'Certif_alta', 'Votos_total', 'Seccion', 'Censo', 
      'Votos_nulos', 'Votos_blancos', 'Certif_correc.', 'Votos_electores', 
      'Mesa', 'Votos_Interventores', 'Distrito', 'cod_muni', 'Municipio',"id"
    ];
    
    const partidos = Object.keys(this.resultados).filter(key => !excludeKeys.includes(key));
    
    return partidos.sort((a, b) => {
      const votosA = this.getVotosPartido(a);
      const votosB = this.getVotosPartido(b);
      return votosB - votosA;
    });
  }

  getVotosPartido(partido: string): number {
    if (!this.resultados) return 0;
    return (this.resultados as any)[partido] || 0;
  }

  getPorcentajePartido(partido: string): number {
    const votos = this.getVotosPartido(partido);
    const total = this.getTotalVotos();
    return total > 0 ? (votos / total * 100) : 0;
  }
}
