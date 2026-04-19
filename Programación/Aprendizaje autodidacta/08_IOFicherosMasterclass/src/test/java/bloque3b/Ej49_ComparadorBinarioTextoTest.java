package bloque3b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej49 - Comparador Binario vs Texto")
class Ej49_ComparadorBinarioTextoTest {

    @TempDir Path tempDir;
    String dir;
    int[] datos = {100, 200, 300, 400, 500};

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("escribirEnTexto: crea fichero no vacio")
    void texto() throws IOException {
        Ej49_ComparadorBinarioTexto.escribirEnTexto(dir + "/d.txt", datos);
        assertTrue(new File(dir + "/d.txt").length() > 0);
    }

    @Test @DisplayName("escribirEnBinario: crea fichero de 20 bytes")
    void binario() throws IOException {
        Ej49_ComparadorBinarioTexto.escribirEnBinario(dir + "/d.bin", datos);
        assertEquals(20, new File(dir + "/d.bin").length());
    }

    @Test @DisplayName("calcularAhorro: ahorro positivo")
    void ahorro() throws IOException {
        Ej49_ComparadorBinarioTexto.escribirEnTexto(dir + "/d.txt", datos);
        Ej49_ComparadorBinarioTexto.escribirEnBinario(dir + "/d.bin", datos);
        assertTrue(Ej49_ComparadorBinarioTexto.calcularAhorro(dir + "/d.txt", dir + "/d.bin") > 0);
    }

    @Test @DisplayName("leerDesdeTexto: devuelve datos correctos")
    void leerTexto() throws IOException {
        Ej49_ComparadorBinarioTexto.escribirEnTexto(dir + "/d.txt", datos);
        int[] leidos = Ej49_ComparadorBinarioTexto.leerDesdeTexto(dir + "/d.txt");
        assertArrayEquals(datos, leidos);
    }

    @Test @DisplayName("leerDesdeBinario: devuelve datos correctos")
    void leerBin() throws IOException {
        Ej49_ComparadorBinarioTexto.escribirEnBinario(dir + "/d.bin", datos);
        int[] leidos = Ej49_ComparadorBinarioTexto.leerDesdeBinario(dir + "/d.bin");
        assertArrayEquals(datos, leidos);
    }

    @Test @DisplayName("mismosDatos: devuelve true")
    void mismos() throws IOException {
        Ej49_ComparadorBinarioTexto.escribirEnTexto(dir + "/d.txt", datos);
        Ej49_ComparadorBinarioTexto.escribirEnBinario(dir + "/d.bin", datos);
        assertTrue(Ej49_ComparadorBinarioTexto.mismosDatos(dir + "/d.txt", dir + "/d.bin"));
    }
}
