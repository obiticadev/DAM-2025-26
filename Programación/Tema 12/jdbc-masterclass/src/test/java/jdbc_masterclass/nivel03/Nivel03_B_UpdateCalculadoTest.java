package jdbc_masterclass.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel03_B_UpdateCalculadoTest {

    @Test
    @DisplayName("Validación 1: Comprobar el UPDATE con cálculo en BBDD")
    void testRebajarPrecios() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS productos");
                s.execute("CREATE TABLE productos (id INTEGER PRIMARY KEY, precio REAL)");
                s.execute("INSERT INTO productos VALUES (1, 100.0)");
                s.execute("INSERT INTO productos VALUES (2, 50.0)");
            }
            
            int rebajados = Nivel03_B_UpdateCalculado.rebajarPreciosUnVeintePorciento(conn);
            assertEquals(2, rebajados, "¡Fallo! Debería retornar 2 filas modificadas.");
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT precio FROM productos WHERE id = 1")) {
                assertTrue(rs.next());
                assertEquals(80.0, rs.getDouble(1), 0.01, "¡Fallo! El descuento del 20% no se aplicó en SQL.");
            }
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
