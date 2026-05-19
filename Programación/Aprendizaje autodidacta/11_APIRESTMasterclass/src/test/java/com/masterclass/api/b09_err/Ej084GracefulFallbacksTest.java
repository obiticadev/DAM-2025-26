package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej084GracefulFallbacksTest {

    @Test
    void exitoNoUsaFallback() {
        var g = new Ej084GracefulFallbacks();
        assertEquals("ok", g.conFallback(() -> "ok", "fb"));
        assertEquals(0, g.fallos());
    }

    @Test
    void falloDevuelveFallbackYCuenta() {
        var g = new Ej084GracefulFallbacks();
        assertEquals("cache", g.conFallback(() -> {
            throw new RuntimeException("down");
        }, "cache"));
        assertEquals(1, g.fallos());
    }
}
