package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej003StatusCodeResolverTest {

    @Test
    void familias() {
        assertEquals("Informativa", Ej003StatusCodeResolver.family(100));
        assertEquals("Exito", Ej003StatusCodeResolver.family(200));
        assertEquals("Redireccion", Ej003StatusCodeResolver.family(304));
        assertEquals("ErrorCliente", Ej003StatusCodeResolver.family(404));
        assertEquals("ErrorServidor", Ej003StatusCodeResolver.family(503));
    }

    @Test
    void rangoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej003StatusCodeResolver.family(99));
        assertThrows(IllegalArgumentException.class, () -> Ej003StatusCodeResolver.family(600));
    }

    @Test
    void erroresYCulpa() {
        assertTrue(Ej003StatusCodeResolver.isError(404));
        assertTrue(Ej003StatusCodeResolver.isError(500));
        assertFalse(Ej003StatusCodeResolver.isError(200));
        assertTrue(Ej003StatusCodeResolver.isClientFault(422));
        assertFalse(Ej003StatusCodeResolver.isClientFault(500));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_esRedireccionEstrictaMetodo() {
        assertTrue(Ej003StatusCodeResolver.esRedireccionEstrictaMetodo(307));
        assertTrue(Ej003StatusCodeResolver.esRedireccionEstrictaMetodo(308));
        assertFalse(Ej003StatusCodeResolver.esRedireccionEstrictaMetodo(301));
        assertFalse(Ej003StatusCodeResolver.esRedireccionEstrictaMetodo(200));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_resolverCodigoPorPhrase() {
        assertEquals(200, Ej003StatusCodeResolver.resolverCodigoPorPhrase("OK"));
        assertEquals(201, Ej003StatusCodeResolver.resolverCodigoPorPhrase("Created"));
        assertEquals(404, Ej003StatusCodeResolver.resolverCodigoPorPhrase("Not Found"));
        assertEquals(500, Ej003StatusCodeResolver.resolverCodigoPorPhrase("internal server error"));
        assertEquals(-1, Ej003StatusCodeResolver.resolverCodigoPorPhrase("Custom phrase"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_esErrorDeServidorTemporal() {
        assertTrue(Ej003StatusCodeResolver.esErrorDeServidorTemporal(503));
        assertTrue(Ej003StatusCodeResolver.esErrorDeServidorTemporal(504));
        assertFalse(Ej003StatusCodeResolver.esErrorDeServidorTemporal(500));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_esReintentablePorElCliente() {
        assertTrue(Ej003StatusCodeResolver.esReintentablePorElCliente(429));
        assertTrue(Ej003StatusCodeResolver.esReintentablePorElCliente(503));
        assertTrue(Ej003StatusCodeResolver.esReintentablePorElCliente(408));
        assertFalse(Ej003StatusCodeResolver.esReintentablePorElCliente(400));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_determinarAccionCliente() {
        assertEquals("PROCESAR_EXITO", Ej003StatusCodeResolver.determinarAccionCliente(200));
        assertEquals("REAUTENTICAR", Ej003StatusCodeResolver.determinarAccionCliente(401));
        assertEquals("CORREGIR_PETICION", Ej003StatusCodeResolver.determinarAccionCliente(422));
        assertEquals("REINTENTAR_DESPUES", Ej003StatusCodeResolver.determinarAccionCliente(503));
        assertEquals("NINGUNA", Ej003StatusCodeResolver.determinarAccionCliente(404));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_esCodigoEstandarIana() {
        assertTrue(Ej003StatusCodeResolver.esCodigoEstandarIana(200));
        assertTrue(Ej003StatusCodeResolver.esCodigoEstandarIana(404));
        assertFalse(Ej003StatusCodeResolver.esCodigoEstandarIana(799));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_permiteCuerpoEnRespuesta() {
        assertFalse(Ej003StatusCodeResolver.permiteCuerpoEnRespuesta(204));
        assertFalse(Ej003StatusCodeResolver.permiteCuerpoEnRespuesta(304));
        assertFalse(Ej003StatusCodeResolver.permiteCuerpoEnRespuesta(100));
        assertTrue(Ej003StatusCodeResolver.permiteCuerpoEnRespuesta(200));
        assertTrue(Ej003StatusCodeResolver.permiteCuerpoEnRespuesta(500));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_esErrorCriticoDeInfraestructura() {
        assertTrue(Ej003StatusCodeResolver.esErrorCriticoDeInfraestructura(502));
        assertTrue(Ej003StatusCodeResolver.esErrorCriticoDeInfraestructura(504));
        assertFalse(Ej003StatusCodeResolver.esErrorCriticoDeInfraestructura(500));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_resolverCodigoDesdeExcepcion() {
        assertEquals(400, Ej003StatusCodeResolver.resolverCodigoDesdeExcepcion(new IllegalArgumentException()));
        assertEquals(403, Ej003StatusCodeResolver.resolverCodigoDesdeExcepcion(new SecurityException("AccessDeniedException")));
        assertEquals(404, Ej003StatusCodeResolver.resolverCodigoDesdeExcepcion(new java.util.NoSuchElementException()));
        assertEquals(500, Ej003StatusCodeResolver.resolverCodigoDesdeExcepcion(new NullPointerException()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_esAutenticacionExpirada() {
        assertTrue(Ej003StatusCodeResolver.esAutenticacionExpirada(401));
        assertFalse(Ej003StatusCodeResolver.esAutenticacionExpirada(403));
        assertFalse(Ej003StatusCodeResolver.esAutenticacionExpirada(200));
    }
}
