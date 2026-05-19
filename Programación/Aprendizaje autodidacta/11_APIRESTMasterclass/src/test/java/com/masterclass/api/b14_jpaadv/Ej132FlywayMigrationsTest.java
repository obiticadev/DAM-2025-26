package com.masterclass.api.b14_jpaadv;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej132FlywayMigrationsTest {

    @Test
    void versionDe() {
        assertEquals(2, Ej132FlywayMigrations.versionDe("V2__add_email.sql"));
        assertEquals(10, Ej132FlywayMigrations.versionDe("V10__x.sql"));
    }

    @Test
    void formatoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej132FlywayMigrations.versionDe("init.sql"));
    }

    @Test
    void siguiente() {
        assertEquals(4, Ej132FlywayMigrations.siguienteVersion(
                List.of("V1__init.sql", "V3__x.sql", "V2__y.sql")));
        assertEquals(1, Ej132FlywayMigrations.siguienteVersion(List.of()));
    }
}
