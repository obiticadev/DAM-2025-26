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

    @Test
    void testExtra01ValidarNombre() {
        var c = new Cliente144(1, "Bob", false);
        assertTrue(Ej144JacksonXml.extra01ValidarNombre(c));
        var invalid = new Cliente144(2, "", false);
        assertFalse(Ej144JacksonXml.extra01ValidarNombre(invalid));
    }

    @Test
    void testExtra02NombreMayusculas() {
        var c = new Cliente144(1, "Bob", false);
        assertEquals("BOB", Ej144JacksonXml.extra02NombreMayusculas(c));
    }

    @Test
    void testExtra03ExtraerIdJackson() {
        var c = new Cliente144(15, "Bob", false);
        assertEquals(15, Ej144JacksonXml.extra03ExtraerIdJackson(Ej144JacksonXml.aXml(c)));
    }

    @Test
    void testExtra04JacksonFormateado() {
        var c = new Cliente144(1, "Bob", false);
        String formatted = Ej144JacksonXml.extra04JacksonFormateado(c);
        assertTrue(formatted.contains("\n") || formatted.contains("\r"));
    }

    @Test
    void testExtra05ClienteAMap() {
        var c = new Cliente144(1, "Bob", false);
        var map = Ej144JacksonXml.extra05ClienteAMap(c);
        assertEquals(1, map.get("id"));
        assertEquals("Bob", map.get("nombre"));
        assertEquals(false, map.get("vip"));
    }

    @Test
    void testExtra06CrearClienteVip() {
        var c = Ej144JacksonXml.extra06CrearClienteVip();
        assertNotNull(c);
        assertTrue(c.isVip());
    }

    @Test
    void testExtra07EsXmlDeCliente() {
        assertTrue(Ej144JacksonXml.extra07EsXmlDeCliente("<cliente id=\"1\">...</cliente>"));
        assertFalse(Ej144JacksonXml.extra07EsXmlDeCliente("<usuario></usuario>"));
    }

    @Test
    void testExtra08SerializarConCabecera() {
        var c = new Cliente144(1, "Bob", false);
        String xml = Ej144JacksonXml.extra08SerializarConCabecera(c);
        assertTrue(xml.startsWith("<?xml"));
    }

    @Test
    void testExtra09TieneDescuentoEspecial() {
        var c1 = new Cliente144(1, "Ada", true);
        var c2 = new Cliente144(2, "Ada", false);
        assertTrue(Ej144JacksonXml.extra09TieneDescuentoEspecial(c1));
        assertFalse(Ej144JacksonXml.extra09TieneDescuentoEspecial(c2));
    }

    @Test
    void testExtra10ClonarCliente() {
        var c = new Cliente144(1, "Bob", false);
        var clon = Ej144JacksonXml.extra10ClonarCliente(c);
        assertNotSame(c, clon);
        assertEquals(c.getId(), clon.getId());
        assertEquals(c.getNombre(), clon.getNombre());
    }
}
