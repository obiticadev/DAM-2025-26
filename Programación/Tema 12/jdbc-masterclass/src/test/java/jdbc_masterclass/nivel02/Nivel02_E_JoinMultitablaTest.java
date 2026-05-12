package jdbc_masterclass.nivel02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Nivel02_E_JoinMultitablaTest {

    @Test
    @DisplayName("Validación 1: Debe retornar la concatenación exacta usando el JOIN y alias")
    void testCruzarTablas() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("DROP TABLE IF EXISTS editoriales");
                s.execute("CREATE TABLE editoriales (id INTEGER PRIMARY KEY, nombre TEXT)");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY, titulo TEXT, id_editorial INTEGER)");
                
                s.execute("INSERT INTO editoriales VALUES (1, 'Salamandra')");
                s.execute("INSERT INTO libros VALUES (1, 'Harry Potter', 1)");
            }
            
            List<String> resultados = Nivel02_E_JoinMultitabla.cruzarTablas(conn);
            assertEquals(1, resultados.size(), "¡Fallo! El JOIN no cruzó los datos correctamente.");
            assertEquals("Harry Potter - Salamandra", resultados.get(0), "¡Fallo! El formato concatenado no coincide con 'Titulo - Editorial' o no leíste bien el Alias.");
            
        } catch (SQLException e) {
            fail("Excepción inesperada en test: " + e.getMessage());
        }
    }
}
