package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej214FormatConversionTest {

    @Test
    void textoABinarioYVuelta() {
        assertEquals("hola", Ej214FormatConversion.textoABinarioYVuelta("hola"));
    }

    @Test
    void propertiesRoundTrip() {
        assertEquals("ana", Ej214FormatConversion.propertiesRoundTrip("nombre", "ana"));
    }

    @Test
    void retoExtra01_numeroDeBytesUtf8() {
        assertEquals(2, Ej214FormatConversion.numeroDeBytesUtf8("ñ"));
    }

    @Test
    void retoExtra02_propertiesACsv() {
        assertEquals(2, Ej214FormatConversion.propertiesACsv());
    }

    @Test
    void retoExtra03_csvAProperties() {
        assertEquals("1", Ej214FormatConversion.csvAProperties());
    }

    @Test
    void retoExtra04_intABytesYVuelta() {
        assertEquals(123456, Ej214FormatConversion.intABytesYVuelta(123456));
    }

    @Test
    void retoExtra05_base64RoundTrip() {
        assertEquals("café ñ", Ej214FormatConversion.base64RoundTrip("café ñ"));
    }

    @Test
    void retoExtra06_csvParsearCampos() {
        assertEquals(3, Ej214FormatConversion.csvParsearCampos("uno,dos,tres"));
    }

    @Test
    void retoExtra07_csvJoin() {
        assertEquals("a,b,c", Ej214FormatConversion.csvJoin(List.of("a", "b", "c")));
    }

    @Test
    void retoExtra08_hexRoundTrip() {
        assertArrayEquals(new byte[]{1, 2, 3, -1}, Ej214FormatConversion.hexRoundTrip(new byte[]{1, 2, 3, -1}));
    }

    @Test
    void retoExtra09_propertiesListarClaves() {
        assertEquals(2, Ej214FormatConversion.propertiesListarClaves());
    }

    @Test
    void retoExtra10_propertiesValorPorDefecto() {
        assertEquals("N/A", Ej214FormatConversion.propertiesValorPorDefecto());
    }

    @Test
    void retoExtra11_doubleABytesYVuelta() {
        assertEquals(2.71828, Ej214FormatConversion.doubleABytesYVuelta(2.71828), 0.0);
    }

    @Test
    void retoExtra12_textoALineasYVuelta() {
        assertEquals(4, Ej214FormatConversion.textoALineasYVuelta("a\nb\nc\nd"));
    }
}
