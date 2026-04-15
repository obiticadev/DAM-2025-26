package bloque2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej11 - Corrupcion Cruzada")
class Ej11_CorrupcionCruzadaTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() {
        dir = tempDir.toString();
    }

    @Test @DisplayName("crearFicheroBinario: crea con contenido correcto")
    void crearFicheroBinario_correcto() throws IOException {
        String ruta = dir + "/bin.dat";
        Ej11_CorrupcionCruzada.crearFicheroBinario(ruta, new byte[]{1, 2, 3});
        byte[] leido = Ej10_LeerBinario.leerTodo(ruta);
        assertNotNull(leido);
        assertArrayEquals(new byte[]{1, 2, 3}, leido);
    }

    @Test @DisplayName("copiarConStream: copia sin corrupcion")
    void copiarConStream_sinCorrupcion() throws IOException {
        String orig = dir + "/orig.bin";
        String copia = dir + "/copia_s.bin";
        byte[] datos = new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, 0x00, (byte) 0x80};
        Ej11_CorrupcionCruzada.crearFicheroBinario(orig, datos);
        Ej11_CorrupcionCruzada.copiarConStream(orig, copia);
        assertEquals(0, Ej11_CorrupcionCruzada.contarDiferencias(orig, copia));
    }

    @Test @DisplayName("copiarConReader: puede corromper datos binarios")
    void copiarConReader_posibleCorrupcion() throws IOException {
        String orig = dir + "/orig2.bin";
        String copiaR = dir + "/copia_r.bin";
        // Bytes que no son ASCII valido, probablemente se corromperan
        byte[] datos = new byte[256];
        for (int i = 0; i < 256; i++) datos[i] = (byte) i;
        Ej11_CorrupcionCruzada.crearFicheroBinario(orig, datos);
        Ej11_CorrupcionCruzada.copiarConReader(orig, copiaR);
        // No aseguramos corrupcion (depende del charset), pero probamos que no falla
        assertTrue(new java.io.File(copiaR).exists());
    }

    @Test @DisplayName("contarDiferencias: 0 si son iguales")
    void contarDiferencias_iguales() throws IOException {
        String f1 = dir + "/a.bin";
        String f2 = dir + "/b.bin";
        Ej11_CorrupcionCruzada.crearFicheroBinario(f1, new byte[]{1, 2, 3});
        Ej11_CorrupcionCruzada.crearFicheroBinario(f2, new byte[]{1, 2, 3});
        assertEquals(0, Ej11_CorrupcionCruzada.contarDiferencias(f1, f2));
    }

    @Test @DisplayName("contarDiferencias: detecta diferencias")
    void contarDiferencias_distintos() throws IOException {
        String f1 = dir + "/c.bin";
        String f2 = dir + "/d.bin";
        Ej11_CorrupcionCruzada.crearFicheroBinario(f1, new byte[]{1, 2, 3});
        Ej11_CorrupcionCruzada.crearFicheroBinario(f2, new byte[]{1, 9, 3});
        assertEquals(1, Ej11_CorrupcionCruzada.contarDiferencias(f1, f2));
    }

    @Test @DisplayName("detectarTipoContenido: texto ASCII detectado como TEXTO")
    void detectarTipoContenido_texto() throws IOException {
        String ruta = dir + "/texto.txt";
        Ej11_CorrupcionCruzada.crearFicheroBinario(ruta, "Hola mundo\n".getBytes());
        assertEquals("TEXTO", Ej11_CorrupcionCruzada.detectarTipoContenido(ruta));
    }

    @Test @DisplayName("detectarTipoContenido: datos binarios detectados como BINARIO")
    void detectarTipoContenido_binario() throws IOException {
        String ruta = dir + "/bin2.dat";
        byte[] datos = new byte[100];
        for (int i = 0; i < 100; i++) datos[i] = (byte) (i + 128);
        Ej11_CorrupcionCruzada.crearFicheroBinario(ruta, datos);
        assertEquals("BINARIO", Ej11_CorrupcionCruzada.detectarTipoContenido(ruta));
    }

    @Test @DisplayName("informeCorrupcion: contiene datos clave")
    void informeCorrupcion_formato() throws IOException {
        String orig = dir + "/o.bin";
        String cr = dir + "/cr.bin";
        String cs = dir + "/cs.bin";
        byte[] datos = {1, 2, 3};
        Ej11_CorrupcionCruzada.crearFicheroBinario(orig, datos);
        Ej11_CorrupcionCruzada.copiarConReader(orig, cr);
        Ej11_CorrupcionCruzada.copiarConStream(orig, cs);
        String informe = Ej11_CorrupcionCruzada.informeCorrupcion(orig, cr, cs);
        assertTrue(informe.contains("Original:"));
        assertTrue(informe.contains("Copia Reader:"));
        assertTrue(informe.contains("Copia Stream:"));
    }

    @Test @DisplayName("esJPEG: detecta cabecera JPEG")
    void esJPEG_true() throws IOException {
        String ruta = dir + "/jpeg.bin";
        Ej11_CorrupcionCruzada.crearFicheroBinario(ruta,
                new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, 0x00});
        assertTrue(Ej11_CorrupcionCruzada.esJPEG(ruta));
    }

    @Test @DisplayName("esJPEG: rechaza fichero no-JPEG")
    void esJPEG_false() throws IOException {
        String ruta = dir + "/no_jpeg.bin";
        Ej11_CorrupcionCruzada.crearFicheroBinario(ruta, new byte[]{0, 0, 0, 0});
        assertFalse(Ej11_CorrupcionCruzada.esJPEG(ruta));
    }
}
