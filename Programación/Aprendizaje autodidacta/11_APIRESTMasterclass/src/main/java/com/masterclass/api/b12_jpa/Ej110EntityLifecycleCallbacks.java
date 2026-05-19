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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: begin tx, persist(a), commit.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: NO asignes 'creadoEn' aquí: debe hacerlo el @PrePersist de la entidad.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: devuelve la entidad (creadoEn ya no será null si el callback funciona).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: anota este método con @jakarta.persistence.PrePersist.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si creadoEn es null, asígnale Instant.now().
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: este método lo invoca el proveedor JPA, no tu código.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: tras persist(), 'creadoEn' debe quedar poblado.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: no lances excepción aquí (rompería el INSERT).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: el callback NO recibe parámetros y suele ser void.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: mantén el método accesible al proveedor (package/protected vale).
    }

}

@jakarta.persistence.Entity
class Auditable110 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String dato;
    private Instant creadoEn;

    protected Auditable110() {
    }

    public Auditable110(String dato) {
        this.dato = dato;
    }

    /**
     * Callback antes de INSERT.
     */
    // TODO 4: anota este método con @jakarta.persistence.PrePersist.
    void alPersistir() {
        // TODO 5: si creadoEn es null, asígnale Instant.now().
        // TODO 6: este método lo invoca el proveedor JPA, no tu código.
        // TODO 7: tras persist(), 'creadoEn' debe quedar poblado.
        // TODO 8: no lances excepción aquí (rompería el INSERT).
        // TODO 9: el callback NO recibe parámetros y suele ser void.
        // TODO 10: mantén el método accesible al proveedor (package/protected vale).
    }

    public Long getId() {
        return id;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }
}
