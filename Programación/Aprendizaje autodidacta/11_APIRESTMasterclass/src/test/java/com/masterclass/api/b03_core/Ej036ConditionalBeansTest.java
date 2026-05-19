package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej036ConditionalBeansTest {

    @Test
    void perfiles() {
        assertEquals("disco-local", Ej036ConditionalBeans.segunPerfil("dev").donde());
        assertEquals("disco-local", Ej036ConditionalBeans.segunPerfil("test").donde());
        assertEquals("aws-s3", Ej036ConditionalBeans.segunPerfil("prod").donde());
    }

    @Test
    void perfilInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej036ConditionalBeans.segunPerfil("qa"));
    }
}
