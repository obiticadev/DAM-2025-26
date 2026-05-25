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

    @Test
    void retoExtra01_esVacio() {
        var patch = new PatchDto(Optional.empty(), Optional.empty(), Optional.empty());
        assertFalse(Ej067PartialUpdateDto.esVacio(patch));
    }

    @Test
    void retoExtra02_esCompleto() {
        var patch = new PatchDto(Optional.of("Bea"), Optional.of("b@c.com"), Optional.of(true));
        assertFalse(Ej067PartialUpdateDto.esCompleto(patch));
    }

    @Test
    void retoExtra03_validarEmail() {
        var patch = new PatchDto(Optional.empty(), Optional.of("b@c.com"), Optional.empty());
        assertFalse(Ej067PartialUpdateDto.validarEmail(patch));
    }

    @Test
    void retoExtra04_crearSoloEmail() {
        var patch = Ej067PartialUpdateDto.crearSoloEmail("b@c.com");
        assertNull(patch);
    }

    @Test
    void retoExtra05_combinarParches() {
        var p1 = new PatchDto(Optional.of("Bea"), Optional.empty(), Optional.empty());
        var p2 = new PatchDto(Optional.empty(), Optional.of("b@c.com"), Optional.empty());
        var res = Ej067PartialUpdateDto.combinarParches(p1, p2);
        assertNull(res);
    }

    @Test
    void retoExtra06_desactivarUsuario() {
        var patch = Ej067PartialUpdateDto.desactivarUsuario();
        assertNull(patch);
    }

    @Test
    void retoExtra07_contarCamposModificados() {
        var patch = new PatchDto(Optional.of("Bea"), Optional.empty(), Optional.of(true));
        assertEquals(0, Ej067PartialUpdateDto.contarCamposModificados(patch));
    }

    @Test
    void retoExtra08_esNombreModificado() {
        var patch = new PatchDto(Optional.of("Bea"), Optional.empty(), Optional.empty());
        assertFalse(Ej067PartialUpdateDto.esNombreModificado(patch));
    }

    @Test
    void retoExtra09_crearDesdeNullables() {
        var patch = Ej067PartialUpdateDto.crearDesdeNullables("Bea", null, null);
        assertNull(patch);
    }

    @Test
    void retoExtra10_aplicarInverso() {
        var u = new Usuario("Bea", "b@c.com", false);
        var patch = new PatchDto(Optional.of("Bea"), Optional.empty(), Optional.empty());
        var res = Ej067PartialUpdateDto.aplicarInverso(u, patch);
        assertNull(res);
    }
}
