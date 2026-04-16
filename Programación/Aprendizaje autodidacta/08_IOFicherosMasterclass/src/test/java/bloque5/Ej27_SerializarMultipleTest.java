package bloque5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej27 - Serializar Multiples Objetos")
class Ej27_SerializarMultipleTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws Exception {
        dir = tempDir.toString();
        ruta = dir + "/multi.dat";
        Ej27_SerializarMultiple.escribirUnoAUno(ruta, new Producto[]{
                new Producto("Arroz", 1.20, 50),
                new Producto("Aceite", 3.75, 20),
                new Producto("Sal", 0.80, 100)
        });
    }

    @Test @DisplayName("escribirUnoAUno + leerTodos: ida y vuelta")
    void idaYVuelta() throws Exception {
        List<Producto> l = Ej27_SerializarMultiple.leerTodos(ruta);
        assertEquals(3, l.size());
        assertEquals("Arroz", l.get(0).getNombre());
    }

    @Test @DisplayName("contarObjetos: cuenta correctamente")
    void contarObjetos() throws Exception {
        assertEquals(3, Ej27_SerializarMultiple.contarObjetos(ruta));
    }

    @Test @DisplayName("filtrarPorPrecio: filtra correctamente")
    void filtrarPorPrecio() throws Exception {
        String dest = dir + "/filtrado.dat";
        int n = Ej27_SerializarMultiple.filtrarPorPrecio(ruta, dest, 1.00);
        assertEquals(1, n); // solo Sal (0.80)
        assertEquals("Sal", Ej27_SerializarMultiple.leerTodos(dest).get(0).getNombre());
    }

    @Test @DisplayName("masCaro: devuelve el mas caro")
    void masCaro() throws Exception {
        Producto p = Ej27_SerializarMultiple.masCaro(ruta);
        assertNotNull(p);
        assertEquals("Aceite", p.getNombre());
    }

    @Test @DisplayName("actualizarStock: actualiza correctamente")
    void actualizarStock() throws Exception {
        assertTrue(Ej27_SerializarMultiple.actualizarStock(ruta, "arroz", 999));
        List<Producto> l = Ej27_SerializarMultiple.leerTodos(ruta);
        assertEquals(999, l.get(0).getStock());
    }

    @Test @DisplayName("actualizarStock: no existente devuelve false")
    void actualizarStock_noExiste() throws Exception {
        assertFalse(Ej27_SerializarMultiple.actualizarStock(ruta, "pizza", 10));
    }

    @Test @DisplayName("resumen: contiene numero de productos")
    void resumen() throws Exception {
        String r = Ej27_SerializarMultiple.resumen(ruta);
        assertTrue(r.contains("3"));
    }
}
