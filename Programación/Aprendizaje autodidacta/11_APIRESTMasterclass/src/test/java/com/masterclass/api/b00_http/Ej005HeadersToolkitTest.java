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

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_obtenerMultiplesValores() {
        var hMulti = Map.of("Accept", "text/html, application/json, text/plain");
        var res = Ej005HeadersToolkit.obtenerMultiplesValores(hMulti, "accept");
        assertEquals(3, res.size());
        assertEquals("text/html", res.get(0));
        assertEquals("application/json", res.get(1));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_bearerTokenSeguro() {
        var hSpace = Map.of("Authorization", "Bearer    customTokenHere   ");
        assertEquals("customTokenHere", Ej005HeadersToolkit.bearerTokenSeguro(hSpace));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_esEsquemaDeAutorizacion() {
        assertTrue(Ej005HeadersToolkit.esEsquemaDeAutorizacion(h, "Bearer"));
        assertTrue(Ej005HeadersToolkit.esEsquemaDeAutorizacion(h, "bearer"));
        assertFalse(Ej005HeadersToolkit.esEsquemaDeAutorizacion(h, "Basic"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_normalizarClaves() {
        var normalized = Ej005HeadersToolkit.normalizarClaves(h);
        assertTrue(normalized.containsKey("content-type"));
        assertTrue(normalized.containsKey("authorization"));
        assertFalse(normalized.containsKey("Content-Type"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_obtenerCabeceraNumerica() {
        var hNum = Map.of("Content-Length", "1024", "X-Limit", "abc");
        assertEquals(1024L, Ej005HeadersToolkit.obtenerCabeceraNumerica(hNum, "content-length"));
        assertEquals(-1L, Ej005HeadersToolkit.obtenerCabeceraNumerica(hNum, "x-limit"));
        assertEquals(-1L, Ej005HeadersToolkit.obtenerCabeceraNumerica(hNum, "X-Missing"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_esPeticionAjax() {
        var hAjax = Map.of("X-Requested-With", "XMLHttpRequest");
        assertTrue(Ej005HeadersToolkit.esPeticionAjax(hAjax));
        assertFalse(Ej005HeadersToolkit.esPeticionAjax(h));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_resolverDireccionIpCliente() {
        var hIp = Map.of("X-Forwarded-For", "192.168.1.1, 10.0.0.1");
        assertEquals("192.168.1.1", Ej005HeadersToolkit.resolverDireccionIpCliente(hIp));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_esAgenteMovil() {
        var hUA = Map.of("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X)");
        assertTrue(Ej005HeadersToolkit.esAgenteMovil(hUA));
        assertFalse(Ej005HeadersToolkit.esAgenteMovil(h));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_obtenerCabeceraFecha() {
        var hDate = Map.of("If-Modified-Since", "Sun, 06 Nov 1994 08:49:37 GMT");
        var instantOpt = Ej005HeadersToolkit.obtenerCabeceraFecha(hDate, "if-modified-since");
        assertTrue(instantOpt.isPresent());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_esPeticionCors() {
        var hCors = Map.of("Origin", "http://localhost:3000");
        assertTrue(Ej005HeadersToolkit.esPeticionCors(hCors));
        assertFalse(Ej005HeadersToolkit.esPeticionCors(h));
    }
}
