package com.masterclass.api.b16_xml;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej143JaxbBindingTest {

    @Test
    void serializaConRaizYAtributo() {
        String xml = Ej143JaxbBinding.aXml(new Libro143("978-84", "Clean Code", 2008));
        assertNotNull(xml);
        assertTrue(xml.contains("<libro"));
        assertTrue(xml.contains("isbn=\"978-84\""));
        assertTrue(xml.contains("Clean Code"));
    }

    @Test
    void roundTripPreservaEstado() {
        Libro143 original = new Libro143("978-84", "Clean Code", 2008);
        Libro143 ida = Ej143JaxbBinding.desdeXml(Ej143JaxbBinding.aXml(original));
        assertEquals("978-84", ida.getIsbn());
        assertEquals("Clean Code", ida.getTitulo());
        assertEquals(2008, ida.getAnio());
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class, () -> Ej143JaxbBinding.aXml(null));
        assertThrows(IllegalArgumentException.class, () -> Ej143JaxbBinding.desdeXml(" "));
    }
}
