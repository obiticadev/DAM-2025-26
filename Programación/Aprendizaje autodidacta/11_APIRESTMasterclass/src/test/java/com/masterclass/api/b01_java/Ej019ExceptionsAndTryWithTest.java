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

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void retoExtra01_cerrarRecursoSeguro() {
        var r = new Ej019ExceptionsAndTryWith.RecursoFragil(true);
        assertDoesNotThrow(() -> Ej019ExceptionsAndTryWith.cerrarRecursoSeguro(r));
        assertTrue(r.cerrado);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void retoExtra02_procesarConTryWithResources() throws Exception {
        var r = new Ej019ExceptionsAndTryWith.RecursoFragil(false);
        assertEquals("operacion-exitosa", Ej019ExceptionsAndTryWith.procesarConTryWithResources(r, false));
        assertTrue(r.cerrado);

        var rFalla = new Ej019ExceptionsAndTryWith.RecursoFragil(false);
        assertThrows(Ej019ExceptionsAndTryWith.ErrorNegocioException.class,
                () -> Ej019ExceptionsAndTryWith.procesarConTryWithResources(rFalla, true));
        assertTrue(rFalla.cerrado);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void retoExtra03_lanzarConCausaOriginal() {
        var causa = new IllegalArgumentException("argumento incorrecto");
        var ex = assertThrows(Ej019ExceptionsAndTryWith.ErrorSistemaException.class,
                () -> Ej019ExceptionsAndTryWith.lanzarConCausaOriginal("Fallo de sistema", causa));
        assertEquals("Fallo de sistema", ex.getMessage());
        assertEquals(causa, ex.getCause());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void retoExtra04_ejecutarAccionIgnorandoExcepcion() {
        assertDoesNotThrow(() -> Ej019ExceptionsAndTryWith.ejecutarAccionIgnorandoExcepcion(() -> {
            throw new RuntimeException("Ignórame");
        }));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void retoExtra05_detectarExcepcionSuprimida() {
        var r = new Ej019ExceptionsAndTryWith.RecursoFragil(true);
        String suprimidoMsg = Ej019ExceptionsAndTryWith.detectarExcepcionSuprimida(r);
        assertEquals("Fallo catastrófico al cerrar", suprimidoMsg);
        assertTrue(r.cerrado);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void retoExtra06_esExcepcionDeNegocio() {
        var ex = new Ej019ExceptionsAndTryWith.ErrorNegocioException("Negocio");
        var exWrapper = new RuntimeException("Sistema", ex);
        assertTrue(Ej019ExceptionsAndTryWith.esExcepcionDeNegocio(ex));
        assertTrue(Ej019ExceptionsAndTryWith.esExcepcionDeNegocio(exWrapper));
        assertFalse(Ej019ExceptionsAndTryWith.esExcepcionDeNegocio(new IllegalArgumentException()));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void retoExtra07_obtenerMensajeDeErrorFormateado() {
        var causaRaiz = new NullPointerException("Valor nulo inesperado");
        var ex = new RuntimeException("Error nivel 1", causaRaiz);
        assertEquals("Error: Valor nulo inesperado", Ej019ExceptionsAndTryWith.obtenerMensajeDeErrorFormateado(ex));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void retoExtra08_ejecutarConReintentos() {
        java.util.concurrent.atomic.AtomicInteger intentos = new java.util.concurrent.atomic.AtomicInteger(0);
        String resultado = Ej019ExceptionsAndTryWith.ejecutarConReintentos(() -> {
            if (intentos.incrementAndGet() < 3) {
                throw new java.io.IOException("Conexión perdida");
            }
            return "conectado";
        }, 3);
        assertEquals("conectado", resultado);
        assertEquals(3, intentos.get());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void retoExtra09_esCausaRaiz() {
        var causa = new NullPointerException("NPE");
        var ex = new RuntimeException("E1", causa);
        assertTrue(Ej019ExceptionsAndTryWith.esCausaRaiz(NullPointerException.class, ex));
        assertFalse(Ej019ExceptionsAndTryWith.esCausaRaiz(IllegalArgumentException.class, ex));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void retoExtra10_procesarRecursosMultiples() throws Exception {
        var r1 = new Ej019ExceptionsAndTryWith.RecursoFragil(false);
        var r2 = new Ej019ExceptionsAndTryWith.RecursoFragil(false);
        assertEquals("operacion-exitosa+operacion-exitosa", Ej019ExceptionsAndTryWith.procesarRecursosMultiples(r1, r2));
        assertTrue(r1.cerrado);
        assertTrue(r2.cerrado);
    }
}
