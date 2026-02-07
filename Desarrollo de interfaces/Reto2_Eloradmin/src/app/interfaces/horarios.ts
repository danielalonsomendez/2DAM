import { Modulos } from "./modulos";
import { User } from "./user";

export interface Horarios {
  id: number;
  users?: User;
  modulos?: Modulos;
  dia?: string;
  hora: number;
  aula?: string;
  observaciones?: string;
  createdAt?: string;
  updatedAt?: string;
}