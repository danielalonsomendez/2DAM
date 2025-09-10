let habilidades: string[] = ['Bash', 'Counter', 'Healing'];

interface Personaje {
    nombre: string;
    hp: number;
    habilidades: string[];
    puebloNatal?: string;  // es opcional
}

const mipersonaje: Personaje = {
  nombre: 'Jorge',
  hp: 100,
  habilidades: ['aa', 'bb', 'cc'],
};
mipersonaje.puebloNatal = "Barakaldo";
console.log(mipersonaje);
console.table(mipersonaje);

let aaa=[]; // es de tipo any[]
let bbb:[12,"Jorge",true,[],{}];
let ccc:boolean[]=[];