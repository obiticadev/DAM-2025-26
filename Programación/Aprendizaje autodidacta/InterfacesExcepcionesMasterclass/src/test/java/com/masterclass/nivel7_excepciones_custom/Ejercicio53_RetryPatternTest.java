package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 53: Retry Pattern")
class Ejercicio53_RetryPatternTest {
    @Test @DisplayName("53.1 - reintentar exito inmediato") void ok() { assertThat(Ejercicio53_RetryPattern.reintentar(() -> "OK", 3)).isEqualTo("OK"); }
    @Test @DisplayName("53.2 - reintentar exito al tercer intento") void retry() {
        var contador = new AtomicInteger(0);
        assertThat(Ejercicio53_RetryPattern.reintentar(() -> { if (contador.incrementAndGet() < 3) throw new RuntimeException("fail"); return "OK"; }, 3)).isEqualTo("OK");
    }
    @Test @DisplayName("53.3 - reintentar falla todos los intentos") void fail() { assertThatThrownBy(() -> Ejercicio53_RetryPattern.reintentar(() -> { throw new RuntimeException("fail"); }, 2)).isInstanceOf(RuntimeException.class); }
    @Test @DisplayName("53.4 - reintentarODefault devuelve default") void def() { assertThat(Ejercicio53_RetryPattern.reintentarODefault(() -> { throw new RuntimeException(); }, 2, "DEFAULT")).isEqualTo("DEFAULT"); }
}
