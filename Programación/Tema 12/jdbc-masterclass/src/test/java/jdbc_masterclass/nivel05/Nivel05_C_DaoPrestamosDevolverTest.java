package jdbc_masterclass.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel05_C_DaoPrestamosDevolverTest {

    @Test
    @DisplayName("Validación 1: El DAO devuelve préstamo")
    void testDevolverPrestamo() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS prestamos");
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY, copias_disponibles INTEGER)");
                s.execute("CREATE TABLE prestamos (id INTEGER PRIMARY KEY, estado TEXT)");
                s.execute("INSERT INTO libros VALUES (5, 9)");
                s.execute("INSERT INTO prestamos VALUES (100, 'ACTIVO')");
            }
            
            Nivel05_C_DaoPrestamosDevolver.devolverPrestamo(conn, 100, 5);
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT copias_disponibles FROM libros WHERE id = 5")) {
                rs.next();
                assertEquals(10, rs.getInt(1), "¡Fallo! No se sumó la copia de vuelta al libro.");
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
