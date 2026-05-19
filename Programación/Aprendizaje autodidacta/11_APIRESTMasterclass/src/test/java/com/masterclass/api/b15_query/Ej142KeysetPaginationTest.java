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
}
