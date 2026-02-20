console.log('Ejercicio 1: Cálculo de Potencias');

/**
 * 
 * @param {number[]} array 
 */

const potenciaCubo = (array) => {
    let arrayCuadrado = [];
    let arrayCubo = [];
    let arrayCompleto = [];
    for (let i = 0; i < array.length; i++) {
        arrayCuadrado.push(Math.pow(array[i], 2));
        arrayCubo.push(Math.pow(array[i], 3));
        arrayCompleto.push(`Número: ${array[i]} - Cuadrado: ${arrayCuadrado[i]} - Cubo: ${arrayCubo[i]}\n`);
    }
    console.log(arrayCompleto.join(''));

}

potenciaCubo([1, 2, 3, 5, 8])

/**
 * 
 * @param {number[]} array 
 */

const potenciaCuboForOf = (array) => {
    let arrayCompleto = [];
    for (const element of array) {
        let cuadrado = (Math.pow(element, 2));
        let cubo = (Math.pow(element, 3));
        arrayCompleto.push(`Número: ${element} - Cuadrado: ${cuadrado} - Cubo: ${cubo}\n`);
    }
    console.log(arrayCompleto.join(''));
}

potenciaCuboForOf([2, 3, 4, 5]);

console.log('Ejercicio 2: Resaltador de Vocales');

/**
 * 
 * @param {string} word 
 */

const formatMayus = (word) => {
    const regex = /[aeiouáéíóú]/;
    let arrayFormat = [];
    for (const character of word) {
        if (regex.test(character)) {
            arrayFormat.push(character.toUpperCase());
        } else {
            arrayFormat.push(character);
        }
    }
    console.log(arrayFormat.join(''));
}

formatMayus('casa esto es una prueba para verificar la utilidad de la función');

/**
 * 
 * @param {string} word 
 */

const formatMayusNormal = (word) => {
    const regex = /[aeiou]/;
    let arrayFormat = [];
    for (let i = 0; i < word.length; i++) {
        if (regex.test(word.split('')[i])) {
            arrayFormat.push(word.split('')[i].toUpperCase());
        } else {
            arrayFormat.push(word.split('')[i]);
        }

    }
    console.log(arrayFormat.join(''));
}

formatMayusNormal('Esto es una pruebas');