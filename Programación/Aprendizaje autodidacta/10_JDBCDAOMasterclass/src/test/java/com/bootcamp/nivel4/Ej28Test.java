package com.bootcamp.nivel4;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida los cuatro TODOs de Ej28_EntidadesRelacionadas:
 * insertar y obtenerTodos en DAODepartamento,
 * insertar y obtenerPorDepartamento en DAOEmpleado.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej28Test {

    private static Class<?> dptoClass;
    private static Class<?> empClass;
    private static Class<?> daoDptoClass;
    private static Class<?> daoEmpClass;
    private static Object daoDpto;
    private static Object daoEmp;

    @BeforeAll
    static void resolverClases() throws Exception {
        String base = "com.bootcamp.nivel4_integracion.Ej28_EntidadesRelacionadas$";
        dptoClass    = Class.forName(base + "Departamento");
        empClass     = Class.forName(base + "Empleado");
        daoDptoClass = Class.forName(base + "DAODepartamento");
        daoEmpClass  = Class.forName(base + "DAOEmpleado");
        daoDpto = daoDptoClass.getDeclaredConstructor().newInstance();
        daoEmp  = daoEmpClass.getDeclaredConstructor().newInstance();
        daoDptoClass.getMethod("crearTabla").invoke(daoDpto);
        daoEmpClass.getMethod("crearTabla").invoke(daoEmp);
    }

    private Object nuevoDpto(String nombre, String ubicacion) throws Exception {
        return dptoClass.getDeclaredConstructor(String.class, String.class)
                .newInstance(nombre, ubicacion);
    }

    private Object nuevoEmp(String nombre, double salario, int idDpto) throws Exception {
        return empClass.getDeclaredConstructor(String.class, double.class, int.class)
                .newInstance(nombre, salario, idDpto);
    }

    @Test @Order(1) @DisplayName("DAODepartamento.insertar() devuelve true")
    void insertarDptoOk() throws Exception {
        Object d = nuevoDpto("IT", "Planta 3");
        boolean ok = (boolean) daoDptoClass.getMethod("insertar", dptoClass).invoke(daoDpto, d);
        assertTrue(ok);
    }

    @Test @Order(2) @DisplayName("DAODepartamento.obtenerTodos() devuelve lista no vacía")
    void obtenerTodosDptoOk() throws Exception {
        daoDptoClass.getMethod("insertar", dptoClass).invoke(daoDpto, nuevoDpto("Ventas", "Planta 1"));
        List<?> lista = (List<?>) daoDptoClass.getMethod("obtenerTodos").invoke(daoDpto);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(3) @DisplayName("DAOEmpleado.insertar() devuelve true")
    void insertarEmpOk() throws Exception {
        List<?> dptos = (List<?>) daoDptoClass.getMethod("obtenerTodos").invoke(daoDpto);
        assertFalse(dptos.isEmpty(), "Implementa insertar() en DAODepartamento primero (TODO 1)");
        int idDpto = (int) dptos.get(0).getClass().getMethod("getId").invoke(dptos.get(0));
        Object e = nuevoEmp("María Gómez", 30000, idDpto);
        boolean ok = (boolean) daoEmpClass.getMethod("insertar", empClass).invoke(daoEmp, e);
        assertTrue(ok);
    }

    @Test @Order(4) @DisplayName("DAOEmpleado.obtenerPorDepartamento() filtra correctamente")
    void obtenerPorDepartamentoOk() throws Exception {
        List<?> dptos = (List<?>) daoDptoClass.getMethod("obtenerTodos").invoke(daoDpto);
        assertFalse(dptos.isEmpty(), "Implementa insertar() en DAODepartamento primero (TODO 1)");
        int idDpto = (int) dptos.get(0).getClass().getMethod("getId").invoke(dptos.get(0));
        daoEmpClass.getMethod("insertar", empClass).invoke(daoEmp, nuevoEmp("Pedro Ruiz", 25000, idDpto));
        List<?> emps = (List<?>) daoEmpClass.getMethod("obtenerPorDepartamento", int.class).invoke(daoEmp, idDpto);
        assertNotNull(emps);
        assertFalse(emps.isEmpty());
    }

    @Test @Order(5) @DisplayName("obtenerPorDepartamento() con id inexistente devuelve lista vacía")
    void obtenerPorDepartamentoInexistente() throws Exception {
        List<?> emps = (List<?>) daoEmpClass.getMethod("obtenerPorDepartamento", int.class).invoke(daoEmp, 99999);
        assertNotNull(emps);
        assertTrue(emps.isEmpty());
    }
}
