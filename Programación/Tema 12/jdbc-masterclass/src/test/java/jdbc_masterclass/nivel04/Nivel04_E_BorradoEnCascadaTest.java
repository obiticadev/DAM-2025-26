package jdbc_masterclass.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel04_E_BorradoEnCascadaTest {

    @Test
    @DisplayName("Validación 1: Borrar hijos y luego padre correctamente")
    void testBorrarAutorSeguro() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("DROP TABLE IF EXISTS autores");
                
                // Forzamos soporte de Foreign Keys en SQLite para este test
                s.execute("PRAGMA foreign_keys = ON;");
                
                s.execute("CREATE TABLE autores (id INTEGER PRIMARY KEY, nombre TEXT)");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY, titulo TEXT, id_autor INTEGER, FOREIGN KEY(id_autor) REFERENCES autores(id))");
                
                s.execute("INSERT INTO autores VALUES (50, 'Gabo')");
                s.execute("INSERT INTO libros VALUES (100, 'Cien Años', 50)");
            }
            
            Nivel04_E_BorradoEnCascada.borrarAutorSeguro(conn, 50);
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM autores")) {
                rs.next();
                assertEquals(0, rs.getInt(1), "¡Fallo! El autor no fue borrado.");
            }
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM libros")) {
                rs.next();
                assertEquals(0, rs.getInt(1), "¡Fallo! Los libros del autor no fueron borrados.");
            }
            
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            fail("Excepción en test (posible Foreign Key Constraint Failed): " + e.getMessage());
        }
    }
}
