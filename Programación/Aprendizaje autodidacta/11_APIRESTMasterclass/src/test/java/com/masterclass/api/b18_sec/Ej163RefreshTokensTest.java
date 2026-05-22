package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej163RefreshTokensTest {

    @Test
    void verificaValido() {
        Map<String, RefreshInfo163> db = new HashMap<>();
        db.put("rt1", new RefreshInfo163("ana", 10_000L, false));
        assertTrue(Ej163RefreshTokens.verificar(db, "rt1", 5_000L).isPresent());
    }

    @Test
    void revocadoOCaducado() {
        Map<String, RefreshInfo163> db = new HashMap<>();
        db.put("rev", new RefreshInfo163("ana", 10_000L, true));
        db.put("exp", new RefreshInfo163("ana", 1_000L, false));
        assertTrue(Ej163RefreshTokens.verificar(db, "rev", 5_000L).isEmpty());
        assertTrue(Ej163RefreshTokens.verificar(db, "exp", 5_000L).isEmpty());
    }

    @Test
    void rotacionRevocaAntiguo() {
        Map<String, RefreshInfo163> db = new HashMap<>();
        db.put("old", new RefreshInfo163("ana", 10_000L, false));
        Ej163RefreshTokens.rotar(db, "old", "new", "ana", 20_000L);
        assertTrue(Ej163RefreshTokens.verificar(db, "old", 5_000L).isEmpty());
        assertTrue(Ej163RefreshTokens.verificar(db, "new", 5_000L).isPresent());
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej163RefreshTokens.verificar(null, "x", 1L));
    }

    @Test
    void testRetoExtra01_esRefreshTokenValido() {
        // Comprueba longitud y aleatoriedad segura (e.g. UUID completo sin guiones).
        assertTrue(Ej163RefreshTokens.esRefreshTokenValido("uuid"));
    }

    @Test
    void testRetoExtra02_crearRefreshTokenNuevo() {
        // Genera un token de refresco aleatorio fuerte.
        assertNotNull(Ej163RefreshTokens.crearRefreshTokenNuevo());
    }

    @Test
    void testRetoExtra03_esTokenExpirado() {
        // Determina si el token de refresco ya vencio.
        assertTrue(Ej163RefreshTokens.esTokenExpirado(java.time.Instant.now().minusSeconds(1)));
    }

    @Test
    void testRetoExtra04_esExcepcionDeRefresco() {
        // Determina si la excepcion apunta a token de refresco inexistente o caducado.
        assertTrue(Ej163RefreshTokens.esExcepcionDeRefresco(new IllegalArgumentException("refresh")));
    }

    @Test
    void testRetoExtra05_generarRespuestaToken() {
        // Genera el JSON standard de retorno con ambos tokens.
        assertTrue(Ej163RefreshTokens.generarRespuestaToken("a", "r").contains("access"));
    }

    @Test
    void testRetoExtra06_esIntentoRefrescoPermitido() {
        // Determina si no se supero el limite de intentos fallidos de refresco.
        assertTrue(Ej163RefreshTokens.esIntentoRefrescoPermitido(1, 3));
    }

    @Test
    void testRetoExtra07_esIpRefrescoSegura() {
        // Valida que la peticion de refresco provenga de la misma IP original para evitar secuestros.
        assertTrue(Ej163RefreshTokens.esIpRefrescoSegura("1.1.1.1", "1.1.1.1"));
    }

    @Test
    void testRetoExtra08_extraerTiempoExpiracion() {
        // Obtiene los segundos de vida del refresh token.
        assertEquals(3600L, Ej163RefreshTokens.extraerTiempoExpiracion("{\"exp\":3600}"));
    }

    @Test
    void testRetoExtra09_esFalloBaseDatosRefresco() {
        // Determina si el error ocurrio al consultar el almacen de tokens en persistencia.
        assertTrue(Ej163RefreshTokens.esFalloBaseDatosRefresco(new java.sql.SQLException()));
    }

    @Test
    void testRetoExtra10_determinarEstrategiaRefresco() {
        // Resuelve la accion correctiva (ROTO o REUSE).
        assertEquals("ROTO", Ej163RefreshTokens.determinarEstrategiaRefresco(30));
    }

}