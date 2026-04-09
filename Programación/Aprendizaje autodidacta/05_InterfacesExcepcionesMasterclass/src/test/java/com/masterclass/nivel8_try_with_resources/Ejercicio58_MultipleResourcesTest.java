package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 58: Multiple Resources")
class Ejercicio58_MultipleResourcesTest {
    @Test @DisplayName("58.1 - copia y cierra ambos") void ok() {
        var log = new ArrayList<String>();
        var res = Ejercicio58_MultipleResources.copiar(log);
        assertThat(res).contains("LECTOR_ABIERTO","ESCRITOR_ABIERTO","ESCRITO:datos","ESCRITOR_CERRADO","LECTOR_CERRADO");
    }
}
