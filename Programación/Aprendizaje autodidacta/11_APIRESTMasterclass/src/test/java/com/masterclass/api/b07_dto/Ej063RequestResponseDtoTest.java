package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej063RequestResponseDto.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej063RequestResponseDtoTest {

    @Test
    void toEntityHasheaYValida() {
        var e = Ej063RequestResponseDto.toEntity(new RegistroRequest("a@b.com", "secreto"), 1L);
        assertEquals("a@b.com", e.email);
        assertTrue(e.passwordHash.startsWith("hash:"));
        assertNotEquals("secreto", e.passwordHash);
    }

    @Test
    void emailInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej063RequestResponseDto.toEntity(new RegistroRequest("malo", "secreto"), 1L));
    }

    @Test
    void passwordCorta() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej063RequestResponseDto.toEntity(new RegistroRequest("a@b.com", "123"), 1L));
    }

    @Test
    void responseNoExponePassword() {
        var e = new UsuarioEntity(9L, "a@b.com", "hash:x");
        var r = Ej063RequestResponseDto.toResponse(e);
        assertEquals(9L, r.id());
        assertEquals("a@b.com", r.email());
        assertFalse(r.toString().contains("hash"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void testPasoExtra01() {
        assertTrue(Ej063RequestResponseDto.pasoExtra01("Abc12345"));
        assertFalse(Ej063RequestResponseDto.pasoExtra01("123"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void testPasoExtra02() {
        var entity = new UsuarioEntity(1L, "admin@test.com", "hash:123");
        assertNotNull(Ej063RequestResponseDto.pasoExtra02(entity, "USER"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void testPasoExtra03() {
        assertEquals("user", Ej063RequestResponseDto.pasoExtra03("user@domain.com"));
        assertNull(Ej063RequestResponseDto.pasoExtra03("nodomain"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void testPasoExtra04() {
        assertEquals("hash:mysalt:mypass", Ej063RequestResponseDto.pasoExtra04("mypass", "mysalt"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void testPasoExtra05() {
        assertTrue(Ej063RequestResponseDto.pasoExtra05("test@company.com", java.util.List.of("company.com")));
        assertFalse(Ej063RequestResponseDto.pasoExtra05("test@gmail.com", java.util.List.of("company.com")));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void testPasoExtra06() {
        var entity = new UsuarioEntity(1L, "a@b.com", "hash:123");
        assertNotNull(Ej063RequestResponseDto.pasoExtra06(entity));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void testPasoExtra07() {
        var req = new RegistroRequest("  USER@domain.COM  ", "password");
        var sanitized = Ej063RequestResponseDto.pasoExtra07(req);
        assertNotNull(sanitized);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void testPasoExtra08() {
        assertTrue(Ej063RequestResponseDto.pasoExtra08("pass", "hash:pass"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void testPasoExtra09() {
        var req = new RegistroRequest("a@b.com", "pass");
        assertNotNull(Ej063RequestResponseDto.pasoExtra09(req, 30L));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void testPasoExtra10() {
        var res = new UsuarioResponse(1L, "a@b.com");
        assertNotNull(Ej063RequestResponseDto.pasoExtra10(res));
    }
}
