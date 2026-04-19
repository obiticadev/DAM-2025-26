package bloque3b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej48 - Tamano Binario")
class Ej48_TamanoBinarioTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("tamanoDeInt: 4 bytes")
    void tamInt() throws IOException { assertEquals(4, Ej48_TamanoBinario.tamanoDeInt(dir + "/i.bin")); }

    @Test @DisplayName("tamanoDeDouble: 8 bytes")
    void tamDouble() throws IOException { assertEquals(8, Ej48_TamanoBinario.tamanoDeDouble(dir + "/d.bin")); }

    @Test @DisplayName("tamanoDeBoolean: 1 byte")
    void tamBool() throws IOException { assertEquals(1, Ej48_TamanoBinario.tamanoDeBoolean(dir + "/b.bin")); }

    @Test @DisplayName("tamanoDeUTF: 2 + longitud texto")
    void tamUTF() throws IOException {
        long t = Ej48_TamanoBinario.tamanoDeUTF(dir + "/u.bin", "Hola");
        assertEquals(6, t); // 2 + 4
    }

    @Test @DisplayName("tamanoCombinado: 13 bytes")
    void tamCombo() throws IOException { assertEquals(13, Ej48_TamanoBinario.tamanoCombinado(dir + "/c.bin")); }

    @Test @DisplayName("tamanoDeNEnteros: N*4 bytes")
    void tamN() throws IOException { assertEquals(40, Ej48_TamanoBinario.tamanoDeNEnteros(dir + "/n.bin", 10)); }

    @Test @DisplayName("registrosQueCaben: correcto")
    void registros() throws IOException {
        Ej48_TamanoBinario.tamanoDeNEnteros(dir + "/n.bin", 10);
        assertEquals(10, Ej48_TamanoBinario.registrosQueCaben(dir + "/n.bin"));
    }
}
