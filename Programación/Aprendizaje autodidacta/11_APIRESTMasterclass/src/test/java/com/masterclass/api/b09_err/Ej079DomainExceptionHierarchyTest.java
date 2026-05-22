package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b09_err.Ej079DomainExceptionHierarchy.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej079DomainExceptionHierarchyTest {

    @Test
    void mapeo() {
        assertEquals(404, Ej079DomainExceptionHierarchy.aStatus(new NoEncontradoException("x")));
        assertEquals(409, Ej079DomainExceptionHierarchy.aStatus(new ConflictoException("x")));
        assertEquals(403, Ej079DomainExceptionHierarchy.aStatus(new NoAutorizadoException("x")));
        assertEquals(400, Ej079DomainExceptionHierarchy.aStatus(new DominioException("x")));
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej079DomainExceptionHierarchy.aStatus(null));
    }

    @Test
    void testRetoExtra01_esConflictoDatos() {
        // Verifica si es una excepcion por conflicto en el estado actual.
        assertTrue(Ej079DomainExceptionHierarchy.esConflictoDatos(new RuntimeException("conflict")));
    }

    @Test
    void testRetoExtra02_obtenerTipoMimeError() {
        // Resuelve si el cliente acepta el JSON estructurado de error.
        assertEquals("application/problem+json", Ej079DomainExceptionHierarchy.obtenerTipoMimeError("application/json"));
    }

    @Test
    void testRetoExtra03_esExcepcionDeSeguridad() {
        // Determina si la excepcion compromete accesos (credenciales, permisos).
        assertTrue(Ej079DomainExceptionHierarchy.esExcepcionDeSeguridad(new SecurityException()));
    }

    @Test
    void testRetoExtra04_crearRespuestaErrorInterno() {
        // Genera el objeto de contingencia de error de infraestructura.
        assertTrue(Ej079DomainExceptionHierarchy.crearRespuestaErrorInterno("db").contains("db"));
    }

    @Test
    void testRetoExtra05_esFalloNegocioCritico() {
        // Determina si detiene procesos transaccionales importantes.
        assertTrue(Ej079DomainExceptionHierarchy.esFalloNegocioCritico(new IllegalStateException("Stock vacio")));
    }

    @Test
    void testRetoExtra06_extraerMensajeCorto() {
        // Extrae las primeras palabras descriptivas del fallo.
        assertEquals("Fallo", Ej079DomainExceptionHierarchy.extraerMensajeCorto(new Exception("Fallo de conexion de datos")));
    }

    @Test
    void testRetoExtra07_esErrorValidacionEntidad() {
        // Verifica si corresponde a un fallo de validacion del modelo.
        assertTrue(Ej079DomainExceptionHierarchy.esErrorValidacionEntidad(new IllegalArgumentException("validar")));
    }

    @Test
    void testRetoExtra08_generarHashError() {
        // Genera una firma hash unica para indexar logs.
        assertNotNull(Ej079DomainExceptionHierarchy.generarHashError("T1", new Exception()));
    }

    @Test
    void testRetoExtra09_esErrorDeNegocioPuro() {
        // Comprueba si es una excepcion de dominio propia.
        assertTrue(Ej079DomainExceptionHierarchy.esErrorDeNegocioPuro(new IllegalArgumentException()));
    }

    @Test
    void testRetoExtra10_esExcepcionFatal() {
        // Filtra si es un error fatal de memoria o virtual machine.
        assertTrue(Ej079DomainExceptionHierarchy.esExcepcionFatal(new OutOfMemoryError()));
    }

}