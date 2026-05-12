package jdbc_masterclass.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel04_C_RollbackObligatorioTest {

    @Test
    @DisplayName("Validación 1: Comprobar que los datos originales siguen intactos tras el error")
    void testForzarErrorYRevertir() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS inventario");
                s.execute("CREATE TABLE inventario (id INTEGER PRIMARY KEY, copias INTEGER)");
                s.execute("INSERT INTO inventario VALUES (10, 5)");
            }
            
            boolean rollbackActivado = Nivel04_C_RollbackObligatorio.forzarErrorYRevertir(conn);
            assertTrue(rollbackActivado, "¡Fallo! El método no retornó true en el catch.");
            
            try(Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("SELECT copias FROM inventario WHERE id = 10")) {
                rs.next();
                assertEquals(5, rs.getInt(1), "¡Fallo catastrófico! El primer update se guardó a pesar del error en el segundo. ¡No hiciste rollback!");
            }
            
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
