package bloque3b;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej63_RegistroBinarioSecuencialTest {

    private final String DIR = "temp_test_63";
    private final String RUTA = DIR + "/empleados.bin";
    private final String RUTA_FILTRADOS = DIR + "/filtrados.bin";
    
    private List<Ej63_RegistroBinarioSecuencial.Empleado> empleadosTest;

    @BeforeEach
    void setUp() throws IOException {
        new File(DIR).mkdirs();
        empleadosTest = List.of(
            new Ej63_RegistroBinarioSecuencial.Empleado(1, 1000.0, true, "Ana"),
            new Ej63_RegistroBinarioSecuencial.Empleado(2, 2000.0, false, "Luis"),
            new Ej63_RegistroBinarioSecuencial.Empleado(3, 3000.0, true, "Pepe")
        );
        Ej63_RegistroBinarioSecuencial.escribirEmpleados(RUTA, empleadosTest);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(RUTA));
        Files.deleteIfExists(Paths.get(RUTA_FILTRADOS));
        Files.deleteIfExists(Paths.get(DIR));
    }

    @Test
    void testLeerTodosEmpleados() throws IOException {
        List<Ej63_RegistroBinarioSecuencial.Empleado> leidos = Ej63_RegistroBinarioSecuencial.leerTodosEmpleados(RUTA);
        assertEquals(3, leidos.size(), "Debe leer todos los empleados");
        assertEquals("Ana", leidos.get(0).nombre());
        assertEquals(2000.0, leidos.get(1).salario());
        assertTrue(leidos.get(2).activo());
    }

    @Test
    void testLeerSoloNombres() throws IOException {
        List<String> nombres = Ej63_RegistroBinarioSecuencial.leerSoloNombres(RUTA);
        assertEquals(List.of("Ana", "Luis", "Pepe"), nombres, "Debe descartar el resto y leer solo nombres");
    }

    @Test
    void testContarActivos() throws IOException {
        assertEquals(2, Ej63_RegistroBinarioSecuencial.contarActivos(RUTA), "Hay 2 activos");
    }

    @Test
    void testSalarioMedio() throws IOException {
        assertEquals(2000.0, Ej63_RegistroBinarioSecuencial.salarioMedio(RUTA), 0.001);
    }

    @Test
    void testFiltrarActivos() throws IOException {
        int filtrados = Ej63_RegistroBinarioSecuencial.filtrarActivos(RUTA, RUTA_FILTRADOS);
        assertEquals(2, filtrados, "Debe devolver la cantidad de activos");
        
        List<Ej63_RegistroBinarioSecuencial.Empleado> leidosFiltrados = Ej63_RegistroBinarioSecuencial.leerTodosEmpleados(RUTA_FILTRADOS);
        assertEquals(2, leidosFiltrados.size());
        assertEquals("Ana", leidosFiltrados.get(0).nombre());
        assertEquals("Pepe", leidosFiltrados.get(1).nombre());
    }

    @Test
    void testContarRegistros() throws IOException {
        assertEquals(3, Ej63_RegistroBinarioSecuencial.contarRegistros(RUTA));
    }
}
