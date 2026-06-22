package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static org.junit.jupiter.api.Assertions.*;

class Ej270FormValidationLiveTest {

    @Test
    void puedeEnviar() {
        assertTrue(Ej270FormValidationLive.puedeEnviar("ana", "12345678")); // clave de 8 (>7)
        assertFalse(Ej270FormValidationLive.puedeEnviar("", "12345678"));   // usuario vacío
        assertFalse(Ej270FormValidationLive.puedeEnviar("ana", "1234567")); // caso límite: clave de 7
    }

    @Test
    void mensajeValidacion() {
        assertEquals("Usuario requerido", Ej270FormValidationLive.mensajeValidacion("", "x"));
        assertEquals("Clave demasiado corta", Ej270FormValidationLive.mensajeValidacion("ana", "123"));
        assertEquals("", Ej270FormValidationLive.mensajeValidacion("ana", "12345678")); // todo OK
    }

    @Test
    void retoExtra01_noVacio() {
        assertTrue(Ej270FormValidationLive.noVacio(new SimpleStringProperty("x")));
        assertFalse(Ej270FormValidationLive.noVacio(new SimpleStringProperty("")));
    }

    @Test
    void retoExtra02_longitudMinima() {
        assertTrue(Ej270FormValidationLive.longitudMinima(new SimpleStringProperty("12345678"), 8));
        assertFalse(Ej270FormValidationLive.longitudMinima(new SimpleStringProperty("123"), 8));
    }

    @Test
    void retoExtra03_coincidenClaves() {
        assertTrue(Ej270FormValidationLive.coincidenClaves(new SimpleStringProperty("abc"), new SimpleStringProperty("abc")));
        assertFalse(Ej270FormValidationLive.coincidenClaves(new SimpleStringProperty("abc"), new SimpleStringProperty("xyz")));
    }

    @Test
    void retoExtra04_emailValido() {
        assertTrue(Ej270FormValidationLive.emailValido("ana@correo.com"));
        assertFalse(Ej270FormValidationLive.emailValido("ana@malo"));
    }

    @Test
    void retoExtra05_contadorCaracteres() {
        assertEquals(5, Ej270FormValidationLive.contadorCaracteres(new SimpleStringProperty("hola!")));
    }

    @Test
    void retoExtra06_habilitarReactivo() {
        StringProperty u = new SimpleStringProperty("");
        StringProperty c = new SimpleStringProperty("");
        BooleanBinding habil = Ej270FormValidationLive.habilitarReactivo(u, c);
        assertFalse(habil.get()); // vacío
        u.set("ana");
        c.set("12345678");
        assertTrue(habil.get()); // reactivo: sin volver a llamar al método
    }

    @Test
    void retoExtra07_mensajeDinamico() {
        StringProperty u = new SimpleStringProperty("");
        StringBinding msg = Ej270FormValidationLive.mensajeDinamico(u);
        assertEquals("Usuario requerido", msg.get());
        u.set("ana");
        assertEquals("", msg.get()); // el mensaje desaparece solo
    }

    @Test
    void retoExtra08_todosCompletos() {
        assertTrue(Ej270FormValidationLive.todosCompletos(List.of(
                new SimpleStringProperty("a"), new SimpleStringProperty("b"))));
        assertFalse(Ej270FormValidationLive.todosCompletos(List.of(
                new SimpleStringProperty("a"), new SimpleStringProperty(""))));
    }

    @Test
    void retoExtra09_forzarMayusculas() {
        assertEquals("ABC", Ej270FormValidationLive.forzarMayusculas(new SimpleStringProperty(""), "abc"));
    }

    @Test
    void retoExtra10_registroValido() {
        assertTrue(Ej270FormValidationLive.registroValido("ana", "ana@correo.com", "12345678", "12345678"));
        assertFalse(Ej270FormValidationLive.registroValido("ana", "ana@malo", "12345678", "12345678"));   // email
        assertFalse(Ej270FormValidationLive.registroValido("ana", "ana@correo.com", "12345678", "otra")); // no coinciden
        assertFalse(Ej270FormValidationLive.registroValido("ana", "ana@correo.com", "123", "123"));       // corta
    }
}
