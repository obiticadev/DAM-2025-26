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
}
