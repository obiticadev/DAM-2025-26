package bloque5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Ej65_SerializacionConDataTest {

    private final String DIR = "temp_test_65";
    private final String RUTA_DATA = DIR + "/data.bin";
    private final String RUTA_OBJ = DIR + "/obj.dat";

    private Ej65_SerializacionConData.Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        new File(DIR).mkdirs();
        vehiculo = new Ej65_SerializacionConData.Vehiculo("9999-ZZZ", 20000.0, 2023);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(RUTA_DATA));
        Files.deleteIfExists(Paths.get(RUTA_OBJ));
        Files.deleteIfExists(Paths.get(DIR));
    }

    @Test
    void testGuardarYCargarVehiculoManual() throws IOException {
        Ej65_SerializacionConData.guardarVehiculoManual(RUTA_DATA, vehiculo);
        assertTrue(new File(RUTA_DATA).exists(), "El fichero debe existir");

        Ej65_SerializacionConData.Vehiculo cargado = Ej65_SerializacionConData.cargarVehiculoManual(RUTA_DATA);
        assertNotNull(cargado);
        assertEquals(vehiculo, cargado, "El vehículo cargado debe ser igual al guardado");
    }

    @Test
    void testVerificarFalloSerializable() {
        boolean falla = Ej65_SerializacionConData.verificarFalloSerializable(RUTA_OBJ, vehiculo);
        assertTrue(falla, "Debe devolver true porque lanza NotSerializableException");
    }

    @Test
    void testComparativaTamanos() throws IOException {
        Ej65_SerializacionConData.guardarVehiculoManual(RUTA_DATA, vehiculo);
        long tamData = Ej65_SerializacionConData.obtenerTamanoFichero(RUTA_DATA);

        Producto prod = new Producto("Test", 10.0, 5);
        Ej65_SerializacionConData.guardarProductoAuto(RUTA_OBJ, prod);
        long tamObj = Ej65_SerializacionConData.obtenerTamanoFichero(RUTA_OBJ);

        assertTrue(tamData > 0);
        assertTrue(tamObj > 0);
        // La serialización automática añade metadatos (nombres de clase, campos, etc.)
        // por lo que el tamaño suele ser significativamente mayor que solo escribir primitivos.
        assertTrue(tamObj > tamData, "La serialización con ObjectOutputStream suele ocupar más");
    }
}
