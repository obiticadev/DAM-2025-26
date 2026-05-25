package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b09_err.Ej080ValidationErrorPayload.FieldError;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej080ValidationErrorPayloadTest {

    @Test
    void agrupaPorCampo() {
        Map<String, List<String>> m = Ej080ValidationErrorPayload.agrupar(List.of(
                new FieldError("email", "obligatorio"),
                new FieldError("email", "formato"),
                new FieldError("edad", "min 18")));
        assertEquals(List.of("obligatorio", "formato"), m.get("email"));
        assertEquals(List.of("min 18"), m.get("edad"));
        assertEquals(2, m.size());
    }

    @Test
    void listaVacia() {
        assertTrue(Ej080ValidationErrorPayload.agrupar(List.of()).isEmpty());
    }

    @Test
    void testRetoExtra01_esNombreCampoValido() {
        // Valida nomenclatura estandar en formato camelCase.
        assertTrue(Ej080ValidationErrorPayload.esNombreCampoValido("nombreUsuario"));
    }

    @Test
    void testRetoExtra02_formatearMensajeValidacion() {
        // Construye la representacion leible de una violacion.
        assertEquals("El campo 'edad' es obligatorio", Ej080ValidationErrorPayload.formatearMensajeValidacion("edad", "es obligatorio"));
    }

    @Test
    void testRetoExtra03_esErrorGlobal() {
        // Determina si el error es global del objeto y no de un campo concreto.
        assertTrue(Ej080ValidationErrorPayload.esErrorGlobal(""));
    }

    @Test
    void testRetoExtra04_crearParClaveValor() {
        // Genera un String formateado del campo y su valor.
        assertEquals("k:v", Ej080ValidationErrorPayload.crearParClaveValor("k", "v"));
    }

    @Test
    void testRetoExtra05_tieneCaracteresEspeciales() {
        // Busca signos extraños no permitidos en nombres de campos.
        assertTrue(Ej080ValidationErrorPayload.tieneCaracteresEspeciales("user_id!"));
    }

    @Test
    void testRetoExtra06_esLargoValido() {
        // Comprueba limites inclusivos de longitud.
        assertTrue(Ej080ValidationErrorPayload.esLargoValido("abc", 2, 5));
    }

    @Test
    void testRetoExtra07_esNumeroRangoValido() {
        // Comprueba limites inclusivos para campos numericos.
        assertTrue(Ej080ValidationErrorPayload.esNumeroRangoValido(5.0, 1.0, 10.0));
    }

    @Test
    void testRetoExtra08_extraerUltimoSegmentoCampo() {
        // Extrae la propiedad hoja de una ruta anidada.
        assertEquals("nombre", Ej080ValidationErrorPayload.extraerUltimoSegmentoCampo("cliente.perfil.nombre"));
    }

    @Test
    void testRetoExtra09_contieneErroresInvalidos() {
        // Verifica si la coleccion tiene strings vacios o nulos.
        assertTrue(Ej080ValidationErrorPayload.contieneErroresInvalidos(java.util.List.of("")));
    }

    @Test
    void testRetoExtra10_esErrorDuplicidad() {
        // Determina si el error reportado indica valores ya en uso.
        assertTrue(Ej080ValidationErrorPayload.esErrorDuplicidad("el correo ya existe"));
    }

}