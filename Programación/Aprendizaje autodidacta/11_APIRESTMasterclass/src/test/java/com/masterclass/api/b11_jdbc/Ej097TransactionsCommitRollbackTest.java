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
}
