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

console.log('Ejercicio 3: Contador de frencuencia de vocales');

/**
 * 
 * @param {string} phrase 
 */

const contadorVocales = (phrase) => {
    const a = /[aeiou]/i;
    let arrayContadorVocales = [0, 0, 0, 0, 0];
    let arrayPhrase = phrase.split('');

    for (let i = 0; i < arrayPhrase.length; i++) {
        if (a.test(arrayPhrase[i])) {
            switch (arrayPhrase[i].toLowerCase()) {
                case 'a':
                    arrayContadorVocales[0]++;
                    break;
                case 'e':
                    arrayContadorVocales[1]++;
                    break;
                case 'i':
                    arrayContadorVocales[2]++;
                    break;
                case 'o':
                    arrayContadorVocales[3]++;
                    break;
                case 'u':
                    arrayContadorVocales[4]++;
                    break;

                default:
                    break;
            }
        }
    }

    console.log(arrayContadorVocales.join(' '));
}

contadorVocales('Esto es una prueba para que me digas cuantas vocales estás capturando')

/**
 * 
 * @param {string} phrase 
 */

const contadorVocalesForOf = (phrase) => {
    const a = /[aeiou]/i;
    let arrayContadorVocales = [0, 0, 0, 0, 0];
    let arrayPhrase = phrase.split('');

    for (const element of arrayPhrase) {
        if (a.test(element)) {
            switch (element.toLowerCase()) {
                case 'a':
                    arrayContadorVocales[0]++;
                    break;
                case 'e':
                    arrayContadorVocales[1]++;
                    break;
                case 'i':
                    arrayContadorVocales[2]++;
                    break;
                case 'o':
                    arrayContadorVocales[3]++;
                    break;
                case 'u':
                    arrayContadorVocales[4]++;
                    break;

                default:
                    break;
            }
        }
    }

    console.log(arrayContadorVocales.join(' '));
}

contadorVocalesForOf('Esto es una prueba para que me digas cuantas vocales estás capturando')


console.log('Ejercicio 4: Generador y Buscador Aleatorio');

/**
 * 
 * @param {number[]} arrayDelimitado 
 */

const generadorYBuscador = (arrayDelimitado) => {
    for (let i = 0; i < arrayDelimitado.length; i++) {
        const numGenerator = Math.floor(Math.random() * (arrayDelimitado[i] + 1));
        console.log(numGenerator);
        for (let j = 0; j < arrayDelimitado.length; j++) {
            if (numGenerator === arrayDelimitado[j]) {
                console.log(`El número ${numGenerator} se encuentra en la posición ${j + 1}`);
            }
        }
    }
}



generadorYBuscador([4, 8, 2, 7])
console.log('\nSEGUNDA PARTE\n\n');
/**
 * 
 * @param {number[]} arrayDelimitado 
 */

const generadorYBuscadorForOf = (arrayDelimitado) => {
    for (const element of arrayDelimitado) {
        const numGenerator = Math.floor(Math.random() * (element + 1));
        console.log(numGenerator);
        let contador = 0;
        for (const element2 of arrayDelimitado) {
            if (numGenerator === element2) {
                console.log(`El número ${numGenerator} se encuentra en la posición ${contador + 1}`);
            }
            contador++;
        }
    }
}

generadorYBuscadorForOf([4, 8, 2, 7]);

console.log('Ejercicio 5 : Inversión de Cadena (Manual)');

/**
 * 
 * @param {string} word 
 */

const reversePhrase = (word) => {
    let arrayReverse = [];
    let copiaWordArray = word.split('');
    for (let i = 0; i < word.length; i++) {
        let elementoRetirado = copiaWordArray.pop();
        arrayReverse.push(elementoRetirado);
    }
    console.log(arrayReverse.join(''));
}

reversePhrase('Esto es una prueba')

/**
 * 
 * @param {string} word 
 */

const reversePhraseForOf = (word) => {
    let arrayReverse = [];
    let copiaWordArray = word.split('');

    for (const element of word) {
        let elementoRetirado = copiaWordArray.pop();
        arrayReverse.push(elementoRetirado);
    }
    console.log(arrayReverse.join(''));
}

reversePhraseForOf('Esto es una prueba');

console.log('Ejercicio 6: Clasificador de Pares e Impares Aleatorios');

/**
 * 
 * @param {number[]} array 
 */

const clasificadorParesImpares = (array) => {
    let arrayPar = [];
    let arrayImpar = [];
    for (let i = 0; i < array.length; i++) {
        const numGenerated = Math.floor(Math.random() * (array[i] + 1))
        if (numGenerated % 2 === 0) {
            arrayPar.push(numGenerated);
        } else {
            arrayImpar.push(numGenerated);
        }
    }
    console.log(`Array original: ${array.join(' ')}\nArray de pares: ${arrayPar.join(' ')}\nArray de impares: ${arrayImpar.join(' ')}`);
}

clasificadorParesImpares([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])

/**
 * 
 * @param {number[]} array 
 */

const clasificadorParesImparesForOf = (array) => {
    let arrayPar = [];
    let arrayImpar = [];

    for (const element of array) {
        const numGenerated = Math.floor(Math.random() * (element + 1))
        if (numGenerated % 2 === 0) {
            arrayPar.push(numGenerated);
        } else {
            arrayImpar.push(numGenerated);
        }
    }
    console.log(`Array original: ${array.join(' ')}\nArray de pares: ${arrayPar.join(' ')}\nArray de impares: ${arrayImpar.join(' ')}`);
}

clasificadorParesImparesForOf([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);

console.log('Ejercicio 7: Extractor de extremos de palabras');

/**
 * 
 * @param {string[]} arrayWord 
 */

const extractCharacter = (arrayWord) => {
    let arrayFinal = [];
    for (let i = 0; i < arrayWord.length; i++) {
        const first = arrayWord[i].split('').shift();
        const end = arrayWord[i].split('').pop();
        arrayFinal.push(first);
        arrayFinal.push(end);
    }
    console.log(arrayFinal.join(' ').toUpperCase());
}

extractCharacter(['Hola', 'Mundo', 'qué', 'tal', 'estás'])

/**
 * 
 * @param {string[]} arrayWord 
 */

const extractCharacterForOf = (arrayWord) => {
    let arrayFinal = [];
    for (const element of arrayWord) {
        const first = element.split('').shift();
        const end = element.split('').pop();
        arrayFinal.push(first);
        arrayFinal.push(end);
    }
    console.log(arrayFinal.join(' ').toUpperCase());
}

extractCharacterForOf(['Hola', 'Mundo', 'qué', 'tal', 'estás'])