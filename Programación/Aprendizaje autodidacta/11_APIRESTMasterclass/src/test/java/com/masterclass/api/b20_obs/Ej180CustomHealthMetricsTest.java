package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej180CustomHealthMetricsTest {

    @Test
    void bajoUmbralUp() {
        Map<String, String> r = Ej180CustomHealthMetrics.salud(100, 5, 0.1);
        assertEquals("0.0500", r.get("errorRate"));
        assertEquals("UP", r.get("status"));
    }

    @Test
    void sobreUmbralDown() {
        assertEquals("DOWN", Ej180CustomHealthMetrics.salud(100, 20, 0.1).get("status"));
    }

    @Test
    void totalCeroSinDivision() {
        assertEquals("0.0000", Ej180CustomHealthMetrics.salud(0, 0, 0.1).get("errorRate"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class, () -> Ej180CustomHealthMetrics.salud(-1, 0, 0.1));
        assertThrows(IllegalArgumentException.class, () -> Ej180CustomHealthMetrics.salud(10, 11, 0.1));
        assertThrows(IllegalArgumentException.class, () -> Ej180CustomHealthMetrics.salud(10, 1, 2.0));
    }

    @Test
    void testRetoExtra01_esTotalValido() {
        // Valida total >= 0.
        assertTrue(Ej180CustomHealthMetrics.esTotalValido(0));
    }

    @Test
    void testRetoExtra02_esErroresValido() {
        // Valida errores.
        assertTrue(Ej180CustomHealthMetrics.esErroresValido(5, 10));
    }

    @Test
    void testRetoExtra03_esUmbralValido() {
        // Valida umbral.
        assertTrue(Ej180CustomHealthMetrics.esUmbralValido(0.5));
    }

    @Test
    void testRetoExtra04_calcularTasaError() {
        // Calcula tasa real.
        assertEquals(0.5, Ej180CustomHealthMetrics.calcularTasaError(10, 5));
    }

    @Test
    void testRetoExtra05_formatearTasa() {
        // Formatea con 4 decimales.
        assertEquals("0.5000", Ej180CustomHealthMetrics.formatearTasa(0.5));
    }

    @Test
    void testRetoExtra06_determinarStatus() {
        // Calcula status.
        assertEquals("DOWN", Ej180CustomHealthMetrics.determinarStatus(0.2, 0.1));
    }

    @Test
    void testRetoExtra07_esStatusUp() {
        // Valida si es UP.
        assertTrue(Ej180CustomHealthMetrics.esStatusUp("UP"));
    }

    @Test
    void testRetoExtra08_esStatusDown() {
        // Valida si es DOWN.
        assertTrue(Ej180CustomHealthMetrics.esStatusDown("DOWN"));
    }

    @Test
    void testRetoExtra09_obtenerTasaDelMapa() {
        // Extrae tasa del mapa.
        assertEquals("0.0500", Ej180CustomHealthMetrics.obtenerTasaDelMapa(java.util.Map.of("errorRate", "0.0500")));
    }

    @Test
    void testRetoExtra10_obtenerStatusDelMapa() {
        // Extrae status del mapa.
        assertEquals("UP", Ej180CustomHealthMetrics.obtenerStatusDelMapa(java.util.Map.of("status", "UP")));
    }

}