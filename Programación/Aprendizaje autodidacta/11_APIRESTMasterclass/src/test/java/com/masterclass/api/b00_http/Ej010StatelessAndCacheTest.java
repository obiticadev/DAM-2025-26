package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej010StatelessAndCacheTest {

    @Test
    void etagDeterministaYEntrecomillado() {
        String a = Ej010StatelessAndCache.etag("{\"id\":1}");
        String b = Ej010StatelessAndCache.etag("{\"id\":1}");
        assertEquals(a, b, "El ETag debe ser determinista");
        assertTrue(a.startsWith("\"") && a.endsWith("\""), "El ETag va entre comillas dobles");
        assertNotEquals(a, Ej010StatelessAndCache.etag("{\"id\":2}"));
    }

    @Test
    void getCondicional() {
        String c = "{\"id\":1}";
        String tag = Ej010StatelessAndCache.etag(c);
        assertEquals(304, Ej010StatelessAndCache.conditionalGetStatus(c, tag));
        assertEquals(200, Ej010StatelessAndCache.conditionalGetStatus(c, "\"otro\""));
        assertEquals(200, Ej010StatelessAndCache.conditionalGetStatus(c, null));
    }
}
