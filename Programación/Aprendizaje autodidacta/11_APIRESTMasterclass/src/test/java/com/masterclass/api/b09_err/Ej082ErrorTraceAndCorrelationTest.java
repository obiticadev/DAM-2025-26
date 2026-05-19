package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej082ErrorTraceAndCorrelationTest {

    @Test
    void reutilizaTraceEntrante() {
        Map<String, Object> b = Ej082ErrorTraceAndCorrelation.errorBody(500, "boom", "abc-123");
        assertEquals(500, b.get("status"));
        assertEquals("boom", b.get("error"));
        assertEquals("abc-123", b.get("traceId"));
    }

    @Test
    void generaTraceSiFalta() {
        Map<String, Object> b = Ej082ErrorTraceAndCorrelation.errorBody(400, "bad", null);
        assertNotNull(b.get("traceId"));
        assertFalse(b.get("traceId").toString().isBlank());
    }

    @Test
    void statusNoErrorFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej082ErrorTraceAndCorrelation.errorBody(200, "ok", null));
    }
}
