package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej05_CerrarConexion;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej05Test {

    @AfterEach
    void resetEstado() throws SQLException {
        // Asegura estado limpio entre tests
        Connection c = Ej05_CerrarConexion.reiniciarConexion();
    }

    @Test
    @Order(1)
    @DisplayName("estaDisponible() es false cuando no hay conexión abierta")
    void estaDisponibleFalseSinConexion() {
        Ej05_CerrarConexion.cerrarSiAbierta();
        assertFalse(Ej05_CerrarConexion.estaDisponible(),
                "estaDisponible() debe ser false sin conexión activa");
    }

    @Test
    @Order(2)
    @DisplayName("estaDisponible() es true tras abrir conexión")
    void estaDisponibleTrasConectar() throws SQLException {
        Ej05_CerrarConexion.getConexion();
        assertTrue(Ej05_CerrarConexion.estaDisponible(),
                "estaDisponible() debe ser true con conexión abierta");
    }

    @Test
    @Order(3)
    @DisplayName("cerrarSiAbierta() cierra la conexión")
    void cerrarSiAbiertaFunciona() throws SQLException {
        Ej05_CerrarConexion.getConexion();
        Ej05_CerrarConexion.cerrarSiAbierta();
        assertFalse(Ej05_CerrarConexion.estaDisponible(),
                "Tras cerrarSiAbierta(), estaDisponible() debe ser false");
    }

    @Test
    @Order(4)
    @DisplayName("cerrarSiAbierta() no lanza excepción si ya está cerrada")
    void cerrarDobleNoExplota() {
        Ej05_CerrarConexion.cerrarSiAbierta();
        assertDoesNotThrow(() -> Ej05_CerrarConexion.cerrarSiAbierta(),
                "Cerrar dos veces no debe lanzar excepción");
    }

    @Test
    @Order(5)
    @DisplayName("reiniciarConexion() devuelve una conexión abierta")
    void reiniciarConexionDevuelveAbierta() throws SQLException {
        Connection nueva = Ej05_CerrarConexion.reiniciarConexion();
        assertNotNull(nueva);
        assertFalse(nueva.isClosed(), "reiniciarConexion() debe devolver conexión abierta");
    }
}
