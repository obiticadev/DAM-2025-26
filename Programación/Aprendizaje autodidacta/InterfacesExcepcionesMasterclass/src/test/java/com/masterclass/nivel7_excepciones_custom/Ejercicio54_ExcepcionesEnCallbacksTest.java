package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 54: Excepciones en Callbacks")
class Ejercicio54_ExcepcionesEnCallbacksTest {
    @Test @DisplayName("54.1 - callback sin errores") void ok() {
        var res = new ArrayList<String>();
        Ejercicio54_ExcepcionesEnCallbacks.ejecutarConCallback(List.of("A","B"), res::add);
        assertThat(res).containsExactly("A","B");
    }
    @Test @DisplayName("54.2 - callback con error lanza CallbackException") void fail() {
        assertThatThrownBy(() -> Ejercicio54_ExcepcionesEnCallbacks.ejecutarConCallback(List.of("A",null), s -> { if (s==null) throw new RuntimeException("null!"); }))
            .isInstanceOf(Ejercicio54_ExcepcionesEnCallbacks.CallbackException.class);
    }
    @Test @DisplayName("54.3 - ignorar errores cuenta exitosos") void ignora() {
        int ok = Ejercicio54_ExcepcionesEnCallbacks.ejecutarIgnorandoErrores(List.of("A",null,"B"), s -> { if (s==null) throw new RuntimeException(); });
        assertThat(ok).isEqualTo(2);
    }
}
