# Guía del Algoritmo de Ordenación por Burbuja (Bubble Sort)

El algoritmo de ordenación por burbuja es uno de los métodos de ordenamiento más simples. Aunque no es el más eficiente, es fundamental para comprender los conceptos básicos de la ordenación de datos.

---

## 1. Funcionamiento del Algoritmo

El mecanismo se basa en comparar repetidamente pares de elementos adyacentes y intercambiarlos si no están en el orden correcto.

1.  **Comparación de Adyacentes:** En cada "pasada", el algoritmo recorre el array de izquierda a derecha, comparando cada elemento con el siguiente (`arr[j]` con `arr[j+1]`).
2.  **Intercambio:** Si el elemento actual es mayor que el siguiente, se intercambian sus posiciones.
3.  **"Flotación" del Máximo:** Como resultado, después de la primera pasada, el elemento más grande del array habrá "flotado" hasta la última posición. En la segunda pasada, el segundo más grande llegará a la penúltima posición, y así sucesivamente.
4.  **Repetición:** El proceso de pasadas se repite hasta que no se necesiten más intercambios, lo que indica que el array está completamente ordenado.

---

## 2. Implementación en Java

Existen varias versiones del algoritmo, desde la más básica hasta otras con pequeñas optimizaciones.

### Versión 1: Burbuja Simple

Esta es la implementación más directa y menos eficiente. Realiza todas las pasadas y comparaciones posibles, incluso si el array ya está ordenado.

```java
public class OrdenarBurbujaSimple {

    public static void burbujaSimple(int[] arr) {
        int n = arr.length;
        // Bucle externo para controlar las pasadas
        for (int i = 0; i < n - 1; i++) {
            // Bucle interno para las comparaciones y intercambios
            for (int j = 0; j < n - i - 1; j++) {
                // Compara elementos adyacentes
                if (arr[j] > arr[j + 1]) {
                    // Intercambio
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 8};
        System.out.println("Array original: " + java.util.Arrays.toString(arr));
        
        burbujaSimple(arr);
        
        System.out.println("Array ordenado: " + java.util.Arrays.toString(arr));
    }
}
```
**Salida:**
```
Array original: [5, 1, 4, 2, 8]
Array ordenado: [1, 2, 4, 5, 8]
```

### Versión 2: Burbuja Mejorada (con Bandera)

Esta versión introduce una optimización clave: si durante una pasada completa no se realiza ningún intercambio, significa que el array ya está ordenado y el algoritmo puede detenerse prematuramente.

```java
public class OrdenarBurbujaMejorada {

    public static void burbujaMejorada(int[] arr) {
        int n = arr.length;
        boolean huboIntercambio;
        for (int i = 0; i < n - 1; i++) {
            huboIntercambio = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    huboIntercambio = true; // Se marca que hubo un cambio
                }
            }
            // Si no hubo intercambios en esta pasada, el array ya está ordenado
            if (!huboIntercambio) {
                break;
            }
        }
    }
}
```
Esta versión es significativamente más rápida para arrays que ya están parcial o totalmente ordenados.

---

## 3. Ordenación por Burbuja en Arrays Bidimensionales

Para ordenar una matriz o array bidimensional, el enfoque más común es "aplanar" la matriz en un array unidimensional, ordenarlo y luego reconstruir la matriz.

### Enfoque 1: Convertir a Unidimensional

1.  Crear un array unidimensional con tamaño `filas * columnas`.
2.  Copiar todos los elementos de la matriz al array unidimensional.
3.  Aplicar el algoritmo de burbuja al array unidimensional.
4.  Copiar los elementos ordenados de vuelta a la matriz.

```java
public class OrdenamientoBurbujaBidimensional {

    public static void ordenarMatriz(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz.length > 0 ? matriz.length : 0;
        if (columnas == 0) return; // Matriz vacía
        
        int totalElementos = filas * columnas;
        int[] unidimensional = new int[totalElementos];
        
        // 1. Aplanar la matriz
        int index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                unidimensional[index++] = matriz[i][j];
            }
        }
        
        // 2. Ordenar el array unidimensional (usando la versión mejorada)
        burbujaMejorada(unidimensional);
        
        // 3. Reconstruir la matriz
        index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = unidimensional[index++];
            }
        }
    }
    
    // (Incluir aquí el método burbujaMejorada de la sección anterior)
}
```

---

## 4. Análisis de Complejidad

*   **Peor Caso y Caso Promedio:** **O(n²)**. Esto ocurre cuando el array está en orden inverso. Se requieren comparaciones y intercambios en casi todas las iteraciones.
*   **Mejor Caso:**
    *   Burbuja Simple: **O(n²)**, ya que siempre completa todas las pasadas.
    *   Burbuja Mejorada: **O(n)**. Esto ocurre cuando el array ya está ordenado. El algoritmo realiza una única pasada, no encuentra intercambios y termina.

Debido a su complejidad cuadrática, el algoritmo de burbuja **no es adecuado para ordenar grandes volúmenes de datos**. Sin embargo, su simplicidad lo convierte en una excelente herramienta educativa.