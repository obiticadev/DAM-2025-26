package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej002HttpResponseBuilderTest {

    @Test
    void frasesDeMotivo() {
        assertEquals("OK", Ej002HttpResponseBuilder.reasonPhrase(200));
        assertEquals("Created", Ej002HttpResponseBuilder.reasonPhrase(201));
        assertEquals("Not Found", Ej002HttpResponseBuilder.reasonPhrase(404));
        assertEquals("Unknown", Ej002HttpResponseBuilder.reasonPhrase(799));
    }

    @Test
    void construyeRespuestaCompleta() {
        Map<String, String> h = new LinkedHashMap<>();
        h.put("Content-Type", "application/json");
        String out = Ej002HttpResponseBuilder.build(200, h, "{\"ok\":true}");
        assertTrue(out.startsWith("HTTP/1.1 200 OK"), "Línea de estado incorrecta: " + out);
        assertTrue(out.contains("Content-Type: application/json"));
        assertTrue(out.contains("\n\n{\"ok\":true}") || out.contains("\r\n\r\n{\"ok\":true}"));
    }

    @Test
    void sinBody() {
        String out = Ej002HttpResponseBuilder.build(204, new LinkedHashMap<>(), null);
        assertTrue(out.startsWith("HTTP/1.1 204 No Content"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_obtenerFamiliaDeRespuesta() {
        assertEquals("Success", Ej002HttpResponseBuilder.obtenerFamiliaDeRespuesta(200));
        assertEquals("Client Error", Ej002HttpResponseBuilder.obtenerFamiliaDeRespuesta(404));
        assertEquals("Server Error", Ej002HttpResponseBuilder.obtenerFamiliaDeRespuesta(503));
        assertEquals("Redirection", Ej002HttpResponseBuilder.obtenerFamiliaDeRespuesta(301));
        assertEquals("Unknown", Ej002HttpResponseBuilder.obtenerFamiliaDeRespuesta(99));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_formatearCabeceraEstandar() {
        assertEquals("Content-Type: text/html", Ej002HttpResponseBuilder.formatearCabeceraEstandar("content-type", "text/html"));
        assertEquals("X-Cache-Status: HIT", Ej002HttpResponseBuilder.formatearCabeceraEstandar("x-cache-status", "HIT"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_generarCabeceraFechaActual() {
        String fecha = Ej002HttpResponseBuilder.generarCabeceraFechaActual();
        assertNotNull(fecha);
        assertTrue(fecha.endsWith("GMT"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_requiereCabeceraLocation() {
        assertTrue(Ej002HttpResponseBuilder.requiereCabeceraLocation(301));
        assertTrue(Ej002HttpResponseBuilder.requiereCabeceraLocation(302));
        assertFalse(Ej002HttpResponseBuilder.requiereCabeceraLocation(200));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_calcularLongitudEnBytes() {
        assertEquals(5, Ej002HttpResponseBuilder.calcularLongitudEnBytes("Hello"));
        // El emoji de cohete 🚀 toma 4 bytes en UTF-8
        assertEquals(4, Ej002HttpResponseBuilder.calcularLongitudEnBytes("🚀"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_inyectarContentLength() {
        Map<String, String> headers = Map.of("Host", "test");
        var res = Ej002HttpResponseBuilder.inyectarContentLength(headers, "🚀");
        assertEquals("4", res.get("Content-Length"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_generarLineaEstadoSegura() {
        assertEquals("HTTP/1.1 200 OK", Ej002HttpResponseBuilder.generarLineaEstadoSegura(200));
        assertThrows(IllegalArgumentException.class, () -> Ej002HttpResponseBuilder.generarLineaEstadoSegura(99));
        assertThrows(IllegalArgumentException.class, () -> Ej002HttpResponseBuilder.generarLineaEstadoSegura(600));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_extraerCharset() {
        assertEquals("UTF-8", Ej002HttpResponseBuilder.extraerCharset(Map.of("Content-Type", "application/json; charset=UTF-8")));
        assertEquals("ISO-8859-1", Ej002HttpResponseBuilder.extraerCharset(Map.of("Content-Type", "application/json")));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_esRespuestaSinCuerpo() {
        assertTrue(Ej002HttpResponseBuilder.esRespuestaSinCuerpo(204));
        assertTrue(Ej002HttpResponseBuilder.esRespuestaSinCuerpo(304));
        assertTrue(Ej002HttpResponseBuilder.esRespuestaSinCuerpo(101));
        assertFalse(Ej002HttpResponseBuilder.esRespuestaSinCuerpo(200));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_esSeguroContraResponseSplitting() {
        assertTrue(Ej002HttpResponseBuilder.esSeguroContraResponseSplitting(Map.of("X-Valid", "Good")));
        assertFalse(Ej002HttpResponseBuilder.esSeguroContraResponseSplitting(Map.of("X-Injected", "Bad\nLocation: http://evil.com")));
        assertFalse(Ej002HttpResponseBuilder.esSeguroContraResponseSplitting(Map.of("X-Injected\r\n", "Bad")));
    }
}
