package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej02_SingletonConexion;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej02Test {

    @AfterEach
    void resetSingleton() {
        Ej02_SingletonConexion.cerrarConexion();
    }

    @Test
    @Order(1)
    @DisplayName("getConexion() no devuelve null")
    void getConexionNoEsNull() {
        assertNotNull(Ej02_SingletonConexion.getConexion());
    }

    @Test
    @Order(2)
    @DisplayName("getConexion() devuelve siempre el mismo objeto (Singleton)")
    void getConexionEsMismaInstancia() {
        Connection c1 = Ej02_SingletonConexion.getConexion();
        Connection c2 = Ej02_SingletonConexion.getConexion();
        assertSame(c1, c2, "El Singleton debe devolver siempre el mismo objeto Connection");
    }

    @Test
    @Order(3)
    @DisplayName("La conexión está abierta tras getConexion()")
    void conexionEstaAbierta() throws SQLException {
        Connection conn = Ej02_SingletonConexion.getConexion();
        assertFalse(conn.isClosed(), "La conexión debe estar abierta");
    }

    @Test
    @Order(4)
    @DisplayName("cerrarConexion() cierra la conexión")
    void cerrarConexionFunciona() throws SQLException {
        Connection conn = Ej02_SingletonConexion.getConexion();
        Ej02_SingletonConexion.cerrarConexion();
        assertTrue(conn.isClosed(), "La conexión debe estar cerrada tras cerrarConexion()");
    }

    @Test
    @Order(5)
    @DisplayName("getConexion() crea una nueva conexión si la anterior fue cerrada")
    void getConexionRecreaDespuesDeCerrar() throws SQLException {
        Connection c1 = Ej02_SingletonConexion.getConexion();
        Ej02_SingletonConexion.cerrarConexion();

        Connection c2 = Ej02_SingletonConexion.getConexion();
        assertFalse(c2.isClosed(), "La nueva conexión debe estar abierta");
        assertNotSame(c1, c2, "Debe ser un objeto Connection distinto al anterior");
    }
}
