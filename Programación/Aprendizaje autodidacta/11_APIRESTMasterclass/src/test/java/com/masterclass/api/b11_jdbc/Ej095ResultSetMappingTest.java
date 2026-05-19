package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej095ResultSetMappingTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej095;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR(50), precio DOUBLE)");
        st.execute("INSERT INTO PRODUCTO VALUES (2,'b',20.0),(1,'a',10.0)");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void listaOrdenadaPorId() throws Exception {
        var l = Ej095ResultSetMapping.listar(c);
        assertEquals(2, l.size());
        assertEquals(1, l.get(0).id());
        assertEquals("b", l.get(1).nombre());
        assertEquals(20.0, l.get(1).precio(), 0.0001);
    }
}
