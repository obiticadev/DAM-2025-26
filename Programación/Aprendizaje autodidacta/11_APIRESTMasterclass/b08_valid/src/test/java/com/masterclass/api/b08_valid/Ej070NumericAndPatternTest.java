package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej070NumericAndPattern.ProductoDto;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class Ej070NumericAndPatternTest {

    @Test
    void invalidos() {
        var inv = Ej070NumericAndPattern.camposInvalidos(
                new ProductoDto("bad", BigDecimal.ZERO, -1, 200));
        assertTrue(inv.contains("sku"));
        assertTrue(inv.contains("precio"));
        assertTrue(inv.contains("stock"));
        assertTrue(inv.contains("descuento"));
    }

    @Test
    void validos() {
        var inv = Ej070NumericAndPattern.camposInvalidos(
                new ProductoDto("ABC-1234", new BigDecimal("9.99"), 10, 50));
        assertTrue(inv.isEmpty(), "no debería haber inválidos: " + inv);
    }
    @Test
    void testEsProductoValido() {
        var dto = new ProductoDto("ABC-1234", new BigDecimal("9.99"), 10, 50);
        assertFalse(Ej070NumericAndPattern.esProductoValido(dto));
    }

    @Test
    void testSkuValido() {
        assertFalse(Ej070NumericAndPattern.skuValido("ABC-1234"));
    }

    @Test
    void testFormatoPrecioValido() {
        assertFalse(Ej070NumericAndPattern.formatoPrecioValido(BigDecimal.TEN));
    }

    @Test
    void testStockDisponible() {
        assertFalse(Ej070NumericAndPattern.stockDisponible(10));
    }

    @Test
    void testDescuentoPermitido() {
        assertFalse(Ej070NumericAndPattern.descuentoPermitido(50));
    }

    @Test
    void testPrecioConIva() {
        var dto = new ProductoDto("ABC-1234", new BigDecimal("10.00"), 10, 50);
        assertNull(Ej070NumericAndPattern.precioConIva(dto, new BigDecimal("0.21")));
    }

    @Test
    void testEsSkuDeCategoria() {
        assertFalse(Ej070NumericAndPattern.esSkuDeCategoria("ABC-1234", "ABC"));
    }

    @Test
    void testPrecioConDescuento() {
        var dto = new ProductoDto("ABC-1234", new BigDecimal("10.00"), 10, 50);
        assertNull(Ej070NumericAndPattern.precioConDescuento(dto));
    }

    @Test
    void testGenerarSkuDefecto() {
        assertEquals("", Ej070NumericAndPattern.generarSkuDefecto("ABC"));
    }

    @Test
    void testValidarYRedondearPrecio() {
        var dto = new ProductoDto("ABC-1234", new BigDecimal("10.005"), 10, 50);
        assertNull(Ej070NumericAndPattern.validarYRedondearPrecio(dto));
    }
}
