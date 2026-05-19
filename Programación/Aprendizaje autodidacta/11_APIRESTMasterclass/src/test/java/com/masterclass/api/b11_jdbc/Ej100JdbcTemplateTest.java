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
}
