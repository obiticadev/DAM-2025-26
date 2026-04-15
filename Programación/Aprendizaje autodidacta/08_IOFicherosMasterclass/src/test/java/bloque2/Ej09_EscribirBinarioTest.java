package bloque2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej09 - Escribir Datos Binarios")
class Ej09_EscribirBinarioTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() {
        dir = tempDir.toString();
    }

    @Test @DisplayName("escribirCabecera: escribe 8 bytes con firma y tamano")
    void escribirCabecera_tamano() throws IOException {
        String ruta = dir + "/cab.bin";
        Ej09_EscribirBinario.escribirCabecera(ruta, new byte[]{1, 2, 3, 4}, 256);
        assertEquals(8, new File(ruta).length());
    }

    @Test @DisplayName("escribirCabecera: firma invalida lanza excepcion")
    void escribirCabecera_firmaInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej09_EscribirBinario.escribirCabecera(dir + "/x.bin",
                        new byte[]{1, 2}, 100));
    }

    @Test @DisplayName("generarPatron: tamano correcto")
    void generarPatron_tamano() throws IOException {
        String ruta = dir + "/patron.bin";
        Ej09_EscribirBinario.generarPatron(ruta, new byte[]{10, 20}, 5);
        assertEquals(10, new File(ruta).length());
    }

    @Test @DisplayName("generarPatron: patron vacio lanza excepcion")
    void generarPatron_patronVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej09_EscribirBinario.generarPatron(dir + "/x.bin",
                        new byte[0], 3));
    }

    @Test @DisplayName("escribirIntComoBytes: escribe byte bajo de cada int")
    void escribirIntComoBytes_contenido() throws IOException {
        String ruta = dir + "/ints.bin";
        Ej09_EscribirBinario.escribirIntComoBytes(ruta, new int[]{65, 300});
        try (FileInputStream fis = new FileInputStream(ruta)) {
            assertEquals(65, fis.read());
            assertEquals(300 & 0xFF, fis.read()); // 44
        }
    }

    @Test @DisplayName("anadirDatos: anade al final sin borrar")
    void anadirDatos_append() throws IOException {
        String ruta = dir + "/app.bin";
        Ej09_EscribirBinario.escribirIntComoBytes(ruta, new int[]{1, 2});
        Ej09_EscribirBinario.anadirDatos(ruta, new byte[]{3});
        assertEquals(3, new File(ruta).length());
    }

    @Test @DisplayName("generarGradiente: tamano correcto 0-255")
    void generarGradiente_completo() throws IOException {
        String ruta = dir + "/grad.bin";
        Ej09_EscribirBinario.generarGradiente(ruta, 0, 255);
        assertEquals(256, new File(ruta).length());
    }

    @Test @DisplayName("generarGradiente: inicio > fin lanza excepcion")
    void generarGradiente_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej09_EscribirBinario.generarGradiente(dir + "/x.bin", 200, 100));
    }

    @Test @DisplayName("escribirConCharset: UTF-8 y Latin1 pueden diferir en tamano")
    void escribirConCharset_diferencia() throws IOException {
        String rutaUtf = dir + "/utf.bin";
        String rutaLat = dir + "/lat.bin";
        Ej09_EscribirBinario.escribirConCharset(rutaUtf, "\u00e9", "UTF-8");   // e con tilde
        Ej09_EscribirBinario.escribirConCharset(rutaLat, "\u00e9", "ISO-8859-1");
        // UTF-8: 2 bytes, Latin1: 1 byte
        assertEquals(2, new File(rutaUtf).length());
        assertEquals(1, new File(rutaLat).length());
    }

    @Test @DisplayName("checksumSimple: calculo correcto")
    void checksumSimple_correcto() {
        assertEquals(60, Ej09_EscribirBinario.checksumSimple(new byte[]{10, 20, 30}));
    }

    @Test @DisplayName("checksumSimple: modulo 256")
    void checksumSimple_modulo() {
        // 200 + 200 = 400 % 256 = 144
        assertEquals(144, Ej09_EscribirBinario.checksumSimple(
                new byte[]{(byte) 200, (byte) 200}));
    }
}
