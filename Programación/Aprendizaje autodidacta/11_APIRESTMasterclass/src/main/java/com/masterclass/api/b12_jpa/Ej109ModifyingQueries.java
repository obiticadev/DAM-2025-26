package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;

/**
 * Ejercicio 109 · Modificaciones masivas (@Modifying / executeUpdate).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 */
public final class Ej109ModifyingQueries {

    private final EntityManager em;

    public Ej109ModifyingQueries(EntityManager em) {
        this.em = em;
    }

    /**
     * Sube el precio un porcentaje a TODOS los productos de una categoría (UPDATE masivo).
     *
     * @param categoria  categoría afectada
     * @param porcentaje p.ej. 10 para +10 %
     * @return número de filas modificadas
     */
    public int subirPrecios(String categoria, double porcentaje) {
        // TODO 1: begin transaction (los UPDATE/DELETE masivos requieren tx).
        // TODO 2: JPQL "update Prod109 p set p.precio = p.precio * :factor where p.categoria = :cat".
        // TODO 3: factor = 1 + porcentaje/100.
        // TODO 4: createQuery(...).setParameter("factor", factor).setParameter("cat", categoria).
        // TODO 5: executeUpdate() devuelve el nº de filas afectadas.
        // TODO 6: commit.
        // TODO 7: tras un update masivo el contexto puede estar desincronizado: limpia con em.clear().
        // TODO 8: devuelve el nº de filas.
        return -1;
    }

    /**
     * Borra todos los productos con stock 0 (DELETE masivo).
     *
     * @return filas borradas
     */
    public int borrarSinStock() {
        // TODO 9: begin tx; JPQL "delete from Prod109 p where p.stock = 0"; executeUpdate.
        // TODO 10: commit y devuelve el nº de filas borradas.
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
     * TODO extra 2: Actualiza el departamento de un empleado mediante el repositorio.
     */
    public static int desafioActualizarDepartamento(EmpleadoRepository repo, String depNuevo, String depViejo) {
        return repo.actualizarDepartamento(depNuevo, depViejo);
    }

    /**
     * TODO extra 3: Comprueba si la anotación @Modifying existe en un método por reflexión (simulado).
     */
    public static boolean desafioMetodoTieneModifying(Class<?> interfaz, String nombreMetodo, Class<?>... params) {
        try {
            var m = interfaz.getMethod(nombreMetodo, params);
            return m.isAnnotationPresent(org.springframework.data.jpa.repository.Modifying.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 4: Comprueba si la anotación @Query existe en un método por reflexión (simulado).
     */
    public static boolean desafioMetodoTieneQuery(Class<?> interfaz, String nombreMetodo, Class<?>... params) {
        try {
            var m = interfaz.getMethod(nombreMetodo, params);
            return m.isAnnotationPresent(org.springframework.data.jpa.repository.Query.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 5: Comprueba si el número de filas afectadas por la modificación es positivo.
     */
    public static boolean desafioVerificarModificacionExitosa(int filasAfectadas) {
        return filasAfectadas > 0;
    }

    /**
     * TODO extra 6: Lanza una excepción si el nuevo departamento a establecer es nulo.
     */
    public static void desafioValidarNuevoDepartamento(String dep) {
        if (dep == null || dep.isBlank()) {
            throw new IllegalArgumentException("Nuevo departamento no válido");
        }
    }

    /**
     * TODO extra 7: Crea un Empleado rápido para pruebas.
     */
    public static Empleado desafioCrearInstanciaEmpleado(String nombre, String dep) {
        var e = new Empleado();
        e.setNombre(nombre);
        e.setDepartamento(dep);
        return e;
    }

    /**
     * TODO extra 8: Cuenta los empleados totales mediante el repositorio.
     */
    public static long desafioContarEmpleados(EmpleadoRepository repo) {
        return repo.count();
    }

    /**
     * TODO extra 9: Comprueba si todos los empleados de la lista tienen el nuevo departamento.
     */
    public static boolean desafioVerificarTodosEnDepartamento(java.util.List<Empleado> empleados, String dep) {
        return empleados.stream().allMatch(e -> dep.equals(e.getDepartamento()));
    }

    /**
     * TODO extra 10: Retorna verdadero si una lista de empleados es no nula.
     */
    public static boolean desafioTieneDatos(java.util.List<Empleado> empleados) {
        return empleados != null;
    }

}
