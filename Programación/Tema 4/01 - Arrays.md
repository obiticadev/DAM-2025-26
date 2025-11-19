# Guía de Arrays (Arreglos) en Java

En Java, un **array unidimensional** es una estructura de datos fundamental que permite almacenar una colección de elementos del mismo tipo bajo una única variable. Son ampliamente utilizados por su simplicidad y eficiencia en el acceso a los datos.

---

## 1. ¿Qué es un Array Unidimensional?

Es una estructura de datos lineal donde los elementos se almacenan en una secuencia contigua de memoria. Cada elemento tiene una posición única identificada por un **índice** numérico, que en Java siempre comienza en `0`.

### Características Principales
*   **Homogeneidad:** Todos los elementos de un array deben ser del mismo tipo de dato (`int`, `String`, `double`, etc.).
*   **Tamaño Fijo:** Una vez que se crea un array con un tamaño determinado, este no puede cambiar.
*   **Acceso por Índice:** Se accede a cada elemento directamente a través de su índice (ej. `miArray[0]`, `miArray[1]`).
*   **Eficiencia:** El acceso a un elemento por su índice es muy rápido (operación de tiempo constante, O(1)).

---

## 2. Declaración e Inicialización de Arrays

### Declaración
Primero, se declara una variable que contendrá el array, especificando el tipo de dato seguido de corchetes `[]`.
```java
// Sintaxis: tipo[] nombreArray;
int[] numeros;
String[] nombres;
```

### Inicialización
Una vez declarado, el array debe ser "creado" o inicializado. Hay dos formas principales:

**1. Inicialización con `new` (especificando el tamaño):**
Se crea un array vacío con un tamaño fijo. Los elementos se inicializan con sus valores por defecto:
*   `0` para tipos numéricos (`int`, `double`, etc.).
*   `false` para `boolean`.
*   `null` para tipos de referencia (como `String` u otros objetos).

```java
// Sintaxis: nombreArray = new tipo[tamaño];
numeros = new int; // Crea un array de 5 enteros, todos inicializados a 0
```
La declaración e inicialización se pueden combinar en una sola línea:
```java
int[] numeros = new int;
```

**2. Inicialización Directa (con valores):**
Se crea el array y se le asignan valores iniciales directamente, usando llaves `{}`. El tamaño del array se determina automáticamente por la cantidad de elementos.
```java
// Sintaxis: tipo[] nombreArray = {valor1, valor2, ...};
int[] numeros = {1, 2, 3, 4, 5};
String[] dias = {"Lunes", "Martes", "Miércoles"};
```

---

## 3. Acceso y Modificación de Elementos

Se utiliza el nombre del array seguido del índice del elemento entre corchetes `[]`. **Recuerda que los índices empiezan en 0.**

### Acceder a un Elemento
```java
int[] numeros = {10, 20, 30, 40, 50};

int primerElemento = numeros; // Accede al primer elemento (10)
int tercerElemento = numeros; // Accede al tercer elemento (30)
```

### Modificar un Elemento
```java
String[] nombres = {"Ana", "Luis", "Carlos"};

// Modifica el segundo elemento del array
nombres = "Pedro"; 
// Ahora el array es: {"Ana", "Pedro", "Carlos"}
```

---

## 4. Recorrido de Arrays (Iteración)

Para procesar todos los elementos de un array, se utilizan bucles.

### Uso del Bucle `for` Tradicional
Este bucle utiliza un contador (índice) para acceder a cada elemento. La propiedad `.length` del array nos da su tamaño total.
```java
int[] numeros = {10, 20, 30, 40, 50};

for (int i = 0; i < numeros.length; i++) {
    System.out.println("Elemento en el índice " + i + ": " + numeros[i]);
}
```

### Uso del Bucle `for-each` (Enhanced for)
Esta sintaxis es más concisa y se prefiere cuando no necesitas el índice del elemento.
```java
int[] numeros = {10, 20, 30, 40, 50};

for (int numero : numeros) {
    System.out.println("Elemento: " + numero);
}
```

---

## 5. La Clase de Utilidad `java.util.Arrays`

Java proporciona una clase de utilidad (`java.util.Arrays`) con métodos estáticos muy útiles para manipular arrays. Para usarla, necesitas importarla: `import java.util.Arrays;`.

### Métodos Comunes

| Método | Descripción |
| :--- | :--- |
| `Arrays.sort(array)` | Ordena los elementos del array en orden ascendente. |
| `Arrays.binarySearch(array, key)` | Busca un elemento (`key`) en un **array ya ordenado** y devuelve su índice. |
| `Arrays.toString(array)` | Devuelve una representación en `String` del contenido del array, ideal para imprimirlo. |
| `Arrays.fill(array, valor)` | Asigna un valor a todos los elementos del array. |
| `Arrays.equals(array1, array2)`| Compara si dos arrays son iguales (mismo tamaño y mismos elementos en el mismo orden). |
| `Arrays.copyOf(array, newLength)` | Crea una copia del array con una nueva longitud especificada. |

---

## 6. Ejemplo Completo

Este programa combina la declaración, inicialización, ordenación e impresión de un array, además de la búsqueda de un elemento.
```java
import java.util.Arrays;

public class EjemploCompletoArray {
    public static void main(String[] args) {
        // 1. Declaración e inicialización
        int[] numeros = {5, 2, 8, 1, 4};
        
        System.out.println("Array original: " + Arrays.toString(numeros));

        // 2. Ordenar el array
        Arrays.sort(numeros);

        // 3. Imprimir el array ordenado
        System.out.println("Array ordenado: " + Arrays.toString(numeros));

        // 4. Búsqueda binaria de un elemento (el array DEBE estar ordenado)
        int elementoABuscar = 4;
        int indice = Arrays.binarySearch(numeros, elementoABuscar);

        if (indice >= 0) {
            System.out.println("Elemento " + elementoABuscar + " encontrado en la posición: " + indice);
        } else {
            System.out.println("Elemento " + elementoABuscar + " no encontrado.");
        }
    }
}
```
**Salida:**
```
Array original: [5, 2, 8, 1, 4]
Array ordenado: [1, 2, 4, 5, 8]
Elemento 4 encontrado en la posición: 2
```