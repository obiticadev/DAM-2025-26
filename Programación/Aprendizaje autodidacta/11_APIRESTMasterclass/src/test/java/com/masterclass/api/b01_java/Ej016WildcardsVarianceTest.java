package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej016WildcardsVarianceTest {

    @Test
    void sumarMixto() {
        assertEquals(6.5, Ej016WildcardsVariance.sumar(List.of(1, 2.5, 3L)), 0.0001);
    }

    @Test
    void rellenar() {
        List<Number> destino = new ArrayList<>();
        Ej016WildcardsVariance.rellenar(destino);
        assertEquals(List.of(1, 2, 3), destino);
    }

    @Test
    void contarDeTipo() {
        List<Object> items = List.of(1, "a", 2, "b", 3);
        assertEquals(3, Ej016WildcardsVariance.contarDeTipo(items, Integer.class));
        assertEquals(2, Ej016WildcardsVariance.contarDeTipo(items, String.class));
    }
}
