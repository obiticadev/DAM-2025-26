package jdbc_masterclass.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel05_E_RetoFinalTest {

    @Test
    @DisplayName("Validación 1: Flujo completo de la biblioteca ejecutado con éxito")
    void testHistoria() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS prestamos");
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("DROP TABLE IF EXISTS usuarios");
                
                s.execute("CREATE TABLE usuarios (id INTEGER PRIMARY KEY, nombre TEXT)");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY, copias_disponibles INTEGER)");
                s.execute("CREATE TABLE prestamos (id INTEGER PRIMARY KEY AUTOINCREMENT, id_libro INTEGER, id_usuario INTEGER, estado TEXT)");
            }
            
            boolean exito = Nivel05_E_RetoFinal.ejecutarHistoriaCompleta(conn);
            assertTrue(exito, "¡Fallo! El método retornó false, lo que significa que saltó una SQLException.");
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT copias_disponibles FROM libros WHERE id = 500")) {
                assertTrue(rs.next(), "¡El libro 500 no existe!");
                assertEquals(1, rs.getInt(1), "¡Fallo! El stock no regresó a 1 tras ser prestado y devuelto.");
            }
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
