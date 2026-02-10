import { Libro } from "./libro";

export interface Datoslibro {
    libros: Libro[], 
    precioPromedio: number, 
    sumaPrecios: number, 
    numeroLibros: number, 
    decadas: { ano: number, libros: number }[], 
    generos: { genero: string, libros: number }[]
}
