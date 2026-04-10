package com.masterclass.arrays.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 05 - Ejercicio 24: Identidad y Simetría")
class Ej24_MatrizIdentidadYSimetricaTest {

    @Test
    void testIdentidad() {
        int[][] id = Ej24_MatrizIdentidadYSimetrica.generarIdentidad(3);
        assertThat(id[0]).containsExactly(1, 0, 0);
        assertThat(id[1]).containsExactly(0, 1, 0);
        assertThat(id[2]).containsExactly(0, 0, 1);
        assertThat(Ej24_MatrizIdentidadYSimetrica.esIdentidad(id)).isTrue();
    }

    @Test
    void testSimetria() {
        int[][] sim = {
            {1, 2, 3},
            {2, 5, 6},
            {3, 6, 9}
        };
        assertThat(Ej24_MatrizIdentidadYSimetrica.esSimetrica(sim)).isTrue();
    }

    @Test
    void testTraspuesta() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] t = Ej24_MatrizIdentidadYSimetrica.traspuesta(m);
        assertThat(t.length).isEqualTo(3);
        assertThat(t[0].length).isEqualTo(2);
        assertThat(t[0]).containsExactly(1, 4);
        assertThat(t[2]).containsExactly(3, 6);
    }
}
