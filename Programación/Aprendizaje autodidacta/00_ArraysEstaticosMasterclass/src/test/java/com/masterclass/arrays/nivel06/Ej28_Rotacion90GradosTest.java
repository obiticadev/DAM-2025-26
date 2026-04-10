package com.masterclass.arrays.nivel06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 06 - Ejercicio 28: Rotación 90")
class Ej28_Rotacion90GradosTest {

    @Test
    void testRotar90Horario() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] r = Ej28_Rotacion90Grados.rotar90Horario(m);
        assertThat(r[0]).containsExactly(3, 1);
        assertThat(r[1]).containsExactly(4, 2);
    }

    @Test
    void testRotar90Antihorario() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] r = Ej28_Rotacion90Grados.rotar90Antihorario(m);
        assertThat(r[0]).containsExactly(2, 4);
        assertThat(r[1]).containsExactly(1, 3);
    }
}
