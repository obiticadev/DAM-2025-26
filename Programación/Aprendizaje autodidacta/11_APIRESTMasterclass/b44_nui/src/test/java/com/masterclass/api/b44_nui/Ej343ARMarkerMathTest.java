package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej343ARMarkerMathTest {

    @Test
    void proyectar() {
        assertArrayEquals(new double[]{1, 2}, Ej343ARMarkerMath.proyectar(new double[]{2, 4, 2}, 1.0), 1e-9);
        assertNull(Ej343ARMarkerMath.proyectar(new double[]{2, 4, 0}, 1.0)); // caso límite: z=0
    }

    @Test
    void multiplicarMatrizVector() {
        double[][] id = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        assertArrayEquals(new double[]{1, 2, 3}, Ej343ARMarkerMath.multiplicarMatrizVector(id, new double[]{1, 2, 3}), 1e-9);
        assertNull(Ej343ARMarkerMath.multiplicarMatrizVector(id, new double[]{1, 2})); // caso límite
    }

    @Test
    void retoExtra01_normalizarPerspectiva() {
        assertArrayEquals(new double[]{1, 2}, Ej343ARMarkerMath.normalizarPerspectiva(new double[]{2, 4, 2}), 1e-9);
        assertNull(Ej343ARMarkerMath.normalizarPerspectiva(new double[]{2, 4, 0})); // caso límite
    }

    @Test
    void retoExtra02_distanciaReproyeccion() {
        assertEquals(5.0, Ej343ARMarkerMath.distanciaReproyeccion(new double[]{0, 0}, new double[]{3, 4}), 1e-9);
        assertEquals(0.0, Ej343ARMarkerMath.distanciaReproyeccion(new double[]{1, 1}, new double[]{1, 1}), 1e-9);
    }

    @Test
    void retoExtra03_esMatrizSingular() {
        double[][] id = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        double[][] ceros = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertFalse(Ej343ARMarkerMath.esMatrizSingular(id));
        assertTrue(Ej343ARMarkerMath.esMatrizSingular(ceros)); // caso límite
    }

    @Test
    void retoExtra04_rotarZ() {
        assertArrayEquals(new double[]{0, 1, 0}, Ej343ARMarkerMath.rotarZ(new double[]{1, 0, 0}, 90), 1e-9);
    }

    @Test
    void retoExtra05_gradosARadianes() {
        assertEquals(Math.PI, Ej343ARMarkerMath.gradosARadianes(180), 1e-9);
        assertEquals(0.0, Ej343ARMarkerMath.gradosARadianes(0), 1e-9);
    }

    @Test
    void retoExtra06_escalarMundo() {
        assertArrayEquals(new double[]{2, 4, 6}, Ej343ARMarkerMath.escalarMundo(new double[]{1, 2, 3}, 2), 1e-9);
        assertArrayEquals(new double[]{0, 0, 0}, Ej343ARMarkerMath.escalarMundo(new double[]{1, 2, 3}, 0), 1e-9);
    }

    @Test
    void retoExtra07_centroDeMarcador() {
        double[][] esquinas = {{0, 0}, {2, 0}, {2, 2}, {0, 2}};
        assertArrayEquals(new double[]{1, 1}, Ej343ARMarkerMath.centroDeMarcador(esquinas), 1e-9);
        assertNull(Ej343ARMarkerMath.centroDeMarcador(new double[][]{})); // caso límite
    }

    @Test
    void retoExtra08_dentroDelMarcador() {
        assertTrue(Ej343ARMarkerMath.dentroDelMarcador(new double[]{1, 1}, new double[]{0, 0}, new double[]{2, 2}));
        assertFalse(Ej343ARMarkerMath.dentroDelMarcador(new double[]{3, 1}, new double[]{0, 0}, new double[]{2, 2}));
    }

    @Test
    void retoExtra09_idMarcadorPorPatron() {
        assertEquals(5, Ej343ARMarkerMath.idMarcadorPorPatron(new int[]{1, 0, 1}));
        assertEquals(0, Ej343ARMarkerMath.idMarcadorPorPatron(new int[]{})); // caso límite
    }

    @Test
    void retoExtra10_anclarObjeto() {
        assertArrayEquals(new double[]{1, 2, 4},
                Ej343ARMarkerMath.anclarObjeto(new double[]{1, 2, 3}, new double[]{0, 0, 1}), 1e-9);
        assertNull(Ej343ARMarkerMath.anclarObjeto(new double[]{1, 2, 3}, new double[]{0, 0})); // caso límite
    }
}
