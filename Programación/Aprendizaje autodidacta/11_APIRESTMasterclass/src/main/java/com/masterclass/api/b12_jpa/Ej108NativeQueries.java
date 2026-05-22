package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 108 · SQL nativo (cuando JPQL no basta).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 */
public final class Ej108NativeQueries {

    private final EntityManager em;

    public Ej108NativeQueries(EntityManager em) {
        this.em = em;
    }

    /**
     * Cuenta filas con SQL nativo (nombre de TABLA, no de entidad).
     *
     * @return número de filas de la tabla CIUDAD108
     */
    public long contarNativo() {
        // TODO 1: createNativeQuery("SELECT COUNT(*) FROM CIUDAD108").
        // TODO 2: getSingleResult() (devuelve Number/Long según dialecto).
        // TODO 3: convierte a long con ((Number)res).longValue().
        // TODO 4: SQL nativo usa el nombre real de la tabla, no la entidad.
        return -1;
    }

    /**
     * Recupera entidades con SQL nativo mapeado a la entidad.
     *
     * @param paisMin población mínima
     * @return ciudades con población &gt;= paisMin
     */
    @SuppressWarnings("unchecked")
    public List<Ciudad108> grandesNativo(int paisMin) {
        // TODO 5: createNativeQuery("SELECT * FROM CIUDAD108 WHERE poblacion >= ?", Ciudad108.class).
        // TODO 6: setParameter(1, paisMin) (parámetros nativos son posicionales con ?).
        // TODO 7: getResultList() devuelve List de Ciudad108 (mapeo por la 2ª arg).
        // TODO 8: nunca concatenes 'paisMin' en el SQL (inyección).
        // TODO 9: el orden no está garantizado sin ORDER BY (añádelo si el test lo pide).
        // TODO 10: devuelve la lista.
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
     * TODO extra 2: Busca un empleado por departamento con query nativa SQL usando el repositorio.
     */
    public static java.util.List<Empleado> desafioBuscarPorDeptoNativa(EmpleadoRepository repo, String dep) {
        return repo.buscarPorDepartamentoNativa(dep);
    }

    /**
     * TODO extra 3: Comprueba si el departamento a filtrar en query nativa es nulo.
     */
    public static void desafioValidarFiltroDepto(String dep) {
        if (dep == null || dep.isBlank()) {
            throw new IllegalArgumentException("Filtro no válido");
        }
    }

    /**
     * TODO extra 4: Crea una instancia rápida de Empleado para tests.
     */
    public static Empleado desafioCrearInstanciaEmpleado(String nombre, String dep) {
        var e = new Empleado();
        e.setNombre(nombre);
        e.setDepartamento(dep);
        return e;
    }

    /**
     * TODO extra 5: Retorna el nombre nativo de la tabla de empleados en la base de datos (p.ej. EMPLEADO).
     */
    public static String desafioObtenerNombreTablaNativo() {
        return "EMPLEADO";
    }

    /**
     * TODO extra 6: Comprueba si el empleado recuperado tiene datos coherentes.
     */
    public static boolean desafioDatosCoherentes(Empleado e) {
        return e != null && e.getNombre() != null && e.getDepartamento() != null;
    }

    /**
     * TODO extra 7: Lanza una excepción si un empleado tiene un nombre vacío.
     */
    public static void desafioValidarEmpleadoDePrueba(Empleado e) {
        if (e == null || e.getNombre() == null || e.getNombre().isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
    }

    /**
     * TODO extra 8: Cuenta el número total de empleados mediante el repositorio.
     */
    public static long desafioContarEmpleados(EmpleadoRepository repo) {
        return repo.count();
    }

    /**
     * TODO extra 9: Retorna verdadero si una lista de empleados es no vacía y contiene empleados.
     */
    public static boolean desafioTieneRegistros(java.util.List<Empleado> lista) {
        return lista != null && !lista.isEmpty();
    }

    /**
     * TODO extra 10: Retorna un objeto descriptivo con los nombres de todos los empleados mapeados nativamente.
     */
    public static java.util.List<String> desafioObtenerNombresLista(java.util.List<Empleado> empleados) {
        return empleados.stream().map(Empleado::getNombre).toList();
    }

}
