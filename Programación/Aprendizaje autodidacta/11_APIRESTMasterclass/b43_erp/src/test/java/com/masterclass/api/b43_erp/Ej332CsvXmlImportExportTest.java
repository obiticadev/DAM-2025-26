package com.masterclass.api.b43_erp;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej332CsvXmlImportExportTest {

    private static final String CSV = Ej332CsvXmlImportExport.CABECERA
            + "\nCLI-001;Acme SA;info@acme.es;ES\nCLI-002;Globex;hi@globex.com;US\n";

    @Test
    void importarClientesCsv() {
        List<ClienteErp> c = Ej332CsvXmlImportExport.importarClientesCsv(CSV);
        assertEquals(2, c.size());
        assertEquals("Acme SA", c.get(0).nombre());
        assertEquals("US", c.get(1).pais());
        assertTrue(Ej332CsvXmlImportExport.importarClientesCsv(Ej332CsvXmlImportExport.CABECERA + "\n").isEmpty()); // límite
    }

    @Test
    void exportarRoundTrip() {
        List<ClienteErp> c = Ej332CsvXmlImportExport.importarClientesCsv(CSV);
        String exportado = Ej332CsvXmlImportExport.exportarClientesCsv(c);
        assertEquals(c, Ej332CsvXmlImportExport.importarClientesCsv(exportado));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej332CsvXmlImportExport.importarClientesCsv(null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej332CsvXmlImportExport.importarClientesCsv(Ej332CsvXmlImportExport.CABECERA + "\nx;y\n"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej332CsvXmlImportExport.exportarClientesCsv(null));
    }

    @Test
    void testRetoExtra01_cabeceraCsvValida() {
        assertTrue(Ej332CsvXmlImportExport.cabeceraCsvValida(CSV));
        assertFalse(Ej332CsvXmlImportExport.cabeceraCsvValida("otra;cosa\n"));
    }

    @Test
    void testRetoExtra02_contarRegistrosCsv() {
        assertEquals(2, Ej332CsvXmlImportExport.contarRegistrosCsv(CSV));
        assertEquals(0, Ej332CsvXmlImportExport.contarRegistrosCsv(Ej332CsvXmlImportExport.CABECERA + "\n"));
    }

    @Test
    void testRetoExtra03_emailValido() {
        assertTrue(Ej332CsvXmlImportExport.emailValido("info@acme.es"));
        assertFalse(Ej332CsvXmlImportExport.emailValido("acme.es"));
    }

    @Test
    void testRetoExtra04_escaparXml() {
        assertEquals("a &amp; b &lt;c&gt;", Ej332CsvXmlImportExport.escaparXml("a & b <c>"));
    }

    @Test
    void testRetoExtra05_clienteAXml() {
        ClienteErp c = new ClienteErp("CLI-9", "Tom & Jerry", "t@j.com", "ES");
        String xml = Ej332CsvXmlImportExport.clienteAXml(c);
        assertTrue(xml.contains("Tom &amp; Jerry"));
        assertTrue(xml.startsWith("<cliente"));
    }

    @Test
    void testRetoExtra06_extraerTag() {
        assertEquals("ES", Ej332CsvXmlImportExport.extraerTag("<pais>ES</pais>", "pais"));
        assertEquals("", Ej332CsvXmlImportExport.extraerTag("<x>1</x>", "pais"));
    }

    @Test
    void testRetoExtra07_desescaparXml() {
        String s = "a & b <c>";
        assertEquals(s, Ej332CsvXmlImportExport.desescaparXml(Ej332CsvXmlImportExport.escaparXml(s)));
    }

    @Test
    void testRetoExtra08_deduplicarPorId() {
        List<ClienteErp> in = List.of(
                new ClienteErp("CLI-1", "A", "a@a.com", "ES"),
                new ClienteErp("CLI-1", "B", "b@b.com", "ES"),
                new ClienteErp("CLI-2", "C", "c@c.com", "US"));
        List<ClienteErp> out = Ej332CsvXmlImportExport.deduplicarPorId(in);
        assertEquals(2, out.size());
        assertEquals("A", out.get(0).nombre());
    }

    @Test
    void testRetoExtra09_detectarFormato() {
        assertEquals("xml", Ej332CsvXmlImportExport.detectarFormato("  <clientes></clientes>"));
        assertEquals("csv", Ej332CsvXmlImportExport.detectarFormato("a;b;c\n1;2;3"));
        assertEquals("desconocido", Ej332CsvXmlImportExport.detectarFormato("   "));
    }

    @Test
    void testRetoExtra10_clientesAXml() {
        List<ClienteErp> in = List.of(
                new ClienteErp("CLI-1", "A", "a@a.com", "ES"),
                new ClienteErp("CLI-2", "C", "c@c.com", "US"));
        String xml = Ej332CsvXmlImportExport.clientesAXml(in);
        assertTrue(xml.startsWith("<clientes>"));
        assertEquals(2, xml.split("<cliente ", -1).length - 1);
    }
}
