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

    for (let i = 0; i < 3; i++) {
        arrayEmpty[i] = randomNum();

    }
    return arrayEmpty;
}

let arrayEmpty = [];
console.log(returnArrayWithValues(arrayEmpty));

console.log('Ejercicio 5: Función que reciba un array de 3 números');

/**
 * 
 * @param {number[]} array3Nums 
 */

const randomNum10 = () => {
    return Math.floor(Math.random() * 10 + 1);
}

const splitArrays = (array3Nums) => {
    let arrayPar = [];
    let arrayImpar = [];
    let contadorPar = 0;
    let contadorImpar = 0;
    for (let i = 0; i < array3Nums.length; i++) {
        let valor = array3Nums[i] * randomNum10();
        if (valor % 2 === 0) {
            arrayPar[contadorPar] = valor;
            contadorPar++;
        } else {
            arrayImpar[contadorImpar] = valor;
            contadorImpar++;
        }
    };
    console.log(array3Nums);
    console.log(arrayPar);
    console.log(arrayImpar);
}

const array3Nums = [2, 3, 6]

splitArrays(array3Nums);

console.log('Ejercicio 6: función dniLetter');

const dniLetter = (dni) => {
    const arrayLetter = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'];
    const pos = dni % 23;
    console.log(arrayLetter[pos]);
}


dniLetter(33045654);

console.log('Ejercicio 7: Array con 3 palabras');

/**
 * 
 * @param {string[]} arrayStrings 
 */

const extractCharacter = (arrayStrings) => {
    let salida = [];
    let contador = 0;
    for (let i = 0; i < arrayStrings.length; i++) {
        salida[contador++] = arrayStrings[i].charAt(0).toUpperCase();
        salida[contador++] = arrayStrings[i].charAt(arrayStrings[i].length - 1).toUpperCase();
    }
    console.log(salida);
}

extractCharacter(['Oliver', 'Bitica', 'Patina']);

console.log('Ejercicio 8: Devolver palabra invertida');

/**
 * 
 * @param {string} word 
 */

const reverseWord = (word) => {
    return (word.split('').reverse().join(''));
}

console.log(reverseWord('Oliver'));

console.log('Ejercicio 9: Fusión de arrayString y arrayNumber');

/**
 * 
 * @param {string[]} arrayString 
 * @param {number[]} arrayNumber 
 */

const fusionArrays = (arrayString, arrayNumber) => {
    if (arrayString.length === arrayNumber.length) {
        let salida = [];
        for (let i = 0; i < arrayString.length; i++) {
            salida[i] = (arrayString[i] + arrayNumber[i]);

        }
        console.log(salida);
    } else {
        console.log('No contiene el mismo número de elementos en un array que en el otro');
    }
}

fusionArrays(['Oliver', 'Bitica', 'Patina'], [23, 35, 67])

console.log('Ejercicio 10: cuadrado de array');

const powArray = (arrayMathPow2) => {
    let newPowArray = [];
    for (let i = 0; i < arrayMathPow2.length; i++) {
        newPowArray[i] = Math.pow(arrayMathPow2[i], 2);

    }
    console.log(newPowArray);
}

powArray([2, 5, 7])

console.log('Ejercicio 11: Seleccion de palabras');

/**
 * 
 * @param {string[]} arrayNames 
 */

const selectNameStartWithA = (arrayNames) => {
    let selectNames = [];
    let contador = 0;
    for (let i = 0; i < arrayNames.length; i++) {
        if (arrayNames[i].startsWith('A')) {
            selectNames[contador++] = arrayNames[i];
        }

    }
    return selectNames;
}

console.log(selectNameStartWithA(['Oliver', 'Alex', 'Alejandro']));

console.log('Ejercicio 12: reverse sin usar reverse');

/**
 * 
 * @param {number[]} arrayReverse 
 */

const reverseWithOutReverse = (arrayReverse) => {
    let newArrayNum = [];
    for (let i = 0; i < arrayReverse.length; i++) {
        newArrayNum[i] = arrayReverse[arrayReverse.length - 1 - i];
    }
    return newArrayNum;

}

let arrayNum = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];

console.log(reverseWithOutReverse(arrayNum));