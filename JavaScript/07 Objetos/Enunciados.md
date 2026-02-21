Aquí tienes el contenido formateado de manera organizada, con títulos claros y bloques de código resaltados para que sea más fácil de leer y resolver.

---

# Ejercicios de JavaScript: Manipulación de Objetos

## Ejercicio 1: Procesamiento de Datos Numéricos

**Instrucciones:**  
Utiliza el array alojado en la propiedad `numbers` para rellenar el resto de las propiedades del objeto `numbersData` siguiendo las indicaciones de los comentarios.

```javascript
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
```

---

## Ejercicio 2: Procesamiento de Cadenas de Texto (Strings)

**Instrucciones:**  
Utiliza el texto de la propiedad `phrase` para completar las distintas secciones del objeto `stringsData`. Presta especial atención a las reglas de codificación del último nivel.

```javascript
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
```

---

### Notas sugeridas para la resolución:
* Para el **Ejercicio 1**, te recomendamos usar métodos de arrays como `.map()`, `.filter()` y `.reverse()`.
* Para el **Ejercicio 2**, el uso de `.split()`, `.replace()` y bucles `for` o `.forEach()` te facilitará la manipulación de los caracteres.