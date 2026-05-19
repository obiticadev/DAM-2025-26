package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej042ExternalizedConfigTest {

    @Test
    void envPisaYml() {
        assertEquals("us-east-1", Ej042ExternalizedConfig.resolve(
                Map.of("APP_REGION", "us-east-1"), Map.of("app.region", "eu-west-1"),
                "app.region", "local"));
    }

    @Test
    void ymlSiNoHayEnv() {
        assertEquals("eu-west-1", Ej042ExternalizedConfig.resolve(
                Map.of(), Map.of("app.region", "eu-west-1"), "app.region", "local"));
    }

    @Test
    void defaultSiNada() {
        assertEquals("local", Ej042ExternalizedConfig.resolve(
                Map.of(), Map.of(), "app.region", "local"));
    }
}
