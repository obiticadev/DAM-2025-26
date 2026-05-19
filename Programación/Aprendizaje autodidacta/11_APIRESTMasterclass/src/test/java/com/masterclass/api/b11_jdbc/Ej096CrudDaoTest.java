package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej096CrudDaoTest {

    private Connection c;
    private Ej096CrudDao dao;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej096;DB_CLOSE_DELAY=-1", "sa", "");
        c.createStatement().execute("CREATE TABLE CLIENTE(id INT PRIMARY KEY, nombre VARCHAR(50))");
        dao = new Ej096CrudDao(c);
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void cicloCrud() throws Exception {
        dao.crear(1, "Ana");
        assertEquals("Ana", dao.leerNombre(1));
        assertTrue(dao.actualizar(1, "Bea"));
        assertEquals("Bea", dao.leerNombre(1));
        assertTrue(dao.borrar(1));
        assertNull(dao.leerNombre(1));
        assertFalse(dao.borrar(99));
    }
}
