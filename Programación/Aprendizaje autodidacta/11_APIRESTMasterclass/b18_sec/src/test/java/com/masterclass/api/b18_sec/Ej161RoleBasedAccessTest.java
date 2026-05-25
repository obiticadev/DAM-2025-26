package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej161RoleBasedAccessTest {

    @Test
    void adminAccedeAdmin() {
        assertTrue(Ej161RoleBasedAccess.permitido("GET", "/admin/users", Set.of("ROLE_ADMIN")));
    }

    @Test
    void userNoAccedeAdmin() {
        assertFalse(Ej161RoleBasedAccess.permitido("GET", "/admin/users", Set.of("ROLE_USER")));
    }

    @Test
    void userAccedeApi() {
        assertTrue(Ej161RoleBasedAccess.permitido("GET", "/api/x", Set.of("ROLE_USER")));
    }

    @Test
    void status() {
        assertEquals(401, Ej161RoleBasedAccess.statusHttp(false, false));
        assertEquals(403, Ej161RoleBasedAccess.statusHttp(true, false));
        assertEquals(200, Ej161RoleBasedAccess.statusHttp(true, true));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej161RoleBasedAccess.permitido("GET", "/api/x", null));
    }

    @Test
    void testRetoExtra01_esAccesoPermitidoRol() {
        // Determina si el rol del usuario supera o iguala el nivel de acceso requerido.
        assertTrue(Ej161RoleBasedAccess.esAccesoPermitidoRol("ADMIN", "USER"));
    }

    @Test
    void testRetoExtra02_esRolPrefijoCorrecto() {
        // Verifica si la cadena empieza por el prefijo standard 'ROLE_'.
        assertTrue(Ej161RoleBasedAccess.esRolPrefijoCorrecto("ROLE_USER"));
    }

    @Test
    void testRetoExtra03_esJerarquiaValida() {
        // Valida la sintaxis de la jerarquia de roles.
        assertTrue(Ej161RoleBasedAccess.esJerarquiaValida("ADMIN > USER"));
    }

    @Test
    void testRetoExtra04_tieneJerarquiaMayor() {
        // Determina si r1 tiene mayor nivel que r2.
        assertTrue(Ej161RoleBasedAccess.tieneJerarquiaMayor("ADMIN", "USER"));
    }

    @Test
    void testRetoExtra05_esExcepcionDeDenegacion() {
        // Determina si la excepcion es de tipo AccessDeniedException.
        assertTrue(Ej161RoleBasedAccess.esExcepcionDeDenegacion(new SecurityException("Access denied")));
    }

    @Test
    void testRetoExtra06_crearJsonAccesoProhibido() {
        // Genera el JSON de error 403.
        assertTrue(Ej161RoleBasedAccess.crearJsonAccesoProhibido("r", "msg").contains("msg"));
    }

    @Test
    void testRetoExtra07_esRutaSoloAdmin() {
        // Indica si la ruta esta reservada exclusivamente a administradores.
        assertTrue(Ej161RoleBasedAccess.esRutaSoloAdmin("/api/admin/db"));
    }

    @Test
    void testRetoExtra08_esGrupoValido() {
        // Comprueba sintaxis de grupos organizativos en el rol.
        assertTrue(Ej161RoleBasedAccess.esGrupoValido("GRP_ADMIN"));
    }

    @Test
    void testRetoExtra09_esUsuarioValidoRol() {
        // Determina correspondencia minima.
        assertTrue(Ej161RoleBasedAccess.esUsuarioValidoRol("ada", "ADMIN"));
    }

    @Test
    void testRetoExtra10_formatearLogAcceso() {
        // Genera log de auditoria de accesos denegados/permitidos.
        assertTrue(Ej161RoleBasedAccess.formatearLogAcceso("u", "p", true).contains("ALLOWED"));
    }

}