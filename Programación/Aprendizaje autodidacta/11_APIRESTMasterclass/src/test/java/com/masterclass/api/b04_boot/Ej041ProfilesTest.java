package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej041ProfilesTest {

    @Test
    void urlPorPerfil() {
        assertEquals("jdbc:h2:mem:devdb", Ej041Profiles.datasourceUrl("dev"));
        assertEquals("jdbc:h2:mem:testdb", Ej041Profiles.datasourceUrl("test"));
        assertEquals("jdbc:postgresql://db:5432/app", Ej041Profiles.datasourceUrl("prod"));
    }

    @Test
    void perfilNuloEsDev() {
        assertEquals("jdbc:h2:mem:devdb", Ej041Profiles.datasourceUrl(null));
    }

    @Test
    void perfilInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej041Profiles.datasourceUrl("qa"));
    }

    @Test
    void verboseSoloDevTest() {
        assertTrue(Ej041Profiles.verboseErrors("dev"));
        assertTrue(Ej041Profiles.verboseErrors("test"));
        assertFalse(Ej041Profiles.verboseErrors("prod"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 01")
    @org.junit.jupiter.api.Test
    void testPasoExtra01() {
        var env = new org.springframework.mock.env.MockEnvironment().withProperty("spring.profiles.active", "dev");
        assertTrue(Ej041Profiles.pasoExtra01(env, "dev"));
        assertFalse(Ej041Profiles.pasoExtra01(env, "prod"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 02")
    @org.junit.jupiter.api.Test
    void testPasoExtra02() {
        var env = new org.springframework.mock.env.MockEnvironment();
        Ej041Profiles.pasoExtra02(env, "dev", "cloud");
        assertTrue(env.acceptsProfiles(org.springframework.core.env.Profiles.of("dev")));
        assertTrue(env.acceptsProfiles(org.springframework.core.env.Profiles.of("cloud")));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 03")
    @org.junit.jupiter.api.Test
    void testPasoExtra03() {
        var config = Ej041Profiles.pasoExtra03();
        assertNotNull(config);
        assertEquals("Dev Environment", config.message());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 04")
    @org.junit.jupiter.api.Test
    void testPasoExtra04() {
        var service = Ej041Profiles.pasoExtra04();
        assertNotNull(service);
        assertEquals("Safe for testing", service.getStatus());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 05")
    @org.junit.jupiter.api.Test
    void testPasoExtra05() {
        var env = new org.springframework.mock.env.MockEnvironment().withProperty("spring.profiles.active", "dev,cloud");
        var result = Ej041Profiles.pasoExtra05(env);
        assertNotNull(result);
        assertTrue(result.contains("dev"));
        assertTrue(result.contains("cloud"));

        var envDefault = new org.springframework.mock.env.MockEnvironment();
        envDefault.setDefaultProfiles("default-profile");
        var resultDefault = Ej041Profiles.pasoExtra05(envDefault);
        assertTrue(resultDefault.contains("default-profile"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 06")
    @org.junit.jupiter.api.Test
    void testPasoExtra06() {
        var service = Ej041Profiles.pasoExtra06();
        assertNotNull(service);
        assertEquals("Local Cloud Runner", service.getProvider());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 07")
    @org.junit.jupiter.api.Test
    void testPasoExtra07() {
        var envActive = new org.springframework.mock.env.MockEnvironment().withProperty("spring.profiles.active", "dev");
        assertFalse(Ej041Profiles.pasoExtra07(envActive));

        var envDefault = new org.springframework.mock.env.MockEnvironment();
        assertTrue(Ej041Profiles.pasoExtra07(envDefault));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 08")
    @org.junit.jupiter.api.Test
    void testPasoExtra08() {
        List<Ej041Profiles.DbService> services = List.of(
            new Ej041Profiles.DevDbService(),
            new Ej041Profiles.ProdDbService()
        );
        var active = Ej041Profiles.pasoExtra08(services, Ej041Profiles.ProdDbService.class);
        assertNotNull(active);
        assertTrue(active instanceof Ej041Profiles.ProdDbService);
        assertEquals("jdbc:postgresql://db", active.getUrl());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 09")
    @org.junit.jupiter.api.Test
    void testPasoExtra09() {
        var prodProfiles = Ej041Profiles.pasoExtra09("EU");
        assertArrayEquals(new String[]{"prod", "eu"}, prodProfiles);

        var devProfiles = Ej041Profiles.pasoExtra09("ASIA");
        assertArrayEquals(new String[]{"dev"}, devProfiles);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @org.junit.jupiter.api.Test
    void testPasoExtra10() {
        var config = Ej041Profiles.pasoExtra10();
        assertNotNull(config);
        assertEquals("Complex Mode", config.getMode());
    }
}
