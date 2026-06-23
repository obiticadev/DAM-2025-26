package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej312ImageFiltersTest {

    @Test
    void aGrises() {
        // Rojo puro: 0.299*255 = 76.245 -> 76
        assertArrayEquals(new int[][]{{76}}, Ej312ImageFilters.aGrises(new int[][]{{0xFFFF0000}}));
        // Verde puro: 0.587*255 = 149.685 -> 150
        assertArrayEquals(new int[][]{{150}}, Ej312ImageFilters.aGrises(new int[][]{{0xFF00FF00}}));
        assertEquals(0, Ej312ImageFilters.aGrises(new int[0][0]).length); // caso límite
    }

    @Test
    void ajustarBrillo() {
        assertArrayEquals(new int[][]{{200, 255}}, Ej312ImageFilters.ajustarBrillo(new int[][]{{100, 200}}, 100));
        assertArrayEquals(new int[][]{{0}}, Ej312ImageFilters.ajustarBrillo(new int[][]{{50}}, -100)); // satura a 0
        assertEquals(0, Ej312ImageFilters.ajustarBrillo(new int[0][0], 10).length); // caso límite
    }

    @Test
    void retoExtra01_invertir() {
        assertArrayEquals(new int[][]{{255, 0, 155}}, Ej312ImageFilters.invertir(new int[][]{{0, 255, 100}}));
    }

    @Test
    void retoExtra02_umbralBinario() {
        assertArrayEquals(new int[][]{{0, 255}}, Ej312ImageFilters.umbralBinario(new int[][]{{100, 200}}, 150));
    }

    @Test
    void retoExtra03_clamp() {
        assertEquals(255, Ej312ImageFilters.clamp(300));
        assertEquals(0, Ej312ImageFilters.clamp(-5));
        assertEquals(128, Ej312ImageFilters.clamp(128));
    }

    @Test
    void retoExtra04_histograma() {
        int[] h = Ej312ImageFilters.histograma(new int[][]{{0, 0, 255}});
        assertEquals(2, h[0]);
        assertEquals(1, h[255]);
        assertEquals(256, h.length);
    }

    @Test
    void retoExtra05_valorMedio() {
        assertEquals(127.5, Ej312ImageFilters.valorMedio(new int[][]{{0, 255}}), 1e-9);
        assertEquals(0.0, Ej312ImageFilters.valorMedio(new int[0][0]), 1e-9); // caso límite
    }

    @Test
    void retoExtra06_ajustarContraste() {
        assertArrayEquals(new int[][]{{128}}, Ej312ImageFilters.ajustarContraste(new int[][]{{128}}, 2.0)); // el centro no se mueve
        assertArrayEquals(new int[][]{{200}}, Ej312ImageFilters.ajustarContraste(new int[][]{{200}}, 1.0)); // factor 1 = identidad
    }

    @Test
    void retoExtra07_aSepia() {
        assertEquals(0xFF000000, Ej312ImageFilters.aSepia(0xFF000000)); // negro sigue negro
        assertEquals(0x80, (Ej312ImageFilters.aSepia(0x80FFFFFF) >>> 24) & 0xFF); // el alfa se conserva
    }

    @Test
    void retoExtra08_grisPromedio() {
        assertEquals(85, Ej312ImageFilters.grisPromedio(0xFFFF0000)); // (255+0+0)/3
    }

    @Test
    void retoExtra09_aplicarGamma() {
        assertArrayEquals(new int[][]{{0, 255}}, Ej312ImageFilters.aplicarGamma(new int[][]{{0, 255}}, 2.2)); // extremos fijos
        assertArrayEquals(new int[][]{{128}}, Ej312ImageFilters.aplicarGamma(new int[][]{{128}}, 1.0)); // gamma 1 = identidad
    }

    @Test
    void retoExtra10_convolucionar() {
        int[][] m = {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}};
        double[][] identidad = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertArrayEquals(m, Ej312ImageFilters.convolucionar(m, identidad)); // el kernel identidad no cambia nada
    }
}
