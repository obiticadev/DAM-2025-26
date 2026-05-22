package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class Ej152EmbeddedVsReferencesTest {

    @Test
    @SuppressWarnings("unchecked")
    void embebeLineasEnElDocumento() {
        Map<String, Object> doc = Ej152EmbeddedVsReferences.documentoEmbebido(
                "p1", List.of(new Linea152("café", 2), new Linea152("té", 1)));
        assertEquals("p1", doc.get("_id"));
        List<Map<String, Object>> lineas = (List<Map<String, Object>>) doc.get("lineas");
        assertEquals(2, lineas.size());
        assertEquals("café", lineas.get(0).get("producto"));
    }

    @Test
    void resuelveReferenciaExistente() {
        assertEquals("Ana",
                Ej152EmbeddedVsReferences.resolverReferencia("c1", Map.of("c1", "Ana")));
    }

    @Test
    void referenciaRotaLanza() {
        assertThrows(NoSuchElementException.class,
                () -> Ej152EmbeddedVsReferences.resolverReferencia("x", Map.of("c1", "Ana")));
    }

    @Test
    void argumentosInvalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej152EmbeddedVsReferences.documentoEmbebido("", List.of()));
        assertThrows(IllegalArgumentException.class,
                () -> Ej152EmbeddedVsReferences.resolverReferencia("c1", null));
    }

    @Test
    void testRetoExtra01_esRelacionEmbebida() {
        // Determina si la relacion se modela con documentos embebidos.
        assertTrue(Ej152EmbeddedVsReferences.esRelacionEmbebida("embedded"));
    }

    @Test
    void testRetoExtra02_esRelacionReferenciada() {
        // Determina si la relacion se modela mediante DBRef o ID de referencia.
        assertTrue(Ej152EmbeddedVsReferences.esRelacionReferenciada("referenced"));
    }

    @Test
    void testRetoExtra03_crearReferenciaDbRef() {
        // Genera la estructura de un DBRef standard.
        assertTrue(Ej152EmbeddedVsReferences.crearReferenciaDbRef("users", "123").contains("$ref"));
    }

    @Test
    void testRetoExtra04_esReferenciaValida() {
        // Comprueba la estructura de un DBRef.
        assertTrue(Ej152EmbeddedVsReferences.esReferenciaValida("{\"$ref\":\"u\",\"$id\":\"1\"}"));
    }

    @Test
    void testRetoExtra05_esDocumentoGrande() {
        // Advierte si el documento excede el limite teorico seguro (e.g. 16MB) de MongoDB BSON.
        assertTrue(Ej152EmbeddedVsReferences.esDocumentoGrande(20 * 1024 * 1024L));
    }

    @Test
    void testRetoExtra06_contieneIdsDuplicados() {
        // Verifica si la lista de referencias tiene ids repetidos.
        assertTrue(Ej152EmbeddedVsReferences.contieneIdsDuplicados(java.util.List.of("1", "1")));
    }

    @Test
    void testRetoExtra07_esEmbeddedSeguroNivel() {
        // Valida que la profundidad del arbol embebido no sature lecturas (maximo 5 niveles).
        assertTrue(Ej152EmbeddedVsReferences.esEmbeddedSeguroNivel(3));
    }

    @Test
    void testRetoExtra08_extraerNombreDeColeccionReferenciada() {
        // Obtiene la coleccion destino de una referencia DBRef.
        assertEquals("users", Ej152EmbeddedVsReferences.extraerNombreDeColeccionReferenciada("{\"$ref\":\"users\"}"));
    }

    @Test
    void testRetoExtra09_esExcepcionReferenciaRota() {
        // Determina si el error apunta a una referencia no resuelta.
        assertTrue(Ej152EmbeddedVsReferences.esExcepcionReferenciaRota(new IllegalArgumentException("reference")));
    }

    @Test
    void testRetoExtra10_crearJsonReferencia() {
        // Genera el formato simple de clave ajena referenciada.
        assertEquals("{\"$id\":\"5\"}", Ej152EmbeddedVsReferences.crearJsonReferencia("5"));
    }

}