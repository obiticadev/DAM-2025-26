package bloque3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej14 - Excepciones Personalizadas")
class Ej14_ExcepcionesTest {

    @Test @DisplayName("Constructor valido")
    void constructor_valido() {
        assertDoesNotThrow(() -> new Ej14_Excepciones(5, 5));
    }

    @Test @DisplayName("Constructor lanza DimensionException si filas <= 0")
    void constructor_filasNeg() {
        Ej14_DimensionException e = assertThrows(Ej14_DimensionException.class,
            () -> new Ej14_Excepciones(-1, 5));
        assertTrue(e.getMessage().contains("-1"));
    }

    @Test @DisplayName("Constructor lanza DimensionException si columnas <= 0")
    void constructor_colsNeg() {
        assertThrows(Ej14_DimensionException.class, () -> new Ej14_Excepciones(5, 0));
    }

    @Test @DisplayName("Constructor lanza DimensionException si filas > 20")
    void constructor_filasMax() {
        Ej14_DimensionException e = assertThrows(Ej14_DimensionException.class,
            () -> new Ej14_Excepciones(21, 5));
        assertTrue(e.getMessage().contains("21"));
    }

    @Test @DisplayName("Constructor lanza DimensionException si columnas > 20")
    void constructor_colsMax() {
        assertThrows(Ej14_DimensionException.class, () -> new Ej14_Excepciones(5, 21));
    }

    @Test @DisplayName("crearSeguro devuelve null si falla")
    void crearSeguro_null() {
        assertNull(Ej14_Excepciones.crearSeguro(0, 5));
        assertNull(Ej14_Excepciones.crearSeguro(5, -1));
    }

    @Test @DisplayName("crearSeguro devuelve instancia si OK")
    void crearSeguro_ok() {
        assertNotNull(Ej14_Excepciones.crearSeguro(3, 3));
    }

    @Test @DisplayName("setValorEstricto lanza excepcion si fuera de rango")
    void setEstricto_fueraRango() {
        Ej14_Excepciones c = new Ej14_Excepciones(3, 3);
        assertThrows(Ej14_DimensionException.class, () -> c.setValorEstricto(9, 0, 5));
    }

    @Test @DisplayName("setValorSuave devuelve false si fuera de rango")
    void setSuave_fueraRango() {
        Ej14_Excepciones c = new Ej14_Excepciones(3, 3);
        assertFalse(c.setValorSuave(9, 0, 5));
        assertTrue(c.setValorSuave(1, 1, 5));
    }

    @Test @DisplayName("DimensionException es IllegalArgumentException")
    void excepcionHerencia() {
        Ej14_DimensionException e = new Ej14_DimensionException("test");
        assertTrue(e instanceof IllegalArgumentException);
    }
}
