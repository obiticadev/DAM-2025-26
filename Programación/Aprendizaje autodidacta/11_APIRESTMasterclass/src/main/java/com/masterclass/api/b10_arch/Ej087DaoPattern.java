package com.masterclass.api.b10_arch;

import java.util.List;

/**
 * Ejercicio 087 · Patrón DAO clásico (relevante para Acceso a Datos).
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.2).
 *
 * <p>El DAO encapsula CRUD; el resto del código no sabe si hay JDBC, fichero o memoria.
 */
public final class Ej087DaoPattern {

    public record Empleado(int id, String nombre, String departamento) {
    }

    /** Contrato DAO específico de Empleado. */
    public interface EmpleadoDao {
        void insertar(Empleado e);
        Empleado buscarPorId(int id);
        List<Empleado> buscarPorDepartamento(String dep);
        boolean eliminar(int id);
    }

    /** Implementación en memoria (sustituible por una JDBC sin tocar clientes). */
    public static class EmpleadoDaoMem implements EmpleadoDao {
        private final java.util.Map<Integer, Empleado> tabla = new java.util.LinkedHashMap<>();

        @Override
        public void insertar(Empleado e) {
            // TODO 1: valida e no null.
            // TODO 2: si ya existe e.id() lanza IllegalStateException (clave duplicada, como en SQL).
            // TODO 3: inserta en 'tabla'.
        }

        @Override
        public Empleado buscarPorId(int id) {
            // TODO 4: devuelve el empleado o null si no existe (estilo DAO clásico).
            return null;
        }

        @Override
        public List<Empleado> buscarPorDepartamento(String dep) {
            // TODO 5: filtra los valores cuyo departamento sea 'dep'.
            // TODO 6: devuelve la lista (vacía si ninguno).
            return List.of();
        }

        @Override
        public boolean eliminar(int id) {
            // TODO 7: elimina por id; devuelve true si existía.
            return false;
        }
    }

    private Ej087DaoPattern() {
    }

    /**
     * @param dao DAO de empleados
     * @return cuántos empleados hay en "IT" tras insertar 2 (uno IT, uno RRHH)
     */
    public static int escenario(EmpleadoDao dao) {
        // TODO 8: inserta Empleado(1,"Ana","IT") y Empleado(2,"Leo","RRHH").
        // TODO 9: consulta buscarPorDepartamento("IT").
        // TODO 10: devuelve el tamaño de esa lista (debe ser 1).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(escenario(new EmpleadoDaoMem()));
    }

    /**
     * TODO extra 1: Valida que el empleado no sea nulo.
     */
    public static void desafioValidarEmpleado(Empleado e) {
        if (e == null) {
            throw new IllegalArgumentException("Empleado no puede ser nulo");
        }
    }

    /**
     * TODO extra 2: Comprueba duplicados de clave en el mapa de memoria.
     */
    public static void desafioComprobarDuplicado(java.util.Map<Integer, Empleado> tabla, int id) {
        if (tabla.containsKey(id)) {
            throw new IllegalStateException("Clave duplicada: " + id);
        }
    }

    /**
     * TODO extra 3: Inserta en el mapa simulado de base de datos.
     */
    public static void desafioInsertarEnTabla(java.util.Map<Integer, Empleado> tabla, Empleado e) {
        desafioValidarEmpleado(e);
        desafioComprobarDuplicado(tabla, e.id());
        tabla.put(e.id(), e);
    }

    /**
     * TODO extra 4: Busca un empleado por ID devolviendo null si no existe.
     */
    public static Empleado desafioBuscarPorId(java.util.Map<Integer, Empleado> tabla, int id) {
        return tabla.get(id);
    }

    /**
     * TODO extra 5: Filtra los empleados de un departamento específico.
     */
    public static java.util.List<Empleado> desafioFiltrarPorDepartamento(java.util.Map<Integer, Empleado> tabla, String dep) {
        if (dep == null) return java.util.List.of();
        return tabla.values().stream().filter(e -> dep.equals(e.departamento())).toList();
    }

    /**
     * TODO extra 6: Crea una lista de empleados inmodificable y segura.
     */
    public static java.util.List<Empleado> desafioCrearListaSegura(java.util.List<Empleado> empleados) {
        return java.util.List.copyOf(empleados);
    }

    /**
     * TODO extra 7: Elimina un empleado del mapa devolviendo si existía.
     */
    public static boolean desafioEliminarPorId(java.util.Map<Integer, Empleado> tabla, int id) {
        return tabla.remove(id) != null;
    }

    /**
     * TODO extra 8: Inserta varios empleados usando el DAO.
     */
    public static void desafioInsertarVarios(EmpleadoDao dao, java.util.List<Empleado> empleados) {
        for (Empleado e : empleados) {
            dao.insertar(e);
        }
    }

    /**
     * TODO extra 9: Busca todos los empleados del departamento IT usando el DAO.
     */
    public static java.util.List<Empleado> desafioBuscarIT(EmpleadoDao dao) {
        return dao.buscarPorDepartamento("IT");
    }

    /**
     * TODO extra 10: Devuelve el tamaño de la lista de empleados o cero si es nula.
     */
    public static int desafioObtenerTamañoSeguro(java.util.List<Empleado> empleados) {
        return empleados == null ? 0 : empleados.size();
    }

}
