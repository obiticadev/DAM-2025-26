package com.masterclass.arrays.nivel08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 08 - Ejercicio 37: Map Paralelo")
class Ej37_MapConArraysParalelosTest {

    @Test
    void testBasicOps() {
        Ej37_MapConArraysParalelos map = new Ej37_MapConArraysParalelos(10);
        map.put("Java", 100);
        map.put("Python", 80);
        
        assertThat(map.get("Java")).isEqualTo(100);
        assertThat(map.containsKey("Python")).isTrue();
        assertThat(map.size()).isEqualTo(2);
        
        map.put("Java", 110);
        assertThat(map.get("Java")).isEqualTo(110);
    }

    @Test
    void testRemove() {
        Ej37_MapConArraysParalelos map = new Ej37_MapConArraysParalelos(5);
        map.put("A", 1);
        map.put("B", 2);
        map.remove("A");
        assertThat(map.containsKey("A")).isFalse();
        assertThat(map.size()).isEqualTo(1);
    }
}
