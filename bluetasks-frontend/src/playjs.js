/*const n = 10;
let x = 20;
x = x + 10;
console.log(n);
console.log(x);*/

/*const v1 = 40.5;
const v2 = 30;
const v3 = false;
const v4 = "olá, Softblue!";
//const v5 = "Eu tenho " + v2 + " anos";
const v5 = `Eu tenho ${v2} anos`;
console.log(v5);*/

/*const x = 10;
const y = "10";

if (x == y) {
    console.log("x e y são iguais");
}*/

/*const numeros = [1, 2, 3, 4];
console.log(numeros);
const e = numeros[1];
console.log(e);

const c = {
    cor: "azul",
    ano: 2010
}
console.log(c.ano);

const carros = [
    { cor: "vermelho", ano: 2000 },
    {
        cor: "branco",
        ano: 2020
    },
    {
        cor: "amarelo",
        ano: 1982
    }
]
console.log(carros[2].ano);*/

/*function print(msg) {
    console.log(msg);
}

const print2 = msg => console.log(msg);
const print3 = () => console.log("mensagem padrão");
const mult = (a, b) => a * b;

print("você está na Softblue!");
print2("este é o print2");
print3();
const r = mult(2, 4);
print(r);

class Carro {

    constructor() {
        this.velAtual = 0;
    }

    acelerar(v) {
        this.velAtual += v;
    }

    velocidade() {
        return this.velAtual;
    }
}

const carro = new Carro();
carro.acelerar(40);
carro.acelerar(10);
print(carro.velocidade());*/

/*const multiplicar = (a, b) =>  a * b;

function processar(v1, v2, op) {
    return op(v1, v2);
}

const result = processar(4, 5, (a, b) =>  a * b);
console.log(result);

const numeros = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
console.log(numeros.map(v => v * 2));

console.log(numeros.filter(v => v % 2 !== 0));*/
/*const letters = [ "a", "b", "c" ];
console.log(letters);
console.log(...letters);

const person = {
    name: "Joao",
    age: 20
}

const otherPerson = {
    ...person,
    hand: "left"
}

console.log(person);
console.log(otherPerson);*/