package jdbc_masterclass.nivel02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Nivel02_A_SelectBasico {

    /**
     * Misión: El Select Básico y el bucle while
     * 
     * @param conn Conexión activa
     * @return Una lista de Java con todos los 'títulos' de los libros en la base de
     *         datos.
     */
    public static List<String> obtenerTodosLosTitulos(Connection conn) {
        List<String> titulos = new ArrayList<>();
        String sql = "SELECT titulo FROM libros";

        // TODO: Abre un Statement (try-with-resources recomendado)
        // TODO: Ejecuta la query con executeQuery() y guarda el ResultSet
        // TODO: Itera el ResultSet con el método next()
        // TODO: Extrae el título usando getString() y añádelo a la lista 'titulos'
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                titulos.add(rs.getString("titulo"));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return titulos;
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            jdbc_masterclass.nivel01.Nivel01_C_CrearTabla.inicializarTablaLibros(c);
            jdbc_masterclass.nivel01.Nivel01_E_ScriptInit.inicializarDatosBase(c);

            System.out.println("Títulos encontrados en la BBDD:");
            List<String> titulos = obtenerTodosLosTitulos(c);
            titulos.forEach(t -> System.out.println("- " + t));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
