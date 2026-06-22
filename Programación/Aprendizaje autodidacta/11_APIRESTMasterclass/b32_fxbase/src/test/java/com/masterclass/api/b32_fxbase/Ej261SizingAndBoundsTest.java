package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej261SizingAndBoundsTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void tamanoEfectivo() {
        assertEquals(800, Ej261SizingAndBounds.tamanoEfectivo(1000, 200, 800), 0.001);
        assertEquals(200, Ej261SizingAndBounds.tamanoEfectivo(100, 200, 800), 0.001); // caso límite
        assertEquals(500, Ej261SizingAndBounds.tamanoEfectivo(500, 200, 800), 0.001);
    }

    @Test
    void boundsConInsets() {
        assertArrayEquals(new double[]{280, 180},
                Ej261SizingAndBounds.boundsConInsets(300, 200, 10, 10, 10, 10), 0.001);
        // caso límite: insets mayores que el tamaño -> contenido 0
        assertArrayEquals(new double[]{0, 0},
                Ej261SizingAndBounds.boundsConInsets(10, 10, 20, 20, 20, 20), 0.001);
    }

    @Test
    void retoExtra01_esRedimensionable() {
        assertTrue(Ej261SizingAndBounds.esRedimensionable(100, 400));
        assertFalse(Ej261SizingAndBounds.esRedimensionable(100, 100)); // caso límite
    }

    @Test
    void retoExtra02_areaContenido() {
        assertEquals(200.0, Ej261SizingAndBounds.areaContenido(20, 10), 0.001);
        assertEquals(0.0, Ej261SizingAndBounds.areaContenido(-5, 10), 0.001); // caso límite
    }

    @Test
    void retoExtra03_aplicarPref() {
        Region r = Ej261SizingAndBounds.aplicarPref(new VBox(), 120, 80);
        assertEquals(120, r.getPrefWidth(), 0.001);
        assertEquals(80, r.getPrefHeight(), 0.001);
    }

    @Test
    void retoExtra04_aplicarMinMaxAncho() {
        Region r = Ej261SizingAndBounds.aplicarMinMaxAncho(new VBox(), 50, 300);
        assertEquals(50, r.getMinWidth(), 0.001);
        assertEquals(300, r.getMaxWidth(), 0.001);
    }

    @Test
    void retoExtra05_cabe() {
        assertTrue(Ej261SizingAndBounds.cabe(200, 300));
        assertFalse(Ej261SizingAndBounds.cabe(400, 300));
    }

    @Test
    void retoExtra06_clamp() {
        assertEquals(5, Ej261SizingAndBounds.clamp(10, 0, 5), 0.001);
        assertEquals(0, Ej261SizingAndBounds.clamp(-3, 0, 5), 0.001);
        assertEquals(3, Ej261SizingAndBounds.clamp(3, 0, 5), 0.001);
    }

    @Test
    void retoExtra07_anchoConInsets() {
        assertEquals(300, Ej261SizingAndBounds.anchoConInsets(280, 10, 10), 0.001);
    }

    @Test
    void retoExtra08_expandirParaLlenar() {
        assertEquals(300, Ej261SizingAndBounds.expandirParaLlenar(100, 400, 300), 0.001);
        assertEquals(400, Ej261SizingAndBounds.expandirParaLlenar(100, 400, 500), 0.001); // topa en max
        assertEquals(100, Ej261SizingAndBounds.expandirParaLlenar(100, 400, 50), 0.001);  // no baja de pref
    }

    @Test
    void retoExtra09_redondearPixel() {
        assertEquals(10, Ej261SizingAndBounds.redondearPixel(10.4), 0.001);
        assertEquals(11, Ej261SizingAndBounds.redondearPixel(10.6), 0.001);
    }

    @Test
    void retoExtra10_escalaParaCaber() {
        assertEquals(0.5, Ej261SizingAndBounds.escalaParaCaber(1600, 1200, 800, 800), 0.001);
        assertEquals(1.0, Ej261SizingAndBounds.escalaParaCaber(400, 300, 800, 800), 0.001); // ya cabe
    }
}
