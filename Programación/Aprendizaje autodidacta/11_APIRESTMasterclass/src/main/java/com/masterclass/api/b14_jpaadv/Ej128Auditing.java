package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Ejercicio 128 · Auditoría con @PrePersist/@PreUpdate.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.3).
 */
public final class Ej128Auditing {

    private Ej128Auditing() {
    }

    /**
     * Persiste y luego modifica para comprobar que las marcas de auditoría se rellenan.
     *
     * @param em EntityManager
     * @param d  documento nuevo
     * @return el id generado
     */
    public static Long guardar(EntityManager em, DocAud128 d) {
        // TODO 1: begin tx, persist(d), commit -> @PrePersist fija creadoEn.
        // TODO 2: devuelve d.getId().
        return null;
    }

    /**
     * @param em EntityManager
     * @param id id
     * @param texto nuevo contenido (provoca @PreUpdate -> actualizadoEn)
     */
    public static void modificar(EntityManager em, Long id, String texto) {
        // TODO 3: begin tx, find, cambiar texto, commit -> @PreUpdate fija actualizadoEn.
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: begin tx, persist(d), commit -> @PrePersist fija creadoEn.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: devuelve d.getId().
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: begin tx, find, cambiar texto, commit -> @PreUpdate fija actualizadoEn.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: anota con @PrePersist.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: asigna creadoEn = Instant.now() (solo al insertar).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: anota con @PreUpdate.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: asigna actualizadoEn = Instant.now() (en cada UPDATE).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: NO toques creadoEn aquí (la fecha de creación es inmutable).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: asigna el nuevo texto.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: el cambio de texto disparará @PreUpdate al hacer commit.
    }

}

@Entity
class DocAud128 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private Instant creadoEn;
    private Instant actualizadoEn;

    protected DocAud128() {
    }

    public DocAud128(String texto) {
        this.texto = texto;
    }

    // TODO 4: anota con @PrePersist.
    void prePersist() {
        // TODO 5: asigna creadoEn = Instant.now() (solo al insertar).
    }

    // TODO 6: anota con @PreUpdate.
    void preUpdate() {
        // TODO 7: asigna actualizadoEn = Instant.now() (en cada UPDATE).
        // TODO 8: NO toques creadoEn aquí (la fecha de creación es inmutable).
    }

    public Long getId() {
        return id;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public Instant getActualizadoEn() {
        return actualizadoEn;
    }

    public void setTexto(String t) {
        // TODO 9: asigna el nuevo texto.
        // TODO 10: el cambio de texto disparará @PreUpdate al hacer commit.
        this.texto = t;
    }
}
