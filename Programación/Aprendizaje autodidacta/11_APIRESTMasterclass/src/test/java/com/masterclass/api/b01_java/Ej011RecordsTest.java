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

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_esValido() {
        assertTrue(Ej011Records.esValido(new ProductoDto(1L, "Teclado", 100.0)));
        assertFalse(Ej011Records.esValido(null));
        assertFalse(Ej011Records.esValido(new ProductoDto(-1L, "Teclado", 100.0)));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_conDescuento() {
        var p = new ProductoDto(1L, "Teclado", 100.0);
        var res = Ej011Records.conDescuento(p, 10.0);
        assertEquals(90.0, res.precio(), 0.0001);
        assertEquals(1L, res.id());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_aJsonSimple() {
        var p = new ProductoDto(1L, "Teclado", 100.0);
        String json = Ej011Records.aJsonSimple(p);
        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"nombre\":\"Teclado\""));
        assertTrue(json.contains("\"precio\":100.0"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_crearServicioDto() {
        var s = Ej011Records.crearServicioDto("Consultoría", 75.0);
        assertNull(s.id());
        assertEquals("Consultoría", s.nombre());
        assertEquals(75.0, s.precio());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_esEquivalente() {
        var p1 = new ProductoDto(1L, "Teclado", 100.0);
        var p2 = new ProductoDto(2L, "TECLADO", 100.0);
        assertTrue(Ej011Records.esEquivalente(p1, p2));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_normalizarId() {
        var pInvalido = new ProductoDto(-5L, "Teclado", 100.0);
        var pNormalizado = Ej011Records.normalizarId(pInvalido);
        assertEquals(999L, pNormalizado.id());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_esPrecioRedondeado() {
        assertTrue(Ej011Records.esPrecioRedondeado(new ProductoDto(1L, "x", 10.25)));
        assertFalse(Ej011Records.esPrecioRedondeado(new ProductoDto(1L, "x", 10.3333)));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_crearDesdeValores() {
        var p = Ej011Records.crearDesdeValores("42,Teclado Mecanico,89.99");
        assertEquals(42L, p.id());
        assertEquals("Teclado Mecanico", p.nombre());
        assertEquals(89.99, p.precio());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_formatoEtiqueta() {
        var p = new ProductoDto(1L, "Teclado", 100.0);
        assertEquals("Teclado - $100.0", Ej011Records.formatoEtiqueta(p));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_reconstruirConEnvio() {
        var p = new ProductoDto(1L, "Teclado", 100.0);
        var conEnvio = Ej011Records.reconstruirConEnvio(p, 15.0);
        assertEquals(115.0, conEnvio.precio());
        assertEquals("Teclado", conEnvio.nombre());
    }
}
