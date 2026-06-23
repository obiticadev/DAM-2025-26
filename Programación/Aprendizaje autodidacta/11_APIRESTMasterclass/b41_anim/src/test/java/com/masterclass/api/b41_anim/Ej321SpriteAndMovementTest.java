package com.masterclass.api.b41_anim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej321SpriteAndMovementTest {

    private static final double D = 1e-9;

    @Test
    void nuevaPosicion() {
        assertEquals(20, Ej321SpriteAndMovement.nuevaPosicion(10, 5, 2), D);
        assertEquals(10, Ej321SpriteAndMovement.nuevaPosicion(10, 5, -1), D); // caso límite: dt negativo no mueve
    }

    @Test
    void limitarAPantalla() {
        assertEquals(15, Ej321SpriteAndMovement.limitarAPantalla(20, 0, 15), D);
        assertEquals(0, Ej321SpriteAndMovement.limitarAPantalla(-5, 0, 15), D); // caso límite: se sale por abajo
    }

    @Test
    void reboteEnBorde() {
        assertEquals(-5, Ej321SpriteAndMovement.reboteEnBorde(5, 20, 0, 20), D); // toca el borde -> invierte
        assertEquals(5, Ej321SpriteAndMovement.reboteEnBorde(5, 10, 0, 20), D);  // en el centro -> igual
    }

    @Test
    void retoExtra01_magnitudVelocidad() {
        assertEquals(5, Ej321SpriteAndMovement.magnitudVelocidad(3, 4), D);
    }

    @Test
    void retoExtra02_velocidadDiagonal() {
        double v = Ej321SpriteAndMovement.velocidadDiagonal(1.0);
        assertEquals(1.0, v * Math.sqrt(2), 1e-9); // al recomponer la diagonal da la rapidez original
    }

    @Test
    void retoExtra03_moverHaciaObjetivo() {
        assertEquals(3, Ej321SpriteAndMovement.moverHaciaObjetivo(0, 10, 3), D);
        assertEquals(2, Ej321SpriteAndMovement.moverHaciaObjetivo(0, 2, 3), D); // no se pasa del objetivo
    }

    @Test
    void retoExtra04_distancia() {
        assertEquals(5, Ej321SpriteAndMovement.distancia(0, 0, 3, 4), D);
    }

    @Test
    void retoExtra05_dentroDePantalla() {
        assertTrue(Ej321SpriteAndMovement.dentroDePantalla(0, 0, 10, 10, 100, 100));
        assertFalse(Ej321SpriteAndMovement.dentroDePantalla(95, 0, 10, 10, 100, 100)); // se sale por la derecha
    }

    @Test
    void retoExtra06_envolver() {
        assertEquals(5, Ej321SpriteAndMovement.envolver(105, 100), D);
        assertEquals(95, Ej321SpriteAndMovement.envolver(-5, 100), D); // negativo reaparece por el otro lado
    }

    @Test
    void retoExtra07_aplicarGravedad() {
        assertEquals(9.8, Ej321SpriteAndMovement.aplicarGravedad(0, 9.8, 1), D);
    }

    @Test
    void retoExtra08_aplicarFriccion() {
        assertEquals(9.0, Ej321SpriteAndMovement.aplicarFriccion(10, 0.9), D);
    }

    @Test
    void retoExtra09_limitarRapidez() {
        assertArrayEquals(new double[]{3, 4}, Ej321SpriteAndMovement.limitarRapidez(3, 4, 5), D);
        assertArrayEquals(new double[]{3, 4}, Ej321SpriteAndMovement.limitarRapidez(6, 8, 5), D); // rapidez 10 -> 5
    }

    @Test
    void retoExtra10_anguloHaciaGrados() {
        assertEquals(0, Ej321SpriteAndMovement.anguloHaciaGrados(1, 0));
        assertEquals(90, Ej321SpriteAndMovement.anguloHaciaGrados(0, 1));
        assertEquals(180, Ej321SpriteAndMovement.anguloHaciaGrados(-1, 0));
    }
}
