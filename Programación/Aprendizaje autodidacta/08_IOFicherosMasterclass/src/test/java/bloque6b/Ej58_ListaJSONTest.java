package bloque6b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej58 - Lista JSON")
class Ej58_ListaJSONTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("guardarInventario + cargarInventario")
    void idaVuelta() throws IOException {
        String r = dir + "/inv.json";
        List<ProductoJSON> orig = Arrays.asList(new ProductoJSON("A", 1), new ProductoJSON("B", 2));
        Ej58_ListaJSON.guardarInventario(r, new ArrayList<>(orig));
        List<ProductoJSON> leidos = Ej58_ListaJSON.cargarInventario(r);
        assertEquals(2, leidos.size());
        assertEquals("A", leidos.get(0).nombre);
    }

    @Test @DisplayName("anadirProducto")
    void anadir() throws IOException {
        String r = dir + "/inv.json";
        Ej58_ListaJSON.guardarInventario(r, new ArrayList<>(Arrays.asList(new ProductoJSON("A", 1))));
        Ej58_ListaJSON.anadirProducto(r, new ProductoJSON("B", 2));
        assertEquals(2, Ej58_ListaJSON.totalProductos(r));
    }

    @Test @DisplayName("eliminarProducto")
    void eliminar() throws IOException {
        String r = dir + "/inv.json";
        Ej58_ListaJSON.guardarInventario(r, new ArrayList<>(Arrays.asList(new ProductoJSON("A", 1), new ProductoJSON("B", 2))));
        assertTrue(Ej58_ListaJSON.eliminarProducto(r, "A"));
        assertEquals(1, Ej58_ListaJSON.totalProductos(r));
    }

    @Test @DisplayName("buscarPorNombre")
    void buscar() throws IOException {
        String r = dir + "/inv.json";
        Ej58_ListaJSON.guardarInventario(r, new ArrayList<>(Arrays.asList(new ProductoJSON("Laptop", 999))));
        assertNotNull(Ej58_ListaJSON.buscarPorNombre(r, "Laptop"));
        assertNull(Ej58_ListaJSON.buscarPorNombre(r, "NoExiste"));
    }

    @Test @DisplayName("precioTotal")
    void precioTotal() throws IOException {
        String r = dir + "/inv.json";
        Ej58_ListaJSON.guardarInventario(r, new ArrayList<>(Arrays.asList(new ProductoJSON("A", 10), new ProductoJSON("B", 20))));
        assertEquals(30.0, Ej58_ListaJSON.precioTotal(r), 0.01);
    }
}
