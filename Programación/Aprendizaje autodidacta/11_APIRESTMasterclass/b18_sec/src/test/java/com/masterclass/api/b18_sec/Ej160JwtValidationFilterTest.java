package com.masterclass.api.b18_sec;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Ej160JwtValidationFilterTest {

    private static final String SECRETO = "01234567890123456789012345678901";

    @Test
    void extraeBearer() {
        assertEquals(Optional.of("abc.def.ghi"),
                Ej160JwtValidationFilter.extraerBearer("Bearer abc.def.ghi"));
    }

    @Test
    void headerInvalido() {
        assertTrue(Ej160JwtValidationFilter.extraerBearer(null).isEmpty());
        assertTrue(Ej160JwtValidationFilter.extraerBearer("Basic xyz").isEmpty());
    }

    @Test
    void tokenValidoDevuelveClaims() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        String t = Ej159JwtIssue.emitir(k, "ana", "ROLE_ADMIN", 1_000L, 60_000L);
        Optional<Claims> c = Ej160JwtValidationFilter.validar(k, t, 2_000L);
        assertTrue(c.isPresent());
        assertEquals("ana", c.get().getSubject());
    }

    @Test
    void tokenCaducado() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        String t = Ej159JwtIssue.emitir(k, "ana", "ROLE_ADMIN", 1_000L, 1_000L);
        assertTrue(Ej160JwtValidationFilter.validar(k, t, 999_999L).isEmpty());
    }

    @Test
    void testRetoExtra01_esHeaderBearerValido() {
        // Verifica presencia de prefijo 'Bearer '.
        assertTrue(Ej160JwtValidationFilter.esHeaderBearerValido("Bearer token123"));
    }

    @Test
    void testRetoExtra02_extraerTokenDeHeader() {
        // Extrae el token eliminando el prefijo Bearer.
        assertEquals("token123", Ej160JwtValidationFilter.extraerTokenDeHeader("Bearer token123"));
    }

    @Test
    void testRetoExtra03_esExcepcionJwtExpirado() {
        // Determina si el error es de tipo token caducado (ExpiredJwtException).
        assertTrue(Ej160JwtValidationFilter.esExcepcionJwtExpirado(new IllegalArgumentException("expired")));
    }

    @Test
    void testRetoExtra04_esExcepcionJwtMalformado() {
        // Determina si el error apunta a token corrupto (MalformedJwtException).
        assertTrue(Ej160JwtValidationFilter.esExcepcionJwtMalformado(new IllegalArgumentException("malformed")));
    }

    @Test
    void testRetoExtra05_crearContextoAutenticacion() {
        // Genera el String de registro en el contexto de seguridad.
        assertTrue(Ej160JwtValidationFilter.crearContextoAutenticacion("u", "USER").contains("USER"));
    }

    @Test
    void testRetoExtra06_esRutaOmitidaFiltro() {
        // Indica si la ruta se salta el filtro de validacion.
        assertTrue(Ej160JwtValidationFilter.esRutaOmitidaFiltro("/assets/index.js"));
    }

    @Test
    void testRetoExtra07_esTokenNegro() {
        // Verifica si el token fue revocado y esta en la lista negra.
        assertTrue(Ej160JwtValidationFilter.esTokenNegro("tok", java.util.List.of("tok")));
    }

    @Test
    void testRetoExtra08_extraerJwtClaim() {
        // Obtiene el valor de un claim arbitrario.
        assertEquals("val", Ej160JwtValidationFilter.extraerJwtClaim("{\"k\":\"val\"}", "k"));
    }

    @Test
    void testRetoExtra09_esFalloServicioFiltro() {
        // Determina si el filtro fallo por caida de infraestructura interna.
        assertTrue(Ej160JwtValidationFilter.esFalloServicioFiltro(new java.io.IOException()));
    }

    @Test
    void testRetoExtra10_esFirmaAlgoritmoSeguro() {
        // Determina si el algoritmo de firma del token es fuerte (e.g. HS512 o RS256).
        assertTrue(Ej160JwtValidationFilter.esFirmaAlgoritmoSeguro("{\"alg\":\"HS512\"}"));
    }

}