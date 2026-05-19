package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b02_json.Ej024ObjectMapperBasics.Cliente;
import static org.junit.jupiter.api.Assertions.*;

class Ej024ObjectMapperBasicsTest {

    @Test
    void serializa() {
        String j = Ej024ObjectMapperBasics.toJson(new Cliente(1L, "Ana"));
        assertTrue(j.contains("\"id\":1"));
        assertTrue(j.contains("\"nombre\":\"Ana\""));
    }

    @Test
    void deserializa() {
        Cliente c = Ej024ObjectMapperBasics.fromJson("{\"id\":5,\"nombre\":\"Leo\"}");
        assertEquals(5L, c.id());
        assertEquals("Leo", c.nombre());
    }

    @Test
    void roundTrip() {
        Cliente o = new Cliente(9L, "Zoe");
        assertEquals(o, Ej024ObjectMapperBasics.fromJson(Ej024ObjectMapperBasics.toJson(o)));
    }
}
