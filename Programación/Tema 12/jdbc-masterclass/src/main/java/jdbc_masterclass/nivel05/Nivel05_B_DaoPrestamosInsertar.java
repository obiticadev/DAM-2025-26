package jdbc_masterclass.nivel05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel05_B_DaoPrestamosInsertar {

    /**
     * Misión: DAO Insertar Préstamo con Transacción
     * Debemos restar una copia al libro y añadir un registro al préstamo atómicamente.
     */
    public static boolean realizarPrestamo(Connection conn, int idLibro, int idUsuario) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // TODO: Restar 1 a 'copias_disponibles' en la tabla 'libros' WHERE id = ?
            
            // TODO: Hacer un INSERT INTO prestamos (id_libro, id_usuario, estado) VALUES (?, ?, 'ACTIVO')
            
            // TODO: Haz commit() y retorna true
            return false;
        } catch (SQLException e) {
            // TODO: Haz rollback()
            conn.rollback();
            throw e;
        }
    }
}
