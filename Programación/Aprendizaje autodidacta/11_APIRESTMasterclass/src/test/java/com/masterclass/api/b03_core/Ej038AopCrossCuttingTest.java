package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej038AopCrossCuttingTest {

    @Test
    void cuentaYDevuelveResultado() {
        var aop = new Ej038AopCrossCutting();
        assertEquals("a", aop.alrededor(() -> "a"));
        assertEquals(42, (int) aop.alrededor(() -> 42));
        assertEquals(2, aop.invocaciones());
    }
}
