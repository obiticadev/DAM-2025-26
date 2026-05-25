package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej006ContentTypeNegotiationTest {

    private final List<String> supported = List.of("application/json", "application/xml");

    @Test
    void clienteElige() {
        assertEquals("application/xml",
                Ej006ContentTypeNegotiation.negotiate("application/xml", supported));
    }

    @Test
    void preferenciaServidorConVarios() {
        assertEquals("application/json",
                Ej006ContentTypeNegotiation.negotiate("application/xml, application/json", supported));
    }

    @Test
    void comodin() {
        assertEquals("application/json",
                Ej006ContentTypeNegotiation.negotiate("*/*", supported));
    }

    @Test
    void incompatible() {
        assertEquals("",
                Ej006ContentTypeNegotiation.negotiate("text/csv", supported));
    }

    @Test
    void retoExtra01_extraerCalidad() {
        assertEquals(0.9, Ej006ContentTypeNegotiation.extraerCalidad("text/html;q=0.9"));
        assertEquals(1.0, Ej006ContentTypeNegotiation.extraerCalidad("text/html"));
        assertEquals(1.0, Ej006ContentTypeNegotiation.extraerCalidad("text/html;q=abc"));
    }

    @Test
    void retoExtra02_ordenarPorCalidad() {
        var sorted = Ej006ContentTypeNegotiation.ordenarPorCalidad("text/plain;q=0.5, application/json;q=0.9, text/html");
        assertEquals(3, sorted.size());
        assertEquals("text/html", sorted.get(0)); // q = 1.0 (default)
        assertEquals("application/json", sorted.get(1)); // q = 0.9
        assertEquals("text/plain", sorted.get(2)); // q = 0.5
    }

    @Test
    void retoExtra03_esMimeSoportado() {
        var list = List.of("text/html", "image/*");
        assertTrue(Ej006ContentTypeNegotiation.esMimeSoportado("image/png", list));
        assertTrue(Ej006ContentTypeNegotiation.esMimeSoportado("text/html", list));
        assertFalse(Ej006ContentTypeNegotiation.esMimeSoportado("text/plain", list));
    }

    @Test
    void retoExtra04_esXml() {
        assertTrue(Ej006ContentTypeNegotiation.esXml("application/xml"));
        assertTrue(Ej006ContentTypeNegotiation.esXml("application/soap+xml"));
        assertFalse(Ej006ContentTypeNegotiation.esXml("application/json"));
    }

    @Test
    void retoExtra05_esJson() {
        assertTrue(Ej006ContentTypeNegotiation.esJson("application/json"));
        assertTrue(Ej006ContentTypeNegotiation.esJson("application/ld+json"));
        assertFalse(Ej006ContentTypeNegotiation.esJson("text/html"));
    }

    @Test
    void retoExtra06_normalizarMime() {
        assertEquals("text/html", Ej006ContentTypeNegotiation.normalizarMime("  TEXT/html;charset=utf-8 "));
        assertEquals("application/json", Ej006ContentTypeNegotiation.normalizarMime("application/json;q=1.0"));
    }

    @Test
    void retoExtra07_clienteAceptaHtml() {
        assertTrue(Ej006ContentTypeNegotiation.clienteAceptaHtml("text/html, application/xhtml+xml"));
        assertTrue(Ej006ContentTypeNegotiation.clienteAceptaHtml("*/*"));
        assertFalse(Ej006ContentTypeNegotiation.clienteAceptaHtml("application/json"));
    }

    @Test
    void retoExtra08_negociarConCalidad() {
        var supported = List.of("application/json", "application/xml");
        assertEquals("application/xml", Ej006ContentTypeNegotiation.negociarConCalidad("application/json;q=0.5, application/xml;q=0.9", supported));
    }

    @Test
    void retoExtra09_esTipoDeContenidoValido() {
        assertTrue(Ej006ContentTypeNegotiation.esTipoDeContenidoValido("application/json"));
        assertFalse(Ej006ContentTypeNegotiation.esTipoDeContenidoValido("invalid-mime"));
        assertFalse(Ej006ContentTypeNegotiation.esTipoDeContenidoValido(""));
    }

    @Test
    void retoExtra10_extraerSubtipo() {
        assertEquals("json", Ej006ContentTypeNegotiation.extraerSubtipo("application/json"));
        assertEquals("xhtml+xml", Ej006ContentTypeNegotiation.extraerSubtipo("application/xhtml+xml"));
        assertEquals("", Ej006ContentTypeNegotiation.extraerSubtipo("invalid"));
    }
}
