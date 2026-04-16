package bloque7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej38 - Files: leer y escribir con NIO.2")
class Ej38_FilesLeerEscribirTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("escribirTexto + leerTexto: ida y vuelta")
    void idaYVuelta() throws IOException {
        String ruta = dir + "/t.txt";
        Ej38_FilesLeerEscribir.escribirTexto(ruta, "Hola NIO.2");
        assertEquals("Hola NIO.2", Ej38_FilesLeerEscribir.leerTexto(ruta));
    }

    @Test @DisplayName("escribirLineas + leerLineas: ida y vuelta")
    void lineasIdaVuelta() throws IOException {
        String ruta = dir + "/l.txt";
        List<String> orig = List.of("A", "B", "C");
        Ej38_FilesLeerEscribir.escribirLineas(ruta, orig);
        assertEquals(orig, Ej38_FilesLeerEscribir.leerLineas(ruta));
    }

    @Test @DisplayName("tamanoFichero: devuelve > 0")
    void tamanoFichero() throws IOException {
        String ruta = dir + "/s.txt";
        Ej38_FilesLeerEscribir.escribirTexto(ruta, "12345");
        assertTrue(Ej38_FilesLeerEscribir.tamanoFichero(ruta) > 0);
    }

    @Test @DisplayName("existe: true para fichero existente")
    void existe_true() throws IOException {
        String ruta = dir + "/e.txt";
        Ej38_FilesLeerEscribir.escribirTexto(ruta, "x");
        assertTrue(Ej38_FilesLeerEscribir.existe(ruta));
    }

    @Test @DisplayName("existe: false para fichero inexistente")
    void existe_false() {
        assertFalse(Ej38_FilesLeerEscribir.existe(dir + "/noexiste.txt"));
    }

    @Test @DisplayName("verificarIdaVuelta: devuelve true")
    void verificarIdaVuelta() throws IOException {
        assertTrue(Ej38_FilesLeerEscribir.verificarIdaVuelta(dir + "/v.txt", "test"));
    }
}
