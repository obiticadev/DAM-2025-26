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

    /**
     * Reto Extra 1: Cuenta el numero de cursos de un alumno.
     */
    public static int contarCursos(Alumno117 a) {
        // TODO extra: Reto Extra 1: Cuenta el numero de cursos de un alumno.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarCursos");
    }

    /**
     * Reto Extra 2: Comprueba si el alumno tiene al menos un curso.
     */
    public static boolean tieneCursos(Alumno117 a) {
        // TODO extra: Reto Extra 2: Comprueba si el alumno tiene al menos un curso.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCursos");
    }

    /**
     * Reto Extra 3: Verifica si un alumno esta matriculado en un curso concreto.
     */
    public static boolean estaMatriculado(Alumno117 a, Curso117 c) {
        // TODO extra: Reto Extra 3: Verifica si un alumno esta matriculado en un curso concreto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaMatriculado");
    }

    /**
     * Reto Extra 4: Comprueba si el alumno tiene un curso por nombre.
     */
    public static boolean tieneCursoPorNombre(Alumno117 a, String nombreCurso) {
        // TODO extra: Reto Extra 4: Comprueba si el alumno tiene un curso por nombre.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCursoPorNombre");
    }

    /**
     * Reto Extra 5: Cuenta cursos con nombre mas largo que len.
     */
    public static int contarCursosNombreLargo(Alumno117 a, int len) {
        // TODO extra: Reto Extra 5: Cuenta cursos con nombre mas largo que len.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarCursosNombreLargo");
    }

    /**
     * Reto Extra 6: Crea un nuevo curso.
     */
    public static Curso117 crearCurso(String nombre) {
        // TODO extra: Reto Extra 6: Crea un nuevo curso.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearCurso");
    }

    /**
     * Reto Extra 7: Desmatricula a un alumno de un curso.
     */
    public static boolean desmatricular(Alumno117 a, Curso117 c) {
        // TODO extra: Reto Extra 7: Desmatricula a un alumno de un curso.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desmatricular");
    }

    /**
     * Reto Extra 8: Comprueba si dos alumnos comparten al menos un curso.
     */
    public static boolean compartenCurso(Alumno117 a1, Alumno117 a2) {
        // TODO extra: Reto Extra 8: Comprueba si dos alumnos comparten al menos un curso.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compartenCurso");
    }

    /**
     * Reto Extra 9: Matricula a un alumno en multiples cursos.
     */
    public static void matricularEnLote(Alumno117 a, java.util.List<Curso117> lista) {
        // TODO extra: Reto Extra 9: Matricula a un alumno en multiples cursos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para matricularEnLote");
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto del alumno.
     */
    public static String formatearAlumno(Alumno117 a) {
        // TODO extra: Reto Extra 10: Retorna una representacion de texto del alumno.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearAlumno");
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
