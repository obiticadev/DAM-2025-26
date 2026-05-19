package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 114 · Proyección por constructor en JPQL (select new Dto(...)).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 *
 * <p>No traigas la entidad entera si solo necesitas 2 campos: proyecta a un DTO.
 */
public final class Ej114DtoConstructorProjection {

    /** DTO de solo lectura (NO es entidad). */
    public record ResumenPedido(Long id, double total) {
    }

    private final EntityManager em;

    public Ej114DtoConstructorProjection(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve un resumen (id, total) por pedido, sin cargar la entidad completa.
     *
     * @return lista de ResumenPedido ordenada por id
     */
    public List<ResumenPedido> resumen() {
        // TODO 1: el JPQL usa el nombre COMPLETO de la clase DTO en "select new".
        // TODO 2: "select new com.masterclass.api.b12_jpa.Ej114DtoConstructorProjection$ResumenPedido(
        //          p.id, p.total) from Pedido114 p order by p.id".
        // TODO 3: el DTO debe tener un constructor que case con los tipos/orden seleccionados.
        // TODO 4: crea la query con createQuery(jpql, ResumenPedido.class).
        // TODO 5: getResultList().
        // TODO 6: la proyección evita el overhead de materializar entidades completas.
        // TODO 7: el DTO NO está gestionado por el contexto de persistencia.
        // TODO 8: respeta el orden (ORDER BY p.id).
        // TODO 9: si no hay pedidos, devuelve lista vacía (no null).
        // TODO 10: devuelve la lista de ResumenPedido.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@jakarta.persistence.Entity
class Pedido114 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private double total;

    protected Pedido114() {
    }

    public Pedido114(double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }
}
