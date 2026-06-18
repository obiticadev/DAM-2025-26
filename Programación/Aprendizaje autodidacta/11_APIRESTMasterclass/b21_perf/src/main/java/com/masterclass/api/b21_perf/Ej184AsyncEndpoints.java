package com.masterclass.api.b21_perf;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Ejercicio 184 · Endpoints asincronos.
 *
 * <p>Teoria: {@code teoria/21_Rendimiento_Resiliencia.md} (seccion 21.2).
 *
 * <p>{@code @Async} + {@link CompletableFuture} permiten lanzar trabajos en
 * paralelo y combinarlos sin bloquear el hilo de peticion. Aqui usamos
 * {@link CompletableFuture} puro del JDK: paralelizar tareas y agregar resultados.
 */
public final class Ej184AsyncEndpoints {

    private Ej184AsyncEndpoints() {
    }

    /**
     * Lanza una tarea por cada entrada y combina TODOS los resultados sumando.
     *
     * @param entradas lista de enteros a procesar (no null, sin nulls)
     * @param tarea    funcion (posiblemente costosa) aplicada a cada entrada (no null)
     * @return suma de los resultados de aplicar 'tarea' a cada entrada
     * @throws IllegalArgumentException si entradas o tarea son null o hay algun null
     */
    public static int sumarEnParalelo(List<Integer> entradas, Function<Integer, Integer> tarea) {
        // TODO 1: si entradas es null -> IllegalArgumentException.
        // TODO 2: si tarea es null -> IllegalArgumentException.
        // TODO 3: si alguna entrada es null -> IllegalArgumentException.
        // TODO 4: crea un CompletableFuture por entrada con supplyAsync(() -> tarea.apply(e)).
        // TODO 5: recoge todos los futures en una lista.
        // TODO 6: usa CompletableFuture.allOf(...) para esperar a que todos terminen.
        // TODO 7: tras allOf, join() ya no bloquea de forma indefinida.
        // TODO 8: mapea cada future a su resultado con join() y suma con reduce/sum.
        // TODO 9: si entradas esta vacia, el resultado es 0 (identidad de la suma).
        // TODO 10: devuelve la suma combinada.
        return 0;
    }

    /**
     * Encadena dos transformaciones asincronas: primero 'paso1', luego 'paso2'.
     *
     * @param valor valor inicial (no null)
     * @param paso1 primera transformacion asincrona (no null)
     * @param paso2 segunda transformacion encadenada (no null)
     * @return el resultado final tras aplicar paso1 y luego paso2
     * @throws IllegalArgumentException si algun argumento es null
     */
    public static String encadenar(String valor, Function<String, String> paso1,
                                    Function<String, String> paso2) {
        // TODO 1: si valor es null -> IllegalArgumentException.
        // TODO 2: si paso1 es null -> IllegalArgumentException.
        // TODO 3: si paso2 es null -> IllegalArgumentException.
        // TODO 4: arranca con CompletableFuture.supplyAsync(() -> valor).
        // TODO 5: aplica paso1 con thenApply (transformacion en el mismo flujo).
        // TODO 6: encadena paso2 con otro thenApply (composicion secuencial).
        // TODO 7: el orden importa: paso2 SIEMPRE recibe la salida de paso1.
        // TODO 8: no bloquees entre pasos; solo un join() al final.
        // TODO 9: obten el resultado final con join().
        // TODO 10: devuelve la cadena resultante.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(sumarEnParalelo(List.of(1, 2, 3), x -> x * x));
    }

        /**
     * RETO EXTRA 01: Valida si contiene null.
     */
    public static boolean esEntradaNula(java.util.List<Integer> list) {
        // GUÍA: una línea — return list.contains(null);
        // El test: Arrays.asList(1, null) → true. Es exactamente la comprobación
        // del TODO 3 de sumarEnParalelo: una entrada null reventaría tarea.apply.
        // PISTA alternativa: list.stream().anyMatch(java.util.Objects::isNull).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEntradaNula");
    }

    /**
     * RETO EXTRA 02: Crea un future asincrono.
     */
    public static java.util.concurrent.CompletableFuture<String> crearFuture(java.util.function.Supplier<String> sup) {
        // GUÍA: una línea — return CompletableFuture.supplyAsync(sup);
        // El test solo exige notNull. supplyAsync lanza el Supplier en el
        // ForkJoinPool.commonPool() y devuelve el future sin bloquear: es el
        // "empieza a calcular" de la tabla de 21.2 y la base de sumarEnParalelo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearFuture");
    }

    /**
     * RETO EXTRA 03: Obtiene resultado bloqueando.
     */
    public static String obtenerResultadoFuture(java.util.concurrent.CompletableFuture<String> fut) {
        // GUÍA: una línea — return fut.join();
        // El test: completedFuture("a") → "a". join() bloquea hasta tener el
        // valor (aquí ya está). Prefiere join() a get(): get() obliga a manejar
        // checked exceptions (InterruptedException/ExecutionException); join()
        // las envuelve en CompletionException (unchecked), como en 21.2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerResultadoFuture");
    }

    /**
     * RETO EXTRA 04: Crea un future completado.
     */
    public static java.util.concurrent.CompletableFuture<String> futureCompletadoCon(String v) {
        // GUÍA: una línea — return CompletableFuture.completedFuture(v);
        // El test exige .isDone() == true: completedFuture nace YA terminado,
        // sin lanzar nada en otro hilo. Útil para devolver un valor inmediato
        // por una interfaz que pide CompletableFuture (p. ej. un mock o un
        // atajo cuando ya tienes el dato cacheado).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para futureCompletadoCon");
    }

    /**
     * RETO EXTRA 05: Combina dos futures asincronos.
     */
    public static java.util.concurrent.CompletableFuture<String> combinarDos(java.util.concurrent.CompletableFuture<String> f1, java.util.concurrent.CompletableFuture<String> f2) {
        // GUÍA: una línea — return f1.thenCombine(f2, (a, b) -> a + b);
        // El test: completedFuture("a") + completedFuture("b") → join() == "ab".
        // thenCombine espera a que AMBOS terminen y aplica la BiFunction a los
        // dos resultados (21.2). Es el patrón "precio + stock → ficha": dos
        // llamadas en paralelo que se juntan. Devuelve OTRO future (no bloquea).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarDos");
    }

    /**
     * RETO EXTRA 06: Obtiene valor de inmediato o un default.
     */
    public static String ejecutarRapido(java.util.concurrent.CompletableFuture<String> fut) {
        // GUÍA: una línea — return fut.getNow("default");
        // OJO al test: pasa un new CompletableFuture<>() SIN completar nunca, y
        // espera "default". getNow NO bloquea: si el future aún no terminó,
        // devuelve el valor por defecto al instante. Con join() aquí te
        // colgarías para siempre. Es el patrón "responde ya con lo que haya".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarRapido");
    }

    /**
     * RETO EXTRA 07: Espera al mas rapido.
     */
    public static java.util.concurrent.CompletableFuture<Object> esperarCualquiera(java.util.concurrent.CompletableFuture<String> f1, java.util.concurrent.CompletableFuture<String> f2) {
        // GUÍA: una línea — return CompletableFuture.anyOf(f1, f2);
        // El test solo exige notNull. anyOf completa con el PRIMERO que termine
        // (21.2): el patrón "consulta a dos réplicas, quédate con la más rápida".
        // OJO al tipo: anyOf devuelve CompletableFuture<Object> (no <String>),
        // por eso la firma del método usa Object: no sabe cuál de los dos ganó.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esperarCualquiera");
    }

    /**
     * RETO EXTRA 08: Transforma asincronamente el resultado.
     */
    public static java.util.concurrent.CompletableFuture<Integer> mapearResultado(java.util.concurrent.CompletableFuture<Integer> fut) {
        // GUÍA: una línea — return fut.thenApply(x -> x * 2);
        // El test: completedFuture(2) → join() == 4. thenApply es el "map" de
        // los futures (21.2): transforma el resultado SIN bloquear y devuelve un
        // nuevo CompletableFuture<Integer>. Es lo mismo que hace encadenar() con
        // String, aquí con Integer.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearResultado");
    }

    /**
     * RETO EXTRA 09: Provee fallback si falla el future.
     */
    public static java.util.concurrent.CompletableFuture<String> excepcionControlada(java.util.concurrent.CompletableFuture<String> fut) {
        // GUÍA: una línea — return fut.exceptionally(ex -> "error");
        // El test: failedFuture(new RuntimeException()) → join() == "error".
        // exceptionally es el "plan B" de los futures (21.2): si el future falló,
        // recibe la excepción y produce un valor de recuperación; si tuvo éxito,
        // se ignora. Conecta con los reintentos/circuit breaker de 21.4.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para excepcionControlada");
    }

    /**
     * RETO EXTRA 10: Crea una lista limpia de futures.
     */
    public static java.util.List<java.util.concurrent.CompletableFuture<String>> crearListaFutures() {
        // GUÍA: una línea — return new java.util.ArrayList<>();
        // El test solo exige notNull. Es la lista mutable donde sumarEnParalelo
        // acumula los futures antes de pasarlos a allOf(...). Devuelve un
        // ArrayList vacío (no null): a una lista vacía puedes ir añadiéndole.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearListaFutures");
    }

}
