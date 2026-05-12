package jdbc_masterclass.nivel04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel04_E_BorradoEnCascada {

    /**
     * Misión: El Borrado Padre-Hijo (Cuidando la Clave Foránea)
     * Si borras un Autor que tiene Libros, la BBDD gritará "Foreign Key Constraint Failed".
     * Siempre debes borrar los "hijos" (libros) antes que el "padre" (autor).
     *
     * @param conn Conexión activa
     * @param idAutor ID del autor a eliminar
     */
    public static void borrarAutorSeguro(Connection conn, int idAutor) throws SQLException {
        // En este ejercicio, delegamos el auto-commit temporalmente
        conn.setAutoCommit(false);
        
        try {
            // TODO: Haz un DELETE en la tabla 'libros' WHERE id_autor = ?
            
            // TODO: Haz un DELETE en la tabla 'autores' WHERE id = ?
            
            // TODO: Confirma la transacción con commit()
        } catch(SQLException e) {
            // TODO: Si algo falla, revierte todo.
            conn.rollback();
            throw e; // Relanzamos para que la app se entere del fallo final
        }
    }
}
