package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej005HeadersToolkitTest {

    private final Map<String, String> h = Map.of(
            "Content-Type", "application/json",
            "Authorization", "Bearer abc.def.ghi");

    @Test
    void getCaseInsensitive() {
        assertEquals("application/json", Ej005HeadersToolkit.get(h, "content-type").orElseThrow());
        assertTrue(Ej005HeadersToolkit.get(h, "X-Missing").isEmpty());
    }

    @Test
    void has() {
        assertTrue(Ej005HeadersToolkit.has(h, "AUTHORIZATION"));
        assertFalse(Ej005HeadersToolkit.has(h, "Cookie"));
    }

    @Test
    void bearer() {
        assertEquals("abc.def.ghi", Ej005HeadersToolkit.bearerToken(h));
        assertEquals("", Ej005HeadersToolkit.bearerToken(Map.of("Content-Type", "text/plain")));
    }
}
