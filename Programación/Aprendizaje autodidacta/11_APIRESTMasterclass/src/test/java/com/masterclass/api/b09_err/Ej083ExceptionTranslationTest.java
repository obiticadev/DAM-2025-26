package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b09_err.Ej083ExceptionTranslation.DatoDuplicadoException;
import com.masterclass.api.b09_err.Ej083ExceptionTranslation.PersistenciaException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class Ej083ExceptionTranslationTest {

    @Test
    void duplicado23505() {
        var r = Ej083ExceptionTranslation.traducir(new SQLException("dup", "23505"));
        assertInstanceOf(DatoDuplicadoException.class, r);
    }

    @Test
    void otroSqlStateEsPersistencia() {
        var ex = new SQLException("io", "08006");
        var r = Ej083ExceptionTranslation.traducir(ex);
        assertInstanceOf(PersistenciaException.class, r);
        assertSame(ex, r.getCause(), "debe conservar la causa");
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class, () -> Ej083ExceptionTranslation.traducir(null));
    }
}
