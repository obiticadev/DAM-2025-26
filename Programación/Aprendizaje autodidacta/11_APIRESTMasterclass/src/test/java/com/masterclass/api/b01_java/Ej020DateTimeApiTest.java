package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class Ej020DateTimeApiTest {

    @Test
    void diasEntre() {
        assertEquals(10, Ej020DateTimeApi.diasEntre(
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 11)));
    }

    @Test
    void haCaducado() {
        var emit = LocalDateTime.of(2026, 1, 1, 10, 0);
        assertTrue(Ej020DateTimeApi.haCaducado(emit, emit.plusMinutes(31), 30));
        assertFalse(Ej020DateTimeApi.haCaducado(emit, emit.plusMinutes(10), 30));
    }

    @Test
    void aIso() {
        assertEquals("2026-05-18", Ej020DateTimeApi.aIso(LocalDate.of(2026, 5, 18)));
    }
}
