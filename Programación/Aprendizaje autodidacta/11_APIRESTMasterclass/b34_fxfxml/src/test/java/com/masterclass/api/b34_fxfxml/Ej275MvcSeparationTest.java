package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import com.masterclass.api.b34_fxfxml.Ej275MvcSeparation.Cliente;

import static org.junit.jupiter.api.Assertions.*;

class Ej275MvcSeparationTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void crearCliente() {
        assertEquals(new Cliente(1, "Ana", "ana@correo.com"),
                Ej275MvcSeparation.crearCliente(1, "  Ana  ", "ana@correo.com")); // recorta espacios
        assertNull(Ej275MvcSeparation.crearCliente(1, "   ", "x")); // caso límite: nombre en blanco
    }

    @Test
    void procesarAlta() {
        assertEquals("Cliente creado: Ana", Ej275MvcSeparation.procesarAlta("Ana", "ana@correo.com"));
        assertEquals("Error: nombre requerido", Ej275MvcSeparation.procesarAlta("", "x")); // caso límite
    }

    @Test
    void retoExtra01_capaDe() {
        assertEquals("Vista", Ej275MvcSeparation.capaDe("Button"));
        assertEquals("Modelo", Ej275MvcSeparation.capaDe("reglaNegocio"));
        assertEquals("Controlador", Ej275MvcSeparation.capaDe("initialize"));
    }

    @Test
    void retoExtra02_esVista() {
        assertTrue(Ej275MvcSeparation.esVista("Label"));
        assertFalse(Ej275MvcSeparation.esVista("reglaNegocio"));
    }

    @Test
    void retoExtra03_nombreValido() {
        assertTrue(Ej275MvcSeparation.nombreValido("Ana"));
        assertFalse(Ej275MvcSeparation.nombreValido(""));
        assertFalse(Ej275MvcSeparation.nombreValido("   "));
    }

    @Test
    void retoExtra04_emailValido() {
        assertTrue(Ej275MvcSeparation.emailValido("a@b.com"));
        assertFalse(Ej275MvcSeparation.emailValido("a@b"));
    }

    @Test
    void retoExtra05_primerError() {
        assertEquals("Error: nombre requerido", Ej275MvcSeparation.primerError("", "a@b.com"));
        assertEquals("Error: email no válido", Ej275MvcSeparation.primerError("Ana", "malo"));
        assertEquals("", Ej275MvcSeparation.primerError("Ana", "a@b.com"));
    }

    @Test
    void retoExtra06_resumen() {
        assertEquals("Ana <ana@correo.com>", Ej275MvcSeparation.resumen(new Cliente(1, "Ana", "ana@correo.com")));
    }

    @Test
    void retoExtra07_nombresDe() {
        List<Cliente> cs = List.of(new Cliente(1, "Ana", "a@b.com"), new Cliente(2, "Berto", "b@b.com"));
        assertEquals(List.of("Ana", "Berto"), Ej275MvcSeparation.nombresDe(cs));
        assertEquals(List.of(), Ej275MvcSeparation.nombresDe(List.of())); // caso límite
    }

    @Test
    void retoExtra08_buscarPorId() {
        List<Cliente> cs = List.of(new Cliente(1, "Ana", "a@b.com"), new Cliente(2, "Berto", "b@b.com"));
        assertEquals(Optional.of(new Cliente(2, "Berto", "b@b.com")), Ej275MvcSeparation.buscarPorId(cs, 2));
        assertEquals(Optional.empty(), Ej275MvcSeparation.buscarPorId(cs, 9)); // caso límite
    }

    @Test
    void retoExtra09_conNombre() {
        Cliente original = new Cliente(1, "Ana", "a@b.com");
        Cliente cambiado = Ej275MvcSeparation.conNombre(original, "Berta");
        assertEquals(new Cliente(1, "Berta", "a@b.com"), cambiado);
        assertEquals("Ana", original.nombre()); // el original no cambia (inmutable)
    }

    @Test
    void retoExtra10_flujoAlta() {
        List<Cliente> vacio = List.of();
        assertEquals(1, Ej275MvcSeparation.flujoAlta(vacio, "Ana", "a@b.com").size()); // alta válida
        assertEquals(0, Ej275MvcSeparation.flujoAlta(vacio, "", "a@b.com").size()); // alta inválida
    }
}
