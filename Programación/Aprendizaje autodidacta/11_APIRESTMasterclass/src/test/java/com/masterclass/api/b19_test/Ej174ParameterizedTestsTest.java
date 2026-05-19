package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej174ParameterizedTestsTest {

    @Test
    void todosPasan() {
        List<Caso174> casos = List.of(new Caso174(2, 4), new Caso174(3, 9));
        assertTrue(Ej174ParameterizedTests.casosFallidos(casos, x -> x * x).isEmpty());
    }

    @Test
    void detectaFallidos() {
        List<Caso174> casos = List.of(new Caso174(2, 4), new Caso174(3, 8), new Caso174(4, 16));
        assertEquals(List.of(1),
                Ej174ParameterizedTests.casosFallidos(casos, x -> x * x));
    }

    @Test
    void nulls() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej174ParameterizedTests.casosFallidos(null, x -> x));
    }
}
