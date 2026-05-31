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

    @Test
    void retoExtra01_generarEtagFuerteSHA256() {
        String tag = Ej010StatelessAndCache.generarEtagFuerteSHA256("abc".getBytes());
        assertTrue(tag.startsWith("\"") && tag.endsWith("\""));
        assertEquals(66, tag.length()); // 64 chars hex + 2 quotes
    }

    @Test
    void retoExtra02_esEtagDebil() {
        assertTrue(Ej010StatelessAndCache.esEtagDebil("W/\"abc\""));
        assertFalse(Ej010StatelessAndCache.esEtagDebil("\"abc\""));
    }

    @Test
    void retoExtra03_validarIfNoneMatchConComodines() {
        assertTrue(Ej010StatelessAndCache.validarIfNoneMatchConComodines("*", "\"abc\""));
        assertTrue(Ej010StatelessAndCache.validarIfNoneMatchConComodines("\"xyz\", \"abc\"", "\"abc\""));
        assertFalse(Ej010StatelessAndCache.validarIfNoneMatchConComodines("\"xyz\"", "\"abc\""));
    }

    @Test
    void retoExtra04_normalizarEtag() {
        assertEquals("abc", Ej010StatelessAndCache.normalizarEtag("W/\"abc\""));
        assertEquals("abc", Ej010StatelessAndCache.normalizarEtag("\"abc\""));
    }

    @Test
    void retoExtra05_calcularMaxAge() {
        assertEquals(3600L, Ej010StatelessAndCache.calcularMaxAge("public, max-age=3600"));
        assertEquals(-1L, Ej010StatelessAndCache.calcularMaxAge("no-cache"));
    }

    @Test
    void retoExtra06_debeRevalidarSiempre() {
        assertTrue(Ej010StatelessAndCache.debeRevalidarSiempre("no-cache, private"));
        assertTrue(Ej010StatelessAndCache.debeRevalidarSiempre("must-revalidate"));
        assertFalse(Ej010StatelessAndCache.debeRevalidarSiempre("public, max-age=3600"));
    }

    @Test
    void retoExtra07_esMetodoSeguroYCacheable() {
        assertTrue(Ej010StatelessAndCache.esMetodoSeguroYCacheable("GET"));
        assertTrue(Ej010StatelessAndCache.esMetodoSeguroYCacheable("HEAD"));
        assertFalse(Ej010StatelessAndCache.esMetodoSeguroYCacheable("POST"));
    }

    @Test
    void retoExtra08_construirCabeceraCacheControlCompleta() {
        String cc = Ej010StatelessAndCache.construirCabeceraCacheControlCompleta(true, 3600, true);
        assertTrue(cc.contains("public"));
        assertTrue(cc.contains("max-age=3600"));
        assertTrue(cc.contains("must-revalidate"));
    }

    @Test
    void retoExtra09_esSesionStateless() {
        assertFalse(Ej010StatelessAndCache.esSesionStateless("JSESSIONID=123", "Bearer abc"));
        assertTrue(Ej010StatelessAndCache.esSesionStateless(null, "Bearer abc"));
    }

    @Test
    void retoExtra10_esRespuestaValidaPara304() {
        assertTrue(Ej010StatelessAndCache.esRespuestaValidaPara304("GET", 304));
        assertFalse(Ej010StatelessAndCache.esRespuestaValidaPara304("POST", 304));
        assertFalse(Ej010StatelessAndCache.esRespuestaValidaPara304("GET", 200));
    }
}
