package bloque5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej26 - Serializar Listas de Objetos")
class Ej26_SerializarListaTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws Exception {
        dir = tempDir.toString();
        ruta = dir + "/inv.dat";
        List<Producto> l = new ArrayList<>();
        l.add(new Producto("Arroz", 1.20, 50));
        l.add(new Producto("Aceite", 3.75, 20));
        l.add(new Producto("Sal", 0.80, 0));
        Ej26_SerializarLista.serializarLista(ruta, l);
    }

    @Test @DisplayName("serializarLista + deserializarLista: ida y vuelta")
    void idaYVuelta() throws Exception {
        List<Producto> l = Ej26_SerializarLista.deserializarLista(ruta);
        assertEquals(3, l.size());
        assertEquals("Arroz", l.get(0).getNombre());
    }

    @Test @DisplayName("anadirProducto: incrementa tamano lista")
    void anadirProducto() throws Exception {
        Ej26_SerializarLista.anadirProducto(ruta, new Producto("Pan", 1.50, 30));
        assertEquals(4, Ej26_SerializarLista.deserializarLista(ruta).size());
    }

    @Test @DisplayName("buscarPorNombre: encuentra existente")
    void buscarPorNombre_existe() throws Exception {
        Producto p = Ej26_SerializarLista.buscarPorNombre(ruta, "arroz");
        assertNotNull(p);
        assertEquals("Arroz", p.getNombre());
    }

    @Test @DisplayName("buscarPorNombre: no encuentra devuelve null")
    void buscarPorNombre_noExiste() throws Exception {
        assertNull(Ej26_SerializarLista.buscarPorNombre(ruta, "pizza"));
    }

    @Test @DisplayName("eliminarPorNombre: elimina existente")
    void eliminarPorNombre_existe() throws Exception {
        assertTrue(Ej26_SerializarLista.eliminarPorNombre(ruta, "sal"));
        assertEquals(2, Ej26_SerializarLista.deserializarLista(ruta).size());
    }

    @Test @DisplayName("eliminarPorNombre: no existente devuelve false")
    void eliminarPorNombre_noExiste() throws Exception {
        assertFalse(Ej26_SerializarLista.eliminarPorNombre(ruta, "nada"));
    }

    @Test @DisplayName("contarConStock: cuenta productos con stock > 0")
    void contarConStock() throws Exception {
        assertEquals(2, Ej26_SerializarLista.contarConStock(ruta));
    }

    @Test @DisplayName("valorInventario: calcula correctamente")
    void valorInventario() throws Exception {
        // Arroz: 1.20*50=60, Aceite: 3.75*20=75, Sal: 0.80*0=0 -> 135
        assertEquals(135.0, Ej26_SerializarLista.valorInventario(ruta), 0.01);
    }
}
