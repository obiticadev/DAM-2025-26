package com.masterclass.api.b38_fxreports;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej304ExportFormatsTest {

    private static byte[] ascii(String s) {
        return s.getBytes(StandardCharsets.US_ASCII);
    }

    private static final byte[] XLSX_BYTES = {0x50, 0x4B, 0x03, 0x04, 0x14, 0x00};

    @Test
    void magicDe() {
        assertArrayEquals(new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D}, Ej304ExportFormats.magicDe("pdf"));
        assertArrayEquals(new byte[]{0x50, 0x4B, 0x03, 0x04}, Ej304ExportFormats.magicDe("xlsx"));
        assertEquals(0, Ej304ExportFormats.magicDe("csv").length); // texto: sin firma
        assertNull(Ej304ExportFormats.magicDe(null)); // caso límite
    }

    @Test
    void detectarFormato() {
        assertEquals("pdf", Ej304ExportFormats.detectarFormato(ascii("%PDF-1.7")));
        assertEquals("xlsx", Ej304ExportFormats.detectarFormato(XLSX_BYTES));
        assertEquals("desconocido", Ej304ExportFormats.detectarFormato(ascii("texto plano")));
        assertEquals("desconocido", Ej304ExportFormats.detectarFormato(null)); // caso límite
    }

    @Test
    void aCsv() {
        assertEquals("a;b\nc;d", Ej304ExportFormats.aCsv(
                List.of(new String[]{"a", "b"}, new String[]{"c", "d"})));
        assertEquals("", Ej304ExportFormats.aCsv(null)); // caso límite
    }

    @Test
    void retoExtra01_extensionDeFormato() {
        assertEquals(".pdf", Ej304ExportFormats.extensionDeFormato("PDF"));
        assertEquals("", Ej304ExportFormats.extensionDeFormato(null)); // caso límite
    }

    @Test
    void retoExtra02_mimeDe() {
        assertEquals("application/pdf", Ej304ExportFormats.mimeDe("pdf"));
        assertEquals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                Ej304ExportFormats.mimeDe("xlsx"));
        assertEquals("text/csv", Ej304ExportFormats.mimeDe("csv"));
        assertEquals("application/octet-stream", Ej304ExportFormats.mimeDe("raro")); // caso límite
    }

    @Test
    void retoExtra03_esBinario() {
        assertTrue(Ej304ExportFormats.esBinario("pdf"));
        assertTrue(Ej304ExportFormats.esBinario("xlsx"));
        assertFalse(Ej304ExportFormats.esBinario("csv")); // texto
    }

    @Test
    void retoExtra04_celdaCsv() {
        assertEquals("ana", Ej304ExportFormats.celdaCsv("ana"));
        assertEquals("\"a;b\"", Ej304ExportFormats.celdaCsv("a;b"));
        assertEquals("\"di \"\"hola\"\"\"", Ej304ExportFormats.celdaCsv("di \"hola\"")); // comillas dobladas
    }

    @Test
    void retoExtra05_filaCsv() {
        assertEquals("a;\"b;c\"", Ej304ExportFormats.filaCsv(new String[]{"a", "b;c"}));
    }

    @Test
    void retoExtra06_aCsvConCabecera() {
        assertEquals("id;nombre\n1;Ana", Ej304ExportFormats.aCsvConCabecera(
                new String[]{"id", "nombre"}, List.of(new String[]{"1", "Ana"})));
    }

    @Test
    void retoExtra07_contarColumnas() {
        assertEquals(3, Ej304ExportFormats.contarColumnas("a;b;c"));
        assertEquals(3, Ej304ExportFormats.contarColumnas("a;;b")); // celda central vacía cuenta
        assertEquals(0, Ej304ExportFormats.contarColumnas("")); // caso límite
    }

    @Test
    void retoExtra08_formatosDisponibles() {
        List<String> f = Ej304ExportFormats.formatosDisponibles();
        assertEquals(3, f.size());
        assertEquals("pdf", f.get(0));
    }

    @Test
    void retoExtra09_nombreSalida() {
        assertEquals("factura.pdf", Ej304ExportFormats.nombreSalida("factura", "pdf"));
        assertEquals("informe.csv", Ej304ExportFormats.nombreSalida(null, "csv")); // caso límite
    }

    @Test
    void retoExtra10_coincideFormato() {
        assertTrue(Ej304ExportFormats.coincideFormato(ascii("%PDF-1.7"), "pdf"));
        assertFalse(Ej304ExportFormats.coincideFormato(ascii("%PDF-1.7"), "xlsx")); // los bytes son PDF
        assertTrue(Ej304ExportFormats.coincideFormato(ascii("cualquier texto"), "csv")); // csv = texto
    }
}
