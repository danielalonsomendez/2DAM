import { Horarios } from "./horarios";

export interface HorarioFila {
  hora: number;
  lunes?: Horarios;
  martes?: Horarios;
  miercoles?: Horarios;
  jueves?: Horarios;
  viernes?: Horarios;
}
