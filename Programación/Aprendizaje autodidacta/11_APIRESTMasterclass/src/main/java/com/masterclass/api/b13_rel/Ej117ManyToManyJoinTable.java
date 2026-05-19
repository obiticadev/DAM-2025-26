package com.masterclass.api.b13_rel;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Ejercicio 117 · @ManyToMany con tabla intermedia.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.1).
 */
public final class Ej117ManyToManyJoinTable {

    private Ej117ManyToManyJoinTable() {
    }

    /**
     * Matricula un alumno en varios cursos y recarga para contar.
     *
     * @param em EntityManager
     * @param a  alumno con cursos añadidos
     * @return número de cursos del alumno tras recargar
     */
    public static int guardarYContarCursos(EntityManager em, Alumno117 a) {
        // TODO 1: begin tx, persist(a), commit (cascade persiste cursos).
        // TODO 2: em.clear() y recarga el alumno.
        // TODO 3: devuelve el tamaño de su set de cursos.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: begin tx, persist(a), commit (cascade persiste cursos).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: em.clear() y recarga el alumno.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: devuelve el tamaño de su set de cursos.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: anota 'cursos' con @ManyToMany(cascade = CascadeType.ALL).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: usa @JoinTable(name = "ALUMNO_CURSO",
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: asigna el nombre.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: añade 'c' al set de cursos.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: N–N: un alumno tiene muchos cursos y un curso muchos alumnos.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: la tabla intermedia ALUMNO_CURSO guarda los pares.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: no dupliques (es un Set: añade idempotente).
    }

}

@Entity
class Curso117 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    protected Curso117() {
    }

    public Curso117(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }
}

@Entity
class Alumno117 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // TODO 4: anota 'cursos' con @ManyToMany(cascade = CascadeType.ALL).
    // TODO 5: usa @JoinTable(name = "ALUMNO_CURSO",
    //          joinColumns=@JoinColumn(name="alumno_id"),
    //          inverseJoinColumns=@JoinColumn(name="curso_id")).
    private Set<Curso117> cursos = new HashSet<>();

    protected Alumno117() {
    }

    public Alumno117(String nombre) {
        // TODO 6: asigna el nombre.
        this.nombre = nombre;
    }

    public void matricular(Curso117 c) {
        // TODO 7: añade 'c' al set de cursos.
        // TODO 8: N–N: un alumno tiene muchos cursos y un curso muchos alumnos.
        // TODO 9: la tabla intermedia ALUMNO_CURSO guarda los pares.
        // TODO 10: no dupliques (es un Set: añade idempotente).
    }

    public Long getId() {
        return id;
    }

    public Set<Curso117> getCursos() {
        return cursos;
    }
}
