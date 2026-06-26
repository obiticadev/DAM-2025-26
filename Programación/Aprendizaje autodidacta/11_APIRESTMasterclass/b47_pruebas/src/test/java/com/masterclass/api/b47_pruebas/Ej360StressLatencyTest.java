package com.masterclass.api.b47_pruebas;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej360StressLatencyTest {

    @Test
    void percentil_p50() {
        var latencias = List.of(10L, 20L, 30L, 40L, 50L);
        assertEquals(30L, Ej360StressLatency.percentil(latencias, 50));
    }

    @Test
    void percentil_p100() {
        var latencias = List.of(10L, 20L, 30L, 40L, 50L);
        assertEquals(50L, Ej360StressLatency.percentil(latencias, 100));
    }

    @Test
    void percentil_fueraDerango() {
        var latencias = List.of(10L, 20L, 30L);
        assertEquals(-1, Ej360StressLatency.percentil(latencias, 101));
        assertEquals(-1, Ej360StressLatency.percentil(null, 50));
        assertEquals(-1, Ej360StressLatency.percentil(List.of(), 50));
    }

    @Test
    void puntoSaturacion_detecta() {
        var curva = List.of(
                new Ej360StressLatency.Medida(10, 50),
                new Ej360StressLatency.Medida(100, 80),
                new Ej360StressLatency.Medida(500, 105) // > 100 (50*2)
        );
        var sat = Ej360StressLatency.puntoSaturacion(curva);
        assertNotNull(sat);
        assertEquals(500, sat.carga());
    }

    @Test
    void puntoSaturacion_sinSaturacion() {
        var curva = List.of(
                new Ej360StressLatency.Medida(10, 50),
                new Ej360StressLatency.Medida(100, 90)
        );
        assertNull(Ej360StressLatency.puntoSaturacion(curva));
    }

    @Test
    void puntoSaturacion_null() {
        assertNull(Ej360StressLatency.puntoSaturacion(null));
        assertNull(Ej360StressLatency.puntoSaturacion(List.of(new Ej360StressLatency.Medida(1, 10)))); // size<2
    }

    @Test
    void retoExtra01_tieneColaLarga() {
        assertTrue(Ej360StressLatency.tieneColaLarga(50L, 100L, 200L)); // 200>150 y 200>150
        assertFalse(Ej360StressLatency.tieneColaLarga(50L, 100L, 140L)); // 140<150
    }

    @Test
    void retoExtra02_corregirCoordinatedOmission() {
        var resultado = Ej360StressLatency.corregirCoordinatedOmission(List.of(50L, 200L, 30L), 100L);
        assertEquals(List.of(100L, 200L, 100L), resultado);
        assertEquals(List.of(), Ej360StressLatency.corregirCoordinatedOmission(null, 100L));
        assertEquals(List.of(50L), Ej360StressLatency.corregirCoordinatedOmission(List.of(50L), 0L));
    }

    @Test
    void retoExtra03_histograma() {
        var latencias = List.of(0L, 10L, 20L, 30L, 40L, 50L, 60L, 70L, 80L, 90L);
        var hist = Ej360StressLatency.histograma(latencias, 5); // 5 cubos: [0-18],[18-36],[36-54],[54-72],[72-90]
        assertEquals(5, hist.size());
        int suma = hist.stream().mapToInt(Integer::intValue).sum();
        assertEquals(10, suma);
    }

    @Test
    void retoExtra04_cumpleSLA() {
        assertTrue(Ej360StressLatency.cumpleSLA(90L, 100L, 0.999, 0.99));
        assertFalse(Ej360StressLatency.cumpleSLA(100L, 100L, 0.999, 0.99)); // exactamente igual → false
        assertFalse(Ej360StressLatency.cumpleSLA(90L, 100L, 0.98, 0.99));
    }

    @Test
    void retoExtra05_modoServicio() {
        assertEquals("NORMAL", Ej360StressLatency.modoServicio(100L, 100L));
        assertEquals("DEGRADADO", Ej360StressLatency.modoServicio(150L, 100L));
        assertEquals("DEGRADADO", Ej360StressLatency.modoServicio(200L, 100L));
        assertEquals("CORTADO", Ej360StressLatency.modoServicio(201L, 100L));
    }

    @Test
    void retoExtra06_rechazarPeticion() {
        assertTrue(Ej360StressLatency.rechazarPeticion(80, 80)); // en el límite → rechaza
        assertFalse(Ej360StressLatency.rechazarPeticion(79, 80));
    }

    @Test
    void retoExtra07_superaTimeout() {
        assertTrue(Ej360StressLatency.superaTimeout(101L, 100L));
        assertFalse(Ej360StressLatency.superaTimeout(100L, 100L));
    }

    @Test
    void retoExtra08_tiempoRecuperacionPct() {
        assertEquals(50.0, Ej360StressLatency.tiempoRecuperacionPct(100L, 150L), 0.01);
        assertEquals(-1, Ej360StressLatency.tiempoRecuperacionPct(0L, 150L), 0.01);
        assertEquals(0.0, Ej360StressLatency.tiempoRecuperacionPct(100L, 100L), 0.01);
    }

    @Test
    void retoExtra09_detectarSpikes() {
        var latencias = List.of(50L, 52L, 51L, 300L); // media ~113; 300 > 113*2=226
        var spikes = Ej360StressLatency.detectarSpikes(latencias, 2.0);
        assertEquals(1, spikes.size());
        assertEquals(300L, spikes.get(0));
        assertEquals(List.of(), Ej360StressLatency.detectarSpikes(List.of(), 2.0));
    }

    @Test
    void retoExtra10_pctSegundosViolaSLA() {
        var historial = List.of(
                List.of(10L, 20L, 30L),   // p99 = 30 <= 50 → cumple
                List.of(100L, 200L, 300L) // p99 = 300 > 50 → viola
        );
        assertEquals(50.0, Ej360StressLatency.pctSegundosViolaSLA(historial, 50L), 0.01);
        assertEquals(0.0, Ej360StressLatency.pctSegundosViolaSLA(List.of(), 50L), 0.01);
    }
}
