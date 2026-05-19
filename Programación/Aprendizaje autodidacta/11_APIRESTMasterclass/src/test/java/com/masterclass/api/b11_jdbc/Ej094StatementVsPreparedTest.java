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
}
