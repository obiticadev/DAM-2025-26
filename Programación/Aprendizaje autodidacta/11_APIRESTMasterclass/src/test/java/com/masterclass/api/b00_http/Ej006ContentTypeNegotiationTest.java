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
}
