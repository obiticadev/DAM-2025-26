package jdbc_masterclass.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel04_B_TransaccionBasicaTest {

    @Test
    @DisplayName("Validación 1: La transacción debe aplicarse atómicamente")
    void testTraspasarStock() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS inventario");
                s.execute("CREATE TABLE inventario (id INTEGER PRIMARY KEY, copias INTEGER)");
                s.execute("INSERT INTO inventario VALUES (10, 5)");
                s.execute("INSERT INTO inventario VALUES (20, 5)");
            }
            
            boolean exito = Nivel04_B_TransaccionBasica.traspasarStock(conn, 10, 20);
            assertTrue(exito, "¡Fallo! El método no retornó true. Asegúrate de hacer commit().");
            
            assertFalse(conn.getAutoCommit(), "¡Fallo! No desactivaste el auto-commit.");
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT copias FROM inventario WHERE id = 10")) {
                rs.next();
                assertEquals(4, rs.getInt(1), "El stock del libro origen no se restó.");
            }
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT copias FROM inventario WHERE id = 20")) {
                rs.next();
                assertEquals(6, rs.getInt(1), "El stock del libro destino no se sumó.");
            }
            
            // Volvemos a dejarlo como estaba para no afectar al resto de tests
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
