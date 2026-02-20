console.log('Ejercicio 1: Contador del 1 al 10');

const bucle1_10 = () => {
    for (let i = 1; i < 11; i++) {
        console.log(i);
    }
}

bucle1_10();

console.log('Ejercicio 2: Números Pares del 1 al 20');

const NumerosPares1_20 = () => {
    for (let i = 1; i <= 20; i++) {
        if (i % 2 === 0) {
            console.log(i);
        }
    }
}

NumerosPares1_20();

console.log('Ejercicio 3: Impares en Orden Descendente');

const imparesDescendente = () => {
    let array = [];
    for (let i = 50; i > 10; i--) {
        if (i % 2 === 1) {
            array.push(i);
        }
    }
    console.log(array.join('|'));
}

imparesDescendente();

console.log('Ejercicio 4: Cuenta atrás personalizada');

/**
 * 
 * @param {number} num 
 */

const cuentaAtras = (num) => {
    let array = [];
    for (let i = num; i >= 0; i--) {
        array.push(i);

    }
    console.log(array.join(' '));
}

cuentaAtras(2);

console.log('Ejercicio 5: Rango de números pares');

/**
 * 
 * @param {number} num1 
 * @param {number} num2 
 */

const rangoDePares = (num1, num2) => {
    let array = [];

    for (let i = num1; i <= num2; i++) {
        if (i % 2 === 0) {
            array.push(i);
        }
    }
    console.log(array.join(' '));

}

rangoDePares(23, 65);

console.log('Ejercicio 6: Tabla de multiplicar');

/**
 * 
 * @param {number} num1 
 */

const tablaMultiplicar = (num1) => {
    let array = [];
    for (let i = 0; i < 11; i++) {
        array.push(`${num1} x ${i} = ${num1 * i}\n`);
    }
    console.log(array.join('').toString());
}

tablaMultiplicar(3);

console.log('Ejercicio 7: Tabla de multiplicar inversa');

/**
 * 
 * @param {number} num1 
 */

const tablaMultiplicarInversa = (num1) => {
    let array = [];
    for (let i = 10; i >= 0; i--) {
        array.push(`${num1} x ${i} = ${num1 * i}\n`);
    }
    console.log(array.join('').toString());
}

tablaMultiplicarInversa(3);

console.log('Ejercicio 8: Histórico de Cumpleaños');

/**
 * 
 * @param {number} anio 
 * @param {number} edad 
 */

const historic = (anio, edad) => {
    const nacimiento = anio - edad;
    let array = [];
    for (let i = 0; i <= edad; i++) {
        if (i === 0) {
            array.push(`Naciste en el año ${nacimiento}\n`)
        } else {
            array.push(`En el año ${nacimiento + i} cumpliste ${i}\n`)
        }


    }

    console.log(array.join('').toString());
}

historic(2026, 29);

console.log('Ejercicio 9: Verificador de números primos');

/**
 * 
 * @param {number} num1 
 */

const numPrimo = (num1) => {
    let array = []
    for (let i = num1; i > 0; i--) {
        if (num1 % i === 0) {
            array.push(i);
        }

    }
    console.log(array.join(' ').toString());
    if (array.length === 2) {
        return true;
    } else {
        return false;
    }
}

const num = 74;

if (numPrimo(num)) {
    console.log(`El número ${num} es primo`);
} else {
    console.log(`El número ${num} no es primo`);
}


