package com.masterclass.api.b41_anim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej322CollisionDetectionTest {

    private static final double D = 1e-9;

    @Test
    void colisionanAABB() {
        assertTrue(Ej322CollisionDetection.colisionanAABB(0, 0, 10, 10, 5, 5, 10, 10));
        assertFalse(Ej322CollisionDetection.colisionanAABB(0, 0, 10, 10, 20, 20, 5, 5)); // caso límite: lejos
    }

    @Test
    void colisionanCirculos() {
        assertTrue(Ej322CollisionDetection.colisionanCirculos(0, 0, 5, 6, 0, 5));   // distancia 6 < 10
        assertFalse(Ej322CollisionDetection.colisionanCirculos(0, 0, 5, 11, 0, 5)); // caso límite: distancia 11 > 10
    }

    @Test
    void retoExtra01_puntoEnRect() {
        assertTrue(Ej322CollisionDetection.puntoEnRect(5, 5, 0, 0, 10, 10));
        assertFalse(Ej322CollisionDetection.puntoEnRect(15, 5, 0, 0, 10, 10));
    }

    @Test
    void retoExtra02_puntoEnCirculo() {
        assertTrue(Ej322CollisionDetection.puntoEnCirculo(3, 4, 0, 0, 5));  // justo en el borde
        assertFalse(Ej322CollisionDetection.puntoEnCirculo(4, 4, 0, 0, 5)); // 32 > 25
    }

    @Test
    void retoExtra03_solapamientoX() {
        assertEquals(5, Ej322CollisionDetection.solapamientoX(0, 10, 5, 10), D);
        assertEquals(0, Ej322CollisionDetection.solapamientoX(0, 10, 20, 10), D); // no se tocan
    }

    @Test
    void retoExtra04_centroX() {
        assertEquals(20, Ej322CollisionDetection.centroX(10, 20), D);
    }

    @Test
    void retoExtra05_responderRebote() {
        assertEquals(-5, Ej322CollisionDetection.responderRebote(5, true), D);
        assertEquals(5, Ej322CollisionDetection.responderRebote(5, false), D);
    }

    @Test
    void retoExtra06_distanciaCentros() {
        assertEquals(5, Ej322CollisionDetection.distanciaCentros(0, 0, 3, 4), D);
    }

    @Test
    void retoExtra07_colisionRectCirculo() {
        assertTrue(Ej322CollisionDetection.colisionRectCirculo(0, 0, 10, 10, 13, 5, 5));  // a 3 del borde
        assertFalse(Ej322CollisionDetection.colisionRectCirculo(0, 0, 10, 10, 20, 5, 5)); // a 10 del borde
    }

    @Test
    void retoExtra08_contenidoEn() {
        assertTrue(Ej322CollisionDetection.contenidoEn(2, 2, 4, 4, 0, 0, 10, 10));
        assertFalse(Ej322CollisionDetection.contenidoEn(8, 8, 4, 4, 0, 0, 10, 10)); // se sale
    }

    @Test
    void retoExtra09_ladoColision() {
        // A(0,2,4,4) entra por la izquierda de B(3,0,6,6): penetración mínima por la izquierda.
        assertEquals("izquierda", Ej322CollisionDetection.ladoColision(0, 2, 4, 4, 3, 0, 6, 6));
        assertEquals("", Ej322CollisionDetection.ladoColision(0, 0, 1, 1, 50, 50, 1, 1)); // no colisionan
    }

    @Test
    void retoExtra10_chocaParedLateral() {
        assertTrue(Ej322CollisionDetection.chocaParedLateral(0, 10, 100));   // toca la izquierda
        assertFalse(Ej322CollisionDetection.chocaParedLateral(45, 10, 100)); // en medio
        assertTrue(Ej322CollisionDetection.chocaParedLateral(95, 10, 100));  // toca la derecha
    }
}
