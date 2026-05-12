package jdbc_masterclass.nivel01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Nivel01_A_ConexionSegura {

    // URL de la base de datos (SQLite en memoria para hacer tests volátiles)
    public static final String URL_BD = "jdbc:sqlite::memory:";
    
    // Instancia estática privada para almacenar nuestra tubería viva
    private static Connection instanciaConexion = null;

    /**
     * Misión 1: Patrón Singleton
     * El objetivo es devolver la 'instanciaConexion'. 
     * PERO debes comprobar: 
     * 1. Si la instancia es null, inicialízala con DriverManager.getConnection(URL_BD)
     * 2. Si la instancia está cerrada (método isClosed()), vuelve a inicializarla.
     * En caso de que se lance un SQLException, captúralo, imprime su mensaje y no hagas nada más.
     */
    public static Connection obtenerConexionSingleton() {
        // TODO: Misión 1 -> Escribe la lógica para retornar el Singleton aquí
        
        return instanciaConexion;
    }


    /**
     * Misión 2: El poder del Try-With-Resources
     * Crea un método que devuelva true SI Y SÓLO SI eres capaz de crear una conexión,
     * imprimir por consola "¡Me he conectado!" y que se cierre completamente sola sin usar el bloque finally.
     * (Obviamente, no llames al singleton aquí, inicializa una DriverManager.getConnection directamente dentro del try)
     */
    public static boolean probarConexionEfimera() {
        // TODO: Misión 2 -> Escribe tu Try-With-Resources. Retorna true al final del try, o false si salta el catch.
        
        return false;
    }


    // =======================================================
    // 🛝 PLAYGROUND MASTER (ZONA DE EJECUCIÓN)
    // Usa este main para probar tu código dándole al "Run"
    // =======================================================
    public static void main(String[] args) {
        System.out.println("--- PRUEBA DE SINGLETON ---");
        Connection c1 = obtenerConexionSingleton();
        Connection c2 = obtenerConexionSingleton();
        
        System.out.println("¿Es la misma conexión en memoria?: " + (c1 == c2));
        System.out.println("\n--- PRUEBA DE CONEXIÓN EFÍMERA ---");
        boolean funciono = probarConexionEfimera();
        System.out.println("¿Funcionó la efímera sin arrojar fallos?: " + funciono);
    }
}
