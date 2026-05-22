package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej173SecurityTestingTest {

    @Test
    void sinToken401() {
        assertEquals(401,
                Ej173SecurityTesting.statusEndpointProtegido(false, Set.of(), "ROLE_ADMIN"));
    }

    @Test
    void sinRol403() {
        assertEquals(403,
                Ej173SecurityTesting.statusEndpointProtegido(true, Set.of("ROLE_USER"), "ROLE_ADMIN"));
    }

    @Test
    void conRol200() {
        assertEquals(200,
                Ej173SecurityTesting.statusEndpointProtegido(true, Set.of("ROLE_ADMIN"), "ROLE_ADMIN"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej173SecurityTesting.statusEndpointProtegido(true, null, "ROLE_ADMIN"));
    }

    @Test
    void testRetoExtra01_esRolAdmin() {
        // Valida rol Admin.
        assertTrue(Ej173SecurityTesting.esRolAdmin("ROLE_ADMIN"));
    }

    @Test
    void testRetoExtra02_esRolUser() {
        // Valida rol User.
        assertTrue(Ej173SecurityTesting.esRolUser("ROLE_USER"));
    }

    @Test
    void testRetoExtra03_crearSetRoles() {
        // Crea un set con dos roles.
        assertEquals(2, Ej173SecurityTesting.crearSetRoles("A", "B").size());
    }

    @Test
    void testRetoExtra04_esTokenVacio() {
        // Valida si el token esta ausente.
        assertTrue(Ej173SecurityTesting.esTokenVacio(""));
    }

    @Test
    void testRetoExtra05_contieneAlgunRol() {
        // Verifica si se cumple algun rol.
        assertTrue(Ej173SecurityTesting.contieneAlgunRol(java.util.Set.of("A"), java.util.Set.of("A", "B")));
    }

    @Test
    void testRetoExtra06_esAutenticado() {
        // Comprueba si hay token.
        assertTrue(Ej173SecurityTesting.esAutenticado(true));
    }

    @Test
    void testRetoExtra07_esAdmin() {
        // Valida si el usuario es Admin.
        assertTrue(Ej173SecurityTesting.esAdmin(java.util.Set.of("ROLE_ADMIN")));
    }

    @Test
    void testRetoExtra08_tienePermiso() {
        // Comprueba autenticacion y rol.
        assertTrue(Ej173SecurityTesting.tienePermiso(true, java.util.Set.of("ROLE_USER"), "ROLE_USER"));
    }

    @Test
    void testRetoExtra09_esAnonimo() {
        // Comprueba si es anonimo.
        assertTrue(Ej173SecurityTesting.esAnonimo(false));
    }

    @Test
    void testRetoExtra10_codigoAutorizado() {
        // Codigo HTTP de exito.
        assertEquals(200, Ej173SecurityTesting.codigoAutorizado());
    }

}