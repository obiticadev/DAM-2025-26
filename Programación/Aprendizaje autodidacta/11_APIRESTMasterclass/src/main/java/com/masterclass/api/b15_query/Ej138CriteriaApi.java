package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

/**
 * Ejercicio 138 · Criteria API tipada (agregado).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.3).
 */
public final class Ej138CriteriaApi {

    private final EntityManager em;

    public Ej138CriteriaApi(EntityManager em) {
        this.em = em;
    }

    /**
     * Suma el importe de las ventas de una región usando Criteria.
     *
     * @param region región a filtrar
     * @return suma de importes (0.0 si no hay)
     */
    public double totalPorRegion(String region) {
        // TODO 1: CriteriaBuilder cb = em.getCriteriaBuilder().
        // TODO 2: CriteriaQuery<Double> cq = cb.createQuery(Double.class).
        // TODO 3: Root<Venta138> root = cq.from(Venta138.class).
        // TODO 4: cq.select(cb.sum(root.get("importe"))) (agregado tipado).
        // TODO 5: cq.where(cb.equal(root.get("region"), region)).
        // TODO 6: ejecuta em.createQuery(cq).getSingleResult().
        // TODO 7: el resultado puede ser null si no hay filas.
        // TODO 8: si es null, devuelve 0.0.
        // TODO 9: si no, devuelve el double.
        // TODO 10: Criteria es type-safe: errores de campo se ven antes (vs JPQL string).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: CriteriaBuilder cb = em.getCriteriaBuilder().
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: CriteriaQuery<Double> cq = cb.createQuery(Double.class).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: Root<Venta138> root = cq.from(Venta138.class).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: cq.select(cb.sum(root.get("importe"))) (agregado tipado).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: cq.where(cb.equal(root.get("region"), region)).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: ejecuta em.createQuery(cq).getSingleResult().
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: el resultado puede ser null si no hay filas.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: si es null, devuelve 0.0.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si no, devuelve el double.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: Criteria es type-safe: errores de campo se ven antes (vs JPQL string).
    }

}

@Entity
class Venta138 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;
    private double importe;

    public Venta138() {
    }

    public Venta138(String region, double importe) {
        this.region = region;
        this.importe = importe;
    }
}
