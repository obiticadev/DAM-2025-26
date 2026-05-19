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
}
