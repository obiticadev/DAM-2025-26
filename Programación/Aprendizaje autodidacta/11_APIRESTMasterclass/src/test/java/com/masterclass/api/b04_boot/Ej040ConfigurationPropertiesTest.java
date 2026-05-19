package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej040ConfigurationPropertiesTest {

    @Test
    void bindCompleto() {
        var p = Ej040ConfigurationProperties.bind(Map.of("app.region", "eu", "app.timeout", "45"));
        assertEquals("eu", p.region());
        assertEquals(45, p.timeout());
    }

    @Test
    void timeoutPorDefecto() {
        var p = Ej040ConfigurationProperties.bind(Map.of("app.region", "eu"));
        assertEquals(30, p.timeout());
    }

    @Test
    void faltaRegionObligatoria() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej040ConfigurationProperties.bind(Map.of("app.timeout", "10")));
    }

    @Test
    void timeoutNoNumerico() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej040ConfigurationProperties.bind(Map.of("app.region", "eu", "app.timeout", "x")));
    }
}
