package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej188NPlusOneAndQueryTuningTest {

    private final List<Long> ids = List.of(1L, 2L, 3L, 4L, 5L);

    @Test
    void naiveEsNMasUno() {
        assertEquals(6, Ej188NPlusOneAndQueryTuning.consultasNaive(ids));
        assertEquals(1, Ej188NPlusOneAndQueryTuning.consultasNaive(List.of()));
    }

    @Test
    void optimizadaUsaLotes() {
        assertEquals(4, Ej188NPlusOneAndQueryTuning.consultasOptimizadas(ids, 2));
        assertEquals(2, Ej188NPlusOneAndQueryTuning.consultasOptimizadas(ids, 10));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej188NPlusOneAndQueryTuning.consultasNaive(null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej188NPlusOneAndQueryTuning.consultasOptimizadas(ids, 0));
    }
}
