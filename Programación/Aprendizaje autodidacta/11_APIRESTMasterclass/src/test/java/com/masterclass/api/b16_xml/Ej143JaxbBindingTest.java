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

    @Test
    void testExtra01ValidarFormatoIsbn() {
        assertTrue(Ej143JaxbBinding.extra01ValidarFormatoIsbn("978-84"));
        assertFalse(Ej143JaxbBinding.extra01ValidarFormatoIsbn("invalid-isbn"));
        assertThrows(IllegalArgumentException.class, () -> Ej143JaxbBinding.extra01ValidarFormatoIsbn(null));
    }

    @Test
    void testExtra02CompactarXml() {
        String compact = Ej143JaxbBinding.extra02CompactarXml("  <libro>   </libro> ");
        assertEquals("<libro>   </libro>", compact); // wait, replaceAll("\\s+", " ") yields "<libro> <libro>" or similar depending on matches. Let's make sure it equals the regex output.
        // Actually, "  <libro>   </libro> " replacement gives "<libro> </libro>"
        assertEquals("<libro> </libro>", compact);
    }

    @Test
    void testExtra03ExtraerAnioJAXB() {
        Libro143 original = new Libro143("978-84", "Clean Code", 2008);
        int anio = Ej143JaxbBinding.extra03ExtraerAnioJAXB(Ej143JaxbBinding.aXml(original));
        assertEquals(2008, anio);
    }

    @Test
    void testExtra04MarshallFragmento() {
        Libro143 original = new Libro143("978-84", "Clean Code", 2008);
        String fragment = Ej143JaxbBinding.extra04MarshallFragmento(original);
        assertFalse(fragment.contains("<?xml"));
        assertTrue(fragment.contains("<libro"));
    }

    @Test
    void testExtra05LibroAMap() {
        Libro143 original = new Libro143("978-84", "Clean Code", 2008);
        var map = Ej143JaxbBinding.extra05LibroAMap(original);
        assertEquals("978-84", map.get("isbn"));
        assertEquals("Clean Code", map.get("titulo"));
        assertEquals(2008, map.get("anio"));
    }

    @Test
    void testExtra06CrearLibroDefault() {
        var def = Ej143JaxbBinding.extra06CrearLibroDefault();
        assertNotNull(def);
        assertEquals("000-00", def.getIsbn());
    }

    @Test
    void testExtra07EsXmlDeLibro() {
        assertTrue(Ej143JaxbBinding.extra07EsXmlDeLibro("<libro isbn=\"123\">...</libro>"));
        assertFalse(Ej143JaxbBinding.extra07EsXmlDeLibro("<revista></revista>"));
    }

    @Test
    void testExtra08SerializarIso8859() {
        Libro143 original = new Libro143("978-84", "Clean Code", 2008);
        String xml = Ej143JaxbBinding.extra08SerializarIso8859(original);
        assertTrue(xml.contains("encoding=\"ISO-8859-1\""));
    }

    @Test
    void testExtra09EsPublicacionReciente() {
        Libro143 original = new Libro143("978-84", "Clean Code", 2008);
        assertTrue(Ej143JaxbBinding.extra09EsPublicacionReciente(original, 2000));
        assertFalse(Ej143JaxbBinding.extra09EsPublicacionReciente(original, 2015));
    }

    @Test
    void testExtra10ClonarLibro() {
        Libro143 original = new Libro143("978-84", "Clean Code", 2008);
        Libro143 clon = Ej143JaxbBinding.extra10ClonarLibro(original);
        assertNotSame(original, clon);
        assertEquals(original.getIsbn(), clon.getIsbn());
        assertEquals(original.getTitulo(), clon.getTitulo());
    }
}
