package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej061VersioningStrategiesTest {

    @Test
    void versionDeRuta() {
        assertEquals(2, Ej061VersioningStrategies.resolveVersion("/api/v2/users", null));
    }

    @Test
    void headerPisaRuta() {
        assertEquals(3, Ej061VersioningStrategies.resolveVersion("/api/v2/users", "3"));
    }

    @Test
    void defaultUno() {
        assertEquals(1, Ej061VersioningStrategies.resolveVersion("/api/users", null));
    }

    @Test
    void versionInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej061VersioningStrategies.resolveVersion("/api/users", "x"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void testPasoExtra01() {
        assertEquals(2, Ej061VersioningStrategies.pasoExtra01("application/vnd.company.app-v2+json"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void testPasoExtra02() {
        assertEquals("3.0", Ej061VersioningStrategies.pasoExtra02("/api/users?api-version=3.0"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void testPasoExtra03() {
        assertTrue(Ej061VersioningStrategies.pasoExtra03("/api/v2/users"));
        assertFalse(Ej061VersioningStrategies.pasoExtra03("/api/av2/users"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void testPasoExtra04() {
        assertEquals(3, Ej061VersioningStrategies.pasoExtra04("3", "2", "/v1/"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void testPasoExtra05() {
        assertArrayEquals(new int[]{1, 4, 2}, Ej061VersioningStrategies.pasoExtra05("1.4.2-beta"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void testPasoExtra06() {
        assertTrue(Ej061VersioningStrategies.pasoExtra06("1.5.2", "^1.0.0"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void testPasoExtra07() {
        org.springframework.http.ResponseEntity.BodyBuilder builder = org.springframework.http.ResponseEntity.ok();
        assertNotNull(Ej061VersioningStrategies.pasoExtra07(builder, "Wed, 11 Nov 2026 00:00:00 GMT"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void testPasoExtra08() {
        assertTrue(Ej061VersioningStrategies.pasoExtra08(2, java.util.Arrays.asList(1, 2, 3)));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void testPasoExtra09() {
        assertEquals("/api/users/1", Ej061VersioningStrategies.pasoExtra09("/api/v2/users/1"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void testPasoExtra10() {
        assertEquals("/v3/users/5", Ej061VersioningStrategies.pasoExtra10("/users/5", 3));
    }
}
