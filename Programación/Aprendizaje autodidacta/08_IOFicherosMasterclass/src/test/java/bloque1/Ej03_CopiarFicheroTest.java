package bloque1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej03 - Copiar un Fichero Byte a Byte")
class Ej03_CopiarFicheroTest {

    @TempDir
    Path tempDir;
    String origen;

    @BeforeEach
    void setUp() throws IOException {
        origen = tempDir.resolve("origen.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(origen)) {
            byte[] datos = new byte[500];
            for (int i = 0; i < datos.length; i++) datos[i] = (byte) (i % 256);
            fos.write(datos);
        }
    }

    @Test @DisplayName("copiarByteAByte: copia correcta de contenido")
    void copiarByteAByte_contenido() throws IOException {
        String destino = tempDir.resolve("dest_bab.bin").toString();
        long copiados = Ej03_CopiarFichero.copiarByteAByte(origen, destino);
        assertEquals(500, copiados);
        assertEquals(new File(origen).length(), new File(destino).length());
    }

    @Test @DisplayName("copiarByteAByte: fichero vacio devuelve 0")
    void copiarByteAByte_vacio() throws IOException {
        String vacio = tempDir.resolve("vacio.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(vacio)) { /* vacio */ }
        String destino = tempDir.resolve("dest_vacio.bin").toString();
        assertEquals(0, Ej03_CopiarFichero.copiarByteAByte(vacio, destino));
    }

    @Test @DisplayName("copiarConBuffer: copia correcta con buffer 64")
    void copiarConBuffer_resultado() throws IOException {
        String destino = tempDir.resolve("dest_buf.bin").toString();
        long copiados = Ej03_CopiarFichero.copiarConBuffer(origen, destino, 64);
        assertEquals(500, copiados);
    }

    @Test @DisplayName("copiarConBuffer: lanza excepcion si buffer <= 0")
    void copiarConBuffer_bufferInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej03_CopiarFichero.copiarConBuffer(origen,
                        tempDir.resolve("x.bin").toString(), 0));
    }

    @Test @DisplayName("medirTiempoByteAByte: devuelve tiempo >= 0")
    void medirTiempoByteAByte_positivo() throws IOException {
        long t = Ej03_CopiarFichero.medirTiempoByteAByte(origen,
                tempDir.resolve("t1.bin").toString());
        assertTrue(t >= 0);
    }

    @Test @DisplayName("medirTiempoConBuffer: devuelve tiempo >= 0")
    void medirTiempoConBuffer_positivo() throws IOException {
        long t = Ej03_CopiarFichero.medirTiempoConBuffer(origen,
                tempDir.resolve("t2.bin").toString(), 256);
        assertTrue(t >= 0);
    }

    @Test @DisplayName("generarFicheroPrueba: genera fichero con tamano correcto")
    void generarFicheroPrueba_tamano() throws IOException {
        String ruta = tempDir.resolve("gen.bin").toString();
        Ej03_CopiarFichero.generarFicheroPrueba(ruta, 1000);
        assertEquals(1000, new File(ruta).length());
    }

    @Test @DisplayName("generarFicheroPrueba: tamano negativo lanza excepcion")
    void generarFicheroPrueba_negativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej03_CopiarFichero.generarFicheroPrueba(
                        tempDir.resolve("neg.bin").toString(), -1));
    }

    @Test @DisplayName("verificarCopia: true si son identicos")
    void verificarCopia_identicos() throws IOException {
        String destino = tempDir.resolve("copia.bin").toString();
        Ej03_CopiarFichero.copiarConBuffer(origen, destino, 128);
        assertTrue(Ej03_CopiarFichero.verificarCopia(origen, destino));
    }

    @Test @DisplayName("verificarCopia: false si son diferentes")
    void verificarCopia_diferentes() throws IOException {
        String otro = tempDir.resolve("otro.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(otro)) {
            fos.write(new byte[]{1, 2, 3});
        }
        assertFalse(Ej03_CopiarFichero.verificarCopia(origen, otro));
    }

    @Test @DisplayName("resumenRendimiento: formato correcto")
    void resumenRendimiento_formato() {
        String r = Ej03_CopiarFichero.resumenRendimiento(100, 10, 4096);
        assertTrue(r.contains("Byte a byte: 100 ms"));
        assertTrue(r.contains("Buffer (4096 bytes): 10 ms"));
        assertTrue(r.contains("Speedup:"));
    }

    @Test @DisplayName("resumenRendimiento: no falla con tiempoBuffer 0")
    void resumenRendimiento_divisionPorCero() {
        String r = Ej03_CopiarFichero.resumenRendimiento(50, 0, 1024);
        assertNotNull(r);
        assertFalse(r.isEmpty());
    }
}
