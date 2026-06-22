package com.masterclass.api.b36_fxstyle;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej291UsabilityFeedbackTest {

    @Test
    void mensajeError() {
        assertEquals("El campo 'email' no tiene un formato válido.",
                Ej291UsabilityFeedback.mensajeError("email", "formato"));
        assertEquals("El campo 'nombre' es obligatorio.",
                Ej291UsabilityFeedback.mensajeError("nombre", "requerido"));
        assertEquals("El campo 'x' no es válido.",
                Ej291UsabilityFeedback.mensajeError("x", "raro")); // caso límite: tipo desconocido
    }

    @Test
    void estadoBoton() {
        assertEquals("cargando", Ej291UsabilityFeedback.estadoBoton(true, true));   // cargando manda
        assertEquals("deshabilitado", Ej291UsabilityFeedback.estadoBoton(false, false));
        assertEquals("activo", Ej291UsabilityFeedback.estadoBoton(false, true));
    }

    @Test
    void retoExtra01_estaDeshabilitado() {
        assertTrue(Ej291UsabilityFeedback.estaDeshabilitado(true));
        assertFalse(Ej291UsabilityFeedback.estaDeshabilitado(false));
    }

    @Test
    void retoExtra02_textoCargando() {
        assertEquals("Cargando…", Ej291UsabilityFeedback.textoCargando("Guardar", true));
        assertEquals("Guardar", Ej291UsabilityFeedback.textoCargando("Guardar", false));
    }

    @Test
    void retoExtra03_tooltipDe() {
        assertEquals("Introduce un correo válido, p.ej. ana@mail.com", Ej291UsabilityFeedback.tooltipDe("email"));
        assertEquals("", Ej291UsabilityFeedback.tooltipDe("desconocido")); // caso límite
    }

    @Test
    void retoExtra04_plural() {
        assertEquals("1 elemento", Ej291UsabilityFeedback.plural(1));
        assertEquals("0 elementos", Ej291UsabilityFeedback.plural(0)); // el cero va en plural
    }

    @Test
    void retoExtra05_truncar() {
        assertEquals("Hola…", Ej291UsabilityFeedback.truncar("Hola mundo", 4));
        assertEquals("Hi", Ej291UsabilityFeedback.truncar("Hi", 4)); // cabe: no se toca
    }

    @Test
    void retoExtra06_claseFeedback() {
        assertEquals("campo-error", Ej291UsabilityFeedback.claseFeedback("error"));
        assertEquals("", Ej291UsabilityFeedback.claseFeedback("???")); // caso límite
    }

    @Test
    void retoExtra07_resumenErrores() {
        assertEquals("Hay 2 errores: a, b", Ej291UsabilityFeedback.resumenErrores(List.of("a", "b")));
        assertEquals("Todo correcto", Ej291UsabilityFeedback.resumenErrores(List.of())); // caso límite
    }

    @Test
    void retoExtra08_porcentaje() {
        assertEquals("50%", Ej291UsabilityFeedback.porcentaje(0.5));
        assertEquals("100%", Ej291UsabilityFeedback.porcentaje(1.0));
    }

    @Test
    void retoExtra09_puedeEnviar() {
        assertTrue(Ej291UsabilityFeedback.puedeEnviar(true, false, true));
        assertFalse(Ej291UsabilityFeedback.puedeEnviar(true, true, true)); // cargando bloquea
    }

    @Test
    void retoExtra10_mensajeAccionable() {
        assertEquals("No hay conexión. Revisa tu red e inténtalo de nuevo.",
                Ej291UsabilityFeedback.mensajeAccionable("conexion"));
        assertEquals("Ha ocurrido un error. Inténtalo más tarde.",
                Ej291UsabilityFeedback.mensajeAccionable("???")); // caso límite
    }
}
