import { Tipos } from "./tipos";

export interface User {
  id: number;
  username: string;
  password: string;
  nombre: string;
  apellidos: string;
  email: string;
  dni: string;
  direccion?: string;
  telefono1?: string;
  telefono2?: string;
  tipos: Tipos;
  argazkiaUrl?: string;
  createdAt?: string;
  updatedAt?: string;
}
