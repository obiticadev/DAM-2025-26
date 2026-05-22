package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import org.h2.jdbcx.JdbcDataSource;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

class Ej100JdbcTemplateTest {

    private DataSource ds;

    @BeforeEach
    void setUp() throws Exception {
        var h2 = new JdbcDataSource();
        h2.setURL("jdbc:h2:mem:ej100;DB_CLOSE_DELAY=-1");
        h2.setUser("sa");
        ds = h2;
        try (var c = ds.getConnection()) {
            c.createStatement().execute("CREATE TABLE TAREA(id INT, titulo VARCHAR(50))");
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        try (var c = ds.getConnection()) {
            c.createStatement().execute("DROP ALL OBJECTS");
        }
    }

    @Test
    void insertarContarYLeer() {
        var t = new Ej100JdbcTemplate(ds);
        assertEquals(1, t.insertar(1, "comprar"));
        assertEquals(1, t.contar());
        assertEquals("comprar", t.tituloDe(1));
        assertNull(t.tituloDe(99));
    }

@Test
    void testDesafioObtenerSqlInsert() {
        assertEquals("INSERT INTO TAREA(id, descripcion, completada) VALUES (?, ?, ?)", Ej100JdbcTemplate.desafioObtenerSqlInsert());
    }

    @Test
    void testDesafioObtenerSqlSelectById() {
        assertEquals("SELECT id, descripcion, completada FROM TAREA WHERE id = ?", Ej100JdbcTemplate.desafioObtenerSqlSelectById());
    }

    @Test
    void testDesafioObtenerSqlCount() {
        assertEquals("SELECT COUNT(*) FROM TAREA", Ej100JdbcTemplate.desafioObtenerSqlCount());
    }

    @Test
    void testDesafioInsertarConTemplate() {
        var ds = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:h2:mem:test100_1;DB_CLOSE_DELAY=-1", "sa", "");
        var jt = new org.springframework.jdbc.core.JdbcTemplate(ds);
        jt.execute("CREATE TABLE TAREA(id INT, descripcion VARCHAR, completada BOOLEAN)");
        assertEquals(1, Ej100JdbcTemplate.desafioInsertarConTemplate(jt, 1, "test", false));
    }

    @Test
    void testDesafioTemplateActivo() {
        assertTrue(Ej100JdbcTemplate.desafioTemplateActivo(new org.springframework.jdbc.core.JdbcTemplate()));
        assertFalse(Ej100JdbcTemplate.desafioTemplateActivo(null));
    }

    @Test
    void testDesafioContarTareasConTemplate() {
        var ds = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:h2:mem:test100_2;DB_CLOSE_DELAY=-1", "sa", "");
        var jt = new org.springframework.jdbc.core.JdbcTemplate(ds);
        jt.execute("CREATE TABLE TAREA(id INT, descripcion VARCHAR, completada BOOLEAN)");
        jt.execute("INSERT INTO TAREA VALUES (1, 't', false)");
        assertEquals(1, Ej100JdbcTemplate.desafioContarTareasConTemplate(jt));
    }

    @Test
    void testDesafioValidarId() {
        assertThrows(IllegalArgumentException.class, () -> Ej100JdbcTemplate.desafioValidarId(0));
        assertDoesNotThrow(() -> Ej100JdbcTemplate.desafioValidarId(1));
    }

    @Test
    void testDesafioTieneTareas() {
        assertTrue(Ej100JdbcTemplate.desafioTieneTareas(5));
        assertFalse(Ej100JdbcTemplate.desafioTieneTareas(0));
    }

    @Test
    void testDesafioPreconfigurarParametros() {
        assertArrayEquals(new Object[]{1, "d", true}, Ej100JdbcTemplate.desafioPreconfigurarParametros(1, "d", true));
    }

    @Test
    void testDesafioDescripcionValida() {
        assertTrue(Ej100JdbcTemplate.desafioDescripcionValida("a"));
        assertFalse(Ej100JdbcTemplate.desafioDescripcionValida(" "));
    }
}
