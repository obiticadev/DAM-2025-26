package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej175TestSlicesAndProfilesTest {

    private final Map<String, String> props = Map.of("url", "prod-db", "test.url", "h2-mem");

    @Test
    void perfilGanaSobreBase() {
        assertEquals("h2-mem", Ej175TestSlicesAndProfiles.resolver(props, "test", "url"));
    }

    @Test
    void fallbackABase() {
        assertEquals("prod-db", Ej175TestSlicesAndProfiles.resolver(props, "prod", "url"));
    }

    @Test
    void noExiste() {
        assertNull(Ej175TestSlicesAndProfiles.resolver(props, "test", "ausente"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej175TestSlicesAndProfiles.resolver(null, "test", "url"));
    }
}
