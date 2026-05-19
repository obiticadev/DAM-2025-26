package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej067PartialUpdateDto.PatchDto;
import com.masterclass.api.b07_dto.Ej067PartialUpdateDto.Usuario;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Ej067PartialUpdateDtoTest {

    private final Usuario base = new Usuario("Ana", "a@b.com", true);

    @Test
    void soloCambiaPresentes() {
        var r = Ej067PartialUpdateDto.aplicar(base,
                new PatchDto(Optional.of("Bea"), Optional.empty(), Optional.empty()));
        assertEquals("Bea", r.nombre());
        assertEquals("a@b.com", r.email());
        assertTrue(r.activo());
    }

    @Test
    void cambiosMultiples() {
        var r = Ej067PartialUpdateDto.aplicar(base,
                new PatchDto(Optional.empty(), Optional.of("z@z.com"), Optional.of(false)));
        assertEquals("Ana", r.nombre());
        assertEquals("z@z.com", r.email());
        assertFalse(r.activo());
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej067PartialUpdateDto.aplicar(null, null));
    }
}
