package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 106 · "Query methods" (filtrar por campos) con JPQL parametrizado.
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 *
 * <p>Spring Data deriva el JPQL del nombre del método; aquí lo escribes tú para
 * entender qué genera por debajo.
 */
public final class Ej106DerivedQueryMethods {

    private final EntityManager em;

    public Ej106DerivedQueryMethods(EntityManager em) {
        this.em = em;
    }

    /**
     * Equivalente a {@code findByCategoria(String)}.
     *
     * @param categoria categoría exacta
     * @return artículos de esa categoría, ordenados por id
     */
    public List<Articulo106> findByCategoria(String categoria) {
        // TODO 1: JPQL "select a from Articulo106 a where a.categoria = :cat order by a.id".
        // TODO 2: usa createQuery con la clase tipada.
        // TODO 3: setParameter("cat", categoria) (NUNCA concatenes).
        // TODO 4: getResultList().
        return List.of();
    }

    /**
     * Equivalente a {@code findByPrecioGreaterThan(double)}.
     *
     * @param min precio mínimo (exclusivo)
     * @return artículos con precio &gt; min
     */
    public List<Articulo106> findByPrecioMayorQue(double min) {
        // TODO 5: JPQL con "where a.precio > :min".
        // TODO 6: setParameter("min", min).
        // TODO 7: getResultList().
        return List.of();
    }

    /**
     * Equivalente a {@code countByCategoria(String)}.
     *
     * @param categoria categoría
     * @return número de artículos
     */
    public long countByCategoria(String categoria) {
        // TODO 8: JPQL "select count(a) from Articulo106 a where a.categoria = :cat".
        // TODO 9: getSingleResult() devuelve Long.
        // TODO 10: devuelve ese long.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * TODO extra 1: Comprueba si un repositorio JPA de tipo EmpleadoRepository está instanciado.
     */
    public static boolean desafioRepositoryActivo(EmpleadoRepository repo) {
        return repo != null;
    }

    /**
     * TODO extra 2: Busca un empleado por su nombre exacto usando el repositorio.
     */
    public static java.util.List<Empleado> desafioBuscarPorNombre(EmpleadoRepository repo, String nombre) {
        return repo.findByNombre(nombre);
    }

    /**
     * TODO extra 3: Busca empleados de un departamento específico.
     */
    public static java.util.List<Empleado> desafioBuscarPorDepartamento(EmpleadoRepository repo, String dep) {
        return repo.findByDepartamento(dep);
    }

    /**
     * TODO extra 4: Busca empleados por departamento y nombre.
     */
    public static java.util.List<Empleado> desafioBuscarPorDeptoYNombre(EmpleadoRepository repo, String dep, String nom) {
        return repo.findByDepartamentoAndNombre(dep, nom);
    }

    /**
     * TODO extra 5: Comprueba si existe algún empleado con un nombre específico.
     */
    public static boolean desafioExistePorNombre(EmpleadoRepository repo, String nombre) {
        return !repo.findByNombre(nombre).isEmpty();
    }

    /**
     * TODO extra 6: Cuenta la cantidad total de empleados en un departamento específico.
     */
    public static long desafioContarEnDepartamento(EmpleadoRepository repo, String dep) {
        return repo.countByDepartamento(dep);
    }

    /**
     * TODO extra 7: Lanza una excepción si el nombre de departamento buscado es nulo.
     */
    public static void desafioValidarDepartamentoBuscado(String dep) {
        if (dep == null || dep.isBlank()) {
            throw new IllegalArgumentException("Departamento no válido");
        }
    }

    /**
     * TODO extra 8: Crea un Empleado con los datos de prueba.
     */
    public static Empleado desafioCrearInstanciaEmpleado(String nombre, String dep) {
        var e = new Empleado();
        e.setNombre(nombre);
        e.setDepartamento(dep);
        return e;
    }

    /**
     * TODO extra 9: Comprueba si los empleados recuperados contienen al menos un elemento del departamento especificado.
     */
    public static boolean desafioContieneDepartamento(java.util.List<Empleado> empleados, String dep) {
        return empleados.stream().anyMatch(e -> dep.equals(e.getDepartamento()));
    }

    /**
     * TODO extra 10: Retorna verdadero si una lista de empleados es no nula y contiene elementos.
     */
    public static boolean desafioTieneDatos(java.util.List<Empleado> empleados) {
        return empleados != null && !empleados.isEmpty();
    }

}
