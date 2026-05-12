package jdbc_masterclass.nivel02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel02_D_CursoresVaciosTest {

    @Test
    @DisplayName("Validación 1: Comprobar comportamiento con IDs existentes e inexistentes")
    void testObtenerTituloPorId() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT)");
                s.execute("INSERT INTO libros (id, titulo) VALUES (5, 'El Hobbit')");
            }
            
            String tituloExistente = Nivel02_D_CursoresVacios.obtenerTituloPorId(conn, 5);
            assertEquals("El Hobbit", tituloExistente, "¡Fallo! No se recuperó el libro existente.");
            
            String tituloFantasma = Nivel02_D_CursoresVacios.obtenerTituloPorId(conn, 999);
            assertNull(tituloFantasma, "¡Fallo! Si rs.next() es false, deberías haber devuelto null, pero devolviste otra cosa.");
            
        } catch (SQLException e) {
            fail("Excepción inesperada en test: " + e.getMessage());
        }
    }
}
