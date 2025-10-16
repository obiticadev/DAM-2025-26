# Guía del Bucle for en Java

El bucle `for` es una estructura de control de repetición que permite ejecutar un bloque de código un número específico de veces. Es ideal cuando se conoce de antemano la cantidad exacta de iteraciones necesarias.

---

## 1. Sintaxis del Bucle `for` Clásico

El bucle `for` consta de tres partes principales, separadas por punto y coma, que controlan su ejecución.

### Estructura
```java
for (inicialización; condición; actualización) {
    // Bloque de código a repetir
}
```

### Componentes Clave
*   **Inicialización:** Se ejecuta **una sola vez** al inicio del bucle. Generalmente, aquí se declara e inicializa una variable contadora (ej. `int i = 0`).
*   **Condición:** Se evalúa **antes** de cada iteración. Mientras la condición sea `true`, el bucle continúa. Si se vuelve `false`, el bucle termina.
*   **Actualización:** Se ejecuta **al final** de cada iteración. Normalmente, se utiliza para incrementar o decrementar la variable contadora (ej. `i++`, `i--`, `i += 2`).

### Ejemplo 1: Imprimir números del 1 al 5
```java
public class CicloFor {
    public static void main(String[] args) {
        // inicialización: int i = 1
        // condición: i <= 5
        // actualización: i++
        for (int i = 1; i <= 5; i++) {
            System.out.println("Número: " + i);
        }
    }
}
```
**Salida:**
```
Número: 1
Número: 2
Número: 3
Número: 4
Número: 5
```

### Ejemplo 2: Imprimir números pares del 2 al 10
En este caso, la actualización incrementa el contador de 2 en 2.
```java
public class NumerosPares {
    public static void main(String[] args) {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println("Número par: " + i);
        }
    }
}
```
**Salida:**
```
Número par: 2
Número par: 4
Número par: 6
Número par: 8
Número par: 10
```
---

## 2. Variaciones del Bucle `for`

### Ciclo `for` en Decremento
El bucle también puede contar hacia atrás.
```java
public class CicloDecremento {
    public static void main(String[] args) {
        for (int i = 5; i > 0; i--) {
            System.out.println("Número: " + i);
        }
    }
}
```
**Salida:**
```
Número: 5
Número: 4
Número: 3
Número: 2
Número: 1
```

---

## 3. Bucles `for` Anidados

Es posible colocar un bucle dentro de otro. Esto es muy útil para trabajar con estructuras bidimensionales como matrices o para generar patrones.

### Ejemplo: Imprimir una tabla de multiplicar
```java
public class TablaMultiplicar {
    public static void main(String[] args) {
        // El bucle exterior controla las filas (i)
        for (int i = 1; i <= 5; i++) {
            // El bucle interior controla las columnas (j)
            for (int j = 1; j <= 5; j++) {
                System.out.print(i * j + "\t"); // Imprime el producto y un tabulador
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }
}
```
**Salida:**
```
1   2   3   4   5   
2   4   6   8   10  
3   6   9   12  15  
4   8   12  16  20  
5   10  15  20  25  
```

---

## 4. El Bucle `for-each` (Enhanced for Loop)

Java proporciona una sintaxis simplificada del bucle `for` para iterar sobre los elementos de un array o una colección de forma secuencial, sin necesidad de manejar un índice.

### Sintaxis
```java
for (Tipo variableElemento : coleccion) {
    // Código a ejecutar para cada elemento
}
```

### Ejemplo: Recorrer un array
```java
public class CicloForEach {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};

        // En cada iteración, 'numero' toma el valor del siguiente elemento del array 'numeros'
        for (int numero : numeros) {
            System.out.println("Número: " + numero);
        }
    }
}
```
**Salida:**
```
Número: 1
Número: 2
Número: 3
Número: 4
Número: 5
```

---

## 5. Control de Flujo en Bucles: `break` y `continue`

Estas dos sentencias permiten alterar el flujo normal de un bucle.

### `break`
La sentencia `break` **termina inmediatamente** la ejecución del bucle en el que se encuentra. El programa continúa en la siguiente instrucción después del bucle.

**Ejemplo:** Salir del bucle cuando `i` es 6.
```java
public class UsoBreak {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                break; // El bucle se detiene aquí
            }
            System.out.println("Número: " + i);
        }
        // La ejecución continúa aquí después del break
        System.out.println("Bucle terminado.");
    }
}```
**Salida:**
```
Número: 1
Número: 2
Número: 3
Número: 4
Número: 5
Bucle terminado.
```

### `continue`
La sentencia `continue` **salta la iteración actual** y pasa directamente a la siguiente. El código que está después del `continue` dentro del bucle no se ejecuta en esa iteración.

**Ejemplo:** Saltar la iteración cuando `i` es 6.
```java
public class UsoContinue {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                continue; // Salta esta iteración, no imprime el 6
            }
            System.out.println("Número: " + i);
        }
    }
}
```
**Salida:**
```
Número: 1
Número: 2
Número: 3
Número: 4
Número: 5
Número: 7
Número: 8
Número: 9
Número: 10
```
---

## 6. Ejemplo Práctico: Buscar un Valor en un Array

Este ejemplo combina un bucle `for` con una sentencia `if` y `break` para realizar una tarea común.
```java
public class BuscarEnArray {
    public static void main(String[] args) {
        int[] numeros = {3, 6, 8, 1, 9};
        int valorBuscado = 8;
        boolean encontrado = false;

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == valorBuscado) {
                encontrado = true;
                break; // Si lo encontramos, no es necesario seguir buscando
            }
        }

        if (encontrado) {
            System.out.println("El valor " + valorBuscado + " se encontró en el array.");
        } else {
            System.out.println("El valor " + valorBuscado + " no se encontró en el array.");
        }
    }
}
```
**Salida:**
```
El valor 8 se encontró en el array.
```