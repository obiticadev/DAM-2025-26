package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej094StatementVsPreparedTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej094;DB_CLOSE_DELAY=-1", "sa", "");
        c.createStatement().execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR(100))");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void insertSeguroResisteComillas() throws Exception {
        Ej094StatementVsPrepared.insertarSeguro(c, 1, "O'Brien");
        Ej094StatementVsPrepared.insertarSeguro(c, 2, "Ana");
        assertEquals(1, Ej094StatementVsPrepared.contarPorNombre(c, "O'Brien"));
        assertEquals(0, Ej094StatementVsPrepared.contarPorNombre(c, "noexiste"));
    }

@Test
    void testDesafioObtenerSqlInsert() {
        assertEquals("INSERT INTO USUARIO(id,nombre) VALUES (?,?)", Ej094StatementVsPrepared.desafioObtenerSqlInsert());
    }

    @Test
    void testDesafioEvitarConcatenacionSql() {
        assertEquals("'O''Reilly'", Ej094StatementVsPrepared.desafioEvitarConcatenacionSql("O'Reilly"));
    }

    @Test
    void testDesafioCrearPreparedStatement() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test094", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR)");
            }
            try (var ps = Ej094StatementVsPrepared.desafioCrearPreparedStatement(c, "INSERT INTO USUARIO(id,nombre) VALUES (?,?)")) {
                assertNotNull(ps);
            }
        }
    }

    @Test
    void testDesafioEstablecerIdParam() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test094_2", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR)");
            }
            try (var ps = c.prepareStatement("INSERT INTO USUARIO(id,nombre) VALUES (?,?)")) {
                assertDoesNotThrow(() -> Ej094StatementVsPrepared.desafioEstablecerIdParam(ps, 1));
            }
        }
    }

    @Test
    void testDesafioEstablecerNombreParam() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test094_3", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR)");
            }
            try (var ps = c.prepareStatement("INSERT INTO USUARIO(id,nombre) VALUES (?,?)")) {
                assertDoesNotThrow(() -> Ej094StatementVsPrepared.desafioEstablecerNombreParam(ps, "Ana"));
            }
        }
    }

    @Test
    void testDesafioEjecutarUpdate() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test094_4", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR)");
            }
            try (var ps = c.prepareStatement("INSERT INTO USUARIO(id,nombre) VALUES (?,?)")) {
                ps.setInt(1, 1);
                ps.setString(2, "Ana");
                assertEquals(1, Ej094StatementVsPrepared.desafioEjecutarUpdate(ps));
            }
        }
    }

    @Test
    void testDesafioVerificarCierreStatement() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test094_5", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR)");
            }
            var ps = c.prepareStatement("INSERT INTO USUARIO(id,nombre) VALUES (?,?)");
            assertFalse(Ej094StatementVsPrepared.desafioVerificarCierreStatement(ps));
            ps.close();
            assertTrue(Ej094StatementVsPrepared.desafioVerificarCierreStatement(ps));
        }
    }

    @Test
    void testDesafioObtenerSqlSelectCount() {
        assertEquals("SELECT COUNT(*) FROM USUARIO WHERE nombre = ?", Ej094StatementVsPrepared.desafioObtenerSqlSelectCount());
    }

    @Test
    void testDesafioMapearCountResultSet() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test094_6", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR)");
                s.execute("INSERT INTO USUARIO VALUES (1, 'Ana')");
            }
            try (var ps = c.prepareStatement("SELECT COUNT(*) FROM USUARIO")) {
                try (var rs = ps.executeQuery()) {
                    assertEquals(1, Ej094StatementVsPrepared.desafioMapearCountResultSet(rs));
                }
            }
        }
    }

    @Test
    void testDesafioContarSeguroLocal() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test094_7", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE USUARIO(id INT, nombre VARCHAR)");
                s.execute("INSERT INTO USUARIO VALUES (1, 'Ana')");
            }
            assertEquals(1, Ej094StatementVsPrepared.desafioContarSeguroLocal(c, "Ana"));
            assertEquals(0, Ej094StatementVsPrepared.desafioContarSeguroLocal(c, "Inexistente"));
        }
    }
}
