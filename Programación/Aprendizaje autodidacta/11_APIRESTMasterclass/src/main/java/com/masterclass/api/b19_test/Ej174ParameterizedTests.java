package com.masterclass.api.b19_test;

import java.util.List;

/**
 * Ejercicio 174 · Tests parametrizados (motor de casos entrada→esperado).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.11).
 *
 * <p>{@code @ParameterizedTest} ejecuta el mismo test con N juegos de
 * datos. Modelamos el motor: dada una lista de {@link Caso174}
 * (entrada + esperado) y una función bajo prueba, devuelve cuáles
 * fallan. El test espejo valida este runner.
 */
public final class Ej174ParameterizedTests {

    private Ej174ParameterizedTests() {
    }

    /**
     * Ejecuta una tabla de casos contra una función y devuelve los fallidos.
     *
     * @param casos  lista de casos entrada→esperado (no null)
     * @param funcion función bajo prueba (no null), recibe la entrada
     * @return índices (0-based) de los casos cuyo resultado != esperado
     * @throws IllegalArgumentException si casos o funcion son null
     */
    public static List<Integer> casosFallidos(List<Caso174> casos, java.util.function.IntUnaryOperator funcion) {
        // TODO 1: si casos es null -> IllegalArgumentException.
        // TODO 2: si funcion es null -> IllegalArgumentException.
        // TODO 3: recorre los casos con su índice (como hace @ParameterizedTest).
        // TODO 4: para cada caso aplica funcion.applyAsInt(caso.entrada).
        // TODO 5: compara el resultado con caso.esperado.
        // TODO 6: si difieren, registra el índice del caso fallido.
        // TODO 7: un caso no debe abortar los demás (cada caso es independiente).
        // TODO 8: si todos pasan -> devuelve lista vacía (no null).
        // TODO 9: no mutes la lista de casos de entrada.
        // TODO 10: devuelve la lista de índices fallidos en orden ascendente.
        return List.of();
    }

    public static void main(String[] args) {
        List<Caso174> casos = List.of(new Caso174(2, 4), new Caso174(3, 9));
        System.out.println(casosFallidos(casos, x -> x * x));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si casos es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si funcion es null -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: recorre los casos con su índice (como hace @ParameterizedTest).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: para cada caso aplica funcion.applyAsInt(caso.entrada).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: compara el resultado con caso.esperado.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si difieren, registra el índice del caso fallido.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: un caso no debe abortar los demás (cada caso es independiente).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: si todos pasan -> devuelve lista vacía (no null).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: no mutes la lista de casos de entrada.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la lista de índices fallidos en orden ascendente.
    }

}

/** Un caso parametrizado: entrada y resultado esperado. */
final class Caso174 {
    final int entrada;
    final int esperado;

    Caso174(int entrada, int esperado) {
        this.entrada = entrada;
        this.esperado = esperado;
    }
}
