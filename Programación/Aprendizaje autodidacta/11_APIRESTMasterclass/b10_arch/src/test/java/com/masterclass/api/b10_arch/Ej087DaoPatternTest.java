package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej087DaoPattern.*;
import java.util.List;
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

@Test
    void testDesafioValidarEmpleado() {
        assertThrows(IllegalArgumentException.class, () -> Ej087DaoPattern.desafioValidarEmpleado(null));
        assertDoesNotThrow(() -> Ej087DaoPattern.desafioValidarEmpleado(new Empleado(1, "Ana", "IT")));
    }

    @Test
    void testDesafioComprobarDuplicado() {
        var tabla = new java.util.LinkedHashMap<Integer, Empleado>();
        tabla.put(1, new Empleado(1, "Ana", "IT"));
        assertThrows(IllegalStateException.class, () -> Ej087DaoPattern.desafioComprobarDuplicado(tabla, 1));
        assertDoesNotThrow(() -> Ej087DaoPattern.desafioComprobarDuplicado(tabla, 2));
    }

    @Test
    void testDesafioInsertarEnTabla() {
        var tabla = new java.util.LinkedHashMap<Integer, Empleado>();
        var e = new Empleado(1, "Ana", "IT");
        Ej087DaoPattern.desafioInsertarEnTabla(tabla, e);
        assertEquals(e, tabla.get(1));
    }

    @Test
    void testDesafioBuscarPorId() {
        var tabla = new java.util.LinkedHashMap<Integer, Empleado>();
        var e = new Empleado(1, "Ana", "IT");
        tabla.put(1, e);
        assertEquals(e, Ej087DaoPattern.desafioBuscarPorId(tabla, 1));
        assertNull(Ej087DaoPattern.desafioBuscarPorId(tabla, 2));
    }

    @Test
    void testDesafioFiltrarPorDepartamento() {
        var tabla = new java.util.LinkedHashMap<Integer, Empleado>();
        tabla.put(1, new Empleado(1, "Ana", "IT"));
        tabla.put(2, new Empleado(2, "Leo", "HR"));
        var it = Ej087DaoPattern.desafioFiltrarPorDepartamento(tabla, "IT");
        assertEquals(1, it.size());
        assertEquals("Ana", it.get(0).nombre());
    }

    @Test
    void testDesafioCrearListaSegura() {
        var list = new java.util.ArrayList<Empleado>();
        list.add(new Empleado(1, "A", "IT"));
        var secure = Ej087DaoPattern.desafioCrearListaSegura(list);
        assertThrows(UnsupportedOperationException.class, () -> secure.add(new Empleado(2, "B", "HR")));
    }

    @Test
    void testDesafioEliminarPorId() {
        var tabla = new java.util.LinkedHashMap<Integer, Empleado>();
        tabla.put(1, new Empleado(1, "A", "IT"));
        assertTrue(Ej087DaoPattern.desafioEliminarPorId(tabla, 1));
        assertFalse(Ej087DaoPattern.desafioEliminarPorId(tabla, 1));
    }

    @Test
    void testDesafioInsertarVarios() {
        var dao = new EmpleadoDaoMem();
        Ej087DaoPattern.desafioInsertarVarios(dao, List.of(new Empleado(1, "A", "IT"), new Empleado(2, "B", "IT")));
        assertEquals(2, dao.buscarPorDepartamento("IT").size());
    }

    @Test
    void testDesafioBuscarIT() {
        var dao = new EmpleadoDaoMem();
        dao.insertar(new Empleado(1, "A", "IT"));
        assertEquals(1, Ej087DaoPattern.desafioBuscarIT(dao).size());
    }

    @Test
    void testDesafioObtenerTamañoSeguro() {
        assertEquals(0, Ej087DaoPattern.desafioObtenerTamañoSeguro(null));
        assertEquals(1, Ej087DaoPattern.desafioObtenerTamañoSeguro(List.of(new Empleado(1, "A", "IT"))));
    }
}
