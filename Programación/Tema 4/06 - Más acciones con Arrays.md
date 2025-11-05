# Guía de Operaciones con Arrays en Java

Esta guía cubre las operaciones más comunes que se realizan sobre arrays en Java, incluyendo búsqueda, inserción, eliminación y copia de elementos.

---

## 1. Búsqueda en Arrays

Buscar un elemento es una de las operaciones más frecuentes. El método a utilizar depende de si el array está ordenado o no.

### Búsqueda en Arrays No Ordenados: Búsqueda Lineal

En un array desordenado, la única forma de encontrar un elemento es recorrerlo secuencialmente desde el principio hasta el final.

*   **Complejidad:** O(n), ya que en el peor de los casos hay que revisar todos los elementos.

```java
public class BusquedaLineal {
    // Devuelve el índice del elemento, o -1 si no se encuentra.
    public static int busquedaLineal(int[] arr, int clave) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == clave) {
                return i; // Elemento encontrado
            }
        }
        return -1; // Elemento no encontrado
    }

    public static void main(String[] args) {
        int[] miArray = {3, 5, 2, 7, 9, 1};
        int clave = 7;
        int resultado = busquedaLineal(miArray, clave);

        if (resultado != -1) {
            System.out.println("Elemento encontrado en el índice: " + resultado); // Salida: 3
        } else {
            System.out.println("Elemento no encontrado.");
        }
    }
}
```

### Búsqueda en Arrays Ordenados: Búsqueda Binaria

Si el array está **ordenado**, se puede usar un algoritmo mucho más eficiente: la búsqueda binaria. Este método divide repetidamente el array a la mitad hasta encontrar el elemento.

*   **Complejidad:** O(log n), mucho más rápido que la búsqueda lineal para arrays grandes.
*   **Requisito:** El array debe estar previamente ordenado.

```java
import java.util.Arrays;

public class BusquedaBinaria {
    // El método Arrays.binarySearch() implementa este algoritmo.
    public static void main(String[] args) {
        int[] miArrayOrdenado = {1, 3, 5, 7, 9, 11};
        int clave = 7;
        
        // Usamos el método predefinido de la clase Arrays
        int resultado = Arrays.binarySearch(miArrayOrdenado, clave);

        if (resultado >= 0) {
            System.out.println("Elemento encontrado en el índice: " + resultado); // Salida: 3
        } else {
            System.out.println("Elemento no encontrado.");
        }
    }
}
```

---

## 2. Inserción de Elementos

Dado que los arrays en Java tienen un **tamaño fijo**, la "inserción" de un nuevo elemento siempre implica la **creación de un nuevo array** con un tamaño mayor.

### Inserción en Arrays No Ordenados
La forma más simple es añadir el nuevo elemento al final.

```java
public class InsercionNoOrdenada {
    public static int[] insertarAlFinal(int[] arr, int elemento) {
        int[] nuevoArray = new int[arr.length + 1];
        // Copia los elementos del array original al nuevo
        System.arraycopy(arr, 0, nuevoArray, 0, arr.length);
        // Añade el nuevo elemento en la última posición
        nuevoArray[arr.length] = elemento;
        return nuevoArray;
    }
}
```

### Inserción en Arrays Ordenados
Para mantener el orden, se debe encontrar la posición correcta del nuevo elemento y desplazar los elementos existentes.

```java
public class InsercionOrdenada {
    public static int[] insertarEnOrden(int[] arr, int elemento) {
        int[] nuevoArray = new int[arr.length + 1];
        int i = 0;
        
        // 1. Copiar elementos menores que el nuevo elemento
        while (i < arr.length && arr[i] < elemento) {
            nuevoArray[i] = arr[i];
            i++;
        }
        
        // 2. Insertar el nuevo elemento en su posición
        nuevoArray[i] = elemento;
        
        // 3. Copiar el resto de elementos (los mayores)
        while (i < arr.length) {
            nuevoArray[i + 1] = arr[i];
            i++;
        }
        return nuevoArray;
    }
}
```

---

## 3. Eliminación de Elementos

Al igual que la inserción, la eliminación requiere crear un **nuevo array** con un tamaño menor, omitiendo el elemento a eliminar.

### Eliminación en Arrays No Ordenados
Se crea un nuevo array y se copian todos los elementos excepto el que se quiere eliminar.

```java
public class EliminacionNoOrdenada {
    public static int[] eliminarElemento(int[] arr, int elemento) {
        // Contar cuántas veces aparece el elemento para definir el tamaño del nuevo array
        int contador = 0;
        for (int valor : arr) {
            if (valor == elemento) {
                contador++;
            }
        }
        
        if (contador == 0) return arr; // Si no está, devolvemos el original
        
        int[] nuevoArray = new int[arr.length - contador];
        int indiceNuevo = 0;
        
        // Copiar todos los elementos excepto el que se va a eliminar
        for (int valor : arr) {
            if (valor != elemento) {
                nuevoArray[indiceNuevo++] = valor;
            }
        }
        return nuevoArray;
    }
}
```

### Eliminación en Arrays Ordenados
El proceso es similar, pero se puede optimizar. Una vez encontrado el elemento, se copian las dos partes del array (la de antes y la de después) al nuevo array.

---

## 4. Copia de Arrays

Para crear una copia independiente de un array, se pueden usar los métodos de la clase `java.util.Arrays`.

```java
import java.util.Arrays;

public class CopiaArray {
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};
        
        // Crea una copia exacta del array original
        int[] copia = Arrays.copyOf(original, original.length);
        
        System.out.println("Copia: " + Arrays.toString(copia)); // Salida:
    }
}```

---

## 5. Comparación de Arrays

Para comprobar si dos arrays tienen el mismo contenido en el mismo orden, se usa el método `Arrays.equals()`.

> **Importante:** No uses `arr1 == arr2`. Esto solo comprueba si ambas variables apuntan al **mismo objeto** en memoria, no si su contenido es igual.

```java
import java.util.Arrays;

public class ComparacionArrays {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {3, 2, 1};
        
        // Compara el contenido de arr1 y arr2
        System.out.println(Arrays.equals(arr1, arr2)); // Salida: true
        
        // Compara el contenido de arr1 y arr3
        System.out.println(Arrays.equals(arr1, arr3)); // Salida: false
    }
}
```