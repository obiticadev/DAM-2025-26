package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import static org.junit.jupiter.api.Assertions.*;

class Ej159JwtIssueTest {

    private static final String SECRETO = "01234567890123456789012345678901";

    @Test
    void claveValida() {
        assertNotNull(Ej159JwtIssue.clave(SECRETO));
    }

    @Test
    void claveCortaFalla() {
        assertThrows(IllegalArgumentException.class, () -> Ej159JwtIssue.clave("corto"));
    }

    @Test
    void emiteTokenConTresPartes() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        String t = Ej159JwtIssue.emitir(k, "ana", "ROLE_ADMIN", 1_000L, 60_000L);
        assertEquals(3, t.split("\\.").length);
    }

    @Test
    void invalidos() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        assertThrows(IllegalArgumentException.class,
                () -> Ej159JwtIssue.emitir(k, " ", "r", 1L, 1L));
        assertThrows(IllegalArgumentException.class,
                () -> Ej159JwtIssue.emitir(k, "ana", "r", 1L, 0L));
    }
}
