package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej177OpenApiSwaggerTest {

    @Test
    @SuppressWarnings("unchecked")
    void documentaUnEndpoint() {
        Map<String, Object> doc = Ej177OpenApiSwagger.construirDocumento("Demo", "1.0",
                List.of(new EndpointMeta177("/users", "GET", "Lista usuarios")));
        assertEquals("3.0.1", doc.get("openapi"));
        Map<String, Object> info = (Map<String, Object>) doc.get("info");
        assertEquals("Demo", info.get("title"));
        Map<String, Object> paths = (Map<String, Object>) doc.get("paths");
        Map<String, Object> get = (Map<String, Object>) ((Map<String, Object>) paths.get("/users")).get("get");
        assertEquals("Lista usuarios", get.get("summary"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej177OpenApiSwagger.construirDocumento("Demo", "1.0", null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej177OpenApiSwagger.construirDocumento(" ", "1.0", List.of()));
    }

    @Test
    void testRetoExtra01_esRutaValida() {
        // Comprueba ruta valida.
        assertTrue(Ej177OpenApiSwagger.esRutaValida("/users"));
    }

    @Test
    void testRetoExtra02_esMetodoValido() {
        // Comprueba metodo HTTP.
        assertTrue(Ej177OpenApiSwagger.esMetodoValido("GET"));
    }

    @Test
    void testRetoExtra03_crearMeta() {
        // Crea metadato.
        assertNotNull(Ej177OpenApiSwagger.crearMeta("/a", "GET", "d"));
    }

    @Test
    void testRetoExtra04_extraerRuta() {
        // Extrae ruta.
        assertEquals("/a", Ej177OpenApiSwagger.extraerRuta(new EndpointMeta177("/a", "GET", "d")));
    }

    @Test
    void testRetoExtra05_extraerMetodo() {
        // Extrae metodo.
        assertEquals("GET", Ej177OpenApiSwagger.extraerMetodo(new EndpointMeta177("/a", "GET", "d")));
    }

    @Test
    void testRetoExtra06_extraerDescripcion() {
        // Extrae descripcion.
        assertEquals("d", Ej177OpenApiSwagger.extraerDescripcion(new EndpointMeta177("/a", "GET", "d")));
    }

    @Test
    void testRetoExtra07_esVersionSemantica() {
        // Comprueba version semantica.
        assertTrue(Ej177OpenApiSwagger.esVersionSemantica("1.0.0"));
    }

    @Test
    void testRetoExtra08_esMetaCompleto() {
        // Comprueba campos no nulos.
        assertTrue(Ej177OpenApiSwagger.esMetaCompleto(new EndpointMeta177("/a", "GET", "d")));
    }

    @Test
    void testRetoExtra09_formatearRuta() {
        // Formatea ruta.
        assertEquals("/a", Ej177OpenApiSwagger.formatearRuta(" /a "));
    }

    @Test
    void testRetoExtra10_esColeccionEndpoint() {
        // Comprueba lista no vacia.
        assertTrue(Ej177OpenApiSwagger.esColeccionEndpoint(java.util.List.of(new EndpointMeta177("/a", "GET", "d"))));
    }

}