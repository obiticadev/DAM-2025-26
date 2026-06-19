package com.masterclass.api.b31_oodb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej254TransactionsOnObjectsTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej254;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE TABLE DEPT(id INT PRIMARY KEY, nombre VARCHAR(50), presupuesto INT)");
        st.execute("INSERT INTO DEPT VALUES (1,'IT',1000),(2,'RRHH',0)");
        st.execute("CREATE TABLE EMP(id INT PRIMARY KEY, nombre VARCHAR(50))");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    private int presupuesto(int id) throws Exception {
        try (var ps = c.prepareStatement("SELECT presupuesto FROM DEPT WHERE id=?")) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    @Test
    void transferirPresupuestoCommit() throws Exception {
        Ej254TransactionsOnObjects.transferirPresupuesto(c, 1, 2, 400);
        assertEquals(600, presupuesto(1));
        assertEquals(400, presupuesto(2));
    }

    @Test
    void transferirPresupuestoRollback() {
        assertThrows(Ej254TransactionsOnObjects.PresupuestoException.class,
                () -> Ej254TransactionsOnObjects.transferirPresupuesto(c, 2, 1, 500));
        assertDoesNotThrow(() -> {
            assertEquals(0, presupuesto(2), "rollback debe dejar el presupuesto original");
            assertEquals(1000, presupuesto(1));
        });
    }

    @Test
    void guardarTodoONada_exito() throws Exception {
        boolean ok = Ej254TransactionsOnObjects.guardarTodoONada(c,
                List.of(new Empleado(1, "Ana", 0), new Empleado(2, "Beto", 0)));
        assertTrue(ok);
    }

    @Test
    void guardarTodoONada_rollbackPorIdDuplicado() throws Exception {
        boolean ok = Ej254TransactionsOnObjects.guardarTodoONada(c,
                List.of(new Empleado(1, "Ana", 0), new Empleado(1, "Beto", 0)));
        assertFalse(ok, "un id duplicado debe abortar TODA la transacción");
        try (var rs = c.createStatement().executeQuery("SELECT COUNT(*) FROM EMP")) {
            rs.next();
            assertEquals(0, rs.getInt(1), "rollback: no debe quedar ningún empleado");
        }
    }

    @Test
    void retoExtra01_presupuestoDe() throws Exception {
        assertEquals(1000, Ej254TransactionsOnObjects.presupuestoDe(c, 1));
    }

    @Test
    void retoExtra02_desactivarAutocommit() throws Exception {
        Ej254TransactionsOnObjects.desactivarAutocommit(c);
        assertFalse(c.getAutoCommit());
    }

    @Test
    void retoExtra03_confirmar() throws Exception {
        c.setAutoCommit(false);
        assertDoesNotThrow(() -> Ej254TransactionsOnObjects.confirmar(c));
    }

    @Test
    void retoExtra04_revertir() throws Exception {
        c.setAutoCommit(false);
        assertDoesNotThrow(() -> Ej254TransactionsOnObjects.revertir(c));
    }

    @Test
    void retoExtra05_montoValido() {
        assertTrue(Ej254TransactionsOnObjects.montoValido(100));
        assertFalse(Ej254TransactionsOnObjects.montoValido(-5));
    }

    @Test
    void retoExtra06_crearSavepoint() throws Exception {
        c.setAutoCommit(false);
        assertNotNull(Ej254TransactionsOnObjects.crearSavepoint(c, "sp1"));
    }

    @Test
    void retoExtra07_revertirASavepoint() throws Exception {
        c.setAutoCommit(false);
        var sp = c.setSavepoint("sp2");
        assertDoesNotThrow(() -> Ej254TransactionsOnObjects.revertirASavepoint(c, sp));
    }

    @Test
    void retoExtra08_transferirYLeerDestino() throws Exception {
        assertEquals(400, Ej254TransactionsOnObjects.transferirYLeerDestino(c, 1, 2, 400));
    }

    @Test
    void retoExtra09_aislamientoSerializable() throws Exception {
        Ej254TransactionsOnObjects.aislamientoSerializable(c);
        assertEquals(Connection.TRANSACTION_SERIALIZABLE, c.getTransactionIsolation());
    }

    @Test
    void retoExtra10_hayFondos() throws Exception {
        assertTrue(Ej254TransactionsOnObjects.hayFondos(c, 1, 400));
        assertFalse(Ej254TransactionsOnObjects.hayFondos(c, 2, 400));
    }
}
