package com.masterclass.arrays.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 05 - Ejercicio 22: Analítica de Matrices")
class Ej22_SumaFilasColumnasMediasTest {

    @Test
    void testSumas() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        assertThat(Ej22_SumaFilasColumnasMedias.sumaFila(m, 0)).isEqualTo(6);
        assertThat(Ej22_SumaFilasColumnasMedias.sumaColumna(m, 2)).isEqualTo(9);
    }

    @Test
    void testVectoresDeSumas() {
        int[][] m = {{10, 20}, {30, 40}, {50, 60}};
        assertThat(Ej22_SumaFilasColumnasMedias.vectorSumasFilas(m)).containsExactly(30, 70, 110);
        assertThat(Ej22_SumaFilasColumnasMedias.vectorSumasColumnas(m)).containsExactly(90, 120);
    }

    @Test
    void testMedia() {
        int[][] m = {{1, 2}, {3, 4}};
        assertThat(Ej22_SumaFilasColumnasMedias.mediaMatriz(m)).isEqualTo(2.5, offset(0.01));
    }

    @Test
    void testExtremos() {
        int[][] m = {
            {1, 1, 1}, // 3
            {10, 10, 10}, // 30 (max fila)
            {5, 5, 5}  // 15
        };
        assertThat(Ej22_SumaFilasColumnasMedias.filaDeMayorSuma(m)).isEqualTo(1);
    }
}
