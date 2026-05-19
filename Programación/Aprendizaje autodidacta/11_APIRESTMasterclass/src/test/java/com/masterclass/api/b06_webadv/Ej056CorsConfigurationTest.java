package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej056CorsConfigurationTest {

    @Test
    void origenPermitidoPorLista() {
        var h = Ej056CorsConfiguration.corsHeaders("https://app.com", List.of("https://app.com"));
        assertEquals("https://app.com", h.get("Access-Control-Allow-Origin"));
        assertTrue(h.containsKey("Access-Control-Allow-Methods"));
    }

    @Test
    void comodin() {
        var h = Ej056CorsConfiguration.corsHeaders("https://x.com", List.of("*"));
        assertEquals("*", h.get("Access-Control-Allow-Origin"));
    }

    @Test
    void origenNoPermitido() {
        assertTrue(Ej056CorsConfiguration.corsHeaders("https://evil.com",
                List.of("https://app.com")).isEmpty());
    }
}
