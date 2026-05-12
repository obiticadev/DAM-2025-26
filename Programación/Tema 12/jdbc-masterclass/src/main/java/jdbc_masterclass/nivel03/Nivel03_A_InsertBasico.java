package jdbc_masterclass.nivel03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel03_A_InsertBasico {

    /**
     * Misión: Insertar un Fabricante usando PreparedStatement
     *
     * @param conn Conexión activa
     * @param codigo ID del fabricante
     * @param nombre Nombre del fabricante
     * @return true si se insertó con éxito, false si ocurrió algún error o las filas afectadas son 0.
     */
    public static boolean insertarFabricante(Connection conn, int codigo, String nombre) {
        String sql = "INSERT INTO fabricante (codigo, nombre) VALUES (?, ?)";
        
        // TODO: Prepara el statement
        // TODO: Asigna el parámetro 1 a codigo, y el parámetro 2 a nombre (setX)
        // TODO: Ejecuta la actualización (executeUpdate)
        // TODO: Retorna true si las filas afectadas son mayores a 0
        
        return false;
    }
}
