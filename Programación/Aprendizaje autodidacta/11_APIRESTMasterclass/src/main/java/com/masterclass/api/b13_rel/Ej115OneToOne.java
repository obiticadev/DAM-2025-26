package com.masterclass.api.b13_rel;

import jakarta.persistence.*;

/**
 * Ejercicio 115 · @OneToOne.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.1).
 */
public final class Ej115OneToOne {

    private Ej115OneToOne() {
    }

    /**
     * Persiste un usuario con su perfil asociado y lo recarga.
     *
     * @param em EntityManager
     * @param u  usuario con perfil seteado
     * @return el usuario recargado desde BD (con su perfil)
     */
    public static Usuario115 guardarYRecargar(EntityManager em, Usuario115 u) {
        // TODO 1: begin tx.
        // TODO 2: persist(u) — con cascade debe persistir también el perfil.
        // TODO 3: commit.
        // TODO 4: em.clear() para leer fresco desde BD.
        // TODO 5: devuelve em.find(Usuario115.class, u.getId()).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: begin tx.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: persist(u) — con cascade debe persistir también el perfil.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: commit.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: em.clear() para leer fresco desde BD.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: devuelve em.find(Usuario115.class, u.getId()).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: anota 'perfil' con @OneToOne.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: añade cascade = CascadeType.ALL (al guardar el usuario, guarda el perfil).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: usa @JoinColumn(name = "perfil_id") para la FK.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: asigna nombre y perfil.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: la relación es 1–1: un usuario tiene exactamente un perfil.
    }

}

@Entity
class Perfil115 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;

    protected Perfil115() {
    }

    public Perfil115(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}

@Entity
class Usuario115 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // TODO 6: anota 'perfil' con @OneToOne.
    // TODO 7: añade cascade = CascadeType.ALL (al guardar el usuario, guarda el perfil).
    // TODO 8: usa @JoinColumn(name = "perfil_id") para la FK.
    private Perfil115 perfil;

    protected Usuario115() {
    }

    public Usuario115(String nombre, Perfil115 perfil) {
        // TODO 9: asigna nombre y perfil.
        // TODO 10: la relación es 1–1: un usuario tiene exactamente un perfil.
        this.nombre = nombre;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public Perfil115 getPerfil() {
        return perfil;
    }
}
