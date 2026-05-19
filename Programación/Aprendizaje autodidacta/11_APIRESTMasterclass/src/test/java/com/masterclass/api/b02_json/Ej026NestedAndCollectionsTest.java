package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b02_json.Ej026NestedAndCollections.Linea;
import com.masterclass.api.b02_json.Ej026NestedAndCollections.Pedido;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej026NestedAndCollectionsTest {

    @Test
    void serializaAnidado() {
        String j = Ej026NestedAndCollections.toJson(new Pedido(1L, List.of(new Linea("cafe", 2))));
        assertTrue(j.contains("\"lineas\""));
        assertTrue(j.contains("\"producto\":\"cafe\""));
    }

    @Test
    void deserializaLista() {
        var lineas = Ej026NestedAndCollections.lineasFromJson(
                "[{\"producto\":\"cafe\",\"cantidad\":2},{\"producto\":\"te\",\"cantidad\":1}]");
        assertEquals(2, lineas.size());
        assertEquals("te", lineas.get(1).producto());
    }
}
