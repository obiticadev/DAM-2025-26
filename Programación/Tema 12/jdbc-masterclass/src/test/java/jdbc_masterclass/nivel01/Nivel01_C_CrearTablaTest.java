package jdbc_masterclass.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class Nivel01_C_CrearTablaTest {

    @Test
    @DisplayName("Validación 1: La tabla libros debe existir tras ejecutar el método")
    void testInicializarTablaLibros() {
        try (Connection conn = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            boolean result = Nivel01_C_CrearTabla.inicializarTablaLibros(conn);
            assertTrue(result, "¡Fallo! El método devolvió false. Revisa tu manejo de excepciones.");

            DatabaseMetaData meta = conn.getMetaData();
            try (ResultSet rs = meta.getTables(null, null, "libros", null)) {
                assertTrue(rs.next(), "¡Fallo! La tabla 'libros' no se encuentra en la base de datos.");
            }
        } catch (SQLException e) {
            fail("Excepción inesperada en test: " + e.getMessage());
        }
    }
}
