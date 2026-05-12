package jdbc_masterclass.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel04_D_TraspasoDeDatosTest {

    @Test
    @DisplayName("Validación 1: Verificación de inserciones dinámicas en bucle")
    void testMigrarAutores() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS autores_viejos");
                s.execute("DROP TABLE IF EXISTS autores_nuevos");
                s.execute("CREATE TABLE autores_viejos (id INTEGER PRIMARY KEY, nombre TEXT)");
                s.execute("CREATE TABLE autores_nuevos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)");
                s.execute("INSERT INTO autores_viejos VALUES (1, 'Tolkien')");
                s.execute("INSERT INTO autores_viejos VALUES (2, 'Rowling')");
                s.execute("INSERT INTO autores_viejos VALUES (3, 'Martin')");
            }
            
            int cantidad = Nivel04_D_TraspasoDeDatos.migrarAutores(conn);
            assertEquals(3, cantidad, "¡Fallo! Deberían haberse migrado 3 autores.");
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM autores_nuevos")) {
                assertTrue(rs.next());
                assertEquals(3, rs.getInt(1), "Las 3 filas no constan en la tabla nueva.");
            }
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
