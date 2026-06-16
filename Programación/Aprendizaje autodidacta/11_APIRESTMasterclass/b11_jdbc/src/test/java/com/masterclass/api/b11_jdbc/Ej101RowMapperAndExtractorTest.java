package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import org.h2.jdbcx.JdbcDataSource;
import javax.sql.DataSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej101RowMapperAndExtractorTest {

    private DataSource ds;

    @BeforeEach
    void setUp() throws Exception {
        var h2 = new JdbcDataSource();
        h2.setURL("jdbc:h2:mem:ej101;DB_CLOSE_DELAY=-1");
        h2.setUser("sa");
        ds = h2;
        try (var c = ds.getConnection()) {
            var st = c.createStatement();
            st.execute("CREATE TABLE LIBRO(id INT, titulo VARCHAR(50), paginas INT)");
            st.execute("INSERT INTO LIBRO VALUES (1,'DDD',300),(2,'Clean',200)");
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        try (var c = ds.getConnection()) {
            c.createStatement().execute("DROP ALL OBJECTS");
        }
    }

    @Test
    void rowMapperYAgregado() {
        var e = new Ej101RowMapperAndExtractor(ds);
        var libros = e.listar();
        assertEquals(2, libros.size());
        assertEquals("DDD", libros.get(0).titulo());
        assertEquals(500, e.totalPaginas());
    }

@Test
    void testDesafioObtenerSqlSelect() {
        assertEquals("SELECT id, nombre, precio FROM PRODUCTO ORDER BY id", Ej101RowMapperAndExtractor.desafioObtenerSqlSelect());
    }

    @Test
    void testDesafioRowMapperActivo() {
        assertTrue(Ej101RowMapperAndExtractor.desafioRowMapperActivo((rs, rowNum) -> null));
        assertFalse(Ej101RowMapperAndExtractor.desafioRowMapperActivo(null));
    }

    @Test
    void testDesafioResultSetExtractorActivo() {
        assertTrue(Ej101RowMapperAndExtractor.desafioResultSetExtractorActivo(rs -> List.of()));
        assertFalse(Ej101RowMapperAndExtractor.desafioResultSetExtractorActivo(null));
    }

    @Test
    void testDesafioMapearFilaConMapper() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test101_1", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE)");
                s.execute("INSERT INTO PRODUCTO VALUES (1, 'A', 10.0)");
            }
            try (var ps = c.prepareStatement("SELECT * FROM PRODUCTO")) {
                try (var rs = ps.executeQuery()) {
                    rs.next();
                    var p = Ej101RowMapperAndExtractor.desafioMapearFilaConMapper(new ProductoRowMapper(), rs, 1);
                    assertEquals(1, p.id());
                }
            }
        }
    }

    @Test
    void testDesafioExtraerConExtractor() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test101_2", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE)");
                s.execute("INSERT INTO PRODUCTO VALUES (1, 'A', 10.0)");
            }
            try (var ps = c.prepareStatement("SELECT * FROM PRODUCTO")) {
                try (var rs = ps.executeQuery()) {
                    var list = Ej101RowMapperAndExtractor.desafioExtraerConExtractor(new ProductoExtractor(), rs);
                    assertEquals(1, list.size());
                }
            }
        }
    }

    @Test
    void testDesafioExisteProductoCaro() {
        var list = List.of(new Producto(1, "A", 100.0));
        assertTrue(Ej101RowMapperAndExtractor.desafioExisteProductoCaro(list, 50.0));
        assertFalse(Ej101RowMapperAndExtractor.desafioExisteProductoCaro(list, 150.0));
    }

    @Test
    void testDesafioCrearProductoDePrueba() {
        var p = Ej101RowMapperAndExtractor.desafioCrearProductoDePrueba(1, "x", 1.0);
        assertEquals(1, p.id());
    }

    @Test
    void testDesafioCalcularSumaPrecios() {
        var list = List.of(new Producto(1, "A", 10.0), new Producto(2, "B", 20.0));
        assertEquals(30.0, Ej101RowMapperAndExtractor.desafioCalcularSumaPrecios(list), 0.001);
    }

    @Test
    void testDesafioObtenerNombresDeProductos() {
        var list = List.of(new Producto(1, "A", 10.0));
        assertEquals(List.of("A"), Ej101RowMapperAndExtractor.desafioObtenerNombresDeProductos(list));
    }

    @Test
    void testDesafioNombreFormatoValido() {
        assertTrue(Ej101RowMapperAndExtractor.desafioNombreFormatoValido("Ok"));
        assertFalse(Ej101RowMapperAndExtractor.desafioNombreFormatoValido("x"));
    }
}
