package com.masterclass.arrays.nivel07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 07 - Ejercicio 34: Operaciones Capas")
class Ej34_OperacionesPorCapasTest {

    @Test
    void testExtraerPlano() {
        int[][][] c = {{{1, 2}, {3, 4}}, {{5, 6}, {7, 8}}};
        int[][] p = Ej34_OperacionesPorCapas.extraerPlano(c, 1);
        assertThat(p[0]).containsExactly(5, 6);
    }

    @Test
    void testSumaPlanos() {
        int[][][] c = {{{1, 1}, {1, 1}}, {{2, 2}, {2, 2}}};
        int[][] res = Ej34_OperacionesPorCapas.sumaDeTodosLosPlanos(c);
        assertThat(res).isNotNull();
        assertThat(res[0]).containsExactly(3, 3);
    }

    @Test
    void testAplastar() {
        int[][][] c = {{{1}, {2}}, {{3}, {4}}};
        assertThat(Ej34_OperacionesPorCapas.aplastarCubo(c)).containsExactly(1, 2, 3, 4);
    }
}
