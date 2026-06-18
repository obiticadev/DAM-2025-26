package com.masterclass.api.b27_concur;

/**
 * Ejercicio 225 · {@code CompletableFuture}: composición asíncrona.
 *
 * <p>El {@code Future} clásico solo sabe bloquear con {@code get()}. {@code CompletableFuture}
 * permite ENCADENAR pasos sin bloquear ({@code thenApply}, {@code thenCompose},
 * {@code thenCombine}), combinar varios ({@code allOf}/{@code anyOf}) y manejar errores
 * ({@code exceptionally}, {@code handle}). Es la base del estilo reactivo y de {@code @Async}.
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.11).
 */
public final class Ej225CompletableFutureAdvanced {

    private Ej225CompletableFutureAdvanced() {
    }

    /**
     * Encadena una transformación con {@code thenApply} sobre un {@code supplyAsync}.
     *
     * @param x valor inicial
     * @return x*2 calculado de forma asíncrona
     */
    public static int thenApplyEncadena(int x) {
        // TODO 1: crea un CompletableFuture con CompletableFuture.supplyAsync(() -> x).
        // TODO 2: encadena .thenApply(v -> v * 2).
        // TODO 3: recoge el resultado con .join() (no lanza checked exceptions, a diferencia de get()).
        // TODO 4: (piensa) thenApply NO bloquea: define el paso siguiente, que correrá cuando el anterior acabe.
        // TODO 5: devuelve el resultado.
        return -1;
    }

    /**
     * Lanza n tareas asíncronas que devuelven 1 y combina sus resultados con {@code allOf}.
     *
     * @param n número de tareas
     * @return suma de los resultados (== n)
     */
    public static int allOfSuma(int n) {
        // TODO 6: crea n CompletableFuture<Integer> con supplyAsync(() -> 1) y guárdalos en un array/lista.
        // TODO 7: combina con CompletableFuture.allOf(arrayDeFutures) y haz .join() para esperar a todos.
        // TODO 8: tras allOf, cada futuro ya está completo: recoge cada uno con join().
        // TODO 9: acumula la suma de los resultados.
        // TODO 10: devuelve la suma (== n).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("thenApplyEncadena(21) = " + thenApplyEncadena(21));
        System.out.println("allOfSuma(10) = " + allOfSuma(10));
    }

    /**
     * Reto Extra 1: {@code supplyAsync} + {@code join}.
     * @return el valor producido asíncronamente
     */
    public static int supplyAsyncJoin(int v) {
        // GUÍA: return CompletableFuture.supplyAsync(() -> v).join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para supplyAsyncJoin");
    }

    /**
     * Reto Extra 2: {@code thenCompose} (encadena otro futuro, no un valor).
     * @return v+1 (el segundo futuro depende del resultado del primero)
     */
    public static int thenComposeEncadena(int v) {
        // GUÍA: thenCompose APLANA futuros (evita CompletableFuture<CompletableFuture<...>>):
        //   return CompletableFuture.supplyAsync(() -> v)
        //          .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + 1))
        //          .join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para thenComposeEncadena");
    }

    /**
     * Reto Extra 3: {@code thenCombine} (une dos futuros independientes).
     * @return a+b combinando dos cálculos paralelos
     */
    public static int thenCombineDos(int a, int b) {
        // GUÍA: var fa = CompletableFuture.supplyAsync(() -> a);
        //   var fb = CompletableFuture.supplyAsync(() -> b);
        //   return fa.thenCombine(fb, (x, y) -> x + y).join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para thenCombineDos");
    }

    /**
     * Reto Extra 4: {@code exceptionally} recupera de un fallo.
     * @return 'fallback' cuando la etapa asíncrona lanza una excepción
     */
    public static int exceptionallyRecupera(int fallback) {
        // GUÍA: return CompletableFuture.<Integer>supplyAsync(() -> { throw new RuntimeException("boom"); })
        //          .exceptionally(ex -> fallback)
        //          .join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exceptionallyRecupera");
    }

    /**
     * Reto Extra 5: {@code handle} procesa resultado Y error.
     * @return 'fallback' usando handle((r, e) -> e != null ? fallback : r) sobre una etapa que falla
     */
    public static int handleProcesaResultadoYError(int fallback) {
        // GUÍA: handle recibe (resultado, excepción); uno de los dos será null.
        //   return CompletableFuture.<Integer>supplyAsync(() -> { throw new RuntimeException(); })
        //          .handle((r, e) -> e != null ? fallback : r)
        //          .join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para handleProcesaResultadoYError");
    }

    /**
     * Reto Extra 6: {@code anyOf} devuelve el primero que termine.
     * @return el valor (todas las etapas devuelven 'v', así que anyOf devuelve 'v')
     */
    public static int anyOfPrimero(int v) {
        // GUÍA: CompletableFuture<Object> r = CompletableFuture.anyOf(
        //          CompletableFuture.supplyAsync(() -> v), CompletableFuture.supplyAsync(() -> v));
        //   return (Integer) r.join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anyOfPrimero");
    }

    /**
     * Reto Extra 7: {@code thenAccept} consume el resultado (efecto lateral).
     * @return el valor capturado por el consumidor
     */
    public static int thenAcceptEfecto(int v) {
        // GUÍA: int[] capt = {0};
        //   CompletableFuture.supplyAsync(() -> v).thenAccept(x -> capt[0] = x).join();
        //   return capt[0];
        // thenAccept no devuelve valor (Consumer), solo ejecuta un efecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para thenAcceptEfecto");
    }

    /**
     * Reto Extra 8: Ejecutar en un {@code Executor} concreto.
     * @return true si supplyAsync corrió en el pool proporcionado (no en el ForkJoinPool común)
     */
    public static boolean corrioEnExecutorDado() {
        // GUÍA: crea un pool con ThreadFactory que nombre los hilos "cf-pool-...":
        //   ExecutorService pool = Executors.newSingleThreadExecutor(r -> { Thread t=new Thread(r,"cf-pool-1"); return t; });
        //   String nombre = CompletableFuture.supplyAsync(() -> Thread.currentThread().getName(), pool).join();
        //   pool.shutdown();
        //   return nombre.startsWith("cf-pool");
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para corrioEnExecutorDado");
    }

    /**
     * Reto Extra 9: Completar manualmente un {@code CompletableFuture}.
     * @return el valor con el que se completó a mano
     */
    public static int completarManualmente(int v) {
        // GUÍA: CompletableFuture<Integer> cf = new CompletableFuture<>();
        //   cf.complete(v);     // lo completamos nosotros (útil para adaptar callbacks)
        //   return cf.join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para completarManualmente");
    }

    /**
     * Reto Extra 10: {@code completeOnTimeout} aporta un valor por defecto.
     * @return 'fallback' cuando la etapa no completa a tiempo
     */
    public static int completeOnTimeoutFallback(int fallback) {
        // GUÍA: una etapa que nunca completa, con un valor por defecto si tarda demasiado:
        //   CompletableFuture<Integer> cf = new CompletableFuture<>();   // nadie la completa
        //   return cf.completeOnTimeout(fallback, 50, TimeUnit.MILLISECONDS).join();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para completeOnTimeoutFallback");
    }
}
