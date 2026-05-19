package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej158UserDetailsServiceTest {

    private final List<CuentaBd158> repo = List.of(
            new CuentaBd158("ana", "$2a$10$h", "ROLE_ADMIN", true, false),
            new CuentaBd158("bob", "$2a$10$h", "ROLE_USER", true, true));

    @Test
    void cargaUsuario() {
        assertTrue(Ej158UserDetailsService.loadUserByUsername(repo, "ANA").isPresent());
    }

    @Test
    void bloqueadoNoCarga() {
        assertTrue(Ej158UserDetailsService.loadUserByUsername(repo, "bob").isEmpty());
    }

    @Test
    void puedeAutenticar() {
        assertTrue(Ej158UserDetailsService.puedeAutenticar(
                new CuentaBd158("ana", "h", "ROLE_ADMIN", true, false)));
        assertFalse(Ej158UserDetailsService.puedeAutenticar(
                new CuentaBd158("x", "h", "ROLE_USER", false, false)));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej158UserDetailsService.loadUserByUsername(null, "ana"));
    }
}
