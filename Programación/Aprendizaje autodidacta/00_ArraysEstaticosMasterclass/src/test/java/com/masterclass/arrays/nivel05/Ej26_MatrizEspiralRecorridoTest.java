package com.masterclass.arrays.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 05 - Ejercicio 26: Recorrido Espiral")
class Ej26_MatrizEspiralRecorridoTest {

    @Test
    void testBordes() {
        int[][] m = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        assertThat(Ej26_MatrizEspiralRecorrido.recorridoBordes(m)).containsExactly(1, 2, 3, 6, 9, 8, 7, 4);
    }

    @Test
    void testEspiral() {
        int[][] m = {
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}
        };
        assertThat(Ej26_MatrizEspiralRecorrido.recorridoEspiral(m)).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    void testZigZag() {
        int[][] m = {
            {1, 2},
            {3, 4}
        };
        // Fila 0: 1, 2 -> Fila 1: 4, 3
        assertThat(Ej26_MatrizEspiralRecorrido.recorridoZigZag(m)).containsExactly(1, 2, 4, 3);
    }
}
