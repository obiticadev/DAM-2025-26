package com.masterclass.api.b45_juego3d;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej350MiniGame3DTest {

    @Test
    void mover() {
        assertArrayEquals(new double[]{1, 0, 0},
                Ej350MiniGame3D.mover(new double[]{0, 0, 0}, new double[]{1, 0, 0}, 2, 0.5), 1e-9);
        assertNull(Ej350MiniGame3D.mover(new double[]{0, 0, 0}, new double[]{1, 0}, 2, 0.5)); // caso límite
    }

    @Test
    void puntuarSiRecoge() {
        assertEquals(1, Ej350MiniGame3D.puntuarSiRecoge(0, new double[]{0, 0, 0}, new double[]{0.5, 0, 0}, 1));
        assertEquals(5, Ej350MiniGame3D.puntuarSiRecoge(5, new double[]{0, 0, 0}, new double[]{10, 0, 0}, 1)); // caso límite: lejos
        assertEquals(-1, Ej350MiniGame3D.puntuarSiRecoge(0, new double[]{0, 0, 0}, new double[]{1, 0}, 1)); // inválido
    }

    @Test
    void retoExtra01_distancia() {
        assertEquals(5.0, Ej350MiniGame3D.distancia(new double[]{0, 0, 0}, new double[]{0, 3, 4}), 1e-9);
        assertEquals(-1.0, Ej350MiniGame3D.distancia(new double[]{0, 0, 0}, new double[]{0, 3}), 1e-9); // caso límite
    }

    @Test
    void retoExtra02_dentroDelMundo() {
        assertTrue(Ej350MiniGame3D.dentroDelMundo(new double[]{1, 1, 1}, 2));
        assertFalse(Ej350MiniGame3D.dentroDelMundo(new double[]{3, 0, 0}, 2)); // caso límite
    }

    @Test
    void retoExtra03_clampPosicion() {
        assertArrayEquals(new double[]{2, -2, 0}, Ej350MiniGame3D.clampPosicion(new double[]{5, -5, 0}, 2), 1e-9);
    }

    @Test
    void retoExtra04_camaraSigue() {
        assertArrayEquals(new double[]{1, 6, -9},
                Ej350MiniGame3D.camaraSigue(new double[]{1, 1, 1}, new double[]{0, 5, -10}), 1e-9);
    }

    @Test
    void retoExtra05_nivelPorPuntos() {
        assertEquals(1, Ej350MiniGame3D.nivelPorPuntos(0));
        assertEquals(3, Ej350MiniGame3D.nivelPorPuntos(25));
    }

    @Test
    void retoExtra06_vidaTrasGolpe() {
        assertEquals(2, Ej350MiniGame3D.vidaTrasGolpe(3, true));
        assertEquals(0, Ej350MiniGame3D.vidaTrasGolpe(0, true)); // caso límite: no baja de cero
    }

    @Test
    void retoExtra07_gameOver() {
        assertTrue(Ej350MiniGame3D.gameOver(0));
        assertFalse(Ej350MiniGame3D.gameOver(1));
    }

    @Test
    void retoExtra08_spawnPosicion() {
        double[][] puntos = {{0, 0, 0}, {1, 1, 1}};
        assertArrayEquals(new double[]{1, 1, 1}, Ej350MiniGame3D.spawnPosicion(3, puntos), 1e-9); // 3%2=1
        assertNull(Ej350MiniGame3D.spawnPosicion(0, new double[][]{})); // caso límite
    }

    @Test
    void retoExtra09_aplicarGravedadSalto() {
        assertEquals(-5.0, Ej350MiniGame3D.aplicarGravedadSalto(0, -10, 0.5), 1e-9);
    }

    @Test
    void retoExtra10_enemigoPersigue() {
        assertArrayEquals(new double[]{2, 0, 0},
                Ej350MiniGame3D.enemigoPersigue(new double[]{0, 0, 0}, new double[]{10, 0, 0}, 2, 1), 1e-9);
    }
}
