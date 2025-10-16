# Cómo Hacer Esperar a un Programa en Java

En Java, es posible pausar la ejecución de un programa durante un período de tiempo específico. Esto es útil para controlar el ritmo entre procesos, en simulaciones, juegos o en programación concurrente. Las dos formas principales de lograrlo son a través de `Thread.sleep()` y `TimeUnit.sleep()`.

---

## 1. Usando `Thread.sleep()`

Este es el método tradicional para crear pausas. Se encuentra en la clase `Thread` y detiene la ejecución del hilo actual.

### Descripción
El método `sleep()` es estático y acepta como argumento el tiempo de espera medido en **milisegundos**.

### Sintaxis
```java
Thread.sleep(milisegundos);
```

### Manejo de Excepciones
El método `Thread.sleep()` puede lanzar una `InterruptedException`. Esta es una **excepción comprobada (checked exception)**, lo que significa que estás **obligado** a manejarla, generalmente con un bloque `try-catch`. Esta excepción se lanza si otro hilo interrumpe al hilo actual mientras está "durmiendo".

### Ejemplo Básico
```java
public class ThreadSleepExample {
    public static void main(String[] args) {
        System.out.println("Inicio del retardo...");
        
        try {
            // Pausa el programa durante 2 segundos (2000 milisegundos)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Este bloque se ejecutaría si el hilo fuera interrumpido
            System.out.println("El hilo fue interrumpido.");
            // Restablece el estado de interrupción del hilo
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Fin del retardo.");
    }
}
```
**Salida:**
```
Inicio del retardo...
(espera 2 segundos)
Fin del retardo.
```

---

## 2. Usando `TimeUnit.sleep()`

Introducida en `java.util.concurrent`, la clase `TimeUnit` ofrece una forma más legible y flexible de manejar unidades de tiempo.

### Descripción
`TimeUnit` es un `enum` que proporciona métodos `sleep()` para diferentes unidades de tiempo, lo que mejora enormemente la claridad del código.

### Sintaxis
```java
import java.util.concurrent.TimeUnit;

// Ejemplos de uso
TimeUnit.SECONDS.sleep(segundos);
TimeUnit.MILLISECONDS.sleep(milisegundos);
TimeUnit.MINUTES.sleep(minutos);
```

### Manejo de Excepciones
Al igual que `Thread.sleep()`, `TimeUnit.sleep()` también lanza una `InterruptedException` y debe ser manejada.

### Ejemplo Básico
```java
import java.util.concurrent.TimeUnit;

public class TimeUnitExample {
    public static void main(String[] args) {
        System.out.println("Esperando 3 segundos...");
        
        try {
            // Pausa el programa durante 3 segundos
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Fin del retardo.");
    }
}
```
Este código es funcionalmente idéntico a `Thread.sleep(3000)`, pero `TimeUnit.SECONDS.sleep(3)` es mucho más fácil de leer.

---

## 3. `Thread.sleep()` vs. `TimeUnit.sleep()`: ¿Cuál Usar?

| Característica | `Thread.sleep()` | `TimeUnit.sleep()` (Recomendado) |
| :--- | :--- | :--- |
| **Legibilidad** | Baja. Requiere pensar siempre en milisegundos (`sleep(60000)`). | **Alta.** El código es autodocumentado (`MINUTES.sleep(1)`). |
| **Flexibilidad** | Limitada. Solo acepta milisegundos. | **Alta.** Permite especificar `DAYS`, `HOURS`, `MINUTES`, `SECONDS`, etc. |
| **Conversión** | Requiere cálculos manuales para otras unidades (`60 * 1000`). | Realiza las conversiones internamente. |
| **Excepción** | Ambos lanzan `InterruptedException` y requieren manejo. | Ambos lanzan `InterruptedException` y requieren manejo. |

**Conclusión:** Para cualquier aplicación moderna, es preferible usar `TimeUnit.sleep()` por su claridad y flexibilidad, a menos que estés trabajando con un codebase antiguo o tengas una razón específica para usar `Thread.sleep()`.

---

## 4. La Excepción `InterruptedException`

Esta excepción es fundamental en la programación concurrente (multihilo).

*   **¿Qué significa?** Se lanza cuando un hilo que está en estado de espera (`sleep()`, `wait()`, `join()`) es "despertado" por otro hilo antes de que termine su tiempo de espera.
*   **¿Por qué hay que manejarla?** Es una señal para que el hilo que estaba esperando detenga lo que está haciendo y reaccione a la interrupción.
*   **¿Cómo manejarla?** La práctica común es:
    1.  Capturar la excepción en un bloque `catch`.
    2.  Opcionalmente, realizar una limpieza si es necesario.
    3.  **Restablecer el estado de interrupción** llamando a `Thread.currentThread().interrupt()`. Esto es importante para que el código de más alto nivel también sepa que el hilo fue interrumpido.

```java
try {
    TimeUnit.SECONDS.sleep(10);
} catch (InterruptedException e) {
    System.err.println("La espera fue interrumpida.");
    // Buena práctica: preservar la señal de interrupción
    Thread.currentThread().interrupt();
}
```