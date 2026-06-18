package com.masterclass.api.b27_concur;

/**
 * Ejercicio 219 · {@code ExecutorService}: pools de hilos en vez de crearlos a mano.
 *
 * <p>Crear un {@code Thread} por tarea no escala. Un pool reutiliza un número fijo de hilos
 * para ejecutar muchas tareas. Aquí ves {@code submit}, {@code shutdown},
 * {@code awaitTermination} y por qué un pool es lo normal en producción.
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.5).
 */
public final class Ej219ExecutorService {

    private Ej219ExecutorService() {
    }

    /**
     * Lanza nTareas en un pool fijo; cada tarea incrementa un contador atómico.
     *
     * @param nTareas número de tareas
     * @param nHilos  tamaño del pool
     * @return tareas ejecutadas (== nTareas)
     */
    public static int contarTareasEjecutadas(int nTareas, int nHilos) {
        // TODO 1: crea un ExecutorService con Executors.newFixedThreadPool(nHilos).
        // TODO 2: crea un AtomicInteger c.
        // TODO 3: haz nTareas veces pool.submit(c::incrementAndGet) (como Runnable).
        // TODO 4: llama a pool.shutdown() (no acepta más tareas; las pendientes terminan).
        // TODO 5: espera con pool.awaitTermination(...) y devuelve c.get() (maneja InterruptedException).
        return -1;
    }

    /**
     * Suma el rango desde..hasta repartiéndolo en nHilos tareas que devuelven parciales.
     *
     * @param desde  inicio incluido
     * @param hasta  fin incluido
     * @param nHilos número de tareas/hilos
     * @return suma total del rango
     */
    public static long sumaConFutures(long desde, long hasta, int nHilos) {
        // TODO 6: crea un pool fijo de nHilos y una lista de Future<Long>.
        // TODO 7: reparte [desde,hasta] en nHilos tramos; por cada tramo haz pool.submit(Callable<Long>)
        //         que sume su tramo y lo devuelva; guarda el Future.
        // TODO 8: recorre los Future y acumula future.get() (maneja InterruptedException/ExecutionException).
        // TODO 9: haz pool.shutdown().
        // TODO 10: devuelve la suma acumulada.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("contarTareasEjecutadas(1000, 4) = " + contarTareasEjecutadas(1000, 4));
        System.out.println("sumaConFutures(1, 100, 4) = " + sumaConFutures(1, 100, 4));
    }

    /**
     * Reto Extra 1: {@code submit(Callable)} devuelve un Future con el resultado.
     * @return el valor producido por el Callable
     */
    public static int submitCallableDevuelve(int valor) {
        // GUÍA: ExecutorService ex = Executors.newSingleThreadExecutor();
        //   Future<Integer> f = ex.submit(() -> valor);
        //   int r = f.get();  ex.shutdown();  return r;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para submitCallableDevuelve");
    }

    /**
     * Reto Extra 2: {@code invokeAll} ejecuta una lista de Callables y devuelve sus Futures.
     * @return suma de los resultados (cada tarea i devuelve i+1, para i en 0..nTareas-1)
     */
    public static long invokeAllSuma(int nTareas) {
        // GUÍA: List<Callable<Integer>> tareas = ...; cada una () -> (i+1).
        //   List<Future<Integer>> fs = ex.invokeAll(tareas);  // bloquea hasta que TODAS acaban
        //   suma f.get(). Total = nTareas*(nTareas+1)/2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para invokeAllSuma");
    }

    /**
     * Reto Extra 3: {@code invokeAny} devuelve el resultado de una tarea (cualquiera que acabe).
     * @return el valor (todas las tareas devuelven el mismo 'valor' para que sea determinista)
     */
    public static int invokeAnyDevuelveUno(int valor) {
        // GUÍA: si todas las tareas devuelven 'valor', invokeAny devuelve 'valor' sea cual sea la
        // ganadora: List<Callable<Integer>> con varias () -> valor; ex.invokeAny(tareas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para invokeAnyDevuelveUno");
    }

    /**
     * Reto Extra 4: {@code awaitTermination} tras {@code shutdown}.
     * @return true si el pool termina ordenadamente dentro del plazo
     */
    public static boolean awaitTerminationTrasShutdown(int nTareas) {
        // GUÍA: somete nTareas cortas, shutdown(), y return ex.awaitTermination(5, TimeUnit.SECONDS).
        // awaitTermination devuelve true si terminó antes del timeout.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para awaitTerminationTrasShutdown");
    }

    /**
     * Reto Extra 5: {@code newSingleThreadExecutor} serializa las tareas.
     * @return tareas ejecutadas (== nTareas), todas en un único hilo
     */
    public static int singleThreadSerializa(int nTareas) {
        // GUÍA: un solo hilo procesa las tareas EN ORDEN, una tras otra. El contador final sigue
        // siendo nTareas (no hay paralelismo, pero tampoco hace falta sincronizar el orden).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para singleThreadSerializa");
    }

    /**
     * Reto Extra 6: {@code newCachedThreadPool}.
     * @return tareas ejecutadas (== nTareas)
     */
    public static int cachedPoolCuenta(int nTareas) {
        // GUÍA: el cached pool crea hilos bajo demanda y los reaprovecha; útil para muchas tareas
        // cortas. Mismo patrón de conteo con AtomicInteger.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cachedPoolCuenta");
    }

    /**
     * Reto Extra 7: Enviar tras {@code shutdown} lanza {@code RejectedExecutionException}.
     * @return true si submit tras shutdown es rechazado
     */
    public static boolean rejectedExecutionTrasShutdown() {
        // GUÍA: ex.shutdown();
        //   try { ex.submit(() -> {}); return false; }
        //   catch (RejectedExecutionException e) { return true; }
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rejectedExecutionTrasShutdown");
    }

    /**
     * Reto Extra 8: {@code ScheduledExecutorService} ejecuta una vez con retardo.
     * @return el valor producido por la tarea programada
     */
    public static long scheduledUnaVez(long valor) {
        // GUÍA: ScheduledExecutorService se = Executors.newSingleThreadScheduledExecutor();
        //   ScheduledFuture<Long> f = se.schedule(() -> valor, 50, TimeUnit.MILLISECONDS);
        //   long r = f.get(); se.shutdown(); return r;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para scheduledUnaVez");
    }

    /**
     * Reto Extra 9: El pool REUTILIZA hilos.
     * @return número de hilos DISTINTOS que ejecutaron las tareas (debe ser &lt;= nHilos)
     */
    public static int reutilizaHilosDelPool(int nTareas, int nHilos) {
        // GUÍA: recoge los nombres de hilo en un Set concurrente:
        //   Set<String> usados = ConcurrentHashMap.newKeySet();
        //   cada tarea: usados.add(Thread.currentThread().getName());
        //   tras terminar, return usados.size();  // <= nHilos: se reutilizan
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reutilizaHilosDelPool");
    }

    /**
     * Reto Extra 10: Una excepción en la tarea llega como {@code ExecutionException} al get().
     * @return true si future.get() envuelve la excepción de la tarea en ExecutionException
     */
    public static boolean futureGetPropagaExecutionException() {
        // GUÍA: Future<?> f = ex.submit(() -> { throw new IllegalStateException("boom"); });
        //   try { f.get(); return false; }
        //   catch (ExecutionException e) { return e.getCause() instanceof IllegalStateException; }
        //   catch (InterruptedException e) { Thread.currentThread().interrupt(); return false; }
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para futureGetPropagaExecutionException");
    }
}
