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

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej31 - Leer un Fichero CSV")
class Ej31_LeerCSVTest {

    @TempDir Path tempDir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        ruta = tempDir.toString() + "/prod.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;3.75;20\n");
            bw.write("Sal;0.80;100\n");
        }
    }

    @Test @DisplayName("leerLineasDatos: devuelve 3 lineas")
    void leerLineasDatos() throws IOException {
        assertEquals(3, Ej31_LeerCSV.leerLineasDatos(ruta).size());
    }

    @Test @DisplayName("leerCabecera: devuelve 3 columnas")
    void leerCabecera() throws IOException {
        String[] cab = Ej31_LeerCSV.leerCabecera(ruta, ";");
        assertEquals(3, cab.length);
        assertEquals("nombre", cab[0].trim().toLowerCase());
    }

    @Test @DisplayName("contarRegistros: cuenta 3")
    void contarRegistros() throws IOException {
        assertEquals(3, Ej31_LeerCSV.contarRegistros(ruta));
    }

    @Test @DisplayName("sumaStock: 50+20+100=170")
    void sumaStock() throws IOException {
        assertEquals(170, Ej31_LeerCSV.sumaStock(ruta));
    }

    @Test @DisplayName("buscarProducto: encuentra arroz")
    void buscarProducto_existe() throws IOException {
        String l = Ej31_LeerCSV.buscarProducto(ruta, "arroz");
        assertNotNull(l);
        assertTrue(l.contains("1.20"));
    }

    @Test @DisplayName("buscarProducto: no encuentra pizza")
    void buscarProducto_noExiste() throws IOException {
        assertNull(Ej31_LeerCSV.buscarProducto(ruta, "pizza"));
    }

    @Test @DisplayName("productoMasCaro: devuelve Aceite")
    void productoMasCaro() throws IOException {
        assertEquals("Aceite", Ej31_LeerCSV.productoMasCaro(ruta));
    }

    @Test @DisplayName("validarCSV: CSV valido devuelve 0 invalidas")
    void validarCSV_ok() throws IOException {
        assertEquals(0, Ej31_LeerCSV.validarCSV(ruta, ";"));
    }
}
