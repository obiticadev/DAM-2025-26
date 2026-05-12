package jdbc_masterclass.nivel05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class Nivel05_D_ManejoExcepcionesComplejasTest {

    @Test
    @DisplayName("Validación 1: Atrapa violaciones de Foreign Key")
    void testConstraint() {
        try (Connection conn = jdbc_masterclass.nivel01.Nivel01_A_ConexionSegura.obtenerConexionSingleton()) {
            try(Statement s = conn.createStatement()) {
                s.execute("PRAGMA foreign_keys = ON;");
                s.execute("DROP TABLE IF EXISTS tabla_hija");
                s.execute("DROP TABLE IF EXISTS tabla_padre");
                s.execute("CREATE TABLE tabla_padre (id INTEGER PRIMARY KEY)");
                s.execute("CREATE TABLE tabla_hija (id_padre INTEGER, FOREIGN KEY(id_padre) REFERENCES tabla_padre(id))");
            }
            
            assertFalse(Nivel05_D_ManejoExcepcionesComplejas.insertarForzandoErrorClaveFija(conn), "¡Fallo! Debería retornar false al saltar el SQLException de Constraint.");
        } catch (SQLException e) {
            fail("Excepción escapada hacia arriba: " + e.getMessage());
        }
    }
}
