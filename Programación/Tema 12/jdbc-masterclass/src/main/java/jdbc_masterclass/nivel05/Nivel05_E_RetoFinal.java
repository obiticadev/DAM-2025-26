package jdbc_masterclass.nivel05;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivel05_E_RetoFinal {

    /**
     * Misión: El Arquitecto de Flujos
     * 
     * Eres el controlador principal de la App. Debes usar los métodos de las clases de este nivel
     * para orquestar la siguiente historia:
     * 
     * 1. Preparar las tablas (ya te lo doy hecho en el test).
     * 2. Insertar un libro temporal directamente con un executeUpdate (Libro ID 500, copias 1).
     * 3. Llamar a Nivel05_B_DaoPrestamosInsertar.realizarPrestamo(conn, 500, 1)
     * 4. Llamar a Nivel05_C_DaoPrestamosDevolver.devolverPrestamo(conn, idPrestamo, 500)
     *    *Nota: Supón que el idPrestamo insertado fue el 1.*
     * 
     * @param conn Conexión activa
     * @return true si la historia termina sin excepciones.
     */
    public static boolean ejecutarHistoriaCompleta(Connection conn) {
        try {
            // TODO: Haz un executeUpdate simple para "INSERT INTO libros (id, copias_disponibles) VALUES (500, 1)"
            
            // TODO: Haz un executeUpdate simple para crear un usuario "INSERT INTO usuarios (id, nombre) VALUES (1, 'Admin')"
            
            // TODO: Usa la clase Nivel05_B_DaoPrestamosInsertar para realizar el préstamo del libro 500 al usuario 1.
            
            // TODO: Usa la clase Nivel05_C_DaoPrestamosDevolver para devolver el préstamo (asume que el ID del préstamo fue 1).
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
