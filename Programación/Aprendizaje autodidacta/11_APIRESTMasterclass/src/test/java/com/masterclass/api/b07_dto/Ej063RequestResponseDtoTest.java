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
}
