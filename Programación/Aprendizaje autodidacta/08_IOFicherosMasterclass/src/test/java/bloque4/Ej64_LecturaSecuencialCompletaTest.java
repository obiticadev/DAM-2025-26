package bloque4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej64_LecturaSecuencialCompletaTest {

    private final String DIR = "temp_test_64";

    @BeforeEach
    void setUp() throws IOException {
        new File(DIR).mkdirs();
    }

    @AfterEach
    void tearDown() throws IOException {
        File dir = new File(DIR);
        if (dir.exists()) {
            for (File f : dir.listFiles()) {
                f.delete();
            }
            dir.delete();
        }
    }

    @Test
    void testTransformarAMayusculas() throws IOException {
        String in = DIR + "/in.txt";
        String out = DIR + "/out.txt";
        Files.write(Paths.get(in), List.of("hola", "mundo"));

        int lineas = Ej64_LecturaSecuencialCompleta.transformarAMayusculas(in, out);
        assertEquals(2, lineas);

        List<String> leidas = Files.readAllLines(Paths.get(out));
        assertEquals(List.of("HOLA", "MUNDO"), leidas);
    }

    @Test
    void testFiltrarPositivosABinarioYSumar() throws IOException {
        String txt = DIR + "/nums.txt";
        String bin = DIR + "/nums.bin";
        Files.write(Paths.get(txt), List.of("10.0", "-5.0", "basura", "20.5"));

        int escritos = Ej64_LecturaSecuencialCompleta.filtrarPositivosABinario(txt, bin);
        assertEquals(2, escritos, "Solo debe escribir 10.0 y 20.5");

        double suma = Ej64_LecturaSecuencialCompleta.sumarBinario(bin);
        assertEquals(30.5, suma, 0.001);
    }

    @Test
    void testFiltrarPorPrefijo() throws IOException {
        String in = DIR + "/cfg.txt";
        String out = DIR + "/cfg_out.txt";
        Files.write(Paths.get(in), List.of("db.url=a", "app.name=b", "db.user=c"));

        int lineas = Ej64_LecturaSecuencialCompleta.filtrarPorPrefijo(in, out, "db.");
        assertEquals(2, lineas);

        List<String> leidas = Files.readAllLines(Paths.get(out));
        assertEquals(List.of("db.url=a", "db.user=c"), leidas);
    }

    @Test
    void testGenerarResumenNotas() throws IOException {
        String csv = DIR + "/notas.csv";
        String res = DIR + "/resumen.txt";
        Files.write(Paths.get(csv), List.of("cabecera", "Ana;8.0", "Luis;4.0", "Pepe;6.0"));

        double media = Ej64_LecturaSecuencialCompleta.generarResumenNotas(csv, res);
        assertEquals(6.0, media, 0.001);

        String contenidoResumen = Files.readString(Paths.get(res));
        assertTrue(contenidoResumen.contains("Total alumnos: 3"));
        assertTrue(contenidoResumen.contains("Aprobados: 2"));
        assertTrue(contenidoResumen.contains("Suspensos: 1"));
    }

    @Test
    void testEstadisticasFichero() throws IOException {
        String txt = DIR + "/stats.txt";
        Files.write(Paths.get(txt), List.of("Hola mundo", "Adios", ""));

        int[] stats = Ej64_LecturaSecuencialCompleta.estadisticasFichero(txt);
        assertEquals(3, stats[0], "Líneas");
        assertEquals(3, stats[1], "Palabras (Hola, mundo, Adios)");
        assertTrue(stats[2] > 10, "Caracteres");
    }
}
