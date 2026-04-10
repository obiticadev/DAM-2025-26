package com.masterclass.arrays.nivel06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 06 - Ejercicio 29: Rotaciones 180/270")
class Ej29_Rotacion180Y270Test {

    @Test
    void testRotar180() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] r = Ej29_Rotacion180Y270.rotar180(m);
        assertThat(r[0]).containsExactly(4, 3);
        assertThat(r[1]).containsExactly(2, 1);
    }

    @Test
    void testRotar270() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] r = Ej29_Rotacion180Y270.rotar270(m);
        // equiv a 90 antihorario
        assertThat(r[0]).containsExactly(2, 4);
        assertThat(r[1]).containsExactly(1, 3);
    }
}
