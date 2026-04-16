package bloque6;

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

@DisplayName("Ej32 - Escribir un Fichero CSV")
class Ej32_EscribirCSVTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    private List<String> leer(String ruta) throws IOException {
        List<String> l = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String ln; while ((ln = br.readLine()) != null) l.add(ln);
        }
        return l;
    }

    @Test @DisplayName("escribirProductos: genera cabecera + registros")
    void escribirProductos() throws IOException {
        String ruta = dir + "/p.csv";
        Ej32_EscribirCSV.escribirProductos(ruta,
                new String[]{"A", "B"}, new double[]{1.0, 2.0}, new int[]{10, 20});
        List<String> l = leer(ruta);
        assertEquals(3, l.size()); // cabecera + 2
        assertTrue(l.get(0).contains("nombre"));
    }

    @Test @DisplayName("escribirProductos: arrays distintos lanza excepcion")
    void escribirProductos_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej32_EscribirCSV.escribirProductos(dir + "/x.csv",
                        new String[]{"A"}, new double[]{1.0, 2.0}, new int[]{10}));
    }

    @Test @DisplayName("escribirGenerico: usa delimitador correcto")
    void escribirGenerico() throws IOException {
        String ruta = dir + "/g.csv";
        List<String[]> regs = List.of(new String[]{"A", "1"}, new String[]{"B", "2"});
        Ej32_EscribirCSV.escribirGenerico(ruta, new String[]{"col1", "col2"}, regs, "|");
        List<String> l = leer(ruta);
        assertTrue(l.get(0).contains("|"));
    }

    @Test @DisplayName("anadirRegistro: anade al final")
    void anadirRegistro() throws IOException {
        String ruta = dir + "/ap.csv";
        Ej32_EscribirCSV.escribirProductos(ruta,
                new String[]{"A"}, new double[]{1.0}, new int[]{10});
        Ej32_EscribirCSV.anadirRegistro(ruta, "B;2.0;20");
        assertEquals(3, leer(ruta).size());
    }

    @Test @DisplayName("tablaMultiplicarCSV: genera n+1 lineas")
    void tablaMultiplicar() throws IOException {
        String ruta = dir + "/m.csv";
        Ej32_EscribirCSV.tablaMultiplicarCSV(ruta, 3);
        assertEquals(4, leer(ruta).size()); // cabecera + 3 filas
    }

    @Test @DisplayName("convertirDelimitador: cambia ; por |")
    void convertirDelimitador() throws IOException {
        String orig = dir + "/orig.csv";
        Ej32_EscribirCSV.escribirProductos(orig,
                new String[]{"A"}, new double[]{1.0}, new int[]{10});
        String dest = dir + "/conv.csv";
        int n = Ej32_EscribirCSV.convertirDelimitador(orig, dest, ";", "|");
        assertTrue(n >= 2);
        assertTrue(leer(dest).get(0).contains("|"));
    }

    @Test @DisplayName("generarResumen: contiene metricas")
    void generarResumen() throws IOException {
        String ruta = dir + "/res.csv";
        Ej32_EscribirCSV.generarResumen(ruta, 5, 2.50, 100);
        String todo = String.join("\n", leer(ruta));
        assertTrue(todo.contains("5"));
        assertTrue(todo.contains("100"));
    }

    @Test @DisplayName("unirCampos: une con delimitador")
    void unirCampos() {
        assertEquals("A;B;C", Ej32_EscribirCSV.unirCampos(new String[]{"A", "B", "C"}, ";"));
    }
}
