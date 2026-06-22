package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej257StageWindowTest {

    @Test
    void centrar() {
        assertArrayEquals(new double[]{560, 240}, Ej257StageWindow.centrar(800, 600, 1920, 1080), 0.001);
        // caso límite: ventana más grande que la pantalla -> esquina en (0,0)
        assertArrayEquals(new double[]{0, 0}, Ej257StageWindow.centrar(2000, 1200, 1920, 1080), 0.001);
    }

    @Test
    void aplicarLimitesTamano() {
        assertEquals(1280, Ej257StageWindow.aplicarLimitesTamano(2000, 400, 1280), 0.001);
        assertEquals(400, Ej257StageWindow.aplicarLimitesTamano(100, 400, 1280), 0.001); // caso límite
        assertEquals(800, Ej257StageWindow.aplicarLimitesTamano(800, 400, 1280), 0.001);
    }

    @Test
    void retoExtra01_tituloPorDefecto() {
        assertEquals("Mi App", Ej257StageWindow.tituloPorDefecto("Mi App"));
        assertEquals("Sin título", Ej257StageWindow.tituloPorDefecto("   "));
    }

    @Test
    void retoExtra02_areaVentana() {
        assertEquals(480000.0, Ej257StageWindow.areaVentana(800, 600), 0.001);
    }

    @Test
    void retoExtra03_cabeEnPantalla() {
        assertTrue(Ej257StageWindow.cabeEnPantalla(800, 600, 1920, 1080));
        assertFalse(Ej257StageWindow.cabeEnPantalla(2000, 600, 1920, 1080));
    }

    @Test
    void retoExtra04_clampPosicion() {
        // ventana 800x600 colocada en (1500,-50) sobre 1920x1080 -> derecha y arriba mal
        assertArrayEquals(new double[]{1120, 0},
                Ej257StageWindow.clampPosicion(1500, -50, 800, 600, 1920, 1080), 0.001);
    }

    @Test
    void retoExtra05_modalidadBloquea() {
        assertTrue(Ej257StageWindow.modalidadBloquea("APPLICATION_MODAL"));
        assertFalse(Ej257StageWindow.modalidadBloquea("NONE"));
    }

    @Test
    void retoExtra06_relacionAspecto() {
        assertEquals(1920.0 / 1080.0, Ej257StageWindow.relacionAspecto(1920, 1080), 0.0001);
        assertEquals(0.0, Ej257StageWindow.relacionAspecto(100, 0), 0.0001); // caso límite
    }

    @Test
    void retoExtra07_escalarManteniendoAspecto() {
        assertArrayEquals(new double[]{800, 600},
                Ej257StageWindow.escalarManteniendoAspecto(1600, 1200, 800, 800), 0.001);
        // ya cabe: no se agranda
        assertArrayEquals(new double[]{400, 300},
                Ej257StageWindow.escalarManteniendoAspecto(400, 300, 800, 800), 0.001);
    }

    @Test
    void retoExtra08_siguienteCascada() {
        assertArrayEquals(new double[]{130, 130}, Ej257StageWindow.siguienteCascada(100, 100, 30), 0.001);
    }

    @Test
    void retoExtra09_pilaDeVentanas() {
        List<double[]> pila = Ej257StageWindow.pilaDeVentanas(3, 30);
        assertEquals(3, pila.size());
        assertArrayEquals(new double[]{0, 0}, pila.get(0), 0.001);
        assertArrayEquals(new double[]{60, 60}, pila.get(2), 0.001);
        assertTrue(Ej257StageWindow.pilaDeVentanas(0, 30).isEmpty()); // caso límite
    }

    @Test
    void retoExtra10_dpToPx() {
        assertEquals(200, Ej257StageWindow.dpToPx(100, 2.0));
        assertEquals(150, Ej257StageWindow.dpToPx(100, 1.5));
    }
}
