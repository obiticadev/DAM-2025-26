package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej176CoverageAndQualityGateTest {

    @Test
    void calculaCobertura() {
        assertEquals(80.0, Ej176CoverageAndQualityGate.porcentajeCobertura(80, 100));
        assertEquals(0.0, Ej176CoverageAndQualityGate.porcentajeCobertura(0, 100));
        assertEquals(100.0, Ej176CoverageAndQualityGate.porcentajeCobertura(50, 50));
    }

    @Test
    void coberturaInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej176CoverageAndQualityGate.porcentajeCobertura(10, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej176CoverageAndQualityGate.porcentajeCobertura(120, 100));
    }

    @Test
    void gate() {
        assertTrue(Ej176CoverageAndQualityGate.pasaQualityGate(80.0, 70.0));
        assertTrue(Ej176CoverageAndQualityGate.pasaQualityGate(70.0, 70.0));
        assertFalse(Ej176CoverageAndQualityGate.pasaQualityGate(60.0, 70.0));
    }

    @Test
    void gateInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej176CoverageAndQualityGate.pasaQualityGate(-1.0, 70.0));
    }
}
