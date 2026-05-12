package jdbc_masterclass.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel03_E_ValidarFilasTest {

    @Test
    @DisplayName("Validación 1: Comprobar el retorno estricto = 1")
    void testActualizarSueldo() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS empleados");
                s.execute("CREATE TABLE empleados (id INTEGER PRIMARY KEY, sueldo REAL)");
                s.execute("INSERT INTO empleados VALUES (1, 1000.0)");
            }
            
            boolean exito = Nivel03_E_ValidarFilas.actualizarSueldo(conn, 1, 1500.0);
            assertTrue(exito, "¡Fallo! Deberías retornar true al actualizar exactamente 1 fila existente.");
            
            boolean fallo = Nivel03_E_ValidarFilas.actualizarSueldo(conn, 99, 2000.0);
            assertFalse(fallo, "¡Fallo! Retornaste true al actualizar un ID fantasma.");
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
