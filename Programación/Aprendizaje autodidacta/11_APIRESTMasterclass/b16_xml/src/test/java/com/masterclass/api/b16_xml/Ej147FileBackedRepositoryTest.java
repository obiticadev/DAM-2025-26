package com.masterclass.api.b16_xml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Ej147FileBackedRepositoryTest {

    @Test
    void guardaYRecupera(@TempDir Path dir) {
        Ej147FileBackedRepository repo =
                new Ej147FileBackedRepository(dir.resolve("notas.txt"));
        repo.save(1L, "hola");
        repo.save(2L, "mundo");
        assertEquals(Optional.of("hola"), repo.findById(1L));
        assertEquals(2, repo.findAll().size());
    }

    @Test
    void upsertReemplaza(@TempDir Path dir) {
        Ej147FileBackedRepository repo =
                new Ej147FileBackedRepository(dir.resolve("notas.txt"));
        repo.save(1L, "v1");
        repo.save(1L, "v2");
        assertEquals(Optional.of("v2"), repo.findById(1L));
        assertEquals(1, repo.findAll().size());
    }

    @Test
    void invalidos(@TempDir Path dir) {
        Ej147FileBackedRepository repo =
                new Ej147FileBackedRepository(dir.resolve("notas.txt"));
        assertThrows(IllegalArgumentException.class, () -> repo.save(0L, "x"));
        assertThrows(IllegalArgumentException.class, () -> repo.save(1L, "a;b"));
        assertEquals(Optional.empty(), repo.findById(99L));
    }

    @Test
    void testRetoExtra01_validarRutaArchivo() {
        // Comprueba que el archivo este en un directorio seguro y tenga extension valida.
        assertTrue(Ej147FileBackedRepository.validarRutaArchivo("datos.xml"));
    }

    @Test
    void testRetoExtra02_esArchivoEditable() {
        // Verifica que el archivo no sea de solo lectura.
        assertTrue(Ej147FileBackedRepository.esArchivoEditable("datos.xml"));
    }

    @Test
    void testRetoExtra03_generarBackupNombre() {
        // Genera el nombre de respaldo añadiendo sufijo temporal.
        assertTrue(Ej147FileBackedRepository.generarBackupNombre("datos.xml").contains(".bak"));
    }

    @Test
    void testRetoExtra04_esFormatoXmlCorrecto() {
        // Realiza validacion basica de etiquetas XML en el String.
        assertTrue(Ej147FileBackedRepository.esFormatoXmlCorrecto("<data></data>"));
    }

    @Test
    void testRetoExtra05_calcularCheksumManual() {
        // Genera un hash rapido de seguridad sobre el contenido.
        assertNotNull(Ej147FileBackedRepository.calcularCheksumManual("data"));
    }

    @Test
    void testRetoExtra06_esTamanoPermitido() {
        // Comprueba limites seguros de tamano para evitar desbordes de memoria.
        assertTrue(Ej147FileBackedRepository.esTamanoPermitido(1024L));
    }

    @Test
    void testRetoExtra07_esXmlValidoEstructura() {
        // Verifica si abre y cierra con la misma etiqueta raiz.
        assertTrue(Ej147FileBackedRepository.esXmlValidoEstructura("<root></root>"));
    }

    @Test
    void testRetoExtra08_crearElementoXmlSimple() {
        // Genera una representacion XML de linea.
        assertEquals("<tag>val</tag>", Ej147FileBackedRepository.crearElementoXmlSimple("tag", "val"));
    }

    @Test
    void testRetoExtra09_extraerContenidoEtiqueta() {
        // Obtiene el texto interior de un nodo simple.
        assertEquals("val", Ej147FileBackedRepository.extraerContenidoEtiqueta("<tag>val</tag>", "tag"));
    }

    @Test
    void testRetoExtra10_esExcepcionDePersistenciaXml() {
        // Determina si el error es de parseo o binding XML.
        assertTrue(Ej147FileBackedRepository.esExcepcionDePersistenciaXml(new IllegalArgumentException("xml")));
    }

}