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
}
