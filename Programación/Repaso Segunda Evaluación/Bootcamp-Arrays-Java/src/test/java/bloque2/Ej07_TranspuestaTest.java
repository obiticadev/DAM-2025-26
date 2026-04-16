package bloque2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej07 - Transpuesta de una Matriz")
class Ej07_TranspuestaTest {

    @Test
    @DisplayName("transponer: 2x3 se convierte en 3x2")
    void transponer_dimensiones() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] t = Ej07_Transpuesta.transponer(m);
        assertNotNull(t);
        assertEquals(3, t.length);
        assertEquals(2, t[0].length);
    }

    @Test
    @DisplayName("transponer: valores correctos")
    void transponer_valores() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] t = Ej07_Transpuesta.transponer(m);
        assertNotNull(t);
        assertArrayEquals(new int[]{1, 4}, t[0]);
        assertArrayEquals(new int[]{2, 5}, t[1]);
        assertArrayEquals(new int[]{3, 6}, t[2]);
    }

    @Test
    @DisplayName("transponer: matriz cuadrada")
    void transponer_cuadrada() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] t = Ej07_Transpuesta.transponer(m);
        assertNotNull(t);
        assertArrayEquals(new int[]{1, 3}, t[0]);
        assertArrayEquals(new int[]{2, 4}, t[1]);
    }

    @Test
    @DisplayName("esSimetrica: true para matriz simetrica")
    void esSimetrica_true() {
        int[][] m = {{1, 2, 3}, {2, 5, 6}, {3, 6, 9}};
        assertTrue(Ej07_Transpuesta.esSimetrica(m));
    }

    @Test
    @DisplayName("esSimetrica: false para no simetrica")
    void esSimetrica_false() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertFalse(Ej07_Transpuesta.esSimetrica(m));
    }

    @Test
    @DisplayName("esSimetrica: false para no cuadrada")
    void esSimetrica_noCuadrada() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertFalse(Ej07_Transpuesta.esSimetrica(m));
    }

    @Test
    @DisplayName("dobleTransposicionEsOriginal: siempre true")
    void dobleTransposicion() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertTrue(Ej07_Transpuesta.dobleTransposicionEsOriginal(m));
    }

    @Test
    @DisplayName("pintar: formato correcto")
    void pintar_formato() {
        int[][] m = {{1, 2}, {3, 4}};
        assertEquals("1 2\n3 4", Ej07_Transpuesta.pintar(m));
    }

    @Test
    @DisplayName("diagonalTranspuesta: valores correctos")
    void diagonalTranspuesta_basico() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[] d = Ej07_Transpuesta.diagonalTranspuesta(m);
        assertNotNull(d);
        // Transpuesta es 3x2, diagonal tiene min(3,2)=2 elementos: [0][0]=1, [1][1]=5
        assertEquals(2, d.length);
        assertEquals(1, d[0]);
        assertEquals(5, d[1]);
    }

    @Test
    @DisplayName("sumaConservada: la transpuesta conserva la suma total")
    void sumaConservada_true() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertTrue(Ej07_Transpuesta.sumaConservada(m));
    }

    @Test
    @DisplayName("transponerConSumaFila: valores correctos")
    void transponerConSumaFila_basico() {
        int[][] m = {{1, 2}, {3, 4}}; // sumaFila0=3, sumaFila1=7
        int[][] r = Ej07_Transpuesta.transponerConSumaFila(m);
        assertNotNull(r);
        assertEquals(2, r.length);
        assertEquals(2, r[0].length);
        // r[j][i] = sumaFila[i] => r[0][0]=3, r[0][1]=7, r[1][0]=3, r[1][1]=7
        assertEquals(3, r[0][0]);
        assertEquals(7, r[0][1]);
        assertEquals(3, r[1][0]);
        assertEquals(7, r[1][1]);
    }
}
