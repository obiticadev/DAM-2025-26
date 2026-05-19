package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 141 · Agregaciones y GROUP BY.
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 */
public final class Ej141AggregationsGroupBy {

    private final EntityManager em;

    public Ej141AggregationsGroupBy(EntityManager em) {
        this.em = em;
    }

    /**
     * Cuenta ventas agrupadas por categoría.
     *
     * @return mapa categoría → nº de ventas, ordenado por categoría
     */
    public Map<String, Long> conteoPorCategoria() {
        Map<String, Long> out = new LinkedHashMap<>();
        // TODO 1: JPQL "select v.categoria, count(v) from Venta141 v
        //         group by v.categoria order by v.categoria".
        // TODO 2: el resultado es List<Object[]> (cada fila: [categoria, count]).
        // TODO 3: createQuery(jpql, Object[].class).getResultList().
        // TODO 4: recorre cada Object[].
        // TODO 5: fila[0] es la categoría (String).
        // TODO 6: fila[1] es el count (Long).
        // TODO 7: mete cada par en 'out' (LinkedHashMap conserva el orden).
        // TODO 8: GROUP BY agrupa filas; count() cuenta por grupo.
        // TODO 9: si no hay ventas, devuelve mapa vacío.
        // TODO 10: devuelve el mapa.
        return out;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: JPQL "select v.categoria, count(v) from Venta141 v
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: el resultado es List<Object[]> (cada fila: [categoria, count]).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: createQuery(jpql, Object[].class).getResultList().
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: recorre cada Object[].
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: fila[0] es la categoría (String).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: fila[1] es el count (Long).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: mete cada par en 'out' (LinkedHashMap conserva el orden).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: GROUP BY agrupa filas; count() cuenta por grupo.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si no hay ventas, devuelve mapa vacío.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el mapa.
    }

}

@Entity
class Venta141 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;

    public Venta141() {
    }

    public Venta141(String categoria) {
        this.categoria = categoria;
    }
}
