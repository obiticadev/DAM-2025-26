package jdbc_masterclass.nivel03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel03_E_ValidarFilas {

    /**
     * Misión: Validación estricta de filas
     * Un update sin 'WHERE' afectará a toda la tabla. Aquí queremos actualizar el sueldo de un empleado específico.
     * Si no existe, no debemos engañar a la app diciendo que se actualizó.
     *
     * @param conn Conexión activa
     * @param idEmpleado ID del empleado
     * @param nuevoSueldo Nuevo sueldo
     * @return true si SE ACTUALIZÓ EXACTAMENTE 1 FILA. false si se actualizaron 0, o si hubo error.
     */
    public static boolean actualizarSueldo(Connection conn, int idEmpleado, double nuevoSueldo) {
        String sql = "UPDATE empleados SET sueldo = ? WHERE id = ?";
        
        // TODO: Abre PreparedStatement y haz los sets
        // TODO: Comprueba el resultado de executeUpdate()
        // TODO: Retorna true SÓLO si el resultado es igual a 1
        
        return false;
    }
}
