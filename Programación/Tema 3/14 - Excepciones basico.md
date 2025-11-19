# Guía de Manejo de Excepciones en Java

En Java, una **excepción** es un evento inesperado que ocurre durante la ejecución de un programa e interrumpe el flujo normal de sus instrucciones. El manejo de excepciones es el mecanismo que permite controlar estos errores de forma robusta para evitar que el programa se detenga abruptamente.

---

## 1. Estructura Básica: `try-catch-finally`

La estructura fundamental para manejar excepciones se compone de tres bloques: `try`, `catch` y `finally`.

### Sintaxis
```java
try {
    // Código "protegido" que puede lanzar una excepción.
} catch (TipoDeExcepcion e) {
    // Código que se ejecuta si se produce la excepción especificada.
} finally {
    // Código que se ejecuta siempre, haya o no una excepción.
}
```

### Componentes Clave

1.  **Bloque `try`:**
    *   Aquí se coloca el código que podría generar un error.
    *   Si se produce una excepción dentro de este bloque, el flujo normal se interrumpe y Java busca un bloque `catch` que pueda manejarla.
    *   Si no ocurre ninguna excepción, el bloque (o bloques) `catch` se ignora por completo.

2.  **Bloque `catch`:**
    *   Este bloque "captura" y maneja la excepción.
    *   Se ejecuta **solo si** se produce una excepción en el bloque `try` que coincida con el `TipoDeExcepcion` especificado.
    *   Puedes tener múltiples bloques `catch` para manejar diferentes tipos de excepciones.

3.  **Bloque `finally`:**
    *   Este bloque es **opcional**.
    *   El código dentro de `finally` se ejecuta **siempre**, sin importar si se produjo una excepción o no.
    *   Es el lugar ideal para liberar recursos, como cerrar archivos, conexiones de red o bases de datos, garantizando que no queden abiertos incluso si ocurre un error.

---

## 2. Ejemplo Práctico: División por Cero

Este es el ejemplo clásico para ilustrar el manejo de excepciones. Intentaremos dividir un número por cero, lo cual lanza una `ArithmeticException`.

```java
import java.util.Scanner;

public class EjemploExcepciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dividendo, divisor;

        System.out.print("Ingrese el dividendo: ");
        dividendo = scanner.nextInt();
        System.out.print("Ingrese el divisor: ");
        divisor = scanner.nextInt();

        try {
            // Se intenta realizar la división. Esto puede lanzar una ArithmeticException.
            int resultado = dividendo / divisor;
            System.out.println("El resultado de la división es: " + resultado);
        } catch (ArithmeticException e) {
            // Se captura la excepción y se muestra un mensaje de error amigable.
            System.out.println("Error: No se puede dividir por cero.");
        } finally {
            // Este bloque se ejecuta siempre para cerrar el scanner.
            System.out.println("Operación finalizada.");
            scanner.close();
        }
    }
}
```

### Análisis del Flujo
*   **Si el usuario ingresa `10` y `2`:**
    1.  El `try` se ejecuta con éxito.
    2.  Se imprime "El resultado de la división es: 5".
    3.  El `catch` se ignora.
    4.  El `finally` se ejecuta, imprimiendo "Operación finalizada." y cerrando el scanner.

*   **Si el usuario ingresa `10` y `0`:**
    1.  El `try` intenta dividir `10 / 0`, lo que lanza una `ArithmeticException`.
    2.  El flujo salta inmediatamente al bloque `catch (ArithmeticException e)`.
    3.  Se imprime "Error: No se puede dividir por cero.".
    4.  El `finally` se ejecuta, imprimiendo "Operación finalizada." y cerrando el scanner.

---

## 3. Manejo de Múltiples Excepciones

Un solo bloque `try` puede generar diferentes tipos de excepciones. Puedes usar múltiples bloques `catch` para manejarlas de forma específica.

> **Importante:** Las excepciones deben ordenarse de la más específica a la más general. Si colocas `catch (Exception e)` primero, capturará todos los errores y los bloques `catch` más específicos que le sigan nunca se ejecutarán.

### Sintaxis
```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplesExcepciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Ingrese un número entero: ");
            int numero = scanner.nextInt(); // Puede lanzar InputMismatchException si no se ingresa un número.
            
            int resultado = 100 / numero; // Puede lanzar ArithmeticException si el número es 0.
            
            System.out.println("Resultado: " + resultado);

        } catch (ArithmeticException e) {
            // Maneja específicamente la división por cero.
            System.out.println("Error: No se puede dividir por cero.");
            
        } catch (InputMismatchException e) {
            // Maneja el caso en que la entrada no es un entero válido.
            System.out.println("Error: Debe ingresar un número entero.");
            
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada. Es una buena práctica tenerlo al final.
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
```