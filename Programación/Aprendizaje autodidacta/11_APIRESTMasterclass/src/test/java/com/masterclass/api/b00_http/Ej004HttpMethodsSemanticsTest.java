package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej004HttpMethodsSemanticsTest {

    @Test
    void seguridad() {
        assertTrue(Ej004HttpMethodsSemantics.isSafe("GET"));
        assertTrue(Ej004HttpMethodsSemantics.isSafe("get"));
        assertFalse(Ej004HttpMethodsSemantics.isSafe("POST"));
        assertFalse(Ej004HttpMethodsSemantics.isSafe("DELETE"));
    }

    @Test
    void idempotencia() {
        assertTrue(Ej004HttpMethodsSemantics.isIdempotent("PUT"));
        assertTrue(Ej004HttpMethodsSemantics.isIdempotent("DELETE"));
        assertFalse(Ej004HttpMethodsSemantics.isIdempotent("POST"));
        assertFalse(Ej004HttpMethodsSemantics.isIdempotent("PATCH"));
    }

    @Test
    void verboInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej004HttpMethodsSemantics.isSafe("FOO"));
    }
}
