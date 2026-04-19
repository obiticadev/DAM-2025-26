package bloque3b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej46 - Copiar Binario con Buffer")
class Ej46_CopiarBinarioConBufferTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        // Crear fichero origen con datos
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(dir + "/origen.bin"))) {
            for (int i = 0; i < 1000; i++) dos.writeInt(i);
        }
    }

    @Test @DisplayName("copiarByteAByte: copia correcta")
    void copiarByteAByte() throws IOException {
        Ej46_CopiarBinarioConBuffer.copiarByteAByte(dir + "/origen.bin", dir + "/c1.bin");
        assertEquals(new File(dir + "/origen.bin").length(), new File(dir + "/c1.bin").length());
    }

    @Test @DisplayName("copiarConBuffer: copia correcta")
    void copiarConBuffer() throws IOException {
        Ej46_CopiarBinarioConBuffer.copiarConBuffer(dir + "/origen.bin", dir + "/c2.bin", 1024);
        assertEquals(new File(dir + "/origen.bin").length(), new File(dir + "/c2.bin").length());
    }

    @Test @DisplayName("copiarContandoIteraciones: devuelve > 0")
    void copiarContando() throws IOException {
        int iter = Ej46_CopiarBinarioConBuffer.copiarContandoIteraciones(
            dir + "/origen.bin", dir + "/c3.bin", 512);
        assertTrue(iter > 0);
    }

    @Test @DisplayName("sonIdenticos: ficheros iguales")
    void sonIdenticos() throws IOException {
        Ej46_CopiarBinarioConBuffer.copiarConBuffer(dir + "/origen.bin", dir + "/c4.bin", 1024);
        assertTrue(Ej46_CopiarBinarioConBuffer.sonIdenticos(dir + "/origen.bin", dir + "/c4.bin"));
    }

    @Test @DisplayName("tamano: devuelve tamano correcto")
    void tamano() {
        assertEquals(4000, Ej46_CopiarBinarioConBuffer.tamano(dir + "/origen.bin"));
    }

    @Test @DisplayName("copiarConBuffered: copia correcta")
    void copiarConBuffered() throws IOException {
        Ej46_CopiarBinarioConBuffer.copiarConBuffered(dir + "/origen.bin", dir + "/c5.bin");
        assertEquals(new File(dir + "/origen.bin").length(), new File(dir + "/c5.bin").length());
    }

    @Test @DisplayName("copiarYVerificar: devuelve true")
    void copiarYVerificar() throws IOException {
        assertTrue(Ej46_CopiarBinarioConBuffer.copiarYVerificar(dir + "/origen.bin", dir + "/c6.bin", 1024));
    }
}
