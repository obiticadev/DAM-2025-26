package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej008RestResourceModelerTest {

    @Test
    void coleccion() {
        assertEquals("/pedidos", Ej008RestResourceModeler.collection("pedidos"));
    }

    @Test
    void elemento() {
        assertEquals("/pedidos/42", Ej008RestResourceModeler.item("pedidos", 42));
    }

    @Test
    void anidado() {
        assertEquals("/pedidos/42/lineas", Ej008RestResourceModeler.nested("pedidos", 42, "lineas"));
    }

    @Test
    void recursoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej008RestResourceModeler.collection(""));
    }
}
