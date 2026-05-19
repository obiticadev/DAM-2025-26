package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej087DaoPattern.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej087DaoPatternTest {

    @Test
    void escenarioPorDepartamento() {
        assertEquals(1, Ej087DaoPattern.escenario(new EmpleadoDaoMem()));
    }

    @Test
    void insertarDuplicadoFalla() {
        var dao = new EmpleadoDaoMem();
        dao.insertar(new Empleado(1, "Ana", "IT"));
        assertThrows(IllegalStateException.class,
                () -> dao.insertar(new Empleado(1, "Otra", "IT")));
    }

    @Test
    void eliminar() {
        var dao = new EmpleadoDaoMem();
        dao.insertar(new Empleado(5, "Leo", "RRHH"));
        assertTrue(dao.eliminar(5));
        assertNull(dao.buscarPorId(5));
    }
}
