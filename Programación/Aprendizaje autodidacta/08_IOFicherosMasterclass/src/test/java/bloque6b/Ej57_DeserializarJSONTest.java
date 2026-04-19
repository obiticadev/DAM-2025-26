package bloque6b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej57 - Deserializar JSON")
class Ej57_DeserializarJSONTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        ObjectMapper m = new ObjectMapper();
        m.writeValue(new File(dir + "/p.json"), new ProductoJSON("Test", 10.0));
        m.writeValue(new File(dir + "/lista.json"), Arrays.asList(
            new ProductoJSON("A", 1), new ProductoJSON("B", 2)));
    }

    @Test @DisplayName("deserializarProducto")
    void desProd() throws IOException {
        ProductoJSON p = Ej57_DeserializarJSON.deserializarProducto(dir + "/p.json");
        assertNotNull(p);
        assertEquals("Test", p.nombre);
    }

    @Test @DisplayName("desdeString")
    void desString() throws IOException {
        ProductoJSON p = Ej57_DeserializarJSON.desdeString("{\"nombre\":\"X\",\"precio\":5.0}");
        assertEquals("X", p.nombre);
    }

    @Test @DisplayName("deserializarLista")
    void desLista() throws IOException {
        List<ProductoJSON> l = Ej57_DeserializarJSON.deserializarLista(dir + "/lista.json");
        assertEquals(2, l.size());
    }

    @Test @DisplayName("verificarIntegridad")
    void integridad() throws IOException {
        assertTrue(Ej57_DeserializarJSON.verificarIntegridad(dir + "/vi.json", new ProductoJSON("Z", 99)));
    }

    @Test @DisplayName("intentarDeserializar: fichero invalido")
    void invalido() throws IOException {
        new FileOutputStream(dir + "/bad.json").close();
        String r = Ej57_DeserializarJSON.intentarDeserializar(dir + "/bad.json");
        assertNotEquals("OK", r);
    }

    @Test @DisplayName("contarProductos")
    void contar() throws IOException {
        assertEquals(2, Ej57_DeserializarJSON.contarProductos(dir + "/lista.json"));
    }
}
