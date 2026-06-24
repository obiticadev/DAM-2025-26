package com.masterclass.api.b43_erp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej333ErpApiClientTest {

    private static final ObjectMapper M = new ObjectMapper();

    private static JsonNode parse(String json) {
        try {
            return M.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void construirLlamada() {
        JsonNode n = parse(Ej333ErpApiClient.construirLlamada("res.partner", "search_read"));
        assertEquals("2.0", n.get("jsonrpc").asText());
        assertEquals("call", n.get("method").asText());
        assertEquals("res.partner", n.get("params").get("model").asText());
        assertEquals("search_read", n.get("params").get("method").asText());
        assertNull(Ej333ErpApiClient.construirLlamada(null, "x")); // caso límite
    }

    @Test
    void contarRegistrosRespuesta() {
        assertEquals(2, Ej333ErpApiClient.contarRegistrosRespuesta("{\"result\":[{\"id\":1},{\"id\":2}]}"));
        assertEquals(0, Ej333ErpApiClient.contarRegistrosRespuesta("{\"result\":[]}"));
        assertEquals(-1, Ej333ErpApiClient.contarRegistrosRespuesta("{\"error\":{\"message\":\"x\"}}")); // caso límite
        assertEquals(-1, Ej333ErpApiClient.contarRegistrosRespuesta(null));
    }

    @Test
    void testRetoExtra01_esRespuestaHttpOk() {
        assertTrue(Ej333ErpApiClient.esRespuestaHttpOk(200));
        assertFalse(Ej333ErpApiClient.esRespuestaHttpOk(404));
    }

    @Test
    void testRetoExtra02_construirUrlEndpoint() {
        assertEquals("http://localhost:8069/jsonrpc",
                Ej333ErpApiClient.construirUrlEndpoint("http://localhost:8069/"));
    }

    @Test
    void testRetoExtra03_tieneError() {
        assertTrue(Ej333ErpApiClient.tieneError("{\"error\":{\"message\":\"x\"}}"));
        assertFalse(Ej333ErpApiClient.tieneError("{\"result\":[]}"));
    }

    @Test
    void testRetoExtra04_extraerMensajeError() {
        assertEquals("Access Denied",
                Ej333ErpApiClient.extraerMensajeError("{\"error\":{\"message\":\"Access Denied\"}}"));
        assertEquals("", Ej333ErpApiClient.extraerMensajeError("{\"result\":[]}"));
    }

    @Test
    void testRetoExtra05_extraerUid() {
        assertEquals(7, Ej333ErpApiClient.extraerUid("{\"result\":7}"));
        assertEquals(-1, Ej333ErpApiClient.extraerUid("{\"result\":false}"));
    }

    @Test
    void testRetoExtra06_construirDominioIgual() {
        JsonNode d = parse(Ej333ErpApiClient.construirDominioIgual("name", "Acme"));
        assertEquals("name", d.get(0).get(0).asText());
        assertEquals("=", d.get(0).get(1).asText());
        assertEquals("Acme", d.get(0).get(2).asText());
    }

    @Test
    void testRetoExtra07_construirPaginacion() {
        JsonNode p = parse(Ej333ErpApiClient.construirPaginacion(10, 20));
        assertEquals(10, p.get("limit").asInt());
        assertEquals(20, p.get("offset").asInt());
        assertThrows(IllegalArgumentException.class, () -> Ej333ErpApiClient.construirPaginacion(-1, 0));
    }

    @Test
    void testRetoExtra08_parsearIdsResultado() {
        assertEquals(List.of(1, 2, 3), Ej333ErpApiClient.parsearIdsResultado("{\"result\":[1,2,3]}"));
        assertEquals(List.of(), Ej333ErpApiClient.parsearIdsResultado("{\"error\":{}}"));
    }

    @Test
    void testRetoExtra09_extraerCampoDeRegistro() {
        String json = "{\"result\":[{\"name\":\"Acme\"},{\"name\":\"Globex\"}]}";
        assertEquals("Globex", Ej333ErpApiClient.extraerCampoDeRegistro(json, 1, "name"));
        assertEquals("", Ej333ErpApiClient.extraerCampoDeRegistro(json, 5, "name"));
    }

    @Test
    void testRetoExtra10_construirExecuteKw() {
        JsonNode n = parse(Ej333ErpApiClient.construirExecuteKw("odoo", 7, "pass", "res.partner", "read"));
        assertEquals("object", n.get("params").get("service").asText());
        assertEquals("execute_kw", n.get("params").get("method").asText());
        assertEquals(7, n.get("params").get("args").get(1).asInt());
        assertEquals("res.partner", n.get("params").get("args").get(3).asText());
    }
}
