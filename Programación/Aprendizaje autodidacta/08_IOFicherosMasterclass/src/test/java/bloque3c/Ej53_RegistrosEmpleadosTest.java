package bloque3c;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej53 - Registros Empleados RAF")
class Ej53_RegistrosEmpleadosTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("generarEmpleados + contarEmpleados")
    void generar() throws IOException {
        String r = dir + "/emp.dat";
        Ej53_RegistrosEmpleados.generarEmpleados(r, 5);
        assertEquals(5, Ej53_RegistrosEmpleados.contarEmpleados(r));
    }

    @Test @DisplayName("leerEmpleado: formato correcto")
    void leer() throws IOException {
        String r = dir + "/emp.dat";
        Ej53_RegistrosEmpleados.generarEmpleados(r, 3);
        String emp0 = Ej53_RegistrosEmpleados.leerEmpleado(r, 0);
        assertTrue(emp0.contains("1") && emp0.contains("2000"));
    }

    @Test @DisplayName("actualizarSalario: cambia solo salario")
    void actualizar() throws IOException {
        String r = dir + "/emp.dat";
        Ej53_RegistrosEmpleados.generarEmpleados(r, 3);
        Ej53_RegistrosEmpleados.actualizarSalario(r, 0, 9999.99);
        String emp = Ej53_RegistrosEmpleados.leerEmpleado(r, 0);
        assertTrue(emp.contains("9999"));
    }

    @Test @DisplayName("indiceMaxSalario: devuelve el ultimo")
    void maxSalario() throws IOException {
        String r = dir + "/emp.dat";
        Ej53_RegistrosEmpleados.generarEmpleados(r, 5);
        assertEquals(4, Ej53_RegistrosEmpleados.indiceMaxSalario(r));
    }

    @Test @DisplayName("leerTodosEmpleados: devuelve array correcto")
    void leerTodos() throws IOException {
        String r = dir + "/emp.dat";
        Ej53_RegistrosEmpleados.generarEmpleados(r, 3);
        assertEquals(3, Ej53_RegistrosEmpleados.leerTodosEmpleados(r).length);
    }
}
