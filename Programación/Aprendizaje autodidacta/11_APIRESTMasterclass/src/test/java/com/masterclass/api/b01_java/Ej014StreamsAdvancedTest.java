package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej014StreamsAdvancedTest {

    @Test
    void aplanar() {
        assertEquals(List.of(1, 2, 3, 4),
                Ej014StreamsAdvanced.aplanar(List.of(List.of(1, 2), List.of(3), List.of(4))));
    }

    @Test
    void agruparPorInicial() {
        var m = Ej014StreamsAdvanced.agruparPorInicial(List.of("ana", "alba", "bea"));
        assertEquals(List.of("ana", "alba"), m.get('a'));
        assertEquals(List.of("bea"), m.get('b'));
    }

    @Test
    void unirConComas() {
        assertEquals("a, b, c", Ej014StreamsAdvanced.unirConComas(List.of("a", "b", "c")));
        assertEquals("", Ej014StreamsAdvanced.unirConComas(List.of()));
    }
}
