package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej056CorsConfigurationTest {

    @Test
    void origenPermitidoPorLista() {
        var h = Ej056CorsConfiguration.corsHeaders("https://app.com", List.of("https://app.com"));
        assertEquals("https://app.com", h.get("Access-Control-Allow-Origin"));
        assertTrue(h.containsKey("Access-Control-Allow-Methods"));
    }

    @Test
    void comodin() {
        var h = Ej056CorsConfiguration.corsHeaders("https://x.com", List.of("*"));
        assertEquals("*", h.get("Access-Control-Allow-Origin"));
    }

    @Test
    void origenNoPermitido() {
        assertTrue(Ej056CorsConfiguration.corsHeaders("https://evil.com",
                List.of("https://app.com")).isEmpty());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void testPasoExtra01() {
        List<String> allowed = List.of("https://*.company.com", "https://app.com");
        assertTrue(Ej056CorsConfiguration.pasoExtra01("https://sub.company.com", allowed));
        assertTrue(Ej056CorsConfiguration.pasoExtra01("https://app.com", allowed));
        assertFalse(Ej056CorsConfiguration.pasoExtra01("https://company.com", allowed));
        assertFalse(Ej056CorsConfiguration.pasoExtra01("https://another.com", allowed));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void testPasoExtra02() {
        assertTrue(Ej056CorsConfiguration.pasoExtra02("OPTIONS", Map.of("Access-Control-Request-Method", "POST")));
        assertFalse(Ej056CorsConfiguration.pasoExtra02("GET", Map.of("Access-Control-Request-Method", "POST")));
        assertFalse(Ej056CorsConfiguration.pasoExtra02("OPTIONS", Map.of()));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void testPasoExtra03() {
        Map<String, String> headers = Map.of("Access-Control-Allow-Origin", "*");
        Map<String, String> resolved = Ej056CorsConfiguration.pasoExtra03(headers, true, "https://client.com");
        assertNotNull(resolved);
        assertEquals("true", resolved.get("Access-Control-Allow-Credentials"));
        assertEquals("https://client.com", resolved.get("Access-Control-Allow-Origin"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void testPasoExtra04() {
        assertEquals("60", Ej056CorsConfiguration.pasoExtra04("10"));
        assertEquals("1800", Ej056CorsConfiguration.pasoExtra04("3600"));
        assertEquals("600", Ej056CorsConfiguration.pasoExtra04("600"));
        assertEquals("1800", Ej056CorsConfiguration.pasoExtra04("invalid"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void testPasoExtra05() {
        List<String> allowed = List.of("Content-Type", "Authorization");
        assertTrue(Ej056CorsConfiguration.pasoExtra05(List.of("content-type"), allowed));
        assertTrue(Ej056CorsConfiguration.pasoExtra05(List.of("CONTENT-TYPE", "AUTHORIZATION"), allowed));
        assertFalse(Ej056CorsConfiguration.pasoExtra05(List.of("X-Custom-Header"), allowed));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void testPasoExtra06() {
        assertTrue(Ej056CorsConfiguration.pasoExtra06("http://localhost:3000"));
        assertTrue(Ej056CorsConfiguration.pasoExtra06("http://127.0.0.1:8080"));
        assertFalse(Ej056CorsConfiguration.pasoExtra06("https://localhost"));
        assertFalse(Ej056CorsConfiguration.pasoExtra06("http://localhostcompany.com"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void testPasoExtra07() {
        Map<String, String> headers = Map.of();
        Map<String, String> exposed = Ej056CorsConfiguration.pasoExtra07(headers, List.of("X-Total-Count", "X-Limit"));
        assertNotNull(exposed);
        assertEquals("X-Total-Count, X-Limit", exposed.get("Access-Control-Expose-Headers"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void testPasoExtra08() {
        assertEquals("https://safe.com", Ej056CorsConfiguration.pasoExtra08("https://safe.com"));
        assertNull(Ej056CorsConfiguration.pasoExtra08("https://safe.com\r\nHeader-Injection: evil"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void testPasoExtra09() {
        assertTrue(Ej056CorsConfiguration.pasoExtra09("GET"));
        assertTrue(Ej056CorsConfiguration.pasoExtra09("POST"));
        assertTrue(Ej056CorsConfiguration.pasoExtra09("HEAD"));
        assertFalse(Ej056CorsConfiguration.pasoExtra09("PUT"));
        assertFalse(Ej056CorsConfiguration.pasoExtra09("DELETE"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void testPasoExtra10() {
        assertTrue(Ej056CorsConfiguration.pasoExtra10("Accept", "text/html"));
        assertTrue(Ej056CorsConfiguration.pasoExtra10("Content-Type", "application/x-www-form-urlencoded"));
        assertFalse(Ej056CorsConfiguration.pasoExtra10("Content-Type", "application/json"));
        assertFalse(Ej056CorsConfiguration.pasoExtra10("X-Custom", "value"));
    }
}
