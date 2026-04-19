package bloque3c;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej50 - Escritura Secuencial RAF")
class Ej50_EscrituraSecuencialRAFTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("escribirSecuencia: tamano correcto")
    void tamano() throws IOException {
        String r = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(r, 10);
        assertEquals(40, Ej50_EscrituraSecuencialRAF.tamanoFichero(r));
    }

    @Test @DisplayName("leerEnPosicion: valores correctos")
    void leerPos() throws IOException {
        String r = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(r, 10);
        assertEquals(0, Ej50_EscrituraSecuencialRAF.leerEnPosicion(r, 0));
        assertEquals(40, Ej50_EscrituraSecuencialRAF.leerEnPosicion(r, 4));
        assertEquals(90, Ej50_EscrituraSecuencialRAF.leerEnPosicion(r, 9));
    }

    @Test @DisplayName("contarEnteros")
    void contar() throws IOException {
        String r = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(r, 5);
        assertEquals(5, Ej50_EscrituraSecuencialRAF.contarEnteros(r));
    }

    @Test @DisplayName("leerTodos: devuelve array correcto")
    void leerTodos() throws IOException {
        String r = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(r, 3);
        int[] todos = Ej50_EscrituraSecuencialRAF.leerTodos(r);
        assertArrayEquals(new int[]{0, 10, 20}, todos);
    }

    @Test @DisplayName("escribirArray: escribe correctamente")
    void escribirArr() throws IOException {
        String r = dir + "/a.dat";
        Ej50_EscrituraSecuencialRAF.escribirArray(r, new int[]{5, 10, 15});
        int[] todos = Ej50_EscrituraSecuencialRAF.leerTodos(r);
        assertArrayEquals(new int[]{5, 10, 15}, todos);
    }

    @Test @DisplayName("leerUltimo: devuelve el ultimo valor")
    void leerUltimo() throws IOException {
        String r = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(r, 5);
        assertEquals(40, Ej50_EscrituraSecuencialRAF.leerUltimo(r));
    }
}
