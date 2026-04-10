package com.masterclass.arrays.nivel09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 09 - Ejercicio 47: Jump Search")
class Ej47_JumpSearchTest {

    @Test
    void testJumpSearch() {
        int[] m = {1, 3, 5, 8, 12, 15, 18, 20};
        assertThat(Ej47_JumpSearch.jumpSearch(m, 12)).isEqualTo(4);
        assertThat(Ej47_JumpSearch.jumpSearch(m, 100)).isEqualTo(-1);
    }
}
