package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej156InMemoryUsersTest {

    private final Map<String, Usuario156> db = Map.of(
            "ana", new Usuario156("ana", "{bcrypt}h", Set.of("ROLE_ADMIN"), true),
            "bob", new Usuario156("bob", "{bcrypt}h", Set.of("ROLE_USER"), false));

    @Test
    void encuentraActivoCaseInsensitive() {
        assertTrue(Ej156InMemoryUsers.buscar(db, "ANA").isPresent());
    }

    @Test
    void deshabilitadoNoAparece() {
        assertTrue(Ej156InMemoryUsers.buscar(db, "bob").isEmpty());
    }

    @Test
    void noExiste() {
        assertTrue(Ej156InMemoryUsers.buscar(db, "zzz").isEmpty());
    }

    @Test
    void roles() {
        Usuario156 ana = new Usuario156("ana", "h", Set.of("ROLE_ADMIN"), true);
        assertTrue(Ej156InMemoryUsers.tieneAlgunRol(ana, Set.of("ROLE_ADMIN")));
        assertFalse(Ej156InMemoryUsers.tieneAlgunRol(ana, Set.of("ROLE_USER")));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej156InMemoryUsers.buscar(null, "ana"));
    }
}
