package jdbc_masterclass.nivel02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Nivel02_C_MapeoObjetos {

    // Entidad de Transporte Básica (POJO / Record)
    public record LibroDto(int id, String titulo, String autor) {
    }

    /**
     * Misión: El Patrón Mapeador (ORMs primitivos)
     * En la vida real no devolvemos "Strings". Devolvemos Objetos o Listas de
     * Objetos.
     * 
     * @param conn Conexión activa
     * @return Una lista de instancias LibroDto con todos los libros de la BBDD
     */
    public static List<LibroDto> mapearTodosLosLibros(Connection conn) {
        List<LibroDto> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor FROM libros";

        // TODO: Abre un Statement y ejecuta la consulta
        // TODO: En el bucle while, extrae id, titulo y autor
        // TODO: Por cada iteración, instancia un nuevo LibroDto y añádelo a la lista

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String string = rs.getString("titulo");
                String autor = rs.getString("autor");
                lista.add(new LibroDto(id, string, autor));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return lista;
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            jdbc_masterclass.nivel01.Nivel01_C_CrearTabla.inicializarTablaLibros(c);
            jdbc_masterclass.nivel01.Nivel01_E_ScriptInit.inicializarDatosBase(c);

            List<LibroDto> objetos = mapearTodosLosLibros(c);
            objetos.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
