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

    /**
     * TODO extra 1: Comprueba si una instancia de EntityManager no es nula.
     */
    public static boolean desafioEntityManagerActivo(jakarta.persistence.EntityManager em) {
        return em != null;
    }

    /**
     * TODO extra 2: Comprueba si una entidad está actualmente gestionada (managed) por el contexto.
     */
    public static boolean desafioEsEntidadGestionada(jakarta.persistence.EntityManager em, Object entidad) {
        return em.contains(entidad);
    }

    /**
     * TODO extra 3: Persiste una entidad nueva utilizando el EntityManager.
     */
    public static void desafioPersistirEntidad(jakarta.persistence.EntityManager em, Object entidad) {
        em.persist(entidad);
    }

    /**
     * TODO extra 4: Sincroniza el estado del contexto con la base de datos ejecutando flush().
     */
    public static void desafioSincronizarContexto(jakarta.persistence.EntityManager em) {
        em.flush();
    }

    /**
     * TODO extra 5: Desasocia (detach) una entidad del contexto de persistencia.
     */
    public static void desafioDesasociarEntidad(jakarta.persistence.EntityManager em, Object entidad) {
        em.detach(entidad);
    }

    /**
     * TODO extra 6: Asocia de nuevo (merge) una entidad desasociada al contexto.
     */
    public static <T> T desafioReasociarEntidad(jakarta.persistence.EntityManager em, T entidad) {
        return em.merge(entidad);
    }

    /**
     * TODO extra 7: Remueve una entidad gestionada de la base de datos.
     */
    public static void desafioRemoverEntidad(jakarta.persistence.EntityManager em, Object entidad) {
        em.remove(entidad);
    }

    /**
     * TODO extra 8: Vacía completamente el contexto de persistencia (clear).
     */
    public static void desafioVaciarContexto(jakarta.persistence.EntityManager em) {
        em.clear();
    }

    /**
     * TODO extra 9: Crea una instancia de Usuario básica para probar transiciones de estado.
     */
    public static Usuario desafioCrearInstanciaUsuario(String nombre) {
        var u = new Usuario();
        u.setNombre(nombre);
        return u;
    }

    /**
     * TODO extra 10: Retorna verdadero si el ID del usuario no es nulo tras persistir.
     */
    public static boolean desafioTieneIdAsignado(Usuario u) {
        return u != null && u.getId() != null;
    }

}
