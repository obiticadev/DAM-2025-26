package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej07_MultiplesTablasCreacion;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej07Test {

    @Test
    @Order(1)
    @DisplayName("crearTablaAutores() no lanza excepción")
    void crearTablaAutoresOk() {
        assertDoesNotThrow(() -> Ej07_MultiplesTablasCreacion.crearTablaAutores());
    }

    @Test
    @Order(2)
    @DisplayName("crearTablaEditoriales() no lanza excepción")
    void crearTablaEditorialesOk() {
        assertDoesNotThrow(() -> Ej07_MultiplesTablasCreacion.crearTablaEditoriales());
    }

    @Test
    @Order(3)
    @DisplayName("crearTablaLibros() no lanza excepción")
    void crearTablaLibrosOk() {
        assertDoesNotThrow(() -> Ej07_MultiplesTablasCreacion.crearTablaLibros());
    }

    @Test
    @Order(4)
    @DisplayName("inicializarBaseDatos() crea las tres tablas sin excepción")
    void inicializarBaseDatosOk() {
        assertDoesNotThrow(() -> Ej07_MultiplesTablasCreacion.inicializarBaseDatos());
    }

    @Test
    @Order(5)
    @DisplayName("inicializarBaseDatos() es idempotente")
    void inicializarBaseDatosIdempotente() {
        assertDoesNotThrow(() -> {
            Ej07_MultiplesTablasCreacion.inicializarBaseDatos();
            Ej07_MultiplesTablasCreacion.inicializarBaseDatos();
        }, "Llamar a inicializarBaseDatos() dos veces no debe lanzar excepción");
    }

    @Test
    @Order(6)
    @DisplayName("getConexion() devuelve la misma instancia en todos los métodos (Singleton)")
    void mismaInstanciaEnTodosLosMetodos() throws SQLException {
        var c1 = Ej07_MultiplesTablasCreacion.getConexion();
        Ej07_MultiplesTablasCreacion.crearTablaAutores();
        var c2 = Ej07_MultiplesTablasCreacion.getConexion();
        assertSame(c1, c2, "La conexión debe ser siempre el mismo objeto Singleton");
    }
}
