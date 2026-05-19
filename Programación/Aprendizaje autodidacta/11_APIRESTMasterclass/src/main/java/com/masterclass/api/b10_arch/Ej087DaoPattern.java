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
}
