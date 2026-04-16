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

@DisplayName("Ej30 - Comparar Serializacion Texto vs Binario")
class Ej30_SerializarTextoVsBinarioTest {

    @TempDir Path tempDir;
    String dir;
    List<Producto> productos;

    @BeforeEach void setUp() {
        dir = tempDir.toString();
        productos = new ArrayList<>();
        productos.add(new Producto("Arroz", 1.20, 50));
        productos.add(new Producto("Aceite", 3.75, 20));
    }

    @Test @DisplayName("guardarComoTexto + cargarDesdeTexto: ida y vuelta")
    void textoIdaVuelta() throws IOException {
        String ruta = dir + "/t.txt";
        Ej30_SerializarTextoVsBinario.guardarComoTexto(ruta, productos);
        List<Producto> l = Ej30_SerializarTextoVsBinario.cargarDesdeTexto(ruta);
        assertEquals(2, l.size());
        assertEquals("Arroz", l.get(0).getNombre());
    }

    @Test @DisplayName("guardarComoBinario + cargarDesdeBinario: ida y vuelta")
    void binarioIdaVuelta() throws Exception {
        String ruta = dir + "/b.dat";
        Ej30_SerializarTextoVsBinario.guardarComoBinario(ruta, productos);
        List<Producto> l = Ej30_SerializarTextoVsBinario.cargarDesdeBinario(ruta);
        assertEquals(2, l.size());
        assertEquals("Arroz", l.get(0).getNombre());
    }

    @Test @DisplayName("compararTamano: ambos tamanos > 0")
    void compararTamano() throws IOException {
        long[] tam = Ej30_SerializarTextoVsBinario.compararTamano(dir, productos);
        assertTrue(tam[0] > 0);
        assertTrue(tam[1] > 0);
    }

    @Test @DisplayName("textoEsLegible: devuelve true")
    void textoEsLegible() {
        assertTrue(Ej30_SerializarTextoVsBinario.textoEsLegible());
    }

    @Test @DisplayName("informeComparativo: contiene Texto y Binario")
    void informeComparativo() {
        String inf = Ej30_SerializarTextoVsBinario.informeComparativo(100, 200);
        assertTrue(inf.contains("Texto"));
        assertTrue(inf.contains("Binario"));
        assertTrue(inf.contains("100"));
    }

    @Test @DisplayName("informeComparativo: diferencia absoluta correcta")
    void informeComparativo_diferencia() {
        String inf = Ej30_SerializarTextoVsBinario.informeComparativo(50, 80);
        assertTrue(inf.contains("30"));
    }

    @Test @DisplayName("cargarDesdeTexto: precios parseados correctamente")
    void preciosParseados() throws IOException {
        String ruta = dir + "/prec.txt";
        Ej30_SerializarTextoVsBinario.guardarComoTexto(ruta, productos);
        List<Producto> l = Ej30_SerializarTextoVsBinario.cargarDesdeTexto(ruta);
        assertEquals(1.20, l.get(0).getPrecio(), 0.01);
        assertEquals(50, l.get(0).getStock());
    }
}
