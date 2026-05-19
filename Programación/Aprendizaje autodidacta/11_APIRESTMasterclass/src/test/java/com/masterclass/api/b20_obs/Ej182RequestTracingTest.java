package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej182RequestTracingTest {

    @Test
    void origenGeneraTrace() {
        TraceContext182 c = Ej182RequestTracing.propagar(null, 0);
        assertNotNull(c.traceId());
        assertFalse(c.traceId().isBlank());
        assertTrue(c.raiz());
        assertTrue(c.spanId().startsWith(c.traceId() + ":0"));
    }

    @Test
    void propagaTraceEntrante() {
        TraceContext182 c = Ej182RequestTracing.propagar("trace-xyz", 2);
        assertEquals("trace-xyz", c.traceId());
        assertFalse(c.raiz());
        assertEquals("trace-xyz:2", c.spanId());
    }

    @Test
    void saltoNegativoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej182RequestTracing.propagar(null, -1));
    }
}
