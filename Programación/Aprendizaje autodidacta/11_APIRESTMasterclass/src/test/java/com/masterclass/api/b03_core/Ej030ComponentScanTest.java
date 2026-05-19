package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej030ComponentScanTest {

    @Test
    void contenedorDevuelveBeanFuncional() {
        var s = Ej030ComponentScan.obtenerBean();
        assertNotNull(s, "el contexto debe entregar el bean");
        assertEquals("hola", s.saludar());
    }
}
