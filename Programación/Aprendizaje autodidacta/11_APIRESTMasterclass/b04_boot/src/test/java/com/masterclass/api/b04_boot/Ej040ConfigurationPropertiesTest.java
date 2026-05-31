package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.time.Duration;
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

    @org.junit.jupiter.api.Test
    void testPasoExtra01() {
        var props = Map.of("server.host", "localhost", "server.port", "8080");
        var result = Ej040ConfigurationProperties.pasoExtra01(props);
        assertNotNull(result);
        assertEquals("localhost", result.getHost());
        assertEquals(8080, result.getPort());
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra02() {
        var propsValidas = Map.of("server.host", "localhost", "server.port", "8080");
        var resultValido = Ej040ConfigurationProperties.pasoExtra02(propsValidas);
        assertNotNull(resultValido);

        var propsInvalidas = Map.of("server.host", "localhost", "server.port", "80");
        assertThrows(Exception.class, () -> Ej040ConfigurationProperties.pasoExtra02(propsInvalidas));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra03() {
        var props = Map.of("app.name", "MyDemoApp", "app.server.host", "127.0.0.1", "app.server.port", "9000");
        var result = Ej040ConfigurationProperties.pasoExtra03(props);
        assertNotNull(result);
        assertEquals("MyDemoApp", result.getName());
        assertNotNull(result.getServer());
        assertEquals("127.0.0.1", result.getServer().getHost());
        assertEquals(9000, result.getServer().getPort());
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra04() {
        var props = Map.of(
            "services.urls[0]", "http://service-a",
            "services.urls[1]", "http://service-b",
            "services.metadata.env", "production",
            "services.metadata.version", "1.0.0"
        );
        var result = Ej040ConfigurationProperties.pasoExtra04(props);
        assertNotNull(result);
        assertEquals(2, result.getUrls().size());
        assertEquals("http://service-a", result.getUrls().get(0));
        assertEquals("production", result.getMetadata().get("env"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra05() {
        var props = Map.of("my-custom-prefix.host", "some-host", "my-custom-prefix.port", "8888");
        var result = Ej040ConfigurationProperties.pasoExtra05(props, Ej040ConfigurationProperties.ServerConfig.class, "my-custom-prefix");
        assertNotNull(result);
        assertEquals("some-host", result.getHost());
        assertEquals(8888, result.getPort());
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra06() {
        var props = Map.of("external-service-url", "https://api.external.com");
        var result = Ej040ConfigurationProperties.pasoExtra06(props);
        assertNotNull(result);
        assertEquals("https://api.external.com", result.getExternalServiceUrl());
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra07() {
        var result = Ej040ConfigurationProperties.pasoExtra07(Map.of());
        assertNotNull(result);
        assertEquals("localhost", result.getHost());
        assertEquals(8080, result.getPort());
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra08() {
        @org.springframework.boot.context.properties.EnableConfigurationProperties(Ej040ConfigurationProperties.ServerConfig.class)
        class DummyEnableClass {}

        var result = Ej040ConfigurationProperties.pasoExtra08(Ej040ConfigurationProperties.ServerConfig.class, DummyEnableClass.class);
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra09() {
        var props = Map.of("timeout", "5s");
        var result = Ej040ConfigurationProperties.pasoExtra09(props);
        assertNotNull(result);
        assertEquals(Duration.ofSeconds(5), result.getTimeout());
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra10() {
        var server = new Ej040ConfigurationProperties.ServerConfig("local-dns", 443);
        var result = Ej040ConfigurationProperties.pasoExtra10(server);
        assertNotNull(result);
        assertEquals("local-dns", result.get("host"));
        assertEquals(443, result.get("port"));
    }
}
