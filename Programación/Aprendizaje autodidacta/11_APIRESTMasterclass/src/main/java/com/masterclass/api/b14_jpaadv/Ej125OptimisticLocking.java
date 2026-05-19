package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 125 · Bloqueo optimista con @Version.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.2).
 */
public final class Ej125OptimisticLocking {

    private Ej125OptimisticLocking() {
    }

    /**
     * Persiste un producto y devuelve su id.
     *
     * @param em EntityManager
     * @param p  producto
     * @return id generado
     */
    public static Long guardar(EntityManager em, ProdVer125 p) {
        // TODO 1: begin tx, persist(p), commit.
        // TODO 2: devuelve p.getId().
        return null;
    }

    /**
     * Actualiza el precio en una transacción independiente.
     *
     * @param em      EntityManager
     * @param id      id del producto
     * @param precio  nuevo precio
     */
    public static void actualizarPrecio(EntityManager em, Long id, double precio) {
        // TODO 3: begin tx.
        // TODO 4: find del producto (queda managed con su version actual).
        // TODO 5: cambia el precio con el setter.
        // TODO 6: commit -> Hibernate incrementa la columna @Version.
        // TODO 7: si otra tx ya cambió la version, aquí saltaría OptimisticLockException
        //         (el test provoca ese escenario con dos EntityManager).
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class ProdVer125 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double precio;

    // TODO 8: anota 'version' con @jakarta.persistence.Version.
    // TODO 9: el tipo long/Integer es válido para @Version; aquí usamos long.
    private long version;

    protected ProdVer125() {
    }

    public ProdVer125(double precio) {
        // TODO 10: asigna el precio inicial.
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double p) {
        this.precio = p;
    }
}
