package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej055ContentNegotiationTest {

    private final List<String> p = List.of("application/json", "application/xml");

    @Test
    void exacto() {
        assertEquals("application/xml", Ej055ContentNegotiation.resolve("application/xml", p));
    }

    @Test
    void comodinTotal() {
        assertEquals("application/json", Ej055ContentNegotiation.resolve("*/*", p));
    }

    @Test
    void comodinSubtipo() {
        assertEquals("application/json", Ej055ContentNegotiation.resolve("application/*", p));
    }

    @Test
    void noAceptable() {
        assertEquals("", Ej055ContentNegotiation.resolve("text/csv", p));
    }

    @Test
    void testPasoExtra01() {
        assertTrue(Ej055ContentNegotiation.pasoExtra01(List.of("application/json", "text/plain")));
        assertFalse(Ej055ContentNegotiation.pasoExtra01(List.of("application/json", "invalid-media-type")));
        assertFalse(Ej055ContentNegotiation.pasoExtra01(List.of("", "text/plain")));
    }

    @Test
    void testPasoExtra02() {
        List<String> sorted = Ej055ContentNegotiation.pasoExtra02("text/plain;q=0.5, application/json;q=0.9, text/html");
        assertNotNull(sorted);
        assertEquals(3, sorted.size());
        assertEquals("text/html", sorted.get(0)); // Default q=1.0 has highest quality
        assertEquals("application/json", sorted.get(1));
        assertEquals("text/plain", sorted.get(2));
    }

    @Test
    void testPasoExtra03() {
        assertTrue(Ej055ContentNegotiation.pasoExtra03("text/*", "text/plain"));
        assertTrue(Ej055ContentNegotiation.pasoExtra03("application/json", "application/json"));
        assertFalse(Ej055ContentNegotiation.pasoExtra03("text/*", "application/json"));
    }

    @Test
    void testPasoExtra04() {
        // El cliente prefiere xml (0.9) sobre json (0.8), a pesar de que el servidor prefiere json en su lista
        String best = Ej055ContentNegotiation.pasoExtra04("application/json;q=0.8, application/xml;q=0.9", p);
        assertEquals("application/xml", best);
    }

    @Test
    void testPasoExtra05() {
        assertEquals("application/json", Ej055ContentNegotiation.pasoExtra05("json"));
        assertEquals("application/xml", Ej055ContentNegotiation.pasoExtra05("xml"));
        assertEquals("text/plain", Ej055ContentNegotiation.pasoExtra05("text"));
        assertEquals("application/octet-stream", Ej055ContentNegotiation.pasoExtra05("unknown"));
    }

    @Test
    void testPasoExtra06() {
        assertEquals("utf-8", Ej055ContentNegotiation.pasoExtra06("text/plain;charset=utf-8"));
        assertEquals("UTF-8", Ej055ContentNegotiation.pasoExtra06("application/json"));
    }

    @Test
    void testPasoExtra07() {
        assertTrue(Ej055ContentNegotiation.pasoExtra07("application/vnd.mycompany.v1+json"));
        assertFalse(Ej055ContentNegotiation.pasoExtra07("application/json"));
    }

    @Test
    void testPasoExtra08() {
        assertEquals("", Ej055ContentNegotiation.pasoExtra08("*/*", p));
        assertEquals("application/json", Ej055ContentNegotiation.pasoExtra08("application/json", p));
    }

    @Test
    void testPasoExtra09() {
        assertEquals("application/json;version=2", Ej055ContentNegotiation.pasoExtra09("application/json;version=2", List.of("application/json")));
        assertEquals("application/json", Ej055ContentNegotiation.pasoExtra09("application/json", List.of("application/json")));
    }

    @Test
    void testPasoExtra10() {
        assertTrue(Ej055ContentNegotiation.pasoExtra10("application/json"));
        assertTrue(Ej055ContentNegotiation.pasoExtra10("text/plain;q=0.8"));
        assertFalse(Ej055ContentNegotiation.pasoExtra10("invalid-format-without-slash"));
    }
}
