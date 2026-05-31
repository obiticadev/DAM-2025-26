package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.List;
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

    @org.junit.jupiter.api.Test
    void testPasoExtra01() {
        var env = new org.springframework.mock.env.MockEnvironment().withProperty("custom.property", "hello");
        assertEquals("hello", Ej042ExternalizedConfig.pasoExtra01(env, "custom.property"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra02() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.setProperty("my.key", "original");
        Ej042ExternalizedConfig.pasoExtra02(env, "priority-source", Map.of("my.key", "winner"));
        assertEquals("winner", env.getProperty("my.key"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra03() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.setProperty("my.key", "original");
        Ej042ExternalizedConfig.pasoExtra03(env, "fallback-source", Map.of("my.key", "loser", "new.key", "fallback"));
        assertEquals("original", env.getProperty("my.key"));
        assertEquals("fallback", env.getProperty("new.key"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra04() {
        assertEquals("SPRING_DATASOURCE_CONNECTION_TIMEOUT", Ej042ExternalizedConfig.pasoExtra04("spring.datasource.connection-timeout"));
        assertEquals("APP_REGION", Ej042ExternalizedConfig.pasoExtra04("app.region"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra05() {
        var env = new org.springframework.mock.env.MockEnvironment();
        var sources = env.getPropertySources();
        sources.addFirst(new org.springframework.core.env.MapPropertySource("source-a", Map.of("my.key", "val-a")));
        sources.addFirst(new org.springframework.core.env.MapPropertySource("source-b", Map.of("my.key", "val-b")));
        assertEquals("source-b", Ej042ExternalizedConfig.pasoExtra05(env, "my.key"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra06() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.getPropertySources().addLast(new org.springframework.core.env.MapPropertySource("to-delete", Map.of()));
        assertTrue(Ej042ExternalizedConfig.pasoExtra06(env, "to-delete"));
        assertFalse(env.getPropertySources().contains("to-delete"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra07() {
        var app = new org.springframework.boot.SpringApplication(Ej042ExternalizedConfig.class);
        Ej042ExternalizedConfig.pasoExtra07(app, Map.of("default.port", 9999));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra08() {
        var env = new org.springframework.mock.env.MockEnvironment();
        Ej042ExternalizedConfig.pasoExtra08(env, "server.port=9090\napp.env=prod");
        assertEquals("9090", env.getProperty("server.port"));
        assertEquals("prod", env.getProperty("app.env"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra09() {
        assertTrue(Ej042ExternalizedConfig.pasoExtra09("java.home") || Ej042ExternalizedConfig.pasoExtra09("PATH"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra10() {
        var env = new org.springframework.mock.env.MockEnvironment();
        var list = Ej042ExternalizedConfig.pasoExtra10(env);
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }
}
