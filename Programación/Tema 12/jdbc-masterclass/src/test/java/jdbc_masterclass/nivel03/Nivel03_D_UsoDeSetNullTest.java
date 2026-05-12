package jdbc_masterclass.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel03_D_UsoDeSetNullTest {

    @Test
    @DisplayName("Validación 1: Diferenciación de URL e Inyección Null pura")
    void testInsertarLibroMixto() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, url_descarga TEXT)");
            }
            
            Nivel03_D_UsoDeSetNull.insertarLibroMixto(conn, "Digital", true);
            Nivel03_D_UsoDeSetNull.insertarLibroMixto(conn, "Papel", false);
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT url_descarga FROM libros WHERE titulo = 'Papel'")) {
                assertTrue(rs.next());
                assertNull(rs.getString(1), "¡Fallo! La base de datos tiene un string en lugar de un NULL real.");
            }
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
