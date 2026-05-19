package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej162MethodSecurityTest {

    @Test
    void hasAnyRoleOk() {
        assertTrue(Ej162MethodSecurity.hasAnyRole(Set.of("ROLE_USER"), Set.of("ROLE_USER", "ROLE_ADMIN")));
    }

    @Test
    void hasAnyRoleFalla() {
        assertFalse(Ej162MethodSecurity.hasAnyRole(Set.of("ROLE_GUEST"), Set.of("ROLE_ADMIN")));
    }

    @Test
    void propietario() {
        assertTrue(Ej162MethodSecurity.propietarioOAdmin("ana", "ana", Set.of()));
    }

    @Test
    void adminAccedeAjeno() {
        assertTrue(Ej162MethodSecurity.propietarioOAdmin("ana", "bob", Set.of("ROLE_ADMIN")));
    }

    @Test
    void ajenoSinAdmin() {
        assertFalse(Ej162MethodSecurity.propietarioOAdmin("ana", "bob", Set.of("ROLE_USER")));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej162MethodSecurity.hasAnyRole(Set.of("ROLE_USER"), Set.of()));
    }
}
