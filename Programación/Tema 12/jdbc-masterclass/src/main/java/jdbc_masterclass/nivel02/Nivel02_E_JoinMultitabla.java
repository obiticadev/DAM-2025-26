package jdbc_masterclass.nivel02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Nivel02_E_JoinMultitabla {

    /**
     * Misión: Consultas complejas (JOIN) y alias de columnas
     * Vamos a simular un escenario donde tenemos 'libros' y 'fabricantes'/'autores'.
     * Las bases de datos se explotan al máximo al cruzar tablas.
     * 
     * @param conn Conexión activa
     * @return Lista de Strings formateados como "Libro - Autor"
     */
    public static List<String> cruzarTablas(Connection conn) {
        List<String> resultados = new ArrayList<>();
        
        // Fíjate en los ALIAS (l.titulo, e.nombre)
        String sql = "SELECT l.titulo, e.nombre AS editorial_nombre " +
                     "FROM libros l " +
                     "INNER JOIN editoriales e ON l.id_editorial = e.id";
        
        // TODO: Abre un Statement, ejecuta la query y haz el while(rs.next())
        // TODO: Extrae el título y el nombre de la editorial (¡ojo!, usa el alias de columna "editorial_nombre")
        // TODO: Añade a la lista el string concatenado "Titulo - Editorial"
        
        return resultados;
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            // Creamos las tablas para este experimento aislado
            try(Statement s = c.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("DROP TABLE IF EXISTS editoriales");
                s.execute("CREATE TABLE editoriales (id INTEGER PRIMARY KEY, nombre TEXT)");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY, titulo TEXT, id_editorial INTEGER)");
                
                s.execute("INSERT INTO editoriales VALUES (10, 'Planeta')");
                s.execute("INSERT INTO libros VALUES (1, 'IT', 10)");
            }
            
            System.out.println("Resultados del JOIN:");
            cruzarTablas(c).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
