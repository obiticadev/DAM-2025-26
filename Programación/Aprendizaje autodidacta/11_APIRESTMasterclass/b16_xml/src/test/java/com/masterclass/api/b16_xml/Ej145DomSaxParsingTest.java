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

    @Test
    void testExtra01EsXmlBienFormado() {
        assertTrue(Ej145DomSaxParsing.extra01EsXmlBienFormado("<tag></tag>"));
        assertFalse(Ej145DomSaxParsing.extra01EsXmlBienFormado("<tag"));
    }

    @Test
    void testExtra02ConvertirEtiquetasAMayusculas() {
        assertEquals("<LIBRO>A</LIBRO>", Ej145DomSaxParsing.extra02ConvertirEtiquetasAMayusculas("<libro>A</libro>"));
    }

    @Test
    void testExtra03ObtenerAtributoPorDom() {
        String testXml = "<libros><libro id=\"123\">A</libro></libros>";
        assertEquals("123", Ej145DomSaxParsing.extra03ObtenerAtributoPorDom(testXml, "libro", "id"));
    }

    @Test
    void testExtra04ObtenerPrimerTextoPorDom() {
        assertEquals("A", Ej145DomSaxParsing.extra04ObtenerPrimerTextoPorDom(XML, "libro"));
    }

    @Test
    void testExtra05ContarMultiplesEtiquetas() {
        assertEquals(4, Ej145DomSaxParsing.extra05ContarMultiplesEtiquetas(XML, List.of("libro", "libros")));
    }

    @Test
    void testExtra06ContieneDoctype() {
        assertTrue(Ej145DomSaxParsing.extra06ContieneDoctype("<!DOCTYPE html><html></html>"));
        assertFalse(Ej145DomSaxParsing.extra06ContieneDoctype("<root></root>"));
    }

    @Test
    void testExtra07EsPadreDirecto() {
        assertTrue(Ej145DomSaxParsing.extra07EsPadreDirecto(XML, "libros", "libro"));
        assertFalse(Ej145DomSaxParsing.extra07EsPadreDirecto(XML, "libro", "libros"));
    }

    @Test
    void testExtra08ExtraerTodoTextoDom() {
        assertEquals("ABC", Ej145DomSaxParsing.extra08ExtraerTodoTextoDom(XML));
    }

    @Test
    void testExtra09ValidarEtiquetaRaiz() {
        assertTrue(Ej145DomSaxParsing.extra09ValidarEtiquetaRaiz(XML, "libros"));
        assertFalse(Ej145DomSaxParsing.extra09ValidarEtiquetaRaiz(XML, "libro"));
    }

    @Test
    void testExtra10ContarNodosElemento() {
        assertEquals(4, Ej145DomSaxParsing.extra10ContarNodosElemento(XML));
    }
}
