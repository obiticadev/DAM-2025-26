package com.masterclass.api.b16_xml;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej144JacksonXmlTest {

    @Test
    void serializaConRaizCliente() {
        String xml = Ej144JacksonXml.aXml(new Cliente144(7, "Ada", true));
        assertNotNull(xml);
        assertFalse(xml.isBlank());
        assertTrue(xml.contains("cliente"));
        assertTrue(xml.contains("Ada"));
    }

    @Test
    void roundTripPreservaEstado() {
        Cliente144 original = new Cliente144(7, "Ada", true);
        Cliente144 ida = Ej144JacksonXml.desdeXml(Ej144JacksonXml.aXml(original));
        assertEquals(7, ida.getId());
        assertEquals("Ada", ida.getNombre());
        assertTrue(ida.isVip());
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class, () -> Ej144JacksonXml.aXml(null));
        assertThrows(IllegalArgumentException.class, () -> Ej144JacksonXml.desdeXml(""));
    }
}
