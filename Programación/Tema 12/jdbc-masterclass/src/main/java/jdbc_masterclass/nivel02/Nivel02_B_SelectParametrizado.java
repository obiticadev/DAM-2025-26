package jdbc_masterclass.nivel02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivel02_B_SelectParametrizado {

    /**
     * Misión: Evitar Inyección SQL usando PreparedStatement
     * NUNCA debes concatenar strings en una query. Utiliza interrogaciones '?'.
     * 
     * @param conn  Conexión activa
     * @param autor El autor a buscar
     * @return La cantidad de libros escritos por ese autor
     */
    public static int contarLibrosPorAutor(Connection conn, String autor) {
        String sql = "SELECT COUNT(*) FROM libros WHERE autor = ?";
        int contador = 0;

        // TODO: Abre un PreparedStatement usando la variable sql (try-with-resources)
        // TODO: Sustituye la interrogación por la variable 'autor' usando setString()
        // (¡Ojo al índice!)
        // TODO: Ejecuta la consulta y extrae el valor devuelto por el COUNT(*)
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, autor);
            ResultSet rs = ps.executeQuery();
            contador = rs.getInt(1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return contador;
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            jdbc_masterclass.nivel01.Nivel01_C_CrearTabla.inicializarTablaLibros(c);
            jdbc_masterclass.nivel01.Nivel01_E_ScriptInit.inicializarDatosBase(c);

            String autorBuscado = "George Orwell";
            int cantidad = contarLibrosPorAutor(c, autorBuscado);
            System.out.println("Libros de " + autorBuscado + ": " + cantidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
