package bloque7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej42 - Comparar java.io vs java.nio.file")
class Ej42_NIOvsIOTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("escribirIO + leerIO: ida y vuelta")
    void ioIdaVuelta() throws IOException {
        String ruta = dir + "/io.txt";
        Ej42_NIOvsIO.escribirIO(ruta, "hola IO");
        assertEquals("hola IO", Ej42_NIOvsIO.leerIO(ruta));
    }

    @Test @DisplayName("escribirNIO + leerNIO: ida y vuelta")
    void nioIdaVuelta() throws IOException {
        String ruta = dir + "/nio.txt";
        Ej42_NIOvsIO.escribirNIO(ruta, "hola NIO");
        assertEquals("hola NIO", Ej42_NIOvsIO.leerNIO(ruta));
    }

    @Test @DisplayName("copiarIO: copia correctamente")
    void copiarIO() throws IOException {
        String orig = dir + "/orig.txt";
        Ej42_NIOvsIO.escribirIO(orig, "contenido");
        String dest = dir + "/copia_io.txt";
        Ej42_NIOvsIO.copiarIO(orig, dest);
        assertTrue(Files.exists(Path.of(dest)));
    }

    @Test @DisplayName("copiarNIO: copia correctamente")
    void copiarNIO() throws IOException {
        String orig = dir + "/orig2.txt";
        Ej42_NIOvsIO.escribirNIO(orig, "contenido");
        String dest = dir + "/copia_nio.txt";
        Ej42_NIOvsIO.copiarNIO(orig, dest);
        assertTrue(Files.exists(Path.of(dest)));
    }

    @Test @DisplayName("IO y NIO producen mismo resultado")
    void mismoResultado() throws IOException {
        String txt = "texto de prueba";
        Ej42_NIOvsIO.escribirIO(dir + "/a.txt", txt);
        Ej42_NIOvsIO.escribirNIO(dir + "/b.txt", txt);
        assertEquals(Ej42_NIOvsIO.leerIO(dir + "/a.txt"),
                     Ej42_NIOvsIO.leerNIO(dir + "/b.txt"));
    }

    @Test @DisplayName("resumenComparativo: contiene IO y NIO")
    void resumenComparativo() {
        String r = Ej42_NIOvsIO.resumenComparativo();
        assertTrue(r.contains("IO"));
        assertTrue(r.contains("NIO"));
    }
}
