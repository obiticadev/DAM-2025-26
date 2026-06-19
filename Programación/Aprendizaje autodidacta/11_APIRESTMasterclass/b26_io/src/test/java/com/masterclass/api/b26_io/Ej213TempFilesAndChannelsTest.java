package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej213TempFilesAndChannelsTest {

    @Test
    void escribirConChannel() {
        assertArrayEquals(new byte[]{1, 2, 3}, Ej213TempFilesAndChannels.escribirConChannel(new byte[]{1, 2, 3}));
    }

    @Test
    void tamanoViaChannel() {
        assertEquals(42L, Ej213TempFilesAndChannels.tamanoViaChannel(new byte[42]));
    }

    @Test
    void retoExtra01_ficheroTemporalSeCrea() {
        assertTrue(Ej213TempFilesAndChannels.ficheroTemporalSeCrea());
    }

    @Test
    void retoExtra02_byteBufferCapacidad() {
        assertEquals(10, Ej213TempFilesAndChannels.byteBufferCapacidad());
    }

    @Test
    void retoExtra03_flipPonePosicionACero() {
        assertEquals(0, Ej213TempFilesAndChannels.flipPonePosicionACero());
    }

    @Test
    void retoExtra04_channelRoundTrip() {
        assertArrayEquals(new byte[]{7, 8, 9}, Ej213TempFilesAndChannels.channelRoundTrip(new byte[]{7, 8, 9}));
    }

    @Test
    void retoExtra05_byteBufferWrap() {
        assertArrayEquals(new byte[]{4, 5, 6}, Ej213TempFilesAndChannels.byteBufferWrap(new byte[]{4, 5, 6}));
    }

    @Test
    void retoExtra06_posicionDelChannelTrasEscribir() {
        assertEquals(8L, Ej213TempFilesAndChannels.posicionDelChannelTrasEscribir(new byte[8]));
    }

    @Test
    void retoExtra07_remainingTrasPut() {
        assertEquals(6, Ej213TempFilesAndChannels.remainingTrasPut());
    }

    @Test
    void retoExtra08_mapearFicheroAMemoria() {
        assertEquals(55, Ej213TempFilesAndChannels.mapearFicheroAMemoria(new byte[]{55, 1, 2}));
    }

    @Test
    void retoExtra09_truncarConChannel() {
        assertEquals(4L, Ej213TempFilesAndChannels.truncarConChannel());
    }

    @Test
    void retoExtra10_transferirEntreChannels() {
        assertEquals(12L, Ej213TempFilesAndChannels.transferirEntreChannels(new byte[12]));
    }

    @Test
    void retoExtra11_clearReseteaBuffer() {
        assertTrue(Ej213TempFilesAndChannels.clearReseteaBuffer());
    }

    @Test
    void retoExtra12_directorioTemporalSeCrea() {
        assertTrue(Ej213TempFilesAndChannels.directorioTemporalSeCrea());
    }
}
