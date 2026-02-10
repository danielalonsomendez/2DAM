export interface Libro {
    id: string;
    title: string;
    author: string;
    genre: string;
    year: number | null;
    language: string;
    price:number  | null;
    esClasico?: string;
    anosPublicacion?:number;
    precioDescuento?: number;
}
