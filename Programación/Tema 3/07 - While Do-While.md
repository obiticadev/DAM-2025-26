# Guía de Bucles while y do-while en Java

Los bucles `while` y `do-while` son estructuras de control que repiten un bloque de código mientras una condición específica se mantenga verdadera. La principal diferencia entre ellos es el momento en que se evalúa dicha condición.

---

## 1. Bucle `while`

El bucle `while` es una estructura de **pre-condición**. Evalúa la condición **antes** de ejecutar el bloque de código. Si la condición es `false` desde el principio, el bucle no se ejecutará ni una sola vez.

### Sintaxis
```java
while (condicion) {
    // Código a ejecutar mientras la condición sea verdadera
}
```
**Flujo de ejecución:**
1.  Se evalúa la `condicion`.
2.  Si es `true`, se ejecuta el bloque de código.
3.  Se vuelve al paso 1.
4.  Si es `false`, el bucle termina.

### Ejemplo: Imprimir números del 1 al 5
```java
public class EjemploWhile {
    public static void main(String[] args) {
        int contador = 1; // 1. Inicialización

        while (contador <= 5) { // 2. Condición
            System.out.println(contador);
            contador++; // 3. Actualización (¡crucial para evitar bucles infinitos!)
        }
    }
}
```
**Salida:**
```
1
2
3
4
5
```

---

## 2. Bucle `do-while`

El bucle `do-while` es una estructura de **post-condición**. Ejecuta el bloque de código **primero** y luego evalúa la condición. Esto garantiza que el bloque de código se ejecutará **al menos una vez**, sin importar si la condición es verdadera o falsa.

### Sintaxis
```java
do {
    // Código a ejecutar (se ejecuta al menos una vez)
} while (condicion);
```
**Flujo de ejecución:**
1.  Se ejecuta el bloque de código.
2.  Se evalúa la `condicion`.
3.  Si es `true`, se vuelve al paso 1.
4.  Si es `false`, el bucle termina.

### Ejemplo: Imprimir números del 1 al 5
```java
public class EjemploDoWhile {
    public static void main(String[] args) {
        int contador = 1;

        do {
            System.out.println(contador);
            contador++;
        } while (contador <= 5);
    }
}
```
**Salida:**
```
1
2
3
4
5
```

---

## 3. Diferencia Clave: `while` vs. `do-while`

La diferencia fundamental es **cuándo se comprueba la condición**.

*   **`while`:** Comprueba primero, ejecuta después. **Puede que no se ejecute nunca.**
*   **`do-while`:** Ejecuta primero, comprueba después. **Se ejecuta al menos una vez, garantizado.**

### Ejemplo Comparativo
```java
public class DiferenciaWhile {
    public static void main(String[] args) {
        int contador = 6;

        // Bucle while: la condición (6 <= 5) es falsa, no entra.
        System.out.println("Evaluando el bucle while...");
        while (contador <= 5) {
            System.out.println("Esto no se ejecutará");
        }

        // Bucle do-while: primero ejecuta el bloque, luego comprueba.
        System.out.println("\nEvaluando el bucle do-while...");
        do {
            System.out.println("Esto se ejecuta al menos una vez");
        } while (contador <= 5); // La condición (6 <= 5) es falsa, pero ya se ejecutó una vez.
    }
}
```
**Salida:**
```
Evaluando el bucle while...

Evaluando el bucle do-while...
Esto se ejecuta al menos una vez
```

---

## 4. Resumen Comparativo

| Característica | Bucle `while` | Bucle `do-while` |
| :--- | :--- | :--- |
| **Tipo de bucle** | Bucle de pre-condición (Entry-Controlled) | Bucle de post-condición (Exit-Controlled) |
| **Evaluación** | La condición se evalúa **antes** de la iteración. | La condición se evalúa **después** de la iteración. |
| **Garantía de ejecución**| Puede no ejecutarse nunca. | Se ejecuta **al menos una vez**. |
| **Uso común** | Para iterar mientras una condición general sea cierta (ej. "mientras haya líneas en el archivo"). | Para menús de opciones o validación de entradas, donde se necesita una primera acción. |

---

## 5. Cuidado con los Bucles Infinitos

Un bucle infinito ocurre si su condición de terminación nunca se vuelve `false`. Esto suele pasar cuando se olvida actualizar la variable que controla el bucle.

### Ejemplo de Bucle Infinito
```java
public class BucleInfinito {
    public static void main(String[] args) {
        int contador = 1;

        while (contador > 0) { // Esta condición siempre será verdadera
            System.out.println("Bucle infinito...");
            // Falta la actualización (ej. contador--), por lo que 'contador' nunca deja de ser > 0
        }
    }
}
```
Este programa imprimirá "Bucle infinito..." sin parar y deberá ser terminado manualmente.