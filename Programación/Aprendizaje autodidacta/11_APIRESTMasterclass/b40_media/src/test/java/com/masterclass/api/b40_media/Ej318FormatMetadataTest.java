package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej318FormatMetadataTest {

    @Test
    void ratioCompresion() {
        assertEquals(4.0, Ej318FormatMetadata.ratioCompresion(1000, 250), 1e-9);
        assertEquals(0.0, Ej318FormatMetadata.ratioCompresion(1000, 0), 1e-9); // caso límite: división por cero
    }

    @Test
    void detectarFormatoMedia() {
        assertEquals("MP3", Ej318FormatMetadata.detectarFormatoMedia(new byte[]{'I', 'D', '3', 0}));
        assertEquals("WAV", Ej318FormatMetadata.detectarFormatoMedia(new byte[]{'R', 'I', 'F', 'F'}));
        assertEquals("OGG", Ej318FormatMetadata.detectarFormatoMedia(new byte[]{'O', 'g', 'g', 'S'}));
        assertEquals("MP4", Ej318FormatMetadata.detectarFormatoMedia(new byte[]{0, 0, 0, 0, 'f', 't', 'y', 'p'}));
        assertEquals("desconocido", Ej318FormatMetadata.detectarFormatoMedia(new byte[]{'X', 'Y'})); // caso límite
    }

    @Test
    void retoExtra01_porcentajeAhorro() {
        assertEquals(75, Ej318FormatMetadata.porcentajeAhorro(1000, 250));
        assertEquals(0, Ej318FormatMetadata.porcentajeAhorro(0, 250)); // caso límite
    }

    @Test
    void retoExtra02_extensionMedia() {
        assertEquals(".mp4", Ej318FormatMetadata.extensionMedia("MP4"));
        assertEquals("", Ej318FormatMetadata.extensionMedia("XXX"));
    }

    @Test
    void retoExtra03_esAudio() {
        assertTrue(Ej318FormatMetadata.esAudio("MP3"));
        assertFalse(Ej318FormatMetadata.esAudio("MP4"));
    }

    @Test
    void retoExtra04_esVideo() {
        assertTrue(Ej318FormatMetadata.esVideo("MP4"));
        assertFalse(Ej318FormatMetadata.esVideo("WAV"));
    }

    @Test
    void retoExtra05_mimeMedia() {
        assertEquals("audio/mpeg", Ej318FormatMetadata.mimeMedia("MP3"));
        assertEquals("video/mp4", Ej318FormatMetadata.mimeMedia("MP4"));
    }

    @Test
    void retoExtra06_bitrateKbps() {
        assertEquals(160, Ej318FormatMetadata.bitrateKbps(1_000_000, 50));
        assertEquals(0, Ej318FormatMetadata.bitrateKbps(1_000_000, 0)); // caso límite
    }

    @Test
    void retoExtra07_tamanoEstimado() {
        assertEquals(1_000_000L, Ej318FormatMetadata.tamanoEstimado(160, 50));
    }

    @Test
    void retoExtra08_duracionDesdeMuestras() {
        assertEquals(1.0, Ej318FormatMetadata.duracionDesdeMuestras(44100, 44100), 1e-9);
        assertEquals(0.0, Ej318FormatMetadata.duracionDesdeMuestras(44100, 0), 1e-9); // caso límite
    }

    @Test
    void retoExtra09_framesTotales() {
        assertEquals(300L, Ej318FormatMetadata.framesTotales(30, 10));
    }

    @Test
    void retoExtra10_formatearTamano() {
        assertEquals("512 B", Ej318FormatMetadata.formatearTamano(512));
        assertEquals("1.0 KB", Ej318FormatMetadata.formatearTamano(1024));
        assertEquals("1.0 MB", Ej318FormatMetadata.formatearTamano(1048576));
    }
}
