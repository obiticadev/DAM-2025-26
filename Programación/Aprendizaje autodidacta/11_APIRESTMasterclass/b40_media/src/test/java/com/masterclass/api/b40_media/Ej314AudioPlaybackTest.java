package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej314AudioPlaybackTest {

    @Test
    void siguienteEstado() {
        assertEquals("PLAYING", Ej314AudioPlayback.siguienteEstado("READY", "play"));
        assertEquals("PAUSED", Ej314AudioPlayback.siguienteEstado("PLAYING", "pause"));
        assertEquals("STOPPED", Ej314AudioPlayback.siguienteEstado("PLAYING", "stop"));
        assertEquals("PLAYING", Ej314AudioPlayback.siguienteEstado("PAUSED", "play"));
        assertEquals("STOPPED", Ej314AudioPlayback.siguienteEstado("STOPPED", "pause")); // transición inválida: sin cambio
    }

    @Test
    void clampVolumen() {
        assertEquals(1.0, Ej314AudioPlayback.clampVolumen(1.5), 1e-9);
        assertEquals(0.0, Ej314AudioPlayback.clampVolumen(-0.2), 1e-9);
        assertEquals(0.5, Ej314AudioPlayback.clampVolumen(0.5), 1e-9);
    }

    @Test
    void retoExtra01_formatearTiempo() {
        assertEquals("1:05", Ej314AudioPlayback.formatearTiempo(65));
        assertEquals("0:05", Ej314AudioPlayback.formatearTiempo(5)); // segundos con dos dígitos
    }

    @Test
    void retoExtra02_porcentajeProgreso() {
        assertEquals(25, Ej314AudioPlayback.porcentajeProgreso(30, 120));
        assertEquals(0, Ej314AudioPlayback.porcentajeProgreso(30, 0)); // caso límite: total 0
    }

    @Test
    void retoExtra03_estaReproduciendo() {
        assertTrue(Ej314AudioPlayback.estaReproduciendo("PLAYING"));
        assertFalse(Ej314AudioPlayback.estaReproduciendo("PAUSED"));
    }

    @Test
    void retoExtra04_puedePausar() {
        assertTrue(Ej314AudioPlayback.puedePausar("PLAYING"));
        assertFalse(Ej314AudioPlayback.puedePausar("READY"));
    }

    @Test
    void retoExtra05_cambiarVolumen() {
        assertEquals(1.0, Ej314AudioPlayback.cambiarVolumen(0.9, 0.2), 1e-9); // satura a 1
        assertEquals(0.2, Ej314AudioPlayback.cambiarVolumen(0.5, -0.3), 1e-9);
    }

    @Test
    void retoExtra06_volumenAPorcentaje() {
        assertEquals(50, Ej314AudioPlayback.volumenAPorcentaje(0.5));
        assertEquals(100, Ej314AudioPlayback.volumenAPorcentaje(1.3)); // clamp -> 100
    }

    @Test
    void retoExtra07_velocidadValida() {
        assertTrue(Ej314AudioPlayback.velocidadValida(1.0));
        assertFalse(Ej314AudioPlayback.velocidadValida(0.0));
        assertFalse(Ej314AudioPlayback.velocidadValida(9.0));
    }

    @Test
    void retoExtra08_estadoAlTerminar() {
        assertEquals("PLAYING", Ej314AudioPlayback.estadoAlTerminar(true));
        assertEquals("STOPPED", Ej314AudioPlayback.estadoAlTerminar(false));
    }

    @Test
    void retoExtra09_tiempoRestante() {
        assertEquals(60.0, Ej314AudioPlayback.tiempoRestante(40, 100), 1e-9);
        assertEquals(0.0, Ej314AudioPlayback.tiempoRestante(120, 100), 1e-9); // nunca negativo
    }

    @Test
    void retoExtra10_esEstadoValido() {
        assertTrue(Ej314AudioPlayback.esEstadoValido("PLAYING"));
        assertFalse(Ej314AudioPlayback.esEstadoValido("ROTO"));
        assertFalse(Ej314AudioPlayback.esEstadoValido(null));
    }
}
