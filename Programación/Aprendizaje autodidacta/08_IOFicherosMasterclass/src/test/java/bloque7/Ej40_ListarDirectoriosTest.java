package bloque7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej40 - Listar Directorios con NIO.2")
class Ej40_ListarDirectoriosTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        Files.writeString(tempDir.resolve("a.txt"), "AAA");
        Files.writeString(tempDir.resolve("b.csv"), "BB");
        Files.writeString(tempDir.resolve("c.txt"), "CCCC");
        Files.createDirectories(tempDir.resolve("sub"));
    }

    @Test @DisplayName("listarContenido: devuelve 4 (3 ficheros + 1 dir)")
    void listarContenido() throws IOException {
        assertEquals(4, Ej40_ListarDirectorios.listarContenido(dir).size());
    }

    @Test @DisplayName("listarSoloFicheros: devuelve 3")
    void listarSoloFicheros() throws IOException {
        assertEquals(3, Ej40_ListarDirectorios.listarSoloFicheros(dir).size());
    }

    @Test @DisplayName("listarPorGlob: *.txt devuelve 2")
    void listarPorGlob() throws IOException {
        assertEquals(2, Ej40_ListarDirectorios.listarPorGlob(dir, "*.txt").size());
    }

    @Test @DisplayName("contarFicherosRecursivo: 3 ficheros")
    void contarFicherosRecursivo() throws IOException {
        assertEquals(3, Ej40_ListarDirectorios.contarFicherosRecursivo(dir));
    }

    @Test @DisplayName("buscarPorExtension: txt devuelve 2")
    void buscarPorExtension() throws IOException {
        assertEquals(2, Ej40_ListarDirectorios.buscarPorExtension(dir, "txt").size());
    }

    @Test @DisplayName("tamanoTotalDirectorio: > 0")
    void tamanoTotal() throws IOException {
        assertTrue(Ej40_ListarDirectorios.tamanoTotalDirectorio(dir) > 0);
    }

    @Test @DisplayName("ficheroMasGrande: c.txt (4 bytes)")
    void ficheroMasGrande() throws IOException {
        assertEquals("c.txt", Ej40_ListarDirectorios.ficheroMasGrande(dir));
    }
}
