package jdbc_masterclass.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel04_A_RecuperarIDGeneradoTest {

    @Test
    @DisplayName("Validación 1: Comprobar que devuelve el ID generado correctamente")
    void testCrearLibroYObtenerID() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros_magicos");
                s.execute("CREATE TABLE libros_magicos (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT)");
                s.execute("INSERT INTO libros_magicos (titulo) VALUES ('Pre-existente')");
            }
            
            int idGenerado = Nivel04_A_RecuperarIDGenerado.crearLibroYObtenerID(conn, "Nuevo Libro");
            assertEquals(2, idGenerado, "¡Fallo! Debería haber devuelto el ID 2.");
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
