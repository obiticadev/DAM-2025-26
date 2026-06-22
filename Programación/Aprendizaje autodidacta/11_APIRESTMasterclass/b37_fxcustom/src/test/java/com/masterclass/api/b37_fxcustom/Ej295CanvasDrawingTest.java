package com.masterclass.api.b37_fxcustom;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej295CanvasDrawingTest {

    private static final double D = 1e-9;

    @Test
    void verticesPoligonoRegular() {
        List<double[]> sq = Ej295CanvasDrawing.verticesPoligonoRegular(0, 0, 1, 4);
        assertEquals(4, sq.size());
        // Primer vértice en ángulo 0 -> (1, 0)
        assertEquals(1.0, sq.get(0)[0], D);
        assertEquals(0.0, sq.get(0)[1], D);
        // Segundo vértice en ángulo 90° -> (0, 1)
        assertEquals(0.0, sq.get(1)[0], D);
        assertEquals(1.0, sq.get(1)[1], D);
        assertTrue(Ej295CanvasDrawing.verticesPoligonoRegular(0, 0, 1, 2).isEmpty()); // caso límite: < 3 lados
    }

    @Test
    void puntoTrasRotacion() {
        double[] p = Ej295CanvasDrawing.puntoTrasRotacion(1, 0, 90);
        assertEquals(0.0, p[0], D);
        assertEquals(1.0, p[1], D);
        double[] q = Ej295CanvasDrawing.puntoTrasRotacion(1, 0, 0); // caso límite: sin giro
        assertEquals(1.0, q[0], D);
        assertEquals(0.0, q[1], D);
    }

    @Test
    void retoExtra01_centroRectangulo() {
        double[] c = Ej295CanvasDrawing.centroRectangulo(0, 0, 10, 4);
        assertArrayEquals(new double[]{5, 2}, c, D);
    }

    @Test
    void retoExtra02_puntoMedio() {
        assertArrayEquals(new double[]{2, 3},
                Ej295CanvasDrawing.puntoMedio(new double[]{0, 0}, new double[]{4, 6}), D);
    }

    @Test
    void retoExtra03_distancia() {
        assertEquals(5.0, Ej295CanvasDrawing.distancia(new double[]{0, 0}, new double[]{3, 4}), D);
    }

    @Test
    void retoExtra04_escalar() {
        assertArrayEquals(new double[]{4, 6}, Ej295CanvasDrawing.escalar(new double[]{2, 3}, 2), D);
    }

    @Test
    void retoExtra05_trasladar() {
        assertArrayEquals(new double[]{6, -1}, Ej295CanvasDrawing.trasladar(new double[]{1, 1}, 5, -2), D);
    }

    @Test
    void retoExtra06_puntoEnCircunferencia() {
        assertArrayEquals(new double[]{2, 0}, Ej295CanvasDrawing.puntoEnCircunferencia(0, 0, 2, 0), D);
    }

    @Test
    void retoExtra07_cajaContenedora() {
        List<double[]> ps = List.of(new double[]{1, 2}, new double[]{5, 0}, new double[]{3, 7});
        assertArrayEquals(new double[]{1, 0, 5, 7}, Ej295CanvasDrawing.cajaContenedora(ps), D);
        assertArrayEquals(new double[]{0, 0, 0, 0}, Ej295CanvasDrawing.cajaContenedora(List.of()), D); // límite
    }

    @Test
    void retoExtra08_trasladarPoligono() {
        List<double[]> orig = List.of(new double[]{0, 0}, new double[]{2, 0});
        List<double[]> mov = Ej295CanvasDrawing.trasladarPoligono(orig, 1, 1);
        assertArrayEquals(new double[]{1, 1}, mov.get(0), D);
        assertArrayEquals(new double[]{0, 0}, orig.get(0), D); // la original no cambia
    }

    @Test
    void retoExtra09_areaPoligono() {
        List<double[]> cuadrado = List.of(
                new double[]{0, 0}, new double[]{2, 0}, new double[]{2, 2}, new double[]{0, 2});
        assertEquals(4.0, Ej295CanvasDrawing.areaPoligono(cuadrado), D);
        assertEquals(0.0, Ej295CanvasDrawing.areaPoligono(List.of(new double[]{0, 0})), D); // límite
    }

    @Test
    void retoExtra10_verticesEstrella() {
        List<double[]> estrella = Ej295CanvasDrawing.verticesEstrella(0, 0, 2, 1, 5);
        assertEquals(10, estrella.size()); // 2 * puntas
        assertArrayEquals(new double[]{2, 0}, estrella.get(0), D); // primera punta, radio exterior
    }
}
