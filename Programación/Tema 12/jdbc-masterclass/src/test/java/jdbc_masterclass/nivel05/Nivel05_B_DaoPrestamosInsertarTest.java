package jdbc_masterclass.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel05_B_DaoPrestamosInsertarTest {

    @Test
    @DisplayName("Validación 1: El DAO realiza el préstamo y altera ambas tablas")
    void testRealizarPrestamo() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS prestamos");
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY, copias_disponibles INTEGER)");
                s.execute("CREATE TABLE prestamos (id_libro INTEGER, id_usuario INTEGER, estado TEXT)");
                s.execute("INSERT INTO libros VALUES (5, 10)");
            }
            
            Nivel05_B_DaoPrestamosInsertar.realizarPrestamo(conn, 5, 99);
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT copias_disponibles FROM libros WHERE id = 5")) {
                rs.next();
                assertEquals(9, rs.getInt(1), "¡Fallo! No se restó la copia.");
            }
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT estado FROM prestamos WHERE id_libro = 5")) {
                assertTrue(rs.next(), "¡Fallo! No se insertó el préstamo.");
                assertEquals("ACTIVO", rs.getString(1));
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
