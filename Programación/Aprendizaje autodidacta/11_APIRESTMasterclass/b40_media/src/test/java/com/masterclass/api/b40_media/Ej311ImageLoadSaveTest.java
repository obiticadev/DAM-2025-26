package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej311ImageLoadSaveTest {

    @Test
    void detectarFormato() {
        assertEquals("PNG", Ej311ImageLoadSave.detectarFormato(new byte[]{(byte) 0x89, 'P', 'N', 'G'}));
        assertEquals("JPEG", Ej311ImageLoadSave.detectarFormato(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF}));
        assertEquals("GIF", Ej311ImageLoadSave.detectarFormato(new byte[]{'G', 'I', 'F'}));
        assertEquals("BMP", Ej311ImageLoadSave.detectarFormato(new byte[]{'B', 'M'}));
        assertEquals("desconocido", Ej311ImageLoadSave.detectarFormato(new byte[]{})); // caso límite
    }

    @Test
    void empaquetarARGB() {
        assertEquals(0xFF102030, Ej311ImageLoadSave.empaquetarARGB(255, 16, 32, 48));
        assertEquals(0, Ej311ImageLoadSave.empaquetarARGB(0, 0, 0, 0)); // caso límite: todo negro transparente
    }

    @Test
    void retoExtra01_canalAlfa() {
        assertEquals(255, Ej311ImageLoadSave.canalAlfa(0xFF102030));
        assertEquals(0, Ej311ImageLoadSave.canalAlfa(0x00102030));
    }

    @Test
    void retoExtra02_canalRojo() {
        assertEquals(16, Ej311ImageLoadSave.canalRojo(0xFF102030));
    }

    @Test
    void retoExtra03_canalVerde() {
        assertEquals(32, Ej311ImageLoadSave.canalVerde(0xFF102030));
    }

    @Test
    void retoExtra04_canalAzul() {
        assertEquals(48, Ej311ImageLoadSave.canalAzul(0xFF102030));
    }

    @Test
    void retoExtra05_totalPixeles() {
        assertEquals(2073600L, Ej311ImageLoadSave.totalPixeles(1920, 1080));
        assertEquals(0L, Ej311ImageLoadSave.totalPixeles(0, 5)); // caso límite
    }

    @Test
    void retoExtra06_extensionPorFormato() {
        assertEquals(".jpg", Ej311ImageLoadSave.extensionPorFormato("JPEG"));
        assertEquals(".png", Ej311ImageLoadSave.extensionPorFormato("PNG"));
        assertEquals("", Ej311ImageLoadSave.extensionPorFormato("XXX")); // caso límite
    }

    @Test
    void retoExtra07_mimePorFormato() {
        assertEquals("image/png", Ej311ImageLoadSave.mimePorFormato("PNG"));
        assertEquals("image/jpeg", Ej311ImageLoadSave.mimePorFormato("JPEG"));
    }

    @Test
    void retoExtra08_soportaTransparencia() {
        assertTrue(Ej311ImageLoadSave.soportaTransparencia("PNG"));
        assertFalse(Ej311ImageLoadSave.soportaTransparencia("JPEG"));
    }

    @Test
    void retoExtra09_tamanoSinComprimir() {
        assertEquals(24L, Ej311ImageLoadSave.tamanoSinComprimir(2, 3));
    }

    @Test
    void retoExtra10_forzarOpaco() {
        assertEquals(0xFF102030, Ej311ImageLoadSave.forzarOpaco(0x00102030));
    }
}
