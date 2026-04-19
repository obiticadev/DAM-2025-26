package bloque3c;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej54 - Ultimo Registro")
class Ej54_UltimoRegistroTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        ruta = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(ruta, 10);
    }

    @Test @DisplayName("ultimoEntero: correcto")
    void ultimo() throws IOException { assertEquals(90, Ej54_UltimoRegistro.ultimoEntero(ruta)); }

    @Test @DisplayName("anadirEntero + leer ultimo")
    void anadir() throws IOException {
        Ej54_UltimoRegistro.anadirEntero(ruta, 12345);
        assertEquals(12345, Ej54_UltimoRegistro.ultimoEntero(ruta));
    }

    @Test @DisplayName("eliminarUltimo")
    void eliminar() throws IOException {
        int elim = Ej54_UltimoRegistro.eliminarUltimo(ruta);
        assertEquals(90, elim);
        assertEquals(80, Ej54_UltimoRegistro.ultimoEntero(ruta));
    }

    @Test @DisplayName("penultimoEntero")
    void penultimo() throws IOException { assertEquals(80, Ej54_UltimoRegistro.penultimoEntero(ruta)); }

    @Test @DisplayName("ultimosN")
    void ultimosN() throws IOException {
        int[] ult = Ej54_UltimoRegistro.ultimosN(ruta, 3);
        assertArrayEquals(new int[]{70, 80, 90}, ult);
    }

    @Test @DisplayName("tieneRegistros: true")
    void tieneRegistros() throws IOException { assertTrue(Ej54_UltimoRegistro.tieneRegistros(ruta)); }
}
