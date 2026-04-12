package bloque1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej05 - Copia Profunda de Arrays Bidimensionales")
class Ej05_CopiaDeepTest {

    private final int[][] original = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    @DisplayName("copiarMatriz: la copia tiene los mismos valores")
    void copiarMatriz_valores() {
        int[][] copia = Ej05_CopiaDeep.copiarMatriz(original);
        assertNotNull(copia);
        assertEquals(original.length, copia.length);
        for (int i = 0; i < original.length; i++) {
            assertArrayEquals(original[i], copia[i]);
        }
    }

    @Test
    @DisplayName("copiarMatriz: la copia es independiente del original")
    void copiarMatriz_independiente() {
        int[][] copia = Ej05_CopiaDeep.copiarMatriz(original);
        assertNotNull(copia);
        copia[0][0] = 999;
        assertEquals(1, original[0][0], "Modificar la copia NO debe afectar al original");
    }

    @Test
    @DisplayName("sonIguales: true si mismos valores")
    void sonIguales_true() {
        int[][] otra = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertTrue(Ej05_CopiaDeep.sonIguales(original, otra));
    }

    @Test
    @DisplayName("sonIguales: false si valores distintos")
    void sonIguales_false() {
        int[][] otra = {{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        assertFalse(Ej05_CopiaDeep.sonIguales(original, otra));
    }

    @Test
    @DisplayName("sonIguales: false si dimensiones distintas")
    void sonIguales_dimensiones() {
        int[][] otra = {{1, 2}, {3, 4}};
        assertFalse(Ej05_CopiaDeep.sonIguales(original, otra));
    }

    @Test
    @DisplayName("esIndependiente: true para copia profunda")
    void esIndependiente_true() {
        int[][] copia = Ej05_CopiaDeep.copiarMatriz(original);
        assertNotNull(copia);
        assertTrue(Ej05_CopiaDeep.esIndependiente(original, copia));
    }

    @Test
    @DisplayName("esIndependiente: false para copia por referencia")
    void esIndependiente_false() {
        int[][] referencia = original; // NO es copia profunda
        assertFalse(Ej05_CopiaDeep.esIndependiente(original, referencia));
    }

    @Test
    @DisplayName("copiarConBorde: dimensiones correctas")
    void copiarConBorde_dimensiones() {
        int[][] resultado = Ej05_CopiaDeep.copiarConBorde(original);
        assertNotNull(resultado);
        assertEquals(5, resultado.length);
        assertEquals(5, resultado[0].length);
    }

    @Test
    @DisplayName("copiarConBorde: bordes son 0 y centro es el original")
    void copiarConBorde_valores() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] resultado = Ej05_CopiaDeep.copiarConBorde(m);
        assertNotNull(resultado);
        // Bordes
        assertEquals(0, resultado[0][0]);
        assertEquals(0, resultado[0][1]);
        assertEquals(0, resultado[3][3]);
        // Centro
        assertEquals(1, resultado[1][1]);
        assertEquals(2, resultado[1][2]);
        assertEquals(3, resultado[2][1]);
        assertEquals(4, resultado[2][2]);
    }

    @Test
    @DisplayName("extraerSubmatriz: extrae la subregion correcta")
    void extraerSubmatriz_basico() {
        int[][] sub = Ej05_CopiaDeep.extraerSubmatriz(original, 0, 1, 1, 2);
        assertNotNull(sub);
        assertEquals(2, sub.length);
        assertEquals(2, sub[0].length);
        assertEquals(2, sub[0][0]);
        assertEquals(3, sub[0][1]);
        assertEquals(5, sub[1][0]);
        assertEquals(6, sub[1][1]);
    }

    @Test
    @DisplayName("extraerSubmatriz: recorta si indices exceden limites")
    void extraerSubmatriz_recorta() {
        int[][] sub = Ej05_CopiaDeep.extraerSubmatriz(original, 0, 100, 0, 100);
        assertNotNull(sub);
        assertEquals(3, sub.length);
        assertEquals(3, sub[0].length);
    }

    @Test
    @DisplayName("unirHorizontal: une dos matrices lado a lado")
    void unirHorizontal_basico() {
        int[][] izq = {{1, 2}, {3, 4}};
        int[][] der = {{5}, {6}};
        int[][] resultado = Ej05_CopiaDeep.unirHorizontal(izq, der);
        assertNotNull(resultado);
        assertEquals(2, resultado.length);
        assertEquals(3, resultado[0].length);
        assertArrayEquals(new int[]{1, 2, 5}, resultado[0]);
        assertArrayEquals(new int[]{3, 4, 6}, resultado[1]);
    }

    @Test
    @DisplayName("unirHorizontal: null si filas distintas")
    void unirHorizontal_filasDistintas() {
        int[][] izq = {{1, 2}};
        int[][] der = {{5}, {6}};
        assertNull(Ej05_CopiaDeep.unirHorizontal(izq, der));
    }

    @Test
    @DisplayName("unirVertical: une dos matrices una encima de otra")
    void unirVertical_basico() {
        int[][] arriba = {{1, 2, 3}};
        int[][] abajo = {{4, 5, 6}, {7, 8, 9}};
        int[][] resultado = Ej05_CopiaDeep.unirVertical(arriba, abajo);
        assertNotNull(resultado);
        assertEquals(3, resultado.length);
        assertArrayEquals(new int[]{1, 2, 3}, resultado[0]);
        assertArrayEquals(new int[]{4, 5, 6}, resultado[1]);
        assertArrayEquals(new int[]{7, 8, 9}, resultado[2]);
    }

    @Test
    @DisplayName("unirVertical: null si columnas distintas")
    void unirVertical_columnasDistintas() {
        int[][] arriba = {{1, 2}};
        int[][] abajo = {{4, 5, 6}};
        assertNull(Ej05_CopiaDeep.unirVertical(arriba, abajo));
    }
}
