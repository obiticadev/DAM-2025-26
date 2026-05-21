package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b01_java.Ej018SealedPatternMatching.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej018SealedPatternMatchingTest {

    @Test
    void aHttpStatus() {
        assertEquals(200, Ej018SealedPatternMatching.aHttpStatus(new Ok("v")));
        assertEquals(404, Ej018SealedPatternMatching.aHttpStatus(new NotFound(7)));
        assertEquals(500, Ej018SealedPatternMatching.aHttpStatus(new Fallo("x")));
    }

    @Test
    void describir() {
        assertTrue(Ej018SealedPatternMatching.describir(new Ok("v")).toLowerCase().contains("v"));
        assertNotNull(Ej018SealedPatternMatching.describir(new Fallo("boom")));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void retoExtra01_esClaseSelladaPermitida() {
        assertTrue(Ej018SealedPatternMatching.esClaseSelladaPermitida(Forma.class, Circulo.class));
        assertTrue(Ej018SealedPatternMatching.esClaseSelladaPermitida(UsuarioRol.class, Admin.class));
        assertFalse(Ej018SealedPatternMatching.esClaseSelladaPermitida(Forma.class, String.class));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void retoExtra02_clasificarFormaGeometrica() {
        assertEquals("CÍRCULO", Ej018SealedPatternMatching.clasificarFormaGeometrica(new Circulo(5)));
        assertEquals("RECTÁNGULO", Ej018SealedPatternMatching.clasificarFormaGeometrica(new Rectangulo(4, 5)));
        assertEquals("TRIÁNGULO", Ej018SealedPatternMatching.clasificarFormaGeometrica(new Triangulo(3, 4)));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void retoExtra03_calcularAreaConPatternMatching() {
        assertEquals(Math.PI * 25.0, Ej018SealedPatternMatching.calcularAreaConPatternMatching(new Circulo(5)), 0.001);
        assertEquals(20.0, Ej018SealedPatternMatching.calcularAreaConPatternMatching(new Rectangulo(4, 5)), 0.001);
        assertEquals(6.0, Ej018SealedPatternMatching.calcularAreaConPatternMatching(new Triangulo(3, 4)), 0.001);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void retoExtra04_obtenerNombreDeUsuarioPorRol() {
        assertEquals("Ana", Ej018SealedPatternMatching.obtenerNombreDeUsuarioPorRol(new Admin("Ana", 4)));
        assertEquals("Carlos", Ej018SealedPatternMatching.obtenerNombreDeUsuarioPorRol(new Gestor("Carlos", "IT")));
        assertEquals("Maria", Ej018SealedPatternMatching.obtenerNombreDeUsuarioPorRol(new Cliente("Maria", true)));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void retoExtra05_esRolAdministrador() {
        assertTrue(Ej018SealedPatternMatching.esRolAdministrador(new Admin("Ana", 3)));
        assertTrue(Ej018SealedPatternMatching.esRolAdministrador(new Admin("Ana", 5)));
        assertFalse(Ej018SealedPatternMatching.esRolAdministrador(new Admin("Ana", 2)));
        assertFalse(Ej018SealedPatternMatching.esRolAdministrador(new Gestor("Carlos", "IT")));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void retoExtra06_esJerarquiaSelladaCompleta() {
        assertTrue(Ej018SealedPatternMatching.esJerarquiaSelladaCompleta(Forma.class));
        assertTrue(Ej018SealedPatternMatching.esJerarquiaSelladaCompleta(UsuarioRol.class));
        assertFalse(Ej018SealedPatternMatching.esJerarquiaSelladaCompleta(String.class));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void retoExtra07_formatearRespuestaApi() {
        assertEquals("Código 200: {\"ok\":true}", Ej018SealedPatternMatching.formatearRespuestaApi(new ExitoApi("{\"ok\":true}", 200)));
        assertEquals("Código 400: Error de entrada", Ej018SealedPatternMatching.formatearRespuestaApi(new ErrorApi("Error de entrada", 400, null)));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void retoExtra08_obtenerDetalleError() {
        assertTrue(Ej018SealedPatternMatching.obtenerDetalleError(new ErrorApi("Fallo", 500, null)).isPresent());
        assertEquals("Fallo", Ej018SealedPatternMatching.obtenerDetalleError(new ErrorApi("Fallo", 500, null)).get());
        assertTrue(Ej018SealedPatternMatching.obtenerDetalleError(new ExitoApi("{}", 200)).isEmpty());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void retoExtra09_evaluarEstadoConGuardias() {
        assertEquals("Círculo Grande", Ej018SealedPatternMatching.evaluarEstadoConGuardias(new Circulo(15)));
        assertEquals("Círculo Pequeño", Ej018SealedPatternMatching.evaluarEstadoConGuardias(new Circulo(5)));
        assertEquals("Cuadrado", Ej018SealedPatternMatching.evaluarEstadoConGuardias(new Rectangulo(4, 4)));
        assertEquals("Rectángulo Estándar", Ej018SealedPatternMatching.evaluarEstadoConGuardias(new Rectangulo(4, 5)));
        assertEquals("Triángulo", Ej018SealedPatternMatching.evaluarEstadoConGuardias(new Triangulo(3, 4)));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void retoExtra10_reconstruirFormaEscalada() {
        Forma f1 = Ej018SealedPatternMatching.reconstruirFormaEscalada(new Circulo(5), 2);
        assertTrue(f1 instanceof Circulo);
        assertEquals(10.0, ((Circulo) f1).radio(), 0.001);

        Forma f2 = Ej018SealedPatternMatching.reconstruirFormaEscalada(new Rectangulo(4, 5), 3);
        assertTrue(f2 instanceof Rectangulo);
        assertEquals(12.0, ((Rectangulo) f2).ancho(), 0.001);
        assertEquals(15.0, ((Rectangulo) f2).alto(), 0.001);
    }
}
