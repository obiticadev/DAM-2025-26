package jdbc_masterclass.nivel04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel04_B_TransaccionBasica {

    /**
     * Misión: Atomicidad en acción
     * Traspasar el stock de un libro a otro de forma segura. O se hace todo, o no se hace nada.
     * 
     * @param conn Conexión activa
     * @param idLibroOrigen ID del libro al que le quitamos 1 copia
     * @param idLibroDestino ID del libro al que le sumamos 1 copia
     * @return true si la transacción se confirma con éxito.
     */
    public static boolean traspasarStock(Connection conn, int idLibroOrigen, int idLibroDestino) throws SQLException {
        // TODO: 1. Desactiva el auto-commit
        
        String sqlRestar = "UPDATE inventario SET copias = copias - 1 WHERE id = ?";
        String sqlSumar  = "UPDATE inventario SET copias = copias + 1 WHERE id = ?";
        
        // TODO: 2. Ejecuta ambas consultas. Si ambas devuelven > 0 filas afectadas, haz commit() y retorna true.
        // TODO: 3. Si alguna falla o devuelve 0, lanza un SQLException manualmente para que el código externo se entere.
        // TODO: ¡OJO! No uses catch aquí, estamos lanzando (throws) el error hacia arriba. Pero el setAutoCommit debe estar false.
        
        return false;
    }
}
