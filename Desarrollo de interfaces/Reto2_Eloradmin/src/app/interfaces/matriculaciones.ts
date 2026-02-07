import { Ciclos } from "./ciclos";
import { User } from "./user";

export interface Matriculaciones {
  id: number;
  ciclos?: Ciclos;
  users?: User;
  curso: number;
  fecha?: string;
}