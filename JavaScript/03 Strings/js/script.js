
console.log('Ejercicio 1: Condicional de longitud');

const condicionalMayusculaMinuscula = word => {
    let finalword;
    if (word.length > 5) {
        finalword = word.toUpperCase();
    } else {
        finalword = word.toLowerCase();
    }
    return finalword;
}

console.log(condicionalMayusculaMinuscula('palabra'));

console.log('Ejercicio 2: Condicional de longitud (operador ternario');

const condicionalMayusculaMinusculaOTernario = word => word.length > 5 ? word.toUpperCase() : word.toLowerCase();

console.log(condicionalMayusculaMinusculaOTernario('palabra2'));

console.log('Ejercicio 3: Letra aleatoria');

/**
 * @param {string} word 
 */


const letraAleatoria = word => {
    const MIN = 0;
    const MAX = word.length - 1;

    const letra = Math.floor(Math.random() * (MAX - MIN + 1));
    return word.charAt(letra);
}

console.log(letraAleatoria('Hola Mundo'));

console.log('Ejercicio 4: Verificación de inicio');

/**
 * @param {string} word 
 */


const letraMayuscula = word => {
    if (word.charAt(0) === word.charAt(0).toUpperCase()) {
        return 'Comienza con letra mayúscula';
    } else {
        return 'No comienza con letra mayúscula';
    }
}

console.log(letraMayuscula('JaGola'));

console.log('Ejercicio 5: Conjugación de verbos');

/**
 * 
 * @param {string} vb1 
 * @param {string} vb2 
 */

const conjugacion = (vb1, vb2) => {
    let verbo1;
    let verbo2;

    if (vb1.endsWith('ar')) {
        verbo1 = 'es de la primera conjugación';
    } else if (vb1.endsWith('er')) {
        verbo1 = 'es de la segunda conjugación';
    } else if (vb1.endsWith('ir')) {
        verbo1 = 'es de la tercera conjugación';
    } else {
        verbo1 = 'no es ningún verbo';
    }
    if (vb2.endsWith('ar')) {
        verbo2 = 'es de la primera conjugación';
    } else if (vb2.endsWith('er')) {
        verbo2 = 'es de la segunda conjugación';
    } else if (vb2.endsWith('ir')) {
        verbo2 = 'es de la tercera conjugación';
    } else {
        verbo2 = 'no es ningún verbo';
    }

    return `El verbo ${vb1} ${verbo1}, y el verbo ${vb2} ${verbo2}`;
}

console.log(conjugacion('Saltar', 'correr'));

console.log('Ejercicio 6: Generador de palabras');

/**
 * 
 * @param {string} word 
 */

const generadorAleatorio = word => {
    const MIN = 0;
    const MAX = word.length - 1;

    const posicion = Math.floor(Math.random() * (MAX - MIN + 1));
    return word.charAt(posicion);
}

/**
 * 
 * @param {string} valor1 
 * @param {string} valor2 
 * @param {string} valor3 
 */

const generadorPalabras = (valor1, valor2, valor3) => {
    return `${generadorAleatorio(valor1)}${generadorAleatorio(valor1)}${generadorAleatorio(valor2)}${generadorAleatorio(valor2)}${generadorAleatorio(valor3)}${generadorAleatorio(valor3)}`;
}

console.log(generadorPalabras('Hola', 'Oliver', 'Saltar'));

console.log('Ejercicio 7: Separador de Email');

/**
 * 
 * @param {string} email 
 */

const emailSeparador = email => {
    const separador = email.indexOf('@');

    const user = email.substring(0, separador);
    const rest = email.substring(separador + 1);

    return `El usuario es ${user} y el dominio es ${rest}`
}

console.log(emailSeparador('oliver@gmail.com'));

console.log('Ejercicio 8: Intercalar mayúsculas');

/**
 * 
 * @param {string} word 
 */

const intercalarMayusculas = word => {

}