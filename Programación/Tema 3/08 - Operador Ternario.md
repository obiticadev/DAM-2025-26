# Guía del Operador Ternario en Java

El operador ternario es una forma compacta y abreviada de la estructura condicional `if-else`. Permite asignar un valor a una variable o ejecutar una expresión basándose en una condición, todo en una sola línea.

Se llama **ternario** porque involucra tres operandos:
1.  Una condición booleana.
2.  Un valor si la condición es `true`.
3.  Un valor si la condición es `false`.

---

## 1. Sintaxis

La estructura del operador ternario es simple y directa.

```java
resultado = (condicion) ? valor_si_es_verdadero : valor_si_es_falso;
```

*   **`condicion`**: Una expresión que se evalúa como `true` o `false`.
*   **`?`**: Separa la condición del valor verdadero.
*   **`valor_si_es_verdadero`**: El valor que se asignará a `resultado` si la condición es `true`.
*   **`:`**: Separa el valor verdadero del valor falso.
*   **`valor_si_es_falso`**: El valor que se asignará a `resultado` si la condición es `false`.

### Comparación Visual con `if-else`

El operador ternario es un reemplazo directo para una estructura `if-else` simple que solo realiza una asignación.

**Estructura `if-else`:**
```java
if (x > y) {
    mayor = x;
} else {
    mayor = y;
}
```

**Equivalente con Operador Ternario:**
```java
mayor = (x > y) ? x : y;
```

---

## 2. Ventajas y Desventajas

### Ventajas
*   **Código Compacto:** Reduce varias líneas de un `if-else` a una sola, haciendo el código más limpio en casos simples.
*   **Rápido de Escribir:** Ideal para asignaciones condicionales rápidas.

### Desventajas
*   **Menos Legible en Casos Complejos:** Anidar operadores ternarios (`cond1 ? val1 : (cond2 ? val2 : val3)`) puede hacer que el código sea muy difícil de leer y entender.
*   **Limitado a una Sola Acción:** Está diseñado para devolver un único valor. Para ejecutar múltiples sentencias o una lógica más compleja, la estructura `if-else` es siempre la mejor opción.

---

## 3. Ejemplos Prácticos

### Ejemplo 1: Encontrar el Mayor de Dos Números
```java
public class MayorDeDos {
    public static void main(String[] args) {
        int a = 5, b = 10;
        int mayor = (a > b) ? a : b;
        System.out.println("El mayor número es: " + mayor); // Salida: 10
    }
}
```
**Explicación:** Como la condición `(a > b)` es falsa, se asigna el valor que está después de los dos puntos (`b`).

### Ejemplo 2: Verificar si un Número es Par o Impar
```java
public class ParOImpar {
    public static void main(String[] args) {
        int numero = 7;
        String resultado = (numero % 2 == 0) ? "Par" : "Impar";
        System.out.println("El número es: " + resultado); // Salida: Impar
    }
}```
**Explicación:** La condición `(7 % 2 == 0)` es falsa, por lo que se asigna el string `"Impar"`.

### Ejemplo 3: Verificar si una Persona es Mayor de Edad
```java
public class MayorDeEdad {
    public static void main(String[] args) {
        int edad = 20;
        String resultado = (edad >= 18) ? "Mayor de edad" : "Menor de edad";
        System.out.println("La persona es: " + resultado); // Salida: Mayor de edad
    }
}
```

### Ejemplo 4: Operador Ternario Anidado
Aunque no es muy recomendable por su legibilidad, es posible anidar operadores ternarios.

```java
public class PositivoNegativoCero {
    public static void main(String[] args) {
        int numero = -5;
        
        String resultado = (numero > 0) ? "Positivo" 
                                        : ((numero < 0) ? "Negativo" : "Cero");

        System.out.println("El número es: " + resultado); // Salida: Negativo
    }
}
```
**Explicación de la lógica anidada:**
1.  Se evalúa `(numero > 0)`. Es `false`.
2.  Se pasa a la parte "falsa" del primer ternario, que es otro operador ternario completo: `((numero < 0) ? "Negativo" : "Cero")`.
3.  Se evalúa la segunda condición `(numero < 0)`. Es `true`.
4.  Se asigna el valor "verdadero" de este segundo ternario, que es `"Negativo"`.