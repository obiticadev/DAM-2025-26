package jdbc_masterclass.nivel03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel03_C_DeleteSeguro {

    /**
     * Misión: Borrado con validación
     * Un DELETE sin coincidencias no explota, retorna 0. Debemos avisar al usuario devolviendo false si no borramos nada.
     *
     * @param conn Conexión activa
     * @param id El ID del registro a borrar
     * @return true si realmente se borró el registro, false si el ID no existía
     */
    public static boolean eliminarRegistro(Connection conn, int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        
        // TODO: Pasa el parámetro usando setInt
        // TODO: Ejecuta y si el número de filas afectadas es 1, retorna true
        
        return false;
    }
}
