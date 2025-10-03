# Guía de System.out para Salida en Consola en Java

En Java, `System.out` es el flujo de salida estándar que se utiliza para mostrar información en la consola o terminal. Sus métodos `print`, `println` y `printf` son fundamentales para la depuración y la interacción con el usuario.

### 1. `System.out.print()`: Imprimir sin Salto de Línea

El método `print()` escribe el contenido especificado en la consola sin añadir un salto de línea al final. Las siguientes llamadas a `print()` continuarán escribiendo en la misma línea.

**Ejemplo:**
```java
public class EjemploPrint {
    public static void main(String[] args) {
        System.out.print("Hola, ");
        System.out.print("Mundo");
        System.out.print(2023);
    }
}
```
**Salida:**
```
Hola, Mundo2023
```

### 2. `System.out.println()`: Imprimir con Salto de Línea

El método `println()` (print line) hace lo mismo que `print()`, pero añade un salto de línea al final, moviendo el cursor a la siguiente línea para la próxima salida.

**Ejemplo:**
```java
public class EjemploPrintln {
    public static void main(String[] args) {
        System.out.println("Hola, Mundo");
        System.out.println(2023);
    }
}
```
**Salida:**
```
Hola, Mundo
2023
```

### 3. `print()` vs. `println()`: Ejemplo Comparativo

- **`System.out.print()`**: Imprime y mantiene el cursor en la misma línea.
- **`System.out.println()`**: Imprime y mueve el cursor a la línea siguiente.

**Ejemplo:**
```java
public class EjemploDiferencia {
    public static void main(String[] args) {
        System.out.print("Hola Mundo! "); // Sin salto de línea
        System.out.println("Bienvenido a Java."); // Con salto de línea
        System.out.println("Esto está en una nueva línea.");
    }
}
```
**Salida:**
```
Hola Mundo! Bienvenido a Java.
Esto está en una nueva línea.
```

### 4. Imprimir Tipos de Datos y Concatenación

Puedes imprimir cualquier tipo de dato primitivo (`int`, `double`, `boolean`, `char`), cadenas de texto (`String`) y el resultado de expresiones. Para combinar texto y variables, se usa el operador `+`.

**Ejemplo:**
```java
public class EjemploVariables {
    public static void main(String[] args) {
        String nombre = "Carlos";
        int edad = 25;
        double salario = 3000.50;

        // Concatenar texto con variables
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        
        // Imprimir el resultado de una expresión
        System.out.print("Suma: " + (5 + 10)); // Imprime: Suma: 15
    }
}
```
**Salida:**
```
Nombre: Carlos
Edad: 25
Suma: 15
```

### 5. `System.out.printf()`: Salida con Formato

Para un control más preciso sobre la salida (por ejemplo, limitar decimales o alinear texto), se utiliza `printf()` (print formatted). Este método usa marcadores de posición para insertar variables en una cadena de formato.

**Sintaxis:** `System.out.printf("cadena con formato", argumento1, argumento2, ...);`

**Marcadores comunes:**
| Marcador | Tipo de Dato |
| :--- | :--- |
| `%s` | Cadenas (String) |
| `%d` | Números enteros (int, long) |
| `%f` | Números flotantes (float, double) |
| `%c` | Caracteres (char) |
| `%.2f`| Flotante con 2 decimales |
| `\n` | Salto de línea |

**Ejemplo:**
```java
public class EjemploPrintf {
    public static void main(String[] args) {
        String nombre = "Ana";
        int edad = 30;
        double salario = 2500.75;

        System.out.printf("Nombre: %s, Edad: %d, Salario: %.2f\n", nombre, edad, salario);
    }
}
```
**Salida:**
```
Nombre: Ana, Edad: 30, Salario: 2500.75
```

### 6. Caracteres de Escape

Son secuencias especiales dentro de una cadena de texto para dar formato.

| Secuencia | Descripción |
| :--- | :--- |
| `\n` | Nueva línea |
| `\t` | Tabulación |
| `\\` | Barra invertida |
| `\"` | Comilla doble |

**Ejemplo:**
```java
public class EjemploEscape {
    public static void main(String[] args) {
        System.out.println("Línea 1\nLínea 2");
        System.out.println("Texto con\ttabulación.");
        System.out.println("Ruta: C:\\Archivos\\Java");
        System.out.println("Él dijo: \"Hola\".");
    }
}
```
**Salida:**
```Línea 1
Línea 2
Texto con	tabulación.
Ruta: C:\Archivos\Java
Él dijo: "Hola".
```

### 7. Funcionalidades Adicionales

#### Impresión de Caracteres Unicode
Puedes imprimir símbolos especiales usando su código Unicode en el formato `\uXXXX`.

**Ejemplo:**
```java
public class EjemploUnicode {
    public static void main(String[] args) {
        System.out.println("Símbolo de corazón: \u2764");
        System.out.println("Símbolo de sombrilla: \u2602");
    }
}
```
#### Uso de Colores en la Consola (ANSI)
*Nota: Esto depende de la compatibilidad del terminal o consola.*
Se pueden usar secuencias de escape ANSI para cambiar el color del texto.

**Ejemplo:**
```java
public class EjemploColores {
    public static void main(String[] args) {
        String rojo = "\033[31m";
        String verde = "\033[32m";
        String reset = "\033[0m"; // Restablece al color por defecto

        System.out.println(rojo + "Este texto es rojo." + reset);
        System.out.println(verde + "Este texto es verde." + reset);
    }
}
```

### 8. Buenas Prácticas

1.  **Claridad**: Usa `println()` para separar lógicamente las salidas y hacer la consola más legible.
2.  **Concatenación**: Ten cuidado al concatenar múltiples valores para evitar espacios faltantes o sobrantes.
3.  **Formato**: Prefiere `printf()` para salidas complejas, especialmente con números, para mantener el código limpio y el resultado bien formateado.
``````
