package bloque6b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej56 - Serializar JSON")
class Ej56_SerializarJSONTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("serializarProducto: crea fichero")
    void serializarProducto() throws IOException {
        String r = dir + "/p.json";
        Ej56_SerializarJSON.serializarProducto(r, new ProductoJSON("Test", 10.0));
        assertTrue(new File(r).exists());
        assertTrue(new File(r).length() > 0);
    }

    @Test @DisplayName("productoAJsonString: contiene nombre")
    void aString() throws IOException {
        String json = Ej56_SerializarJSON.productoAJsonString(new ProductoJSON("Laptop", 99.9));
        assertTrue(json.contains("Laptop"));
        assertTrue(json.contains("99.9"));
    }

    @Test @DisplayName("serializarLista: crea fichero")
    void serializarLista() throws IOException {
        String r = dir + "/lista.json";
        Ej56_SerializarJSON.serializarLista(r, Arrays.asList(new ProductoJSON("A", 1), new ProductoJSON("B", 2)));
        assertTrue(new File(r).length() > 0);
    }

    @Test @DisplayName("serializarBonito: contiene saltos de linea")
    void bonito() throws IOException {
        String json = Ej56_SerializarJSON.serializarBonito(new ProductoJSON("X", 1));
        assertTrue(json.contains("\n") || json.contains("\r"));
    }

    @Test @DisplayName("serializarMapa: crea fichero")
    void mapa() throws IOException {
        String r = dir + "/m.json";
        Map<String, Object> m = new HashMap<>();
        m.put("key", "value");
        Ej56_SerializarJSON.serializarMapa(r, m);
        assertTrue(new File(r).length() > 0);
    }

    @Test @DisplayName("ficheroNoVacio: true cuando existe")
    void noVacio() throws IOException {
        String r = dir + "/p.json";
        Ej56_SerializarJSON.serializarProducto(r, new ProductoJSON("T", 1));
        assertTrue(Ej56_SerializarJSON.ficheroNoVacio(r));
    }

    @Test @DisplayName("tamanoFichero: > 0")
    void tamano() throws IOException {
        String r = dir + "/p.json";
        Ej56_SerializarJSON.serializarProducto(r, new ProductoJSON("T", 1));
        assertTrue(Ej56_SerializarJSON.tamanoFichero(r) > 0);
    }
}
