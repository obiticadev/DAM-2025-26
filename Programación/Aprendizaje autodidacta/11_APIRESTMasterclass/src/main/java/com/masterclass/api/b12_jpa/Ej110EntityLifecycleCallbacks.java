package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.time.Instant;

/**
 * Ejercicio 110 · Callbacks del ciclo de vida (@PrePersist/@PreUpdate).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.2).
 */
public final class Ej110EntityLifecycleCallbacks {

    private Ej110EntityLifecycleCallbacks() {
    }

    /**
     * Persiste la entidad; el callback debe rellenar 'creadoEn' automáticamente.
     *
     * @param em EntityManager
     * @param a  auditable nuevo (creadoEn null al entrar)
     * @return la entidad con creadoEn ya asignado por el callback
     */
    public static Auditable110 guardar(EntityManager em, Auditable110 a) {
        // TODO 1: begin tx, persist(a), commit.
        // TODO 2: NO asignes 'creadoEn' aquí: debe hacerlo el @PrePersist de la entidad.
        // TODO 3: devuelve la entidad (creadoEn ya no será null si el callback funciona).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * TODO extra 1: Comprueba si un método específico de una clase está anotado con @PrePersist.
     */
    public static boolean desafioTienePrePersist(Class<?> clase, String metodo) {
        try {
            var m = clase.getDeclaredMethod(metodo);
            return m.isAnnotationPresent(jakarta.persistence.PrePersist.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 2: Comprueba si un método específico de una clase está anotado con @PreUpdate.
     */
    public static boolean desafioTienePreUpdate(Class<?> clase, String metodo) {
        try {
            var m = clase.getDeclaredMethod(metodo);
            return m.isAnnotationPresent(jakarta.persistence.PreUpdate.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 3: Comprueba si el objeto Auditado tiene la fecha de creación no nula.
     */
    public static boolean desafioTieneCreadoEn(Auditado a) {
        return a != null && a.getCreadoEn() != null;
    }

    /**
     * TODO extra 4: Comprueba si el objeto Auditado tiene la fecha de actualización no nula.
     */
    public static boolean desafioTieneActualizadoEn(Auditado a) {
        return a != null && a.getActualizadoEn() != null;
    }

    /**
     * TODO extra 5: Inicializa manualmente la fecha de creación de un Auditado a la fecha actual.
     */
    public static void desafioInicializarCreadoEn(Auditado a) {
        a.setCreadoEn(java.time.LocalDateTime.now());
    }

    /**
     * TODO extra 6: Inicializa manualmente la fecha de actualización de un Auditado a la fecha actual.
     */
    public static void desafioInicializarActualizadoEn(Auditado a) {
        a.setActualizadoEn(java.time.LocalDateTime.now());
    }

    /**
     * TODO extra 7: Lanza una excepción si un Auditado es nulo en operaciones de ciclo de vida.
     */
    public static void desafioValidarAuditado(Auditado a) {
        if (a == null) {
            throw new IllegalArgumentException("Auditado no válido");
        }
    }

    /**
     * TODO extra 8: Crea una instancia rápida de Auditado con un dato específico.
     */
    public static Auditado desafioCrearInstanciaAuditado(String dato) {
        var a = new Auditado();
        a.setDato(dato);
        return a;
    }

    /**
     * TODO extra 9: Comprueba que la fecha de actualización sea posterior o igual a la de creación.
     */
    public static boolean desafioFechasCoherentes(Auditado a) {
        if (a.getCreadoEn() == null || a.getActualizadoEn() == null) return false;
        return !a.getActualizadoEn().isBefore(a.getCreadoEn());
    }

    /**
     * TODO extra 10: Retorna verdadero si el Auditado tiene un id asignado de forma coherente.
     */
    public static boolean desafioTieneIdAsignado(Auditado a) {
        return a != null && a.getId() != null;
    }

}
