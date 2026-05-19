package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b02_json.Ej027CustomSerializer.Precio;
import static org.junit.jupiter.api.Assertions.*;

class Ej027CustomSerializerTest {

    @Test
    void formateaConSimbolo() {
        assertEquals("\"9.90 €\"", Ej027CustomSerializer.toJson(new Precio(9.9)));
        assertEquals("\"10.00 €\"", Ej027CustomSerializer.toJson(new Precio(10)));
    }
}
