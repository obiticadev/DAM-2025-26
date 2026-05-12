package jdbc_masterclass.nivel05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nivel05_A_DaoLibrosBuscar {

    public record Libro(int id, String titulo, int copias_disponibles) {}

    /**
     * Misión: DAO Buscar
     * 
     * @param conn Conexión
     * @param idLibro ID a buscar
     * @return Objeto Libro instanciado, o null si no se encontró en BBDD.
     */
    public static Libro buscarLibroPorId(Connection conn, int idLibro) throws SQLException {
        // TODO: SELECT id, titulo, copias_disponibles FROM libros WHERE id = ?
        // TODO: Si rs.next() es true, instancia el 'new Libro(...)' y retórnalo.
        // TODO: Si es false, retorna null.
        
        return null;
    }
}
