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
}
