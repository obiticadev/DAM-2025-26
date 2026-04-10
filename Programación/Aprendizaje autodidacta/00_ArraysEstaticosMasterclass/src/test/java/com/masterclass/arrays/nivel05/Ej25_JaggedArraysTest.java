package com.masterclass.arrays.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 05 - Ejercicio 25: Jagged Arrays")
class Ej25_JaggedArraysTest {

    @Test
    void testEscalera() {
        int[][] esc = Ej25_JaggedArrays.crearEscalera(3);
        assertThat(esc[0]).hasSize(1);
        assertThat(esc[1]).hasSize(2);
        assertThat(esc[2]).hasSize(3);
    }

    @Test
    void testPascal() {
        int[][] p = Ej25_JaggedArrays.generarPascal(4);
        assertThat(p[0]).containsExactly(1);
        assertThat(p[1]).containsExactly(1, 1);
        assertThat(p[2]).containsExactly(1, 2, 1);
        assertThat(p[3]).containsExactly(1, 3, 3, 1);
    }

    @Test
    void testAplanar() {
        int[][] j = {{1}, {2, 3}};
        assertThat(Ej25_JaggedArrays.aplanarJagged(j)).containsExactly(1, 2, 3);
    }
}
