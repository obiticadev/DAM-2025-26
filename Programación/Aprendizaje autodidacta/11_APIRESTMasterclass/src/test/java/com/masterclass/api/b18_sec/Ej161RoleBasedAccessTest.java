package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej161RoleBasedAccessTest {

    @Test
    void adminAccedeAdmin() {
        assertTrue(Ej161RoleBasedAccess.permitido("GET", "/admin/users", Set.of("ROLE_ADMIN")));
    }

    @Test
    void userNoAccedeAdmin() {
        assertFalse(Ej161RoleBasedAccess.permitido("GET", "/admin/users", Set.of("ROLE_USER")));
    }

    @Test
    void userAccedeApi() {
        assertTrue(Ej161RoleBasedAccess.permitido("GET", "/api/x", Set.of("ROLE_USER")));
    }

    @Test
    void status() {
        assertEquals(401, Ej161RoleBasedAccess.statusHttp(false, false));
        assertEquals(403, Ej161RoleBasedAccess.statusHttp(true, false));
        assertEquals(200, Ej161RoleBasedAccess.statusHttp(true, true));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej161RoleBasedAccess.permitido("GET", "/api/x", null));
    }
}
