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
}
