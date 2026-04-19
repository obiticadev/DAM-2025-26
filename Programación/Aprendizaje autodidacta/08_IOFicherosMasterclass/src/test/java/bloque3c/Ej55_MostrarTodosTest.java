package bloque3c;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej55 - Mostrar Todos")
class Ej55_MostrarTodosTest {

    @TempDir Path tempDir;
    String dir;
    String ruta;

    @BeforeEach void setUp() throws IOException {
        dir = tempDir.toString();
        ruta = dir + "/e.dat";
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(ruta, 10);
    }

    @Test @DisplayName("mostrarEnteros: 10 elementos")
    void mostrar() throws IOException { assertEquals(10, Ej55_MostrarTodos.mostrarEnteros(ruta).length); }

    @Test @DisplayName("sumaEnteros: 0+10+...+90 = 450")
    void suma() throws IOException { assertEquals(450, Ej55_MostrarTodos.sumaEnteros(ruta)); }

    @Test @DisplayName("mediaEnteros: 45.0")
    void media() throws IOException { assertEquals(45.0, Ej55_MostrarTodos.mediaEnteros(ruta), 0.01); }

    @Test @DisplayName("minimoEntero: 0")
    void min() throws IOException { assertEquals(0, Ej55_MostrarTodos.minimoEntero(ruta)); }

    @Test @DisplayName("maximoEntero: 90")
    void max() throws IOException { assertEquals(90, Ej55_MostrarTodos.maximoEntero(ruta)); }

    @Test @DisplayName("contarMayores: 4 mayores que 50")
    void mayores() throws IOException { assertEquals(4, Ej55_MostrarTodos.contarMayores(ruta, 50)); }

    @Test @DisplayName("mostrarEmpleados: con fichero de empleados")
    void mostrarEmps() throws IOException {
        String rEmp = dir + "/emp.dat";
        Ej53_RegistrosEmpleados.generarEmpleados(rEmp, 3);
        assertEquals(3, Ej55_MostrarTodos.mostrarEmpleados(rEmp).length);
    }
}
