# Ejercicios de Lógica con Arrays y Funciones en JavaScript

1.  Crea una función que reciba un **array de 5 números** e imprime por consola una **posición aleatoria** del array.

2.  Crea una función que reciba un **array con 3 números**. La función deberá imprimir por consola la siguiente información (para el máximo y el mínimo revisa la documentación de Math: [MDN Web Docs](https://developer.mozilla.org/es/docs/Web/JavaScript/Reference/Global_Objects/Math)).
    *   "La suma de todos los números es: `[suma]`"
    *   "La media de todos los números es: `[media]`"
    *   "El mayor es `[mayor]` y el menor es `[menor]`"

3.  Crea una función que reciba un **array con 5 números del 0 al 10** (a tu elección).
    *   Dentro de la función, genera un **número aleatorio entre 0 y 10**.
    *   La función deberá decir si el array contiene ese número y en qué posición está, o si no lo contiene.

4.  Crea una función que reciba un **array vacío** y lo devuelva con **3 números aleatorios entre 0 y 100**.

5.  Crea una función que reciba un **array de 3 números**.
    *   Dentro de esa función crea dos arrays vacíos llamados `even` (pares) y `odd` (impares).
    *   Después, multiplica cada uno de los números del array recibido por un número aleatorio entre 1 y 10.
    *   Si el resultado es **par**, guárdalo en el array de pares; si es **impar**, en el array de impares.
    *   Al final, imprime el array recibido, el array de pares y el de impares por consola.

6.  Crea una función llamada `dniLetter` que recibirá un **número de DNI sin la letra**.
    *   Dentro de esa función pon este array:
        `['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E']`
    *   La letra del DNI se calcula a través del **resto de dividir el número de DNI entre 23**.
    *   Ese número te dará la posición del array donde se encuentra la letra correspondiente a ese DNI.
    *   Imprime por consola el DNI con su letra correspondiente.

7.  Crea una función que reciba un **array con 3 palabras**.
    *   Debes imprimir por consola un array que contenga la **inicial** y la **última letra** de cada palabra en mayúsculas.
    *   *Ejemplo:* Si nuestra función recibiera `['hola', 'adios', 'gato']`, deberá imprimir por consola `['H', 'A', 'A', 'S', 'G', 'O']`.

8.  Crea una función que reciba una palabra y la imprima **al revés**.

9.  Crea una función que reciba un **array con tres nombres** y **otro array con tres números**.
    *   La función deberá imprimir un array con los nombres y los números fusionados.
    *   *Ejemplo:* `['Pepe', 'Marta', 'Javier']` y `[23, 14, 50]`. En consola deberá salir `['Pepe23', 'Marta14', 'Javier50']`.

10. Crea una función que reciba un **array con tres números**. La función deberá imprimir un nuevo array que contenga los **cuadrados** de los números que le has enviado.

11. Crea una función que reciba un **array con tres nombres** y sólo imprima los que **empiecen por "A"**.

12. Crea una función que reciba un **array de 4 números** y que lo imprima en orden inverso, pero **SIN USAR `reverse()`**.