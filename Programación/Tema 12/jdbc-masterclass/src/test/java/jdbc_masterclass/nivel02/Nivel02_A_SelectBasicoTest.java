package jdbc_masterclass.nivel02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Nivel02_A_SelectBasicoTest {

    @Test
    @DisplayName("Validación 1: Debe retornar una lista con los títulos exactamente")
    void testObtenerTodosLosTitulos() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            // Reiniciar DB
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT)");
                s.execute("INSERT INTO libros (titulo) VALUES ('Harry Potter')");
                s.execute("INSERT INTO libros (titulo) VALUES ('El Hobbit')");
            }
            
            List<String> titulos = Nivel02_A_SelectBasico.obtenerTodosLosTitulos(conn);
            assertEquals(2, titulos.size(), "¡Fallo! La lista no tiene el tamaño correcto. ¿Has iterado el ResultSet con while(rs.next())?");
            assertTrue(titulos.contains("Harry Potter"), "Falta un título esperado en la lista.");
            assertTrue(titulos.contains("El Hobbit"), "Falta un título esperado en la lista.");
            
        } catch (SQLException e) {
            fail("Excepción inesperada en test: " + e.getMessage());
        }
    }
}
