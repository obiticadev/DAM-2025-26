package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej158UserDetailsServiceTest {

    private final List<CuentaBd158> repo = List.of(
            new CuentaBd158("ana", "$2a$10$h", "ROLE_ADMIN", true, false),
            new CuentaBd158("bob", "$2a$10$h", "ROLE_USER", true, true));

    @Test
    void cargaUsuario() {
        assertTrue(Ej158UserDetailsService.loadUserByUsername(repo, "ANA").isPresent());
    }

    @Test
    void bloqueadoNoCarga() {
        assertTrue(Ej158UserDetailsService.loadUserByUsername(repo, "bob").isEmpty());
    }

    @Test
    void puedeAutenticar() {
        assertTrue(Ej158UserDetailsService.puedeAutenticar(
                new CuentaBd158("ana", "h", "ROLE_ADMIN", true, false)));
        assertFalse(Ej158UserDetailsService.puedeAutenticar(
                new CuentaBd158("x", "h", "ROLE_USER", false, false)));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej158UserDetailsService.loadUserByUsername(null, "ana"));
    }

    @Test
    void testRetoExtra01_esNombreUsuarioValido() {
        // Comprueba que no contenga espacios ni caracteres prohibidos.
        assertTrue(Ej158UserDetailsService.esNombreUsuarioValido("ada"));
    }

    @Test
    void testRetoExtra02_esUsuarioBloqueado() {
        // Comprueba si la cuenta esta marcada como bloqueada o suspendida.
        assertTrue(Ej158UserDetailsService.esUsuarioBloqueado("BLOCKED"));
    }

    @Test
    void testRetoExtra03_esExcepcionUserNotFound() {
        // Determina si es de tipo UsernameNotFoundException.
        assertTrue(Ej158UserDetailsService.esExcepcionUserNotFound(new IllegalArgumentException("notfound")));
    }

    @Test
    void testRetoExtra04_crearUserDetailsString() {
        // Genera la ficha formateada del usuario cargado.
        assertTrue(Ej158UserDetailsService.crearUserDetailsString("u", "USER").contains("USER"));
    }

    @Test
    void testRetoExtra05_esCuentaHabilitada() {
        // Determina si la cuenta es apta para loguearse.
        assertTrue(Ej158UserDetailsService.esCuentaHabilitada(true, false));
    }

    @Test
    void testRetoExtra06_extraerRolesDeString() {
        // Separa roles por comas.
        assertEquals(List.of("USER"), Ej158UserDetailsService.extraerRolesDeString("USER"));
    }

    @Test
    void testRetoExtra07_esPasswordValidaDb() {
        // Verifica que la contrasena de base de datos no sea nula ni vacia.
        assertTrue(Ej158UserDetailsService.esPasswordValidaDb("hash"));
    }

    @Test
    void testRetoExtra08_esIntentoLoginPermitido() {
        // Determina si no se supero el limite de intentos de acceso.
        assertTrue(Ej158UserDetailsService.esIntentoLoginPermitido(2, 5));
    }

    @Test
    void testRetoExtra09_generarResumenAuditoria() {
        // Genera el texto de log de auditoria de login.
        assertTrue(Ej158UserDetailsService.generarResumenAuditoria("u", true).contains("SUCCESS"));
    }

    @Test
    void testRetoExtra10_esFalloServicioUser() {
        // Determina si el error ocurrio por caida de la conexion con el backend.
        assertTrue(Ej158UserDetailsService.esFalloServicioUser(new java.io.IOException()));
    }

}