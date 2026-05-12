package jdbc_masterclass.nivel01;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivel01_D_ExcepcionesSQL {

    /**
     * Misión: Análisis de Errores SQL
     * Ejecuta una consulta a sabiendas de que tiene un error de sintaxis y analiza la excepción.
     * 
     * @param conn Conexión activa
     * @return El mensaje de error nativo devuelto por el motor de BD.
     */
    public static String forzarYCapturarErrorSintaxis(Connection conn) {
        String sqlInvalido = "SELCT * FRO tabla_falsa"; // Mal escrito a propósito
        
        // TODO: Abre un Statement y haz un executeQuery con la variable sqlInvalido
        // TODO: En el bloque catch (SQLException e), retorna e.getMessage()
        // TODO: Si por un milagro no salta excepción, retorna "SIN ERROR"
        
        return "SIN ERROR";
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            System.out.println("Mensaje de error interceptado:");
            System.out.println(forzarYCapturarErrorSintaxis(c));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
