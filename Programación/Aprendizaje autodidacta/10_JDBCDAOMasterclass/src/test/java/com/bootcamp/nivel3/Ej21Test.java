package com.bootcamp.nivel3;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida que Conexion y DAOVideojuego existen dentro de Ej21_DAO_CrearTabla
 * y que crearTabla() funciona correctamente.
 * Usa reflection para no acoplar el test antes de que el alumno cree las clases.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej21Test {

    private static Class<?> conexionClass;
    private static Class<?> daoClass;
    private static Object dao;

    @BeforeAll
    static void resolverClases() throws Exception {
        conexionClass = Class.forName("com.bootcamp.nivel3_dao.Ej21_DAO_CrearTabla$Conexion");
        daoClass      = Class.forName("com.bootcamp.nivel3_dao.Ej21_DAO_CrearTabla$DAOVideojuego");
        dao = daoClass.getDeclaredConstructor().newInstance();
    }

    @Test
    @Order(1)
    @DisplayName("La clase Conexion existe dentro de Ej21_DAO_CrearTabla")
    void conexionExiste() {
        assertNotNull(conexionClass);
    }

    @Test
    @Order(2)
    @DisplayName("La clase DAOVideojuego existe dentro de Ej21_DAO_CrearTabla")
    void daoExiste() {
        assertNotNull(daoClass);
    }

    @Test
    @Order(3)
    @DisplayName("Conexion tiene el método getConexion()")
    void getConexionExiste() {
        assertDoesNotThrow(() -> conexionClass.getMethod("getConexion"));
    }

    @Test
    @Order(4)
    @DisplayName("Conexion tiene el método cerrarConexion()")
    void cerrarConexionExiste() {
        assertDoesNotThrow(() -> conexionClass.getMethod("cerrarConexion"));
    }

    @Test
    @Order(5)
    @DisplayName("getConexion() no devuelve null")
    void getConexionNoNull() throws Exception {
        Object conn = conexionClass.getMethod("getConexion").invoke(null);
        assertNotNull(conn, "getConexion() debe devolver una Connection, no null");
    }

    @Test
    @Order(6)
    @DisplayName("DAOVideojuego tiene el método crearTabla()")
    void crearTablaExiste() {
        assertDoesNotThrow(() -> daoClass.getMethod("crearTabla"));
    }

    @Test
    @Order(7)
    @DisplayName("crearTabla() no lanza excepción")
    void crearTablaNoLanza() {
        assertDoesNotThrow(() -> daoClass.getMethod("crearTabla").invoke(dao));
    }

    @Test
    @Order(8)
    @DisplayName("crearTabla() es idempotente (IF NOT EXISTS)")
    void crearTablaIdempotente() {
        assertDoesNotThrow(() -> {
            daoClass.getMethod("crearTabla").invoke(dao);
            daoClass.getMethod("crearTabla").invoke(dao);
        });
    }

    @Test
    @Order(9)
    @DisplayName("Tras crearTabla(), la tabla Videojuegos existe en sqlite_master")
    void tablaExisteEnSqliteMaster() throws Exception {
        daoClass.getMethod("crearTabla").invoke(dao);
        Object conn = conexionClass.getMethod("getConexion").invoke(null);
        // conn es java.sql.Connection — usamos su API directamente
        java.sql.Connection c = (java.sql.Connection) conn;
        String check = "SELECT name FROM sqlite_master WHERE type='table' AND name='Videojuegos'";
        try (java.sql.Statement s = c.createStatement();
             java.sql.ResultSet rs = s.executeQuery(check)) {
            assertTrue(rs.next(), "La tabla Videojuegos debe existir en sqlite_master");
        }
    }
}
