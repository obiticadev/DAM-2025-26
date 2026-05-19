package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b01_java.Ej011Records.ProductoDto;
import static org.junit.jupiter.api.Assertions.*;

class Ej011RecordsTest {

    @Test
    void componentes() {
        var p = new ProductoDto(1L, "Teclado", 100.0);
        assertEquals(1L, p.id());
        assertEquals("Teclado", p.nombre());
        assertEquals(100.0, p.precio());
    }

    @Test
    void validaciones() {
        assertThrows(IllegalArgumentException.class, () -> new ProductoDto(1L, "x", -1));
        assertThrows(IllegalArgumentException.class, () -> new ProductoDto(1L, "  ", 10));
    }

    @Test
    void esCaro() {
        assertTrue(new ProductoDto(1L, "x", 100.0).esCaro());
        assertFalse(new ProductoDto(1L, "x", 99.99).esCaro());
    }

    @Test
    void conIvaEsInmutable() {
        var p = new ProductoDto(1L, "Teclado", 100.0);
        var c = p.conIva(21);
        assertEquals(121.0, c.precio(), 0.0001);
        assertEquals(100.0, p.precio(), "el original no debe mutar");
    }
}
