package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.Test;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

class Ej099ConnectionPoolingTest {

    @Test
    void poolReutilizaConexiones() throws Exception {
        DataSource ds = Ej099ConnectionPooling.crearPool(
                "jdbc:h2:mem:ej099;DB_CLOSE_DELAY=-1", 3);
        assertNotNull(ds);
        assertTrue(Ej099ConnectionPooling.usarYDevolver(ds, 10),
                "pedir 10 conexiones de un pool de 3 debe funcionar reutilizándolas");
    }

    @Test
    void poolInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej099ConnectionPooling.crearPool("jdbc:h2:mem:x", 0));
    }

@Test
    void testDesafioDataSourceNoNulo() {
        assertTrue(Ej099ConnectionPooling.desafioDataSourceNoNulo(Ej099ConnectionPooling.crearPool("jdbc:h2:mem:test099", "sa", "")));
    }

    @Test
    void testDesafioValidarConexionPool() {
        var ds = Ej099ConnectionPooling.crearPool("jdbc:h2:mem:test099_2", "sa", "");
        assertTrue(Ej099ConnectionPooling.desafioValidarConexionPool(ds));
    }

    @Test
    void testDesafioMinIdleRecomendado() {
        assertEquals(5, Ej099ConnectionPooling.desafioMinIdleRecomendado());
    }

    @Test
    void testDesafioMaxConnectionsRecomendado() {
        assertEquals(10, Ej099ConnectionPooling.desafioMaxConnectionsRecomendado());
    }

    @Test
    void testDesafioEsHikariDataSource() {
        var ds = Ej099ConnectionPooling.crearPool("jdbc:h2:mem:test099_3", "sa", "");
        assertTrue(Ej099ConnectionPooling.desafioEsHikariDataSource(ds));
    }

    @Test
    void testDesafioConnectionTimeoutRecomendado() {
        assertEquals(30000L, Ej099ConnectionPooling.desafioConnectionTimeoutRecomendado());
    }

    @Test
    void testDesafioVerificarUrlConexion() throws Exception {
        var ds = Ej099ConnectionPooling.crearPool("jdbc:h2:mem:test099_4", "sa", "");
        try (var c = ds.getConnection()) {
            assertTrue(Ej099ConnectionPooling.desafioVerificarUrlConexion(c));
        }
    }

    @Test
    void testDesafioLiberarConexion() throws Exception {
        var ds = Ej099ConnectionPooling.crearPool("jdbc:h2:mem:test099_5", "sa", "");
        var c = ds.getConnection();
        assertFalse(c.isClosed());
        Ej099ConnectionPooling.desafioLiberarConexion(c);
        assertTrue(c.isClosed());
    }

    @Test
    void testDesafioTestearPool() {
        var ds = Ej099ConnectionPooling.crearPool("jdbc:h2:mem:test099_6", "sa", "");
        assertTrue(Ej099ConnectionPooling.desafioTestearPool(ds, 3));
    }

    @Test
    void testDesafioResumirPool() {
        var ds = Ej099ConnectionPooling.crearPool("jdbc:h2:mem:test099_7", "sa", "");
        assertNotNull(Ej099ConnectionPooling.desafioResumirPool(ds));
        assertEquals("Pool desactivado", Ej099ConnectionPooling.desafioResumirPool(null));
    }
}
