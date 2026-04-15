package bloque6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej35 - Unir (Join) Dos Ficheros CSV")
class Ej35_UnirCSVTest {

    @TempDir Path tempDir;
    String dir;
    String prod;
    String prov;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        prod = dir + "/prod.csv";
        prov = dir + "/prov.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(prod))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;3.75;20\n");
            bw.write("Sal;0.80;100\n");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(prov))) {
            bw.write("producto;proveedor;plazo\n");
            bw.write("Arroz;ProvA;3\n");
            bw.write("Sal;ProvB;5\n");
        }
    }

    @Test @DisplayName("cargarComoMapa: 3 claves para prod")
    void cargarComoMapa() throws IOException {
        Map<String, String> m = Ej35_UnirCSV.cargarComoMapa(prod);
        assertEquals(3, m.size());
        assertTrue(m.containsKey("arroz"));
    }

    @Test @DisplayName("innerJoin: 2 registros comunes")
    void innerJoin() throws IOException {
        assertEquals(2, Ej35_UnirCSV.innerJoin(prod, prov, dir + "/inner.csv"));
    }

    @Test @DisplayName("leftJoin: 3 registros (todos de prod)")
    void leftJoin() throws IOException {
        assertEquals(3, Ej35_UnirCSV.leftJoin(prod, prov, dir + "/left.csv"));
    }

    @Test @DisplayName("sinProveedor: Aceite no tiene proveedor")
    void sinProveedor() throws IOException {
        List<String> l = Ej35_UnirCSV.sinProveedor(prod, prov);
        assertEquals(1, l.size());
        assertEquals("aceite", l.get(0).toLowerCase());
    }

    @Test @DisplayName("unionVertical: combina 2 CSVs con misma cabecera")
    void unionVertical() throws IOException {
        String prod2 = dir + "/prod2.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(prod2))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Pan;1.50;30\n");
        }
        assertEquals(4, Ej35_UnirCSV.unionVertical(prod, prod2, dir + "/union.csv"));
    }

    @Test @DisplayName("registrosComunes: 2 comunes")
    void registrosComunes() throws IOException {
        assertEquals(2, Ej35_UnirCSV.registrosComunes(prod, prov));
    }

    @Test @DisplayName("mismaCabecera: false para prod y prov")
    void mismaCabecera_false() throws IOException {
        assertFalse(Ej35_UnirCSV.mismaCabecera(prod, prov));
    }

    @Test @DisplayName("mismaCabecera: true para mismo fichero")
    void mismaCabecera_true() throws IOException {
        assertTrue(Ej35_UnirCSV.mismaCabecera(prod, prod));
    }
}
