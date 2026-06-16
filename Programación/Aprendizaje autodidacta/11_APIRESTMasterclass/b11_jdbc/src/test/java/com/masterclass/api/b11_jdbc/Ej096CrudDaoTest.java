package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;
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

@Test
    void testDesafioObtenerSqlInsert() {
        assertEquals("INSERT INTO CLIENTE(id,nombre,email) VALUES (?,?,?)", Ej096CrudDao.desafioObtenerSqlInsert());
    }

    @Test
    void testDesafioObtenerSqlFindById() {
        assertEquals("SELECT id,nombre,email FROM CLIENTE WHERE id = ?", Ej096CrudDao.desafioObtenerSqlFindById());
    }

    @Test
    void testDesafioObtenerSqlFindAll() {
        assertEquals("SELECT id,nombre,email FROM CLIENTE", Ej096CrudDao.desafioObtenerSqlFindAll());
    }

    @Test
    void testDesafioObtenerSqlDelete() {
        assertEquals("DELETE FROM CLIENTE WHERE id = ?", Ej096CrudDao.desafioObtenerSqlDelete());
    }

    @Test
    void testDesafioValidarCliente() {
        assertThrows(IllegalArgumentException.class, () -> Ej096CrudDao.desafioValidarCliente(null));
        assertDoesNotThrow(() -> Ej096CrudDao.desafioValidarCliente(new Cliente(1, "A", "b@c.com")));
    }

    @Test
    void testDesafioMapearCliente() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test096_1", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE CLIENTE(id INT, nombre VARCHAR, email VARCHAR)");
                s.execute("INSERT INTO CLIENTE VALUES (1, 'Ana', 'a@b.com')");
            }
            try (var ps = c.prepareStatement("SELECT * FROM CLIENTE")) {
                try (var rs = ps.executeQuery()) {
                    rs.next();
                    var cliente = Ej096CrudDao.desafioMapearCliente(rs);
                    assertEquals(1, cliente.id());
                    assertEquals("Ana", cliente.nombre());
                    assertEquals("a@b.com", cliente.email());
                }
            }
        }
    }

    @Test
    void testDesafioConfigurarEliminar() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test096_2", "sa", "")) {
            try (var s = c.createStatement()) {
                s.execute("CREATE TABLE CLIENTE(id INT, nombre VARCHAR, email VARCHAR)");
            }
            try (var ps = c.prepareStatement("DELETE FROM CLIENTE WHERE id = ?")) {
                assertDoesNotThrow(() -> Ej096CrudDao.desafioConfigurarEliminar(ps, 1));
            }
        }
    }

    @Test
    void testDesafioVerificarFilaAfectada() {
        assertTrue(Ej096CrudDao.desafioVerificarFilaAfectada(1));
        assertFalse(Ej096CrudDao.desafioVerificarFilaAfectada(0));
    }

    @Test
    void testDesafioNoTieneNulos() {
        assertTrue(Ej096CrudDao.desafioNoTieneNulos(List.of(new Cliente(1, "A", "b"))));
    }

    @Test
    void testDesafioClienteVacioFallback() {
        var fallback = Ej096CrudDao.desafioClienteVacíoFallback();
        assertEquals(0, fallback.id());
        assertEquals("Sin Nombre", fallback.nombre());
    }
}
