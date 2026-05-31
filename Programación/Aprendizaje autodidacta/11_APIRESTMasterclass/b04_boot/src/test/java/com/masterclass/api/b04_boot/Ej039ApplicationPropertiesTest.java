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

    @org.springframework.context.annotation.Configuration
    static class ConfigProps {
        @org.springframework.context.annotation.Bean
        public Ej039ApplicationProperties.BeanConValueDefault beanConValueDefault() {
            return new Ej039ApplicationProperties.BeanConValueDefault();
        }

        @org.springframework.context.annotation.Bean
        public Ej039ApplicationProperties.BeanConValueList beanConValueList() {
            return new Ej039ApplicationProperties.BeanConValueList();
        }
    }

    @Test
    void retoExtra01_resolverPropiedadSpring() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.setProperty("app.nombre", "Antigravity");
        assertEquals("Antigravity", Ej039ApplicationProperties.resolverPropiedadSpring(env, "app.nombre"));
    }

    @Test
    void retoExtra02_resolverConDefaultSpring() {
        var env = new org.springframework.mock.env.MockEnvironment();
        assertEquals("Fallback", Ej039ApplicationProperties.resolverConDefaultSpring(env, "inexistente", "Fallback"));
    }

    @Test
    void retoExtra03_resolverComoInteger() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.setProperty("app.timeout", "60");
        assertEquals(Integer.valueOf(60), Ej039ApplicationProperties.resolverComoInteger(env, "app.timeout"));
    }

    @Test
    void retoExtra04_resolverComoBoolean() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.setProperty("app.activo", "true");
        assertEquals(Boolean.TRUE, Ej039ApplicationProperties.resolverComoBoolean(env, "app.activo"));
    }

    @Test
    void retoExtra05_resolverComoLista() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.setProperty("app.usuarios", "admin,user,guest");
        assertEquals(java.util.List.of("admin", "user", "guest"), Ej039ApplicationProperties.resolverComoLista(env, "app.usuarios"));
    }

    @Test
    void retoExtra06_beanConValueDefault() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigProps.class)) {
            var bean = ctx.getBean(Ej039ApplicationProperties.BeanConValueDefault.class);
            assertEquals("Anonimo", bean.getNombre());
        }
    }

    @Test
    void retoExtra07_beanConValueList() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigProps.class)) {
            var bean = ctx.getBean(Ej039ApplicationProperties.BeanConValueList.class);
            assertEquals(java.util.List.of("admin"), bean.getAdmins());
        }
    }

    @Test
    void retoExtra08_verificarPropiedadDefinida() {
        var env = new org.springframework.mock.env.MockEnvironment();
        assertFalse(Ej039ApplicationProperties.verificarPropiedadDefinida(env, "app.url"));
        env.setProperty("app.url", "http://localhost");
        assertTrue(Ej039ApplicationProperties.verificarPropiedadDefinida(env, "app.url"));
    }

    @Test
    void retoExtra09_resolverPropiedadesHomonimasSpring() {
        var env = new org.springframework.mock.env.MockEnvironment();
        env.setProperty("app.env", "dev");
        env.setProperty("app.default-env", "local");
        assertEquals("dev", Ej039ApplicationProperties.resolverPropiedadesHomonimasSpring(env, "${app.env:${app.default-env:prod}}"));
    }

    @Test
    void retoExtra10_registrarPropertySourceManual() {
        var env = new org.springframework.mock.env.MockEnvironment();
        var mapa = java.util.Map.<String, Object>of("app.puerto", 8080);
        Ej039ApplicationProperties.registrarPropertySourceManual(env, "customSource", mapa);
        assertEquals("8080", env.getProperty("app.puerto"));
    }
}
