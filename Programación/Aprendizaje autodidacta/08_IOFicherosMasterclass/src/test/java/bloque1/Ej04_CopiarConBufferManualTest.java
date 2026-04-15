package bloque1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej04 - Copiar con Buffer Manual y Comparativa")
class Ej04_CopiarConBufferManualTest {

    @TempDir
    Path tempDir;
    String origen;

    @BeforeEach
    void setUp() throws IOException {
        origen = tempDir.resolve("origen.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(origen)) {
            byte[] datos = new byte[1000];
            for (int i = 0; i < datos.length; i++) datos[i] = (byte) (i % 256);
            fos.write(datos);
        }
    }

    @Test @DisplayName("copiarContandoViajes: cuenta viajes correctamente con buffer 256")
    void copiarContandoViajes_cuenta() throws IOException {
        String destino = tempDir.resolve("d1.bin").toString();
        int viajes = Ej04_CopiarConBufferManual.copiarContandoViajes(origen, destino, 256);
        assertEquals(4, viajes); // 1000 / 256 = 3.9 -> 4 lecturas
    }

    @Test @DisplayName("copiarContandoViajes: lanza excepcion si buffer <= 0")
    void copiarContandoViajes_bufferInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej04_CopiarConBufferManual.copiarContandoViajes(origen,
                        tempDir.resolve("x.bin").toString(), 0));
    }

    @Test @DisplayName("viajesTeoricos: calculo correcto exacto")
    void viajesTeoricos_exacto() {
        assertEquals(4, Ej04_CopiarConBufferManual.viajesTeoricos(1024, 256));
    }

    @Test @DisplayName("viajesTeoricos: calculo correcto con resto")
    void viajesTeoricos_conResto() {
        assertEquals(4, Ej04_CopiarConBufferManual.viajesTeoricos(1000, 256));
    }

    @Test @DisplayName("viajesTeoricos: lanza excepcion si parametros invalidos")
    void viajesTeoricos_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej04_CopiarConBufferManual.viajesTeoricos(100, 0));
    }

    @Test @DisplayName("medirTiempoNanos: devuelve tiempo positivo")
    void medirTiempoNanos_positivo() throws IOException {
        long t = Ej04_CopiarConBufferManual.medirTiempoNanos(origen,
                tempDir.resolve("t.bin").toString(), 512);
        assertTrue(t >= 0);
    }

    @Test @DisplayName("informeComparativo: genera lineas para cada tamano")
    void informeComparativo_lineas() throws IOException {
        int[] tamanos = {256, 1024};
        String informe = Ej04_CopiarConBufferManual.informeComparativo(
                origen, tempDir.resolve("cmp").toString(), tamanos);
        assertNotNull(informe);
        String[] lineas = informe.split("\n");
        assertEquals(2, lineas.length);
    }

    @Test @DisplayName("informeComparativo: array vacio devuelve cadena vacia")
    void informeComparativo_vacio() throws IOException {
        String informe = Ej04_CopiarConBufferManual.informeComparativo(
                origen, tempDir.resolve("cmp2").toString(), new int[0]);
        assertEquals("", informe);
    }

    @Test @DisplayName("indiceMasRapido: encuentra el indice del minimo")
    void indiceMasRapido_correcto() {
        assertEquals(2, Ej04_CopiarConBufferManual.indiceMasRapido(
                new long[]{100, 50, 10, 80}));
    }

    @Test @DisplayName("indiceMasRapido: lanza excepcion con array vacio")
    void indiceMasRapido_vacio() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej04_CopiarConBufferManual.indiceMasRapido(new long[0]));
    }

    @Test @DisplayName("generarFichero: tamano correcto")
    void generarFichero_tamano() throws IOException {
        String ruta = tempDir.resolve("gen.bin").toString();
        Ej04_CopiarConBufferManual.generarFichero(ruta, 500);
        assertEquals(500, new File(ruta).length());
    }

    @Test @DisplayName("resumenMejor: formato correcto")
    void resumenMejor_formato() {
        int[] tamanos = {64, 256, 1024};
        long[] tiempos = {300, 100, 50};
        String resumen = Ej04_CopiarConBufferManual.resumenMejor(tamanos, tiempos);
        assertTrue(resumen.contains("1024"));
        assertTrue(resumen.contains("50"));
    }
}
