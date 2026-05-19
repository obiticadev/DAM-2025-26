package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej023JsonModelTest {

    @Test
    void tipos() {
        assertEquals("objeto", Ej023JsonModel.tipo("{}"));
        assertEquals("array", Ej023JsonModel.tipo("[1,2]"));
        assertEquals("string", Ej023JsonModel.tipo("\"hola\""));
        assertEquals("numero", Ej023JsonModel.tipo("42"));
        assertEquals("numero", Ej023JsonModel.tipo("3.14"));
        assertEquals("booleano", Ej023JsonModel.tipo("true"));
        assertEquals("null", Ej023JsonModel.tipo("null"));
    }

    @Test
    void desconocido() {
        assertEquals("desconocido", Ej023JsonModel.tipo("??"));
        assertEquals("desconocido", Ej023JsonModel.tipo(""));
    }
}
