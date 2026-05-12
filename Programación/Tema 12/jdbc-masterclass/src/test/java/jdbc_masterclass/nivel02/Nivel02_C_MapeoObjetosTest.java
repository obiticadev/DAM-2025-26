package jdbc_masterclass.nivel02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Nivel02_C_MapeoObjetosTest {

    @Test
    @DisplayName("Validación 1: Debe convertir las filas de ResultSet en objetos LibroDto")
    void testMapearTodosLosLibros() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT)");
                s.execute("INSERT INTO libros (titulo, autor) VALUES ('Cien años de soledad', 'Gabo')");
            }
            
            List<Nivel02_C_MapeoObjetos.LibroDto> libros = Nivel02_C_MapeoObjetos.mapearTodosLosLibros(conn);
            assertEquals(1, libros.size(), "¡Fallo! La lista no mapeó la única fila existente.");
            
            Nivel02_C_MapeoObjetos.LibroDto libro = libros.get(0);
            assertNotNull(libro, "¡Fallo! Has añadido un objeto nulo a la lista.");
            assertEquals("Cien años de soledad", libro.titulo(), "El mapeo del título falló.");
            assertEquals("Gabo", libro.autor(), "El mapeo del autor falló.");
            assertTrue(libro.id() > 0, "El mapeo del ID falló o no se autogeneró correctamente.");
            
        } catch (SQLException e) {
            fail("Excepción inesperada en test: " + e.getMessage());
        }
    }
}
