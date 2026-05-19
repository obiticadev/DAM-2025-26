package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej007UrlAndQueryParserTest {

    @Test
    void pathSinQuery() {
        assertEquals("/productos/12", Ej007UrlAndQueryParser.pathOnly("/productos/12?expand=true"));
        assertEquals("/productos", Ej007UrlAndQueryParser.pathOnly("/productos"));
    }

    @Test
    void queryParams() {
        var q = Ej007UrlAndQueryParser.queryParams("/p?expand=true&lang=es");
        assertEquals("true", q.get("expand"));
        assertEquals("es", q.get("lang"));
        assertEquals(2, q.size());
    }

    @Test
    void sinQueryDevuelveVacio() {
        assertTrue(Ej007UrlAndQueryParser.queryParams("/p").isEmpty());
    }
}
