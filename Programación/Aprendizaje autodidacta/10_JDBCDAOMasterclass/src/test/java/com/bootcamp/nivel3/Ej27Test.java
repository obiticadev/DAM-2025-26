package com.bootcamp.nivel3;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de validación del SpeedRun completo.
 * Comprueba que Cancion, Conexion y DAOCancion existen y funcionan
 * usando reflection para no acoplar el test antes de que el alumno
 * haya creado las clases.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej27Test {

    private static Class<?> cancionClass;
    private static Class<?> conexionClass;
    private static Class<?> daoClass;
    private static Object dao;

    @BeforeAll
    static void resolverClases() throws Exception {
        cancionClass  = Class.forName("com.bootcamp.nivel3_dao.Ej27_DAOCompleto_SpeedRun$Cancion");
        conexionClass = Class.forName("com.bootcamp.nivel3_dao.Ej27_DAOCompleto_SpeedRun$Conexion");
        daoClass      = Class.forName("com.bootcamp.nivel3_dao.Ej27_DAOCompleto_SpeedRun$DAOCancion");
        dao = daoClass.getDeclaredConstructor().newInstance();
        daoClass.getMethod("crearTabla").invoke(dao);
    }

    // ── Estructura ────────────────────────────────────────────────────────

    @Test @Order(1) @DisplayName("La clase Cancion existe")
    void cancionExiste() { assertNotNull(cancionClass); }

    @Test @Order(2) @DisplayName("La clase Conexion existe")
    void conexionExiste() { assertNotNull(conexionClass); }

    @Test @Order(3) @DisplayName("La clase DAOCancion existe")
    void daoExiste() { assertNotNull(daoClass); }

    @Test @Order(4)
    @DisplayName("Cancion tiene constructor sin id (4 params)")
    void cancionConstructorSinId() {
        assertDoesNotThrow(() ->
            cancionClass.getDeclaredConstructor(String.class, String.class, int.class, int.class)
        );
    }

    @Test @Order(5)
    @DisplayName("Cancion tiene constructor con id (5 params)")
    void cancionConstructorConId() {
        assertDoesNotThrow(() ->
            cancionClass.getDeclaredConstructor(int.class, String.class, String.class, int.class, int.class)
        );
    }

    // ── CRUD ─────────────────────────────────────────────────────────────

    private Object nuevaCancion(String titulo, String artista, int dur, int anio) throws Exception {
        return cancionClass.getDeclaredConstructor(String.class, String.class, int.class, int.class)
                .newInstance(titulo, artista, dur, anio);
    }

    @Test @Order(6)
    @DisplayName("crearTabla() no lanza excepción")
    void crearTablaOk() {
        assertDoesNotThrow(() -> daoClass.getMethod("crearTabla").invoke(dao));
    }

    @Test @Order(7)
    @DisplayName("insertar() devuelve true")
    void insertarDevuelveTrue() throws Exception {
        Object cancion = nuevaCancion("Bohemian Rhapsody", "Queen", 354, 1975);
        boolean result = (boolean) daoClass.getMethod("insertar", cancionClass).invoke(dao, cancion);
        assertTrue(result);
    }

    @Test @Order(8)
    @DisplayName("obtenerTodos() devuelve lista no null con al menos 1 elemento")
    void obtenerTodosOk() throws Exception {
        nuevaCancion("Stairway to Heaven", "Led Zeppelin", 482, 1971);
        daoClass.getMethod("insertar", cancionClass).invoke(dao, nuevaCancion("Hotel California", "Eagles", 391, 1977));
        List<?> lista = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(9)
    @DisplayName("obtenerPorId() con id existente no devuelve null")
    void obtenerPorIdExistente() throws Exception {
        List<?> todos = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        assertFalse(todos.isEmpty(), "Debe haber al menos una canción");
        int primerID = (int) todos.get(0).getClass().getMethod("getId").invoke(todos.get(0));
        Object encontrado = daoClass.getMethod("obtenerPorId", int.class).invoke(dao, primerID);
        assertNotNull(encontrado);
    }

    @Test @Order(10)
    @DisplayName("obtenerPorId() con id inexistente devuelve null")
    void obtenerPorIdInexistente() throws Exception {
        Object res = daoClass.getMethod("obtenerPorId", int.class).invoke(dao, 99999);
        assertNull(res);
    }

    @Test @Order(11)
    @DisplayName("actualizar() devuelve true para id existente")
    void actualizarOk() throws Exception {
        List<?> todos = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        assertFalse(todos.isEmpty());
        int id = (int) todos.get(0).getClass().getMethod("getId").invoke(todos.get(0));
        Object actualizado = cancionClass
                .getDeclaredConstructor(int.class, String.class, String.class, int.class, int.class)
                .newInstance(id, "Titulo Modificado", "Artista", 300, 2000);
        boolean result = (boolean) daoClass.getMethod("actualizar", cancionClass).invoke(dao, actualizado);
        assertTrue(result);
    }

    @Test @Order(12)
    @DisplayName("actualizar() devuelve false para id inexistente")
    void actualizarIdInexistente() throws Exception {
        Object fantasma = cancionClass
                .getDeclaredConstructor(int.class, String.class, String.class, int.class, int.class)
                .newInstance(99999, "X", "Y", 1, 2000);
        boolean result = (boolean) daoClass.getMethod("actualizar", cancionClass).invoke(dao, fantasma);
        assertFalse(result);
    }

    @Test @Order(13)
    @DisplayName("eliminar() devuelve true para id existente")
    void eliminarOk() throws Exception {
        Object c = nuevaCancion("Para Eliminar", "Test", 60, 2024);
        daoClass.getMethod("insertar", cancionClass).invoke(dao, c);
        List<?> todos = (List<?>) daoClass.getMethod("obtenerTodos").invoke(dao);
        int ultimoId = todos.stream()
                .mapToInt(o -> { try { return (int) o.getClass().getMethod("getId").invoke(o); } catch(Exception e){return 0;} })
                .max().orElse(-1);
        boolean result = (boolean) daoClass.getMethod("eliminar", int.class).invoke(dao, ultimoId);
        assertTrue(result);
    }

    @Test @Order(14)
    @DisplayName("eliminar() devuelve false para id inexistente")
    void eliminarIdInexistente() throws Exception {
        boolean result = (boolean) daoClass.getMethod("eliminar", int.class).invoke(dao, 99999);
        assertFalse(result);
    }
}
