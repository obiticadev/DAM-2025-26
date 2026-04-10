package com.masterclass.arrays.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 05 - Ejercicio 23: Diagonales")
class Ej23_DiagonalesPrincipalSecundariaTest {

    @Test
    void testDiagonalPrincipal() {
        int[][] m = {{1, 2}, {3, 4}};
        assertThat(Ej23_DiagonalesPrincipalSecundaria.diagonalPrincipal(m)).containsExactly(1, 4);
        
        int[][] nonSquare = new int[2][3];
        assertThatThrownBy(() -> Ej23_DiagonalesPrincipalSecundaria.diagonalPrincipal(nonSquare))
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testDiagonalSecundaria() {
        int[][] m = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        assertThat(Ej23_DiagonalesPrincipalSecundaria.diagonalSecundaria(m)).containsExactly(3, 5, 7);
    }

    @Test
    void testTriangular() {
        int[][] m = {
            {1, 5, 9},
            {0, 2, 8},
            {0, 0, 3}
        };
        assertThat(Ej23_DiagonalesPrincipalSecundaria.esMatrizTriangularSuperior(m)).isTrue();
    }
}
