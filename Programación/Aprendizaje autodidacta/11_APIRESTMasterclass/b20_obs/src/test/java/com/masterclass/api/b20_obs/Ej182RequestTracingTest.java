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

    @Test
    void testRetoExtra01_esSaltoValido() {
        // Valida salto >= 0.
        assertTrue(Ej182RequestTracing.esSaltoValido(1));
    }

    @Test
    void testRetoExtra02_esTraceIdValido() {
        // Valida traceId no nulo/blanco.
        assertTrue(Ej182RequestTracing.esTraceIdValido("123"));
    }

    @Test
    void testRetoExtra03_crearTraceContext() {
        // Crea contexto.
        assertNotNull(Ej182RequestTracing.crearTraceContext("a", "b", true));
    }

    @Test
    void testRetoExtra04_obtenerTraceId() {
        // Obtiene traceId.
        assertEquals("a", Ej182RequestTracing.obtenerTraceId(new TraceContext182("a", "b", true)));
    }

    @Test
    void testRetoExtra05_obtenerSpanId() {
        // Obtiene spanId.
        assertEquals("b", Ej182RequestTracing.obtenerSpanId(new TraceContext182("a", "b", true)));
    }

    @Test
    void testRetoExtra06_esRaiz() {
        // Verifica si es raiz.
        assertTrue(Ej182RequestTracing.esRaiz(new TraceContext182("a", "b", true)));
    }

    @Test
    void testRetoExtra07_generarTraceIdAleatorio() {
        // Genera un traceId UUID sin guiones.
        assertEquals(32, Ej182RequestTracing.generarTraceIdAleatorio().length());
    }

    @Test
    void testRetoExtra08_obtenerDobleSalto() {
        // Obtiene una derivacion del spanId.
        assertEquals("a:4", Ej182RequestTracing.obtenerDobleSalto(new TraceContext182("a", "b", true), 2));
    }

    @Test
    void testRetoExtra09_esMismoTraceId() {
        // Verifica si los contextos comparten traceId.
        assertTrue(Ej182RequestTracing.esMismoTraceId(new TraceContext182("a", "b", true), new TraceContext182("a", "c", false)));
    }

    @Test
    void testRetoExtra10_formatearTrazado() {
        // Formatea el flujo.
        assertEquals("a->b", Ej182RequestTracing.formatearTrazado(new TraceContext182("a", "b", true)));
    }

}