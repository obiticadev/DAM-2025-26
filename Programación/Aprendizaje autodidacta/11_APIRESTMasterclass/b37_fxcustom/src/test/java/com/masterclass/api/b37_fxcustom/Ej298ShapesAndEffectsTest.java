package com.masterclass.api.b37_fxcustom;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej298ShapesAndEffectsTest {

    private static final double D = 1e-6;

    @Test
    void mezclarColores() {
        assertArrayEquals(new int[]{127, 127, 127},
                Ej298ShapesAndEffects.mezclarColores(new int[]{0, 0, 0}, new int[]{255, 255, 255}));
        assertArrayEquals(new int[]{50, 50, 50},
                Ej298ShapesAndEffects.mezclarColores(new int[]{0, 0, 0}, new int[]{100, 100, 100}));
    }

    @Test
    void aHex() {
        assertEquals("#FF0080", Ej298ShapesAndEffects.aHex(255, 0, 128));
        assertEquals("#000000", Ej298ShapesAndEffects.aHex(0, 0, 0)); // caso límite: ceros con padding
    }

    @Test
    void retoExtra01_desdeHex() {
        assertArrayEquals(new int[]{255, 0, 128}, Ej298ShapesAndEffects.desdeHex("#FF0080"));
    }

    @Test
    void retoExtra02_aclarar() {
        assertArrayEquals(new int[]{150, 150, 150},
                Ej298ShapesAndEffects.aclarar(new int[]{100, 100, 100}, 0.5));
        assertArrayEquals(new int[]{255, 255, 255},
                Ej298ShapesAndEffects.aclarar(new int[]{200, 200, 200}, 1.0)); // topa en 255
    }

    @Test
    void retoExtra03_oscurecer() {
        assertArrayEquals(new int[]{50, 50, 50},
                Ej298ShapesAndEffects.oscurecer(new int[]{100, 100, 100}, 0.5));
    }

    @Test
    void retoExtra04_luminancia() {
        assertEquals(255.0, Ej298ShapesAndEffects.luminancia(new int[]{255, 255, 255}), D);
        assertEquals(0.0, Ej298ShapesAndEffects.luminancia(new int[]{0, 0, 0}), D);
    }

    @Test
    void retoExtra05_colorTextoLegible() {
        assertEquals("negro", Ej298ShapesAndEffects.colorTextoLegible(new int[]{255, 255, 255}));
        assertEquals("blanco", Ej298ShapesAndEffects.colorTextoLegible(new int[]{0, 0, 0}));
    }

    @Test
    void retoExtra06_conAlpha() {
        assertEquals("rgba(255, 0, 0, 0.5)",
                Ej298ShapesAndEffects.conAlpha(new int[]{255, 0, 0}, 0.5));
    }

    @Test
    void retoExtra07_interpolar() {
        assertArrayEquals(new int[]{128, 128, 128},
                Ej298ShapesAndEffects.interpolar(new int[]{0, 0, 0}, new int[]{255, 255, 255}, 0.5));
        assertArrayEquals(new int[]{0, 0, 0},
                Ej298ShapesAndEffects.interpolar(new int[]{0, 0, 0}, new int[]{255, 255, 255}, 0.0)); // t=0
    }

    @Test
    void retoExtra08_radioSombra() {
        assertEquals(5.0, Ej298ShapesAndEffects.radioSombra(10, 0.5), D);
        assertEquals(0.0, Ej298ShapesAndEffects.radioSombra(10, -2), D); // no negativo
    }

    @Test
    void retoExtra09_mezclarPonderada() {
        assertArrayEquals(new int[]{25, 25, 25},
                Ej298ShapesAndEffects.mezclarPonderada(new int[]{0, 0, 0}, new int[]{100, 100, 100}, 0.25));
    }

    @Test
    void retoExtra10_paletaDesde() {
        List<int[]> paleta = Ej298ShapesAndEffects.paletaDesde(new int[]{200, 200, 200}, 2);
        assertEquals(2, paleta.size());
        assertArrayEquals(new int[]{200, 200, 200}, paleta.get(0)); // i=0 -> base
        assertArrayEquals(new int[]{150, 150, 150}, paleta.get(1)); // i=1 -> oscurecido 0.25
    }
}
