package jdbc_masterclass.nivel04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivel04_A_RecuperarIDGenerado {

    /**
     * Misión: El vuelo del Autoincrementable
     * 
     * @param conn Conexión activa
     * @param nombreLibro Nombre del nuevo libro
     * @return El ID numérico (Autoincremental) que la BBDD le acaba de asignar. Devuelve -1 si falla.
     */
    public static int crearLibroYObtenerID(Connection conn, String nombreLibro) {
        String sql = "INSERT INTO libros_magicos (titulo) VALUES (?)";
        
        // TODO: Abre PreparedStatement inyectando Statement.RETURN_GENERATED_KEYS
        // TODO: Ejecuta executeUpdate
        // TODO: Pide el pstmt.getGeneratedKeys()
        // TODO: Haz next() y extrae el entero de la primera columna
        
        return -1;
    }
}
