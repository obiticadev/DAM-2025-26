package bloque1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej02 - Recorridos en Distintas Direcciones")
class Ej02_RecorridosTest {

    private final int[][] m3x3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private final int[][] m2x3 = {{1, 2, 3}, {4, 5, 6}};

    @Test
    @DisplayName("recorridoPorFilas: orden correcto fila a fila")
    void recorridoPorFilas_3x3() {
        int[] resultado = Ej02_Recorridos.recorridoPorFilas(m3x3);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, resultado);
    }

    @Test
    @DisplayName("recorridoPorFilas: matriz no cuadrada")
    void recorridoPorFilas_2x3() {
        int[] resultado = Ej02_Recorridos.recorridoPorFilas(m2x3);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, resultado);
    }

    @Test
    @DisplayName("recorridoPorColumnas: orden correcto columna a columna")
    void recorridoPorColumnas_3x3() {
        int[] resultado = Ej02_Recorridos.recorridoPorColumnas(m3x3);
        assertArrayEquals(new int[]{1, 4, 7, 2, 5, 8, 3, 6, 9}, resultado);
    }

    @Test
    @DisplayName("recorridoPorColumnas: matriz no cuadrada")
    void recorridoPorColumnas_2x3() {
        int[] resultado = Ej02_Recorridos.recorridoPorColumnas(m2x3);
        assertArrayEquals(new int[]{1, 4, 2, 5, 3, 6}, resultado);
    }

    @Test
    @DisplayName("diagonalPrincipal: elementos donde i == j")
    void diagonalPrincipal_3x3() {
        int[] resultado = Ej02_Recorridos.diagonalPrincipal(m3x3);
        assertArrayEquals(new int[]{1, 5, 9}, resultado);
    }

    @Test
    @DisplayName("diagonalInversa: elementos anti-diagonal")
    void diagonalInversa_3x3() {
        int[] resultado = Ej02_Recorridos.diagonalInversa(m3x3);
        assertArrayEquals(new int[]{3, 5, 7}, resultado);
    }

    @Test
    @DisplayName("recorridoBorde: sentido horario")
    void recorridoBorde_3x3() {
        int[] resultado = Ej02_Recorridos.recorridoBorde(m3x3);
        assertArrayEquals(new int[]{1, 2, 3, 6, 9, 8, 7, 4}, resultado);
    }

    @Test
    @DisplayName("recorridoBorde: matriz 2x2")
    void recorridoBorde_2x2() {
        int[][] m = {{1, 2}, {3, 4}};
        int[] resultado = Ej02_Recorridos.recorridoBorde(m);
        assertArrayEquals(new int[]{1, 2, 4, 3}, resultado);
    }

    @Test
    @DisplayName("recorridoZigzag: filas pares izq->der, impares der->izq")
    void recorridoZigzag_3x3() {
        int[] resultado = Ej02_Recorridos.recorridoZigzag(m3x3);
        assertArrayEquals(new int[]{1, 2, 3, 6, 5, 4, 7, 8, 9}, resultado);
    }

    @Test
    @DisplayName("esCuadrada: true para 3x3")
    void esCuadrada_true() {
        assertTrue(Ej02_Recorridos.esCuadrada(m3x3));
    }

    @Test
    @DisplayName("esCuadrada: false para 2x3")
    void esCuadrada_false() {
        assertFalse(Ej02_Recorridos.esCuadrada(m2x3));
    }
}
