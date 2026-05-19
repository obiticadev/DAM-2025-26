package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;

/**
 * Ejercicio 109 · Modificaciones masivas (@Modifying / executeUpdate).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 */
public final class Ej109ModifyingQueries {

    private final EntityManager em;

    public Ej109ModifyingQueries(EntityManager em) {
        this.em = em;
    }

    /**
     * Sube el precio un porcentaje a TODOS los productos de una categoría (UPDATE masivo).
     *
     * @param categoria  categoría afectada
     * @param porcentaje p.ej. 10 para +10 %
     * @return número de filas modificadas
     */
    public int subirPrecios(String categoria, double porcentaje) {
        // TODO 1: begin transaction (los UPDATE/DELETE masivos requieren tx).
        // TODO 2: JPQL "update Prod109 p set p.precio = p.precio * :factor where p.categoria = :cat".
        // TODO 3: factor = 1 + porcentaje/100.
        // TODO 4: createQuery(...).setParameter("factor", factor).setParameter("cat", categoria).
        // TODO 5: executeUpdate() devuelve el nº de filas afectadas.
        // TODO 6: commit.
        // TODO 7: tras un update masivo el contexto puede estar desincronizado: limpia con em.clear().
        // TODO 8: devuelve el nº de filas.
        return -1;
    }

    /**
     * Borra todos los productos con stock 0 (DELETE masivo).
     *
     * @return filas borradas
     */
    public int borrarSinStock() {
        // TODO 9: begin tx; JPQL "delete from Prod109 p where p.stock = 0"; executeUpdate.
        // TODO 10: commit y devuelve el nº de filas borradas.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@jakarta.persistence.Entity
class Prod109 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private double precio;
    private int stock;

    protected Prod109() {
    }

    public Prod109(String categoria, double precio, int stock) {
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}
