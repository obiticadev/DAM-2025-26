package com.masterclass.arrays.nivel06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 06 - Ejercicio 27: Transposición")
class Ej27_TransposicionTest {

    @Test
    void testTrasponerCuadrada() {
        int[][] m = {{1, 2}, {3, 4}};
        Ej27_Transposicion.trasponerCuadradaInPlace(m);
        assertThat(m[0]).containsExactly(1, 3);
        assertThat(m[1]).containsExactly(2, 4);
    }

    @Test
    void testTrasponerRectangular() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] t = Ej27_Transposicion.trasponerRectangular(m);
        assertThat(t.length).isEqualTo(3);
        assertThat(t[0].length).isEqualTo(2);
        assertThat(t[0]).containsExactly(1, 4);
    }
}
