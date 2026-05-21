package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
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

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_extraerValoresMultiplesQuery() {
        var res = Ej007UrlAndQueryParser.extraerValoresMultiplesQuery("/buscar?tag=java&tag=rest&tag=web", "tag");
        assertEquals(3, res.size());
        assertEquals("java", res.get(0));
        assertEquals("rest", res.get(1));
        assertEquals("web", res.get(2));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_queryParamsDecodificados() {
        var q = Ej007UrlAndQueryParser.queryParamsDecodificados("/p?nombre=Juan+P%C3%A9rez&citt%C3%A0=Roma");
        assertEquals("Juan Pérez", q.get("nombre"));
        assertEquals("Roma", q.get("città"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_esHttpsEsquema() {
        assertTrue(Ej007UrlAndQueryParser.esHttpsEsquema("https://example.com"));
        assertTrue(Ej007UrlAndQueryParser.esHttpsEsquema("HTTPS://example.com"));
        assertFalse(Ej007UrlAndQueryParser.esHttpsEsquema("http://example.com"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_extraerHost() {
        assertEquals("api.example.com", Ej007UrlAndQueryParser.extraerHost("https://api.example.com:8080/v1/usuarios"));
        assertEquals("", Ej007UrlAndQueryParser.extraerHost("/v1/usuarios"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_extraerPuertoConPredeterminados() {
        assertEquals(8080, Ej007UrlAndQueryParser.extraerPuertoConPredeterminados("http://localhost:8080/v1"));
        assertEquals(443, Ej007UrlAndQueryParser.extraerPuertoConPredeterminados("https://example.com"));
        assertEquals(80, Ej007UrlAndQueryParser.extraerPuertoConPredeterminados("http://example.com"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_normalizarBarrasDeRuta() {
        assertEquals("/v1/productos", Ej007UrlAndQueryParser.normalizarBarrasDeRuta("///v1//productos//"));
        assertEquals("/", Ej007UrlAndQueryParser.normalizarBarrasDeRuta("/"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_obtenerSegmentoRuta() {
        assertEquals("v1", Ej007UrlAndQueryParser.obtenerSegmentoRuta("/v1/usuarios/12", 0));
        assertEquals("usuarios", Ej007UrlAndQueryParser.obtenerSegmentoRuta("/v1/usuarios/12", 1));
        assertEquals("12", Ej007UrlAndQueryParser.obtenerSegmentoRuta("/v1/usuarios/12", 2));
        assertEquals("", Ej007UrlAndQueryParser.obtenerSegmentoRuta("/v1/usuarios/12", 5));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_construirQueryStringAlfabetica() {
        var params = Map.of("b", List.of("2"), "a", List.of("1", "3"));
        assertEquals("a=1&a=3&b=2", Ej007UrlAndQueryParser.construirQueryStringAlfabetica(params));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_esUrlAbsolutaSegura() {
        assertTrue(Ej007UrlAndQueryParser.esUrlAbsolutaSegura("https://example.com"));
        assertFalse(Ej007UrlAndQueryParser.esUrlAbsolutaSegura("https://example.com\r\nLocation: http://evil.com"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_eliminarParametroConsulta() {
        assertEquals("/p?b=2", Ej007UrlAndQueryParser.eliminarParametroConsulta("/p?a=1&b=2&a=3", "a"));
        assertEquals("/p", Ej007UrlAndQueryParser.eliminarParametroConsulta("/p?a=1", "a"));
    }
}
