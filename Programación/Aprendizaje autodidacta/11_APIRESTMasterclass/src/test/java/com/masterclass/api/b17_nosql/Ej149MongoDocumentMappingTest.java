package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej149MongoDocumentMappingTest {

    @Test
    void serializaConClaveUnderscoreId() {
        Map<String, Object> doc = Ej149MongoDocumentMapping.aDocumento(new Pedido149("p1", "ana", 99.5));
        assertEquals("p1", doc.get("_id"));
        assertEquals("ana", doc.get("cliente"));
        assertEquals(99.5, ((Number) doc.get("total")).doubleValue());
        assertFalse(doc.containsKey("id"));
    }

    @Test
    void mapeoSimetrico() {
        Pedido149 p = new Pedido149("p9", "leo", 12.0);
        assertEquals(p, Ej149MongoDocumentMapping.desdeDocumento(Ej149MongoDocumentMapping.aDocumento(p)));
    }

    @Test
    void documentoSinIdFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej149MongoDocumentMapping.desdeDocumento(Map.of("cliente", "ana")));
    }

    @Test
    void nullsInvalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej149MongoDocumentMapping.aDocumento(null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej149MongoDocumentMapping.desdeDocumento(null));
    }

    @Test
    void testRetoExtra01_esNombreColeccionValido() {
        // Valida nomenclatura de coleccion en Mongo (minusculas, sin caracteres prohibidos).
        assertTrue(Ej149MongoDocumentMapping.esNombreColeccionValido("users"));
    }

    @Test
    void testRetoExtra02_esIdMongoValido() {
        // Valida que el string cumpla con la estructura de un ObjectId (24 hex chars).
        assertTrue(Ej149MongoDocumentMapping.esIdMongoValido("507f1f77bcf86cd799439011"));
    }

    @Test
    void testRetoExtra03_crearIdMongoNuevo() {
        // Genera una representacion ObjectId aleatoria.
        assertNotNull(Ej149MongoDocumentMapping.crearIdMongoNuevo());
    }

    @Test
    void testRetoExtra04_esIndiceCompuestoValido() {
        // Valida la definicion de indice compuesto.
        assertTrue(Ej149MongoDocumentMapping.esIndiceCompuestoValido("{\"a\":1}"));
    }

    @Test
    void testRetoExtra05_esCampoVocal() {
        // Determina si el valor mapeado empieza por vocal.
        assertTrue(Ej149MongoDocumentMapping.esCampoVocal("ada"));
    }

    @Test
    void testRetoExtra06_esDocumentoJsonValido() {
        // Verifica si la representacion cumple la sintaxis JSON estandar.
        assertTrue(Ej149MongoDocumentMapping.esDocumentoJsonValido("{}"));
    }

    @Test
    void testRetoExtra07_crearDocumentoSimple() {
        // Genera un BSON/JSON simple a mano.
        assertEquals("{\"k\":\"v\"}", Ej149MongoDocumentMapping.crearDocumentoSimple("k", "v"));
    }

    @Test
    void testRetoExtra08_esExcepcionMongo() {
        // Determina si la excepcion pertenece a la jerarquia del driver de Mongo.
        assertTrue(Ej149MongoDocumentMapping.esExcepcionMongo(new IllegalArgumentException("mongo")));
    }

    @Test
    void testRetoExtra09_esPropiedadReservada() {
        // Indica si es un campo reservado de Mongo (como _id o _class).
        assertTrue(Ej149MongoDocumentMapping.esPropiedadReservada("_id"));
    }

    @Test
    void testRetoExtra10_formatearFechaMongo() {
        // Genera la representacion BSON Date standard.
        assertTrue(Ej149MongoDocumentMapping.formatearFechaMongo(java.time.Instant.now()).contains("$date"));
    }

}