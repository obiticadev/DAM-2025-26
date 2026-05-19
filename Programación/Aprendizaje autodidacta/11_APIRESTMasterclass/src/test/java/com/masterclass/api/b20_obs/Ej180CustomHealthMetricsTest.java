package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej180CustomHealthMetricsTest {

    @Test
    void bajoUmbralUp() {
        Map<String, String> r = Ej180CustomHealthMetrics.salud(100, 5, 0.1);
        assertEquals("0.0500", r.get("errorRate"));
        assertEquals("UP", r.get("status"));
    }

    @Test
    void sobreUmbralDown() {
        assertEquals("DOWN", Ej180CustomHealthMetrics.salud(100, 20, 0.1).get("status"));
    }

    @Test
    void totalCeroSinDivision() {
        assertEquals("0.0000", Ej180CustomHealthMetrics.salud(0, 0, 0.1).get("errorRate"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class, () -> Ej180CustomHealthMetrics.salud(-1, 0, 0.1));
        assertThrows(IllegalArgumentException.class, () -> Ej180CustomHealthMetrics.salud(10, 11, 0.1));
        assertThrows(IllegalArgumentException.class, () -> Ej180CustomHealthMetrics.salud(10, 1, 2.0));
    }
}
