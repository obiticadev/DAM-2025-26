package bloque2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej11 - Espejo Horizontal y Vertical")
class Ej11_EspejoTest {

    private final int[][] m3x3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    @DisplayName("espejoHorizontal: invierte filas")
    void espejoH_3x3() {
        int[][] r = Ej11_Espejo.espejoHorizontal(m3x3);
        assertNotNull(r);
        assertArrayEquals(new int[]{7, 8, 9}, r[0]);
        assertArrayEquals(new int[]{4, 5, 6}, r[1]);
        assertArrayEquals(new int[]{1, 2, 3}, r[2]);
    }

    @Test
    @DisplayName("espejoVertical: invierte columnas")
    void espejoV_3x3() {
        int[][] r = Ej11_Espejo.espejoVertical(m3x3);
        assertNotNull(r);
        assertArrayEquals(new int[]{3, 2, 1}, r[0]);
        assertArrayEquals(new int[]{6, 5, 4}, r[1]);
        assertArrayEquals(new int[]{9, 8, 7}, r[2]);
    }

    @Test
    @DisplayName("espejoHorizontal: dimensiones no cambian")
    void espejoH_dimensiones() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] r = Ej11_Espejo.espejoHorizontal(m);
        assertNotNull(r);
        assertEquals(2, r.length);
        assertEquals(3, r[0].length);
    }

    @Test
    @DisplayName("pintar: formato correcto")
    void pintar() {
        int[][] m = {{1, 2}, {3, 4}};
        assertEquals("1 2\n3 4", Ej11_Espejo.pintar(m));
    }

    @Test
    @DisplayName("dobleEspejoHEsOriginal: true")
    void dobleEspejoH() {
        assertTrue(Ej11_Espejo.dobleEspejoHEsOriginal(m3x3));
    }

    @Test
    @DisplayName("dobleEspejoVEsOriginal: true")
    void dobleEspejoV() {
        assertTrue(Ej11_Espejo.dobleEspejoVEsOriginal(m3x3));
    }

    @Test
    @DisplayName("espejoHVEquivaleA180: true")
    void hvEquivale180() {
        assertTrue(Ej11_Espejo.espejoHVEquivaleA180(m3x3));
    }

    @Test
    @DisplayName("espejoHVEquivaleA180: true para no cuadrada")
    void hvEquivale180_noCuadrada() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertTrue(Ej11_Espejo.espejoHVEquivaleA180(m));
    }

    @Test
    @DisplayName("tieneSimetriaHorizontal: true")
    void simetriaH_true() {
        int[][] sim = {{1, 2, 3}, {4, 5, 6}, {1, 2, 3}};
        assertTrue(Ej11_Espejo.tieneSimetriaHorizontal(sim));
    }

    @Test
    @DisplayName("tieneSimetriaHorizontal: false")
    void simetriaH_false() {
        assertFalse(Ej11_Espejo.tieneSimetriaHorizontal(m3x3));
    }

    @Test
    @DisplayName("tieneSimetriaHorizontal: fila central no afecta (impar)")
    void simetriaH_filaCentral() {
        int[][] sim = {{1, 2}, {99, 99}, {1, 2}};
        assertTrue(Ej11_Espejo.tieneSimetriaHorizontal(sim));
    }
}
