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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si entradas es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si tarea es null -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si alguna entrada es null -> IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: crea un CompletableFuture por entrada con supplyAsync(() -> tarea.apply(e)).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: recoge todos los futures en una lista.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: usa CompletableFuture.allOf(...) para esperar a que todos terminen.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: tras allOf, join() ya no bloquea de forma indefinida.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: mapea cada future a su resultado con join() y suma con reduce/sum.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si entradas esta vacia, el resultado es 0 (identidad de la suma).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la suma combinada.
    }

}
