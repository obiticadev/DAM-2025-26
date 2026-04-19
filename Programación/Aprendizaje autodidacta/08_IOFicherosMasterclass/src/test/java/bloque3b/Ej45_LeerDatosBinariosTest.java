package bloque3b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej45 - Leer Datos Binarios")
class Ej45_LeerDatosBinariosTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    private void escribirEntero(String ruta, int v) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) { dos.writeInt(v); }
    }
    private void escribirDouble(String ruta, double v) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) { dos.writeDouble(v); }
    }

    @Test @DisplayName("leerEntero: lee correctamente")
    void leerEntero() throws IOException {
        String ruta = dir + "/e.bin";
        escribirEntero(ruta, 42);
        assertEquals(42, Ej45_LeerDatosBinarios.leerEntero(ruta));
    }

    @Test @DisplayName("leerDouble: lee correctamente")
    void leerDouble() throws IOException {
        String ruta = dir + "/d.bin";
        escribirDouble(ruta, 3.14);
        assertEquals(3.14, Ej45_LeerDatosBinarios.leerDouble(ruta), 0.001);
    }

    @Test @DisplayName("leerRegistro: lee en orden correcto")
    void leerRegistro() throws IOException {
        String ruta = dir + "/r.bin";
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) {
            dos.writeInt(1); dos.writeDouble(2500.0); dos.writeBoolean(true); dos.writeUTF("Ana");
        }
        String r = Ej45_LeerDatosBinarios.leerRegistro(ruta);
        assertTrue(r.contains("1"));
        assertTrue(r.contains("Ana"));
    }

    @Test @DisplayName("contarRegistros: cuenta correctamente")
    void contarRegistros() throws IOException {
        String ruta = dir + "/m.bin";
        Ej44_EscribirDatosBinarios.escribirMultiplesRegistros(ruta, 5);
        assertEquals(5, Ej45_LeerDatosBinarios.contarRegistros(ruta));
    }

    @Test @DisplayName("leerBoolean: lee correctamente")
    void leerBoolean() throws IOException {
        String ruta = dir + "/b.bin";
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) { dos.writeBoolean(true); }
        assertTrue(Ej45_LeerDatosBinarios.leerBoolean(ruta));
    }

    @Test @DisplayName("leerUTF: lee correctamente")
    void leerUTF() throws IOException {
        String ruta = dir + "/u.bin";
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) { dos.writeUTF("Test"); }
        assertEquals("Test", Ej45_LeerDatosBinarios.leerUTF(ruta));
    }

    @Test @DisplayName("intentarLeer: fichero vacio devuelve nombre excepcion")
    void intentarLeerVacio() throws IOException {
        String ruta = dir + "/v.bin";
        new FileOutputStream(ruta).close();
        String r = Ej45_LeerDatosBinarios.intentarLeer(ruta);
        assertNotEquals("OK", r);
        assertFalse(r.isEmpty());
    }
}
