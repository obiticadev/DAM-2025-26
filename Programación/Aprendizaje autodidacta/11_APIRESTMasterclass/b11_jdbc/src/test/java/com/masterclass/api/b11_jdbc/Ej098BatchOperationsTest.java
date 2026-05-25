package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej098BatchOperationsTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej098;DB_CLOSE_DELAY=-1", "sa", "");
        c.createStatement().execute("CREATE TABLE LOG(id INT, msg VARCHAR(50))");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void insertaLote() throws Exception {
        int n = Ej098BatchOperations.insertarLote(c, List.of("a", "b", "c"));
        assertEquals(3, n);
        var rs = c.createStatement().executeQuery("SELECT COUNT(*) FROM LOG");
        rs.next();
        assertEquals(3, rs.getInt(1));
    }

    @Test
    void loteVacio() throws Exception {
        assertEquals(0, Ej098BatchOperations.insertarLote(c, List.of()));
    }

@Test
    void testDesafioObtenerSqlInsertBatch() {
        assertEquals("INSERT INTO REGISTRO(id, texto) VALUES (?, ?)", Ej098BatchOperations.desafioObtenerSqlInsertBatch());
    }

    @Test
    void testDesafioAñadirAlLote() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test098_1", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE REGISTRO(id INT, texto VARCHAR)");
            }
            try (var ps = c.prepareStatement("INSERT INTO REGISTRO VALUES (?, ?)")) {
                assertDoesNotThrow(() -> Ej098BatchOperations.desafioAñadirAlLote(ps, 1, "test"));
            }
        }
    }

    @Test
    void testDesafioEjecutarLote() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test098_2", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE REGISTRO(id INT, texto VARCHAR)");
            }
            try (var ps = c.prepareStatement("INSERT INTO REGISTRO VALUES (?, ?)")) {
                ps.setInt(1, 1);
                ps.setString(2, "a");
                ps.addBatch();
                int[] res = Ej098BatchOperations.desafioEjecutarLote(ps);
                assertEquals(1, res.length);
            }
        }
    }

    @Test
    void testDesafioValidarTamañoDatos() {
        assertTrue(Ej098BatchOperations.desafioValidarTamañoDatos(List.of("a")));
        assertFalse(Ej098BatchOperations.desafioValidarTamañoDatos(List.of()));
    }

    @Test
    void testDesafioVerificarExitoLote() {
        assertTrue(Ej098BatchOperations.desafioVerificarExitoLote(new int[]{1, 2, java.sql.Statement.SUCCESS_NO_INFO}));
        assertFalse(Ej098BatchOperations.desafioVerificarExitoLote(new int[]{1, java.sql.Statement.EXECUTE_FAILED}));
    }

    @Test
    void testDesafioSumarFilasAfectadas() {
        assertEquals(3, Ej098BatchOperations.desafioSumarFilasAfectadas(new int[]{1, 2}));
    }

    @Test
    void testDesafioValidarSinFallosLote() {
        assertThrows(IllegalStateException.class, () -> Ej098BatchOperations.desafioValidarSinFallosLote(new int[]{java.sql.Statement.EXECUTE_FAILED}));
    }

    @Test
    void testDesafioLimpiarLote() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test098_3", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE REGISTRO(id INT, texto VARCHAR)");
            }
            try (var ps = c.prepareStatement("INSERT INTO REGISTRO VALUES (?, ?)")) {
                ps.setInt(1, 1);
                ps.setString(2, "a");
                ps.addBatch();
                assertDoesNotThrow(() -> Ej098BatchOperations.desafioLimpiarLote(ps));
            }
        }
    }

    @Test
    void testDesafioEsLimiteLote() {
        assertTrue(Ej098BatchOperations.desafioEsLimiteLote(10, 5));
        assertFalse(Ej098BatchOperations.desafioEsLimiteLote(8, 5));
    }

    @Test
    void testDesafioResumirEjecucionLote() {
        assertEquals("Lote ejecutado. Filas: 2", Ej098BatchOperations.desafioResumirEjecucionLote(new int[]{1, 1}));
    }
}
