package bloque6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej36 - Validar y Limpiar un CSV")
class Ej36_ValidarCSVTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        ruta = dir + "/dirty.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;xxx;20\n");
            bw.write("Sal;0.80\n");        // falta stock
            bw.write("Pan;1.50;30\n");
            bw.write("Arroz;1.20;50\n");   // duplicado
        }
    }

    @Test @DisplayName("separarValidosInvalidos: 4 validos, 1 invalido")
    void separarValidosInvalidos() throws IOException {
        int[] r = Ej36_ValidarCSV.separarValidosInvalidos(ruta, dir + "/ok.csv", dir + "/bad.csv");
        assertEquals(4, r[0]);
        assertEquals(1, r[1]);
    }

    @Test @DisplayName("lineasConErrorNumerico: detecta Aceite;xxx")
    void lineasConErrorNumerico() throws IOException {
        List<String> l = Ej36_ValidarCSV.lineasConErrorNumerico(ruta);
        assertTrue(l.size() >= 1);
        assertTrue(l.get(0).contains("xxx"));
    }

    @Test @DisplayName("eliminarDuplicados: elimina 1 duplicado")
    void eliminarDuplicados() throws IOException {
        // Usar CSV limpio sin linea invalida
        String clean = dir + "/clean.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(clean))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Pan;1.50;30\n");
            bw.write("Arroz;1.20;50\n");
        }
        assertEquals(1, Ej36_ValidarCSV.eliminarDuplicados(clean, dir + "/uniq.csv"));
    }

    @Test @DisplayName("limpiarCSV: elimina lineas vacias y hace trim")
    void limpiarCSV() throws IOException {
        String sp = dir + "/sp.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(sp))) {
            bw.write("nombre ; precio ; stock\n");
            bw.write("  Arroz ; 1.20 ; 50  \n");
            bw.write("\n");
            bw.write("Pan;1.50;30\n");
        }
        int n = Ej36_ValidarCSV.limpiarCSV(sp, dir + "/limpio.csv");
        assertEquals(3, n); // cabecera + 2 datos
    }

    @Test @DisplayName("verificarCabecera: true si coincide")
    void verificarCabecera_true() throws IOException {
        assertTrue(Ej36_ValidarCSV.verificarCabecera(ruta,
                new String[]{"nombre", "precio", "stock"}, ";"));
    }

    @Test @DisplayName("verificarCabecera: false si no coincide")
    void verificarCabecera_false() throws IOException {
        assertFalse(Ej36_ValidarCSV.verificarCabecera(ruta,
                new String[]{"id", "name"}, ";"));
    }

    @Test @DisplayName("informeValidacion: contiene Total lineas")
    void informeValidacion() throws IOException {
        String inf = Ej36_ValidarCSV.informeValidacion(ruta);
        assertTrue(inf.contains("Total lineas"));
    }

    @Test @DisplayName("esNumero: true para 3.14")
    void esNumero_true() { assertTrue(Ej36_ValidarCSV.esNumero("3.14")); }

    @Test @DisplayName("esNumero: false para abc")
    void esNumero_false() { assertFalse(Ej36_ValidarCSV.esNumero("abc")); }
}
