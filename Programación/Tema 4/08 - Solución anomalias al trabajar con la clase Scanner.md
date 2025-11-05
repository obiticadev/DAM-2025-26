# Guía sobre la Anomalía de `nextInt()` y `nextLine()` en la Clase `Scanner` de Java

Al utilizar la clase `Scanner` para capturar entradas del usuario, es muy común encontrar un comportamiento inesperado al mezclar métodos como `nextInt()` (o `nextDouble()`, `nextFloat()`, etc.) con `nextLine()`. Esta guía explica por qué ocurre este problema y cómo solucionarlo de manera efectiva.

---

## 1. Descripción del Problema

Cuando se solicita al usuario un número y luego una cadena de texto, el programa parece "saltarse" la solicitud de la cadena.

### Código que Demuestra el Problema```java
import java.util.Scanner;

public class ScannerAnomalia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt(); // Lee el número, pero deja un salto de línea en el buffer.

        System.out.print("Ingrese su nombre: ");
        // nextLine() encuentra el salto de línea pendiente, lo consume, y no espera la entrada del usuario.
        String nombre = scanner.nextLine(); 

        System.out.println("\n--- Resultados ---");
        System.out.println("Nombre: '" + nombre + "'"); // El nombre estará vacío.
        System.out.println("Edad: " + edad);

        scanner.close();
    }
}
```

### Comportamiento Esperado vs. Real

**Entrada y Salida Esperada:**
```
Ingrese su edad: 25
Ingrese su nombre: Juan Pérez

--- Resultados ---
Nombre: 'Juan Pérez'
Edad: 25
```

**Entrada y Salida Real:**
```
Ingrese su edad: 25
Ingrese su nombre: 
--- Resultados ---
Nombre: ''
Edad: 25
```
Como se puede observar, el programa no espera a que el usuario ingrese el nombre.

---

## 2. La Causa de la Anomalía

El problema reside en cómo los diferentes métodos de `Scanner` tratan el buffer de entrada y los delimitadores.

1.  Cuando el usuario escribe `25` y presiona **Enter**, en el buffer de entrada se colocan los caracteres `2`, `5`, y el carácter de nueva línea (`\n`).
2.  El método `scanner.nextInt()` lee y consume los caracteres numéricos (`2` y `5`) y los convierte a un entero.
3.  Sin embargo, `nextInt()` **no consume el carácter de nueva línea (`\n`)**. Este carácter se queda "pendiente" en el buffer de entrada.
4.  Cuando se llama a `scanner.nextLine()`, su trabajo es leer hasta encontrar un salto de línea. Como encuentra inmediatamente el `\n` que `nextInt()` dejó, lo consume y devuelve una cadena vacía. Por eso, parece que se "salta" la entrada.

---

## 3. Soluciones Efectivas

Existen varias formas de solucionar este problema.

### Solución 1: Consumir el Salto de Línea Pendiente (La más común)

La solución más directa es añadir una llamada extra a `scanner.nextLine()` justo después de `scanner.nextInt()` para "limpiar" el buffer y consumir el salto de línea pendiente.

```java
import java.util.Scanner;

public class ScannerSolucion1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt();

        // Línea clave: consume el '\n' restante.
        scanner.nextLine(); 

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine(); // Ahora sí espera la entrada del usuario.

        System.out.println("\n--- Resultados ---");
        System.out.println("Nombre: '" + nombre + "'");
        System.out.println("Edad: " + edad);

        scanner.close();
    }
}
```

### Solución 2: Usar `nextLine()` para Todo y Convertir (La más robusta)

Una estrategia más segura y consistente es leer **todas** las entradas como una línea de texto completa usando `nextLine()` y luego convertir la cadena al tipo de dato deseado.

Esta aproximación evita por completo los problemas de delimitadores y facilita el manejo de errores (por ejemplo, si el usuario ingresa texto en lugar de un número).

```java
import java.util.Scanner;

public class ScannerSolucion2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese su edad: ");
            // Lee la línea completa como String.
            String edadStr = scanner.nextLine(); 
            // Convierte el String a int.
            int edad = Integer.parseInt(edadStr);

            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("\n--- Resultados ---");
            System.out.println("Nombre: '" + nombre + "'");
            System.out.println("Edad: " + edad);

        } catch (NumberFormatException e) {
            System.out.println("Error: La edad debe ser un número válido.");
        } finally {
            scanner.close();
        }
    }
}
```

**Ventajas de este método:**
*   **Consistencia:** Se usa un único método para leer la entrada.
*   **Robustez:** Es más fácil implementar validaciones y manejar `NumberFormatException` si la conversión falla.

---

## 4. Consideraciones Adicionales

*   **Cerrar el Scanner:** Es una buena práctica cerrar siempre el objeto `Scanner` con `scanner.close()` cuando ya no se necesita, para liberar los recursos del sistema.
*   **`scanner.next()` vs. `scanner.nextLine()`:** Recuerda que `scanner.next()` lee solo hasta el próximo espacio en blanco, mientras que `nextLine()` lee la línea completa. Para nombres y apellidos, `nextLine()` es casi siempre la opción correcta.