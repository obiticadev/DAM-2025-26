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
}
