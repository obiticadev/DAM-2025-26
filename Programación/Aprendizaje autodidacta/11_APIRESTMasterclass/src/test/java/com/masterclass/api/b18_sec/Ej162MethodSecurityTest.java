package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej162MethodSecurityTest {

    @Test
    void hasAnyRoleOk() {
        assertTrue(Ej162MethodSecurity.hasAnyRole(Set.of("ROLE_USER"), Set.of("ROLE_USER", "ROLE_ADMIN")));
    }

    @Test
    void hasAnyRoleFalla() {
        assertFalse(Ej162MethodSecurity.hasAnyRole(Set.of("ROLE_GUEST"), Set.of("ROLE_ADMIN")));
    }

    @Test
    void propietario() {
        assertTrue(Ej162MethodSecurity.propietarioOAdmin("ana", "ana", Set.of()));
    }

    @Test
    void adminAccedeAjeno() {
        assertTrue(Ej162MethodSecurity.propietarioOAdmin("ana", "bob", Set.of("ROLE_ADMIN")));
    }

    @Test
    void ajenoSinAdmin() {
        assertFalse(Ej162MethodSecurity.propietarioOAdmin("ana", "bob", Set.of("ROLE_USER")));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej162MethodSecurity.hasAnyRole(Set.of("ROLE_USER"), Set.of()));
    }

    @Test
    void testRetoExtra01_esPropietarioOAdmin() {
        // Determina si el usuario tiene permiso sobre el recurso (es el dueño o es admin).
        assertTrue(Ej162MethodSecurity.esPropietarioOAdmin("ada", "ada", false));
    }

    @Test
    void testRetoExtra02_esExpresionSpelValida() {
        // Valida sintaxis SpEL basica de Spring Security.
        assertTrue(Ej162MethodSecurity.esExpresionSpelValida("hasRole('USER')"));
    }

    @Test
    void testRetoExtra03_esExcepcionDeMetodo() {
        // Determina si la excepcion es lanzada por la seguridad del metodo (PreAuthorize).
        assertTrue(Ej162MethodSecurity.esExcepcionDeMetodo(new SecurityException()));
    }

    @Test
    void testRetoExtra04_crearLogMetodoSeguro() {
        // Genera el log descriptivo de llamada segura.
        assertTrue(Ej162MethodSecurity.crearLogMetodoSeguro("m", "u").contains("m"));
    }

    @Test
    void testRetoExtra05_esUsuarioInterno() {
        // Verifica si el usuario es del dominio interno de la organizacion.
        assertTrue(Ej162MethodSecurity.esUsuarioInterno("a@masterclass.com"));
    }

    @Test
    void testRetoExtra06_esArgumentoSeguro() {
        // Determina si el parametro del metodo no es nulo.
        assertTrue(Ej162MethodSecurity.esArgumentoSeguro("arg"));
    }

    @Test
    void testRetoExtra07_esMetodoPublico() {
        // Indica si el metodo esta exceptuado de validacion previa.
        assertTrue(Ej162MethodSecurity.esMetodoPublico("saludar", java.util.List.of("saludar")));
    }

    @Test
    void testRetoExtra08_esFalloAnotacion() {
        // Determina si la excepcion es por mala configuracion del PreAuthorize.
        assertTrue(Ej162MethodSecurity.esFalloAnotacion(new IllegalArgumentException("spel")));
    }

    @Test
    void testRetoExtra09_esRolePresente() {
        // Comprueba si la expresion SpEL menciona un rol concreto.
        assertTrue(Ej162MethodSecurity.esRolePresente("hasRole('ADMIN')", "ADMIN"));
    }

    @Test
    void testRetoExtra10_determinarEstrategiaMetodo() {
        // Resuelve si es denegacion por defecto o permitAll.
        assertEquals("DENY", Ej162MethodSecurity.determinarEstrategiaMetodo(""));
    }

}