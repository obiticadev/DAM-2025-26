package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej187TimeoutsAndBulkheadTest {

    @Test
    void timeoutCumplidoYExcedido() {
        assertTrue(Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 50, 100));
        assertTrue(Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 100, 100));
        assertFalse(Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 150, 100));
    }

    @Test
    void bulkheadSaturaYRechaza() {
        long[] est = {0};
        assertTrue(Ej187TimeoutsAndBulkhead.adquirir(est, 2));
        assertTrue(Ej187TimeoutsAndBulkhead.adquirir(est, 2));
        assertFalse(Ej187TimeoutsAndBulkhead.adquirir(est, 2));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 10, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej187TimeoutsAndBulkhead.adquirir(new long[1], 0));
    }
}
