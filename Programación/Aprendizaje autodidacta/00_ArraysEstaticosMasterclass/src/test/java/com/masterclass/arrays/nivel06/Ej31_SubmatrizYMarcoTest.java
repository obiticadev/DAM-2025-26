package com.masterclass.arrays.nivel06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 06 - Ejercicio 31: Submatriz")
class Ej31_SubmatrizYMarcoTest {

    @Test
    void testExtraerSubmatriz() {
        int[][] m = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] sub = Ej31_SubmatrizYMarco.extraerSubmatriz(m, 1, 1, 2, 2);
        assertThat(sub.length).isEqualTo(2);
        assertThat(sub[0].length).isEqualTo(2);
        assertThat(sub[0]).containsExactly(5, 6);
        assertThat(sub[1]).containsExactly(8, 9);
    }

    @Test
    void testMarco() {
        int[][] m = {
            {1, 2},
            {3, 4}
        };
        assertThat(Ej31_SubmatrizYMarco.extraerMarco(m)).containsExactly(1, 2, 4, 3);
    }
}
