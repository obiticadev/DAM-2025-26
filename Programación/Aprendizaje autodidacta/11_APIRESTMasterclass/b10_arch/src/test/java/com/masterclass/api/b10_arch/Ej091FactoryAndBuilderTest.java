package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej091FactoryAndBuilder.UsuarioBuilder;
import com.masterclass.api.b10_arch.Ej091FactoryAndBuilder.Usuario;
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

@Test
    void testDesafioFijarEmail() {
        var b = new UsuarioBuilder();
        assertEquals(b, Ej091FactoryAndBuilder.desafioFijarEmail(b, "a@b.com"));
    }

    @Test
    void testDesafioFijarRol() {
        var b = new UsuarioBuilder();
        assertEquals(b, Ej091FactoryAndBuilder.desafioFijarRol(b, "ADMIN"));
    }

    @Test
    void testDesafioFijarActivo() {
        var b = new UsuarioBuilder();
        assertEquals(b, Ej091FactoryAndBuilder.desafioFijarActivo(b, false));
    }

    @Test
    void testDesafioValidarEmailConstruccion() {
        assertThrows(IllegalArgumentException.class, () -> Ej091FactoryAndBuilder.desafioValidarEmailConstruccion(null));
        assertThrows(IllegalArgumentException.class, () -> Ej091FactoryAndBuilder.desafioValidarEmailConstruccion(" "));
    }

    @Test
    void testDesafioConstruirUsuario() {
        var b = new UsuarioBuilder().email("a@b.com");
        assertNotNull(Ej091FactoryAndBuilder.desafioConstruirUsuario(b));
    }

    @Test
    void testDesafioVerificarValoresPorDefecto() {
        var u = new Usuario("a@b.com", "USER", true);
        assertTrue(Ej091FactoryAndBuilder.desafioVerificarValoresPorDefecto(u));
    }

    @Test
    void testDesafioInstanciarBuilder() {
        assertNotNull(Ej091FactoryAndBuilder.desafioInstanciarBuilder());
    }

    @Test
    void testDesafioFijarRecetaAdmin() {
        var b = new UsuarioBuilder();
        Ej091FactoryAndBuilder.desafioFijarRecetaAdmin(b);
        b.email("a@b.com");
        assertEquals("ADMIN", b.build().rol());
    }

    @Test
    void testDesafioConceptoFactory() {
        assertNotNull(Ej091FactoryAndBuilder.desafioConceptoFactory());
    }

    @Test
    void testDesafioObtenerUsuarioAdminFinal() {
        var u = Ej091FactoryAndBuilder.desafioObtenerUsuarioAdminFinal("a@b.com");
        assertEquals("ADMIN", u.rol());
        assertTrue(u.activo());
    }
}
