package com.masterclass.arrays.nivel06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 06 - Ejercicio 32: Suma y Multiplicación")
class Ej32_SumaMultiplicacionMatricesTest {

    @Test
    void testSumar() {
        int[][] a = {{1, 1}, {1, 1}};
        int[][] b = {{2, 2}, {2, 2}};
        assertThat(Ej32_SumaMultiplicacionMatrices.sumar(a, b)[0]).containsExactly(3, 3);
    }

    @Test
    void testMultiplicar() {
        int[][] a = {{1, 2}, {3, 4}}; // 2x2
        int[][] b = {{5, 6}, {7, 8}}; // 2x2
        int[][] c = Ej32_SumaMultiplicacionMatrices.multiplicarMatrices(a, b);
        // [1*5 + 2*7, 1*6 + 2*8] = [19, 22]
        // [3*5 + 4*7, 3*6 + 4*8] = [43, 50]
        assertThat(c[0]).containsExactly(19, 22);
        assertThat(c[1]).containsExactly(43, 50);
    }
}
