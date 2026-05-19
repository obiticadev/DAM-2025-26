package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 129 · Borrado lógico (soft delete).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.3).
 *
 * <p>No se borra la fila: se marca {@code borrado=true} y las consultas la excluyen.
 */
public final class Ej129SoftDelete {

    private final EntityManager em;

    public Ej129SoftDelete(EntityManager em) {
        this.em = em;
    }

    /** Guarda un cliente activo. @param c cliente @return id */
    public Long crear(ClienteSD129 c) {
        // TODO 1: begin tx, persist(c), commit, devuelve c.getId().
        return null;
    }

    /**
     * Borrado lógico: marca el flag, NO ejecuta DELETE.
     *
     * @param id id del cliente
     * @return true si existía y se marcó
     */
    public boolean borrarLogico(Long id) {
        // TODO 2: begin tx.
        // TODO 3: find del cliente; si null -> false (rollback/commit vacío).
        // TODO 4: setBorrado(true) (NO em.remove).
        // TODO 5: commit.
        // TODO 6: devuelve true.
        return false;
    }

    /**
     * Lista solo los NO borrados.
     *
     * @return clientes activos (borrado = false)
     */
    public List<ClienteSD129> listarActivos() {
        // TODO 7: JPQL "select c from ClienteSD129 c where c.borrado = false order by c.id".
        // TODO 8: getResultList().
        // TODO 9: los borrados lógicamente NO deben aparecer aquí.
        // TODO 10: la fila sigue en BD (consultable sin el filtro) — soft, no físico.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: begin tx, persist(c), commit, devuelve c.getId().
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: begin tx.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: find del cliente; si null -> false (rollback/commit vacío).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: setBorrado(true) (NO em.remove).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: commit.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: devuelve true.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: JPQL "select c from ClienteSD129 c where c.borrado = false order by c.id".
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: getResultList().
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: los borrados lógicamente NO deben aparecer aquí.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: la fila sigue en BD (consultable sin el filtro) — soft, no físico.
    }

}

@Entity
class ClienteSD129 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private boolean borrado = false;

    protected ClienteSD129() {
    }

    public ClienteSD129(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean b) {
        this.borrado = b;
    }
}
