package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej039ApplicationPropertiesTest {

    private final Map<String, String> props = Map.of("app.region", "eu-west-1");

    @Test
    void usaValorDeProps() {
        assertEquals("eu-west-1", Ej039ApplicationProperties.resolve(props, "${app.region:us}"));
    }

    @Test
    void usaDefaultSiFalta() {
        assertEquals("30", Ej039ApplicationProperties.resolve(props, "${app.timeout:30}"));
    }

    @Test
    void sinDefaultNiClave() {
        assertEquals("", Ej039ApplicationProperties.resolve(props, "${app.x}"));
    }

    @Test
    void formatoInvalido() {
        assertEquals("", Ej039ApplicationProperties.resolve(props, "app.region"));
    }
}
