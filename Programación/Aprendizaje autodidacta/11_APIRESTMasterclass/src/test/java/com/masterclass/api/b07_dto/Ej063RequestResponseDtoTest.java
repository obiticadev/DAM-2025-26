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
    void testPasoExtra01() {
        assertTrue(Ej063RequestResponseDto.pasoExtra01("Abc12345"));
        assertFalse(Ej063RequestResponseDto.pasoExtra01("123"));
    }

    @Test
    void testPasoExtra02() {
        var entity = new UsuarioEntity(1L, "admin@test.com", "hash:123");
        assertNotNull(Ej063RequestResponseDto.pasoExtra02(entity, "USER"));
    }

    @Test
    void testPasoExtra03() {
        assertEquals("user", Ej063RequestResponseDto.pasoExtra03("user@domain.com"));
        assertNull(Ej063RequestResponseDto.pasoExtra03("nodomain"));
    }

    @Test
    void testPasoExtra04() {
        assertEquals("hash:mysalt:mypass", Ej063RequestResponseDto.pasoExtra04("mypass", "mysalt"));
    }

    @Test
    void testPasoExtra05() {
        assertTrue(Ej063RequestResponseDto.pasoExtra05("test@company.com", java.util.List.of("company.com")));
        assertFalse(Ej063RequestResponseDto.pasoExtra05("test@gmail.com", java.util.List.of("company.com")));
    }

    @Test
    void testPasoExtra06() {
        var entity = new UsuarioEntity(1L, "a@b.com", "hash:123");
        assertNotNull(Ej063RequestResponseDto.pasoExtra06(entity));
    }

    @Test
    void testPasoExtra07() {
        var req = new RegistroRequest("  USER@domain.COM  ", "password");
        var sanitized = Ej063RequestResponseDto.pasoExtra07(req);
        assertNotNull(sanitized);
    }

    @Test
    void testPasoExtra08() {
        assertTrue(Ej063RequestResponseDto.pasoExtra08("pass", "hash:pass"));
    }

    @Test
    void testPasoExtra09() {
        var req = new RegistroRequest("a@b.com", "pass");
        assertNotNull(Ej063RequestResponseDto.pasoExtra09(req, 30L));
    }

    @Test
    void testPasoExtra10() {
        var res = new UsuarioResponse(1L, "a@b.com");
        assertNotNull(Ej063RequestResponseDto.pasoExtra10(res));
    }
}
