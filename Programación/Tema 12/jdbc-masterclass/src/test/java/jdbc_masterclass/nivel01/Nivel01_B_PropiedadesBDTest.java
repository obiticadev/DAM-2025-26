package jdbc_masterclass.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class Nivel01_B_PropiedadesBDTest {

    @Test
    @DisplayName("Validación 1: Debe leer correctamente SQLite de los metadatos")
    void testObtenerNombreMotorBD() {
        try (Connection conn = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            String motor = Nivel01_B_PropiedadesBD.obtenerNombreMotorBD(conn);
            assertEquals("SQLite", motor, "¡Fallo! El nombre devuelto no coincide con SQLite. Asegúrate de usar getDatabaseProductName().");
        } catch (SQLException e) {
            fail("Falló la obtención de conexión en el test.");
        }
    }
}
