package bloque6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej34 - Agrupar Datos de un CSV")
class Ej34_AgruparCSVTest {

    @TempDir Path tempDir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        ruta = tempDir.toString() + "/ventas.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("producto;categoria;cantidad;precioUnitario\n");
            bw.write("Arroz;Alimentacion;10;1.20\n");
            bw.write("Aceite;Alimentacion;5;3.75\n");
            bw.write("Jabon;Limpieza;8;2.00\n");
            bw.write("Lejia;Limpieza;12;1.50\n");
        }
    }

    @Test @DisplayName("ventasPorCategoria: Alimentacion=15, Limpieza=20")
    void ventasPorCategoria() throws IOException {
        Map<String, Integer> m = Ej34_AgruparCSV.ventasPorCategoria(ruta);
        assertEquals(15, m.get("Alimentacion"));
        assertEquals(20, m.get("Limpieza"));
    }

    @Test @DisplayName("ingresosPorCategoria: Alimentacion=30.75, Limpieza=34.0")
    void ingresosPorCategoria() throws IOException {
        Map<String, Double> m = Ej34_AgruparCSV.ingresosPorCategoria(ruta);
        assertEquals(30.75, m.get("Alimentacion"), 0.01);
        assertEquals(34.0, m.get("Limpieza"), 0.01);
    }

    @Test @DisplayName("productosPorCategoria: 2 por categoria")
    void productosPorCategoria() throws IOException {
        Map<String, Integer> m = Ej34_AgruparCSV.productosPorCategoria(ruta);
        assertEquals(2, m.get("Alimentacion"));
        assertEquals(2, m.get("Limpieza"));
    }

    @Test @DisplayName("generarResumenPorCategoria: genera fichero")
    void generarResumen() throws IOException {
        String dest = tempDir.toString() + "/res.csv";
        Ej34_AgruparCSV.generarResumenPorCategoria(ruta, dest);
        assertTrue(new java.io.File(dest).exists());
    }

    @Test @DisplayName("categoriaMasRentable: Limpieza (34.0 > 30.75)")
    void categoriaMasRentable() throws IOException {
        assertEquals("Limpieza", Ej34_AgruparCSV.categoriaMasRentable(ruta));
    }

    @Test @DisplayName("precioMedioPorCategoria: calcula correctamente")
    void precioMedioPorCategoria() throws IOException {
        Map<String, Double> m = Ej34_AgruparCSV.precioMedioPorCategoria(ruta);
        // Alimentacion: 30.75/15 = 2.05, Limpieza: 34.0/20 = 1.70
        assertEquals(2.05, m.get("Alimentacion"), 0.01);
        assertEquals(1.70, m.get("Limpieza"), 0.01);
    }

    @Test @DisplayName("totalRegistros: 4 registros")
    void totalRegistros() throws IOException {
        assertEquals(4, Ej34_AgruparCSV.totalRegistros(ruta));
    }
}
