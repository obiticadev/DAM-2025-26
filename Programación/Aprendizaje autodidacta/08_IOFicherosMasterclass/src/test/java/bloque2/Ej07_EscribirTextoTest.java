package bloque2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej07 - Escribir Texto con FileWriter")
class Ej07_EscribirTextoTest {

    @TempDir
    Path tempDir;
    String dir;

    @BeforeEach
    void setUp() {
        dir = tempDir.toString();
    }

    private String leer(String ruta) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(ruta)) {
            int c;
            while ((c = fr.read()) != -1) sb.append((char) c);
        }
        return sb.toString();
    }

    @Test @DisplayName("escribir: crea fichero con contenido correcto")
    void escribir_contenido() throws IOException {
        String ruta = dir + "/test.txt";
        Ej07_EscribirTexto.escribir(ruta, "Hola Hotel");
        assertEquals("Hola Hotel", leer(ruta));
    }

    @Test @DisplayName("escribir: sobrescribe fichero existente")
    void escribir_sobrescribe() throws IOException {
        String ruta = dir + "/over.txt";
        Ej07_EscribirTexto.escribir(ruta, "Primero");
        Ej07_EscribirTexto.escribir(ruta, "Segundo");
        assertEquals("Segundo", leer(ruta));
    }

    @Test @DisplayName("escribirLineasConSalto: cada linea termina en salto")
    void escribirLineasConSalto_formato() throws IOException {
        String ruta = dir + "/lineas.txt";
        Ej07_EscribirTexto.escribirLineasConSalto(ruta, new String[]{"A", "B"});
        assertEquals("A\nB\n", leer(ruta));
    }

    @Test @DisplayName("escribirLineasConSalto: array vacio genera fichero vacio")
    void escribirLineasConSalto_vacio() throws IOException {
        String ruta = dir + "/vacio.txt";
        Ej07_EscribirTexto.escribirLineasConSalto(ruta, new String[0]);
        assertEquals("", leer(ruta));
    }

    @Test @DisplayName("generarFichaHuesped: contiene nombre y habitacion")
    void generarFichaHuesped_contenido() throws IOException {
        String ruta = dir + "/ficha.txt";
        Ej07_EscribirTexto.generarFichaHuesped(ruta, "Ana", "12345678A", 301, 3);
        String contenido = leer(ruta);
        assertTrue(contenido.contains("Ana"));
        assertTrue(contenido.contains("301"));
        assertTrue(contenido.contains("12345678A"));
    }

    @Test @DisplayName("generarFichaHuesped: contiene separadores")
    void generarFichaHuesped_separadores() throws IOException {
        String ruta = dir + "/ficha2.txt";
        Ej07_EscribirTexto.generarFichaHuesped(ruta, "Luis", "99999999Z", 101, 1);
        String contenido = leer(ruta);
        assertTrue(contenido.contains("===="));
    }

    @Test @DisplayName("anadirAlFinal: anade sin borrar lo anterior")
    void anadirAlFinal_append() throws IOException {
        String ruta = dir + "/append.txt";
        Ej07_EscribirTexto.escribir(ruta, "Inicio");
        Ej07_EscribirTexto.anadirAlFinal(ruta, "-Fin");
        assertEquals("Inicio-Fin", leer(ruta));
    }

    @Test @DisplayName("generarTablaPrecios: contiene servicios y precios")
    void generarTablaPrecios_contenido() throws IOException {
        String ruta = dir + "/precios.txt";
        Ej07_EscribirTexto.generarTablaPrecios(ruta,
                new String[]{"Parking", "WiFi"}, new double[]{15.50, 0.00});
        String contenido = leer(ruta);
        assertTrue(contenido.contains("Parking"));
        assertTrue(contenido.contains("15"));
    }

    @Test @DisplayName("generarTablaPrecios: lanza excepcion si arrays de distinta longitud")
    void generarTablaPrecios_longitudDistinta() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej07_EscribirTexto.generarTablaPrecios(dir + "/x.txt",
                        new String[]{"A"}, new double[]{1.0, 2.0}));
    }

    @Test @DisplayName("escribirRepetido: AB x3 = AAABBB")
    void escribirRepetido_correcto() throws IOException {
        String ruta = dir + "/rep.txt";
        Ej07_EscribirTexto.escribirRepetido(ruta, "AB", 3);
        assertEquals("AAABBB", leer(ruta));
    }

    @Test @DisplayName("escribirRepetido: lanza excepcion si veces < 1")
    void escribirRepetido_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej07_EscribirTexto.escribirRepetido(dir + "/x.txt", "A", 0));
    }

    @Test @DisplayName("caracteresEscritos: devuelve tamano correcto")
    void caracteresEscritos_tamano() throws IOException {
        String ruta = dir + "/tam.txt";
        Ej07_EscribirTexto.escribir(ruta, "12345");
        assertEquals(5, Ej07_EscribirTexto.caracteresEscritos(ruta));
    }

    @Test @DisplayName("caracteresEscritos: fichero inexistente devuelve 0")
    void caracteresEscritos_noExiste() {
        assertEquals(0, Ej07_EscribirTexto.caracteresEscritos(dir + "/noexiste.txt"));
    }
}
