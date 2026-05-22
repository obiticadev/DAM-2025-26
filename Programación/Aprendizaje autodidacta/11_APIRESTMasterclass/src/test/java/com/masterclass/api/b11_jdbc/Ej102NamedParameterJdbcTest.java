package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import org.h2.jdbcx.JdbcDataSource;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

class Ej102NamedParameterJdbcTest {

    private DataSource ds;

    @BeforeEach
    void setUp() throws Exception {
        var h2 = new JdbcDataSource();
        h2.setURL("jdbc:h2:mem:ej102;DB_CLOSE_DELAY=-1");
        h2.setUser("sa");
        ds = h2;
        try (var c = ds.getConnection()) {
            c.createStatement().execute("CREATE TABLE EVENTO(id INT, tipo VARCHAR(30))");
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        try (var c = ds.getConnection()) {
            c.createStatement().execute("DROP ALL OBJECTS");
        }
    }

    @Test
    void parametrosConNombre() {
        var e = new Ej102NamedParameterJdbc(ds);
        assertEquals(1, e.insertar(1, "LOGIN"));
        e.insertar(2, "LOGIN");
        e.insertar(3, "LOGOUT");
        assertEquals(2, e.contarPorTipo("LOGIN"));
        assertEquals(1, e.contarPorTipo("LOGOUT"));
        assertEquals(0, e.contarPorTipo("X"));
    }

@Test
    void testDesafioObtenerSqlInsert() {
        assertEquals("INSERT INTO CLIENTE(id, nombre) VALUES (:id, :nombre)", Ej102NamedParameterJdbc.desafioObtenerSqlInsert());
    }

    @Test
    void testDesafioObtenerSqlSelect() {
        assertEquals("SELECT nombre FROM CLIENTE WHERE id = :id", Ej102NamedParameterJdbc.desafioObtenerSqlSelect());
    }

    @Test
    void testDesafioParameterSourceActivo() {
        assertTrue(Ej102NamedParameterJdbc.desafioParameterSourceActivo(new org.springframework.jdbc.core.namedparam.MapSqlParameterSource()));
        assertFalse(Ej102NamedParameterJdbc.desafioParameterSourceActivo(null));
    }

    @Test
    void testDesafioCrearSourceConId() {
        var source = Ej102NamedParameterJdbc.desafioCrearSourceConId(10);
        assertEquals(10, source.getValue("id"));
    }

    @Test
    void testDesafioAñadirNombre() {
        var source = new org.springframework.jdbc.core.namedparam.MapSqlParameterSource();
        Ej102NamedParameterJdbc.desafioAñadirNombre(source, "Ana");
        assertEquals("Ana", source.getValue("nombre"));
    }

    @Test
    void testDesafioNamedTemplateActivo() {
        var ds = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        assertTrue(Ej102NamedParameterJdbc.desafioNamedTemplateActivo(new org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate(ds)));
        assertFalse(Ej102NamedParameterJdbc.desafioNamedTemplateActivo(null));
    }

    @Test
    void testDesafioValidarNombreParam() {
        assertThrows(IllegalArgumentException.class, () -> Ej102NamedParameterJdbc.desafioValidarNombreParam(null));
        assertDoesNotThrow(() -> Ej102NamedParameterJdbc.desafioValidarNombreParam("Ana"));
    }

    @Test
    void testDesafioContieneParametro() {
        assertTrue(Ej102NamedParameterJdbc.desafioContieneParametro(Map.of("id", 1), "id"));
        assertFalse(Ej102NamedParameterJdbc.desafioContieneParametro(Map.of("id", 1), "nombre"));
    }

    @Test
    void testDesafioCrearMapParametros() {
        var map = Ej102NamedParameterJdbc.desafioCrearMapParametros(1, "Ana");
        assertEquals(1, map.get("id"));
        assertEquals("Ana", map.get("nombre"));
    }

    @Test
    void testDesafioTieneParametros() {
        var source = new org.springframework.jdbc.core.namedparam.MapSqlParameterSource("id", 1);
        assertTrue(Ej102NamedParameterJdbc.desafioTieneParametros(source));
    }
}
