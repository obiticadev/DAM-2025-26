package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import org.h2.jdbcx.JdbcDataSource;
import javax.sql.DataSource;
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
}
