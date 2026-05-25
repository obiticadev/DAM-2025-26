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

    @Test
    void testRetoExtra01_esTraceIdValido() {
        // Comprueba que la correlacion tenga un formato UUID/hexadecimal estandar.
        assertTrue(Ej082ErrorTraceAndCorrelation.esTraceIdValido("uuid123"));
    }

    @Test
    void testRetoExtra02_crearTraceIdNuevo() {
        // Genera un identificador aleatorio UUID para seguimiento.
        assertNotNull(Ej082ErrorTraceAndCorrelation.crearTraceIdNuevo());
    }

    @Test
    void testRetoExtra03_formatearLogConTrace() {
        // Concatena identificador de flujo y log.
        assertEquals("[TRACE-1] log msg", Ej082ErrorTraceAndCorrelation.formatearLogConTrace("TRACE-1", "log msg"));
    }

    @Test
    void testRetoExtra04_esHeaderCorrelacion() {
        // Identifica si es el cabezal HTTP de seguimiento standard.
        assertTrue(Ej082ErrorTraceAndCorrelation.esHeaderCorrelacion("X-Correlation-ID"));
    }

    @Test
    void testRetoExtra05_obtenerTraceIdDeCabezal() {
        // Limpia y valida el valor de correlacion recibido.
        assertEquals("val", Ej082ErrorTraceAndCorrelation.obtenerTraceIdDeCabezal(" val "));
    }

    @Test
    void testRetoExtra06_esErrorRelacionado() {
        // Determina si pertenecen al mismo flujo global.
        assertTrue(Ej082ErrorTraceAndCorrelation.esErrorRelacionado("TR-1", "TR-1"));
    }

    @Test
    void testRetoExtra07_generarCabeceraRespuesta() {
        // Obtiene el valor del cabezal de respuesta HTTP.
        assertEquals("trace-id-1", Ej082ErrorTraceAndCorrelation.generarCabeceraRespuesta("trace-id-1"));
    }

    @Test
    void testRetoExtra08_esExcepcionDeSeguimiento() {
        // Determina si el error apunta a problemas del colector de trazas.
        assertTrue(Ej082ErrorTraceAndCorrelation.esExcepcionDeSeguimiento(new IllegalArgumentException("trace")));
    }

    @Test
    void testRetoExtra09_crearContingenciaCorrelacion() {
        // Resuelve una traza invalida asignando un fallback seguro.
        assertNotNull(Ej082ErrorTraceAndCorrelation.crearContingenciaCorrelacion(null));
    }

    @Test
    void testRetoExtra10_longitudCorrectaTraza() {
        // Comprueba limites seguros de la traza para evitar inyecciones en logs.
        assertTrue(Ej082ErrorTraceAndCorrelation.longitudCorrectaTraza("1234567890abcdef"));
    }

}