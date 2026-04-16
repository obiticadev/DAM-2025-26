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

@DisplayName("Ej13 - BufferedWriter Basico")
class Ej13_BufferedWriterBasicoTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() { dir = tempDir.toString(); }

    private List<String> leerLineas(String ruta) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String l;
            while ((l = br.readLine()) != null) lineas.add(l);
        }
        return lineas;
    }

    @Test @DisplayName("escribirLineas: escribe correctamente sin salto final")
    void escribirLineas_correcto() throws IOException {
        String ruta = dir + "/lineas.txt";
        Ej13_BufferedWriterBasico.escribirLineas(ruta, new String[]{"A", "B", "C"});
        List<String> l = leerLineas(ruta);
        assertEquals(3, l.size());
        assertEquals("A", l.get(0));
        assertEquals("C", l.get(2));
    }

    @Test @DisplayName("escribirLineas: array vacio genera fichero vacio")
    void escribirLineas_vacio() throws IOException {
        String ruta = dir + "/vacio.txt";
        Ej13_BufferedWriterBasico.escribirLineas(ruta, new String[0]);
        assertEquals(0, leerLineas(ruta).size());
    }

    @Test @DisplayName("generarTicket: contiene numero y total")
    void generarTicket_contenido() throws IOException {
        String ruta = dir + "/ticket.txt";
        Ej13_BufferedWriterBasico.generarTicket(ruta, 7,
                new String[]{"Arroz", "Agua"}, new double[]{10.0, 2.0});
        List<String> l = leerLineas(ruta);
        String todo = String.join("\n", l);
        assertTrue(todo.contains("7"));
        assertTrue(todo.contains("12"));
    }

    @Test @DisplayName("generarTicket: arrays distintos lanza excepcion")
    void generarTicket_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej13_BufferedWriterBasico.generarTicket(dir + "/x.txt", 1,
                        new String[]{"A"}, new double[]{1, 2}));
    }

    @Test @DisplayName("escribirListaNumerada: formato correcto")
    void escribirListaNumerada_formato() throws IOException {
        String ruta = dir + "/lista.txt";
        Ej13_BufferedWriterBasico.escribirListaNumerada(ruta, new String[]{"X", "Y"});
        List<String> l = leerLineas(ruta);
        assertTrue(l.get(0).startsWith("1."));
        assertTrue(l.get(1).startsWith("2."));
    }

    @Test @DisplayName("anadirLineas: no borra lo anterior")
    void anadirLineas_append() throws IOException {
        String ruta = dir + "/app.txt";
        Ej13_BufferedWriterBasico.escribirLineas(ruta, new String[]{"Inicio"});
        Ej13_BufferedWriterBasico.anadirLineas(ruta, new String[]{"Fin"});
        List<String> l = leerLineas(ruta);
        assertTrue(l.size() >= 2);
    }

    @Test @DisplayName("escribirConFlush: escribe correctamente")
    void escribirConFlush_correcto() throws IOException {
        String ruta = dir + "/flush.txt";
        Ej13_BufferedWriterBasico.escribirConFlush(ruta, new String[]{"A", "B"});
        assertEquals(2, leerLineas(ruta).size());
    }

    @Test @DisplayName("generarFicheroGrande: genera N lineas")
    void generarFicheroGrande_tamano() throws IOException {
        String ruta = dir + "/grande.txt";
        Ej13_BufferedWriterBasico.generarFicheroGrande(ruta, 50, "X");
        assertEquals(50, leerLineas(ruta).size());
    }

    @Test @DisplayName("generarFicheroGrande: numLineas negativo lanza excepcion")
    void generarFicheroGrande_negativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej13_BufferedWriterBasico.generarFicheroGrande(dir + "/x.txt", -1, "X"));
    }

    @Test @DisplayName("calcularCaracteres: calculo correcto")
    void calcularCaracteres_correcto() {
        // "ABC" + \n + "DE" + \n + "F" = 3 + 1 + 2 + 1 + 1 = 8
        assertEquals(8, Ej13_BufferedWriterBasico.calcularCaracteres(
                new String[]{"ABC", "DE", "F"}));
    }

    @Test @DisplayName("calcularCaracteres: array vacio devuelve 0")
    void calcularCaracteres_vacio() {
        assertEquals(0, Ej13_BufferedWriterBasico.calcularCaracteres(new String[0]));
    }
}
