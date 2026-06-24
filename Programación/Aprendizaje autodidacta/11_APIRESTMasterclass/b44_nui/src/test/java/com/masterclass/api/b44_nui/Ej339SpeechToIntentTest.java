package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import com.masterclass.api.b44_nui.Ej339SpeechToIntent.AccionUi;

import static org.junit.jupiter.api.Assertions.*;

class Ej339SpeechToIntentTest {

    @Test
    void aAccionUi() {
        assertEquals(Optional.of(AccionUi.ABRIR), Ej339SpeechToIntent.aAccionUi("abrir documento", 0.9, 0.5));
        assertEquals(Optional.empty(), Ej339SpeechToIntent.aAccionUi("abrir documento", 0.3, 0.5)); // caso límite: confianza baja
    }

    @Test
    void mejorCoincidencia() {
        assertEquals("guardar", Ej339SpeechToIntent.mejorCoincidencia("guardr", List.of("guardar", "buscar")));
        assertEquals("", Ej339SpeechToIntent.mejorCoincidencia("xx", List.of())); // caso límite
    }

    @Test
    void retoExtra01_esRechazo() {
        assertTrue(Ej339SpeechToIntent.esRechazo(0.3, 0.5));
        assertFalse(Ej339SpeechToIntent.esRechazo(0.7, 0.5));
    }

    @Test
    void retoExtra02_usarHistorial() {
        assertEquals("abrir", Ej339SpeechToIntent.usarHistorial("", "abrir"));
        assertEquals("cerrar", Ej339SpeechToIntent.usarHistorial("cerrar", "abrir"));
    }

    @Test
    void retoExtra03_necesitaConfirmacion() {
        assertTrue(Ej339SpeechToIntent.necesitaConfirmacion(AccionUi.BORRAR));
        assertFalse(Ej339SpeechToIntent.necesitaConfirmacion(AccionUi.ABRIR));
    }

    @Test
    void retoExtra04_distanciaEdicion() {
        assertEquals(1, Ej339SpeechToIntent.distanciaEdicion("casa", "cosa"));
        assertEquals(3, Ej339SpeechToIntent.distanciaEdicion("", "abc")); // caso límite
    }

    @Test
    void retoExtra05_umbralAdaptativo() {
        assertEquals(0.7, Ej339SpeechToIntent.umbralAdaptativo(0.5, 0.2), 1e-9);
        assertEquals(1.0, Ej339SpeechToIntent.umbralAdaptativo(0.9, 0.5), 1e-9); // caso límite: saturado
    }

    @Test
    void retoExtra06_interpretarNumero() {
        assertEquals(3, Ej339SpeechToIntent.interpretarNumero("tres"));
        assertEquals(-1, Ej339SpeechToIntent.interpretarNumero("zzz")); // caso límite
    }

    @Test
    void retoExtra07_normalizarFonetica() {
        assertEquals("ola", Ej339SpeechToIntent.normalizarFonetica("Hola"));
        assertEquals("baca", Ej339SpeechToIntent.normalizarFonetica("vaca"));
    }

    @Test
    void retoExtra08_tasaErrorPalabras() {
        assertEquals(0.0, Ej339SpeechToIntent.tasaErrorPalabras(
                List.of("abre", "el", "menu"), List.of("abre", "el", "menu")), 1e-9);
        assertEquals(0.5, Ej339SpeechToIntent.tasaErrorPalabras(
                List.of("abre", "menu"), List.of("abre", "mano")), 1e-9);
        assertEquals(0.0, Ej339SpeechToIntent.tasaErrorPalabras(List.of(), List.of("x")), 1e-9); // caso límite
    }

    @Test
    void retoExtra09_mejorDeHipotesis() {
        assertEquals("abrir",
                Ej339SpeechToIntent.mejorDeHipotesis(List.of("abrir", "aprir", "cerrar"), "abrir"));
        assertEquals("", Ej339SpeechToIntent.mejorDeHipotesis(List.of(), "abrir")); // caso límite
    }

    @Test
    void retoExtra10_registrarSinAudio() {
        assertEquals("[oculto]", Ej339SpeechToIntent.registrarSinAudio("mi password es 123"));
        assertEquals("abrir menu", Ej339SpeechToIntent.registrarSinAudio("abrir menu"));
    }
}
