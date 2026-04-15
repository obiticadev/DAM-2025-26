package bloque3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej16 - Buffered Streams Binarios")
class Ej16_BufferedBinarioTest {

    @TempDir
    Path tempDir;
    String dir;
    String origen;

    @BeforeEach
    void setUp() throws IOException {
        dir = tempDir.toString();
        origen = dir + "/orig.bin";
        Ej16_BufferedBinario.generarFicheroPrueba(origen, 1000);
    }

    @Test @DisplayName("copiarBuffered: copia todos los bytes")
    void copiarBuffered_completo() throws IOException {
        String dest = dir + "/cp1.bin";
        long n = Ej16_BufferedBinario.copiarBuffered(origen, dest);
        assertEquals(1000, n);
    }

    @Test @DisplayName("copiarBuffered: copia identica")
    void copiarBuffered_identica() throws IOException {
        String dest = dir + "/cp2.bin";
        Ej16_BufferedBinario.copiarBuffered(origen, dest);
        assertTrue(Ej16_BufferedBinario.sonIdenticos(origen, dest));
    }

    @Test @DisplayName("copiarConTamBuffer: copia correcta con buffer 256")
    void copiarConTamBuffer_correcto() throws IOException {
        String dest = dir + "/cp3.bin";
        long n = Ej16_BufferedBinario.copiarConTamBuffer(origen, dest, 256);
        assertEquals(1000, n);
        assertTrue(Ej16_BufferedBinario.sonIdenticos(origen, dest));
    }

    @Test @DisplayName("copiarConTamBuffer: buffer <= 0 lanza excepcion")
    void copiarConTamBuffer_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej16_BufferedBinario.copiarConTamBuffer(origen, dir + "/x.bin", 0));
    }

    @Test @DisplayName("compararRendimiento: devuelve dos tiempos positivos")
    void compararRendimiento_tiempos() throws IOException {
        long[] t = Ej16_BufferedBinario.compararRendimiento(origen, dir);
        assertEquals(2, t.length);
        assertTrue(t[0] >= 0);
        assertTrue(t[1] >= 0);
    }

    @Test @DisplayName("generarFicheroPrueba: tamano correcto")
    void generarFicheroPrueba_tamano() throws IOException {
        String ruta = dir + "/gen.bin";
        Ej16_BufferedBinario.generarFicheroPrueba(ruta, 500);
        assertEquals(500, new File(ruta).length());
    }

    @Test @DisplayName("sonIdenticos: true para mismos datos")
    void sonIdenticos_true() throws IOException {
        String copia = dir + "/id.bin";
        Ej16_BufferedBinario.copiarBuffered(origen, copia);
        assertTrue(Ej16_BufferedBinario.sonIdenticos(origen, copia));
    }

    @Test @DisplayName("sonIdenticos: false para datos distintos")
    void sonIdenticos_false() throws IOException {
        String otro = dir + "/otro.bin";
        Ej16_BufferedBinario.generarFicheroPrueba(otro, 500);
        assertFalse(Ej16_BufferedBinario.sonIdenticos(origen, otro));
    }

    @Test @DisplayName("informeRendimiento: genera lineas para cada tamano")
    void informeRendimiento_lineas() throws IOException {
        String informe = Ej16_BufferedBinario.informeRendimiento(
                origen, dir, new int[]{256, 1024});
        String[] lineas = informe.split("\n");
        assertEquals(2, lineas.length);
    }

    @Test @DisplayName("mejoraPorcentual: calculo correcto")
    void mejoraPorcentual_correcta() {
        double m = Ej16_BufferedBinario.mejoraPorcentual(1000, 200);
        assertEquals(80.0, m, 0.01);
    }

    @Test @DisplayName("mejoraPorcentual: tiempoLento <= 0 lanza excepcion")
    void mejoraPorcentual_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej16_BufferedBinario.mejoraPorcentual(0, 100));
    }
}
