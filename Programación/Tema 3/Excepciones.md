# Guía de Excepciones Comunes en Java

En Java, las excepciones se dividen en dos categorías principales: **comprobadas (checked)** y **no comprobadas (unchecked)**. Entender la diferencia es fundamental para un buen manejo de errores.

*   **Excepciones Comprobadas (Checked Exceptions):** Son condiciones de error que un programa bien escrito debería anticipar y de las que debería poder recuperarse. El compilador de Java **obliga** a que se manejen, ya sea con un bloque `try-catch` o declarando que el método las lanza con `throws`. Generalmente, están relacionadas con factores externos al programa, como problemas de red o de sistema de archivos.

*   **Excepciones No Comprobadas (Unchecked Exceptions / Runtime Exceptions):** Representan errores de programación o lógica interna (bugs). El compilador no obliga a manejarlas, aunque es posible hacerlo. Si no se capturan, suelen provocar la terminación del programa. Ejemplos típicos son intentar acceder a un objeto nulo o a un índice de array que no existe.

A continuación se presenta una tabla de referencia con las excepciones más comunes de cada tipo.

---

## Excepciones Comprobadas (Checked Exceptions)

| Excepción | Descripción |
| :--- | :--- |
| `IOException` | Error genérico de entrada/salida. |
| `FileNotFoundException` | Se intenta acceder a un archivo que no existe. |
| `SQLException` | Ocurre un error al interactuar con una base de datos. |
| `ClassNotFoundException` | No se encuentra la definición de una clase en tiempo de ejecución. |
| `InterruptedException` | Un hilo en estado de espera (`sleep`, `wait`) es interrumpido por otro hilo. |
| `InstantiationException` | Se intenta crear una instancia de una clase abstracta o una interfaz. |
| `IllegalAccessException` | Se intenta acceder a un campo o método sin los permisos necesarios. |
| `NoSuchFieldException` | Se intenta acceder a un campo de una clase que no existe. |
| `NoSuchMethodException` | Se intenta llamar a un método que no existe. |
| `CloneNotSupportedException` | Se intenta clonar un objeto que no implementa la interfaz `Cloneable`. |
| `EOFException` | Se alcanza el fin de un archivo o stream de forma inesperada durante una lectura. |
| `MalformedURLException` | Una URL no tiene el formato sintáctico correcto. |
| `SocketException` | Ocurre un error al crear o acceder a un socket de red. |
| `UnknownHostException` | No se puede determinar la dirección IP de un host. |
| `ConnectException` | Falla la conexión a un servidor remoto. |

---

## Excepciones No Comprobadas (Unchecked / Runtime Exceptions)

| Excepción | Descripción |
| :--- | :--- |
| `ArithmeticException` | Ocurre una condición aritmética excepcional, como una división entera por cero. |
| `NullPointerException` | Se intenta usar una referencia a un objeto que es `null`. |
| `ArrayIndexOutOfBoundsException`| Se intenta acceder a un índice de un array que es negativo o mayor o igual a su tamaño. |
| `ClassCastException` | Se intenta convertir (hacer un "cast") un objeto a un tipo del cual no es una instancia. |
| `IllegalArgumentException` | Se pasa un argumento inválido o inapropiado a un método. |
| `IllegalStateException` | Un método es invocado en un momento o estado inapropiado del objeto. |
| `NumberFormatException` | Se intenta convertir una cadena de texto a un tipo numérico, pero la cadena no tiene el formato adecuado. |
| `IndexOutOfBoundsException` | Índice fuera de rango en una colección (como `ArrayList`). Es la superclase de `ArrayIndexOutOfBoundsException`. |
| `SecurityException` | Un gestor de seguridad detecta una violación de la seguridad. |
| `UnsupportedOperationException` | Se invoca una operación que no está soportada por el objeto (ej. modificar una lista inmutable). |
| `ConcurrentModificationException` | Se detecta una modificación concurrente de una colección mientras se itera sobre ella. |
| `EmptyStackException` | Se intenta acceder a un elemento de una pila (`Stack`) que está vacía. |