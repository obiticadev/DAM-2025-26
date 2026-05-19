package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b01_java.Ej019ExceptionsAndTryWith.Conexion;
import com.masterclass.api.b01_java.Ej019ExceptionsAndTryWith.RecursoNoEncontradoException;
import static org.junit.jupiter.api.Assertions.*;

class Ej019ExceptionsAndTryWithTest {

    @Test
    void buscarOk() {
        assertEquals("recurso-5", Ej019ExceptionsAndTryWith.buscar(5));
    }

    @Test
    void buscarLanza() {
        var ex = assertThrows(RecursoNoEncontradoException.class,
                () -> Ej019ExceptionsAndTryWith.buscar(0));
        assertTrue(ex.getMessage().contains("0"));
    }

    @Test
    void cierreAutomatico() {
        var c = new Conexion();
        assertEquals("datos", Ej019ExceptionsAndTryWith.usarYCerrar(c));
        assertTrue(c.cerrada, "try-with-resources debe cerrar la conexión");
    }
}
