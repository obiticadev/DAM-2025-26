package jdbc_masterclass.nivel05;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivel05_D_ManejoExcepcionesComplejas {

    /**
     * Misión: Constraint Violation
     * ¿Qué pasa si intento hacer un préstamo a un usuario que NO existe en base de datos?
     * ¡SQLite saltará con un Foreign Key Constraint Violation! 
     * Debemos atrapar la excepción y devolver 'false' indicando que se denegó el préstamo.
     */
    public static boolean insertarForzandoErrorClaveFija(Connection conn) {
        String insertLoco = "INSERT INTO tabla_hija (id_padre) VALUES (99999)";
        
        // TODO: Abre un Statement y ejecuta 'insertLoco'
        // TODO: Retorna true si increíblemente se guardó
        // TODO: En el catch, retorna false (indicando que paramos la ejecución por violación de clave ajena)
        
        return true;
    }
}
