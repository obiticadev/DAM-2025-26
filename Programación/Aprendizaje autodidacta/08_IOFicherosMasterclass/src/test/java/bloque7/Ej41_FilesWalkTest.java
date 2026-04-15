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

@DisplayName("Ej41 - Files.walk: recorrido recursivo")
class Ej41_FilesWalkTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        Files.createDirectories(tempDir.resolve("sub1"));
        Files.createDirectories(tempDir.resolve("sub2/sub3"));
        Files.writeString(tempDir.resolve("a.txt"), "A");
        Files.writeString(tempDir.resolve("sub1/b.txt"), "BB");
        Files.writeString(tempDir.resolve("sub2/sub3/c.dat"), "CCC");
    }

    @Test @DisplayName("listarTodo: incluye ficheros y directorios")
    void listarTodo() throws IOException {
        assertTrue(Ej41_FilesWalk.listarTodo(dir).size() >= 6);
    }

    @Test @DisplayName("buscarPorNombre: encuentra b.txt")
    void buscarPorNombre() throws IOException {
        List<String> r = Ej41_FilesWalk.buscarPorNombre(dir, "b");
        assertEquals(1, r.size());
        assertTrue(r.get(0).contains("b.txt"));
    }

    @Test @DisplayName("tamanoTotalRecursivo: > 0")
    void tamanoTotal() throws IOException {
        assertTrue(Ej41_FilesWalk.tamanoTotalRecursivo(dir) > 0);
    }

    @Test @DisplayName("contarSubdirectorios: 3 (sub1, sub2, sub3)")
    void contarSubdirectorios() throws IOException {
        assertEquals(3, Ej41_FilesWalk.contarSubdirectorios(dir));
    }

    @Test @DisplayName("ficheroMasProfundo: c.dat en sub2/sub3")
    void ficheroMasProfundo() throws IOException {
        String r = Ej41_FilesWalk.ficheroMasProfundo(dir);
        assertNotNull(r);
        assertTrue(r.contains("c.dat"));
    }

    @Test @DisplayName("listarConProfundidad: profundidad 1 solo a.txt")
    void listarConProfundidad() throws IOException {
        List<String> l = Ej41_FilesWalk.listarConProfundidad(dir, 1);
        assertEquals(1, l.size());
        assertTrue(l.get(0).contains("a.txt"));
    }

    @Test @DisplayName("informeArbol: contiene Ficheros y Directorios")
    void informeArbol() throws IOException {
        String inf = Ej41_FilesWalk.informeArbol(dir);
        assertTrue(inf.contains("Ficheros"));
        assertTrue(inf.contains("Directorios"));
    }
}
