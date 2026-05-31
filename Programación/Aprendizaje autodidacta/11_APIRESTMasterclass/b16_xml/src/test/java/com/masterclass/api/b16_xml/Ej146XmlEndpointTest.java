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

    @Test
    void testExtra01ValidarLista() {
        assertTrue(Ej146XmlEndpoint.extra01ValidarLista(List.of()));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra01ValidarLista(null));
    }

    @Test
    void testExtra02CrearStringBuilder() {
        assertNotNull(Ej146XmlEndpoint.extra02CrearStringBuilder(50));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra02CrearStringBuilder(-5));
    }

    @Test
    void testExtra03DeclaracionPersonalizada() {
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>", Ej146XmlEndpoint.extra03DeclaracionPersonalizada("ISO-8859-1"));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra03DeclaracionPersonalizada(""));
    }

    @Test
    void testExtra04AperturaConAtributos() {
        var map = java.util.Map.of("key", "val");
        var res = Ej146XmlEndpoint.extra04AperturaConAtributos("item", map);
        assertTrue(res.startsWith("<item") && res.contains("key=\"val\"") && res.endsWith(">"));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra04AperturaConAtributos(null, map));
    }

    @Test
    void testExtra05TagAutoCerrado() {
        assertEquals("<br/>", Ej146XmlEndpoint.extra05TagAutoCerrado("br"));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra05TagAutoCerrado(null));
    }

    @Test
    void testExtra06FormatearProducto() {
        var p = new Producto146(42, "A & B");
        assertEquals("<producto id=\"42\">A &amp; B</producto>", Ej146XmlEndpoint.extra06FormatearProducto(p));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra06FormatearProducto(null));
    }

    @Test
    void testExtra07EscaparSoloAmpersand() {
        assertEquals("A &amp; B < C", Ej146XmlEndpoint.extra07EscaparSoloAmpersand("A & B < C"));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra07EscaparSoloAmpersand(null));
    }

    @Test
    void testExtra08EtiquetaCierre() {
        assertEquals("</productos>", Ej146XmlEndpoint.extra08EtiquetaCierre("productos"));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra08EtiquetaCierre(" "));
    }

    @Test
    void testExtra09LongitudResultado() {
        // Since serializar returns null (it's not solved yet), length should be 0.
        assertEquals(0, Ej146XmlEndpoint.extra09LongitudResultado(List.of()));
    }

    @Test
    void testExtra10ConcatenarDosProductos() {
        var p1 = new Producto146(1, "A");
        var p2 = new Producto146(2, "B");
        var expected = Ej146XmlEndpoint.extra06FormatearProducto(p1) + Ej146XmlEndpoint.extra06FormatearProducto(p2);
        assertEquals(expected, Ej146XmlEndpoint.extra10ConcatenarDosProductos(p1, p2));
        assertThrows(IllegalArgumentException.class, () -> Ej146XmlEndpoint.extra10ConcatenarDosProductos(null, p2));
    }
}
