package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej01_PrimeraConexion;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej01Test {

    @Test
    @Order(1)
    @DisplayName("abrirConexion() devuelve una conexión no nula")
    void abrirConexionNoEsNull() throws SQLException {
        Connection conn = Ej01_PrimeraConexion.abrirConexion();
        assertNotNull(conn, "abrirConexion() no debe devolver null");
        conn.close();
    }

    @Test
    @Order(2)
    @DisplayName("abrirConexion() devuelve una conexión abierta")
    void abrirConexionEstaAbierta() throws SQLException {
        Connection conn = Ej01_PrimeraConexion.abrirConexion();
        assertFalse(conn.isClosed(), "La conexión debe estar abierta al crearla");
        conn.close();
    }

    @Test
    @Order(3)
    @DisplayName("cerrarConexion() cierra la conexión correctamente")
    void cerrarConexionFunciona() throws SQLException {
        Connection conn = Ej01_PrimeraConexion.abrirConexion();
        Ej01_PrimeraConexion.cerrarConexion(conn);
        assertTrue(conn.isClosed(), "La conexión debe estar cerrada tras cerrarConexion()");
    }

    @Test
    @Order(4)
    @DisplayName("estaAbierta() devuelve true cuando la conexión está abierta")
    void estaAbiertaDevuelveTrue() throws SQLException {
        Connection conn = Ej01_PrimeraConexion.abrirConexion();
        assertTrue(Ej01_PrimeraConexion.estaAbierta(conn),
                "estaAbierta() debe devolver true con conexión abierta");
        conn.close();
    }

    @Test
    @Order(5)
    @DisplayName("estaAbierta() devuelve false cuando la conexión está cerrada")
    void estaAbiertaDevuelveFalseTrasCerrar() throws SQLException {
        Connection conn = Ej01_PrimeraConexion.abrirConexion();
        conn.close();
        assertFalse(Ej01_PrimeraConexion.estaAbierta(conn),
                "estaAbierta() debe devolver false con conexión cerrada");
    }
}
