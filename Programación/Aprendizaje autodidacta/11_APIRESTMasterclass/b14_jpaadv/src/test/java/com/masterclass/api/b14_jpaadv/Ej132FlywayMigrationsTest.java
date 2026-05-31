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

    @Test
    void testRetoExtra01() {
        assertTrue(Ej132FlywayMigrations.esFormatoValido("V1__init.sql"));
        assertFalse(Ej132FlywayMigrations.esFormatoValido("V__init.sql"));
    }

    @Test
    void testRetoExtra02() {
        assertEquals("add email", Ej132FlywayMigrations.descripcionDe("V2__add_email.sql"));
    }

    @Test
    void testRetoExtra03() {
        assertTrue(Ej132FlywayMigrations.esMigracionRepetible("R__view.sql"));
        assertFalse(Ej132FlywayMigrations.esMigracionRepetible("V1__init.sql"));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej132FlywayMigrations.esMigracionVersionada("V1__init.sql"));
        assertFalse(Ej132FlywayMigrations.esMigracionVersionada("R__view.sql"));
    }

    @Test
    void testRetoExtra05() {
        var v = Ej132FlywayMigrations.obtenerVersiones(java.util.List.of("V1__init.sql", "V2__x.sql"));
        assertEquals(java.util.List.of(1, 2), v);
    }

    @Test
    void testRetoExtra06() {
        var f = java.util.List.of("V1__init.sql", "V1__dup.sql");
        assertTrue(Ej132FlywayMigrations.tieneDuplicados(f));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("V", Ej132FlywayMigrations.prefijoDe("V1__init.sql"));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej132FlywayMigrations.esRollback("U1__undo.sql"));
        assertFalse(Ej132FlywayMigrations.esRollback("V1__init.sql"));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej132FlywayMigrations.compararVersiones("V1__a.sql", "V2__b.sql") < 0);
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Migracion[V=1, Desc=init]", Ej132FlywayMigrations.formatearMigracion("V1__init.sql"));
    }

}
