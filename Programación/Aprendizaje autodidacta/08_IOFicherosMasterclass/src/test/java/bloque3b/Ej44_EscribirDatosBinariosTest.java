package bloque3b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej44 - Escribir Datos Binarios")
class Ej44_EscribirDatosBinariosTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("escribirEntero: fichero tiene 4 bytes")
    void escribirEntero() throws IOException {
        String ruta = dir + "/e.bin";
        Ej44_EscribirDatosBinarios.escribirEntero(ruta, 42);
        assertEquals(4, new File(ruta).length());
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            assertEquals(42, dis.readInt());
        }
    }

    @Test @DisplayName("escribirDouble: fichero tiene 8 bytes")
    void escribirDouble() throws IOException {
        String ruta = dir + "/d.bin";
        Ej44_EscribirDatosBinarios.escribirDouble(ruta, 3.14);
        assertEquals(8, new File(ruta).length());
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            assertEquals(3.14, dis.readDouble(), 0.001);
        }
    }

    @Test @DisplayName("escribirRegistro: escribe en orden correcto")
    void escribirRegistro() throws IOException {
        String ruta = dir + "/r.bin";
        Ej44_EscribirDatosBinarios.escribirRegistro(ruta, 1, 2500.50, true, "Ana");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            assertEquals(1, dis.readInt());
            assertEquals(2500.50, dis.readDouble(), 0.01);
            assertTrue(dis.readBoolean());
            assertEquals("Ana", dis.readUTF());
        }
    }

    @Test @DisplayName("escribirMultiplesRegistros: genera N registros")
    void escribirMultiples() throws IOException {
        String ruta = dir + "/m.bin";
        Ej44_EscribirDatosBinarios.escribirMultiplesRegistros(ruta, 3);
        assertTrue(new File(ruta).length() > 0);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            for (int i = 0; i < 3; i++) {
                assertEquals(i, dis.readInt());
                assertEquals(1000.0 + i * 500, dis.readDouble(), 0.01);
                assertEquals(i % 2 == 0, dis.readBoolean());
                assertEquals("Emp-" + i, dis.readUTF());
            }
        }
    }

    @Test @DisplayName("escribirBoolean: fichero tiene 1 byte")
    void escribirBoolean() throws IOException {
        String ruta = dir + "/b.bin";
        Ej44_EscribirDatosBinarios.escribirBoolean(ruta, true);
        assertEquals(1, new File(ruta).length());
    }

    @Test @DisplayName("escribirUTF: fichero contiene el texto")
    void escribirUTF() throws IOException {
        String ruta = dir + "/u.bin";
        Ej44_EscribirDatosBinarios.escribirUTF(ruta, "Hola");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            assertEquals("Hola", dis.readUTF());
        }
    }

    @Test @DisplayName("obtenerTamano: devuelve tamano correcto")
    void obtenerTamano() throws IOException {
        String ruta = dir + "/t.bin";
        Ej44_EscribirDatosBinarios.escribirEntero(ruta, 1);
        assertEquals(4, Ej44_EscribirDatosBinarios.obtenerTamano(ruta));
    }

    @Test @DisplayName("obtenerTamano: devuelve -1 si no existe")
    void obtenerTamanoNoExiste() {
        assertEquals(-1, Ej44_EscribirDatosBinarios.obtenerTamano(dir + "/nope.bin"));
    }
}
