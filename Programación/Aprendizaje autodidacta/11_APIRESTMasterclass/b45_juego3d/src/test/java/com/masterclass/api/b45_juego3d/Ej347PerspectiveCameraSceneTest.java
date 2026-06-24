package com.masterclass.api.b45_juego3d;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej347PerspectiveCameraSceneTest {

    private static final Camara CAM = new Camara(10, 200, 100, 1, 100);

    @Test
    void posicionEnPantalla() {
        assertArrayEquals(new double[]{100, 50}, Ej347PerspectiveCameraScene.posicionEnPantalla(new double[]{0, 0, 5}, CAM), 1e-9);
        assertArrayEquals(new double[]{120, 50}, Ej347PerspectiveCameraScene.posicionEnPantalla(new double[]{10, 0, 5}, CAM), 1e-9);
        assertNull(Ej347PerspectiveCameraScene.posicionEnPantalla(new double[]{1, 1, 0}, CAM)); // caso límite: z=0
    }

    @Test
    void dentroDelFrustum() {
        assertTrue(Ej347PerspectiveCameraScene.dentroDelFrustum(new double[]{0, 0, 5}, CAM));
        assertFalse(Ej347PerspectiveCameraScene.dentroDelFrustum(new double[]{0, 0, 200}, CAM)); // caso límite: > far
    }

    @Test
    void retoExtra01_aspecto() {
        assertEquals(2.0, Ej347PerspectiveCameraScene.aspecto(200, 100), 1e-9);
        assertEquals(-1.0, Ej347PerspectiveCameraScene.aspecto(200, 0), 1e-9); // caso límite
    }

    @Test
    void retoExtra02_focalDesdeFov() {
        assertEquals(100.0, Ej347PerspectiveCameraScene.focalDesdeFov(90, 200), 1e-6);
        assertEquals(-1.0, Ej347PerspectiveCameraScene.focalDesdeFov(0, 200), 1e-9); // caso límite
    }

    @Test
    void retoExtra03_estaDelante() {
        assertTrue(Ej347PerspectiveCameraScene.estaDelante(5, 1));
        assertFalse(Ej347PerspectiveCameraScene.estaDelante(1, 1)); // caso límite: justo en el plano
    }

    @Test
    void retoExtra04_profundidadNormalizada() {
        assertEquals(0.5, Ej347PerspectiveCameraScene.profundidadNormalizada(50, 0, 100), 1e-9);
        assertEquals(1.0, Ej347PerspectiveCameraScene.profundidadNormalizada(200, 0, 100), 1e-9); // recortado
    }

    @Test
    void retoExtra05_mapearAViewport() {
        assertEquals(100.0, Ej347PerspectiveCameraScene.mapearAViewport(0, 200), 1e-9);
        assertEquals(200.0, Ej347PerspectiveCameraScene.mapearAViewport(1, 200), 1e-9);
        assertEquals(0.0, Ej347PerspectiveCameraScene.mapearAViewport(-1, 200), 1e-9);
    }

    @Test
    void retoExtra06_coordenadasEsfericas() {
        assertArrayEquals(new double[]{1, 0, 0}, Ej347PerspectiveCameraScene.coordenadasEsfericas(1, 0, 0), 1e-9);
    }

    @Test
    void retoExtra07_escalaPorDistancia() {
        assertEquals(4.0, Ej347PerspectiveCameraScene.escalaPorDistancia(2, 5, 10), 1e-9);
        assertEquals(-1.0, Ej347PerspectiveCameraScene.escalaPorDistancia(2, 0, 10), 1e-9); // caso límite
    }

    @Test
    void retoExtra08_visibleEnPantalla() {
        assertTrue(Ej347PerspectiveCameraScene.visibleEnPantalla(new double[]{100, 50}, 200, 100));
        assertFalse(Ej347PerspectiveCameraScene.visibleEnPantalla(new double[]{-5, 50}, 200, 100)); // caso límite
    }

    @Test
    void retoExtra09_intensidadLuzPuntual() {
        assertEquals(4.0, Ej347PerspectiveCameraScene.intensidadLuzPuntual(new double[]{0, 0, 0}, new double[]{0, 0, 2}, 16), 1e-9);
    }

    @Test
    void retoExtra10_iluminacionDifusa() {
        assertEquals(1.0, Ej347PerspectiveCameraScene.iluminacionDifusa(new double[]{0, 0, 1}, new double[]{0, 0, 1}), 1e-9);
        assertEquals(0.0, Ej347PerspectiveCameraScene.iluminacionDifusa(new double[]{0, 0, 1}, new double[]{0, 0, -1}), 1e-9); // de espaldas
    }
}
