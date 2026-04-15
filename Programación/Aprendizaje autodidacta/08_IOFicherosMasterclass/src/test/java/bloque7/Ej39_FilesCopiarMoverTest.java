package bloque7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej39 - Files: copiar, mover y borrar")
class Ej39_FilesCopiarMoverTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        Files.writeString(tempDir.resolve("orig.txt"), "contenido");
    }

    @Test @DisplayName("copiar: crea fichero destino con mismo contenido")
    void copiar() throws IOException {
        Ej39_FilesCopiarMover.copiar(dir + "/orig.txt", dir + "/copia.txt");
        assertTrue(Files.exists(tempDir.resolve("copia.txt")));
        assertEquals("contenido", Files.readString(tempDir.resolve("copia.txt")));
    }

    @Test @DisplayName("mover: origen desaparece, destino existe")
    void mover() throws IOException {
        Ej39_FilesCopiarMover.mover(dir + "/orig.txt", dir + "/movido.txt");
        assertFalse(Files.exists(tempDir.resolve("orig.txt")));
        assertTrue(Files.exists(tempDir.resolve("movido.txt")));
    }

    @Test @DisplayName("borrar: existente devuelve true")
    void borrar_existe() throws IOException {
        assertTrue(Ej39_FilesCopiarMover.borrar(dir + "/orig.txt"));
        assertFalse(Files.exists(tempDir.resolve("orig.txt")));
    }

    @Test @DisplayName("borrar: inexistente devuelve false")
    void borrar_noExiste() throws IOException {
        assertFalse(Ej39_FilesCopiarMover.borrar(dir + "/nada.txt"));
    }

    @Test @DisplayName("crearDirectorios: crea jerarquia")
    void crearDirectorios() throws IOException {
        Ej39_FilesCopiarMover.crearDirectorios(dir + "/a/b/c");
        assertTrue(Files.isDirectory(tempDir.resolve("a/b/c")));
    }

    @Test @DisplayName("hacerBackup: crea .bak")
    void hacerBackup() throws IOException {
        String bak = Ej39_FilesCopiarMover.hacerBackup(dir + "/orig.txt");
        assertTrue(bak.endsWith(".bak"));
        assertTrue(Files.exists(Path.of(bak)));
    }

    @Test @DisplayName("copiarADirectorio: copia al directorio destino")
    void copiarADirectorio() throws IOException {
        Files.createDirectories(tempDir.resolve("dest"));
        String r = Ej39_FilesCopiarMover.copiarADirectorio(dir + "/orig.txt", dir + "/dest");
        assertTrue(Files.exists(Path.of(r)));
    }

    @Test @DisplayName("esDirectorio: true para directorio")
    void esDirectorio() {
        assertTrue(Ej39_FilesCopiarMover.esDirectorio(dir));
    }
}
