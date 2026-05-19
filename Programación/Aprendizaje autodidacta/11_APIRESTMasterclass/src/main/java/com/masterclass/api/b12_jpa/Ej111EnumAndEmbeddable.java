package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;

/**
 * Ejercicio 111 · @Enumerated y @Embeddable.
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.1).
 */
public final class Ej111EnumAndEmbeddable {

    public enum Estado {
        ACTIVO, BAJA
    }

    private Ej111EnumAndEmbeddable() {
    }

    /**
     * Persiste y relee para comprobar que enum y embebido se mapean bien.
     *
     * @param em EntityManager
     * @param s  socio a guardar
     * @return el socio recuperado por id (fresh desde BD)
     */
    public static Socio111 guardarYRecargar(EntityManager em, Socio111 s) {
        // TODO 1: begin tx, persist(s), commit.
        // TODO 2: em.clear() para forzar lectura desde BD (no caché de 1er nivel).
        // TODO 3: devuelve em.find(Socio111.class, s.getId()).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

// TODO 4: anota Direccion111 con @jakarta.persistence.Embeddable.
class Direccion111 {
    private String calle;
    private String ciudad;

    protected Direccion111() {
    }

    public Direccion111(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }
}

@jakarta.persistence.Entity
class Socio111 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // TODO 5: anota 'estado' con @Enumerated(EnumType.STRING)
    //         (guardar el NOMBRE del enum, no su ordinal — más robusto).
    private Ej111EnumAndEmbeddable.Estado estado;

    // TODO 6: anota 'direccion' con @jakarta.persistence.Embedded.
    // TODO 7: sin @Embedded el objeto Direccion111 no se persiste como columnas.
    private Direccion111 direccion;

    protected Socio111() {
    }

    public Socio111(String nombre, Ej111EnumAndEmbeddable.Estado estado, Direccion111 direccion) {
        // TODO 8: asigna los tres campos (constructor de conveniencia).
        // TODO 9: el enum se guardará como String por @Enumerated(STRING).
        // TODO 10: la dirección se "aplana" en columnas de la tabla SOCIO111.
        this.nombre = nombre;
        this.estado = estado;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public Ej111EnumAndEmbeddable.Estado getEstado() {
        return estado;
    }

    public Direccion111 getDireccion() {
        return direccion;
    }
}
