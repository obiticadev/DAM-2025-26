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
        // GUÍA: teoría 13.3 (el lado @ManyToMany es un Set normal).
        // 1. Protege a null (devuelve 0).
        // 2. Devuelve a.getCursos().size().
        // PISTA: return a == null ? 0 : a.getCursos().size();
        // OJO: depende de que matricular (TODO 7 del ejercicio base) añada de verdad al Set.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarCursos");
    }

    /**
     * Reto Extra 2: Comprueba si el alumno tiene al menos un curso.
     */
    public static boolean tieneCursos(Alumno117 a) {
        // GUÍA: teoría 13.3. Reutiliza contarCursos del reto 1.
        // PISTA: return contarCursos(a) > 0;   // o !a.getCursos().isEmpty()
        // OJO: vacío → false; tras matricular un curso → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCursos");
    }

    /**
     * Reto Extra 3: Verifica si un alumno esta matriculado en un curso concreto.
     */
    public static boolean estaMatriculado(Alumno117 a, Curso117 c) {
        // GUÍA: teoría 13.3. El Set sabe responder contains.
        // 1. Protege los null.
        // 2. Devuelve a.getCursos().contains(c).
        // PISTA: return a != null && c != null && a.getCursos().contains(c);
        // OJO: Curso117 no define equals, así que contains compara por identidad; el test
        //      matricula y consulta LA MISMA instancia de curso → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaMatriculado");
    }

    /**
     * Reto Extra 4: Comprueba si el alumno tiene un curso por nombre.
     */
    public static boolean tieneCursoPorNombre(Alumno117 a, String nombreCurso) {
        // GUÍA: teoría 13.3 + streams. Curso117 ya tiene getNombre().
        // PISTA: a.getCursos().stream().anyMatch(c -> nombreCurso.equals(c.getNombre()));
        // OJO: el test espera "Historia" true y "Lengua" false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCursoPorNombre");
    }

    /**
     * Reto Extra 5: Cuenta cursos con nombre mas largo que len.
     */
    public static int contarCursosNombreLargo(Alumno117 a, int len) {
        // GUÍA: teoría 13.3 + filter+count.
        // PISTA: (int) a.getCursos().stream()
        //            .filter(c -> c.getNombre() != null && c.getNombre().length() > len).count();
        // OJO: "Geografia" mide 9; con len=5 pasa → 1. Es estrictamente mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarCursosNombreLargo");
    }

    /**
     * Reto Extra 6: Crea un nuevo curso.
     */
    public static Curso117 crearCurso(String nombre) {
        // GUÍA: una línea — factory simple.
        // PISTA: return new Curso117(nombre);
        // OJO: el test solo comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearCurso");
    }

    /**
     * Reto Extra 7: Desmatricula a un alumno de un curso.
     */
    public static boolean desmatricular(Alumno117 a, Curso117 c) {
        // GUÍA: teoría 13.3. En N–N, quitar del Set borra la fila de la tabla de unión,
        //       NO el curso (a diferencia de orphanRemoval en 1–N, teoría 13.5).
        // 1. Devuelve el resultado de a.getCursos().remove(c) (true si estaba).
        // PISTA: return a.getCursos().remove(c);
        // OJO: el test espera true y que el Set quede en size 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desmatricular");
    }

    /**
     * Reto Extra 8: Comprueba si dos alumnos comparten al menos un curso.
     */
    public static boolean compartenCurso(Alumno117 a1, Alumno117 a2) {
        // GUÍA: teoría 13.3 + álgebra de conjuntos (intersección no vacía).
        // 1. Recorre los cursos de a1 y mira si alguno está también en los de a2.
        // PISTA: a1.getCursos().stream().anyMatch(a2.getCursos()::contains);
        //        (alternativa clásica: java.util.Collections.disjoint(a1.getCursos(), a2.getCursos()) == false)
        // OJO: el test matricula a ambos en LA MISMA instancia de curso → comparten → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compartenCurso");
    }

    /**
     * Reto Extra 9: Matricula a un alumno en multiples cursos.
     */
    public static void matricularEnLote(Alumno117 a, java.util.List<Curso117> lista) {
        // GUÍA: teoría 13.3. Itera la lista y matricula cada curso.
        // 1. Protege que a y lista no sean null.
        // 2. Por cada curso, llama a a.matricular(c) (o a.getCursos().add(c)).
        // PISTA: lista.forEach(a::matricular);
        // OJO: el test pasa 2 cursos y espera size 2. Si reutilizas matricular, asegúrate
        //      de haber implementado TODO 7 del ejercicio base.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para matricularEnLote");
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto del alumno.
     */
    public static String formatearAlumno(Alumno117 a) {
        // GUÍA: formato EXACTO.
        // PISTA: return "Alumno[Id=" + a.getId() + ", Cursos=" + a.getCursos().size() + "]";
        // OJO: el test espera literalmente "Alumno[Id=null, Cursos=0]".
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

    public String getNombre() {
        return nombre;
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
