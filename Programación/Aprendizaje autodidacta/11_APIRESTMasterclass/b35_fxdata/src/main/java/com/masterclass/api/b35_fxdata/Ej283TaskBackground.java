package com.masterclass.api.b35_fxdata;

import java.util.List;
import java.util.function.Supplier;

import javafx.concurrent.Task;

/**
 * Ejercicio 283 · {@code Task<V>}: {@code call()}, {@code updateProgress}/{@code updateMessage} y el
 * hilo de fondo.
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 5).
 *
 * <p>Un {@code Task<V>} encapsula trabajo largo para ejecutarlo FUERA del hilo de la interfaz y no
 * congelarla. Implementas su método {@code call()} (lo que se ejecuta en segundo plano) y desde
 * dentro reportas avance con {@code updateProgress(hechos, total)} y {@code updateMessage(texto)};
 * el valor que devuelve {@code call()} queda accesible. Como {@code Task} ES un {@code Future},
 * podemos probarlo de forma 100% determinista: lo ejecutamos y leemos su resultado con
 * {@code get()}, sin abrir ventana ni dormir el hilo a ciegas.
 */
public final class Ej283TaskBackground {

    private Ej283TaskBackground() {
    }

    /**
     * Crea un {@link Task} que, en su {@code call()}, suma los enteros de 1 a {@code n} reportando
     * progreso y mensaje en cada paso, y devuelve la suma.
     *
     * @param n cota superior de la suma (1+2+...+n)
     * @return el Task listo para ejecutarse (no lo ejecuta); {@code null} sin implementar
     */
    public static Task<Integer> tareaSuma(int n) {
        // TODO 1: devuelve un new Task<Integer>() { @Override protected Integer call() { ... } }.
        // TODO 2: dentro de call(), inicializa int suma = 0.
        // TODO 3: bucle for i de 1 a n: suma += i.
        // TODO 4: en cada vuelta reporta progreso con updateProgress(i, n).
        // TODO 5: en cada vuelta reporta el mensaje con updateMessage("Sumando " + i).
        // TODO 6: al terminar el bucle, return suma (será el getValue()/get() del Task).
        return null;
    }

    /**
     * Crea un {@link Task} cuyo {@code call()} lanza una excepción controlada (simula un fallo de
     * trabajo de fondo, p.ej. la red caída).
     *
     * @return el Task que fallará al ejecutarse; {@code null} sin implementar
     */
    public static Task<Integer> tareaQueFalla() {
        // TODO 7: devuelve un new Task<Integer>() { @Override protected Integer call() throws Exception { ... } }.
        // TODO 8: dentro de call(), lanza throw new IllegalStateException("fallo controlado").
        // TODO 9: NO captures la excepción dentro: deja que salga -> el Task pasará a estado FAILED.
        // TODO 10: al ejecutarlo, get() lanzará ExecutionException envolviendo esta IllegalStateException.
        return null;
    }

    public static void main(String[] args) throws Exception {
        Task<Integer> suma = tareaSuma(5);
        suma.run(); // ejecuta call() en este hilo (en una app real lo haría un hilo de fondo)
        System.out.println("Suma 1..5 = " + suma.get());
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Ejecutar y obtener el valor.
     * Ejecuta el task y devuelve el valor que calculó su call().
     */
    public static <V> V obtenerValor(Task<V> task) throws Exception {
        // GUÍA: teoría 5.1 (Task ES un Future: run() lo ejecuta, get() espera y da el resultado).
        // 1. task.run(). 2. return task.get().
        // OJO: get() es determinista (devuelve justo lo que retornó call()); getValue() (la property)
        //   se actualiza vía el hilo FX y puede ir un instante por detrás. Para tests, usa get().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerValor");
    }

    /**
     * Reto Extra 2: Capturar el fallo.
     * Ejecuta el task; si falla, devuelve el mensaje de la excepción que lanzó call(); si no, devuelve "".
     */
    public static String mensajeDelFallo(Task<?> task) {
        // GUÍA: teoría 5.2 (un Task fallido envuelve la excepción de call() en una ExecutionException).
        // 1. task.run(). 2. try { task.get(); return ""; }
        //    catch (java.util.concurrent.ExecutionException e) { return e.getCause().getMessage(); }
        //    catch (InterruptedException e) { Thread.currentThread().interrupt(); return ""; }.
        // PISTA: e.getCause() es la excepción ORIGINAL de call() (la IllegalStateException), no la envoltura.
        // OJO: el test ejecuta tareaQueFalla() y espera "fallo controlado".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mensajeDelFallo");
    }

    /**
     * Reto Extra 3: Task de valor inmediato.
     * Crea un {@link Task} cuyo call() devuelve directamente el valor dado (sin trabajo).
     */
    public static <V> Task<V> tareaDeValor(V valor) {
        // GUÍA: teoría 5.1 (el call() puede ser tan simple como un return).
        // 1. return new Task<V>() { @Override protected V call() { return valor; } };
        // OJO: el test lo ejecuta y comprueba get()==valor. 'valor' debe ser efectivamente final (lo es, es parámetro).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tareaDeValor");
    }

    /**
     * Reto Extra 4: Task que lanza un error con mensaje propio.
     * Crea un {@link Task} cuyo call() lanza una RuntimeException con el mensaje dado.
     */
    public static Task<Integer> tareaDeError(String mensaje) {
        // GUÍA: teoría 5.2 (modelar un fallo concreto del trabajo de fondo).
        // 1. return new Task<Integer>() { @Override protected Integer call() { throw new RuntimeException(mensaje); } };
        // OJO: el test ejecuta y comprueba que mensajeDelFallo(...) devuelve 'mensaje'.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tareaDeError");
    }

    /**
     * Reto Extra 5: Ejecutar en un hilo de fondo de verdad.
     * Lanza el task en un {@link Thread} aparte, espera a que termine y devuelve su valor.
     */
    public static <V> V ejecutarEnHilo(Task<V> task) throws Exception {
        // GUÍA: teoría 5.3 (en una app real NO llamas a run() en el hilo UI: lo arranca otro hilo).
        // 1. Thread hilo = new Thread(task) (un Task es Runnable). 2. hilo.setDaemon(true). 3. hilo.start().
        // 4. hilo.join() (espera a que acabe). 5. return task.get().
        // PISTA: por eso un Task es Runnable: para poder pasarlo a un Thread o a un ExecutorService (b27).
        // OJO: en producción NUNCA harías join() en el hilo UI (lo bloquearías); usas setOnSucceeded.
        //   Aquí join() es legítimo porque el test corre en un hilo de prueba, no en la UI.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarEnHilo");
    }

    /**
     * Reto Extra 6: Cancelar antes de empezar.
     * Cancela el task sin ejecutarlo y devuelve si quedó marcado como cancelado.
     */
    public static boolean cancelar(Task<?> task) {
        // GUÍA: teoría 5.4 (cancel() marca el Task; un call() largo debe consultar isCancelled() para parar).
        // 1. task.cancel(). 2. return task.isCancelled().
        // OJO: cancelar NO mata un hilo en marcha por arte de magia; el call() tiene que mirar
        //   isCancelled() periódicamente y salir. Aquí lo cancelamos antes de run() -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cancelar");
    }

    /**
     * Reto Extra 7: ¿Es monótona la secuencia de progreso?
     * Indica si la lista de valores de progreso es no decreciente (nunca retrocede).
     */
    public static boolean esMonotono(List<Double> progresos) {
        // GUÍA: teoría 5.5 (el progreso de un Task SIEMPRE debe avanzar o quedarse igual, nunca bajar).
        // 1. Recorre desde i=1: si progresos.get(i) < progresos.get(i-1) -> return false.
        // 2. Si llegas al final, return true.
        // PISTA: lista vacía o de 1 elemento -> true (no hay retroceso posible).
        // OJO: este helper lo usa el test del core para validar que tu updateProgress(i, n) crece bien.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMonotono");
    }

    /**
     * Reto Extra 8: Fracción de progreso.
     * Calcula la fracción 0..1 que representa 'hechos' sobre 'total' (lo que updateProgress normaliza).
     */
    public static double fraccionProgreso(long hechos, long total) {
        // GUÍA: teoría 5.5 (la progressProperty va de 0.0 a 1.0; updateProgress(hechos,total) hace hechos/total).
        // 1. Si total <= 0 -> devuelve -1.0 (progreso indeterminado, como la barra "rayada").
        // 2. Si no, devuelve (double) hechos / total.
        // PISTA: -1.0 es justo el valor que JavaFX usa para "progreso desconocido" (ProgressBar animada).
        // OJO: cuidado con la división entera: castea a double ANTES de dividir. El test usa (5,10)->0.5 y (0,0)->-1.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fraccionProgreso");
    }

    /**
     * Reto Extra 9: Task que reporta progreso de 0 a n.
     * Crea un {@link Task} que itera de 1 a n llamando a updateProgress(i, n) y devuelve n.
     * (El test ejecuta el Task en el hilo FX y comprueba que el progreso es monótono y acaba en 1.0.)
     */
    public static Task<Integer> tareaConProgreso(int n) {
        // GUÍA: teoría 5.5 (la barra de progreso de la UI se bindea a este Task; ver Ej284).
        // 1. return new Task<Integer>() { @Override protected Integer call() {
        //        for (int i = 1; i <= n; i++) updateProgress(i, n); return n; } };
        // PISTA: tras el último paso, updateProgress(n, n) deja el progreso en 1.0 (100%).
        // OJO: el test escucha progressProperty ANTES de ejecutar y verifica que los valores no retroceden
        //   y que el último es 1.0. Si reportas updateProgress(i, n+1) nunca llegaría a 1.0 (bug típico).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tareaConProgreso");
    }

    /**
     * Reto Extra 10: Task con reintentos.
     * Crea un {@link Task} que invoca al proveedor y, si lanza excepción, reintenta hasta 'intentos'
     * veces; devuelve el primer resultado exitoso o relanza el último fallo.
     */
    public static Task<Integer> tareaConReintento(Supplier<Integer> proveedor, int intentos) {
        // GUÍA: teoría 5.6 (un trabajo de red puede fallar por un hipo; reintentar es de sentido común).
        // 1. return new Task<Integer>() { @Override protected Integer call() throws Exception {
        //        RuntimeException ultimo = null;
        //        for (int i = 0; i < intentos; i++) {
        //            try { return proveedor.get(); }            // éxito -> salimos
        //            catch (RuntimeException e) { ultimo = e; }  // fallo -> guardamos y reintentamos
        //        }
        //        throw ultimo;                                   // agotados los intentos
        //    } };
        // PISTA: usa un contador mutable en el proveedor del test para que falle las 2 primeras veces y
        //   acierte a la 3ª; con intentos=3 el Task debe tener éxito.
        // OJO: si intentos<=0 o el proveedor nunca acierta, relanza el último fallo (get() -> ExecutionException).
        // CULTURA: este patrón "reintentar con tope" es el abuelo de los backoff/retry de los clientes HTTP
        //   modernos (Resilience4j, Spring Retry). En Ej286 lo aplicas a un GET real contra la API (b05).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tareaConReintento");
    }
}
