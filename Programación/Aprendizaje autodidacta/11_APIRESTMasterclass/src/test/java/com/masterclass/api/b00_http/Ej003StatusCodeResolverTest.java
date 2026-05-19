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
}
