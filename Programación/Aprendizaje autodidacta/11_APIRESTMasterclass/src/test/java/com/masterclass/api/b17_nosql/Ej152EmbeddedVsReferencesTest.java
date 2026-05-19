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
}
