package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
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
}
