# Guía Práctica de Depuración de Código Java en Eclipse

La **depuración** (debugging) es el proceso de encontrar y corregir errores (bugs) en el código. Más que una simple búsqueda de fallos, es una técnica esencial para entender cómo fluye un programa, inspeccionar el estado de las variables en tiempo de ejecución y diagnosticar comportamientos inesperados.

Eclipse, como IDE (Entorno de Desarrollo Integrado), proporciona un conjunto de herramientas muy potentes para facilitar este proceso de forma interactiva.

---

## 1. Concepto Clave: Puntos de Ruptura (Breakpoints)

Un **punto de ruptura** o **breakpoint** es una marca que se coloca en una línea específica del código. Cuando el programa se ejecuta en modo de depuración, la ejecución se **detendrá** justo antes de ejecutar esa línea, permitiéndote inspeccionar el estado del programa en ese preciso instante.

### Cómo Colocar un Breakpoint
1.  Abre el archivo `.java` que quieres depurar.
2.  Haz doble clic o clic derecho en la barra vertical a la izquierda del número de línea donde quieres que el programa se detenga.
3.  Aparecerá un círculo azul, indicando que el breakpoint está activo.

**Ejemplo:**
```java
public class DepuracionEjemplo {
    public static void main(String[] args) {
        int x = 10;
        int y = 5;
        // Colocar un breakpoint en la siguiente línea
        int resultado = sumar(x, y); 
        System.out.println("Resultado de la suma: " + resultado);
    }

    public static int sumar(int a, int b) {
        // También se puede colocar un breakpoint aquí
        int suma = a + b;
        return suma;
    }
}
```

---

## 2. Iniciar una Sesión de Depuración

Una vez que has colocado al menos un breakpoint, puedes iniciar la depuración:

1.  Haz clic derecho en el archivo que contiene tu método `main`.
2.  Selecciona **Debug As > Java Application**.
3.  Eclipse te preguntará si quieres cambiar a la "perspectiva de depuración" (Debug Perspective). Haz clic en **Switch**.

La ejecución del programa comenzará y se detendrá automáticamente en el primer breakpoint que encuentre. La línea donde se ha detenido aparecerá resaltada en verde.

---

## 3. La Perspectiva de Depuración de Eclipse

Cuando entras en modo depuración, Eclipse reorganiza su interfaz para mostrar paneles (vistas) especializados:

*   **Vista `Debug`:** Muestra la pila de llamadas (`call stack`), que es la secuencia de métodos que se han llamado para llegar al punto actual. Es útil para rastrear el flujo del programa.
*   **Vista `Variables`:** **La más importante.** Muestra todas las variables accesibles en el ámbito actual (locales, de instancia) y sus valores. Puedes incluso cambiar los valores de las variables en tiempo real para probar diferentes escenarios.
*   **Vista `Breakpoints`:** Lista todos los breakpoints que has establecido en tu proyecto, permitiéndote activarlos o desactivarlos.
*   **Vista `Expressions`:** Permite escribir y evaluar expresiones personalizadas en tiempo real usando las variables actuales (ej. `x * 2` o `a > b`).

---

## 4. Controlando la Ejecución (Paso a Paso)

Una vez que el programa está pausado en un breakpoint, tienes varios controles para avanzar en la ejecución:

*   **`Resume` (F8):** Reanuda la ejecución normal del programa. Continuará hasta que encuentre el siguiente breakpoint o hasta que el programa termine.

*   **`Step Over` (F6):** Ejecuta la línea actual resaltada y avanza a la siguiente línea **dentro del mismo método**. Si la línea actual es una llamada a otro método, `Step Over` lo ejecutará por completo sin entrar en él. Es útil para "saltar" métodos que sabes que funcionan bien.

*   **`Step Into` (F5):** Si la línea actual contiene una llamada a un método, `Step Into` **entrará** en ese método, permitiéndote depurarlo línea por línea. Es fundamental para investigar el comportamiento dentro de tus propias funciones.

*   **`Step Return` (F7):** Ejecuta el resto del método actual y sale de él, devolviendo el control al método que lo llamó. Es útil para salir rápidamente de un método en el que ya no necesitas depurar.

*   **`Terminate` (Ctrl+F2):** Detiene por completo la sesión de depuración.

### Flujo de Depuración Típico
1.  Inicias en `Debug As...` y el programa se detiene en el primer breakpoint.
2.  Inspeccionas los valores en la vista `Variables`.
3.  Usas `Step Over` (F6) para avanzar línea por línea.
4.  Cuando llegas a una llamada a un método que quieres investigar, usas `Step Into` (F5).
5.  Una vez dentro del método, sigues con `Step Over` (F6).
6.  Cuando terminas de revisar un método, usas `Step Return` (F7) para salir de él.
7.  Repites el proceso hasta encontrar el problema.

---

## 5. Breakpoints Condicionales

A veces, quieres que un breakpoint solo se active si se cumple una cierta condición (por ejemplo, dentro de un bucle, solo cuando `i > 50`).

### Cómo Crear un Breakpoint Condicional
1.  Establece un breakpoint normal.
2.  Haz clic derecho sobre el círculo azul del breakpoint.
3.  Selecciona **Breakpoint Properties**.
4.  Marca la casilla **Conditional**.
5.  Escribe una expresión booleana de Java en el cuadro de texto (ej. `x == 10` o `nombre.equals("admin")`).

Ahora, el depurador solo se detendrá en esa línea si la condición que has especificado es `true`. Esto es extremadamente útil para depurar errores que solo ocurren en escenarios específicos.