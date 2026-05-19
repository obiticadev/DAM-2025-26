package com.masterclass.api.b16_xml;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej146XmlEndpointTest {

    @Test
    void serializaListaConRaiz() {
        String xml = Ej146XmlEndpoint.serializar(
                List.of(new Producto146(1, "Cafe"), new Producto146(2, "Te")));
        assertNotNull(xml);
        assertTrue(xml.contains("<productos>"));
        assertTrue(xml.contains("</productos>"));
        assertTrue(xml.contains("id=\"1\""));
        assertTrue(xml.contains("Cafe"));
    }

    @Test
    void escapaCaracteresReservados() {
        assertEquals("a&amp;b", Ej146XmlEndpoint.escaparXml("a&b"));
        assertEquals("&lt;x&gt;", Ej146XmlEndpoint.escaparXml("<x>"));
        assertEquals("", Ej146XmlEndpoint.escaparXml(""));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej146XmlEndpoint.serializar(null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej146XmlEndpoint.escaparXml(null));
    }
}
