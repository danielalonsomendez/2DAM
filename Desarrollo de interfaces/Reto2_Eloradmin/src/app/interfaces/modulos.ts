import { Ciclos } from "./ciclos";

export interface Modulos {
  id: number;
  ciclos?: Ciclos;
  nombre?: string;
  nombreEus?: string;
  horas: number;
  curso: number;
}