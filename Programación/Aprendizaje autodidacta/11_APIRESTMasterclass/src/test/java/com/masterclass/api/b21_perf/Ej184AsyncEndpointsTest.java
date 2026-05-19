package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej184AsyncEndpointsTest {

    @Test
    void sumaParalelaDeCuadrados() {
        assertEquals(14, Ej184AsyncEndpoints.sumarEnParalelo(List.of(1, 2, 3), x -> x * x));
    }

    @Test
    void vaciaEsCero() {
        assertEquals(0, Ej184AsyncEndpoints.sumarEnParalelo(List.of(), x -> x));
    }

    @Test
    void encadenaEnOrden() {
        assertEquals("AB", Ej184AsyncEndpoints.encadenar("a", String::toUpperCase, s -> s + "B"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej184AsyncEndpoints.sumarEnParalelo(null, x -> x));
        assertThrows(IllegalArgumentException.class,
                () -> Ej184AsyncEndpoints.encadenar("a", null, s -> s));
    }
}
