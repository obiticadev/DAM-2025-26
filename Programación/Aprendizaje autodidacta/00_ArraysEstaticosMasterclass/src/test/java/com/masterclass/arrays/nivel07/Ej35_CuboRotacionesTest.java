package com.masterclass.arrays.nivel07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 07 - Ejercicio 35: Rotaciones Cubo")
class Ej35_CuboRotacionesTest {

    @Test
    void testEspejoCapas() {
        int[][][] c = {{{1}}, {{2}}, {{3}}};
        Ej35_CuboRotaciones.espejoCapas(c);
        assertThat(c[0][0][0]).isEqualTo(3);
        assertThat(c[2][0][0]).isEqualTo(1);
    }

    @Test
    void testSimetriaZ() {
        int[][][] c = {{{5}}, {{1}}, {{5}}};
        assertThat(Ej35_CuboRotaciones.esCuboSimetricoZ(c)).isTrue();
    }
}
