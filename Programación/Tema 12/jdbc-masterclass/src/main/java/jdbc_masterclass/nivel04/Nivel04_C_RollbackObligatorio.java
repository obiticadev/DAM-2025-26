package jdbc_masterclass.nivel04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel04_C_RollbackObligatorio {

    /**
     * Misión: La marcha atrás
     * Si envuelves operaciones en un try-catch y algo falla, TIENES que revertir lo hecho.
     * 
     * @param conn Conexión activa
     * @return true si se disparó el rollback correctamente.
     */
    public static boolean forzarErrorYRevertir(Connection conn) {
        try {
            conn.setAutoCommit(false);
            
            String sql1 = "UPDATE inventario SET copias = copias - 1 WHERE id = 10"; // Esto funcionará
            String sql2 = "UPDATE inventario SET campo_falso = 100 WHERE id = 20";   // Esto ROMPERÁ
            
            // TODO: Ejecuta ambas operaciones. Evidentemente sql2 lanzará SQLException.
            // TODO: Haz un commit() al final del try (que nunca llegará a ejecutarse).
            
            return false;
        } catch (SQLException e) {
            // TODO: Haz un rollback de la conexión aquí.
            // TODO: Retorna true si logras hacer el rollback sin que el rollback explote.
            return true; // Reemplaza esto con tu lógica
        }
    }
}
