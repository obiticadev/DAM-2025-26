package bloque7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej62_IOvsNIO_ExamenTest {

    private final String DIR = "temp_test_62";
    private final String RUTA_IO = DIR + "/io.txt";
    private final String RUTA_NIO = DIR + "/nio.txt";

    @BeforeEach
    void setUp() throws IOException {
        new File(DIR).mkdirs();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(RUTA_IO));
        Files.deleteIfExists(Paths.get(RUTA_NIO));
        Files.deleteIfExists(Paths.get(DIR));
    }

    @Test
    void testCrearFichero() throws IOException {
        assertTrue(Ej62_IOvsNIO_Examen.crearFicheroIO(RUTA_IO), "Debe devolver true si lo crea (IO)");
        assertTrue(new File(RUTA_IO).exists(), "El fichero IO no se ha creado");
        assertFalse(Ej62_IOvsNIO_Examen.crearFicheroIO(RUTA_IO), "Debe devolver false si ya existe (IO)");

        assertTrue(Ej62_IOvsNIO_Examen.crearFicheroNIO(RUTA_NIO), "Debe devolver true si lo crea (NIO)");
        assertTrue(Files.exists(Paths.get(RUTA_NIO)), "El fichero NIO no se ha creado");
        assertFalse(Ej62_IOvsNIO_Examen.crearFicheroNIO(RUTA_NIO), "Debe devolver false si ya existe (NIO)");
    }

    @Test
    void testEscribirYLeerTexto() throws IOException {
        List<String> datos = List.of("A", "B", "C");

        Ej62_IOvsNIO_Examen.escribirTextoIO(RUTA_IO, datos);
        List<String> leidosIO = Ej62_IOvsNIO_Examen.leerTextoIO(RUTA_IO);
        assertEquals(datos, leidosIO, "Datos escritos y leídos con IO no coinciden");

        Ej62_IOvsNIO_Examen.escribirTextoNIO(RUTA_NIO, datos);
        List<String> leidosNIO = Ej62_IOvsNIO_Examen.leerTextoNIO(RUTA_NIO);
        assertEquals(datos, leidosNIO, "Datos escritos y leídos con NIO no coinciden");
    }

    @Test
    void testExiste() throws IOException {
        assertFalse(Ej62_IOvsNIO_Examen.existeIO(RUTA_IO));
        assertFalse(Ej62_IOvsNIO_Examen.existeNIO(RUTA_NIO));

        new File(RUTA_IO).createNewFile();
        Files.createFile(Paths.get(RUTA_NIO));

        assertTrue(Ej62_IOvsNIO_Examen.existeIO(RUTA_IO));
        assertTrue(Ej62_IOvsNIO_Examen.existeNIO(RUTA_NIO));
    }
}
