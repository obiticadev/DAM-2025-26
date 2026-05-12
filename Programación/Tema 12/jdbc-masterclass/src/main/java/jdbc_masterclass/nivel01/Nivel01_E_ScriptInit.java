package jdbc_masterclass.nivel01;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Nivel01_E_ScriptInit {

    /**
     * Misión: Inserción Masiva (Batch Processing Básico)
     * Vamos a aprovechar la tabla 'libros' que creaste antes e inyectarle 3 registros de golpe.
     * 
     * @param conn Conexión activa
     * @return El número de comandos ejecutados con éxito en el batch.
     */
    public static int inicializarDatosBase(Connection conn) {
        String insert1 = "INSERT INTO libros (titulo, autor) VALUES ('El Quijote', 'Cervantes')";
        String insert2 = "INSERT INTO libros (titulo, autor) VALUES ('1984', 'George Orwell')";
        String insert3 = "INSERT INTO libros (titulo, autor) VALUES ('Dune', 'Frank Herbert')";
        
        // TODO: Abre un Statement con try-with-resources
        // TODO: Usa stmt.addBatch() para añadir los 3 inserts a la cola
        // TODO: Ejecuta la cola con stmt.executeBatch() y guarda el array de enteros resultante
        // TODO: Suma cuántas operaciones fueron exitosas y retorna ese número
        
        return 0;
    }

    // =======================================================
    // 🛝 PLAYGROUND MASTER
    // =======================================================
    public static void main(String[] args) {
        try (Connection c = Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            Nivel01_C_CrearTabla.inicializarTablaLibros(c); // Aseguramos que existe la tabla
            int filas = inicializarDatosBase(c);
            System.out.println("Comandos insertados mediante Batch: " + filas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
