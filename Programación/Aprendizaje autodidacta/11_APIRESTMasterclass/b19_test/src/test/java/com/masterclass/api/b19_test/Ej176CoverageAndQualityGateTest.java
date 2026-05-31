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

    @Test
    void testRetoExtra01_esCoberturaTotal() {
        // Valida si es 100%.
        assertTrue(Ej176CoverageAndQualityGate.esCoberturaTotal(100.0));
    }

    @Test
    void testRetoExtra02_esCoberturaNula() {
        // Valida si es 0%.
        assertTrue(Ej176CoverageAndQualityGate.esCoberturaNula(0.0));
    }

    @Test
    void testRetoExtra03_umbralPorDefecto() {
        // Umbral estándar (80%).
        assertEquals(80.0, Ej176CoverageAndQualityGate.umbralPorDefecto());
    }

    @Test
    void testRetoExtra04_umbralEstricto() {
        // Umbral exigente (100%).
        assertEquals(100.0, Ej176CoverageAndQualityGate.umbralEstricto());
    }

    @Test
    void testRetoExtra05_esUmbralValido() {
        // Valida si esta en [0,100].
        assertTrue(Ej176CoverageAndQualityGate.esUmbralValido(50.0));
    }

    @Test
    void testRetoExtra06_diferenciaCobertura() {
        // Calcula diferencia absoluta.
        assertEquals(10.0, Ej176CoverageAndQualityGate.diferenciaCobertura(90.0, 80.0));
    }

    @Test
    void testRetoExtra07_esCoberturaAceptable() {
        // Valida si es >= 80%.
        assertTrue(Ej176CoverageAndQualityGate.esCoberturaAceptable(85.0));
    }

    @Test
    void testRetoExtra08_esCritica() {
        // Valida si es < 50%.
        assertTrue(Ej176CoverageAndQualityGate.esCritica(45.0));
    }

    @Test
    void testRetoExtra09_calcularPorcentajeRapido() {
        // Calculo rapido sin redondeo.
        assertEquals(50.0, Ej176CoverageAndQualityGate.calcularPorcentajeRapido(5, 10));
    }

    @Test
    void testRetoExtra10_formatearCobertura() {
        // Formatea con simbolo de porcentaje.
        assertEquals("85.5%", Ej176CoverageAndQualityGate.formatearCobertura(85.5));
    }

}