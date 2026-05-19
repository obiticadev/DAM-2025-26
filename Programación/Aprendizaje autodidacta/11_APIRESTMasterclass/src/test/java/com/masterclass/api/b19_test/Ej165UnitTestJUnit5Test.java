package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej165UnitTestJUnit5Test {

    @Test
    void descuentoNormal() {
        assertEquals(80.0, Ej165UnitTestJUnit5.aplicarDescuento(100.0, 20.0));
    }

    @Test
    void limites() {
        assertEquals(100.0, Ej165UnitTestJUnit5.aplicarDescuento(100.0, 0.0));
        assertEquals(0.0, Ej165UnitTestJUnit5.aplicarDescuento(100.0, 100.0));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej165UnitTestJUnit5.aplicarDescuento(0.0, 10.0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej165UnitTestJUnit5.aplicarDescuento(100.0, 150.0));
    }
}
