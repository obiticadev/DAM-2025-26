package jdbc_masterclass.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel01_E_ScriptInitTest {

    @Test
    @DisplayName("Validación 1: Se deben insertar exactamente 3 libros mediante Batch")
    void testInicializarDatosBase() {
        try (Connection conn = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            // Aseguramos que la tabla exista limpia
            try (Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT)");
            }
            
            int operados = Nivel01_E_ScriptInit.inicializarDatosBase(conn);
            assertEquals(3, operados, "¡Fallo! El método no ha retornado 3 comandos exitosos. ¿Has usado executeBatch()?");
            
            // Validamos que estén físicamente ahí
            try (Statement s = conn.createStatement();
                 ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM libros")) {
                assertTrue(rs.next());
                assertEquals(3, rs.getInt(1), "¡Fallo! No se han guardado físicamente las 3 filas en la base de datos.");
            }
        } catch (SQLException e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
}
