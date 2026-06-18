package com.masterclass.api.b27_concur;

/**
 * Ejercicio 220 · {@code Callable} y {@code Future}: tareas que devuelven valor.
 *
 * <p>Un {@code Runnable} no devuelve nada; un {@code Callable<V>} sí, y {@code Future<V>}
 * es el "pagaré" de ese resultado: permite {@code get()} (bloqueante, con o sin timeout),
 * {@code cancel()}, {@code isDone()} e {@code isCancelled()}.
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.6).
 */
public final class Ej220CallableFuture {

    private Ej220CallableFuture() {
    }

    /**
     * Calcula la suma 1..n en un Callable y recoge el resultado con Future.get().
     *
     * @param n límite incluido
     * @return suma 1..n
     */
    public static long resultadoDeCallable(long n) {
        // TODO 1: crea un ExecutorService de un hilo.
        // TODO 2: define un Callable<Long> que sume 1..n y lo devuelva.
        // TODO 3: somételo con submit y obtén un Future<Long>.
        // TODO 4: recoge el valor con future.get() (maneja InterruptedException/ExecutionException).
        // TODO 5: haz shutdown() y devuelve el resultado.
        return -1;
    }

    /**
     * Somete nTareas Callables que devuelven 1 y suma todos los resultados.
     *
     * @param nTareas número de tareas
     * @return suma de los resultados (== nTareas)
     */
    public static int recogerVariosFutures(int nTareas) {
        // TODO 6: crea un pool fijo (p.ej. 4 hilos) y una lista de Future<Integer>.
        // TODO 7: somete nTareas Callables que devuelvan 1 y guarda cada Future.
        // TODO 8: recorre los Future acumulando future.get().
        // TODO 9: haz shutdown().
        // TODO 10: devuelve la suma (== nTareas).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("resultadoDeCallable(100) = " + resultadoDeCallable(100));
        System.out.println("recogerVariosFutures(50) = " + recogerVariosFutures(50));
    }

    /**
     * Reto Extra 1: {@code isDone()} es true tras un get() con éxito.
     * @return true si el Future está "done" después de recoger su valor
     */
    public static boolean isDoneTrasGet(int valor) {
        // GUÍA: Future<Integer> f = ex.submit(() -> valor); f.get(); return f.isDone();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para isDoneTrasGet");
    }

    /**
     * Reto Extra 2: Cancelar una tarea que aún no ha empezado.
     * @return true si se puede cancelar una tarea pendiente (isCancelled() == true)
     */
    public static boolean cancelTareaPendiente() {
        // GUÍA: ocupa el único hilo con una tarea bloqueada en un latch, encola otra y cancélala:
        //   ExecutorService ex = Executors.newSingleThreadExecutor();
        //   CountDownLatch ocupado = new CountDownLatch(1);
        //   ex.submit(() -> { try { ocupado.await(); } catch (InterruptedException e) {} });
        //   Future<?> pendiente = ex.submit(() -> {});
        //   boolean cancelada = pendiente.cancel(false);   // aún no había arrancado
        //   ocupado.countDown(); ex.shutdown();
        //   return cancelada && pendiente.isCancelled();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cancelTareaPendiente");
    }

    /**
     * Reto Extra 3: {@code get(timeout)} devuelve a tiempo.
     * @return el valor, recogido con get(1, SECONDS)
     */
    public static int getConTimeoutDevuelve(int valor) {
        // GUÍA: Future<Integer> f = ex.submit(() -> valor);
        //   return f.get(1, TimeUnit.SECONDS);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getConTimeoutDevuelve");
    }

    /**
     * Reto Extra 4: {@code get(timeout)} lanza {@code TimeoutException} si la tarea tarda.
     * @return true si get con timeout corto lanza TimeoutException sobre una tarea bloqueada
     */
    public static boolean getConTimeoutLanzaTimeoutException() {
        // GUÍA: Future<?> f = ex.submit(() -> { latch.await(); return 1; });  // se queda bloqueada
        //   try { f.get(50, TimeUnit.MILLISECONDS); return false; }
        //   catch (TimeoutException e) { return true; }
        //   finally { f.cancel(true); ex.shutdownNow(); }   // limpieza
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getConTimeoutLanzaTimeoutException");
    }

    /**
     * Reto Extra 5: {@code FutureTask} envuelve un Callable y es a la vez Runnable.
     * @return el valor calculado por el FutureTask, ejecutado en un Thread normal
     */
    public static int futureTaskEjecutable(int valor) {
        // GUÍA: FutureTask<Integer> ft = new FutureTask<>(() -> valor);
        //   new Thread(ft).start();   // FutureTask ES un Runnable
        //   return ft.get();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para futureTaskEjecutable");
    }

    /**
     * Reto Extra 6: {@code ExecutorCompletionService} entrega tareas según terminan.
     * @return el valor (todas las tareas devuelven 'valor', así que take().get() == valor)
     */
    public static int completionServiceTomaUno(int nTareas, int valor) {
        // GUÍA: ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<>(ex);
        //   for (int i=0;i<nTareas;i++) cs.submit(() -> valor);
        //   int r = cs.take().get();   // toma el PRIMERO que termine
        //   ex.shutdownNow(); return r;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para completionServiceTomaUno");
    }

    /**
     * Reto Extra 7: Cancelar una tarea ya terminada devuelve false.
     * @return true si cancel() sobre un Future ya completado devuelve false
     */
    public static boolean cancelDevuelveFalseSiYaTermino(int valor) {
        // GUÍA: Future<Integer> f = ex.submit(() -> valor); f.get();   // ya terminó
        //   return f.cancel(true) == false;   // no se puede cancelar lo ya hecho
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cancelDevuelveFalseSiYaTermino");
    }

    /**
     * Reto Extra 8: {@code isCancelled()} es false si la tarea completó normalmente.
     * @return true si una tarea que terminó bien NO está cancelada
     */
    public static boolean isCancelledFalseSiCompleta(int valor) {
        // GUÍA: Future<Integer> f = ex.submit(() -> valor); f.get(); return !f.isCancelled();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para isCancelledFalseSiCompleta");
    }

    /**
     * Reto Extra 9: {@code get()} es idempotente.
     * @return el valor; llamar a get() dos veces devuelve siempre el mismo resultado
     */
    public static int getEsIdempotente(int valor) {
        // GUÍA: Future<Integer> f = ex.submit(() -> valor);
        //   int a = f.get(); int b = f.get();   // el resultado se cachea
        //   return (a == b) ? a : -1;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getEsIdempotente");
    }

    /**
     * Reto Extra 10: {@code get()} bloquea hasta que el resultado está listo.
     * @return el valor producido tras una señal controlada por latch
     */
    public static long getBloqueaHastaResultado(long valor) {
        // GUÍA: CountDownLatch arranca = new CountDownLatch(1);
        //   Future<Long> f = ex.submit(() -> { arranca.await(); return valor; });
        //   arranca.countDown();        // damos luz verde
        //   long r = f.get();           // get espera a que termine
        //   ex.shutdown(); return r;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getBloqueaHastaResultado");
    }
}
