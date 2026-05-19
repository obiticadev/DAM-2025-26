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
}
