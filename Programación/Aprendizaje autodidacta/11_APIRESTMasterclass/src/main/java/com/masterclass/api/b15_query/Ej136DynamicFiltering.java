package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 136 · Filtrado dinámico (WHERE construido según parámetros).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 */
public final class Ej136DynamicFiltering {

    private final EntityManager em;

    public Ej136DynamicFiltering(EntityManager em) {
        this.em = em;
    }

    /**
     * Busca coches filtrando solo por los parámetros NO nulos.
     *
     * @param marca    filtro opcional por marca exacta (null = no filtrar)
     * @param precioMax filtro opcional precio máximo (null = no filtrar)
     * @return coches que cumplen los filtros activos
     */
    public List<Coche136> buscar(String marca, Double precioMax) {
        List<String> condiciones = new ArrayList<>();
        // TODO 1: empieza con un JPQL base "select c from Coche136 c".
        // TODO 2: si 'marca' != null, añade la condición "c.marca = :marca" a 'condiciones'.
        // TODO 3: si 'precioMax' != null, añade "c.precio <= :precioMax".
        // TODO 4: si hay condiciones, únelas con " and " y antepón " where ".
        // TODO 5: añade " order by c.id" al final.
        // TODO 6: crea la query tipada con el JPQL resultante.
        // TODO 7: setParameter SOLO de los parámetros que realmente se usaron.
        // TODO 8: usa parámetros con nombre (NUNCA concatenes valores).
        // TODO 9: getResultList().
        // TODO 10: si no hay filtros, devuelve todos (where ausente).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: empieza con un JPQL base "select c from Coche136 c".
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si 'marca' != null, añade la condición "c.marca = :marca" a 'condiciones'.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si 'precioMax' != null, añade "c.precio <= :precioMax".
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si hay condiciones, únelas con " and " y antepón " where ".
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: añade " order by c.id" al final.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: crea la query tipada con el JPQL resultante.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: setParameter SOLO de los parámetros que realmente se usaron.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: usa parámetros con nombre (NUNCA concatenes valores).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: getResultList().
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: si no hay filtros, devuelve todos (where ausente).
    }

}

@Entity
class Coche136 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private double precio;

    public Coche136() {
    }

    public Coche136(String marca, double precio) {
        this.marca = marca;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }
}
