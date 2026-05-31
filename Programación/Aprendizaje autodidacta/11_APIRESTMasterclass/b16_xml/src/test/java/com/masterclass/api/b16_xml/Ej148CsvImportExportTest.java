package com.masterclass.api.b16_xml;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej148CsvImportExportTest {

    private static final String CSV = "id;nombre;edad\n1;Ada;36\n2;Alan;41\n";

    @Test
    void importaFilas() {
        List<Persona148> p = Ej148CsvImportExport.importar(CSV);
        assertEquals(2, p.size());
        assertEquals("Ada", p.get(0).nombre());
        assertEquals(41, p.get(1).edad());
    }

    @Test
    void roundTripEstable() {
        List<Persona148> p = Ej148CsvImportExport.importar(CSV);
        String exportado = Ej148CsvImportExport.exportar(p);
        assertEquals(p, Ej148CsvImportExport.importar(exportado));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej148CsvImportExport.importar(null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej148CsvImportExport.importar("id;nombre;edad\nx;y\n"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej148CsvImportExport.exportar(null));
    }

    @Test
    void testRetoExtra01_esCsvValidoFila() {
        // Determina si una linea de texto tiene formato CSV correcto.
        assertTrue(Ej148CsvImportExport.esCsvValidoFila("a,b,c"));
    }

    @Test
    void testRetoExtra02_extraerCamposCsv() {
        // Separa campos por comas respetando comillas simples.
        assertEquals(List.of("a", "b"), Ej148CsvImportExport.extraerCamposCsv("a,b"));
    }

    @Test
    void testRetoExtra03_esFilaCabecera() {
        // Comprueba si la fila representa las columnas.
        assertTrue(Ej148CsvImportExport.esFilaCabecera("id,name,email", "id"));
    }

    @Test
    void testRetoExtra04_combinarFilaCsv() {
        // Genera la linea CSV uniendo los elementos.
        assertEquals("a,b", Ej148CsvImportExport.combinarFilaCsv(List.of("a", "b")));
    }

    @Test
    void testRetoExtra05_esDatoNumerico() {
        // Determina si un valor del campo CSV es convertible a double.
        assertTrue(Ej148CsvImportExport.esDatoNumerico("12.34"));
    }

    @Test
    void testRetoExtra06_esFechaCsvValida() {
        // Verifica formato ISO de fecha en campo de importacion.
        assertTrue(Ej148CsvImportExport.esFechaCsvValida("2026-05-22"));
    }

    @Test
    void testRetoExtra07_escaparComasCsv() {
        // Sanea cadenas añadiendo dobles comillas si contienen comas.
        assertEquals("\"a,b\"", Ej148CsvImportExport.escaparComasCsv("a,b"));
    }

    @Test
    void testRetoExtra08_eliminarComillasCsv() {
        // Limpia las dobles comillas envolventes del valor.
        assertEquals("a", Ej148CsvImportExport.eliminarComillasCsv("\"a\""));
    }

    @Test
    void testRetoExtra09_esExcepcionDeImportacion() {
        // Evalua si el error proviene del procesador de ficheros.
        assertTrue(Ej148CsvImportExport.esExcepcionDeImportacion(new java.io.IOException()));
    }

    @Test
    void testRetoExtra10_formatearLogCsv() {
        // Crea la linea descriptiva de error de importacion.
        assertTrue(Ej148CsvImportExport.formatearLogCsv(1, "err").contains("fila"));
    }

}