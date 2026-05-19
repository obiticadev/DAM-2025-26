package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 140 · Proyección a un tipo reducido (solo los campos necesarios).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej140InterfaceProjections {

    /** Vista reducida: solo id y email (NO toda la entidad). */
    public record UsuarioVista(Long id, String email) {
    }

    private final EntityManager em;

    public Ej140InterfaceProjections(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve solo (id, email) de cada usuario, sin materializar la entidad completa.
     *
     * @return lista de UsuarioVista ordenada por id
     */
    public List<UsuarioVista> vistas() {
        // TODO 1: usa proyección por constructor en JPQL con "select new".
        // TODO 2: nombre COMPLETO del record:
        //         com.masterclass.api.b15_query.Ej140InterfaceProjections$UsuarioVista.
        // TODO 3: selecciona SOLO u.id, u.email (no u entero).
        // TODO 4: order by u.id.
        // TODO 5: createQuery(jpql, UsuarioVista.class).
        // TODO 6: getResultList().
        // TODO 7: no se cargan columnas innecesarias (proyección = menos I/O).
        // TODO 8: la vista NO está gestionada por el contexto.
        // TODO 9: si no hay usuarios, lista vacía.
        // TODO 10: devuelve la lista.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: usa proyección por constructor en JPQL con "select new".
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: nombre COMPLETO del record:
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: selecciona SOLO u.id, u.email (no u entero).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: order by u.id.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: createQuery(jpql, UsuarioVista.class).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: getResultList().
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: no se cargan columnas innecesarias (proyección = menos I/O).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: la vista NO está gestionada por el contexto.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si no hay usuarios, lista vacía.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la lista.
    }

}

@Entity
class Usuario140 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String passwordHash;

    public Usuario140() {
    }

    public Usuario140(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
