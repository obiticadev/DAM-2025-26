package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej092StrategyAndPolicyTest {

    @Test
    void estrategias() {
        assertEquals(100, Ej092StrategyAndPolicy.aplicar(100, "none"), 0.0001);
        assertEquals(70, Ej092StrategyAndPolicy.aplicar(100, "black-friday"), 0.0001);
        assertEquals(90, Ej092StrategyAndPolicy.aplicar(100, "vip"), 0.0001);
    }

    @Test
    void desconocida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej092StrategyAndPolicy.aplicar(100, "ninguna"));
    }

    @Test
    void precioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej092StrategyAndPolicy.aplicar(-1, "none"));
    }
}
