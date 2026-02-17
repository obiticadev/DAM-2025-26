console.log('Ejercicio 1: Array de 5 números');

/**
 * 
 * @param {number[]} array 
 */

const randomNumArray = (array) => {
    const pos = Math.floor(Math.random() * array.length);
    return array[pos];
}

let array = [3, 4, 6, 2, 1, 4, 6];
console.log(randomNumArray(array));

console.log('Ejercicio 2: Información por pantalla del array');