package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import static org.junit.jupiter.api.Assertions.*;

class Ej269ConvertersTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar(); // el reto 8 construye un DatePicker (necesita toolkit)
    }

    @Test
    void fechaATexto() {
        assertEquals("22/06/2024", Ej269Converters.fechaATexto(LocalDate.of(2024, 6, 22)));
        assertEquals("05/01/2024", Ej269Converters.fechaATexto(LocalDate.of(2024, 1, 5))); // caso límite: ceros a la izquierda
    }

    @Test
    void textoAFecha() {
        assertEquals(LocalDate.of(2024, 6, 22), Ej269Converters.textoAFecha("22/06/2024"));
    }

    @Test
    void retoExtra01_conversorFecha() {
        StringConverter<LocalDate> c = Ej269Converters.conversorFecha();
        LocalDate d = LocalDate.of(2030, 12, 31);
        assertEquals("31/12/2030", c.toString(d));
        assertEquals(d, c.fromString("31/12/2030"));
    }

    @Test
    void retoExtra02_numeroATexto() {
        assertEquals("3.14", Ej269Converters.numeroATexto(3.14));
    }

    @Test
    void retoExtra03_textoANumero() {
        assertEquals(2.5, Ej269Converters.textoANumero("2.5"));
    }

    @Test
    void retoExtra04_enteroDesdeTexto() {
        assertEquals(42, Ej269Converters.enteroDesdeTexto("42"));
    }

    @Test
    void retoExtra05_idaYVuelta() {
        assertTrue(Ej269Converters.idaYVuelta(Ej269Converters.conversorFecha(), LocalDate.of(2024, 6, 22)));
    }

    @Test
    void retoExtra06_conversorBooleano() {
        StringConverter<Boolean> c = Ej269Converters.conversorBooleano();
        assertEquals("Sí", c.toString(true));
        assertEquals("No", c.toString(false));
        assertTrue(c.fromString("Sí"));
    }

    @Test
    void retoExtra07_conversorMayusculas() {
        StringConverter<String> c = Ej269Converters.conversorMayusculas();
        assertEquals("HOLA", c.toString("Hola"));
        assertEquals("hola", c.fromString("HOLA"));
    }

    @Test
    void retoExtra08_aplicarADatePicker() {
        DatePicker dp = new DatePicker();
        String texto = Ej269Converters.aplicarADatePicker(dp, Ej269Converters.conversorFecha(), LocalDate.of(2024, 6, 22));
        assertEquals("22/06/2024", texto);
    }

    @Test
    void retoExtra09_parseSeguro() {
        // texto inválido para un conversor de fecha -> devuelve el por defecto
        LocalDate def = LocalDate.of(2000, 1, 1);
        assertEquals(def, Ej269Converters.parseSeguro(Ej269Converters.conversorFecha(), "no-es-fecha", def));
    }

    @Test
    void retoExtra10_conversorProducto() {
        StringConverter<Ej269Converters.Producto> c = Ej269Converters.conversorProducto();
        Ej269Converters.Producto cafe = new Ej269Converters.Producto("Café", 1.5);
        assertEquals("Café|1.5", c.toString(cafe));
        assertEquals(cafe, c.fromString("Café|1.5"));
    }
}
