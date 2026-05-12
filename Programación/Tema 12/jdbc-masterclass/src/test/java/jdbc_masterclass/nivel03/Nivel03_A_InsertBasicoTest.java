package jdbc_masterclass.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel03_A_InsertBasicoTest {

    @Test
    @DisplayName("Validación 1: Insertar fabricante verifica que se inserta correctamente en BBDD")
    void testInsertarFabricante() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS fabricante");
                s.execute("CREATE TABLE fabricante (codigo INTEGER PRIMARY KEY, nombre TEXT)");
            }
            
            boolean exito = Nivel03_A_InsertBasico.insertarFabricante(conn, 10, "MSI");
            assertTrue(exito, "¡Fallo! El método retornó false. Verifica que ejecutes executeUpdate() > 0.");
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT nombre FROM fabricante WHERE codigo = 10")) {
                assertTrue(rs.next(), "¡Fallo! La fila no se persistió en base de datos.");
                assertEquals("MSI", rs.getString(1));
            }
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
