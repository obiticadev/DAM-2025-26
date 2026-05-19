package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;

/**
 * Ejercicio 112 · Contexto de persistencia (managed/detached, flush, clear).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.2).
 */
public final class Ej112PersistenceContext {

    private Ej112PersistenceContext() {
    }

    /**
     * Demuestra "dirty checking": modificar una entidad MANAGED se persiste sin save().
     *
     * @param em EntityManager
     * @param id id de la entidad existente
     * @param nuevoNombre nuevo valor
     */
    public static void cambioSinSave(EntityManager em, Long id, String nuevoNombre) {
        // TODO 1: begin tx.
        // TODO 2: recupera la entidad con em.find (queda MANAGED en el contexto).
        // TODO 3: cambia su nombre con el setter (NO llames persist ni merge).
        // TODO 4: commit -> Hibernate detecta el cambio (dirty checking) y hace UPDATE.
        // TODO 5: esto solo funciona con entidades MANAGED dentro de la transacción.
    }

    /**
     * Demuestra que una entidad DETACHED ya no se sincroniza.
     *
     * @param em EntityManager
     * @param id id existente
     * @param nuevoNombre cambio que NO debe persistirse
     */
    public static void cambioEnDetachedNoPersiste(EntityManager em, Long id, String nuevoNombre) {
        // TODO 6: begin tx, find la entidad.
        // TODO 7: em.detach(entidad) -> pasa a estado DETACHED.
        // TODO 8: cambia su nombre tras el detach.
        // TODO 9: commit -> el cambio NO se persiste (ya no está gestionada).
        // TODO 10: para persistir un detached habría que em.merge(entidad) (no lo hagas aquí).
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: begin tx.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: recupera la entidad con em.find (queda MANAGED en el contexto).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: cambia su nombre con el setter (NO llames persist ni merge).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: commit -> Hibernate detecta el cambio (dirty checking) y hace UPDATE.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: esto solo funciona con entidades MANAGED dentro de la transacción.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: begin tx, find la entidad.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: em.detach(entidad) -> pasa a estado DETACHED.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: cambia su nombre tras el detach.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: commit -> el cambio NO se persiste (ya no está gestionada).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: para persistir un detached habría que em.merge(entidad) (no lo hagas aquí).
    }

}

@jakarta.persistence.Entity
class Doc112 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    protected Doc112() {
    }

    public Doc112(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }
}
