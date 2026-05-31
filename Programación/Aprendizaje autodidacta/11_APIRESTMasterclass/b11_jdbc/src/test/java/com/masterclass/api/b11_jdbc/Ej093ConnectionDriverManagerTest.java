package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej093ConnectionDriverManagerTest {

    @Test
    void conectaH2EnMemoria() throws Exception {
        assertTrue(Ej093ConnectionDriverManager.conectaYValida("jdbc:h2:mem:ej093", "sa", ""));
    }

@Test
    void testDesafioValidarUrl() {
        assertTrue(Ej093ConnectionDriverManager.desafioValidarUrl("jdbc:h2:mem:test"));
        assertFalse(Ej093ConnectionDriverManager.desafioValidarUrl("http://localhost"));
    }

    @Test
    void testDesafioObtenerCredencialesPorDefecto() {
        assertArrayEquals(new String[]{"sa", ""}, Ej093ConnectionDriverManager.desafioObtenerCredencialesPorDefecto());
    }

    @Test
    void testDesafioValidarConexionH2() {
        assertTrue(Ej093ConnectionDriverManager.desafioValidarConexionH2("jdbc:h2:mem:test093", "sa", ""));
    }

    @Test
    void testDesafioVerificarEstadoCerrada() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test093", "sa", "")) {
            assertFalse(Ej093ConnectionDriverManager.desafioVerificarEstadoCerrada(c));
        }
    }

    @Test
    void testDesafioLanzarErrorSql() {
        assertThrows(java.sql.SQLException.class, () -> Ej093ConnectionDriverManager.desafioLanzarErrorSql());
    }

    @Test
    void testDesafioComprobarTimeoutValido() {
        assertTrue(Ej093ConnectionDriverManager.desafioComprobarTimeoutValido(5));
        assertFalse(Ej093ConnectionDriverManager.desafioComprobarTimeoutValido(0));
    }

    @Test
    void testDesafioPropagarExcepcionConMensaje() {
        var e = new java.sql.SQLException("Demo error");
        assertEquals("Demo error", Ej093ConnectionDriverManager.desafioPropagarExcepcionConMensaje(e));
    }

    @Test
    void testDesafioSimularCierreAutomatico() {
        var flag = new boolean[]{false};
        AutoCloseable mock = () -> flag[0] = true;
        assertTrue(Ej093ConnectionDriverManager.desafioSimularCierreAutomatico(mock));
        assertTrue(flag[0]);
    }

    @Test
    void testDesafioEsConexionValidaConTimeout() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test093", "sa", "")) {
            assertTrue(Ej093ConnectionDriverManager.desafioEsConexionValidaConTimeout(c, 2));
        }
    }

    @Test
    void testDesafioEsConexionAbierta() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test093", "sa", "")) {
            assertTrue(Ej093ConnectionDriverManager.desafioEsConexionAbierta(c));
        }
    }
}
