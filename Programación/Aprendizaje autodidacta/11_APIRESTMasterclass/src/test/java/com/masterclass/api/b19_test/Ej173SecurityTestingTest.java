package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej173SecurityTestingTest {

    @Test
    void sinToken401() {
        assertEquals(401,
                Ej173SecurityTesting.statusEndpointProtegido(false, Set.of(), "ROLE_ADMIN"));
    }

    @Test
    void sinRol403() {
        assertEquals(403,
                Ej173SecurityTesting.statusEndpointProtegido(true, Set.of("ROLE_USER"), "ROLE_ADMIN"));
    }

    @Test
    void conRol200() {
        assertEquals(200,
                Ej173SecurityTesting.statusEndpointProtegido(true, Set.of("ROLE_ADMIN"), "ROLE_ADMIN"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej173SecurityTesting.statusEndpointProtegido(true, null, "ROLE_ADMIN"));
    }
}
