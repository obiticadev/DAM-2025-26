# Informe Educativo: Dominando el Manejo de Errores en Java (Try-Catch)

## 1. Introducción: ¿Qué es una Excepción?
Imagina que tu código es una receta de cocina. Una **Excepción** es un evento imprevisto que rompe el flujo normal de esa receta, como quedarse sin huevos o que el horno explote.

En programación, si no manejas estos eventos, el programa se cierra abruptamente (crashea) y el usuario ve errores en rojo incomprensibles.
*   **Sin manejo de errores:** El programa muere.
*   **Con manejo de errores (`try-catch`):** El programa atrapa el error, avisa al usuario y continúa funcionando.

## 2. La Analogía del Trapecista
Para entender la estructura, usemos la metáfora del circo:
*   **El código normal** es un trapecista haciendo acrobacias.
*   **`try` (Intentar):** Es el acto del trapecista. Aquí ponemos el código "peligroso" que podría fallar.
*   **`catch` (Atrapar):** Es la red de seguridad. Si el trapecista cae (ocurre un error), la red lo atrapa para que no se mate. Aquí ponemos el código de recuperación (mostrar un mensaje de error).
*   **`finally` (Finalmente):** Es apagar las luces del circo al terminar, ocurra o no el accidente. Se ejecuta **siempre**.

## 3. Estructura Básica y Sintaxis
Esta es la anatomía fundamental que todo programador debe conocer.

### Bloque TRY
Es donde encerramos el código riesgoso. Java vigila este bloque.
```java
try {
    // Código que puede fallar
    int resultado = 10 / 0; // Esto es imposible matemáticamente
}
```

### Bloque CATCH
Se activa **solo** si ocurre un error en el `try`. Debe especificar qué tipo de error está esperando atrapar.
```java
catch (ArithmeticException e) {
    // Qué hacer si falla
    System.out.println("¡No puedes dividir por cero!");
}
```

## 4. El Problema del Scanner (InputMismatchException)
Este es el caso más común en programas de consola y el más difícil de entender para principiantes.

**El Escenario:** Pides un número entero (`nextInt`), pero el usuario escribe letras ("hola").
**El Error:** Java lanza una `InputMismatchException`.

### ¿Por qué se crea un bucle infinito si no lo manejo bien?
Cuando el usuario escribe "hola", ese dato entra en el "buzón" (buffer) del Scanner.
1.  `nextInt()` intenta leerlo, ve que no es un número y lanza error.
2.  Si usas un bucle `while` pero **no limpias** el buzón, el programa vuelve a intentar leer "hola" una y otra vez infinitamente.

### La Solución: Limpieza de Buffer
Dentro del `catch`, debemos obligar al scanner a descartar la entrada incorrecta.

```java
try {
    numero = sc.nextInt();
} catch (InputMismatchException e) {
    System.out.println("Error: Escribe un número, no letras.");
    sc.nextLine(); // <--- EL PASO CRÍTICO: Limpia la basura del scanner
}
```

## 5. Jerarquía de Excepciones: De lo Específico a lo General
No todos los errores son iguales. Puedes tener múltiples bloques `catch` para atrapar diferentes problemas. Es como tener diferentes redes para diferentes tipos de caídas.

1.  **Excepciones Específicas:** Se colocan primero.
    *   `InputMismatchException`: Error de tipo de dato (letra en vez de número).
    *   `ArithmeticException`: Error matemático (dividir por cero).
    *   `NullPointerException`: Intentar usar algo que no existe (es nulo).
2.  **Excepción Genérica (`Exception`):** Se coloca al final. Es el "padre" de todos los errores. Atrapa cualquier cosa que los anteriores no hayan atrapado.

**Regla de oro:** Siempre captura primero lo específico y luego lo general.

## 6. Diferencia entre `throw` y `throws`
A menudo causan confusión por sus nombres similares.

*   **`throw` (Lanzar - Verbo activo):** Tú, como programador, generas un error a propósito.
    *   *Ejemplo:* `if (edad < 18) throw new Exception("Eres menor de edad");`
    *   Se usa para validar reglas de negocio (ej: no permitir números negativos en un array).

*   **`throws` (Avisa - Advertencia):** Se pone en la firma del método. Avisa a quien use ese método que "podría ocurrir un error aquí, prepárate".
    *   *Ejemplo:* `public void leerArchivo() throws IOException { ... }`

## 7. Patrón de Diseño: "Validación Robusta"
Para menús y entradas de datos, el patrón profesional es combinar `try-catch` con un bucle `while`.

**El Algoritmo:**
1.  Define una bandera (semáforo) llamada `esValido = false`.
2.  Inicia un bucle `while (!esValido)`.
3.  Dentro, abre un `try`.
4.  Pide el dato.
5.  Si el dato es correcto, cambia `esValido = true` (el bucle termina).
6.  En el `catch`, muestra el error y **limpia el scanner**. El bucle se repite porque `esValido` sigue siendo false.

Este patrón garantiza que el programa **nunca** avance hasta que el usuario introduzca el dato correcto.