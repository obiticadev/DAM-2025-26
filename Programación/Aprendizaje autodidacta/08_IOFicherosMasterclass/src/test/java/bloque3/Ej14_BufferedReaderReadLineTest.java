package bloque3;

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

@DisplayName("Ej14 - BufferedReader y readLine()")
class Ej14_BufferedReaderReadLineTest {

    @TempDir
    Path tempDir;
    String fichero;

    @BeforeEach
    void setUp() throws IOException {
        fichero = tempDir.resolve("datos.txt").toString();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write("Paella valenciana"); bw.newLine();
            bw.write(""); bw.newLine();
            bw.write("Agua mineral"); bw.newLine();
            bw.write("Flan casero");
        }
    }

    @Test @DisplayName("leerTodasLineas: lee 4 lineas")
    void leerTodasLineas_completo() throws IOException {
        List<String> l = Ej14_BufferedReaderReadLine.leerTodasLineas(fichero);
        assertEquals(4, l.size());
        assertEquals("Paella valenciana", l.get(0));
    }

    @Test @DisplayName("leerTodasLineas: fichero vacio devuelve lista vacia")
    void leerTodasLineas_vacio() throws IOException {
        String v = tempDir.resolve("vacio.txt").toString();
        try (FileWriter fw = new FileWriter(v)) { /* vacio */ }
        assertTrue(Ej14_BufferedReaderReadLine.leerTodasLineas(v).isEmpty());
    }

    @Test @DisplayName("contarLineas: cuenta 4 lineas")
    void contarLineas_correcto() throws IOException {
        assertEquals(4, Ej14_BufferedReaderReadLine.contarLineas(fichero));
    }

    @Test @DisplayName("leerLineasNoVacias: filtra vacias")
    void leerLineasNoVacias_filtra() throws IOException {
        List<String> l = Ej14_BufferedReaderReadLine.leerLineasNoVacias(fichero);
        assertEquals(3, l.size());
    }

    @Test @DisplayName("leerLineasNoVacias: todo vacio devuelve lista vacia")
    void leerLineasNoVacias_todoVacio() throws IOException {
        String v = tempDir.resolve("vacias.txt").toString();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(v))) {
            bw.write(""); bw.newLine(); bw.write(""); bw.newLine();
        }
        assertTrue(Ej14_BufferedReaderReadLine.leerLineasNoVacias(v).isEmpty());
    }

    @Test @DisplayName("buscarPalabra: encuentra 'agua' case insensitive")
    void buscarPalabra_encuentra() throws IOException {
        List<String> r = Ej14_BufferedReaderReadLine.buscarPalabra(fichero, "agua");
        assertEquals(1, r.size());
        assertTrue(r.get(0).contains("3"));
    }

    @Test @DisplayName("buscarPalabra: no encuentra devuelve lista vacia")
    void buscarPalabra_noEncuentra() throws IOException {
        assertTrue(Ej14_BufferedReaderReadLine.buscarPalabra(fichero, "pizza").isEmpty());
    }

    @Test @DisplayName("lineaMasLarga: devuelve 'Paella valenciana'")
    void lineaMasLarga_correcta() throws IOException {
        assertEquals("Paella valenciana", Ej14_BufferedReaderReadLine.lineaMasLarga(fichero));
    }

    @Test @DisplayName("lineaMasLarga: fichero vacio devuelve null")
    void lineaMasLarga_vacio() throws IOException {
        String v = tempDir.resolve("v.txt").toString();
        try (FileWriter fw = new FileWriter(v)) { /* vacio */ }
        assertNull(Ej14_BufferedReaderReadLine.lineaMasLarga(v));
    }

    @Test @DisplayName("resumen: contiene datos clave")
    void resumen_formato() throws IOException {
        String r = Ej14_BufferedReaderReadLine.resumen(fichero);
        assertTrue(r.contains("Lineas:"));
        assertTrue(r.contains("Vacias:"));
    }

    @Test @DisplayName("primerasLineas: devuelve N primeras")
    void primerasLineas_correctas() throws IOException {
        List<String> l = Ej14_BufferedReaderReadLine.primerasLineas(fichero, 2);
        assertEquals(2, l.size());
        assertEquals("Paella valenciana", l.get(0));
    }

    @Test @DisplayName("primerasLineas: N negativo lanza excepcion")
    void primerasLineas_negativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej14_BufferedReaderReadLine.primerasLineas(fichero, -1));
    }
}
