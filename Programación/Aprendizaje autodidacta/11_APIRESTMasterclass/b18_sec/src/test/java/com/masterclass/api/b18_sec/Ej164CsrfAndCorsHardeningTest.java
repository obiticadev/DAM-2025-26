package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej164CsrfAndCorsHardeningTest {

    @Test
    void jwtNoNecesitaCsrf() {
        assertFalse(Ej164CsrfAndCorsHardening.requiereCsrf("POST", false));
    }

    @Test
    void cookieMutadoraNecesitaCsrf() {
        assertTrue(Ej164CsrfAndCorsHardening.requiereCsrf("POST", true));
    }

    @Test
    void getNuncaCsrf() {
        assertFalse(Ej164CsrfAndCorsHardening.requiereCsrf("GET", true));
    }

    @Test
    void corsAllowlist() {
        Set<String> ok = Set.of("https://app.com");
        assertTrue(Ej164CsrfAndCorsHardening.corsPermitido("https://app.com", ok));
        assertFalse(Ej164CsrfAndCorsHardening.corsPermitido("http://app.com", ok));
        assertFalse(Ej164CsrfAndCorsHardening.corsPermitido(null, ok));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej164CsrfAndCorsHardening.requiereCsrf(" ", false));
        assertThrows(IllegalArgumentException.class,
                () -> Ej164CsrfAndCorsHardening.corsPermitido("x", null));
    }

    @Test
    void testRetoExtra01_esCsrfTokenValido() {
        // Comprueba que cumpla la estructura standard de token anti-CSRF.
        assertTrue(Ej164CsrfAndCorsHardening.esCsrfTokenValido("csrf123"));
    }

    @Test
    void testRetoExtra02_crearCsrfTokenNuevo() {
        // Genera un token anti-CSRF criptograficamente seguro.
        assertNotNull(Ej164CsrfAndCorsHardening.crearCsrfTokenNuevo());
    }

    @Test
    void testRetoExtra03_esHeaderCsrfCorrecto() {
        // Identifica si es el cabezal HTTP anti-CSRF standard.
        assertTrue(Ej164CsrfAndCorsHardening.esHeaderCsrfCorrecto("X-CSRF-TOKEN"));
    }

    @Test
    void testRetoExtra04_esOrigenCorsPermitido() {
        // Valida si el origen de la peticion esta en la lista blanca de CORS.
        assertTrue(Ej164CsrfAndCorsHardening.esOrigenCorsPermitido("https://a.com", java.util.List.of("https://a.com")));
    }

    @Test
    void testRetoExtra05_esMetodoCorsPermitido() {
        // Determina si el verbo HTTP esta permitido por CORS de forma general.
        assertTrue(Ej164CsrfAndCorsHardening.esMetodoCorsPermitido("GET"));
    }

    @Test
    void testRetoExtra06_esExcepcionCsrf() {
        // Determina si la excepcion apunta a ataque de falsificacion CSRF.
        assertTrue(Ej164CsrfAndCorsHardening.esExcepcionCsrf(new SecurityException("CSRF")));
    }

    @Test
    void testRetoExtra07_crearRespuestaCorsFallo() {
        // Genera la notificacion de origen cruzado prohibido.
        assertTrue(Ej164CsrfAndCorsHardening.crearRespuestaCorsFallo("origin").contains("origin"));
    }

    @Test
    void testRetoExtra08_esHeaderCorsSoportado() {
        // Verifica si el encabezado es de control de CORS.
        assertTrue(Ej164CsrfAndCorsHardening.esHeaderCorsSoportado("Access-Control-Allow-Origin"));
    }

    @Test
    void testRetoExtra09_esCookieCsrfSegura() {
        // Verifica si la cookie CSRF tiene atributos Secure, HttpOnly y SameSite=Strict.
        assertTrue(Ej164CsrfAndCorsHardening.esCookieCsrfSegura("Secure;HttpOnly;SameSite=Strict"));
    }

    @Test
    void testRetoExtra10_esFalloConfiguracionCors() {
        // Determina si la excepcion es por mala configuracion del colector de cors.
        assertTrue(Ej164CsrfAndCorsHardening.esFalloConfiguracionCors(new IllegalArgumentException("cors")));
    }

}