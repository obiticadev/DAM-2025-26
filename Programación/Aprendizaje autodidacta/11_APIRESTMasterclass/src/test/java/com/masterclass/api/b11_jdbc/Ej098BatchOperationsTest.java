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
}
