package bloque3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej15 - PrintWriter Formateado")
class Ej15_PrintWriterFormateadoTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() { dir = tempDir.toString(); }

    private List<String> leerLineas(String ruta) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String l; while ((l = br.readLine()) != null) lineas.add(l);
        }
        return lineas;
    }

    @Test @DisplayName("escribirLog: genera N lineas formateadas")
    void escribirLog_formato() throws IOException {
        String ruta = dir + "/log.txt";
        Ej15_PrintWriterFormateado.escribirLog(ruta,
                new String[]{"Init", "Error"}, new String[]{"INFO", "ERROR"});
        List<String> l = leerLineas(ruta);
        assertEquals(2, l.size());
        assertTrue(l.get(0).contains("INFO"));
        assertTrue(l.get(1).contains("ERROR"));
    }

    @Test @DisplayName("escribirLog: arrays distintos lanza excepcion")
    void escribirLog_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej15_PrintWriterFormateado.escribirLog(dir + "/x.txt",
                        new String[]{"A"}, new String[]{"B", "C"}));
    }

    @Test @DisplayName("generarInventario: contiene productos")
    void generarInventario_contenido() throws IOException {
        String ruta = dir + "/inv.txt";
        Ej15_PrintWriterFormateado.generarInventario(ruta,
                new String[]{"Arroz"}, new int[]{50}, new double[]{1.20});
        String todo = String.join("\n", leerLineas(ruta));
        assertTrue(todo.contains("Arroz"));
    }

    @Test @DisplayName("generarInventario: arrays distintos lanza excepcion")
    void generarInventario_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej15_PrintWriterFormateado.generarInventario(dir + "/x.txt",
                        new String[]{"A", "B"}, new int[]{1}, new double[]{1.0}));
    }

    @Test @DisplayName("escribirConPrintln: genera lineas correctas")
    void escribirConPrintln_correcto() throws IOException {
        String ruta = dir + "/println.txt";
        Ej15_PrintWriterFormateado.escribirConPrintln(ruta, new String[]{"X", "Y", "Z"});
        assertEquals(3, leerLineas(ruta).size());
    }

    @Test @DisplayName("informeVentas: contiene fecha y total")
    void informeVentas_contenido() throws IOException {
        String ruta = dir + "/ventas.txt";
        Ej15_PrintWriterFormateado.informeVentas(ruta, "2024-01-01",
                new double[]{10.0, 20.0});
        String todo = String.join("\n", leerLineas(ruta));
        assertTrue(todo.contains("2024-01-01"));
        assertTrue(todo.contains("30"));
    }

    @Test @DisplayName("anadirAlLog: no borra contenido anterior")
    void anadirAlLog_append() throws IOException {
        String ruta = dir + "/alog.txt";
        Ej15_PrintWriterFormateado.escribirConPrintln(ruta, new String[]{"A"});
        Ej15_PrintWriterFormateado.anadirAlLog(ruta, new String[]{"B"});
        List<String> l = leerLineas(ruta);
        assertTrue(l.size() >= 2);
    }

    @Test @DisplayName("tablaMultiplicar: genera 10 lineas")
    void tablaMultiplicar_lineas() throws IOException {
        String ruta = dir + "/tabla.txt";
        Ej15_PrintWriterFormateado.tablaMultiplicar(ruta, 5);
        assertEquals(10, leerLineas(ruta).size());
    }

    @Test @DisplayName("tablaMultiplicar: contiene resultados correctos")
    void tablaMultiplicar_valores() throws IOException {
        String ruta = dir + "/tabla3.txt";
        Ej15_PrintWriterFormateado.tablaMultiplicar(ruta, 3);
        String todo = String.join("\n", leerLineas(ruta));
        assertTrue(todo.contains("30")); // 3x10=30
    }

    @Test @DisplayName("lineasEsperadas: devuelve el numero de mensajes")
    void lineasEsperadas_correcto() {
        assertEquals(5, Ej15_PrintWriterFormateado.lineasEsperadas(5));
    }

    @Test @DisplayName("lineasEsperadas: 0 mensajes devuelve 0")
    void lineasEsperadas_cero() {
        assertEquals(0, Ej15_PrintWriterFormateado.lineasEsperadas(0));
    }
}
