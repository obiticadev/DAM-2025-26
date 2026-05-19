package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej181StructuredLoggingMdcTest {

    @Test
    void serializaConMdc() {
        String json = Ej181StructuredLoggingMdc.formatear("info", "hola", Map.of("traceId", "abc"));
        assertTrue(json.contains("\"level\":\"INFO\""), json);
        assertTrue(json.contains("\"message\":\"hola\""), json);
        assertTrue(json.contains("\"traceId\":\"abc\""), json);
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej181StructuredLoggingMdc.formatear(" ", "m", Map.of()));
        assertThrows(IllegalArgumentException.class,
                () -> Ej181StructuredLoggingMdc.formatear("INFO", null, Map.of()));
        assertThrows(IllegalArgumentException.class,
                () -> Ej181StructuredLoggingMdc.formatear("INFO", "m", null));
    }
}
