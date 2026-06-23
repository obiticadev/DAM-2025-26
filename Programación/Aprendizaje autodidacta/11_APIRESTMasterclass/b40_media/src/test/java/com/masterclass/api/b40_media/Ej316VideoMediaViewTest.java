package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej316VideoMediaViewTest {

    private static final double D = 1e-9;

    @Test
    void escalarParaCaber() {
        assertArrayEquals(new double[]{1280, 720}, Ej316VideoMediaView.escalarParaCaber(1920, 1080, 1280, 720), 1e-6);
        assertArrayEquals(new double[]{100, 50}, Ej316VideoMediaView.escalarParaCaber(200, 100, 100, 100), D);
        assertNull(Ej316VideoMediaView.escalarParaCaber(0, 100, 100, 100)); // caso límite: vídeo inválido
    }

    @Test
    void esApaisado() {
        assertTrue(Ej316VideoMediaView.esApaisado(16, 9));
        assertFalse(Ej316VideoMediaView.esApaisado(9, 16));
        assertFalse(Ej316VideoMediaView.esApaisado(5, 5)); // cuadrado no es apaisado
    }

    @Test
    void retoExtra01_relacionAspecto() {
        assertEquals(16.0 / 9.0, Ej316VideoMediaView.relacionAspecto(16, 9), D);
        assertEquals(0.0, Ej316VideoMediaView.relacionAspecto(16, 0), D); // caso límite
    }

    @Test
    void retoExtra02_escalarParaRellenar() {
        assertArrayEquals(new double[]{200, 100}, Ej316VideoMediaView.escalarParaRellenar(200, 100, 100, 100), D);
    }

    @Test
    void retoExtra03_barraLetterbox() {
        assertEquals(90.0, Ej316VideoMediaView.barraLetterbox(720, 540), D);
        assertEquals(0.0, Ej316VideoMediaView.barraLetterbox(540, 720), D); // contenido mayor -> sin barra
    }

    @Test
    void retoExtra04_cabeSinEscalar() {
        assertTrue(Ej316VideoMediaView.cabeSinEscalar(100, 100, 200, 200));
        assertFalse(Ej316VideoMediaView.cabeSinEscalar(300, 100, 200, 200));
    }

    @Test
    void retoExtra05_offsetCentrado() {
        assertEquals(30.0, Ej316VideoMediaView.offsetCentrado(100, 40), D);
    }

    @Test
    void retoExtra06_es16por9() {
        assertTrue(Ej316VideoMediaView.es16por9(1920, 1080));
        assertFalse(Ej316VideoMediaView.es16por9(1024, 768)); // 4:3
    }

    @Test
    void retoExtra07_escalarAAncho() {
        assertArrayEquals(new double[]{960, 540}, Ej316VideoMediaView.escalarAAncho(1920, 1080, 960), D);
    }

    @Test
    void retoExtra08_escalarAAlto() {
        assertArrayEquals(new double[]{960, 540}, Ej316VideoMediaView.escalarAAlto(1920, 1080, 540), D);
    }

    @Test
    void retoExtra09_porcentajeEscala() {
        assertEquals(75, Ej316VideoMediaView.porcentajeEscala(1920, 1440));
    }

    @Test
    void retoExtra10_recorteCover() {
        assertArrayEquals(new double[]{100, 0}, Ej316VideoMediaView.recorteCover(200, 100, 100, 100), D);
    }
}
