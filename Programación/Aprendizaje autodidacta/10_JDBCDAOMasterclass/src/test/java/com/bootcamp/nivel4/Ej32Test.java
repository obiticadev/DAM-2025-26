package com.bootcamp.nivel4;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida los tres TODOs de Ej32_ManejoErrores:
 * insertar, obtenerTodas y eliminar con manejo de SQLException.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej32Test {

    private static Class<?> tareaClass;
    private static Class<?> daoClass;
    private static Object dao;

    @BeforeAll
    static void resolverClases() throws Exception {
        String base = "com.bootcamp.nivel4_integracion.Ej32_ManejoErrores$";
        tareaClass = Class.forName(base + "Tarea");
        daoClass   = Class.forName(base + "DAOTarea");
        dao = daoClass.getDeclaredConstructor().newInstance();
        daoClass.getMethod("crearTabla").invoke(dao);
    }

    private Object nuevaTarea(String desc, int prio) throws Exception {
        return tareaClass.getDeclaredConstructor(String.class, int.class).newInstance(desc, prio);
    }

    @Test @Order(1) @DisplayName("insertar() devuelve true")
    void insertarOk() throws Exception {
        Object t = nuevaTarea("Revisar código", 1);
        boolean ok = (boolean) daoClass.getMethod("insertar", tareaClass).invoke(dao, t);
        assertTrue(ok);
    }

    @Test @Order(2) @DisplayName("obtenerTodas() devuelve lista no vacía")
    void obtenerTodasOk() throws Exception {
        daoClass.getMethod("insertar", tareaClass).invoke(dao, nuevaTarea("Escribir tests", 2));
        List<?> lista = (List<?>) daoClass.getMethod("obtenerTodas").invoke(dao);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(3) @DisplayName("eliminar() devuelve true para id existente")
    void eliminarOk() throws Exception {
        Object t = nuevaTarea("Para eliminar", 3);
        daoClass.getMethod("insertar", tareaClass).invoke(dao, t);
        List<?> todas = (List<?>) daoClass.getMethod("obtenerTodas").invoke(dao);
        int ultimoId = todas.stream()
                .mapToInt(o -> { try { return (int) o.getClass().getMethod("getId").invoke(o); } catch (Exception e) { return 0; } })
                .max().orElse(-1);
        boolean ok = (boolean) daoClass.getMethod("eliminar", int.class).invoke(dao, ultimoId);
        assertTrue(ok);
    }

    @Test @Order(4) @DisplayName("eliminar() devuelve false para id inexistente")
    void eliminarIdInexistente() throws Exception {
        boolean ok = (boolean) daoClass.getMethod("eliminar", int.class).invoke(dao, 99999);
        assertFalse(ok);
    }

    @Test @Order(5) @DisplayName("obtenerTodas() devuelve tareas ordenadas por prioridad ASC")
    void ordenadosPorPrioridad() throws Exception {
        daoClass.getMethod("insertar", tareaClass).invoke(dao, nuevaTarea("Urgente",  1));
        daoClass.getMethod("insertar", tareaClass).invoke(dao, nuevaTarea("Normal",   5));
        daoClass.getMethod("insertar", tareaClass).invoke(dao, nuevaTarea("Baja",    10));
        List<?> lista = (List<?>) daoClass.getMethod("obtenerTodas").invoke(dao);
        for (int i = 0; i < lista.size() - 1; i++) {
            int p1 = (int) lista.get(i).getClass().getMethod("getPrioridad").invoke(lista.get(i));
            int p2 = (int) lista.get(i + 1).getClass().getMethod("getPrioridad").invoke(lista.get(i + 1));
            assertTrue(p1 <= p2, "Prioridad " + p1 + " debe ser <= " + p2);
        }
    }
}
