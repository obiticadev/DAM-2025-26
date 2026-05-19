package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 126 · Bloqueo pesimista (LockModeType).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.2).
 */
public final class Ej126PessimisticLocking {

    private Ej126PessimisticLocking() {
    }

    /**
     * Lee una fila con bloqueo de escritura (PESSIMISTIC_WRITE) y resta stock.
     *
     * @param em       EntityManager
     * @param id       id del artículo
     * @param cantidad unidades a descontar
     * @return stock resultante
     * @throws IllegalStateException si no hay stock suficiente
     */
    public static int reservar(EntityManager em, Long id, int cantidad) {
        // TODO 1: begin tx.
        // TODO 2: usa em.find(ArtStock126.class, id, LockModeType.PESSIMISTIC_WRITE)
        //         para bloquear la fila mientras dura la tx.
        // TODO 3: si la entidad es null -> IllegalStateException.
        // TODO 4: comprueba que stock >= cantidad; si no -> IllegalStateException.
        // TODO 5: resta 'cantidad' al stock.
        // TODO 6: commit (libera el lock).
        // TODO 7: el lock pesimista evita que dos reservas concurrentes sobrevendan.
        // TODO 8: devuelve el stock resultante.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class ArtStock126 {
    @Id
    private Long id;
    private int stock;

    protected ArtStock126() {
    }

    public ArtStock126(Long id, int stock) {
        // TODO 9: asigna id y stock.
        this.id = id;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int s) {
        // TODO 10: asigna el nuevo stock.
        this.stock = s;
    }
}
