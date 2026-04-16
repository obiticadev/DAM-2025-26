package bloque1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej05 - Flujo de Caracteres Basico")
class Ej05_FlujoCaracteresTest {

    @TempDir
    Path tempDir;
    String fichero;

    @BeforeEach
    void setUp() throws IOException {
        fichero = tempDir.resolve("texto.txt").toString();
        try (FileWriter fw = new FileWriter(fichero)) {
            fw.write("Linea uno\nLinea dos\nLinea tres");
        }
    }

    @Test @DisplayName("escribirLineas: escribe todas las lineas")
    void escribirLineas_completo() throws IOException {
        String ruta = tempDir.resolve("lineas.txt").toString();
        Ej05_FlujoCaracteres.escribirLineas(ruta, new String[]{"A", "B", "C"});
        String contenido = Ej05_FlujoCaracteres.leerTodo(ruta);
        assertEquals("A\nB\nC", contenido);
    }

    @Test @DisplayName("escribirLineas: array de una sola linea sin salto final")
    void escribirLineas_unaLinea() throws IOException {
        String ruta = tempDir.resolve("una.txt").toString();
        Ej05_FlujoCaracteres.escribirLineas(ruta, new String[]{"Sola"});
        assertEquals("Sola", Ej05_FlujoCaracteres.leerTodo(ruta));
    }

    @Test @DisplayName("leerTodo: devuelve contenido completo")
    void leerTodo_completo() throws IOException {
        String contenido = Ej05_FlujoCaracteres.leerTodo(fichero);
        assertEquals("Linea uno\nLinea dos\nLinea tres", contenido);
    }

    @Test @DisplayName("leerTodo: fichero vacio devuelve cadena vacia")
    void leerTodo_vacio() throws IOException {
        String vacio = tempDir.resolve("vacio.txt").toString();
        try (FileWriter fw = new FileWriter(vacio)) { /* vacio */ }
        assertEquals("", Ej05_FlujoCaracteres.leerTodo(vacio));
    }

    @Test @DisplayName("contarCaracteres: cuenta todos incluidos saltos")
    void contarCaracteres_total() throws IOException {
        int total = Ej05_FlujoCaracteres.contarCaracteres(fichero);
        assertEquals("Linea uno\nLinea dos\nLinea tres".length(), total);
    }

    @Test @DisplayName("contarCaracteres: fichero vacio devuelve 0")
    void contarCaracteres_vacio() throws IOException {
        String vacio = tempDir.resolve("vacio2.txt").toString();
        try (FileWriter fw = new FileWriter(vacio)) { /* vacio */ }
        assertEquals(0, Ej05_FlujoCaracteres.contarCaracteres(vacio));
    }

    @Test @DisplayName("contarCaracter: cuenta ocurrencias de 'n'")
    void contarCaracter_encuentra() throws IOException {
        int count = Ej05_FlujoCaracteres.contarCaracter(fichero, 'n');
        // "Linea uno\nLinea dos\nLinea tres" -> 'n': L-i-n-e-a- -u-n-o-\n-... 
        // n en "Linea" x3 = 3, n en "uno" = 1, \n = 2 => total 6
        assertEquals(6, count);
    }

    @Test @DisplayName("contarCaracter: devuelve 0 si no existe el caracter")
    void contarCaracter_noExiste() throws IOException {
        assertEquals(0, Ej05_FlujoCaracteres.contarCaracter(fichero, 'z'));
    }

    @Test @DisplayName("anadirTexto: anade al final")
    void anadirTexto_append() throws IOException {
        Ej05_FlujoCaracteres.anadirTexto(fichero, "!");
        String contenido = Ej05_FlujoCaracteres.leerTodo(fichero);
        assertTrue(contenido.endsWith("!"));
    }

    @Test @DisplayName("anadirTexto: crea fichero si no existe")
    void anadirTexto_creaFichero() throws IOException {
        String nuevo = tempDir.resolve("nuevo.txt").toString();
        Ej05_FlujoCaracteres.anadirTexto(nuevo, "Hola");
        assertEquals("Hola", Ej05_FlujoCaracteres.leerTodo(nuevo));
    }

    @Test @DisplayName("copiarTexto: copia correcta y devuelve total")
    void copiarTexto_correcto() throws IOException {
        String destino = tempDir.resolve("copia.txt").toString();
        int total = Ej05_FlujoCaracteres.copiarTexto(fichero, destino);
        assertEquals(Ej05_FlujoCaracteres.contarCaracteres(fichero), total);
        assertEquals(Ej05_FlujoCaracteres.leerTodo(fichero),
                Ej05_FlujoCaracteres.leerTodo(destino));
    }

    @Test @DisplayName("estadisticas: formato correcto")
    void estadisticas_formato() throws IOException {
        String stats = Ej05_FlujoCaracteres.estadisticas(fichero);
        assertTrue(stats.contains("Caracteres:"));
        assertTrue(stats.contains("Lineas:"));
        assertTrue(stats.contains("Tamano fichero:"));
    }
}
