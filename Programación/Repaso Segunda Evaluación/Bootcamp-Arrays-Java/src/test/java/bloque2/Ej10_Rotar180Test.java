package bloque2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej10 - Rotacion 180°")
class Ej10_Rotar180Test {

    private final int[][] m3x3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    @DisplayName("rotar180: 3x3 valores correctos")
    void rotar180_3x3() {
        int[][] r = Ej10_Rotar180.rotar180(m3x3);
        assertNotNull(r);
        assertArrayEquals(new int[]{9, 8, 7}, r[0]);
        assertArrayEquals(new int[]{6, 5, 4}, r[1]);
        assertArrayEquals(new int[]{3, 2, 1}, r[2]);
    }

    @Test
    @DisplayName("rotar180: dimensiones se mantienen para 2x4")
    void rotar180_2x4() {
        int[][] m = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        int[][] r = Ej10_Rotar180.rotar180(m);
        assertNotNull(r);
        assertEquals(2, r.length);
        assertEquals(4, r[0].length);
        assertArrayEquals(new int[]{8, 7, 6, 5}, r[0]);
        assertArrayEquals(new int[]{4, 3, 2, 1}, r[1]);
    }

    @Test
    @DisplayName("pintar: formato correcto")
    void pintar() {
        int[][] m = {{1, 2}, {3, 4}};
        assertEquals("1 2\n3 4", Ej10_Rotar180.pintar(m));
    }

    @Test
    @DisplayName("equivaleADosRotaciones90: true")
    void equivale2x90() {
        assertTrue(Ej10_Rotar180.equivaleADosRotaciones90(m3x3));
    }

    @Test
    @DisplayName("equivaleADosRotaciones90: true para no cuadrada")
    void equivale2x90_noCuadrada() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertTrue(Ej10_Rotar180.equivaleADosRotaciones90(m));
    }

    @Test
    @DisplayName("dobleRotacion180EsOriginal: true")
    void doble180() {
        assertTrue(Ej10_Rotar180.dobleRotacion180EsOriginal(m3x3));
    }

    @Test
    @DisplayName("equivaleADobleEspejo: true")
    void dobleEspejo() {
        assertTrue(Ej10_Rotar180.equivaleADobleEspejo(m3x3));
    }

    @Test
    @DisplayName("posicionTraducida: [0][0] en 3x3 -> [2][2]")
    void posicionTraducida_esquina() {
        int[] pos = Ej10_Rotar180.posicionTraducida(3, 3, 0, 0);
        assertNotNull(pos);
        assertArrayEquals(new int[]{2, 2}, pos);
    }

    @Test
    @DisplayName("posicionTraducida: [1][1] en 3x3 -> [1][1] (centro)")
    void posicionTraducida_centro() {
        int[] pos = Ej10_Rotar180.posicionTraducida(3, 3, 1, 1);
        assertNotNull(pos);
        assertArrayEquals(new int[]{1, 1}, pos);
    }

    @Test
    @DisplayName("esPalindromo2D: true para palindromo")
    void palindromo_true() {
        int[][] p = {{1, 2, 1}, {3, 5, 3}, {1, 2, 1}};
        assertTrue(Ej10_Rotar180.esPalindromo2D(p));
    }

    @Test
    @DisplayName("esPalindromo2D: false para no palindromo")
    void palindromo_false() {
        assertFalse(Ej10_Rotar180.esPalindromo2D(m3x3));
    }
}
