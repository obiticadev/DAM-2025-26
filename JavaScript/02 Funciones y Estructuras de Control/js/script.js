console.log('Ejercicio 1: Verificación de edad');

const verificacionEdad = (nombre, edad) => {
    if (edad >= 18) {
        return 'Hola ' + nombre + ', eres mayor de edad';
    } else {
        return 'Hola ' + nombre + ', eres menor de edad';
    }
}

console.log(verificacionEdad('Oliver', 28));

console.log('Ejercicio 2: Comparación de dos números');

const comparador = (num1, num2) => {
    if (num1 === num2) {
        return num1 + ' y ' + num2 + ' son iguales';
    } else if (num1 > num2) {
        return num1 + ' es mayor que ' + num2;
    } else {
        return num1 + ' es menor que ' + num2;
    }
}

console.log(comparador(5, 9));

console.log('Ejercicio 3: Signo de un número');
const signoNumero = num => {
    if (num === 0) {
        return num + ' es cero'
    } else if (num > 0) {
        return num + ' es positivo'
    } else {
        return num + ' es negativo'
    }
}

console.log(signoNumero(-5));

console.log('Ejercicio 4: Calificación de notas');

const media = (num1, num2, num3) => {
    let mediaPonderada = (num1 + num2 + num3) / 3
    if (mediaPonderada < 5) {
        return 'Suspenso';
    } else if (mediaPonderada >= 5 && mediaPonderada < 8) {
        return 'Aprobado';
    } else {
        return 'Matricula de honor';
    }
}

console.log(media(10, 2, 6));

console.log('Ejercicio 5: El mayor de tres números');

const notas = (num1, num2, num3) => {
    if (num1 > num2) {
        if (num1 > num3) {
            return num1 + ' es el más grande';
        }
    } else {
        if (num2 > num3) {
            return num2 + ' es el más grande';
        }
    }
    return num3 + ' es el más grande';

}

console.log(notas(9, 6, 5));

console.log('Ejercicio 6: Divisibilidad');

const divisibilidad = num => {
    if (num % 3 === 0 && num % 5 === 0) {
        return num + ' es divisible por 3 y por 5';
    } else if (num % 3 === 0) {
        return num + ' es divisible por 3';
    } else if (num % 5 === 0) {
        return num + ' es divisible por 5';
    } else {
        return num + ' no es divisible ni por 3 ni por 5'
    }
}

console.log(divisibilidad(12031));

console.log('Ejercicio 7: Par o Impar');

const parImpar = num => {
    if (num % 2 == 0) {
        return num + ' es par';
    } else {
        return num + ' es impar'
    }
}

console.log(parImpar(53));

console.log('Ejercicio 8: Año Bisiesto');

const anioBisiesto = anio => {
    if (anio % 400 === 0 || anio % 4 === 0 && !(anio % 100 === 0)) {
        return anio + ' es un año bisiesto';

    } else {
        return anio + ' no es un año bisiesto';
    }
}

console.log(anioBisiesto(2026));

console.log('Ejercicio 9: Suma con conversión de tipos');

console.log(typeof 4);

const sumaConversion = (valor1, valor2) => {
    let valor1Real;
    let valor2Real;

    if (typeof valor1 === 'string') {
        valor1Real = Number(valor1);
    } else {
        valor1Real = valor1;
    }

    if (typeof valor2 === 'string') {
        valor2Real = Number(valor2);
    } else {
        valor2Real = valor2;
    }

    return valor1Real + valor2Real;

}

console.log(sumaConversion(9, '3'));