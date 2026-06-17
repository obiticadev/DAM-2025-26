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

        /**
     * RETO EXTRA 01: Determina si el caso es valido.
     */
    public static boolean esCasoValido(Caso174 c) {
        // GUÍA: teoría 19.11 — un caso es válido si existe (no es null).
        // return c != null;
        // El test pasa new Caso174(5, 10) y espera true. Como Caso174 admite
        // cualquier int, la única invalidez posible es el null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCasoValido");
    }

    /**
     * RETO EXTRA 02: Crea un nuevo caso.
     */
    public static Caso174 crearCaso(int ent, int esp) {
        // GUÍA: teoría 19.11 — una línea: return new Caso174(ent, esp);
        // El test (crearCaso(1, 2)) solo hace assertNotNull. Es una fila de la
        // tabla entrada→esperado de un @ParameterizedTest.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearCaso");
    }

    /**
     * RETO EXTRA 03: Obtiene valor de entrada.
     */
    public static int obtenerEntrada(Caso174 c) {
        // GUÍA: teoría 19.11 — una línea: return c.entrada;
        // El test (Caso174(5, 10)) espera 5. Accesor del campo entrada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerEntrada");
    }

    /**
     * RETO EXTRA 04: Obtiene valor esperado.
     */
    public static int obtenerEsperado(Caso174 c) {
        // GUÍA: teoría 19.11 — una línea: return c.esperado;
        // El test (Caso174(5, 10)) espera 10. Simétrico a obtenerEntrada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerEsperado");
    }

    /**
     * RETO EXTRA 05: Valida si difieren.
     */
    public static boolean sonDiferentes(Caso174 c, int real) {
        // GUÍA: teoría 19.11 — ¿el resultado real difiere del esperado?
        // return c.esperado != real;
        // El test (Caso174(5, 10), real=9) espera true (10 != 9). Es la condición
        // que marca un caso como FALLIDO en casosFallidos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sonDiferentes");
    }

    /**
     * RETO EXTRA 06: Valida si coinciden.
     */
    public static boolean sonIguales(Caso174 c, int real) {
        // GUÍA: teoría 19.11 — una línea: return c.esperado == real;
        // El test (Caso174(5, 10), real=10) espera true. Negación exacta de
        // sonDiferentes (reto 5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sonIguales");
    }

    /**
     * RETO EXTRA 07: Obtiene total de casos.
     */
    public static int tamanioCasos(java.util.List<Caso174> casos) {
        // GUÍA: teoría 19.11 — una línea: return casos.size();
        // El test (lista de 2 casos) espera 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanioCasos");
    }

    /**
     * RETO EXTRA 08: Obtiene el primer caso.
     */
    public static Caso174 primerCaso(java.util.List<Caso174> casos) {
        // GUÍA: teoría 19.11 — una línea: return casos.get(0);
        // El test (lista con 1 caso) hace assertNotNull. OJO: get(0) lanzaría
        // IndexOutOfBounds con lista vacía; el test siempre pasa una no vacía.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para primerCaso");
    }

    /**
     * RETO EXTRA 09: Verifica si el operador falla.
     */
    public static boolean esCasoFalla(Caso174 c, java.util.function.IntUnaryOperator op) {
        // GUÍA: teoría 19.11 — aplica el operador y compara con el esperado.
        // return op.applyAsInt(c.entrada) != c.esperado;
        // El test (Caso174(2, 5), x -> x*2) espera true: 2*2=4, y 4 != 5 → falla.
        // Es la lógica de UN caso dentro de casosFallidos. PISTA: IntUnaryOperator
        // se invoca con applyAsInt(int).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCasoFalla");
    }

    /**
     * RETO EXTRA 10: Verifica si el operador tiene exito.
     */
    public static boolean esCasoExito(Caso174 c, java.util.function.IntUnaryOperator op) {
        // GUÍA: teoría 19.11 — éxito = el operador da el esperado.
        // return op.applyAsInt(c.entrada) == c.esperado;
        // El test (Caso174(2, 4), x -> x*2) espera true: 2*2=4 == 4. Negación de
        // esCasoFalla (reto 9); puedes incluso devolver !esCasoFalla(c, op).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCasoExito");
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
