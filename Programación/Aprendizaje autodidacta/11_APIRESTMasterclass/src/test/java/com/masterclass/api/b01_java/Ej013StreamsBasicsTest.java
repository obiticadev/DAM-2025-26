package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej013StreamsBasicsTest {

    @Test
    void nombresCarosEnMayus() {
        var entradas = List.of(
                Map.entry("teclado", 100.0),
                Map.entry("cable", 5.0),
                Map.entry("monitor", 200.0));
        assertEquals(List.of("TECLADO", "MONITOR"),
                Ej013StreamsBasics.nombresCarosEnMayus(entradas, 50));
    }

    @Test
    void total() {
        assertEquals(35.5, Ej013StreamsBasics.total(List.of(10.0, 20.0, 5.5)), 0.0001);
        assertEquals(0.0, Ej013StreamsBasics.total(List.of()), 0.0001);
    }

    @Test
    void contarPares() {
        assertEquals(2, Ej013StreamsBasics.contarPares(List.of(1, 2, 3, 4)));
    }
}
