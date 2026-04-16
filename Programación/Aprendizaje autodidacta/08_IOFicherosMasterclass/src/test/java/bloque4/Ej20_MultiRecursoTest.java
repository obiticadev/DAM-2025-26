package bloque4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej20 - Multiples Recursos en un Solo try")
class Ej20_MultiRecursoTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    private void crear(String ruta, List<String> lineas) throws IOException {
        Ej20_MultiRecurso.escribirDesdeLista(ruta, lineas);
    }

    @Test @DisplayName("unirFicheros: une dos ficheros con separador")
    void unirFicheros() throws IOException {
        crear(dir + "/a.txt", List.of("L1", "L2"));
        crear(dir + "/b.txt", List.of("L3"));
        int total = Ej20_MultiRecurso.unirFicheros(dir + "/a.txt", dir + "/b.txt", dir + "/u.txt");
        assertTrue(total >= 3);
        List<String> l = Ej20_MultiRecurso.leerComoLista(dir + "/u.txt");
        assertTrue(l.contains("L1"));
        assertTrue(l.contains("L3"));
    }

    @Test @DisplayName("generarInforme: contiene Total registros")
    void generarInforme() throws IOException {
        crear(dir + "/reg.txt", List.of("Luna;Ana;Vacuna", "Max;Pedro;Revision"));
        Ej20_MultiRecurso.generarInforme(dir + "/reg.txt", dir + "/inf.txt");
        List<String> l = Ej20_MultiRecurso.leerComoLista(dir + "/inf.txt");
        String todo = String.join("\n", l);
        assertTrue(todo.contains("Total registros: 2") || todo.contains("Total registros:2"));
    }

    @Test @DisplayName("separarLineas: separa correctamente")
    void separarLineas() throws IOException {
        crear(dir + "/sep.txt", List.of("Vacuna gato", "Revision perro", "Vacuna perro"));
        int[] r = Ej20_MultiRecurso.separarLineas(dir + "/sep.txt",
                dir + "/ac.txt", dir + "/re.txt", "vacuna");
        assertEquals(2, r[0]);
        assertEquals(1, r[1]);
    }

    @Test @DisplayName("duplicarConTransformacion: genera mayusculas y minusculas")
    void duplicarConTransformacion() throws IOException {
        crear(dir + "/dt.txt", List.of("Hola Mundo"));
        int n = Ej20_MultiRecurso.duplicarConTransformacion(dir + "/dt.txt",
                dir + "/may.txt", dir + "/min.txt");
        assertEquals(1, n);
        assertEquals("HOLA MUNDO", Ej20_MultiRecurso.leerComoLista(dir + "/may.txt").get(0));
        assertEquals("hola mundo", Ej20_MultiRecurso.leerComoLista(dir + "/min.txt").get(0));
    }

    @Test @DisplayName("logOperaciones: genera lineas correctas")
    void logOperaciones() throws IOException {
        Ej20_MultiRecurso.logOperaciones(dir + "/log.txt",
                new String[]{"Init", "Process", "Done"});
        List<String> l = Ej20_MultiRecurso.leerComoLista(dir + "/log.txt");
        assertEquals(3, l.size());
    }

    @Test @DisplayName("leerComoLista + escribirDesdeLista: ida y vuelta")
    void listaIdaVuelta() throws IOException {
        List<String> orig = List.of("A", "B", "C");
        Ej20_MultiRecurso.escribirDesdeLista(dir + "/lista.txt", orig);
        List<String> leida = Ej20_MultiRecurso.leerComoLista(dir + "/lista.txt");
        assertEquals(orig, leida);
    }
}
