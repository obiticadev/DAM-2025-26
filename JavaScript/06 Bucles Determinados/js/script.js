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
        arrayCuadrado.push(Math.pow(array[i],2));
        arrayCubo.push(Math.pow(array[i],3));
        arrayCompleto.push(`Número: ${array[i]} - Cuadrado: ${arrayCuadrado[i]} - Cubo: ${arrayCubo[i]}\n`);
    }
    console.log(arrayCompleto.join(''));
}

potenciaCubo([1,2,3,5,8])