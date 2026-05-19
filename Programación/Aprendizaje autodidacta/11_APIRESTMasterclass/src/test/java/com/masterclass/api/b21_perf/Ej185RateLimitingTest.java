package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej185RateLimitingTest {

    @Test
    void consumeHastaAgotar() {
        long[] est = {2, 0};
        assertTrue(Ej185RateLimiting.permitido(est, 5, 1000, 0));
        assertTrue(Ej185RateLimiting.permitido(est, 5, 1000, 0));
        assertFalse(Ej185RateLimiting.permitido(est, 5, 1000, 0));
    }

    @Test
    void recargaConElTiempo() {
        long[] est = {0, 0};
        assertFalse(Ej185RateLimiting.permitido(est, 5, 1000, 0));
        assertTrue(Ej185RateLimiting.permitido(est, 5, 1000, 1000));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej185RateLimiting.permitido(new long[1], 5, 1000, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej185RateLimiting.permitido(new long[]{1, 100}, 5, 1000, 0));
    }
}
