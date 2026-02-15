console.log('Ejercicio 1: Condicional de longitud');

const condicionalMayusculaMinuscula = word => {
    let finalword;
    if (word.length > 5) {
        finalword = word.toUpperCase();
    } else {
        finalword = word.toLowerCase();
    }
}

console.log(condicionalMayusculaMinuscula('palabra'));