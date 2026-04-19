package bloque3c;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej52 - Actualizar Registros")
class Ej52_ActualizarRegistroTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        ruta = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(ruta, 10);
    }

    @Test @DisplayName("actualizar: cambia el valor")
    void actualizar() throws IOException {
        Ej52_ActualizarRegistro.actualizar(ruta, 0, 999);
        assertEquals(999, Ej51_LecturaConSeek.leerEnteroConSeek(ruta, 0));
    }

    @Test @DisplayName("duplicarValor")
    void duplicar() throws IOException {
        int nuevo = Ej52_ActualizarRegistro.duplicarValor(ruta, 2);
        assertEquals(40, nuevo);
        assertEquals(40, Ej51_LecturaConSeek.leerEnteroConSeek(ruta, 2));
    }

    @Test @DisplayName("intercambiar")
    void intercambiar() throws IOException {
        Ej52_ActualizarRegistro.intercambiar(ruta, 0, 9);
        assertEquals(90, Ej51_LecturaConSeek.leerEnteroConSeek(ruta, 0));
        assertEquals(0, Ej51_LecturaConSeek.leerEnteroConSeek(ruta, 9));
    }

    @Test @DisplayName("resetearTodos")
    void resetear() throws IOException {
        Ej52_ActualizarRegistro.resetearTodos(ruta);
        int[] todos = Ej52_ActualizarRegistro.leerTodos(ruta);
        for (int v : todos) assertEquals(0, v);
    }

    @Test @DisplayName("incrementar")
    void incrementar() throws IOException {
        int nuevo = Ej52_ActualizarRegistro.incrementar(ruta, 5);
        assertEquals(51, nuevo);
    }

    @Test @DisplayName("reemplazarTodos")
    void reemplazar() throws IOException {
        Ej52_ActualizarRegistro.actualizar(ruta, 1, 50);
        int count = Ej52_ActualizarRegistro.reemplazarTodos(ruta, 50, 999);
        assertTrue(count >= 1);
    }

    @Test @DisplayName("leerTodos: devuelve 10 valores")
    void leerTodos() throws IOException {
        assertEquals(10, Ej52_ActualizarRegistro.leerTodos(ruta).length);
    }
}
