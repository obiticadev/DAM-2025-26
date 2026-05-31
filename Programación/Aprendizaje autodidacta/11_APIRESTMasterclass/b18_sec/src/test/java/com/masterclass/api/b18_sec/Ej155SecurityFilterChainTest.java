package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej155SecurityFilterChainTest {

    @Test
    void rutasPublicas() {
        assertTrue(Ej155SecurityFilterChain.esPublica("GET", "/public/health"));
        assertTrue(Ej155SecurityFilterChain.esPublica("POST", "/auth/login"));
        assertTrue(Ej155SecurityFilterChain.esPublica("POST", "/auth/refresh"));
    }

    @Test
    void rutasPrivadas() {
        assertFalse(Ej155SecurityFilterChain.esPublica("GET", "/api/users"));
        assertFalse(Ej155SecurityFilterChain.esPublica("POST", "/public/health"));
    }

    @Test
    void status401() {
        assertTrue(Ej155SecurityFilterChain.requiere401("GET", "/api/users", false));
        assertFalse(Ej155SecurityFilterChain.requiere401("GET", "/api/users", true));
        assertFalse(Ej155SecurityFilterChain.requiere401("GET", "/public/x", false));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej155SecurityFilterChain.esPublica(null, "/x"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej155SecurityFilterChain.esPublica("GET", " "));
    }

    @Test
    void testRetoExtra01_esRutaPublica() {
        // Comprueba si la ruta debe estar abierta de forma predeterminada (ej. login, swagger).
        assertTrue(Ej155SecurityFilterChain.esRutaPublica("/login"));
    }

    @Test
    void testRetoExtra02_esFiltroCorsActivo() {
        // Verifica la presencia de encabezados de origen cruzado en peticiones.
        assertTrue(Ej155SecurityFilterChain.esFiltroCorsActivo("Origin"));
    }

    @Test
    void testRetoExtra03_esCabeceraSegura() {
        // Determina si es un encabezado standard de proteccion (HSTS, XSS).
        assertTrue(Ej155SecurityFilterChain.esCabeceraSegura("X-Frame-Options", "DENY"));
    }

    @Test
    void testRetoExtra04_esProtocoloSeguro() {
        // Verifica que solo se acepte HTTPS.
        assertTrue(Ej155SecurityFilterChain.esProtocoloSeguro("https"));
    }

    @Test
    void testRetoExtra05_esUsuarioAnonimo() {
        // Determina si el usuario no esta autenticado.
        assertTrue(Ej155SecurityFilterChain.esUsuarioAnonimo("anonymousUser"));
    }

    @Test
    void testRetoExtra06_extraerRolDeAutoridad() {
        // Normaliza el rol removiendo el prefijo ROLE_.
        assertEquals("ADMIN", Ej155SecurityFilterChain.extraerRolDeAutoridad("ROLE_ADMIN"));
    }

    @Test
    void testRetoExtra07_esExcepcionDeSeguridadFiltro() {
        // Determina si la excepcion proviene del stack de autenticacion de Spring Security.
        assertTrue(Ej155SecurityFilterChain.esExcepcionDeSeguridadFiltro(new SecurityException()));
    }

    @Test
    void testRetoExtra08_esRutaExclusivaAdmin() {
        // Identifica si la ruta requiere credenciales de administracion.
        assertTrue(Ej155SecurityFilterChain.esRutaExclusivaAdmin("/admin/console"));
    }

    @Test
    void testRetoExtra09_generarMensajeAccesoDenegado() {
        // Genera el JSON de error de acceso prohibido.
        assertTrue(Ej155SecurityFilterChain.generarMensajeAccesoDenegado("/api").contains("/api"));
    }

    @Test
    void testRetoExtra10_esFiltroHabilitado() {
        // Comprueba si un filtro de seguridad concreto esta activo.
        assertTrue(Ej155SecurityFilterChain.esFiltroHabilitado("CsrfFilter", "CsrfFilter,CorsFilter"));
    }

}