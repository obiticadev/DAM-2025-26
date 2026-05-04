package com.bootcamp.nivel4;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de validación del SpeedRun de examen — Ej33.
 * Comprueba que Pelicula, Conexion y DAOPelicula existen y el CRUD completo funciona.
 * Usa reflection porque las tres clases son TODOs del alumno.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej33Test {

    private static Class<?> peliculaClass;
    private static Class<?> conexionClass;
    private static Class<?> daoClass;
    private static Object dao;

    @BeforeAll
    static void resolverClases() throws Exception {
        String base = "com.bootcamp.nivel4_integracion.Ej33_SpeedRun_Examen$";
        peliculaClass = Class.forName(base + "Pelicula");
        conexionClass = Class.forName(base + "Conexion");
        daoClass      = Class.forName(base + "DAOPelicula");
        dao = daoClass.getDeclaredConstructor().newInstance();
        daoClass.getMethod("crearTabla").invoke(dao);
    }

    private Object nuevaPelicula(String titulo, String director, int anio, int dur) throws Exception {
        return peliculaClass
                .getDeclaredConstructor(String.class, String.class, int.class, int.class)
                .newInstance(titulo, director, anio, dur);
    }

    // ── Estructura ────────────────────────────────────────────────────────

    @Test @Order(1) @DisplayName("La clase Pelicula existe")
    void peliculaExiste() { assertNotNull(peliculaClass); }

    @Test @Order(2) @DisplayName("La clase Conexion existe")
    void conexionExiste() { assertNotNull(conexionClass); }

    @Test @Order(3) @DisplayName("La clase DAOPelicula existe")
    void daoExiste() { assertNotNull(daoClass); }

    @Test @Order(4) @DisplayName("Pelicula tiene constructor sin id (4 params)")
    void constructorSinId() {
        assertDoesNotThrow(() ->
            peliculaClass.getDeclaredConstructor(String.class, String.class, int.class, int.class)
        );
    }

    @Test @Order(5) @DisplayName("Pelicula tiene constructor con id (5 params)")
    void constructorConId() {
        assertDoesNotThrow(() ->
            peliculaClass.getDeclaredConstructor(int.class, String.class, String.class, int.class, int.class)
        );
    }

    @Test @Order(6) @DisplayName("Conexion.getConexion() no devuelve null")
    void getConexionNoNull() throws Exception {
        Object conn = conexionClass.getMethod("getConexion").invoke(null);
        assertNotNull(conn);
    }

    // ── CRUD ─────────────────────────────────────────────────────────────

    @Test @Order(7) @DisplayName("crearTabla() no lanza excepción")
    void crearTablaOk() {
        assertDoesNotThrow(() -> daoClass.getMethod("crearTabla").invoke(dao));
    }

    @Test @Order(8) @DisplayName("insertar() devuelve true")
    void insertarOk() throws Exception {
        Object p = nuevaPelicula("El Padrino", "Coppola", 1972, 175);
        boolean ok = (boolean) daoClass.getMethod("insertar", peliculaClass).invoke(dao, p);
        assertTrue(ok);
    }

    @Test @Order(9) @DisplayName("obtenerTodos() devuelve lista no vacía")
    void obtenerTodosOk() throws Exception {
        daoClass.getMethod("insertar", peliculaClass).invoke(dao, nuevaPelicula("Inception", "Nolan", 2010, 148));
        List<?> lista = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(10) @DisplayName("obtenerPorId() con id existente no devuelve null")
    void obtenerPorIdExistente() throws Exception {
        List<?> todos = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        int primerID = (int) todos.get(0).getClass().getMethod("getId").invoke(todos.get(0));
        Object encontrado = daoClass.getMethod("obtenerPorId", int.class).invoke(dao, primerID);
        assertNotNull(encontrado);
    }

    @Test @Order(11) @DisplayName("obtenerPorId() con id inexistente devuelve null")
    void obtenerPorIdInexistente() throws Exception {
        Object res = daoClass.getMethod("obtenerPorId", int.class).invoke(dao, 99999);
        assertNull(res);
    }

    @Test @Order(12) @DisplayName("actualizar() devuelve true para id existente")
    void actualizarOk() throws Exception {
        List<?> todos = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        int id = (int) todos.get(0).getClass().getMethod("getId").invoke(todos.get(0));
        Object actualizado = peliculaClass
                .getDeclaredConstructor(int.class, String.class, String.class, int.class, int.class)
                .newInstance(id, "Titulo Modificado", "Director Test", 2000, 120);
        boolean ok = (boolean) daoClass.getMethod("actualizar", peliculaClass).invoke(dao, actualizado);
        assertTrue(ok);
    }

    @Test @Order(13) @DisplayName("actualizar() devuelve false para id inexistente")
    void actualizarIdInexistente() throws Exception {
        Object fantasma = peliculaClass
                .getDeclaredConstructor(int.class, String.class, String.class, int.class, int.class)
                .newInstance(99999, "X", "Y", 2000, 90);
        boolean ok = (boolean) daoClass.getMethod("actualizar", peliculaClass).invoke(dao, fantasma);
        assertFalse(ok);
    }

    @Test @Order(14) @DisplayName("eliminar() devuelve true para id existente")
    void eliminarOk() throws Exception {
        daoClass.getMethod("insertar", peliculaClass).invoke(dao, nuevaPelicula("Para Eliminar", "Test", 2024, 60));
        List<?> todos = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        int ultimoId = todos.stream()
                .mapToInt(o -> { try { return (int) o.getClass().getMethod("getId").invoke(o); } catch (Exception e) { return 0; } })
                .max().orElse(-1);
        boolean ok = (boolean) daoClass.getMethod("eliminar", int.class).invoke(dao, ultimoId);
        assertTrue(ok);
    }

    @Test @Order(15) @DisplayName("eliminar() devuelve false para id inexistente")
    void eliminarIdInexistente() throws Exception {
        boolean ok = (boolean) daoClass.getMethod("eliminar", int.class).invoke(dao, 99999);
        assertFalse(ok);
    }
}
