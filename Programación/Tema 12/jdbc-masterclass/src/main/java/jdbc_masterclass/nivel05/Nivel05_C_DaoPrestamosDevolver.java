package jdbc_masterclass.nivel05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel05_C_DaoPrestamosDevolver {

    /**
     * Misión: DAO Devolver Préstamo
     * La inversa exacta. Pasamos de 'ACTIVO' a 'DEVUELTO' y sumamos stock.
     */
    public static void devolverPrestamo(Connection conn, int idPrestamo, int idLibroAsociado) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // TODO: UPDATE prestamos SET estado = 'DEVUELTO' WHERE id = ?
            
            // TODO: UPDATE libros SET copias_disponibles = copias_disponibles + 1 WHERE id = ?
            
            // TODO: commit()
        } catch (SQLException e) {
            // TODO: rollback() y throw e;
            conn.rollback();
            throw e;
        }
    }
}
