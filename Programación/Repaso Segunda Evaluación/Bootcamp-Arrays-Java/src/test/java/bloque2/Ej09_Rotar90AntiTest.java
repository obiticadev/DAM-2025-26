package bloque2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej09 - Rotacion 90° Antihorario")
class Ej09_Rotar90AntiTest {

    private final int[][] m3x3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    @DisplayName("rotar90Antihorario: 3x3 valores correctos")
    void rotar90Anti_3x3() {
        int[][] r = Ej09_Rotar90Anti.rotar90Antihorario(m3x3);
        assertNotNull(r);
        assertArrayEquals(new int[]{3, 6, 9}, r[0]);
        assertArrayEquals(new int[]{2, 5, 8}, r[1]);
        assertArrayEquals(new int[]{1, 4, 7}, r[2]);
    }

    @Test
    @DisplayName("rotar90Antihorario: 2x3 cambia dimensiones a 3x2")
    void rotar90Anti_2x3() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] r = Ej09_Rotar90Anti.rotar90Antihorario(m);
        assertNotNull(r);
        assertEquals(3, r.length);
        assertEquals(2, r[0].length);
        assertArrayEquals(new int[]{3, 6}, r[0]);
        assertArrayEquals(new int[]{2, 5}, r[1]);
        assertArrayEquals(new int[]{1, 4}, r[2]);
    }

    @Test
    @DisplayName("horarioAntihorarioEsOriginal: true")
    void horarioAntihorario() {
        assertTrue(Ej09_Rotar90Anti.horarioAntihorarioEsOriginal(m3x3));
    }

    @Test
    @DisplayName("horarioAntihorarioEsOriginal: true para no cuadrada")
    void horarioAntihorario_noCuadrada() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertTrue(Ej09_Rotar90Anti.horarioAntihorarioEsOriginal(m));
    }

    @Test
    @DisplayName("antihorarioEsTresHorario: true")
    void antihorarioEsTres() {
        assertTrue(Ej09_Rotar90Anti.antihorarioEsTresHorario(m3x3));
    }

    @Test
    @DisplayName("rotarAntihorarioNVeces: 0 devuelve copia")
    void rotarNVeces_cero() {
        int[][] r = Ej09_Rotar90Anti.rotarAntihorarioNVeces(m3x3, 0);
        assertNotNull(r);
        assertArrayEquals(new int[]{1, 2, 3}, r[0]);
    }

    @Test
    @DisplayName("rotarAntihorarioNVeces: 4 devuelve original")
    void rotarNVeces_cuatro() {
        int[][] r = Ej09_Rotar90Anti.rotarAntihorarioNVeces(m3x3, 4);
        assertNotNull(r);
        assertArrayEquals(new int[]{1, 2, 3}, r[0]);
        assertArrayEquals(new int[]{4, 5, 6}, r[1]);
        assertArrayEquals(new int[]{7, 8, 9}, r[2]);
    }

    @Test
    @DisplayName("posicionTraducida: [0][2] en 3col -> [0][0]")
    void posicionTraducida() {
        int[] pos = Ej09_Rotar90Anti.posicionTraducida(3, 0, 2);
        assertNotNull(pos);
        assertArrayEquals(new int[]{0, 0}, pos);
    }

    @Test
    @DisplayName("primeraColumnaRotada: primera fila invertida")
    void primeraColumnaRotada() {
        int[] pc = Ej09_Rotar90Anti.primeraColumnaRotada(m3x3);
        assertNotNull(pc);
        assertArrayEquals(new int[]{3, 2, 1}, pc);
    }

    @Test
    @DisplayName("pintar: formato correcto")
    void pintar() {
        assertEquals("1 2 3\n4 5 6\n7 8 9", Ej09_Rotar90Anti.pintar(m3x3));
    }
}
