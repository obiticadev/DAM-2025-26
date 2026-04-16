package bloque3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej17 - Copiar y Transformar Texto Buffered")
class Ej17_CopiarTextoBufferedTest {

    @TempDir
    Path tempDir;
    String dir;
    String menu;

    @BeforeEach
    void setUp() throws IOException {
        dir = tempDir.toString();
        menu = dir + "/menu.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(menu))) {
            bw.write("Paella valenciana"); bw.newLine();
            bw.write("Ensalada cesar"); bw.newLine();
            bw.write("Agua mineral");
        }
    }

    private List<String> leer(String ruta) throws IOException {
        List<String> l = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String s; while ((s = br.readLine()) != null) l.add(s);
        }
        return l;
    }

    @Test @DisplayName("copiarEnMayusculas: todo en mayusculas")
    void copiarEnMayusculas_correcto() throws IOException {
        String d = dir + "/upper.txt";
        assertEquals(3, Ej17_CopiarTextoBuffered.copiarEnMayusculas(menu, d));
        assertEquals("PAELLA VALENCIANA", leer(d).get(0));
    }

    @Test @DisplayName("copiarEnMayusculas: devuelve numero de lineas")
    void copiarEnMayusculas_count() throws IOException {
        assertEquals(3, Ej17_CopiarTextoBuffered.copiarEnMayusculas(menu, dir + "/u2.txt"));
    }

    @Test @DisplayName("numerarLineas: formato con numeros")
    void numerarLineas_formato() throws IOException {
        String d = dir + "/num.txt";
        Ej17_CopiarTextoBuffered.numerarLineas(menu, d);
        List<String> l = leer(d);
        assertTrue(l.get(0).contains("1"));
        assertTrue(l.get(0).contains("Paella"));
    }

    @Test @DisplayName("filtrarLineas: filtra correctamente")
    void filtrarLineas_correcto() throws IOException {
        String d = dir + "/filt.txt";
        int n = Ej17_CopiarTextoBuffered.filtrarLineas(menu, d, "ensalada");
        assertEquals(1, n);
        assertEquals("Ensalada cesar", leer(d).get(0));
    }

    @Test @DisplayName("filtrarLineas: sin coincidencias genera fichero vacio")
    void filtrarLineas_sinCoincidencias() throws IOException {
        String d = dir + "/filt2.txt";
        assertEquals(0, Ej17_CopiarTextoBuffered.filtrarLineas(menu, d, "pizza"));
    }

    @Test @DisplayName("reemplazar: reemplaza y cuenta")
    void reemplazar_correcto() throws IOException {
        String d = dir + "/repl.txt";
        int n = Ej17_CopiarTextoBuffered.reemplazar(menu, d, "cesar", "mixta");
        assertEquals(1, n);
        assertTrue(leer(d).get(1).contains("mixta"));
    }

    @Test @DisplayName("invertirLineas: orden invertido")
    void invertirLineas_correcto() throws IOException {
        String d = dir + "/inv.txt";
        assertEquals(3, Ej17_CopiarTextoBuffered.invertirLineas(menu, d));
        List<String> l = leer(d);
        assertEquals("Agua mineral", l.get(0));
        assertEquals("Paella valenciana", l.get(2));
    }

    @Test @DisplayName("unirFicheros: une dos ficheros")
    void unirFicheros_correcto() throws IOException {
        String f2 = dir + "/extra.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f2))) {
            bw.write("Postre"); bw.newLine(); bw.write("Cafe");
        }
        String d = dir + "/unido.txt";
        int total = Ej17_CopiarTextoBuffered.unirFicheros(menu, f2, d);
        assertTrue(total >= 5); // 3 + 1 separador + 2
    }

    @Test @DisplayName("contarPalabras: cuenta correctamente")
    void contarPalabras_correcto() throws IOException {
        // "Paella valenciana" = 2, "Ensalada cesar" = 2, "Agua mineral" = 2 -> 6
        assertEquals(6, Ej17_CopiarTextoBuffered.contarPalabras(menu));
    }

    @Test @DisplayName("contarPalabras: fichero vacio devuelve 0")
    void contarPalabras_vacio() throws IOException {
        String v = dir + "/vacio.txt";
        try (FileWriter fw = new FileWriter(v)) { /* vacio */ }
        assertEquals(0, Ej17_CopiarTextoBuffered.contarPalabras(v));
    }
}
