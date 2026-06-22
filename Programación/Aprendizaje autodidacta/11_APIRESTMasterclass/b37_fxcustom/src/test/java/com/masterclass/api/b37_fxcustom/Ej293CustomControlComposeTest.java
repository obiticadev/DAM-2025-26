package com.masterclass.api.b37_fxcustom;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej293CustomControlComposeTest {

    @Test
    void etiquetaMostrada() {
        assertEquals("Email *", Ej293CustomControlCompose.etiquetaMostrada("Email", true));
        assertEquals("Apodo", Ej293CustomControlCompose.etiquetaMostrada("Apodo", false));
        assertEquals(" *", Ej293CustomControlCompose.etiquetaMostrada("", true)); // caso límite
    }

    @Test
    void estadoCampo() {
        assertEquals("vacio", Ej293CustomControlCompose.estadoCampo("", 3));
        assertEquals("corto", Ej293CustomControlCompose.estadoCampo("ab", 3));
        assertEquals("valido", Ej293CustomControlCompose.estadoCampo("abc", 3)); // frontera == minimo
    }

    @Test
    void retoExtra01_etiquetaConDosPuntos() {
        assertEquals("Nombre:", Ej293CustomControlCompose.etiquetaConDosPuntos("Nombre"));
    }

    @Test
    void retoExtra02_placeholderVisible() {
        assertTrue(Ej293CustomControlCompose.placeholderVisible(""));
        assertFalse(Ej293CustomControlCompose.placeholderVisible("hola"));
    }

    @Test
    void retoExtra03_textoContador() {
        assertEquals("3/10", Ej293CustomControlCompose.textoContador("abc", 10));
        assertEquals("0/10", Ej293CustomControlCompose.textoContador(null, 10)); // caso límite
    }

    @Test
    void retoExtra04_recortarAlLimite() {
        assertEquals("abc", Ej293CustomControlCompose.recortarAlLimite("abcdef", 3));
        assertEquals("abc", Ej293CustomControlCompose.recortarAlLimite("abc", 3)); // frontera, no recorta
    }

    @Test
    void retoExtra05_claseSegunEstado() {
        assertEquals("campo-ok", Ej293CustomControlCompose.claseSegunEstado("valido"));
        assertEquals("campo-aviso", Ej293CustomControlCompose.claseSegunEstado("corto"));
        assertEquals("campo", Ej293CustomControlCompose.claseSegunEstado("???")); // default
    }

    @Test
    void retoExtra06_valorNormalizado() {
        assertEquals("ana@mail.com", Ej293CustomControlCompose.valorNormalizado("  Ana@MAIL.com "));
    }

    @Test
    void retoExtra07_mensajeError() {
        assertEquals("Mínimo 3 caracteres.", Ej293CustomControlCompose.mensajeError("corto", 3));
        assertEquals("", Ej293CustomControlCompose.mensajeError("valido", 3));
    }

    @Test
    void retoExtra08_idAccesible() {
        assertEquals("campo-correo-electronico", Ej293CustomControlCompose.idAccesible("Correo Electronico"));
    }

    @Test
    void retoExtra09_progresoRelleno() {
        assertEquals(50, Ej293CustomControlCompose.progresoRelleno(
                List.of("valido", "corto", "valido", "vacio")));
        assertEquals(0, Ej293CustomControlCompose.progresoRelleno(List.of())); // caso límite
    }

    @Test
    void retoExtra10_camposConError() {
        assertEquals(List.of("Email", "Edad"), Ej293CustomControlCompose.camposConError(
                List.of("Nombre", "Email", "Edad"),
                List.of("valido", "vacio", "corto")));
        assertEquals(List.of(), Ej293CustomControlCompose.camposConError(
                List.of("Nombre"), List.of("valido"))); // caso límite: todos válidos
    }
}
