package jdbc_masterclass.nivel04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nivel04_D_TraspasoDeDatos {

    /**
     * Misión: Traspaso Dinámico (SELECT + INSERT encadenados)
     * Igual que en el "Ejercicio 2" de clase, debemos extraer de una tabla y llenar otra dinámicamente.
     *
     * @param conn Conexión activa
     * @return El número de registros traspasados exitosamente
     */
    public static int migrarAutores(Connection conn) throws SQLException {
        String sqlSelect = "SELECT nombre FROM autores_viejos";
        String sqlInsert = "INSERT INTO autores_nuevos (nombre) VALUES (?)";
        
        int insertados = 0;
        
        // TODO: Abre PreparedStatement para el SELECT y ejecuta (rs)
        // TODO: Abre PreparedStatement para el INSERT antes del bucle para optimizar
        // TODO: En el while(rs.next()), extrae el nombre
        // TODO: Mete el nombre en el PreparedStatement del INSERT (setString) y haz executeUpdate()
        // TODO: Suma 1 a 'insertados' por cada vuelta exitosa
        
        return insertados;
    }
}
