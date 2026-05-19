package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.Test;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

class Ej099ConnectionPoolingTest {

    @Test
    void poolReutilizaConexiones() throws Exception {
        DataSource ds = Ej099ConnectionPooling.crearPool(
                "jdbc:h2:mem:ej099;DB_CLOSE_DELAY=-1", 3);
        assertNotNull(ds);
        assertTrue(Ej099ConnectionPooling.usarYDevolver(ds, 10),
                "pedir 10 conexiones de un pool de 3 debe funcionar reutilizándolas");
    }

    @Test
    void poolInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej099ConnectionPooling.crearPool("jdbc:h2:mem:x", 0));
    }
}
