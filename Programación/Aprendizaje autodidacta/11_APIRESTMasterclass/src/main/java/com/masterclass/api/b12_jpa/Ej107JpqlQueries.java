package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 107 · JPQL (consultas sobre entidades, no tablas).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 */
public final class Ej107JpqlQueries {

    private final EntityManager em;

    public Ej107JpqlQueries(EntityManager em) {
        this.em = em;
    }

    /**
     * Nombres de empleados de un departamento, en mayúsculas, ordenados.
     *
     * @param dep departamento
     * @return lista de nombres (String), no entidades
     */
    public List<String> nombresPorDepartamento(String dep) {
        // TODO 1: JPQL que SELECCIONA un campo: "select e.nombre from Empleado107 e
        //         where e.departamento = :d order by e.nombre".
        // TODO 2: el tipo de resultado es String.class (proyección de columna).
        // TODO 3: setParameter("d", dep).
        // TODO 4: getResultList().
        return List.of();
    }

    /**
     * Salario medio del departamento.
     *
     * @param dep departamento
     * @return media (0.0 si no hay empleados)
     */
    public double salarioMedio(String dep) {
        // TODO 5: JPQL de agregación "select avg(e.salario) from Empleado107 e where e.departamento=:d".
        // TODO 6: getSingleResult() devuelve Double (o null si no hay filas).
        // TODO 7: si es null, devuelve 0.0 (caso límite: departamento vacío).
        // TODO 8: si no, devuelve el valor.
        return -1;
    }

    /**
     * Empleados cuyo nombre contiene un fragmento (LIKE).
     *
     * @param fragmento subcadena a buscar
     * @return entidades coincidentes
     */
    public List<Empleado107> buscarPorNombreLike(String fragmento) {
        // TODO 9: JPQL "... where e.nombre like :patron"; patrón = "%" + fragmento + "%".
        // TODO 10: setParameter y getResultList.
        return List.of();
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
     * TODO extra 2: Busca un empleado por departamento con JPQL usando el repositorio.
     */
    public static java.util.List<Empleado> desafioBuscarPorDeptoJpql(EmpleadoRepository repo, String dep) {
        return repo.buscarPorDepartamentoJpql(dep);
    }

    /**
     * TODO extra 3: Cuenta el número total de empleados de forma genérica.
     */
    public static long desafioContarTodos(EmpleadoRepository repo) {
        return repo.count();
    }

    /**
     * TODO extra 4: Comprueba si un departamento es nulo o vacío para búsquedas JPQL.
     */
    public static void desafioValidarDeptoBuscado(String dep) {
        if (dep == null || dep.isBlank()) {
            throw new IllegalArgumentException("Depto no válido");
        }
    }

    /**
     * TODO extra 5: Crea un empleado preconfigurado para pruebas.
     */
    public static Empleado desafioCrearInstanciaEmpleado(String nombre, String dep) {
        var e = new Empleado();
        e.setNombre(nombre);
        e.setDepartamento(dep);
        return e;
    }

    /**
     * TODO extra 6: Comprueba si el empleado tiene un departamento que coincide con el buscado.
     */
    public static boolean desafioCoincideDepartamento(Empleado e, String dep) {
        return e != null && dep.equals(e.getDepartamento());
    }

    /**
     * TODO extra 7: Lanza una excepción si el empleado no tiene un ID asignado.
     */
    public static void desafioValidarIdAsignado(Empleado e) {
        if (e == null || e.getId() == null) {
            throw new IllegalStateException("ID no asignado");
        }
    }

    /**
     * TODO extra 8: Retorna una lista inmodificable de empleados a partir de una lista.
     */
    public static java.util.List<Empleado> desafioObtenerListaInmodificable(java.util.List<Empleado> lista) {
        return java.util.List.copyOf(lista);
    }

    /**
     * TODO extra 9: Comprueba si al menos un empleado pertenece al departamento IT.
     */
    public static boolean desafioTieneEmpleadosIT(java.util.List<Empleado> empleados) {
        return empleados.stream().anyMatch(e -> "IT".equals(e.getDepartamento()));
    }

    /**
     * TODO extra 10: Retorna verdadero si una lista de empleados es no vacía.
     */
    public static boolean desafioContieneElementos(java.util.List<Empleado> empleados) {
        return empleados != null && !empleados.isEmpty();
    }

}
