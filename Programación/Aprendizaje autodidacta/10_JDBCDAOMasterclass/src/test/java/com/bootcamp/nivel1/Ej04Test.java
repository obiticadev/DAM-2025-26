package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej04_CrearTabla;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej04Test {

    @BeforeEach
    void limpiar() throws SQLException {
        // Parte del estado anterior entre tests
        try {
            Ej04_CrearTabla.crearTablaPersonalizada("Peliculas_limpieza",
                "id INTEGER PRIMARY KEY");
        } catch (Exception ignored) {}
    }

    @Test
    @Order(1)
    @DisplayName("crearTablaPeliculas() no lanza excepción")
    void crearTablaPeliculasOk() {
        assertDoesNotThrow(() -> Ej04_CrearTabla.crearTablaPeliculas());
    }

    @Test
    @Order(2)
    @DisplayName("Tabla Peliculas existe tras crearTablaPeliculas()")
    void tablaPeliculasExiste() throws SQLException {
        Ej04_CrearTabla.crearTablaPeliculas();
        assertTrue(Ej04_CrearTabla.tablaExiste("Peliculas"),
                "Después de crearTablaPeliculas() la tabla debe existir");
    }

    @Test
    @Order(3)
    @DisplayName("crearTablaPeliculas() es idempotente (IF NOT EXISTS)")
    void crearTablaPeliculasIdempotente() {
        assertDoesNotThrow(() -> {
            Ej04_CrearTabla.crearTablaPeliculas();
            Ej04_CrearTabla.crearTablaPeliculas(); // Segunda llamada no debe lanzar
        });
    }

    @Test
    @Order(4)
    @DisplayName("crearTablaPersonalizada() crea la tabla con el nombre dado")
    void crearTablaPersonalizadaOk() throws SQLException {
        Ej04_CrearTabla.crearTablaPersonalizada("TestPersonalizada",
                "id INTEGER PRIMARY KEY, valor TEXT");
        assertTrue(Ej04_CrearTabla.tablaExiste("TestPersonalizada"),
                "La tabla personalizada debe existir tras crearla");
    }

    @Test
    @Order(5)
    @DisplayName("tablaExiste() devuelve false para tabla inexistente")
    void tablaNoExistente() throws SQLException {
        assertFalse(Ej04_CrearTabla.tablaExiste("TablaQueNoExisteJamas"),
                "tablaExiste() debe devolver false para una tabla que no existe");
    }
}
