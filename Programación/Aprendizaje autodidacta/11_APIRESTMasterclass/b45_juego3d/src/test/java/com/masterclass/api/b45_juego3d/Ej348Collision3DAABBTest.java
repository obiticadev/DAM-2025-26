package com.masterclass.api.b45_juego3d;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej348Collision3DAABBTest {

    private static Caja caja(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new Caja(new double[]{minX, minY, minZ}, new double[]{maxX, maxY, maxZ});
    }

    @Test
    void colisionanAABB3D() {
        assertTrue(Ej348Collision3DAABB.colisionanAABB3D(caja(0, 0, 0, 2, 2, 2), caja(1, 1, 1, 3, 3, 3)));
        assertFalse(Ej348Collision3DAABB.colisionanAABB3D(caja(0, 0, 0, 2, 2, 2), caja(3, 3, 3, 4, 4, 4))); // caso límite
    }

    @Test
    void colisionEsfera() {
        assertTrue(Ej348Collision3DAABB.colisionEsfera(new double[]{0, 0, 0}, 1, new double[]{1.5, 0, 0}, 1));
        assertFalse(Ej348Collision3DAABB.colisionEsfera(new double[]{0, 0, 0}, 1, new double[]{3, 0, 0}, 1)); // caso límite
    }

    @Test
    void retoExtra01_puntoEnCaja() {
        assertTrue(Ej348Collision3DAABB.puntoEnCaja(new double[]{1, 1, 1}, caja(0, 0, 0, 2, 2, 2)));
        assertFalse(Ej348Collision3DAABB.puntoEnCaja(new double[]{3, 1, 1}, caja(0, 0, 0, 2, 2, 2)));
    }

    @Test
    void retoExtra02_volumen() {
        assertEquals(24.0, Ej348Collision3DAABB.volumen(caja(0, 0, 0, 2, 3, 4)), 1e-9);
    }

    @Test
    void retoExtra03_centro() {
        assertArrayEquals(new double[]{1, 1, 1}, Ej348Collision3DAABB.centro(caja(0, 0, 0, 2, 2, 2)), 1e-9);
    }

    @Test
    void retoExtra04_solapeEje() {
        assertEquals(1.0, Ej348Collision3DAABB.solapeEje(0, 2, 1, 3), 1e-9);
        assertEquals(0.0, Ej348Collision3DAABB.solapeEje(0, 1, 2, 3), 1e-9); // separados
    }

    @Test
    void retoExtra05_distanciaCuadrado() {
        assertEquals(9.0, Ej348Collision3DAABB.distanciaCuadrado(new double[]{0, 0, 0}, new double[]{1, 2, 2}), 1e-9);
        assertEquals(-1.0, Ej348Collision3DAABB.distanciaCuadrado(new double[]{0, 0, 0}, new double[]{1, 2}), 1e-9); // caso límite
    }

    @Test
    void retoExtra06_colisionEsferaCaja() {
        assertTrue(Ej348Collision3DAABB.colisionEsferaCaja(new double[]{3, 1, 1}, 1, caja(0, 0, 0, 2, 2, 2)));
        assertFalse(Ej348Collision3DAABB.colisionEsferaCaja(new double[]{4, 1, 1}, 1, caja(0, 0, 0, 2, 2, 2))); // caso límite
    }

    @Test
    void retoExtra07_cajaContieneCaja() {
        assertTrue(Ej348Collision3DAABB.cajaContieneCaja(caja(0, 0, 0, 4, 4, 4), caja(1, 1, 1, 2, 2, 2)));
        assertFalse(Ej348Collision3DAABB.cajaContieneCaja(caja(0, 0, 0, 2, 2, 2), caja(1, 1, 1, 3, 3, 3))); // solape parcial
    }

    @Test
    void retoExtra08_penetracionEsferas() {
        assertEquals(0.5, Ej348Collision3DAABB.penetracionEsferas(new double[]{0, 0, 0}, 1, new double[]{1.5, 0, 0}, 1), 1e-9);
        assertEquals(0.0, Ej348Collision3DAABB.penetracionEsferas(new double[]{0, 0, 0}, 1, new double[]{5, 0, 0}, 1), 1e-9); // sin contacto
    }

    @Test
    void retoExtra09_rayoColisionaCaja() {
        assertTrue(Ej348Collision3DAABB.rayoColisionaCaja(new double[]{-5, 1, 1}, new double[]{1, 0, 0}, caja(0, 0, 0, 2, 2, 2)));
        assertFalse(Ej348Collision3DAABB.rayoColisionaCaja(new double[]{-5, 1, 1}, new double[]{-1, 0, 0}, caja(0, 0, 0, 2, 2, 2))); // se aleja
    }

    @Test
    void retoExtra10_resolverRebote() {
        assertArrayEquals(new double[]{1, 1, 0},
                Ej348Collision3DAABB.resolverRebote(new double[]{1, -1, 0}, new double[]{0, 1, 0}), 1e-9);
    }
}
