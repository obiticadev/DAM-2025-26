package com.masterclass.api.b36_fxstyle;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej292InternationalizationTest {

    private static final Locale ES = Locale.forLanguageTag("es");
    private static final Locale EN = Locale.forLanguageTag("en");

    @Test
    void traducir() {
        assertEquals("Guardar", Ej292Internationalization.traducir("boton.guardar", ES));
        assertEquals("Save", Ej292Internationalization.traducir("boton.guardar", EN));
        assertEquals("", Ej292Internationalization.traducir("no.existe", ES)); // caso límite
    }

    @Test
    void traducirConParametros() {
        assertEquals("Hola, Ana", Ej292Internationalization.traducirConParametros("saludo", ES, "Ana"));
        assertEquals("Hello, Ana", Ej292Internationalization.traducirConParametros("saludo", EN, "Ana"));
    }

    @Test
    void retoExtra01_localeDe() {
        assertEquals("en", Ej292Internationalization.localeDe("en").getLanguage());
        assertEquals("es", Ej292Internationalization.localeDe("es").getLanguage());
    }

    @Test
    void retoExtra02_claveExiste() {
        assertTrue(Ej292Internationalization.claveExiste("boton.guardar", ES));
        assertFalse(Ej292Internationalization.claveExiste("no.existe", ES));
    }

    @Test
    void retoExtra03_traducirConFallback() {
        assertEquals("—", Ej292Internationalization.traducirConFallback("no.existe", ES, "—"));
        assertEquals("Guardar", Ej292Internationalization.traducirConFallback("boton.guardar", ES, "—"));
    }

    @Test
    void retoExtra04_formatearNumero() {
        assertEquals("1.234,5", Ej292Internationalization.formatearNumero(1234.5, ES));
        assertEquals("1,234.5", Ej292Internationalization.formatearNumero(1234.5, EN));
    }

    @Test
    void retoExtra05_codigoMoneda() {
        assertEquals("EUR", Ej292Internationalization.codigoMoneda("es-ES"));
        assertEquals("USD", Ej292Internationalization.codigoMoneda("en-US"));
    }

    @Test
    void retoExtra06_nombreMes() {
        assertEquals("enero", Ej292Internationalization.nombreMes(1, ES));
        assertEquals("January", Ej292Internationalization.nombreMes(1, EN));
    }

    @Test
    void retoExtra07_idiomasDisponibles() {
        assertEquals(List.of("es", "en"), Ej292Internationalization.idiomasDisponibles());
    }

    @Test
    void retoExtra08_traducirCantidad() {
        assertEquals("3 clientes", Ej292Internationalization.traducirCantidad(3, ES));
        assertEquals("3 customers", Ej292Internationalization.traducirCantidad(3, EN));
    }

    @Test
    void retoExtra09_cambiarIdioma() {
        assertEquals("en", Ej292Internationalization.cambiarIdioma(ES).getLanguage());
        assertEquals("es", Ej292Internationalization.cambiarIdioma(EN).getLanguage());
    }

    @Test
    void retoExtra10_traducirPantalla() {
        assertEquals(Map.of("boton.guardar", "Guardar", "boton.cancelar", "Cancelar"),
                Ej292Internationalization.traducirPantalla(List.of("boton.guardar", "boton.cancelar"), ES));
    }
}
