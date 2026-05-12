package jdbc_masterclass.nivel03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel03_C_DeleteSeguroTest {

    @Test
    @DisplayName("Validación 1: Borrado de IDs existentes e inexistentes")
    void testEliminarRegistro() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS empleados");
                s.execute("CREATE TABLE empleados (id INTEGER PRIMARY KEY)");
                s.execute("INSERT INTO empleados VALUES (10)");
            }
            
            assertTrue(Nivel03_C_DeleteSeguro.eliminarRegistro(conn, 10), "Falló al borrar ID que sí existía.");
            assertFalse(Nivel03_C_DeleteSeguro.eliminarRegistro(conn, 999), "Retornaste true al borrar un ID inexistente.");
            
        } catch (SQLException e) {
            fail("Excepción en test: " + e.getMessage());
        }
    }
}
