package bloque1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej01 - Escribir Bytes a un Fichero")
class Ej01_EscribirBytesTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() {
        dir = tempDir.toString();
    }

    @Test @DisplayName("crearDirectorioPadre: crea directorio inexistente")
    void crearDirectorioPadre_crea() {
        String ruta = dir + "/sub/archivo.bin";
        assertTrue(Ej01_EscribirBytes.crearDirectorioPadre(ruta));
        assertTrue(new File(dir + "/sub").isDirectory());
    }

    @Test @DisplayName("crearDirectorioPadre: devuelve true si ya existe")
    void crearDirectorioPadre_yaExiste() {
        assertTrue(Ej01_EscribirBytes.crearDirectorioPadre(dir + "/archivo.bin"));
    }

    @Test @DisplayName("escribirUnByte: escribe exactamente 1 byte")
    void escribirUnByte_tamano() throws IOException {
        String ruta = dir + "/byte.bin";
        Ej01_EscribirBytes.escribirUnByte(ruta, 65);
        File f = new File(ruta);
        assertTrue(f.exists());
        assertEquals(1, f.length());
    }

    @Test @DisplayName("escribirUnByte: el byte escrito es correcto")
    void escribirUnByte_valor() throws IOException {
        String ruta = dir + "/byte.bin";
        Ej01_EscribirBytes.escribirUnByte(ruta, 200);
        try (FileInputStream fis = new FileInputStream(ruta)) {
            assertEquals(200, fis.read());
            assertEquals(-1, fis.read());
        }
    }

    @Test @DisplayName("escribirArrayBytes: escribe todos los bytes")
    void escribirArrayBytes_completo() throws IOException {
        String ruta = dir + "/arr.bin";
        byte[] datos = {10, 20, 30, 40, 50};
        Ej01_EscribirBytes.escribirArrayBytes(ruta, datos);
        File f = new File(ruta);
        assertEquals(5, f.length());
        try (FileInputStream fis = new FileInputStream(ruta)) {
            for (byte b : datos) {
                assertEquals(b, (byte) fis.read());
            }
        }
    }

    @Test @DisplayName("escribirArrayBytes: array vacio crea fichero vacio")
    void escribirArrayBytes_vacio() throws IOException {
        String ruta = dir + "/vacio.bin";
        Ej01_EscribirBytes.escribirArrayBytes(ruta, new byte[0]);
        assertEquals(0, new File(ruta).length());
    }

    @Test @DisplayName("anadirBytes: anade al final del fichero")
    void anadirBytes_append() throws IOException {
        String ruta = dir + "/append.bin";
        Ej01_EscribirBytes.escribirArrayBytes(ruta, new byte[]{1, 2});
        Ej01_EscribirBytes.anadirBytes(ruta, new byte[]{3, 4});
        assertEquals(4, new File(ruta).length());
        try (FileInputStream fis = new FileInputStream(ruta)) {
            assertEquals(1, fis.read());
            assertEquals(2, fis.read());
            assertEquals(3, fis.read());
            assertEquals(4, fis.read());
        }
    }

    @Test @DisplayName("anadirBytes: crea fichero si no existe")
    void anadirBytes_creaFichero() throws IOException {
        String ruta = dir + "/nuevo_append.bin";
        Ej01_EscribirBytes.anadirBytes(ruta, new byte[]{99});
        assertTrue(new File(ruta).exists());
        assertEquals(1, new File(ruta).length());
    }

    @Test @DisplayName("escribirTextoComoBytes: contenido correcto")
    void escribirTextoComoBytes_contenido() throws IOException {
        String ruta = dir + "/texto.bin";
        Ej01_EscribirBytes.escribirTextoComoBytes(ruta, "AB");
        try (FileInputStream fis = new FileInputStream(ruta)) {
            assertEquals(65, fis.read()); // 'A'
            assertEquals(66, fis.read()); // 'B'
        }
    }

    @Test @DisplayName("escribirTextoComoBytes: string vacio")
    void escribirTextoComoBytes_vacio() throws IOException {
        String ruta = dir + "/vacio_texto.bin";
        Ej01_EscribirBytes.escribirTextoComoBytes(ruta, "");
        assertEquals(0, new File(ruta).length());
    }

    @Test @DisplayName("escribirSecuencia: genera secuencia correcta")
    void escribirSecuencia_valores() throws IOException {
        String ruta = dir + "/seq.bin";
        Ej01_EscribirBytes.escribirSecuencia(ruta, 5);
        try (FileInputStream fis = new FileInputStream(ruta)) {
            for (int i = 0; i < 5; i++) {
                assertEquals(i, fis.read());
            }
            assertEquals(-1, fis.read());
        }
    }

    @Test @DisplayName("escribirSecuencia: lanza excepcion si n negativo")
    void escribirSecuencia_negativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej01_EscribirBytes.escribirSecuencia(dir + "/x.bin", -1));
    }

    @Test @DisplayName("obtenerTamano: devuelve tamano correcto")
    void obtenerTamano_correcto() throws IOException {
        String ruta = dir + "/tam.bin";
        Ej01_EscribirBytes.escribirArrayBytes(ruta, new byte[]{1, 2, 3});
        assertEquals(3, Ej01_EscribirBytes.obtenerTamano(ruta));
    }

    @Test @DisplayName("obtenerTamano: devuelve -1 si no existe")
    void obtenerTamano_noExiste() {
        assertEquals(-1, Ej01_EscribirBytes.obtenerTamano(dir + "/inexistente.bin"));
    }
}
