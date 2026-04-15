package bloque6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej33 - Filtrar y Transformar CSV")
class Ej33_FiltrarCSVTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        ruta = dir + "/inv.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;3.75;20\n");
            bw.write("Sal;0.80;100\n");
            bw.write("Pan;1.50;5\n");
        }
    }

    @Test @DisplayName("filtrarPorPrecio: precio <= 1.20 devuelve 2")
    void filtrarPorPrecio() throws IOException {
        assertEquals(2, Ej33_FiltrarCSV.filtrarPorPrecio(ruta, dir + "/b.csv", 1.20));
    }

    @Test @DisplayName("productosAReponer: stock < 10 devuelve 1")
    void productosAReponer() throws IOException {
        assertEquals(1, Ej33_FiltrarCSV.productosAReponer(ruta, dir + "/r.csv", 10));
    }

    @Test @DisplayName("aplicarDescuento: genera CSV con precios reducidos")
    void aplicarDescuento() throws IOException {
        Ej33_FiltrarCSV.aplicarDescuento(ruta, dir + "/desc.csv", 50);
        // solo verificamos que no lanza excepcion y existe el fichero
        assertTrue(new java.io.File(dir + "/desc.csv").exists());
    }

    @Test @DisplayName("nombresAMayusculas: convierte 4 registros")
    void nombresAMayusculas() throws IOException {
        assertEquals(4, Ej33_FiltrarCSV.nombresAMayusculas(ruta, dir + "/may.csv"));
    }

    @Test @DisplayName("ordenarPorPrecio: genera fichero sin error")
    void ordenarPorPrecio() throws IOException {
        Ej33_FiltrarCSV.ordenarPorPrecio(ruta, dir + "/ord.csv");
        assertTrue(new java.io.File(dir + "/ord.csv").exists());
    }

    @Test @DisplayName("calcularValores: genera CSV nombre;valor")
    void calcularValores() throws IOException {
        Ej33_FiltrarCSV.calcularValores(ruta, dir + "/val.csv");
        assertTrue(new java.io.File(dir + "/val.csv").exists());
    }

    @Test @DisplayName("productosUnicos: 4 productos distintos")
    void productosUnicos() throws IOException {
        assertEquals(4, Ej33_FiltrarCSV.productosUnicos(ruta));
    }
}
