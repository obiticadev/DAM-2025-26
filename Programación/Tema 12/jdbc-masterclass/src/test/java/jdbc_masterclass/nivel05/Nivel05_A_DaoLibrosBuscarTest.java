package jdbc_masterclass.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel05_A_DaoLibrosBuscarTest {

    @Test
    @DisplayName("Validación 1: DAO debe mapear un libro si existe, null si no")
    void testBuscarLibroPorId() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER, titulo TEXT, copias_disponibles INTEGER)");
                s.execute("INSERT INTO libros VALUES (10, 'La Fundación', 3)");
            }
            
            var libro = Nivel05_A_DaoLibrosBuscar.buscarLibroPorId(conn, 10);
            assertNotNull(libro, "¡Fallo! Retornó null pero el libro 10 sí existía.");
            assertEquals("La Fundación", libro.titulo());
            assertEquals(3, libro.copias_disponibles());
            
            var nulo = Nivel05_A_DaoLibrosBuscar.buscarLibroPorId(conn, 99);
            assertNull(nulo, "¡Fallo! Retornó un objeto para un libro fantasma.");
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
