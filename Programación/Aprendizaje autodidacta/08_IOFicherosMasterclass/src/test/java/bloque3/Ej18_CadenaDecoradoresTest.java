package bloque3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej18 - Cadena de Decoradores y DataStreams")
class Ej18_CadenaDecoradoresTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("escribirEnteros + leerEnteros: ida y vuelta")
    void enterosIdaVuelta() throws IOException {
        String ruta = dir + "/ints.bin";
        int[] datos = {100, -200, 0, Integer.MAX_VALUE};
        Ej18_CadenaDecoradores.escribirEnteros(ruta, datos);
        int[] leidos = Ej18_CadenaDecoradores.leerEnteros(ruta, 4);
        assertArrayEquals(datos, leidos);
    }

    @Test @DisplayName("escribirEnteros: tamano correcto (N*4 bytes)")
    void enterosBytes() throws IOException {
        String ruta = dir + "/ints2.bin";
        Ej18_CadenaDecoradores.escribirEnteros(ruta, new int[]{1, 2, 3});
        assertEquals(12, new File(ruta).length());
    }

    @Test @DisplayName("escribirFactura + leerFactura: ida y vuelta")
    void facturaIdaVuelta() throws IOException {
        String ruta = dir + "/fact.bin";
        Ej18_CadenaDecoradores.escribirFactura(ruta, 1001, new double[]{12.50, 8.00});
        String resumen = Ej18_CadenaDecoradores.leerFactura(ruta);
        assertTrue(resumen.contains("1001"));
        assertTrue(resumen.contains("20.5") || resumen.contains("20,5"));
    }

    @Test @DisplayName("leerFactura: formato contiene Items")
    void facturaFormato() throws IOException {
        String ruta = dir + "/fact2.bin";
        Ej18_CadenaDecoradores.escribirFactura(ruta, 5, new double[]{1.0, 2.0, 3.0});
        String r = Ej18_CadenaDecoradores.leerFactura(ruta);
        assertTrue(r.contains("Items: 3") || r.contains("Items:3"));
    }

    @Test @DisplayName("escribirMapaBinario + leerMapaBinario: ida y vuelta")
    void mapaIdaVuelta() throws IOException {
        String ruta = dir + "/mapa.bin";
        Ej18_CadenaDecoradores.escribirMapaBinario(ruta,
                new String[]{"A", "B"}, new double[]{1.5, 2.5});
        String contenido = Ej18_CadenaDecoradores.leerMapaBinario(ruta);
        assertTrue(contenido.contains("A"));
        assertTrue(contenido.contains("B"));
        assertTrue(contenido.contains("1.5") || contenido.contains("1,5"));
    }

    @Test @DisplayName("escribirMapaBinario: arrays distintos lanza excepcion")
    void mapaInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej18_CadenaDecoradores.escribirMapaBinario(dir + "/x.bin",
                        new String[]{"A"}, new double[]{1.0, 2.0}));
    }

    @Test @DisplayName("tamanoEsperadoEnteros: 5 enteros = 20 bytes")
    void tamanoEsperado_correcto() {
        assertEquals(20, Ej18_CadenaDecoradores.tamanoEsperadoEnteros(5));
    }

    @Test @DisplayName("tamanoEsperadoEnteros: 0 enteros = 0 bytes")
    void tamanoEsperado_cero() {
        assertEquals(0, Ej18_CadenaDecoradores.tamanoEsperadoEnteros(0));
    }
}
