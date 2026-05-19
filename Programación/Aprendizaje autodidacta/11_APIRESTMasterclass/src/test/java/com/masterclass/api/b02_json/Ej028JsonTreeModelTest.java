package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej028JsonTreeModelTest {

    @Test
    void primerId() {
        assertEquals(7, Ej028JsonTreeModel.primerIdDeDatos("{\"datos\":[{\"id\":7},{\"id\":9}]}"));
    }

    @Test
    void rutaInexistente() {
        assertEquals(-1, Ej028JsonTreeModel.primerIdDeDatos("{\"otros\":[]}"));
    }

    @Test
    void tamanio() {
        assertEquals(2, Ej028JsonTreeModel.tamanioDatos("{\"datos\":[{\"id\":7},{\"id\":9}]}"));
        assertEquals(0, Ej028JsonTreeModel.tamanioDatos("{\"x\":1}"));
    }
}
