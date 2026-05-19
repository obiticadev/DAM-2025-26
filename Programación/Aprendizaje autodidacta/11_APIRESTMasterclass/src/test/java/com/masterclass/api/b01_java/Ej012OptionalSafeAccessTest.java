package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class Ej012OptionalSafeAccessTest {

    @Test
    void primeroLargoIgnoraNull() {
        var r = Ej012OptionalSafeAccess.primeroLargo(Arrays.asList("an", null, "pedro", "li"), 3);
        assertEquals("pedro", r.orElseThrow());
    }

    @Test
    void primeroLargoVacio() {
        assertTrue(Ej012OptionalSafeAccess.primeroLargo(Arrays.asList("a", "b"), 5).isEmpty());
    }

    @Test
    void mayusOrDefault() {
        assertEquals("N/A", Ej012OptionalSafeAccess.enMayusOrDefault(null, "N/A"));
        assertEquals("HOLA", Ej012OptionalSafeAccess.enMayusOrDefault("hola", "N/A"));
    }

    @Test
    void requerido() {
        assertEquals("x", Ej012OptionalSafeAccess.requerido("x"));
        assertThrows(IllegalStateException.class, () -> Ej012OptionalSafeAccess.requerido(null));
    }
}
