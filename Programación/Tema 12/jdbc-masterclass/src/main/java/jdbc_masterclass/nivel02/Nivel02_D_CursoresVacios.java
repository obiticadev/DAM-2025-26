package jdbc_masterclass.nivel02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nivel02_D_CursoresVacios {

    /**
     * Misión: Comprobar si un registro existe
     * Muchas veces no queremos una lista, queremos solo UN registro (por ID).
     * Si no existe, debemos devolver 'null', no un objeto vacío.
     * 
     * @param conn Conexión activa
     * @param id   El ID del libro a buscar
     * @return El título del libro si existe, o NULL si la query no devolvió nada.
     */
    public static String obtenerTituloPorId(Connection conn, int id) {
        String sql = "SELECT titulo FROM libros WHERE id = ?";

        // TODO: Abre PreparedStatement con try-with-resources
        // TODO: Ejecuta la consulta
        // TODO: Si rs.next() es true, retorna rs.getString(1).
        // TODO: Si rs.next() es false (el cursor está vacío), retorna null.
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getString(1);
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            jdbc_masterclass.nivel01.Nivel01_C_CrearTabla.inicializarTablaLibros(c);
            jdbc_masterclass.nivel01.Nivel01_E_ScriptInit.inicializarDatosBase(c); // Esto crea libros con ID 1, 2, 3...

            System.out.println("Libro ID 1: " + obtenerTituloPorId(c, 1));
            System.out.println("Libro ID 999: " + obtenerTituloPorId(c, 999)); // Debe dar null
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
