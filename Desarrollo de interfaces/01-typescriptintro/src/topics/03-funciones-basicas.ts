
// Unir strings
function sumar(a: string, b: string) {
    return a + b;
}
const resultado = sumar("Jorge", "Gonzalez")
console.log(resultado)

// Sumar números
function sumar1(a: number, b: number): number {
    return a + b;
}
const resultado1 = sumar1(1, 2)
console.log(resultado1)

// Función anónima que devuelve una suma de números
const sumar2 = (a: number, b: number): number => {
    return a + b;
}
const resultado2 = sumar2(1, 2)
console.log(resultado2)

// Argumentos en este orden (obligatorios,opcionales y por defecto)
function multiplicar(numero: number, otronumero?: number, base: number = 3): number {
    return numero * base;
}

const resultado3 = multiplicar(5, 10) // 5*3=15
console.log(resultado3)

const resultado4 = multiplicar(5) // 5*3=15
console.log(resultado4)

const resultado5 = multiplicar(5, 10, 20) // 5*20=100
console.log(resultado5)

// Funciones con objectos
interface PersonajeLOR {
    nombre: string;
    pv: number;

}
const nuevoPersonaje: PersonajeLOR = {
    nombre: "Strider",
    pv: 50
};
function curar(mipersonaje: PersonajeLOR, curarX: number): void {
    mipersonaje.pv += curarX;
}
curar(nuevoPersonaje, 20);
console.log(nuevoPersonaje);

// Funciones en interface
interface PersonajeLOR1 {
    nombre: string;
    pv: number;
    mostrarPV: () => void;
}
const nuevoPersonaje1: PersonajeLOR1 = {
    nombre: "Strider",
    pv: 50,
    mostrarPV() {
        console.log(`Puntos ''de vida: ${this.pv}`);
    }
};

nuevoPersonaje1.mostrarPV();