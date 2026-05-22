package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej159JwtIssueTest {

    private static final String SECRETO = "01234567890123456789012345678901";

    @Test
    void claveValida() {
        assertNotNull(Ej159JwtIssue.clave(SECRETO));
    }

    @Test
    void claveCortaFalla() {
        assertThrows(IllegalArgumentException.class, () -> Ej159JwtIssue.clave("corto"));
    }

    @Test
    void emiteTokenConTresPartes() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        String t = Ej159JwtIssue.emitir(k, "ana", "ROLE_ADMIN", 1_000L, 60_000L);
        assertEquals(3, t.split("\\.").length);
    }

    @Test
    void invalidos() {
        SecretKey k = Ej159JwtIssue.clave(SECRETO);
        assertThrows(IllegalArgumentException.class,
                () -> Ej159JwtIssue.emitir(k, " ", "r", 1L, 1L));
        assertThrows(IllegalArgumentException.class,
                () -> Ej159JwtIssue.emitir(k, "ana", "r", 1L, 0L));
    }

    @Test
    void testRetoExtra01_esJwtValidoFormato() {
        // Comprueba estructura basica de 3 partes separadas por puntos (header.payload.signature).
        assertTrue(Ej159JwtIssue.esJwtValidoFormato("a.b.c"));
    }

    @Test
    void testRetoExtra02_esHeaderJwtCorrecto() {
        // Verifica presencia de algoritmo HS256 y tipo JWT.
        assertTrue(Ej159JwtIssue.esHeaderJwtCorrecto("{\"alg\":\"HS256\"}"));
    }

    @Test
    void testRetoExtra03_esExcepcionFirmaJwt() {
        // Determina si la excepcion apunta a firma corrupta o alterada.
        assertTrue(Ej159JwtIssue.esExcepcionFirmaJwt(new IllegalArgumentException("signature")));
    }

    @Test
    void testRetoExtra04_generarJwtSimple() {
        // Genera una cadena simulada JWT.
        assertTrue(Ej159JwtIssue.generarJwtSimple("u", 1000L).contains("."));
    }

    @Test
    void testRetoExtra05_esJwtExpirado() {
        // Determina si la fecha de expiracion en segundos ya paso.
        assertTrue(Ej159JwtIssue.esJwtExpirado(1000L));
    }

    @Test
    void testRetoExtra06_extraerSujeto() {
        // Obtiene el campo 'sub' del payload.
        assertEquals("ada", Ej159JwtIssue.extraerSujeto("{\"sub\":\"ada\"}"));
    }

    @Test
    void testRetoExtra07_esReceptorValido() {
        // Valida el campo de audiencia 'aud'.
        assertTrue(Ej159JwtIssue.esReceptorValido("app", "app"));
    }

    @Test
    void testRetoExtra08_extraerRoles() {
        // Extrae la lista de roles del JSON del payload.
        assertEquals(List.of("USER"), Ej159JwtIssue.extraerRoles("{\"roles\":[\"USER\"]}"));
    }

    @Test
    void testRetoExtra09_longitudFirmaCorrecta() {
        // Verifica longitud y codificacion base64url segura.
        assertTrue(Ej159JwtIssue.longitudFirmaCorrecta("signature"));
    }

    @Test
    void testRetoExtra10_esEmisorValido() {
        // Valida el emisor del token 'iss'.
        assertTrue(Ej159JwtIssue.esEmisorValido("iss", "iss"));
    }

}