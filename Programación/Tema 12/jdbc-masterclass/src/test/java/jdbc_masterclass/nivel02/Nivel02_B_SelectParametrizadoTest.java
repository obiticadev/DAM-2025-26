package jdbc_masterclass.nivel02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel02_B_SelectParametrizadoTest {

    @Test
    @DisplayName("Validación 1: Debe retornar el conteo exacto protegiendo contra inyección SQL")
    void testContarLibrosPorAutor() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("DROP TABLE IF EXISTS libros");
                s.execute("CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT)");
                s.execute("INSERT INTO libros (titulo, autor) VALUES ('Libro A', 'Tolkien')");
                s.execute("INSERT INTO libros (titulo, autor) VALUES ('Libro B', 'Tolkien')");
                s.execute("INSERT INTO libros (titulo, autor) VALUES ('Libro C', 'Rowling')");
            }
            
            int cantidadTolkien = Nivel02_B_SelectParametrizado.contarLibrosPorAutor(conn, "Tolkien");
            assertEquals(2, cantidadTolkien, "¡Fallo! Deberían ser 2 libros para Tolkien.");
            
            int cantidadNula = Nivel02_B_SelectParametrizado.contarLibrosPorAutor(conn, "Inventado");
            assertEquals(0, cantidadNula, "¡Fallo! Debería ser 0 para un autor que no existe.");
            
        } catch (SQLException e) {
            fail("Excepción inesperada en test: " + e.getMessage());
        }
    }
}
