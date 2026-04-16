package bossfinal;

import bloque5.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej43 - BOSS FINAL: Gestion Integral de Inventario")
class Ej43_GestionInventarioTest {

    @TempDir Path tempDir;
    String dir;
    String rutaCSV;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        rutaCSV = dir + "/proveedor.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCSV))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;3.75;20\n");
            bw.write("Sal;0.80;100\n");
            bw.write("Linea;invalida\n"); // linea invalida, debe ignorarse
        }
    }

    @Test @DisplayName("importarCSV: importa 3 productos validos, ignora invalido")
    void importarCSV() throws IOException {
        List<Producto> l = Ej43_GestionInventario.importarCSV(rutaCSV);
        assertEquals(3, l.size());
        assertEquals("Arroz", l.get(0).getNombre());
    }

    @Test @DisplayName("serializarInventario + deserializarInventario: ida y vuelta")
    void serializarDeserializar() throws Exception {
        List<Producto> orig = Ej43_GestionInventario.importarCSV(rutaCSV);
        String dat = dir + "/inv.dat";
        Ej43_GestionInventario.serializarInventario(dat, orig);
        List<Producto> cargado = Ej43_GestionInventario.deserializarInventario(dat);
        assertEquals(orig.size(), cargado.size());
        assertEquals(orig.get(0).getNombre(), cargado.get(0).getNombre());
    }

    @Test @DisplayName("exportarCSV: genera CSV con cabecera y registros")
    void exportarCSV() throws IOException {
        List<Producto> l = new ArrayList<>();
        l.add(new Producto("Pan", 1.50, 30));
        l.add(new Producto("Leche", 0.95, 80));
        String ruta = dir + "/export.csv";
        Ej43_GestionInventario.exportarCSV(ruta, l);
        List<String> lineas = Files.readAllLines(Path.of(ruta));
        assertEquals(3, lineas.size()); // cabecera + 2
        assertTrue(lineas.get(0).contains("nombre"));
        assertTrue(lineas.get(1).contains("Pan"));
    }

    @Test @DisplayName("hacerBackup: crea fichero de backup")
    void hacerBackup() throws Exception {
        List<Producto> l = Ej43_GestionInventario.importarCSV(rutaCSV);
        String dat = dir + "/inv.dat";
        Ej43_GestionInventario.serializarInventario(dat, l);
        String bak = Ej43_GestionInventario.hacerBackup(dat, dir + "/backups");
        assertTrue(Files.exists(Path.of(bak)));
        assertTrue(bak.contains("_bak"));
    }

    @Test @DisplayName("generarInforme: contiene Productos, Valor total, mas caro")
    void generarInforme() throws IOException {
        List<Producto> l = Ej43_GestionInventario.importarCSV(rutaCSV);
        String inf = Ej43_GestionInventario.generarInforme(l);
        assertTrue(inf.contains("Productos"));
        assertTrue(inf.contains("3"));
        assertTrue(inf.contains("Aceite")); // mas caro
    }

    @Test @DisplayName("organizarFicheros: mueve csv y dat a subdirectorios")
    void organizarFicheros() throws Exception {
        // Crear ficheros de prueba
        Files.writeString(Path.of(dir, "a.csv"), "dato");
        Files.writeString(Path.of(dir, "b.csv"), "dato");
        Files.writeString(Path.of(dir, "c.dat"), "dato");
        int[] r = Ej43_GestionInventario.organizarFicheros(dir);
        // a.csv, b.csv, proveedor.csv -> 3 csv; c.dat -> 1 dat
        assertEquals(3, r[0]);
        assertEquals(1, r[1]);
    }

    @Test @DisplayName("flujo completo: CSV -> objetos -> serializar -> deserializar -> exportar")
    void flujoCompleto() throws Exception {
        // Importar
        List<Producto> l1 = Ej43_GestionInventario.importarCSV(rutaCSV);
        // Serializar
        String dat = dir + "/flujo.dat";
        Ej43_GestionInventario.serializarInventario(dat, l1);
        // Deserializar
        List<Producto> l2 = Ej43_GestionInventario.deserializarInventario(dat);
        // Exportar
        String csv2 = dir + "/flujo.csv";
        Ej43_GestionInventario.exportarCSV(csv2, l2);
        // Re-importar y verificar
        List<Producto> l3 = Ej43_GestionInventario.importarCSV(csv2);
        assertEquals(l1.size(), l3.size());
    }
}
