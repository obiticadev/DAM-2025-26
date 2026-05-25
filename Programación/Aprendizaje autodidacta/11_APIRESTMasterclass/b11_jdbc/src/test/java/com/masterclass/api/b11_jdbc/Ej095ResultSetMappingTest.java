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

@Test
    void testDesafioObtenerSqlSelect() {
        assertEquals("SELECT id,nombre,precio FROM PRODUCTO ORDER BY id", Ej095ResultSetMapping.desafioObtenerSqlSelect());
    }

    @Test
    void testDesafioTieneMasFilas() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test095_1", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE)");
                s.execute("INSERT INTO PRODUCTO VALUES (1, 'A', 10.0)");
            }
            try (var ps = c.prepareStatement("SELECT * FROM PRODUCTO")) {
                try (var rs = ps.executeQuery()) {
                    assertTrue(Ej095ResultSetMapping.desafioTieneMasFilas(rs));
                    assertFalse(Ej095ResultSetMapping.desafioTieneMasFilas(rs));
                }
            }
        }
    }

    @Test
    void testDesafioObtenerId() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test095_2", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE)");
                s.execute("INSERT INTO PRODUCTO VALUES (10, 'A', 10.0)");
            }
            try (var ps = c.prepareStatement("SELECT * FROM PRODUCTO")) {
                try (var rs = ps.executeQuery()) {
                    rs.next();
                    assertEquals(10, Ej095ResultSetMapping.desafioObtenerId(rs));
                }
            }
        }
    }

    @Test
    void testDesafioObtenerNombre() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test095_3", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE)");
                s.execute("INSERT INTO PRODUCTO VALUES (10, 'Tablet', 10.0)");
            }
            try (var ps = c.prepareStatement("SELECT * FROM PRODUCTO")) {
                try (var rs = ps.executeQuery()) {
                    rs.next();
                    assertEquals("Tablet", Ej095ResultSetMapping.desafioObtenerNombre(rs));
                }
            }
        }
    }

    @Test
    void testDesafioObtenerPrecio() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test095_4", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE)");
                s.execute("INSERT INTO PRODUCTO VALUES (10, 'Tablet', 150.5)");
            }
            try (var ps = c.prepareStatement("SELECT * FROM PRODUCTO")) {
                try (var rs = ps.executeQuery()) {
                    rs.next();
                    assertEquals(150.5, Ej095ResultSetMapping.desafioObtenerPrecio(rs), 0.001);
                }
            }
        }
    }

    @Test
    void testDesafioInstanciarProducto() {
        var p = Ej095ResultSetMapping.desafioInstanciarProducto(1, "A", 10.0);
        assertEquals(1, p.id());
        assertEquals("A", p.nombre());
        assertEquals(10.0, p.precio(), 0.001);
    }

    @Test
    void testDesafioAgregarALista() {
        var list = new java.util.ArrayList<Producto>();
        var p = new Producto(1, "A", 10.0);
        Ej095ResultSetMapping.desafioAgregarALista(list, p);
        assertEquals(1, list.size());
    }

    @Test
    void testDesafioValidarListaDeSalida() {
        assertThrows(IllegalArgumentException.class, () -> Ej095ResultSetMapping.desafioValidarListaDeSalida(null));
        assertDoesNotThrow(() -> Ej095ResultSetMapping.desafioValidarListaDeSalida(new java.util.ArrayList<>()));
    }

    @Test
    void testDesafioMapearInmodificable() {
        var list = new java.util.ArrayList<Producto>();
        list.add(new Producto(1, "A", 10.0));
        var immutable = Ej095ResultSetMapping.desafioMapearInmodificable(list);
        assertThrows(UnsupportedOperationException.class, () -> immutable.add(new Producto(2, "B", 20.0)));
    }

    @Test
    void testDesafioFueNulo() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test095_5", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE)");
                s.execute("INSERT INTO PRODUCTO VALUES (10, 'A', NULL)");
            }
            try (var ps = c.prepareStatement("SELECT * FROM PRODUCTO")) {
                try (var rs = ps.executeQuery()) {
                    rs.next();
                    rs.getDouble("precio");
                    assertTrue(Ej095ResultSetMapping.desafioFueNulo(rs));
                }
            }
        }
    }
}
