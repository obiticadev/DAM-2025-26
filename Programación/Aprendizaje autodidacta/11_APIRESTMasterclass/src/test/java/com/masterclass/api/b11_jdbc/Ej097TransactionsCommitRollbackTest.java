package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej097TransactionsCommitRollbackTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej097;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE TABLE CUENTA(id INT, saldo INT)");
        st.execute("INSERT INTO CUENTA VALUES (1,100),(2,0)");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    private int saldo(int id) throws Exception {
        try (var ps = c.prepareStatement("SELECT saldo FROM CUENTA WHERE id=?")) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    @Test
    void commitMueveSaldos() throws Exception {
        Ej097TransactionsCommitRollback.transferir(c, 1, 2, 40);
        assertEquals(60, saldo(1));
        assertEquals(40, saldo(2));
    }

    @Test
    void rollbackSinFondos() {
        assertThrows(Ej097TransactionsCommitRollback.FondosException.class,
                () -> Ej097TransactionsCommitRollback.transferir(c, 1, 2, 500));
        assertDoesNotThrow(() -> {
            assertEquals(100, saldo(1), "rollback debe dejar el saldo original");
            assertEquals(0, saldo(2));
        });
    }

@Test
    void testDesafioDesactivarAutoCommit() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_1", "sa", "")) {
            Ej097TransactionsCommitRollback.desafioDesactivarAutoCommit(c);
            assertFalse(c.getAutoCommit());
        }
    }

    @Test
    void testDesafioActivarAutoCommit() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_2", "sa", "")) {
            c.setAutoCommit(false);
            Ej097TransactionsCommitRollback.desafioActivarAutoCommit(c);
            assertTrue(c.getAutoCommit());
        }
    }

    @Test
    void testDesafioConfirmarTransaccion() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_3", "sa", "")) {
            c.setAutoCommit(false);
            assertDoesNotThrow(() -> Ej097TransactionsCommitRollback.desafioConfirmarTransaccion(c));
        }
    }

    @Test
    void testDesafioRevertirTransaccion() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_4", "sa", "")) {
            c.setAutoCommit(false);
            assertDoesNotThrow(() -> Ej097TransactionsCommitRollback.desafioRevertirTransaccion(c));
        }
    }

    @Test
    void testDesafioIsAutoCommitDesactivado() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_5", "sa", "")) {
            c.setAutoCommit(false);
            assertTrue(Ej097TransactionsCommitRollback.desafioIsAutoCommitDesactivado(c));
        }
    }

    @Test
    void testDesafioValidarMontoTransferencia() {
        assertTrue(Ej097TransactionsCommitRollback.desafioValidarMontoTransferencia(100.0));
        assertFalse(Ej097TransactionsCommitRollback.desafioValidarMontoTransferencia(-5.0));
    }

    @Test
    void testDesafioSimularRollbackSeguro() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_6", "sa", "")) {
            c.setAutoCommit(false);
            assertDoesNotThrow(() -> Ej097TransactionsCommitRollback.desafioSimularRollbackSeguro(c));
        }
    }

    @Test
    void testDesafioAislamientoReadCommitted() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_7", "sa", "")) {
            Ej097TransactionsCommitRollback.desafioAislamientoReadCommitted(c);
            assertEquals(java.sql.Connection.TRANSACTION_READ_COMMITTED, c.getTransactionIsolation());
        }
    }

    @Test
    void testDesafioCrearSavepoint() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_8", "sa", "")) {
            c.setAutoCommit(false);
            assertNotNull(Ej097TransactionsCommitRollback.desafioCrearSavepoint(c, "sp1"));
        }
    }

    @Test
    void testDesafioRevertirAlSavepoint() throws Exception {
        try (var c = java.sql.DriverManager.getConnection("jdbc:h2:mem:test097_9", "sa", "")) {
            c.setAutoCommit(false);
            var sp = c.setSavepoint("sp2");
            assertDoesNotThrow(() -> Ej097TransactionsCommitRollback.desafioRevertirAlSavepoint(c, sp));
        }
    }
}
