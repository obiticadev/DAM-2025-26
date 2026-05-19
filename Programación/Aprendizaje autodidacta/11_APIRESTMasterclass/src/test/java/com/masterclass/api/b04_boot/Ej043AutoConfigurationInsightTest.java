package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej043AutoConfigurationInsightTest {

    private final Set<String> cp = Set.of("javax.sql.DataSource", "org.h2.Driver");

    @Test
    void activaSiClaseYNoHayBeanUsuario() {
        assertTrue(Ej043AutoConfigurationInsight.shouldActivate(cp, "org.h2.Driver", false));
    }

    @Test
    void noActivaSiUsuarioYaTieneBean() {
        assertFalse(Ej043AutoConfigurationInsight.shouldActivate(cp, "org.h2.Driver", true));
    }

    @Test
    void noActivaSiFaltaClase() {
        assertFalse(Ej043AutoConfigurationInsight.shouldActivate(cp, "org.postgresql.Driver", false));
    }
}
