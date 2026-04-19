package bloque3b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej47 - Registros Binarios")
class Ej47_RegistrosBinariosTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("generarPuntuaciones + leerTodas")
    void generarYLeer() throws IOException {
        String ruta = dir + "/s.bin";
        Ej47_RegistrosBinarios.generarPuntuaciones(ruta, 3);
        String[] r = Ej47_RegistrosBinarios.leerTodasPuntuaciones(ruta);
        assertEquals(3, r.length);
    }

    @Test @DisplayName("puntuacionMaxima: devuelve la correcta")
    void puntuacionMax() throws IOException {
        String ruta = dir + "/s.bin";
        Ej47_RegistrosBinarios.generarPuntuaciones(ruta, 5);
        assertEquals(400, Ej47_RegistrosBinarios.puntuacionMaxima(ruta));
    }

    @Test @DisplayName("contarRegistros: cuenta correctamente")
    void contar() throws IOException {
        String ruta = dir + "/s.bin";
        Ej47_RegistrosBinarios.generarPuntuaciones(ruta, 4);
        assertEquals(4, Ej47_RegistrosBinarios.contarRegistros(ruta));
    }

    @Test @DisplayName("existeJugador: encuentra un jugador")
    void existeJugador() throws IOException {
        String ruta = dir + "/s.bin";
        Ej47_RegistrosBinarios.generarPuntuaciones(ruta, 3);
        assertTrue(Ej47_RegistrosBinarios.existeJugador(ruta, "Jugador-1"));
        assertFalse(Ej47_RegistrosBinarios.existeJugador(ruta, "NoExiste"));
    }

    @Test @DisplayName("tamanoFichero: mayor que 0")
    void tamano() throws IOException {
        String ruta = dir + "/s.bin";
        Ej47_RegistrosBinarios.generarPuntuaciones(ruta, 2);
        assertTrue(Ej47_RegistrosBinarios.tamanoFichero(ruta) > 0);
    }
}
