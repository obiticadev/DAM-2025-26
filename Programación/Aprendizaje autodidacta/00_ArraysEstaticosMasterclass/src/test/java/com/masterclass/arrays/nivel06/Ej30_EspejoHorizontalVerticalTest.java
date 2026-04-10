package com.masterclass.arrays.nivel06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 06 - Ejercicio 30: Espejos")
class Ej30_EspejoHorizontalVerticalTest {

    @Test
    void testEspejoHorizontal() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] r = Ej30_EspejoHorizontalVertical.espejoHorizontal(m);
        assertThat(r[0]).containsExactly(3, 4);
        assertThat(r[1]).containsExactly(1, 2);
    }

    @Test
    void testEspejoVertical() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] r = Ej30_EspejoHorizontalVertical.espejoVertical(m);
        assertThat(r[0]).containsExactly(2, 1);
        assertThat(r[1]).containsExactly(4, 3);
    }
}
