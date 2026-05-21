package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej001HttpRequestParserTest {

    private static final String RAW = "POST /usuarios?x=1 HTTP/1.1\n"
            + "Host: api.demo\n"
            + "Content-Type: application/json\n"
            + "\n"
            + "{\"nombre\":\"Ana\"}";

    @Test
    void extraeMetodo() {
        assertEquals("POST", Ej001HttpRequestParser.method(RAW));
    }

    @Test
    void extraeRutaConQuery() {
        assertEquals("/usuarios?x=1", Ej001HttpRequestParser.path(RAW));
    }

    @Test
    void extraeHeaders() {
        var h = Ej001HttpRequestParser.headers(RAW);
        assertEquals("api.demo", h.get("Host"));
        assertEquals("application/json", h.get("Content-Type"));
        assertEquals(2, h.size());
    }

    @Test
    void extraeBody() {
        assertEquals("{\"nombre\":\"Ana\"}", Ej001HttpRequestParser.body(RAW));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_esPeticionNulaOVacia() {
        assertTrue(Ej001HttpRequestParser.esPeticionNulaOVacia(null));
        assertTrue(Ej001HttpRequestParser.esPeticionNulaOVacia(""));
        assertTrue(Ej001HttpRequestParser.esPeticionNulaOVacia("   "));
        assertTrue(Ej001HttpRequestParser.esPeticionNulaOVacia("\r\n\t"));
        assertFalse(Ej001HttpRequestParser.esPeticionNulaOVacia("GET / HTTP/1.1"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_extraerPrimeraLineaCompleta() {
        assertEquals("GET /index.html HTTP/1.1", Ej001HttpRequestParser.extraerPrimeraLineaCompleta("GET /index.html HTTP/1.1\r\nHost: loc"));
        assertEquals("GET /index.html HTTP/1.1", Ej001HttpRequestParser.extraerPrimeraLineaCompleta("GET /index.html HTTP/1.1\nHost: loc"));
        assertEquals("", Ej001HttpRequestParser.extraerPrimeraLineaCompleta(null));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_validarMetodoSoportado() {
        assertTrue(Ej001HttpRequestParser.validarMetodoSoportado("GET / HTTP/1.1"));
        assertTrue(Ej001HttpRequestParser.validarMetodoSoportado("post /usuarios HTTP/1.1"));
        assertTrue(Ej001HttpRequestParser.validarMetodoSoportado("DELETE /1 HTTP/1.1"));
        assertFalse(Ej001HttpRequestParser.validarMetodoSoportado("PATCHY / HTTP/1.1"));
        assertFalse(Ej001HttpRequestParser.validarMetodoSoportado("INVALID / HTTP/1.1"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_extraerVersionHttp() {
        assertEquals("HTTP/1.1", Ej001HttpRequestParser.extraerVersionHttp("GET / HTTP/1.1\n"));
        assertEquals("HTTP/2.0", Ej001HttpRequestParser.extraerVersionHttp("POST /api HTTP/2.0\r\n"));
        assertEquals("", Ej001HttpRequestParser.extraerVersionHttp("GET /"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_validarRutaAbsoluta() {
        assertTrue(Ej001HttpRequestParser.validarRutaAbsoluta("GET /productos HTTP/1.1"));
        assertFalse(Ej001HttpRequestParser.validarRutaAbsoluta("GET productos HTTP/1.1"));
        assertFalse(Ej001HttpRequestParser.validarRutaAbsoluta("GET http://google.com/ HTTP/1.1"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_extraerQueryString() {
        assertEquals("q=java&lang=es", Ej001HttpRequestParser.extraerQueryString("GET /buscar?q=java&lang=es HTTP/1.1"));
        assertEquals("", Ej001HttpRequestParser.extraerQueryString("GET /buscar HTTP/1.1"));
        assertEquals("id=10", Ej001HttpRequestParser.extraerQueryString("GET /?id=10 HTTP/1.1"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_extraerCabeceraSegura() {
        String request = "GET / HTTP/1.1\nContent-Type: text/html\nAuthorization: Bearer xyz\n\n";
        assertEquals("text/html", Ej001HttpRequestParser.extraerCabeceraSegura(request, "content-type"));
        assertEquals("text/html", Ej001HttpRequestParser.extraerCabeceraSegura(request, "Content-Type"));
        assertEquals("Bearer xyz", Ej001HttpRequestParser.extraerCabeceraSegura(request, "AUTHORIZATION"));
        assertEquals("", Ej001HttpRequestParser.extraerCabeceraSegura(request, "Accept"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_esConexionCerrada() {
        String req1 = "GET / HTTP/1.1\nConnection: close\n\n";
        String req2 = "GET / HTTP/1.1\nConnection: keep-alive\n\n";
        String req3 = "GET / HTTP/1.1\nCONNECTION: CLOSE\n\n";
        assertTrue(Ej001HttpRequestParser.esConexionCerrada(req1));
        assertFalse(Ej001HttpRequestParser.esConexionCerrada(req2));
        assertTrue(Ej001HttpRequestParser.esConexionCerrada(req3));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_contieneCuerpoChunky() {
        String req1 = "POST /upload HTTP/1.1\nTransfer-Encoding: chunked\n\n";
        String req2 = "POST /upload HTTP/1.1\nContent-Length: 15\n\n";
        assertTrue(Ej001HttpRequestParser.contieneCuerpoChunky(req1));
        assertFalse(Ej001HttpRequestParser.contieneCuerpoChunky(req2));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_obtenerCuerpoSeguroConContentLength() {
        String reqValid = "POST / HTTP/1.1\nContent-Length: 5\n\nHello World";
        String reqInvalid = "POST / HTTP/1.1\n\nHello World";
        String reqShort = "POST / HTTP/1.1\nContent-Length: 50\n\nShort";
        
        assertEquals("Hello", Ej001HttpRequestParser.obtenerCuerpoSeguroConContentLength(reqValid));
        assertEquals("Hello World", Ej001HttpRequestParser.obtenerCuerpoSeguroConContentLength(reqInvalid));
        assertEquals("Short", Ej001HttpRequestParser.obtenerCuerpoSeguroConContentLength(reqShort));
    }
}
