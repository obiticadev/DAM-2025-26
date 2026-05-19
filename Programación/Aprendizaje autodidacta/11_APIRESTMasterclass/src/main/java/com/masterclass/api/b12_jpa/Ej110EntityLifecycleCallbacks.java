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
