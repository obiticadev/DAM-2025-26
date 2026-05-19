package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej091FactoryAndBuilder.UsuarioBuilder;
import static org.junit.jupiter.api.Assertions.*;

class Ej091FactoryAndBuilderTest {

    @Test
    void builderConDefaults() {
        var u = new UsuarioBuilder().email("a@b.com").build();
        assertEquals("a@b.com", u.email());
        assertEquals("USER", u.rol());
        assertTrue(u.activo());
    }

    @Test
    void builderPersonalizado() {
        var u = new UsuarioBuilder().email("x@y.com").rol("OPS").activo(false).build();
        assertEquals("OPS", u.rol());
        assertFalse(u.activo());
    }

    @Test
    void emailObligatorio() {
        assertThrows(IllegalArgumentException.class, () -> new UsuarioBuilder().build());
    }

    @Test
    void factoryAdmin() {
        var a = Ej091FactoryAndBuilder.crearAdmin("admin@x.com");
        assertEquals("ADMIN", a.rol());
        assertTrue(a.activo());
    }
}
