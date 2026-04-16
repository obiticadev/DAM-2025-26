/**
 * @typedef {Object} NumbersData
 * @property {number[]} numbers
 * @property {Object} firstFloor
 * @property {Object} firstFloor.firstRoom
 * @property {number[]} firstFloor.firstRoom.numbersPlus2
 */

/** @type {NumbersData} */

const numbersData = {
  numbers: [10, 32, 31, 68, 91, 24, 51, 47],

  firstFloor: {
    firstRoom: {
      // Cada número del array original + 2
      numbersPlus2: []
    },
    secondRoom: {
      // Cada número del array original - 2
      numbersMinus2: []
    }
  },

  secondFloor: {
    firstRoom: {
      // Cada número del array original * 2
      numbersDouble: []
    },
    secondRoom: {
      // Cada número del array original / 2
      numbersDivided2: []
    }
  },

  thirdFloor: {
    // Solo los números pares
    onlyEven: [],
    // Solo los números impares
    onlyOdd: []
  },

  fourthFloor: {
    // Cada número elevado al cuadrado
    squareNumbers: [],
    // Cada número elevado al cubo
    cubeNumbers: []
  },

  fifthFloor: {
    // Un número aleatorio entre 0 y el número correspondiente del array
    randomNumbers: []
  },

  sixthFloor: {
    // El array de numbers original pero invertido
    reversedNumbers: []
  }
};

numbersData.numbers.forEach(num => {
  numbersData.firstFloor.firstRoom.numbersPlus2.push(num + 2);
  numbersData.firstFloor.secondRoom.numbersMinus2.push(num - 2);
  numbersData.secondFloor.firstRoom.numbersDouble.push(num * 2);
  numbersData.secondFloor.secondRoom.numbersDivided2.push(num / 2);
  if (num % 2 === 0) {
    numbersData.thirdFloor.onlyEven.push(num);
  } else {
    numbersData.thirdFloor.onlyOdd.push(num);
  }
  numbersData.fourthFloor.squareNumbers.push(Math.pow(num, 2));
  numbersData.fourthFloor.cubeNumbers.push(Math.pow(num, 3));
  numbersData.fifthFloor.randomNumbers.push(Math.floor(Math.random() * (num + 1)));
  numbersData.sixthFloor.reversedNumbers.unshift(num);


});


console.log("Ejercicio 1");
console.log(numbersData.firstFloor.firstRoom.numbersPlus2.join(' '));
console.log(numbersData.firstFloor.secondRoom.numbersMinus2.join(' '));
console.log(numbersData.secondFloor.firstRoom.numbersDouble.join(' '));
console.log(numbersData.secondFloor.secondRoom.numbersDivided2.join(' '));
console.log(numbersData.thirdFloor.onlyEven.join(' '));
console.log(numbersData.thirdFloor.onlyOdd.join(' '));
console.log(numbersData.fourthFloor.squareNumbers.join(' '));
console.log(numbersData.fourthFloor.cubeNumbers.join(' '));
console.log(numbersData.fifthFloor.randomNumbers.join(' '));
console.log(numbersData.sixthFloor.reversedNumbers.join(' '));

const stringsData = {
  phrase: 'El conocimiento es poder, pero la práctica es la clave para desbloquearlo.',

  firstFloor: {
    // Solo las vocales de la frase
    vowels: []
  },

  secondFloor: {
    // Solo las consonantes (eliminando espacios, puntos y comas)
    consonants: []
  },

  thirdFloor: {
    // El valor ASCII Code de cada letra de la frase
    // Referencia: https://developer.mozilla.org/es/docs/Web/JavaScript/Reference/Global_Objects/String/charCodeAt
    asciiCode: []
  },

  fourthFloor: {
    // Cada palabra de la frase como una posición del array en mayúsculas
    wordsInUppercase: [],
    // Cada palabra de la frase como una posición del array en minúsculas
    wordsInLowercase: []
  },

  fifthFloor: {
    /**
     * NIVEL DE CODIFICACIÓN (Secret Code):
     * En este nivel transformarás la frase en un mensaje secreto siguiendo estas reglas:
     * 1. Si es una VOCAL: sustituir por número (a=1, e=2, i=3, o=4, u=5).
     * 2. Si es una CONSONANTE: sustituir por su consonante anterior en el abecedario 
     *    (ej: 'c' -> 'b', 'p' -> 'ñ', etc.).
     * 3. Si es un ESPACIO o CARÁCTER ESPECIAL: sustituir por una letra aleatoria del alfabeto.
     */
    secretCode: ''
  }
};

console.log("Ejercicio 2");
console.log(stringsData.phrase.split(''));
