package bloque2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej08 - Rotacion 90° Horario")
class Ej08_Rotar90Test {

    private final int[][] m3x3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    @DisplayName("rotar90Horario: 3x3 valores correctos")
    void rotar90_3x3() {
        int[][] r = Ej08_Rotar90.rotar90Horario(m3x3);
        assertNotNull(r);
        assertArrayEquals(new int[]{7, 4, 1}, r[0]);
        assertArrayEquals(new int[]{8, 5, 2}, r[1]);
        assertArrayEquals(new int[]{9, 6, 3}, r[2]);
    }

    @Test
    @DisplayName("rotar90Horario: 2x3 cambia dimensiones a 3x2")
    void rotar90_2x3() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] r = Ej08_Rotar90.rotar90Horario(m);
        assertNotNull(r);
        assertEquals(3, r.length);
        assertEquals(2, r[0].length);
        assertArrayEquals(new int[]{4, 1}, r[0]);
        assertArrayEquals(new int[]{5, 2}, r[1]);
        assertArrayEquals(new int[]{6, 3}, r[2]);
    }

    @Test
    @DisplayName("pintar: formato correcto")
    void pintar_formato() {
        int[][] m = {{1, 2}, {3, 4}};
        assertEquals("1 2\n3 4", Ej08_Rotar90.pintar(m));
    }

    @Test
    @DisplayName("rotarNVeces: 0 veces devuelve copia igual")
    void rotarNVeces_cero() {
        int[][] r = Ej08_Rotar90.rotarNVeces(m3x3, 0);
        assertNotNull(r);
        assertArrayEquals(new int[]{1, 2, 3}, r[0]);
    }

    @Test
    @DisplayName("rotarNVeces: 4 veces devuelve original")
    void rotarNVeces_cuatro() {
        int[][] r = Ej08_Rotar90.rotarNVeces(m3x3, 4);
        assertNotNull(r);
        assertArrayEquals(new int[]{1, 2, 3}, r[0]);
        assertArrayEquals(new int[]{4, 5, 6}, r[1]);
        assertArrayEquals(new int[]{7, 8, 9}, r[2]);
    }

    @Test
    @DisplayName("rotarNVeces: 5 veces = 1 vez")
    void rotarNVeces_cinco() {
        int[][] r5 = Ej08_Rotar90.rotarNVeces(m3x3, 5);
        int[][] r1 = Ej08_Rotar90.rotar90Horario(m3x3);
        assertNotNull(r5);
        assertNotNull(r1);
        for (int i = 0; i < r5.length; i++) {
            assertArrayEquals(r1[i], r5[i]);
        }
    }

    @Test
    @DisplayName("cuatroRotacionesEsOriginal: true")
    void cuatroRotaciones() {
        assertTrue(Ej08_Rotar90.cuatroRotacionesEsOriginal(m3x3));
    }

    @Test
    @DisplayName("primeraFilaRotada: primera columna de abajo a arriba")
    void primeraFilaRotada() {
        int[] pf = Ej08_Rotar90.primeraFilaRotada(m3x3);
        assertNotNull(pf);
        assertArrayEquals(new int[]{7, 4, 1}, pf);
    }

    @Test
    @DisplayName("ultimaFilaRotada: ultima columna de abajo a arriba")
    void ultimaFilaRotada() {
        int[] uf = Ej08_Rotar90.ultimaFilaRotada(m3x3);
        assertNotNull(uf);
        assertArrayEquals(new int[]{9, 6, 3}, uf);
    }

    @Test
    @DisplayName("posicionTraducida: [0][2] en 3x3 -> [2][2]")
    void posicionTraducida() {
        int[] pos = Ej08_Rotar90.posicionTraducida(3, 0, 2);
        assertNotNull(pos);
        assertArrayEquals(new int[]{2, 2}, pos);
    }

    @Test
    @DisplayName("posicionTraducida: [2][0] en 3x3 -> [0][0]")
    void posicionTraducida_esquina() {
        int[] pos = Ej08_Rotar90.posicionTraducida(3, 2, 0);
        assertNotNull(pos);
        assertArrayEquals(new int[]{0, 0}, pos);
    }
}
