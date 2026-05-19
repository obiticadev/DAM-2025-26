package com.masterclass.api.b16_xml;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej145DomSaxParsingTest {

    private static final String XML =
            "<libros><libro>A</libro><libro>B</libro><libro>C</libro></libros>";

    @Test
    void domCuentaElementos() {
        assertEquals(3, Ej145DomSaxParsing.contarConDom(XML, "libro"));
        assertEquals(1, Ej145DomSaxParsing.contarConDom(XML, "libros"));
    }

    @Test
    void saxExtraeTextosEnOrden() {
        assertEquals(List.of("A", "B", "C"),
                Ej145DomSaxParsing.textosConSax(XML, "libro"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej145DomSaxParsing.contarConDom(null, "libro"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej145DomSaxParsing.textosConSax(XML, " "));
    }
}
