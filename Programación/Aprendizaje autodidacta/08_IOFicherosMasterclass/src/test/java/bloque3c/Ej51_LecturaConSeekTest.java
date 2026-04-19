package bloque3c;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej51 - Lectura con Seek")
class Ej51_LecturaConSeekTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        ruta = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(ruta, 10);
    }

    @Test @DisplayName("leerEnteroConSeek")
    void leerEntero() throws IOException { assertEquals(50, Ej51_LecturaConSeek.leerEnteroConSeek(ruta, 5)); }

    @Test @DisplayName("leerDelMedio")
    void leerMedio() throws IOException { assertEquals(50, Ej51_LecturaConSeek.leerDelMedio(ruta)); }

    @Test @DisplayName("leerPosicionesPares")
    void pares() throws IOException {
        int[] p = Ej51_LecturaConSeek.leerPosicionesPares(ruta);
        assertEquals(5, p.length);
        assertEquals(0, p[0]);
        assertEquals(40, p[2]);
    }

    @Test @DisplayName("sumaPrimeroYUltimo")
    void sumaExtremos() throws IOException { assertEquals(90, Ej51_LecturaConSeek.sumaPrimeroYUltimo(ruta)); }

    @Test @DisplayName("buscarValor: encontrado")
    void buscar() throws IOException { assertEquals(4, Ej51_LecturaConSeek.buscarValor(ruta, 40)); }

    @Test @DisplayName("buscarValor: no encontrado")
    void noEncontrado() throws IOException { assertEquals(-1, Ej51_LecturaConSeek.buscarValor(ruta, 999)); }

    @Test @DisplayName("calcularPosicion")
    void calcPos() { assertEquals(16, Ej51_LecturaConSeek.calcularPosicion(4, 4)); }
}
