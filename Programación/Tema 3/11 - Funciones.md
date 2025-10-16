# Guía de Funciones (Métodos) in Java

En Java, las **funciones** (más comúnmente llamadas **métodos**) son bloques de código reutilizables diseñados para realizar una tarea específica. Son la base para organizar el código en piezas lógicas y manejables, lo que facilita enormemente la reutilización y el mantenimiento del software.

---

## 1. Anatomía de una Función

La estructura o "firma" de una función en Java define cómo se declara y qué componentes tiene.

### Sintaxis General
```java
modificadorDeAcceso static tipoDeRetorno nombreDeFuncion(tipo Parametro1, tipo Parametro2) {
    // Cuerpo de la función: el código que realiza la tarea
    // ...
    return valor; // Se usa si el tipo de retorno no es void
}
```

### Componentes Clave
1.  **Modificador de Acceso:** (Ej. `public`, `private`) Define desde dónde se puede llamar a la función.
    *   `public`: Accesible desde cualquier otra clase.
    *   `private`: Solo accesible desde dentro de la misma clase.
    *   `protected`: Accesible dentro del mismo paquete y por subclases.
    *   (ninguno): Accesible solo dentro del mismo paquete.
2.  **`static` (Opcional):** Indica que el método pertenece a la clase en sí, no a una instancia de la clase. Esto permite llamarlo directamente usando el nombre de la clase (ej. `Math.sqrt()`), sin necesidad de crear un objeto.
3.  **Tipo de Retorno:** (Ej. `int`, `String`, `void`) Especifica el tipo de dato del valor que la función devuelve. Si la función no devuelve ningún valor, se usa la palabra clave `void`.
4.  **Nombre de la Función:** (Ej. `calcularSuma`) Un nombre descriptivo que sigue la convención *camelCase*.
5.  **Parámetros (Opcional):** (Ej. `(int a, int b)`) Una lista de variables que la función recibe como entrada para trabajar con ellas. Se especifican con su tipo y nombre.
6.  **Cuerpo de la Función:** El bloque de código entre llaves `{}` que contiene las instrucciones a ejecutar.
7.  **`return`:** La palabra clave que devuelve un valor al código que llamó a la función. El tipo del valor devuelto debe coincidir con el `tipoDeRetorno`.

---

## 2. Tipos de Funciones

### Funciones sin Parámetros ni Retorno (`void`)

Estas funciones simplemente ejecutan una acción, como imprimir un mensaje. No reciben datos de entrada ni devuelven un resultado.

```java
public class EjemploSimple {
    // Función que no recibe parámetros y no devuelve valor
    public static void mostrarMensaje() {
        System.out.println("¡Hola, Mundo!");
    }

    public static void main(String[] args) {
        // Llamada a la función
        mostrarMensaje(); // Imprime: ¡Hola, Mundo!
    }
}
```

### Funciones con Parámetros y con Retorno

Estas funciones son las más comunes. Reciben datos, los procesan y devuelven un resultado.

```java
public class FuncionesConParametros {
    // Función que recibe dos enteros (a y b) y devuelve su producto (un entero)
    public static int multiplicar(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        // Llamada a la función, pasando 4 y 6 como argumentos
        int resultado = multiplicar(4, 6);
        System.out.println("El resultado es: " + resultado); // Imprime: 24
    }
}
```

### Funciones que Devuelven un Booleano
Son muy útiles para realizar comprobaciones y devolver `true` o `false`.

```java
public class FuncionesBooleanas {
    // Función que retorna true si un número es par, y false si no lo es
    public static boolean esPar(int numero) {
        return numero % 2 == 0;
    }

    public static void main(String[] args) {
        boolean resultado = esPar(10);
        System.out.println("¿El número 10 es par? " + resultado); // Imprime: ¿El número 10 es par? true
    }
}
```

---

## 3. Paso de Parámetros por Valor

En Java, todos los parámetros de tipos primitivos (`int`, `double`, `boolean`, etc.) se pasan **por valor**. Esto significa que la función recibe una **copia** del valor de la variable original, no la variable en sí.

**Consecuencia:** Cualquier modificación que se haga al parámetro dentro de la función **no afectará a la variable original** fuera de ella.

### Ejemplo Demostrativo
```java
public class PasoPorValor {
    // Esta función intenta cambiar el valor de su parámetro 'num'
    public static void cambiarValor(int num) {
        num = 10; // Se modifica la COPIA
        System.out.println("Valor dentro de la función: " + num);
    }

    public static void main(String[] args) {
        int numeroOriginal = 5;
        System.out.println("Valor antes de llamar a la función: " + numeroOriginal);

        cambiarValor(numeroOriginal); // Se pasa una copia del valor 5

        System.out.println("Valor después de llamar a la función: " + numeroOriginal);
    }
}
```
**Salida:**
```
Valor antes de llamar a la función: 5
Valor dentro de la función: 10
Valor después de llamar a la función: 5
```
Como se puede ver, la variable `numeroOriginal` mantiene su valor original de `5`.