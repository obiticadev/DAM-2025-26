package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 30, unit = TimeUnit.SECONDS)
class Ej242PasswordHashingSaltTest {

    @Test
    void hashConSaltYVerificacion() {
        String almacenado = Ej242PasswordHashingSalt.hashConSalt("secreto");
        assertTrue(Ej242PasswordHashingSalt.passwordCoincide("secreto", almacenado));
    }

    @Test
    void rechazaIncorrecta() {
        String almacenado = Ej242PasswordHashingSalt.hashConSalt("secreto");
        assertFalse(Ej242PasswordHashingSalt.passwordCoincide("incorrecta", almacenado));
    }

    @Test
    void retoExtra01_mismaPasswordDistintoHash() {
        assertTrue(Ej242PasswordHashingSalt.mismaPasswordDistintoHash());
    }

    @Test
    void retoExtra02_saltAleatorioDe16Bytes() {
        assertEquals(16, Ej242PasswordHashingSalt.saltAleatorioDe16Bytes());
    }

    @Test
    void retoExtra03_pbkdf2DevuelveTreintaYDosBytes() {
        assertEquals(32, Ej242PasswordHashingSalt.pbkdf2DevuelveTreintaYDosBytes());
    }

    @Test
    void retoExtra04_masIteracionesCambiaElHash() {
        assertTrue(Ej242PasswordHashingSalt.masIteracionesCambiaElHash());
    }

    @Test
    void retoExtra05_aceptaPasswordCorrecta() {
        assertTrue(Ej242PasswordHashingSalt.aceptaPasswordCorrecta());
    }

    @Test
    void retoExtra06_rechazaPasswordIncorrecta() {
        assertTrue(Ej242PasswordHashingSalt.rechazaPasswordIncorrecta());
    }

    @Test
    void retoExtra07_saltDistintoHashDistinto() {
        assertTrue(Ej242PasswordHashingSalt.saltDistintoHashDistinto());
    }

    @Test
    void retoExtra08_pbkdf2DeterministaConMismosParametros() {
        assertTrue(Ej242PasswordHashingSalt.pbkdf2DeterministaConMismosParametros());
    }

    @Test
    void retoExtra09_formatoAlmacenadoTieneDosPartes() {
        assertEquals(2, Ej242PasswordHashingSalt.formatoAlmacenadoTieneDosPartes());
    }

    @Test
    void retoExtra10_saltRoundTripBase64() {
        assertTrue(Ej242PasswordHashingSalt.saltRoundTripBase64());
    }
}
