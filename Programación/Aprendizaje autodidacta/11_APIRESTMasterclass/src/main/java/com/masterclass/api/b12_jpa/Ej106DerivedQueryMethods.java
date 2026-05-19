package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 106 · "Query methods" (filtrar por campos) con JPQL parametrizado.
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 *
 * <p>Spring Data deriva el JPQL del nombre del método; aquí lo escribes tú para
 * entender qué genera por debajo.
 */
public final class Ej106DerivedQueryMethods {

    private final EntityManager em;

    public Ej106DerivedQueryMethods(EntityManager em) {
        this.em = em;
    }

    /**
     * Equivalente a {@code findByCategoria(String)}.
     *
     * @param categoria categoría exacta
     * @return artículos de esa categoría, ordenados por id
     */
    public List<Articulo106> findByCategoria(String categoria) {
        // TODO 1: JPQL "select a from Articulo106 a where a.categoria = :cat order by a.id".
        // TODO 2: usa createQuery con la clase tipada.
        // TODO 3: setParameter("cat", categoria) (NUNCA concatenes).
        // TODO 4: getResultList().
        return List.of();
    }

    /**
     * Equivalente a {@code findByPrecioGreaterThan(double)}.
     *
     * @param min precio mínimo (exclusivo)
     * @return artículos con precio &gt; min
     */
    public List<Articulo106> findByPrecioMayorQue(double min) {
        // TODO 5: JPQL con "where a.precio > :min".
        // TODO 6: setParameter("min", min).
        // TODO 7: getResultList().
        return List.of();
    }

    /**
     * Equivalente a {@code countByCategoria(String)}.
     *
     * @param categoria categoría
     * @return número de artículos
     */
    public long countByCategoria(String categoria) {
        // TODO 8: JPQL "select count(a) from Articulo106 a where a.categoria = :cat".
        // TODO 9: getSingleResult() devuelve Long.
        // TODO 10: devuelve ese long.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: JPQL "select a from Articulo106 a where a.categoria = :cat order by a.id".
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa createQuery con la clase tipada.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: setParameter("cat", categoria) (NUNCA concatenes).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: getResultList().
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: JPQL con "where a.precio > :min".
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: setParameter("min", min).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: getResultList().
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: JPQL "select count(a) from Articulo106 a where a.categoria = :cat".
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: getSingleResult() devuelve Long.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve ese long.
    }

}

@jakarta.persistence.Entity
class Articulo106 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private double precio;

    protected Articulo106() {
    }

    public Articulo106(String categoria, double precio) {
        this.categoria = categoria;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }
}
