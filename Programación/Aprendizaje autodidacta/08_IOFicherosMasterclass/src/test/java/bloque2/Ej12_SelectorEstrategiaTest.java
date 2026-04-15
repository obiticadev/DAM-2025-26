package bloque2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej12 - Selector de Estrategia de I/O")
class Ej12_SelectorEstrategiaTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() {
        dir = tempDir.toString();
    }

    @Test @DisplayName("recomendarFamilia: txt devuelve Reader/Writer")
    void recomendarFamilia_texto() {
        assertEquals("Reader/Writer", Ej12_SelectorEstrategia.recomendarFamilia("txt"));
    }

    @Test @DisplayName("recomendarFamilia: jpg devuelve InputStream/OutputStream")
    void recomendarFamilia_binario() {
        assertEquals("InputStream/OutputStream", Ej12_SelectorEstrategia.recomendarFamilia("jpg"));
    }

    @Test @DisplayName("recomendarFamilia: extension desconocida lanza excepcion")
    void recomendarFamilia_desconocida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej12_SelectorEstrategia.recomendarFamilia("abc"));
    }

    @Test @DisplayName("recomendarClase: csv + leer = FileReader")
    void recomendarClase_textoLeer() {
        assertEquals("FileReader", Ej12_SelectorEstrategia.recomendarClase("csv", "leer"));
    }

    @Test @DisplayName("recomendarClase: dat + escribir = FileOutputStream")
    void recomendarClase_binarioEscribir() {
        assertEquals("FileOutputStream", Ej12_SelectorEstrategia.recomendarClase("dat", "escribir"));
    }

    @Test @DisplayName("recomendarClase: operacion invalida lanza excepcion")
    void recomendarClase_operacionInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej12_SelectorEstrategia.recomendarClase("txt", "borrar"));
    }

    @Test @DisplayName("leerComoTexto: devuelve contenido del fichero")
    void leerComoTexto_correcto() throws IOException {
        String ruta = dir + "/t.txt";
        try (FileWriter fw = new FileWriter(ruta)) { fw.write("Hola"); }
        assertEquals("Hola", Ej12_SelectorEstrategia.leerComoTexto(ruta));
    }

    @Test @DisplayName("leerComoBinario: devuelve bytes correctos")
    void leerComoBinario_correcto() throws IOException {
        String ruta = dir + "/b.bin";
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(new byte[]{10, 20, 30});
        }
        byte[] r = Ej12_SelectorEstrategia.leerComoBinario(ruta);
        assertNotNull(r);
        assertArrayEquals(new byte[]{10, 20, 30}, r);
    }

    @Test @DisplayName("copiarInteligente: texto devuelve TEXTO")
    void copiarInteligente_texto() throws IOException {
        String orig = dir + "/nota.txt";
        try (FileWriter fw = new FileWriter(orig)) { fw.write("Test"); }
        assertEquals("TEXTO", Ej12_SelectorEstrategia.copiarInteligente(orig, dir + "/copia.txt"));
    }

    @Test @DisplayName("copiarInteligente: binario devuelve BINARIO")
    void copiarInteligente_binario() throws IOException {
        String orig = dir + "/img.jpg";
        try (FileOutputStream fos = new FileOutputStream(orig)) { fos.write(new byte[]{1, 2}); }
        assertEquals("BINARIO", Ej12_SelectorEstrategia.copiarInteligente(orig, dir + "/copia.jpg"));
    }

    @Test @DisplayName("diagnostico: contiene nombre y familia")
    void diagnostico_formato() throws IOException {
        String ruta = dir + "/info.csv";
        try (FileWriter fw = new FileWriter(ruta)) { fw.write("a;b;c"); }
        String diag = Ej12_SelectorEstrategia.diagnostico(ruta);
        assertTrue(diag.contains("info.csv"));
        assertTrue(diag.contains("csv"));
    }

    @Test @DisplayName("resumenLote: cuenta correctamente texto y binario")
    void resumenLote_conteo() {
        String[] rutas = {"a.txt", "b.jpg", "c.csv", "d.dat"};
        String resumen = Ej12_SelectorEstrategia.resumenLote(rutas);
        assertTrue(resumen.contains("Total: 4"));
        assertTrue(resumen.contains("Texto: 2"));
        assertTrue(resumen.contains("Binario: 2"));
    }

    @Test @DisplayName("resumenLote: extension desconocida cuenta como Desconocido")
    void resumenLote_desconocido() {
        String[] rutas = {"a.txt", "b.xyz"};
        String resumen = Ej12_SelectorEstrategia.resumenLote(rutas);
        assertTrue(resumen.contains("Desconocido: 1"));
    }
}
