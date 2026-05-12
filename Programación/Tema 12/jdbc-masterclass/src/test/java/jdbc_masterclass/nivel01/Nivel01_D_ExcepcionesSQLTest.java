package jdbc_masterclass.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class Nivel01_D_ExcepcionesSQLTest {

    @Test
    @DisplayName("Validación 1: Debe capturar y devolver el error de sintaxis correctamente")
    void testForzarYCapturarErrorSintaxis() {
        try (Connection conn = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            String errorMsg = Nivel01_D_ExcepcionesSQL.forzarYCapturarErrorSintaxis(conn);
            
            assertNotEquals("SIN ERROR", errorMsg, "¡Fallo! No se lanzó excepción o no se atrapó correctamente en el catch.");
            assertTrue(errorMsg.toLowerCase().contains("syntax error") || errorMsg.toLowerCase().contains("selct"), 
                "¡Fallo! El mensaje devuelto no parece un mensaje de error de sintaxis de SQL. Recibido: " + errorMsg);
            
        } catch (SQLException e) {
            fail("Excepción inesperada en test: " + e.getMessage());
        }
    }
}
