package jdbc_masterclass.nivel01;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivel01_C_CrearTabla {

    /**
     * Misión: DDL (Data Definition Language)
     * Cuando creamos o alteramos tablas, la instrucción correcta es execute() y no
     * executeQuery().
     * 
     * @param conn Conexión activa
     * @return true si la tabla se crea con éxito, false si hay error.
     * @throws SQLException
     */
    public static boolean inicializarTablaLibros(Connection conn) throws SQLException {
        String sql = """
                    CREATE TABLE IF NOT EXISTS libros (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        titulo TEXT NOT NULL,
                        autor TEXT NOT NULL
                    );
                """;

        // TODO: Abre un Statement usando try-with-resources con conn.createStatement()
        // TODO: Llama a stmt.execute(sql)
        // TODO: Retorna true si todo va bien. Captura SQLException y retorna false si
        // falla.

        try (Statement stmt = conn.createStatement()) {
            return stmt.execute(sql);
        }
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            boolean exito = inicializarTablaLibros(c);
            System.out.println("¿Se creó la tabla correctamente?: " + exito);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
