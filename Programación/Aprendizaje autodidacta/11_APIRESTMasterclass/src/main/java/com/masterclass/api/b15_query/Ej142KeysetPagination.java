package com.masterclass.api.b15_query;

import java.util.List;

/**
 * Ejercicio 142 · Keyset pagination (cursor por id).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.4).
 *
 * <p>En vez de OFFSET (lento con millones de filas), avanzas por "el último id visto".
 */
public final class Ej142KeysetPagination {

    private Ej142KeysetPagination() {
    }

    /**
     * Devuelve la siguiente página a partir del último id visto.
     *
     * @param idsOrdenados lista de ids YA ordenada ascendentemente (simula la tabla)
     * @param ultimoIdVisto id tras el cual continuar (null o 0 = desde el principio)
     * @param tamano       tamaño de página (&gt; 0)
     * @return sublista con hasta 'tamano' ids cuyo valor &gt; ultimoIdVisto
     * @throws IllegalArgumentException si tamano &lt;= 0 o la lista es null
     */
    public static List<Long> siguientePagina(List<Long> idsOrdenados, Long ultimoIdVisto, int tamano) {
        // TODO 1: si idsOrdenados es null -> IllegalArgumentException.
        // TODO 2: si tamano <= 0 -> IllegalArgumentException.
        // TODO 3: normaliza el cursor: si ultimoIdVisto es null, trátalo como Long.MIN_VALUE
        //         (o 0 si los ids son positivos) para empezar desde el principio.
        // TODO 4: filtra los ids estrictamente mayores que 'ultimoIdVisto'.
        // TODO 5: la lista ya viene ordenada: NO la reordenes.
        // TODO 6: limita el resultado a 'tamano' elementos (limit).
        // TODO 7: recoge a List.
        // TODO 8: keyset es estable: insertar filas nuevas no descoloca páginas previas.
        // TODO 9: si no hay más allá del cursor, devuelve lista vacía.
        // TODO 10: devuelve la sublista (la "siguiente página").
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(siguientePagina(List.of(1L, 2L, 3L, 4L, 5L), 2L, 2));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si idsOrdenados es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si tamano <= 0 -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: normaliza el cursor: si ultimoIdVisto es null, trátalo como Long.MIN_VALUE
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: filtra los ids estrictamente mayores que 'ultimoIdVisto'.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: la lista ya viene ordenada: NO la reordenes.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: limita el resultado a 'tamano' elementos (limit).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: recoge a List.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: keyset es estable: insertar filas nuevas no descoloca páginas previas.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si no hay más allá del cursor, devuelve lista vacía.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la sublista (la "siguiente página").
    }

}
