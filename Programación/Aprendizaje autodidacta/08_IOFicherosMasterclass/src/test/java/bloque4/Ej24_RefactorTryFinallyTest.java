package bloque4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej24 - Refactorizar try-finally a try-with-resources")
class Ej24_RefactorTryFinallyTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    private void crear(String ruta, String contenido) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(contenido);
        }
    }

    @Test @DisplayName("escribirModerno + leerModerno: ida y vuelta")
    void escribirLeer() throws IOException {
        Ej24_RefactorTryFinally.escribirModerno(dir + "/m.txt", new String[]{"A", "B"});
        String c = Ej24_RefactorTryFinally.leerModerno(dir + "/m.txt");
        assertTrue(c.contains("A"));
        assertTrue(c.contains("B"));
    }

    @Test @DisplayName("copiarModerno: copia lineas correctamente")
    void copiarModerno() throws IOException {
        crear(dir + "/o.txt", "X\nY\nZ");
        assertEquals(3, Ej24_RefactorTryFinally.copiarModerno(dir + "/o.txt", dir + "/c.txt"));
    }

    @Test @DisplayName("tablaASCII: genera 95 lineas (32 a 126)")
    void tablaASCII() throws IOException {
        Ej24_RefactorTryFinally.tablaASCII(dir + "/ascii.txt");
        String c = Ej24_RefactorTryFinally.leerModerno(dir + "/ascii.txt");
        String[] lineas = c.split("\n");
        assertEquals(95, lineas.length);
    }

    @Test @DisplayName("buscarLinea: encuentra palabra")
    void buscarLinea_encuentra() throws IOException {
        crear(dir + "/b.txt", "Hola\nMundo\nJava");
        assertEquals(2, Ej24_RefactorTryFinally.buscarLinea(dir + "/b.txt", "mundo"));
    }

    @Test @DisplayName("buscarLinea: no encuentra devuelve -1")
    void buscarLinea_noEncuentra() throws IOException {
        crear(dir + "/b2.txt", "Hola\nMundo");
        assertEquals(-1, Ej24_RefactorTryFinally.buscarLinea(dir + "/b2.txt", "python"));
    }

    @Test @DisplayName("ultimasLineas: devuelve las N ultimas")
    void ultimasLineas_correctas() throws IOException {
        crear(dir + "/u.txt", "A\nB\nC\nD\nE");
        List<String> l = Ej24_RefactorTryFinally.ultimasLineas(dir + "/u.txt", 2);
        assertEquals(2, l.size());
        assertEquals("D", l.get(0));
        assertEquals("E", l.get(1));
    }

    @Test @DisplayName("ultimasLineas: N mayor que lineas devuelve todas")
    void ultimasLineas_todas() throws IOException {
        crear(dir + "/u2.txt", "A\nB");
        assertEquals(2, Ej24_RefactorTryFinally.ultimasLineas(dir + "/u2.txt", 10).size());
    }

    @Test @DisplayName("ultimasLineas: N negativo lanza excepcion")
    void ultimasLineas_negativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej24_RefactorTryFinally.ultimasLineas(dir + "/x.txt", -1));
    }

    @Test @DisplayName("requiereAutoCloseable: devuelve true")
    void requiereAutoCloseable() {
        assertTrue(Ej24_RefactorTryFinally.requiereAutoCloseable());
    }
}
