package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej164CsrfAndCorsHardeningTest {

    @Test
    void jwtNoNecesitaCsrf() {
        assertFalse(Ej164CsrfAndCorsHardening.requiereCsrf("POST", false));
    }

    @Test
    void cookieMutadoraNecesitaCsrf() {
        assertTrue(Ej164CsrfAndCorsHardening.requiereCsrf("POST", true));
    }

    @Test
    void getNuncaCsrf() {
        assertFalse(Ej164CsrfAndCorsHardening.requiereCsrf("GET", true));
    }

    @Test
    void corsAllowlist() {
        Set<String> ok = Set.of("https://app.com");
        assertTrue(Ej164CsrfAndCorsHardening.corsPermitido("https://app.com", ok));
        assertFalse(Ej164CsrfAndCorsHardening.corsPermitido("http://app.com", ok));
        assertFalse(Ej164CsrfAndCorsHardening.corsPermitido(null, ok));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej164CsrfAndCorsHardening.requiereCsrf(" ", false));
        assertThrows(IllegalArgumentException.class,
                () -> Ej164CsrfAndCorsHardening.corsPermitido("x", null));
    }
}
