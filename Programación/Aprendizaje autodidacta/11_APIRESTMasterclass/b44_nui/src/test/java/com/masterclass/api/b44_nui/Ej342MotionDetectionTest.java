package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej342MotionDetectionTest {

    @Test
    void fraccionMovimiento() {
        int[][] f1 = {{0, 0}, {0, 0}};
        int[][] f2 = {{0, 100}, {0, 0}};
        assertEquals(0.25, Ej342MotionDetection.fraccionMovimiento(f1, f2, 50), 1e-9); // 1 de 4 píxeles
        int[][] otro = {{0, 0}};
        assertEquals(-1.0, Ej342MotionDetection.fraccionMovimiento(f1, otro, 50), 1e-9); // caso límite: dimensiones distintas
    }

    @Test
    void mediaMovil() {
        assertArrayEquals(new double[]{2, 4, 6}, Ej342MotionDetection.mediaMovil(new double[]{2, 4, 6}, 1), 1e-9);
        assertArrayEquals(new double[0], Ej342MotionDetection.mediaMovil(new double[]{2, 4, 6}, 0), 1e-9); // caso límite
    }

    @Test
    void retoExtra01_diferenciaAbsoluta() {
        assertEquals(7, Ej342MotionDetection.diferenciaAbsoluta(10, 3));
        assertEquals(7, Ej342MotionDetection.diferenciaAbsoluta(3, 10));
    }

    @Test
    void retoExtra02_superaUmbral() {
        assertTrue(Ej342MotionDetection.superaUmbral(60, 50));
        assertFalse(Ej342MotionDetection.superaUmbral(40, 50));
    }

    @Test
    void retoExtra03_contarPixelesActivos() {
        assertEquals(2, Ej342MotionDetection.contarPixelesActivos(new int[][]{{10, 60}, {70, 5}}, 50));
        assertEquals(0, Ej342MotionDetection.contarPixelesActivos(new int[][]{}, 50)); // caso límite
    }

    @Test
    void retoExtra04_binarizarFila() {
        assertArrayEquals(new int[]{0, 1}, Ej342MotionDetection.binarizarFila(new int[]{10, 60}, 50));
        assertArrayEquals(new int[0], Ej342MotionDetection.binarizarFila(null, 50)); // caso límite
    }

    @Test
    void retoExtra05_detectaMovimiento() {
        assertTrue(Ej342MotionDetection.detectaMovimiento(0.3, 0.2));
        assertFalse(Ej342MotionDetection.detectaMovimiento(0.1, 0.2));
    }

    @Test
    void retoExtra06_framesProcesados() {
        assertEquals(5, Ej342MotionDetection.framesProcesados(10, 2));
        assertEquals(0, Ej342MotionDetection.framesProcesados(0, 2)); // caso límite
    }

    @Test
    void retoExtra07_ajustarPorIluminacion() {
        assertEquals(70, Ej342MotionDetection.ajustarPorIluminacion(100, 30));
        assertEquals(0, Ej342MotionDetection.ajustarPorIluminacion(10, 30)); // caso límite: no negativo
    }

    @Test
    void retoExtra08_contarEnRegion() {
        assertEquals(1, Ej342MotionDetection.contarEnRegion(new int[][]{{0, 60}, {70, 0}}, 0, 0, 2, 1, 50));
        assertEquals(0, Ej342MotionDetection.contarEnRegion(new int[][]{}, 0, 0, 2, 1, 50)); // caso límite
    }

    @Test
    void retoExtra09_umbralAdaptativoMedia() {
        assertEquals(25.0, Ej342MotionDetection.umbralAdaptativoMedia(new int[][]{{0, 100}, {0, 0}}, 1.0), 1e-9);
        assertEquals(0.0, Ej342MotionDetection.umbralAdaptativoMedia(new int[][]{}, 1.0), 1e-9); // caso límite
    }

    @Test
    void retoExtra10_alarmaSostenida() {
        assertTrue(Ej342MotionDetection.alarmaSostenida(List.of(false, true, true, false), 2));
        assertFalse(Ej342MotionDetection.alarmaSostenida(List.of(true, false, true), 2));
    }
}
