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
}
