package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej177OpenApiSwaggerTest {

    @Test
    @SuppressWarnings("unchecked")
    void documentaUnEndpoint() {
        Map<String, Object> doc = Ej177OpenApiSwagger.construirDocumento("Demo", "1.0",
                List.of(new EndpointMeta177("/users", "GET", "Lista usuarios")));
        assertEquals("3.0.1", doc.get("openapi"));
        Map<String, Object> info = (Map<String, Object>) doc.get("info");
        assertEquals("Demo", info.get("title"));
        Map<String, Object> paths = (Map<String, Object>) doc.get("paths");
        Map<String, Object> get = (Map<String, Object>) ((Map<String, Object>) paths.get("/users")).get("get");
        assertEquals("Lista usuarios", get.get("summary"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej177OpenApiSwagger.construirDocumento("Demo", "1.0", null));
        assertThrows(IllegalArgumentException.class,
                () -> Ej177OpenApiSwagger.construirDocumento(" ", "1.0", List.of()));
    }
}
