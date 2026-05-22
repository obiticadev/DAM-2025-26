package com.masterclass.api.b15_query;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej142KeysetPaginationTest {

    private final List<Long> ids = List.of(1L, 2L, 3L, 4L, 5L, 6L);

    @Test
    void desdeElPrincipio() {
        assertEquals(List.of(1L, 2L, 3L),
                Ej142KeysetPagination.siguientePagina(ids, null, 3));
    }

    @Test
    void desdeUnCursor() {
        assertEquals(List.of(3L, 4L),
                Ej142KeysetPagination.siguientePagina(ids, 2L, 2));
    }

    @Test
    void finDeDatos() {
        assertTrue(Ej142KeysetPagination.siguientePagina(ids, 6L, 3).isEmpty());
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej142KeysetPagination.siguientePagina(null, 1L, 3));
        assertThrows(IllegalArgumentException.class,
                () -> Ej142KeysetPagination.siguientePagina(ids, 1L, 0));
    }

    @Test
    void testRetoExtra01() {
        var i = new ItemKeyset142("Laptop");
        assertNull(Ej142KeysetPagination.obtenerId(i));
    }

    @Test
    void testRetoExtra02() {
        var i = new ItemKeyset142("Laptop");
        assertNull(Ej142KeysetPagination.obtenerCreadoEn(i));
    }

    @Test
    void testRetoExtra03() {
        var a = new ItemKeyset142("A");
        var b = new ItemKeyset142("B");
        assertFalse(Ej142KeysetPagination.esPosteriorId(a, b));
    }

    @Test
    void testRetoExtra04() {
        var i = Ej142KeysetPagination.crearItem("Laptop");
        assertNotNull(i);
    }

    @Test
    void testRetoExtra05() {
        var i = new ItemKeyset142("Laptop");
        assertFalse(Ej142KeysetPagination.tieneCreadoEn(i));
    }

    @Test
    void testRetoExtra06() {
        var i = new ItemKeyset142("Laptop");
        assertTrue(Ej142KeysetPagination.esNuevo(i));
    }

    @Test
    void testRetoExtra07() {
        var i = new ItemKeyset142("Laptop");
        assertEquals("Laptop", Ej142KeysetPagination.obtenerNombre(i));
    }

    @Test
    void testRetoExtra08() {
        var i = new ItemKeyset142("Laptop");
        assertTrue(Ej142KeysetPagination.nombreContiene(i, "lap"));
    }

    @Test
    void testRetoExtra09() {
        var a = new ItemKeyset142("A");
        var b = new ItemKeyset142("B");
        assertEquals(0, Ej142KeysetPagination.compararPorId(a, b));
    }

    @Test
    void testRetoExtra10() {
        var i = new ItemKeyset142("Laptop");
        assertEquals("ItemKeyset[Id=null, Nombre=Laptop]", Ej142KeysetPagination.formatearItem(i));
    }

}
