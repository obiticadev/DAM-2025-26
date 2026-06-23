package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Ej315AudioControlTest {

    @Test
    void siguientePista() {
        assertEquals(1, Ej315AudioControl.siguientePista(0, 3, false));
        assertEquals(-1, Ej315AudioControl.siguientePista(2, 3, false)); // última, sin repetir
        assertEquals(0, Ej315AudioControl.siguientePista(2, 3, true)); // última, repitiendo
        assertEquals(-1, Ej315AudioControl.siguientePista(0, 0, true)); // caso límite: lista vacía
    }

    @Test
    void clampSeek() {
        assertEquals(0.0, Ej315AudioControl.clampSeek(-5, 100), 1e-9);
        assertEquals(100.0, Ej315AudioControl.clampSeek(150, 100), 1e-9);
        assertEquals(50.0, Ej315AudioControl.clampSeek(50, 100), 1e-9);
    }

    @Test
    void retoExtra01_pistaAnterior() {
        assertEquals(1, Ej315AudioControl.pistaAnterior(2, 3, false));
        assertEquals(-1, Ej315AudioControl.pistaAnterior(0, 3, false));
        assertEquals(2, Ej315AudioControl.pistaAnterior(0, 3, true)); // primera, repitiendo -> última
    }

    @Test
    void retoExtra02_duracionTotal() {
        assertEquals(420.0, Ej315AudioControl.duracionTotal(new double[]{120, 200, 100}), 1e-9);
        assertEquals(0.0, Ej315AudioControl.duracionTotal(new double[]{}), 1e-9); // caso límite
    }

    @Test
    void retoExtra03_indiceValido() {
        assertTrue(Ej315AudioControl.indiceValido(0, 3));
        assertFalse(Ej315AudioControl.indiceValido(3, 3)); // el último válido es total-1
    }

    @Test
    void retoExtra04_tiempoAcumuladoHasta() {
        double[] d = {120, 200, 100};
        assertEquals(320.0, Ej315AudioControl.tiempoAcumuladoHasta(d, 2), 1e-9);
        assertEquals(0.0, Ej315AudioControl.tiempoAcumuladoHasta(d, 0), 1e-9); // antes de la primera
    }

    @Test
    void retoExtra05_pistaEnSegundo() {
        double[] d = {100, 100, 100};
        assertEquals(0, Ej315AudioControl.pistaEnSegundo(d, 0));
        assertEquals(1, Ej315AudioControl.pistaEnSegundo(d, 150));
        assertEquals(-1, Ej315AudioControl.pistaEnSegundo(d, 350)); // se pasa del total
    }

    @Test
    void retoExtra06_normalizarMuestra() {
        assertEquals(0.5, Ej315AudioControl.normalizarMuestra(0.5, 1.0), 1e-9);
        assertEquals(0.5, Ej315AudioControl.normalizarMuestra(0.25, 0.5), 1e-9);
        assertEquals(0.0, Ej315AudioControl.normalizarMuestra(0.3, 0.0), 1e-9); // caso límite: pico 0
    }

    @Test
    void retoExtra07_mezclarAMono() {
        assertEquals(0.5, Ej315AudioControl.mezclarAMono(1.0, 0.0), 1e-9);
    }

    @Test
    void retoExtra08_gananciaADecibelios() {
        assertEquals(0.0, Ej315AudioControl.gananciaADecibelios(1.0), 1e-9);
        assertEquals(20.0, Ej315AudioControl.gananciaADecibelios(10.0), 1e-9);
        assertEquals(Double.NEGATIVE_INFINITY, Ej315AudioControl.gananciaADecibelios(0.0)); // silencio = -inf dB
    }

    @Test
    void retoExtra09_duracionTotalFormateada() {
        assertEquals("7:00", Ej315AudioControl.duracionTotalFormateada(new double[]{120, 200, 100}));
    }

    @Test
    void retoExtra10_ordenAleatorio() {
        int[] orden = Ej315AudioControl.ordenAleatorio(3, 42L);
        int[] copia = orden.clone();
        Arrays.sort(copia);
        assertArrayEquals(new int[]{0, 1, 2}, copia); // es una permutación de 0..n-1
        assertArrayEquals(orden, Ej315AudioControl.ordenAleatorio(3, 42L)); // misma semilla -> mismo orden
        assertEquals(0, Ej315AudioControl.ordenAleatorio(0, 42L).length); // caso límite
    }
}
