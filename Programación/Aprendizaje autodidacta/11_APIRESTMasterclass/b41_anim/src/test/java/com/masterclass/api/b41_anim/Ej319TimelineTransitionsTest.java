package com.masterclass.api.b41_anim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej319TimelineTransitionsTest {

    private static final double D = 1e-9;

    @Test
    void interpolarLineal() {
        assertEquals(100, Ej319TimelineTransitions.interpolarLineal(0, 200, 0.5), D);
        assertEquals(0, Ej319TimelineTransitions.interpolarLineal(0, 200, -1), D); // caso límite: antes del inicio
    }

    @Test
    void valorEnInstante() {
        assertEquals(50, Ej319TimelineTransitions.valorEnInstante(0, 100, 2, 1), D);
        assertEquals(100, Ej319TimelineTransitions.valorEnInstante(0, 100, 0, 5), D); // caso límite: duración 0
    }

    @Test
    void retoExtra01_opacidadFade() {
        assertEquals(0.5, Ej319TimelineTransitions.opacidadFade(0, 1, 0.5), D);
    }

    @Test
    void retoExtra02_progresoPorcentaje() {
        assertEquals(25, Ej319TimelineTransitions.progresoPorcentaje(1, 4));
        assertEquals(100, Ej319TimelineTransitions.progresoPorcentaje(5, 4)); // se recorta a 100
    }

    @Test
    void retoExtra03_duracionTotal() {
        assertEquals(6.5, Ej319TimelineTransitions.duracionTotal(0.5, 2, 3), D);
    }

    @Test
    void retoExtra04_interpolarEntero() {
        assertEquals(3, Ej319TimelineTransitions.interpolarEntero(0, 10, 0.25)); // 2.5 redondea a 3
    }

    @Test
    void retoExtra05_easeInQuad() {
        assertEquals(0.25, Ej319TimelineTransitions.easeInQuad(0.5), D);
    }

    @Test
    void retoExtra06_easeOutQuad() {
        assertEquals(0.75, Ej319TimelineTransitions.easeOutQuad(0.5), D);
    }

    @Test
    void retoExtra07_valorConAutoReverse() {
        assertEquals(0.0, Ej319TimelineTransitions.valorConAutoReverse(0), D);
        assertEquals(1.0, Ej319TimelineTransitions.valorConAutoReverse(0.5), D);
        assertEquals(0.0, Ej319TimelineTransitions.valorConAutoReverse(1), D);
    }

    @Test
    void retoExtra08_fotogramaActual() {
        assertEquals(2, Ej319TimelineTransitions.fotogramaActual(0.5, 1.0, 4));
    }

    @Test
    void retoExtra09_interpolarCanal() {
        assertEquals(128, Ej319TimelineTransitions.interpolarCanal(0, 255, 0.5)); // 127.5 redondea a 128
    }

    @Test
    void retoExtra10_instanteDeValor() {
        assertEquals(0.5, Ej319TimelineTransitions.instanteDeValor(0, 200, 100), D);
    }
}
