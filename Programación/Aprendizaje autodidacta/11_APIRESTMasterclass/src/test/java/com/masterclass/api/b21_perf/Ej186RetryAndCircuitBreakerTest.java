package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej186RetryAndCircuitBreakerTest {

    @Test
    void reintentaHastaExito() {
        int[] c = {0};
        String r = Ej186RetryAndCircuitBreaker.conReintentos(() -> {
            if (c[0] < 2) {
                throw new RuntimeException("fallo");
            }
            return "ok";
        }, 5, c);
        assertEquals("ok", r);
        assertEquals(3, c[0]);
    }

    @Test
    void agotaIntentosYRelanza() {
        assertThrows(RuntimeException.class,
                () -> Ej186RetryAndCircuitBreaker.conReintentos(() -> {
                    throw new RuntimeException("siempre");
                }, 2, new int[1]));
    }

    @Test
    void transicionesCircuitBreaker() {
        assertEquals("OPEN", Ej186RetryAndCircuitBreaker.transicion("CLOSED", false, 3, 3));
        assertEquals("HALF_OPEN", Ej186RetryAndCircuitBreaker.transicion("OPEN", false, 0, 3));
        assertEquals("CLOSED", Ej186RetryAndCircuitBreaker.transicion("HALF_OPEN", true, 0, 3));
        assertEquals("OPEN", Ej186RetryAndCircuitBreaker.transicion("HALF_OPEN", false, 0, 3));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej186RetryAndCircuitBreaker.transicion("RARO", true, 0, 3));
        assertThrows(IllegalArgumentException.class,
                () -> Ej186RetryAndCircuitBreaker.conReintentos(() -> "x", 0, new int[1]));
    }
}
