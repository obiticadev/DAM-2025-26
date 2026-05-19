package com.masterclass.api.b18_sec;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Ej160JwtValidationFilterTest {

    private static final String SECRETO = "01234567890123456789012345678901";

    @Test
    void extraeBearer() {
        assertEquals(Optional.of("abc.def.ghi"),
                Ej160JwtValidationFilter.extraerBearer("Bearer abc.def.ghi"));
    }

    @Test
    void headerInvalido() {
        assertTrue(Ej160JwtValidationFilter.extraerBearer(null).isEmpty());
        assertTrue(Ej160JwtValidationFilter.extraerBearer("Basic xyz").isEmpty());
    }

    @Test
    void tokenValidoDevuelveClaims() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        String t = Ej159JwtIssue.emitir(k, "ana", "ROLE_ADMIN", 1_000L, 60_000L);
        Optional<Claims> c = Ej160JwtValidationFilter.validar(k, t, 2_000L);
        assertTrue(c.isPresent());
        assertEquals("ana", c.get().getSubject());
    }

    @Test
    void tokenCaducado() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        String t = Ej159JwtIssue.emitir(k, "ana", "ROLE_ADMIN", 1_000L, 1_000L);
        assertTrue(Ej160JwtValidationFilter.validar(k, t, 999_999L).isEmpty());
    }
}
