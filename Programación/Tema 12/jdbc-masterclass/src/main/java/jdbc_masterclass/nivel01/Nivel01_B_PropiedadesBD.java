package jdbc_masterclass.nivel01;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Nivel01_B_PropiedadesBD {

    /**
     * Misión: Propiedades de la Base de Datos
     * JDBC nos permite consultar metadatos sobre el motor al que estamos
     * conectados.
     * 
     * @param conn Conexión activa a la base de datos
     * @return El nombre del producto de la base de datos (Ej: "SQLite", "MySQL")
     * @throws SQLException
     */
    public static String obtenerNombreMotorBD(Connection conn) throws SQLException {
        // TODO: Usa conn.getMetaData() para obtener un objeto DatabaseMetaData.
        // TODO: Llama al método getDatabaseProductName() y retorna su valor.
        // TODO: Si ocurre un SQLException, captura el error y retorna "DESCONOCIDO".

        return conn.getMetaData().getDatabaseProductName();
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            System.out.println("Motor de Base de Datos: " + obtenerNombreMotorBD(c));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
