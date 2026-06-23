package com.masterclass.api.b41_anim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej323InputGameStateTest {

    @Test
    void siguienteEstado() {
        assertEquals("JUGANDO", Ej323InputGameState.siguienteEstado("MENU", "empezar"));
        assertEquals("MENU", Ej323InputGameState.siguienteEstado("MENU", "pausar")); // evento inválido: no cambia
    }

    @Test
    void ejeHorizontal() {
        assertEquals(-1, Ej323InputGameState.ejeHorizontal(true, false));
        assertEquals(1, Ej323InputGameState.ejeHorizontal(false, true));
        assertEquals(0, Ej323InputGameState.ejeHorizontal(true, true));   // caso límite: ambas se cancelan
        assertEquals(0, Ej323InputGameState.ejeHorizontal(false, false)); // ninguna
    }

    @Test
    void retoExtra01_ejeVertical() {
        assertEquals(-1, Ej323InputGameState.ejeVertical(true, false));
        assertEquals(1, Ej323InputGameState.ejeVertical(false, true));
    }

    @Test
    void retoExtra02_estaPausado() {
        assertTrue(Ej323InputGameState.estaPausado("PAUSA"));
        assertFalse(Ej323InputGameState.estaPausado("JUGANDO"));
    }

    @Test
    void retoExtra03_juegoActivo() {
        assertTrue(Ej323InputGameState.juegoActivo("JUGANDO"));
        assertFalse(Ej323InputGameState.juegoActivo("MENU"));
    }

    @Test
    void retoExtra04_puedePausar() {
        assertTrue(Ej323InputGameState.puedePausar("JUGANDO"));
        assertFalse(Ej323InputGameState.puedePausar("PAUSA"));
    }

    @Test
    void retoExtra05_togglePausa() {
        assertEquals("PAUSA", Ej323InputGameState.togglePausa("JUGANDO"));
        assertEquals("JUGANDO", Ej323InputGameState.togglePausa("PAUSA"));
        assertEquals("MENU", Ej323InputGameState.togglePausa("MENU"));
    }

    @Test
    void retoExtra06_esEstadoValido() {
        assertTrue(Ej323InputGameState.esEstadoValido("MENU"));
        assertFalse(Ej323InputGameState.esEstadoValido("VOLANDO"));
        assertFalse(Ej323InputGameState.esEstadoValido(null));
    }

    @Test
    void retoExtra07_teclaADireccion() {
        assertEquals("arriba", Ej323InputGameState.teclaADireccion("W"));
        assertEquals("derecha", Ej323InputGameState.teclaADireccion("D"));
        assertEquals("", Ej323InputGameState.teclaADireccion("Z"));
    }

    @Test
    void retoExtra08_aplicarMovimiento() {
        assertEquals(5, Ej323InputGameState.aplicarMovimiento(10, -1, 5));
    }

    @Test
    void retoExtra09_teclasPulsadas() {
        assertEquals(3, Ej323InputGameState.teclasPulsadas(new boolean[]{true, false, true, true}));
        assertEquals(0, Ej323InputGameState.teclasPulsadas(new boolean[]{}));
    }

    @Test
    void retoExtra10_vectorMovimiento() {
        assertArrayEquals(new int[]{-1, -1}, Ej323InputGameState.vectorMovimiento(true, false, true, false));
        assertArrayEquals(new int[]{0, 0}, Ej323InputGameState.vectorMovimiento(false, false, false, false));
    }
}
