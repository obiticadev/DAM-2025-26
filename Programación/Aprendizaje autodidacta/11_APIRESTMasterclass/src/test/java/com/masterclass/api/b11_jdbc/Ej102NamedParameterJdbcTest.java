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
}
