package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej171SpringBootIntegrationTest {

    @Test
    void flujoAddYDel() {
        assertEquals(List.of("b"),
                Ej171SpringBootIntegration.ejecutarFlujo(List.of("a"), List.of("ADD:b", "DEL:a")));
    }

    @Test
    void estadoOrdenado() {
        assertEquals(List.of("a", "m", "z"),
                Ej171SpringBootIntegration.ejecutarFlujo(List.of(), List.of("ADD:z", "ADD:a", "ADD:m")));
    }

    @Test
    void comandoMalformado() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej171SpringBootIntegration.ejecutarFlujo(List.of(), List.of("BADCMD")));
    }

    @Test
    void nulls() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej171SpringBootIntegration.ejecutarFlujo(null, List.of()));
    }
}
