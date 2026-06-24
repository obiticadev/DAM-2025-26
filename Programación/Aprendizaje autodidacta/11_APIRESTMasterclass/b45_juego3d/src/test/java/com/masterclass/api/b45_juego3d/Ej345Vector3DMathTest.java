package com.masterclass.api.b45_juego3d;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej345Vector3DMathTest {

    @Test
    void productoVectorial() {
        assertArrayEquals(new double[]{0, 0, 1},
                Ej345Vector3DMath.productoVectorial(new double[]{1, 0, 0}, new double[]{0, 1, 0}), 1e-9);
        assertNull(Ej345Vector3DMath.productoVectorial(new double[]{1, 0, 0}, new double[]{0, 1})); // caso límite
    }

    @Test
    void normalizar() {
        assertArrayEquals(new double[]{1, 0, 0}, Ej345Vector3DMath.normalizar(new double[]{3, 0, 0}), 1e-9);
        assertArrayEquals(new double[]{0, 0, 0}, Ej345Vector3DMath.normalizar(new double[]{0, 0, 0}), 1e-9); // caso límite
    }

    @Test
    void distancia() {
        assertEquals(3.0, Ej345Vector3DMath.distancia(new double[]{0, 0, 0}, new double[]{1, 2, 2}), 1e-9);
        assertEquals(-1.0, Ej345Vector3DMath.distancia(new double[]{0, 0, 0}, new double[]{1, 2}), 1e-9); // caso límite
    }

    @Test
    void retoExtra01_productoEscalar() {
        assertEquals(32.0, Ej345Vector3DMath.productoEscalar(new double[]{1, 2, 3}, new double[]{4, 5, 6}), 1e-9);
        assertEquals(0.0, Ej345Vector3DMath.productoEscalar(new double[]{1, 0, 0}, new double[]{0, 1, 0}), 1e-9);
    }

    @Test
    void retoExtra02_magnitud() {
        assertEquals(5.0, Ej345Vector3DMath.magnitud(new double[]{3, 4, 0}), 1e-9);
        assertEquals(0.0, Ej345Vector3DMath.magnitud(new double[]{0, 0, 0}), 1e-9);
    }

    @Test
    void retoExtra03_sumar() {
        assertArrayEquals(new double[]{5, 7, 9},
                Ej345Vector3DMath.sumar(new double[]{1, 2, 3}, new double[]{4, 5, 6}), 1e-9);
        assertNull(Ej345Vector3DMath.sumar(new double[]{1, 2, 3}, new double[]{4, 5}));
    }

    @Test
    void retoExtra04_escalar() {
        assertArrayEquals(new double[]{2, 4, 6}, Ej345Vector3DMath.escalar(new double[]{1, 2, 3}, 2), 1e-9);
        assertArrayEquals(new double[]{0, 0, 0}, Ej345Vector3DMath.escalar(new double[]{1, 2, 3}, 0), 1e-9);
    }

    @Test
    void retoExtra05_anguloEntre() {
        assertEquals(90.0, Ej345Vector3DMath.anguloEntre(new double[]{1, 0, 0}, new double[]{0, 1, 0}), 1e-6);
        assertEquals(0.0, Ej345Vector3DMath.anguloEntre(new double[]{1, 0, 0}, new double[]{2, 0, 0}), 1e-6);
    }

    @Test
    void retoExtra06_proyeccion() {
        assertArrayEquals(new double[]{2, 0, 0},
                Ej345Vector3DMath.proyeccion(new double[]{2, 2, 0}, new double[]{1, 0, 0}), 1e-9);
        assertNull(Ej345Vector3DMath.proyeccion(new double[]{2, 2, 0}, new double[]{0, 0, 0})); // caso límite
    }

    @Test
    void retoExtra07_reflejar() {
        assertArrayEquals(new double[]{1, 1, 0},
                Ej345Vector3DMath.reflejar(new double[]{1, -1, 0}, new double[]{0, 1, 0}), 1e-9);
    }

    @Test
    void retoExtra08_lerp() {
        assertArrayEquals(new double[]{5, 5, 5},
                Ej345Vector3DMath.lerp(new double[]{0, 0, 0}, new double[]{10, 10, 10}, 0.5), 1e-9);
        assertArrayEquals(new double[]{10, 10, 10},
                Ej345Vector3DMath.lerp(new double[]{0, 0, 0}, new double[]{10, 10, 10}, 2), 1e-9); // t recortado
    }

    @Test
    void retoExtra09_tripleProducto() {
        assertEquals(1.0, Ej345Vector3DMath.tripleProducto(
                new double[]{1, 0, 0}, new double[]{0, 1, 0}, new double[]{0, 0, 1}), 1e-9);
    }

    @Test
    void retoExtra10_sonCoplanares() {
        assertTrue(Ej345Vector3DMath.sonCoplanares(
                new double[]{1, 0, 0}, new double[]{0, 1, 0}, new double[]{1, 1, 0}));
        assertFalse(Ej345Vector3DMath.sonCoplanares(
                new double[]{1, 0, 0}, new double[]{0, 1, 0}, new double[]{0, 0, 1})); // caso límite
    }
}
