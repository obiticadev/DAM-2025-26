package com.masterclass.api.b41_anim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej320AnimationTimerLoopTest {

    private static final double D = 1e-9;

    @Test
    void deltaSegundos() {
        assertEquals(1.0, Ej320AnimationTimerLoop.deltaSegundos(1_000_000_000L, 2_000_000_000L), D);
        assertEquals(0.0, Ej320AnimationTimerLoop.deltaSegundos(0, 1_000_000L), D); // caso límite: primer frame
    }

    @Test
    void fpsDesdeDelta() {
        assertEquals(60, Ej320AnimationTimerLoop.fpsDesdeDelta(1.0 / 60));
        assertEquals(0, Ej320AnimationTimerLoop.fpsDesdeDelta(0)); // caso límite: dt 0
    }

    @Test
    void retoExtra01_nanosAMilis() {
        assertEquals(16, Ej320AnimationTimerLoop.nanosAMilis(16_000_000L));
    }

    @Test
    void retoExtra02_fotogramasEn() {
        assertEquals(120, Ej320AnimationTimerLoop.fotogramasEn(2.0, 60));
    }

    @Test
    void retoExtra03_limitarDelta() {
        assertEquals(0.05, Ej320AnimationTimerLoop.limitarDelta(0.5, 0.05), D);
        assertEquals(0.016, Ej320AnimationTimerLoop.limitarDelta(0.016, 0.05), D);
    }

    @Test
    void retoExtra04_acumular() {
        assertEquals(0.05, Ej320AnimationTimerLoop.acumular(0.03, 0.02), D);
    }

    @Test
    void retoExtra05_pasosFijos() {
        assertEquals(2, Ej320AnimationTimerLoop.pasosFijos(0.05, 0.02));
    }

    @Test
    void retoExtra06_restoAcumulador() {
        assertEquals(0.01, Ej320AnimationTimerLoop.restoAcumulador(0.05, 0.02), 1e-9);
    }

    @Test
    void retoExtra07_fpsPromedio() {
        assertEquals(60.0, Ej320AnimationTimerLoop.fpsPromedio(2.0, 120), D);
    }

    @Test
    void retoExtra08_alphaInterpolacion() {
        assertEquals(0.5, Ej320AnimationTimerLoop.alphaInterpolacion(0.05, 0.02), 1e-9);
    }

    @Test
    void retoExtra09_tiempoTranscurrido() {
        assertEquals(3.0, Ej320AnimationTimerLoop.tiempoTranscurrido(1_000_000_000L, 4_000_000_000L), D);
    }

    @Test
    void retoExtra10_presupuestoFrameMs() {
        assertEquals(1000.0 / 60, Ej320AnimationTimerLoop.presupuestoFrameMs(60), 1e-6);
        assertEquals(1000.0 / 30, Ej320AnimationTimerLoop.presupuestoFrameMs(30), 1e-6);
    }
}
