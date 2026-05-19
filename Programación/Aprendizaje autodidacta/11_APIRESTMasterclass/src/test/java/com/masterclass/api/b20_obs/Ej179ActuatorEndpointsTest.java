package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej179ActuatorEndpointsTest {

    @Test
    void todosUp() {
        assertEquals("UP", Ej179ActuatorEndpoints.estadoAgregado(Map.of("db", "UP", "disk", "up ")));
    }

    @Test
    void unoDown() {
        assertEquals("DOWN", Ej179ActuatorEndpoints.estadoAgregado(Map.of("db", "UP", "disk", "DOWN")));
    }

    @Test
    void vacioUnknown() {
        assertEquals("UNKNOWN", Ej179ActuatorEndpoints.estadoAgregado(Map.of()));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class, () -> Ej179ActuatorEndpoints.estadoAgregado(null));
        Map<String, String> conNull = new HashMap<>();
        conNull.put("db", null);
        assertThrows(IllegalArgumentException.class, () -> Ej179ActuatorEndpoints.estadoAgregado(conNull));
    }
}
