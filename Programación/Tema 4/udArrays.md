# UD06: Arrays en Java

## 1. Introducción a los Arrays

Un array (también llamado vector o arreglo) es una estructura de datos que funciona como una colección de valores, todos del mismo tipo, almacenados bajo una única variable. En Java, los arrays son objetos, lo que significa que tienen propiedades y pueden ser manipulados.

> **Importante:** Un array o vector es una colección de valores de un **mismo tipo** dentro de una misma variable, permitiendo acceder a cada valor de forma independiente.

Los arrays resuelven el problema de manejar una gran cantidad de variables relacionadas. Por ejemplo, en lugar de crear 18 variables para las notas de 18 alumnos, podemos crear un único array de tamaño 18.

## 2. Propiedades de los Arrays

*   **Contenedores:** Se usan para almacenar un conjunto de datos relacionados.
*   **Homogeneidad:** Todos los elementos de un array deben ser del **mismo tipo**. No se puede mezclar un `int` y un `float` en el mismo array.
*   **Tamaño Fijo:** El tamaño de un array se establece en el momento de su creación (con el operador `new`) y no puede cambiar.
*   **Acceso por Índice:** Se accede a cada elemento a través de su posición o índice numérico, que siempre comienza en 0.
*   **Nomenclatura:**
    *   Los arrays unidimensionales se conocen comúnmente como **vectores**.
    *   Los arrays bidimensionales se conocen como **matrices**.

---

## 3. Arrays Unidimensionales (Vectores)

### Declaración

Se declaran de forma similar a una variable, pero añadiendo corchetes `[]` para indicar que es un array.

```java
// Formato 1:
tipo identificador[];

// Formato 2 (más común en Java):
tipo[] identificador;

// Ejemplos:
int notas[];
double[] cuentas;
```
> **Atención:** La declaración por sí sola no reserva espacio en memoria. En este punto, el array es `null`.

### Instanciación (Creación)

Para que un array pueda usarse, debe ser "instanciado" con el operador `new`, especificando su tamaño. Este paso reserva el espacio necesario en la memoria RAM.

```java
// Declaración e instanciación por separado
int notas[];
notas = new int; // Se crea un array de 3 enteros

// Es más habitual hacerlo en una sola línea
int[] notas = new int;
```
> **Importante:** Al instanciar un array de tipos primitivos numéricos, todos sus elementos se inicializan automáticamente a `0`.

### Almacenamiento y Acceso a Elementos

Se accede y se asignan valores a los elementos del array usando su índice entre corchetes.

> ⚠️ **Atención:** El primer elemento de un array siempre está en la posición o **índice 0**.

```java
int[] notas = new int; // Array de 3 elementos, índices 0, 1, 2

notas = 8;  // Asigna 8 al primer elemento
notas = 10; // Asigna 10 al segundo elemento
notas = 2;  // Asigna 2 al tercer elemento
```

También se pueden inicializar los valores directamente en la declaración:

```java
int[] notas = {8, 10, 2, 3, 5}; // Crea e inicializa un array de 5 elementos
```

### Longitud de un Array

Todos los arrays tienen una propiedad pública y final llamada `length` que devuelve su tamaño (el número de elementos que puede contener).

```java
int[] notas = new int; // Tamaño 4
System.out.println(notas.length); // Mostrará un 4
```
> **Atención:** Si un array tiene un `length` de 4, sus índices válidos van de 0 a 3 (`length - 1`).

### Recorrido de un Array

Para acceder a todos los elementos de un array, se utiliza un bucle, comúnmente un `for`.

```java
int[] notas = {7, 3, 9, 6, 5};

// Recorrer y mostrar todos los elementos
System.out.println("Notas del alumno:");
for (int i = 0; i < notas.length; i++) {
    System.out.println(notas[i]);
}

// Recorrer para calcular la media
int suma = 0;
for (int i = 0; i < notas.length; i++) {
    suma += notas[i]; // Acumula el valor de cada nota
}
double media = (double) suma / notas.length;
System.out.println("La nota media es: " + media);
```

### Copia de Arrays

> ⚠️ **Importante:** No se puede copiar el contenido de un array con el operador de asignación (`=`). La instrucción `v2 = v1;` hace que ambas variables apunten a la **misma dirección de memoria** (copia de referencia). Si modificas `v2`, también estarás modificando `v1`.

Para hacer una copia real del contenido, existen dos métodos principales:

**1. Copiar elemento a elemento con un bucle:**
```java
int[] v1 = {10, 20, 30};
int[] v2 = new int[v1.length]; // El array destino debe tener el mismo tamaño

for (int i = 0; i < v1.length; i++) {
    v2[i] = v1[i];
}
```

**2. Usar el método `System.arraycopy()`:**
Es una forma más eficiente de copiar.
```java
System.arraycopy(origen, posOrigen, destino, posDestino, longitud);
```
*   `origen`: El array del que se copian los datos.
*   `posOrigen`: Índice inicial en el array origen.
*   `destino`: El array al que se copian los datos.
*   `posDestino`: Índice inicial en el array destino.
*   `longitud`: Número de elementos a copiar.

```java
int[] v1 = {10, 20, 30};
int[] v2 = new int[v1.length];
System.arraycopy(v1, 0, v2, 0, v1.length);
```

---

## 4. Arrays Multidimensionales (Matrices)

Son arrays que tienen más de una dimensión. Los más comunes son los de dos dimensiones, conocidos como **matrices**, que se organizan en filas y columnas. Son, en esencia, "arrays de arrays".

### Declaración e Instanciación
```java
// Declaración
tipo[][] identificador;

// Instanciación (ej: 3 filas y 3 columnas)
double[][] precios = new double;
```

### Acceso
Se utilizan dobles corchetes, el primero para la fila y el segundo para la columna.```java
precios = 7.5; // Fila 0, Columna 0
precios = 12;  // Fila 0, Columna 1
```

### Recorrido
Se necesitan bucles anidados: uno para las filas y otro para las columnas.
```java
int[][] notas = new int; // 3 alumnos, 6 notas cada uno
// ... (suponiendo que la matriz ya está llena de datos)

// Bucle para cada fila (alumno)
for (int i = 0; i < notas.length; i++) { 
    System.out.print("Notas del alumno " + (i + 1) + ": ");
    // Bucle para cada columna (nota) de la fila actual
    for (int j = 0; j < notas[i].length; j++) {
        System.out.print(notas[i][j] + " ");
    }
    System.out.println(); // Salto de línea al final de cada fila
}
```

---

## 5. La Clase de Utilidad `java.util.Arrays`

Java proporciona una clase `Arrays` en el paquete `java.util` con métodos estáticos para realizar operaciones comunes sobre arrays.

| Método | Descripción |
| :--- | :--- |
| `Arrays.fill(array, valor)` | Rellena todos los elementos de un array con un valor específico. |
| `Arrays.equals(array1, array2)` | Devuelve `true` si ambos arrays tienen el mismo tamaño y los mismos elementos en el mismo orden. |
| `Arrays.sort(array)` | Ordena los elementos del array en orden ascendente. |
| `Arrays.binarySearch(array, clave)`| Busca un elemento en un **array previamente ordenado** y devuelve su índice, o un número negativo si no lo encuentra. |

---

## 6. La Clase `String`

Aunque `String` es una clase, tiene un tratamiento especial y está muy relacionada con los arrays (internamente, es un array de `char`).

### Comparación de Strings
> ⚠️ **Importante:** Los objetos `String` NO deben compararse con `==`. Este operador compara si dos variables apuntan al mismo objeto en memoria, no si tienen el mismo contenido.

*   `cadena1.equals(cadena2)`: Devuelve `true` si las cadenas tienen el mismo contenido (sensible a mayúsculas).
*   `cadena1.equalsIgnoreCase(cadena2)`: Igual que `equals`, pero ignora mayúsculas y minúsculas.

### Métodos Útiles de `String`
A continuación se listan algunos de los métodos más importantes. **Recuerda que los Strings son inmutables**, por lo que métodos como `replace` o `toUpperCase` no modifican la cadena original, sino que **devuelven una nueva cadena** con el resultado.

| Método | Descripción |
| :--- | :--- |
| `length()` | Devuelve el número de caracteres de la cadena. |
| `charAt(indice)` | Devuelve el carácter en la posición especificada. |
| `concat(str)` o `+` | Concatena (une) dos cadenas. |
| `substring(inicio, fin)`| Extrae una subcadena. |
| `indexOf(str)` | Devuelve el índice de la primera aparición de un carácter o subcadena. |
| `toUpperCase()` / `toLowerCase()` | Convierte la cadena a mayúsculas o minúsculas. |
| `replace(viejo, nuevo)`| Reemplaza todas las apariciones de un carácter o subcadena. |
| `trim()` | Elimina los espacios en blanco al principio y al final. |
| `toCharArray()` | Convierte la cadena en un `char[]`. |
| `matches(regex)` | Comprueba si la cadena coincide con una expresión regular. |
| `String.valueOf(valor)`| Convierte un tipo primitivo (ej. `int`) a `String`. |
| `String.format()` | Crea una cadena con formato (ej. para limitar decimales). |

### El Problema de `Scanner` con `nextInt()` y `nextLine()`
Un error muy común ocurre al leer un número y después una cadena.

*   `in.nextInt()` lee el número, pero deja el carácter de salto de línea (`\n`) en el buffer de entrada.
*   La siguiente llamada a `in.nextLine()` lee ese `\n` pendiente y devuelve una cadena vacía, "saltándose" la entrada del usuario.

**Solución:** Añadir una llamada extra a `in.nextLine()` para limpiar el buffer.
```java
Scanner in = new Scanner(System.in);
System.out.print("Introduce un número: ");
int n = in.nextInt();

in.nextLine(); // ¡Esta línea es crucial para limpiar el buffer!

System.out.print("Introduce un String: ");
String s = in.nextLine();
```

---

## 7. Búsqueda en Arrays

*   **Búsqueda Secuencial (Lineal):** Consiste en recorrer el array elemento por elemento hasta encontrar el valor buscado. Funciona con arrays ordenados y desordenados. Su eficiencia es O(n).
*   **Búsqueda Dicotómica (Binaria):** Un algoritmo mucho más eficiente, pero **requiere que el array esté ordenado**. Funciona dividiendo repetidamente el array por la mitad. Su eficiencia es O(log n). El método `Arrays.binarySearch()` implementa este algoritmo.

---

## 8. Ordenación de Arrays

Existen muchos algoritmos para ordenar (Burbuja, Inserción, Selección, Quicksort, etc.), pero para la mayoría de los casos, Java nos facilita esta tarea con:

```java
Arrays.sort(miArray);
```
Este método utiliza un algoritmo de ordenación muy eficiente (una variante de Quicksort para primitivos y Mergesort para objetos).
