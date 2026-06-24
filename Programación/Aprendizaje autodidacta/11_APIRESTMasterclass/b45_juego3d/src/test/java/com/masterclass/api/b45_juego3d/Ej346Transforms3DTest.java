package com.masterclass.api.b45_juego3d;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej346Transforms3DTest {

    /** Identidad 4×4 literal, para no depender del reto matrizIdentidad() en los tests de core. */
    private static double[][] id() {
        return new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
    }

    /** Traslación (dx,dy,dz) literal. */
    private static double[][] tras(double dx, double dy, double dz) {
        return new double[][]{{1, 0, 0, dx}, {0, 1, 0, dy}, {0, 0, 1, dz}, {0, 0, 0, 1}};
    }

    private static void assertMatriz(double[][] esperada, double[][] real) {
        assertNotNull(real);
        for (int i = 0; i < 4; i++) {
            assertArrayEquals(esperada[i], real[i], 1e-9, "fila " + i);
        }
    }

    @Test
    void componer() {
        // identidad · traslación = traslación.
        assertMatriz(tras(1, 2, 3), Ej346Transforms3D.componer(List.of(id(), tras(1, 2, 3))));
        assertNull(Ej346Transforms3D.componer(List.of())); // caso límite: lista vacía
    }

    @Test
    void aplicar() {
        assertArrayEquals(new double[]{1, 2, 3}, Ej346Transforms3D.aplicar(id(), new double[]{1, 2, 3}), 1e-9);
        assertArrayEquals(new double[]{1, 1, 1}, Ej346Transforms3D.aplicar(tras(1, 1, 1), new double[]{0, 0, 0}), 1e-9);
        assertNull(Ej346Transforms3D.aplicar(id(), new double[]{1, 2})); // caso límite
    }

    @Test
    void retoExtra01_matrizIdentidad() {
        double[][] m = Ej346Transforms3D.matrizIdentidad();
        assertNotNull(m);
        assertEquals(1.0, m[0][0], 1e-9);
        assertEquals(1.0, m[3][3], 1e-9);
        assertEquals(0.0, m[0][1], 1e-9); // fuera de la diagonal
    }

    @Test
    void retoExtra02_matrizTraslacion() {
        double[][] m = Ej346Transforms3D.matrizTraslacion(1, 2, 3);
        assertNotNull(m);
        assertEquals(1.0, m[0][3], 1e-9);
        assertEquals(2.0, m[1][3], 1e-9);
        assertEquals(3.0, m[2][3], 1e-9);
        assertEquals(1.0, m[0][0], 1e-9); // la diagonal sigue a 1
    }

    @Test
    void retoExtra03_matrizEscala() {
        double[][] m = Ej346Transforms3D.matrizEscala(2, 3, 4);
        assertNotNull(m);
        assertEquals(2.0, m[0][0], 1e-9);
        assertEquals(3.0, m[1][1], 1e-9);
        assertEquals(4.0, m[2][2], 1e-9);
        assertEquals(1.0, m[3][3], 1e-9);
    }

    @Test
    void retoExtra04_matrizRotacionX() {
        double[][] m = Ej346Transforms3D.matrizRotacionX(90);
        assertNotNull(m);
        assertEquals(1.0, m[2][1], 1e-9);  // sin(90)
        assertEquals(-1.0, m[1][2], 1e-9); // -sin(90)
    }

    @Test
    void retoExtra05_matrizRotacionY() {
        double[][] m = Ej346Transforms3D.matrizRotacionY(90);
        assertNotNull(m);
        assertEquals(1.0, m[0][2], 1e-9);  // sin(90)
        assertEquals(-1.0, m[2][0], 1e-9); // -sin(90)
    }

    @Test
    void retoExtra06_matrizRotacionZ() {
        double[][] m = Ej346Transforms3D.matrizRotacionZ(90);
        assertNotNull(m);
        assertEquals(1.0, m[1][0], 1e-9);  // sin(90)
        assertEquals(-1.0, m[0][1], 1e-9); // -sin(90)
    }

    @Test
    void retoExtra07_multiplicar() {
        double[][] a = tras(1, 2, 3);
        assertMatriz(a, Ej346Transforms3D.multiplicar(id(), a)); // identidad · A = A
        assertNull(Ej346Transforms3D.multiplicar(new double[3][3], a)); // caso límite: no es 4×4
    }

    @Test
    void retoExtra08_esIdentidad() {
        assertTrue(Ej346Transforms3D.esIdentidad(id()));
        assertFalse(Ej346Transforms3D.esIdentidad(tras(1, 0, 0))); // caso límite
    }

    @Test
    void retoExtra09_transpuesta() {
        double[][] t = Ej346Transforms3D.transpuesta(tras(1, 2, 3));
        assertNotNull(t);
        assertEquals(1.0, t[3][0], 1e-9); // la traslación pasa a la última fila
        assertEquals(2.0, t[3][1], 1e-9);
        assertEquals(3.0, t[3][2], 1e-9);
    }

    @Test
    void retoExtra10_componerTRS() {
        // T·I·S = T·S : la diagonal lleva la escala y la 4.ª columna la traslación.
        double[][] m = Ej346Transforms3D.componerTRS(tras(1, 0, 0), id(), Ej346EscalaLiteral(2, 2, 2));
        assertNotNull(m);
        assertEquals(2.0, m[0][0], 1e-9); // escala
        assertEquals(1.0, m[0][3], 1e-9); // traslación
    }

    private static double[][] Ej346EscalaLiteral(double sx, double sy, double sz) {
        return new double[][]{{sx, 0, 0, 0}, {0, sy, 0, 0}, {0, 0, sz, 0}, {0, 0, 0, 1}};
    }
}
