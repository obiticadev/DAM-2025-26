package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej313ImageTransformTest {

    @Test
    void recortar() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertArrayEquals(new int[][]{{5, 6}, {8, 9}}, Ej313ImageTransform.recortar(m, 1, 1, 2, 2));
        assertEquals(0, Ej313ImageTransform.recortar(m, 0, 0, 0, 0).length); // caso límite
    }

    @Test
    void rotar90Horario() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertArrayEquals(new int[][]{{4, 1}, {5, 2}, {6, 3}}, Ej313ImageTransform.rotar90Horario(m));
        assertEquals(0, Ej313ImageTransform.rotar90Horario(new int[0][0]).length); // caso límite
    }

    @Test
    void retoExtra01_voltearHorizontal() {
        assertArrayEquals(new int[][]{{3, 2, 1}}, Ej313ImageTransform.voltearHorizontal(new int[][]{{1, 2, 3}}));
    }

    @Test
    void retoExtra02_voltearVertical() {
        assertArrayEquals(new int[][]{{3}, {2}, {1}}, Ej313ImageTransform.voltearVertical(new int[][]{{1}, {2}, {3}}));
    }

    @Test
    void retoExtra03_transponer() {
        assertArrayEquals(new int[][]{{1, 4}, {2, 5}, {3, 6}},
                Ej313ImageTransform.transponer(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    void retoExtra04_rotar180() {
        assertArrayEquals(new int[][]{{4, 3}, {2, 1}}, Ej313ImageTransform.rotar180(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    void retoExtra05_rotar90Antihorario() {
        assertArrayEquals(new int[][]{{3, 6}, {2, 5}, {1, 4}},
                Ej313ImageTransform.rotar90Antihorario(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    void retoExtra06_dimensionMiniatura() {
        assertArrayEquals(new int[]{200, 100}, Ej313ImageTransform.dimensionMiniatura(800, 400, 200));
        assertArrayEquals(new int[]{50, 50}, Ej313ImageTransform.dimensionMiniatura(100, 100, 50)); // cuadrado
    }

    @Test
    void retoExtra07_recortarCuadradoCentral() {
        int[][] m = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        assertArrayEquals(new int[][]{{2, 3}, {6, 7}}, Ej313ImageTransform.recortarCuadradoCentral(m));
    }

    @Test
    void retoExtra08_escalarVecino() {
        assertArrayEquals(new int[][]{{1, 1, 2, 2}}, Ej313ImageTransform.escalarVecino(new int[][]{{1, 2}}, 1, 4));
    }

    @Test
    void retoExtra09_incrustar() {
        int[][] fondo = {{1, 2}, {3, 4}};
        int[][] r = Ej313ImageTransform.incrustar(fondo, new int[][]{{9}}, 0, 1);
        assertArrayEquals(new int[][]{{1, 9}, {3, 4}}, r);
        assertArrayEquals(new int[][]{{1, 2}, {3, 4}}, fondo); // el fondo original no cambia
    }

    @Test
    void retoExtra10_dimensionTrasRotacion() {
        assertArrayEquals(new int[]{2, 4}, Ej313ImageTransform.dimensionTrasRotacion(4, 2, 90));
        assertArrayEquals(new int[]{4, 2}, Ej313ImageTransform.dimensionTrasRotacion(4, 2, 180));
        assertArrayEquals(new int[]{2, 4}, Ej313ImageTransform.dimensionTrasRotacion(4, 2, -90)); // ángulo negativo
    }
}
