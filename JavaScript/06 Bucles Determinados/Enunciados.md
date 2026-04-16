# 📋 Guía de Ejercicios: Bucles en JavaScript

**Instrucción General:** Debes resolver cada uno de los siguientes ejercicios de **dos formas distintas**:
1.  Utilizando el bucle **`for...of`**.
2.  Utilizando el bucle **`for`** tradicional.

---

### 1. Cálculo de Potencias (Cuadrado y Cubo)
Crea una función que reciba un array de 10 números. Por cada número, debe imprimir un mensaje con el siguiente formato:
*   **Formato:** `"Número: 2 - Cuadrado: 4 - Cubo: 8"`
*   **Objetivo:** Practicar operaciones aritméticas básicas dentro de un bucle.

### 2. Resaltador de Vocales
Crea una función que reciba una palabra como parámetro e imprima la misma palabra, pero transformando todas sus **vocales a mayúsculas**.
*   **Ejemplo:** Si recibe "mariposa", debe devolver "mArIpOsA".

### 3. Contador de Frecuencia de Vocales
Crea una función que reciba una frase y contabilice cuántas veces aparece cada una de las 5 vocales (a, e, i, o, u).
*   **Resultado esperado:**
    *   La vocal "a" se repite X veces.
    *   La vocal "e" se repite X veces... (así con todas).

### 4. Generador y Buscador Aleatorio
Crea una función que reciba un array con 5 números (entre el 1 y el 10). Al recorrerlo, por cada vuelta:
1.  Genera un número aleatorio entre 0 y el número actual del array.
2.  Verifica si ese número generado existe en el array original.
3.  **Resultado:** Debe imprimir si el número está en el array (indicando su posición/índice) o si no se encuentra.

### 5. Inversión de Cadena (Manual)
Crea una función que reciba una palabra e imprima su inversa conservando mayúsculas y minúsculas.
*   **Restricción:** No puedes utilizar el método `.reverse()`.
*   **Ejemplo:** "Mariposas" ➔ "sasopiraM".

### 6. Clasificador de Pares e Impares Aleatorios
Crea una función que reciba un array de 10 números. Dentro de la función:
1.  Crea dos arrays vacíos: `even` (pares) y `odd` (impares).
2.  Multiplica cada número del array original por un número aleatorio entre 1 y 10.
3.  Si el resultado es **par**, guárdalo en `even`. Si es **impar**, en `odd`.
4.  **Resultado:** Imprime al final el array original y los dos nuevos arrays resultantes.

### 7. Extractor de Extremos de Palabras
Crea una función que reciba un array con 5 palabras. Debes construir e imprimir un nuevo array que contenga únicamente la **primera y la última letra** de cada palabra en mayúsculas.
*   **Ejemplo de entrada:** `['hola', 'adios', 'gato']`
*   **Resultado esperado:** `['H', 'A', 'A', 'S', 'G', 'O']`

---

### 💡 Consejos para la resolución:
*   Recuerda que para acceder a los caracteres de un *string* puedes tratarlos de forma similar a un array (por índice).
*   Para generar números aleatorios usa `Math.random()` combinado con `Math.floor()`.
*   Para pasar a mayúsculas usa el método `.toUpperCase()`.