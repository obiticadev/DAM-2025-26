package com.masterclass.api.b38_fxreports;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class Ej300JasperFillExportTest {

    private static byte[] ascii(String s) {
        return s.getBytes(StandardCharsets.US_ASCII);
    }

    @Test
    void cabeceraPdf() {
        assertArrayEquals(new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D}, Ej300JasperFillExport.cabeceraPdf());
    }

    @Test
    void esPdf() {
        assertTrue(Ej300JasperFillExport.esPdf(ascii("%PDF-1.7\nresto")));
        assertFalse(Ej300JasperFillExport.esPdf(ascii("hola mundo")));
        assertFalse(Ej300JasperFillExport.esPdf(null)); // caso límite
    }

    @Test
    void nombreCompilado() {
        assertEquals("factura.jasper", Ej300JasperFillExport.nombreCompilado("factura.jrxml"));
        assertEquals("", Ej300JasperFillExport.nombreCompilado("")); // caso límite
    }

    @Test
    void retoExtra01_extensionDe() {
        assertEquals("pdf", Ej300JasperFillExport.extensionDe("factura.PDF"));
        assertEquals("", Ej300JasperFillExport.extensionDe("sinpunto")); // caso límite
    }

    @Test
    void retoExtra02_tieneContenido() {
        assertTrue(Ej300JasperFillExport.tieneContenido(new byte[10]));
        assertFalse(Ej300JasperFillExport.tieneContenido(null));
        assertFalse(Ej300JasperFillExport.tieneContenido(new byte[0])); // caso límite
    }

    @Test
    void retoExtra03_versionPdf() {
        assertEquals("1.7", Ej300JasperFillExport.versionPdf(ascii("%PDF-1.7\nresto")));
        assertEquals("", Ej300JasperFillExport.versionPdf(ascii("no soy pdf"))); // caso límite
    }

    @Test
    void retoExtra04_tamanoKb() {
        assertEquals(2, Ej300JasperFillExport.tamanoKb(new byte[2048]));
        assertEquals(1, Ej300JasperFillExport.tamanoKb(new byte[1500])); // no llega a 2 KB
    }

    @Test
    void retoExtra05_esJasperCompilado() {
        assertTrue(Ej300JasperFillExport.esJasperCompilado("factura.jasper"));
        assertFalse(Ej300JasperFillExport.esJasperCompilado("factura.jrxml")); // caso límite
    }

    @Test
    void retoExtra06_rutaPlantilla() {
        assertEquals("reports/factura.jrxml", Ej300JasperFillExport.rutaPlantilla("factura"));
        assertEquals("", Ej300JasperFillExport.rutaPlantilla("")); // caso límite
    }

    @Test
    void retoExtra07_empiezaPor() {
        assertTrue(Ej300JasperFillExport.empiezaPor(new byte[]{1, 2, 3, 4}, new byte[]{1, 2}));
        assertFalse(Ej300JasperFillExport.empiezaPor(new byte[]{1, 2, 3, 4}, new byte[]{1, 9})); // caso límite
    }

    @Test
    void retoExtra08_cabeceraComoTexto() {
        assertEquals("%PDF-", Ej300JasperFillExport.cabeceraComoTexto());
    }

    @Test
    void retoExtra09_mismosBytes() {
        assertTrue(Ej300JasperFillExport.mismosBytes(new byte[]{1, 2, 3}, new byte[]{1, 2, 3}));
        assertFalse(Ej300JasperFillExport.mismosBytes(new byte[]{1, 2, 3}, new byte[]{1, 2})); // caso límite
    }

    @Test
    void retoExtra10_resumenExport() {
        assertEquals("factura.pdf (2 KB)", Ej300JasperFillExport.resumenExport("factura.pdf", new byte[2048]));
    }
}
