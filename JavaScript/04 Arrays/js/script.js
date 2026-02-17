console.log('Ejercicio 1: Array de 5 números');

/**
 * 
 * @param {number[]} array 
 */

const randomNumArray = (array) => {
    const pos = Math.floor(Math.random() * array.length);
    return array[pos];
}

let array = [3, 8, 1, 4, 6];
console.log(randomNumArray(array));

console.log('Ejercicio 2: Información por pantalla del array');

/**
 * 
 * @param {number[]} array 
 */

const infoArray = (array) => {
    let suma = 0;
    for (let i = 0; i < array.length; i++) {
        suma += array[i];
    }
    const media = suma / array.length;
    const MAX = Math.max(...array);
    const MIN = Math.min(...array);
    return `
    La suma del array es: ${suma}
    La media es: ${media}
    El Máximo es: ${MAX}
    El Mínimo es: ${MIN}`;
}

console.log(infoArray(array));

console.log('Ejercicio 3: Comprobación de número aleatorio en array');

/**
 * 
 * @param {number[]} array 
 */

const verificationNumOnArray = (array) => {

    const randomNum = Math.floor(Math.random() * 10 + 1);

    for (let i = 0; i < array.length; i++) {
        if (array[i] == randomNum) {
            return `El número aleatorio se encuentra en el array en la posición ${i + 1}`;
        }

    }
    return `No hay ninguna coicidencia`;

}

console.log(verificationNumOnArray(array));

console.log('Ejercicio 4: return de array con valores');

/**
 * 
 * @param {number[]} arrayEmpty 
 */

const randomNum = () => {
    return Math.floor(Math.random() * 100 + 1);
}

const returnArrayWithValues = (arrayEmpty) => {
    const randomNum1 = randomNum;
    const randomNum2 = randomNum;
    const randomNum3 = randomNum;

    for (let i = 0; i < 3; i++) {


    }
}

let arrayEmpty;
console.log(returnArrayWithValues(arrayEmpty));