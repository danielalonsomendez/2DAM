import { User } from "./user";
import { Centros } from "./centros";

export interface Reuniones {
    estado: string;
    fecha: string;
    idReunion: number;
    asunto: string;
    estadoEus?: string;
    aula: string;
    idCentro: number;
    usersByAlumnoId: User;
    usersByProfesorId: User;
    titulo: string;
    createdAt: string;
    updatedAt: string;
    centro?: Centros;
}
