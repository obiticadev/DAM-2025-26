# Guía de Arrays Multidimensionales en Java

Los arrays multidimensionales en Java son una extensión de los arrays unidimensionales, permitiendo almacenar datos en estructuras más complejas como tablas o matrices. Son, en esencia, **arrays de arrays**.

El tipo más común es el **array bidimensional**, que se puede visualizar como una tabla con filas y columnas.

---

## 1. Declaración e Inicialización

### Declaración
Se declara un array bidimensional usando dos pares de corchetes `[][]`.

```java
// Sintaxis: tipo[][] nombreArray;
int[][] matriz;
```

### Inicialización
Existen varias formas de inicializar un array bidimensional.

**1. Especificando el Tamaño (con `new`):**
Se define el número de filas y columnas. El array se llenará con los valores por defecto del tipo de dato.

```java
// Sintaxis: tipo[][] nombreArray = new tipo[filas][columnas];
int[][] matriz = new int; // Crea una matriz de 3 filas y 4 columnas, llena de ceros.
```

**2. Inicialización Directa (con valores):**
Se proporcionan los valores directamente usando llaves anidadas. Cada conjunto de llaves internas representa una fila.

```java
int[][] matriz = {
    {1, 2, 3, 4},  // Fila 0
    {5, 6, 7, 8},  // Fila 1
    {9, 10, 11, 12} // Fila 2
};
```

**3. Arrays Irregulares (Jagged Arrays):**
En Java, no es obligatorio que todas las filas tengan la misma longitud. Se puede definir el número de filas y luego inicializar cada fila con un tamaño diferente.

```java
int[][] matrizIrregular = new int[3][]; // 3 filas, columnas sin definir

// Se inicializa cada fila con un tamaño diferente
matrizIrregular[0] = new int[2]; // La primera fila tiene 2 columnas
matrizIrregular[1] = new int[4]; // La segunda fila tiene 4 columnas
matrizIrregular[2] = new int[3]; // La tercera fila tiene 3 columnas
```

---

## 2. Acceso y Modificación de Elementos

Para acceder o modificar un elemento, se necesitan dos índices: el de la fila y el de la columna. **Ambos índices comienzan en `0`**.

### Sintaxis
`nombreArray[indiceFila][indiceColumna]`

### Ejemplo
```java
int[][] matriz = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Acceder al elemento en la fila 1, columna 2 (el valor es 6)
int valor = matriz[1][2];
System.out.println("El valor es: " + valor); // Salida: 6

// Modificar el elemento en la fila 0, columna 0 (cambiar 1 por 99)
matriz[0][0] = 99;
System.out.println("El nuevo primer valor es: " + matriz[0][0]); // Salida: 99
```

---

## 3. Recorrido de Arrays Multidimensionales

Para procesar todos los elementos de una matriz, la forma más común es usar **bucles anidados**.

### Usando Bucles `for` Tradicionales
El bucle exterior itera sobre las filas y el bucle interior itera sobre las columnas de cada fila.

*   `matriz.length` devuelve el número de **filas**.
*   `matriz[i].length` devuelve el número de **columnas** en la fila `i`.

```java
int[][] matriz = { {1, 2, 3}, {4, 5, 6} };

// Bucle exterior para las filas
for (int i = 0; i < matriz.length; i++) {
    // Bucle interior para las columnas de la fila actual
    for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j] + " ");
    }
    System.out.println(); // Salto de línea después de cada fila
}
```
**Salida:**
```
1 2 3 
4 5 6 
```

### Usando Bucles `for-each`
Esta sintaxis es más concisa y legible, especialmente cuando no se necesitan los índices.

```java
public class MatrizEjemplo {
    public static void main(String[] args) {
        // Declaramos y creamos una matriz de 2 filas y 3 columnas
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6}
        };

        // El bucle exterior recorre cada fila (cada una es un array de enteros)
        for (int[] fila : matriz) {
            // El bucle interior recorre cada elemento dentro de esa fila
            for (int elemento : fila) {
                System.out.print(elemento + " ");
            }
            // Salto de línea después de cada fila
            System.out.println();
        }
    }
}
```
**Salida:**
```
1 2 3 
4 5 6 
```

---

## 4. Arrays Multidimensionales en Funciones

Los arrays multidimensionales se pueden pasar como parámetros a funciones y ser devueltos por ellas, al igual que los arrays unidimensionales.

### Pasar un Array como Parámetro
En Java, los arrays se pasan por **referencia**. Esto significa que cualquier modificación hecha al array dentro de la función afectará al array original.

```java
public class ArrayParametro {
    public static void main(String[] args) {
        int[][] miMatriz = { {1, 2}, {3, 4} };
        System.out.println("Matriz original:");
        imprimirMatriz(miMatriz);
        
        // Modificamos la matriz dentro de una función
        modificarMatriz(miMatriz);
        
        System.out.println("\nMatriz modificada:");
        imprimirMatriz(miMatriz);
    }

    // Método que recibe una matriz y la imprime
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }
    
    // Método que modifica el primer elemento de la matriz
    public static void modificarMatriz(int[][] matriz) {
        if (matriz.length > 0 && matriz.length > 0) {
            matriz = 100;
        }
    }
}
```
**Salida:**
```
Matriz original:
1 2 
3 4 

Matriz modificada:
100 2 
3 4 
```

### Retornar un Array desde una Función
Una función puede crear y devolver un array multidimensional.

```java
public class RetornoArray {
    public static void main(String[] args) {
        // Llamamos a una función que crea y devuelve una matriz
        int[][] matrizGenerada = crearMatrizIdentidad(3);
        
        // Imprimimos la matriz retornada
        imprimirMatriz(matrizGenerada);
    }
    
    // Método que crea y retorna una matriz identidad de tamaño n x n
    public static int[][] crearMatrizIdentidad(int n) {
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            matriz[i][i] = 1; // Pone un 1 en la diagonal principal
        }
        return matriz;
    }

    // Método auxiliar para imprimir
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            System.out.println(java.util.Arrays.toString(fila));
        }
    }
}
```
**Salida:**
```
[1, 0, 0]
[0, 1, 0]
[0, 0, 1]
```