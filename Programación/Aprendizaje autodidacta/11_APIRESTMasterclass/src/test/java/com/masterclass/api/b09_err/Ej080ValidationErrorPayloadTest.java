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
}
