package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej06_VerificarEstado;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej06Test {

    @AfterEach
    void limpiar() {
        Ej06_VerificarEstado.cerrar();
    }

    @Test
    @Order(1)
    @DisplayName("getConexionSegura() devuelve conexión no nula")
    void getConexionSeguraNoNull() {
        assertNotNull(Ej06_VerificarEstado.getConexionSegura());
    }

    @Test
    @Order(2)
    @DisplayName("getConexionSegura() devuelve misma instancia en llamadas consecutivas")
    void getConexionSeguraMismaInstancia() {
        Connection c1 = Ej06_VerificarEstado.getConexionSegura();
        Connection c2 = Ej06_VerificarEstado.getConexionSegura();
        assertSame(c1, c2, "El guard debe reutilizar la misma instancia si está abierta");
    }

    @Test
    @Order(3)
    @DisplayName("getConexionSegura() crea nueva conexión tras cerrar")
    void getConexionSeguraCreaTrasCerrar() throws SQLException {
        Connection c1 = Ej06_VerificarEstado.getConexionSegura();
        Ej06_VerificarEstado.cerrar();

        Connection c2 = Ej06_VerificarEstado.getConexionSegura();
        assertFalse(c2.isClosed(), "La nueva conexión debe estar abierta");
        assertNotSame(c1, c2, "Debe ser un objeto distinto al anterior");
    }

    @Test
    @Order(4)
    @DisplayName("cerrar() no lanza excepción")
    void cerrarNoLanza() {
        Ej06_VerificarEstado.getConexionSegura();
        assertDoesNotThrow(() -> Ej06_VerificarEstado.cerrar());
    }

    @Test
    @Order(5)
    @DisplayName("diagnosticarEstado() no lanza excepción en ningún estado")
    void diagnosticarEstadoNoLanza() {
        assertDoesNotThrow(() -> {
            Ej06_VerificarEstado.diagnosticarEstado(); // sin conexión
            Ej06_VerificarEstado.getConexionSegura();
            Ej06_VerificarEstado.diagnosticarEstado(); // abierta
            Ej06_VerificarEstado.cerrar();
            Ej06_VerificarEstado.diagnosticarEstado(); // cerrada
        });
    }
}
