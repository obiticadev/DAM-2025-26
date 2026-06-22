package com.masterclass.api.b37_fxcustom;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej296CanvasInteractiveTest {

    private static final double D = 1e-9;

    @Test
    void dentroDeRectangulo() {
        assertTrue(Ej296CanvasInteractive.dentroDeRectangulo(5, 5, 0, 0, 10, 10));
        assertFalse(Ej296CanvasInteractive.dentroDeRectangulo(15, 5, 0, 0, 10, 10)); // fuera en X
        assertTrue(Ej296CanvasInteractive.dentroDeRectangulo(0, 0, 0, 0, 10, 10)); // borde inclusive
    }

    @Test
    void dentroDeCirculo() {
        assertTrue(Ej296CanvasInteractive.dentroDeCirculo(3, 4, 0, 0, 5)); // dist 5 == r (frontera)
        assertFalse(Ej296CanvasInteractive.dentroDeCirculo(5, 5, 0, 0, 5)); // dist ~7.07 > 5
        assertTrue(Ej296CanvasInteractive.dentroDeCirculo(0, 0, 0, 0, 5)); // centro
    }

    @Test
    void retoExtra01_dentroDeRangoX() {
        assertTrue(Ej296CanvasInteractive.dentroDeRangoX(5, 0, 10));
        assertTrue(Ej296CanvasInteractive.dentroDeRangoX(10, 0, 10)); // borde
        assertFalse(Ej296CanvasInteractive.dentroDeRangoX(11, 0, 10));
    }

    @Test
    void retoExtra02_distanciaAlCuadrado() {
        assertEquals(25.0, Ej296CanvasInteractive.distanciaAlCuadrado(0, 0, 3, 4), D);
    }

    @Test
    void retoExtra03_sobreLineaHorizontal() {
        assertTrue(Ej296CanvasInteractive.sobreLineaHorizontal(5, 10.5, 0, 10, 10, 1));
        assertFalse(Ej296CanvasInteractive.sobreLineaHorizontal(5, 12, 0, 10, 10, 1)); // 2 > tol
    }

    @Test
    void retoExtra04_ajustarARejilla() {
        assertEquals(20.0, Ej296CanvasInteractive.ajustarARejilla(23, 10), D);
        assertEquals(30.0, Ej296CanvasInteractive.ajustarARejilla(27, 10), D);
    }

    @Test
    void retoExtra05_clampArrastre() {
        assertEquals(0.0, Ej296CanvasInteractive.clampArrastre(-5, 100), D);
        assertEquals(100.0, Ej296CanvasInteractive.clampArrastre(150, 100), D);
        assertEquals(50.0, Ej296CanvasInteractive.clampArrastre(50, 100), D);
    }

    @Test
    void retoExtra06_colisionAABB() {
        assertTrue(Ej296CanvasInteractive.colisionAABB(0, 0, 10, 10, 5, 5, 10, 10));
        assertFalse(Ej296CanvasInteractive.colisionAABB(0, 0, 10, 10, 20, 20, 5, 5));
    }

    @Test
    void retoExtra07_dentroDePoligono() {
        List<double[]> cuadrado = List.of(
                new double[]{0, 0}, new double[]{4, 0}, new double[]{4, 4}, new double[]{0, 4});
        assertTrue(Ej296CanvasInteractive.dentroDePoligono(cuadrado, 2, 2));
        assertFalse(Ej296CanvasInteractive.dentroDePoligono(cuadrado, 5, 5));
    }

    @Test
    void retoExtra08_indiceFiguraTocada() {
        List<double[]> rects = List.of(
                new double[]{0, 0, 10, 10},
                new double[]{5, 5, 10, 10}); // pintado después -> encima
        assertEquals(1, Ej296CanvasInteractive.indiceFiguraTocada(rects, 7, 7)); // zona común -> el de arriba
        assertEquals(-1, Ej296CanvasInteractive.indiceFiguraTocada(rects, 50, 50)); // ninguno
    }

    @Test
    void retoExtra09_aCoordenadaCanvas() {
        assertArrayEquals(new double[]{10, 10},
                Ej296CanvasInteractive.aCoordenadaCanvas(60, 40, 50, 30), D);
    }

    @Test
    void retoExtra10_seleccionEnRectangulo() {
        List<double[]> puntos = List.of(
                new double[]{1, 1}, new double[]{5, 5}, new double[]{20, 20});
        assertEquals(List.of(0, 1), Ej296CanvasInteractive.seleccionEnRectangulo(puntos, 0, 0, 10, 10));
        assertEquals(List.of(), Ej296CanvasInteractive.seleccionEnRectangulo(puntos, 100, 100, 5, 5)); // límite
    }
}
