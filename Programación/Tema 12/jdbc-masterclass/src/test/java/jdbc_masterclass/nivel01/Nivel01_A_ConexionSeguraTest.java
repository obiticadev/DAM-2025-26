package jdbc_masterclass.nivel01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class Nivel01_A_ConexionSeguraTest {

    @Test
    @DisplayName("Validación 1: Singleton debe devolver una conexión activa")
    void testObtenerConexionSingleton_DevuelveConexionActiva() throws SQLException {
        Connection conn = Nivel01_A_ConexionSegura.obtenerConexionSingleton();
        
        assertNotNull(conn, "¡Fallo! El Singleton devolvió null. ¿Has inicializado la conexión?");
        assertFalse(conn.isClosed(), "¡Fallo! La conexión devuelta está cerrada.");
    }

    @Test
    @DisplayName("Validación 2: Singleton debe devolver SIEMPRE la misma instancia")
    void testObtenerConexionSingleton_MismaInstancia() throws SQLException {
        Connection conn1 = Nivel01_A_ConexionSegura.obtenerConexionSingleton();
        Connection conn2 = Nivel01_A_ConexionSegura.obtenerConexionSingleton();
        
        assertSame(conn1, conn2, "¡Fallo! Las instancias de conexión no son la misma. El patrón Singleton no se ha implementado correctamente.");
    }

    @Test
    @DisplayName("Validación 3: Si se cierra la conexión manualmente, el Singleton debe restaurarla")
    void testObtenerConexionSingleton_ReconexionSiCerrada() throws SQLException {
        Connection connVieja = Nivel01_A_ConexionSegura.obtenerConexionSingleton();
        connVieja.close(); // Forzamos el cierre desde fuera
        
        Connection connNueva = Nivel01_A_ConexionSegura.obtenerConexionSingleton();
        assertNotNull(connNueva, "¡Fallo! Tras cerrar la conexión, el Singleton devolvió null.");
        assertFalse(connNueva.isClosed(), "¡Fallo! Tras cerrar la conexión, el Singleton nos dio una conexión cerrada. ¿Has comprobado conn.isClosed() en tu código?");
        assertNotSame(connVieja, connNueva, "¡Fallo! Tras cerrar la conexión vieja, devolviste el mismo objeto obsoleto en lugar de uno nuevo.");
    }

    @Test
    @DisplayName("Validación 4: La conexión efímera con Try-With-Resources no debe devolver error")
    void testProbarConexionEfimera_RetornaTrue() {
        boolean resultado = Nivel01_A_ConexionSegura.probarConexionEfimera();
        assertTrue(resultado, "¡Fallo! Tu conexión efímera con Try-With-Resources ha devuelto false. Revisa tu bloque Try-Catch.");
    }
}
