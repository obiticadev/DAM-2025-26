package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej169JsonAssertionsTest {

    @Test
    void igualesAunqueOrdenDistinto() {
        assertTrue(Ej169JsonAssertions.jsonIguales("{\"a\":1,\"b\":2}", "{\"b\":2,\"a\":1}"));
    }

    @Test
    void distintos() {
        assertFalse(Ej169JsonAssertions.jsonIguales("{\"a\":1}", "{\"a\":2}"));
    }

    @Test
    void jsonInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej169JsonAssertions.jsonIguales("{no-json", "{}"));
    }

    @Test
    void valorDeCampo() {
        assertEquals("Ada", Ej169JsonAssertions.valorCampo("{\"nombre\":\"Ada\"}", "nombre"));
        assertNull(Ej169JsonAssertions.valorCampo("{\"nombre\":\"Ada\"}", "edad"));
    }
}
