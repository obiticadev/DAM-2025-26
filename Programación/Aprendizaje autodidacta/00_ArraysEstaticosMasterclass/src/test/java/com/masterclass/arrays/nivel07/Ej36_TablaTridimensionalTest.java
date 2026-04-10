package com.masterclass.arrays.nivel07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 07 - Ejercicio 36: Temperaturas")
class Ej36_TablaTridimensionalTest {

    @Test
    void testMedia() {
        int[][][] t = new int[12][31][24];
        t[0][0][0] = 10;
        t[0][0][1] = 20;
        // El resto 0. Media de mes 0 será (30 / (31*24))
        assertThat(Ej36_TablaTridimensional.mediaMensual(t, 0)).isLessThan(1.0);
    }

    @Test
    void testMaximo() {
        int[][][] t = new int[12][31][24];
        t[5][10][12] = 45; // Max
        int[] res = Ej36_TablaTridimensional.temperaturaMaxAnual(t);
        assertThat(res).containsExactly(5, 10, 12, 45);
    }
}
