package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej049ResponseEntityTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej049ResponseEntity()).build();

    @Test
    void teapotConHeaderYBody() throws Exception {
        mvc.perform(get("/api/teapot"))
                .andExpect(status().is(418))
                .andExpect(header().string("X-Powered-By", "masterclass"))
                .andExpect(content().string("no coffee"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_respuestaVaciaConStatus() throws Exception {
        mvc.perform(get("/api/no-content"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_respuestaConCache() throws Exception {
        mvc.perform(get("/api/cached"))
                .andExpect(status().isOk())
                .andExpect(header().string("Cache-Control", "max-age=3600"))
                .andExpect(content().string("cacheado"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_respuestaDescarga() throws Exception {
        mvc.perform(get("/api/descargar"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"datos.csv\""))
                .andExpect(content().contentTypeCompatibleWith("text/csv"))
                .andExpect(content().string("id,nombre\n1,cafe\n"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_respuestaCondicional() throws Exception {
        mvc.perform(get("/api/recurso-etag").header("If-None-Match", "\"v1\""))
                .andExpect(status().isNotModified());

        mvc.perform(get("/api/recurso-etag").header("If-None-Match", "\"v2\""))
                .andExpect(status().isOk())
                .andExpect(header().string("ETag", "\"v1\""))
                .andExpect(content().string("contenido fresco"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_respuestaConCookie() throws Exception {
        mvc.perform(get("/api/cookie"))
                .andExpect(status().isOk())
                .andExpect(header().string("Set-Cookie", org.hamcrest.Matchers.containsString("session-id=xyz123")))
                .andExpect(content().string("cookie configurada"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_respuestaErrorDetallado() throws Exception {
        mvc.perform(get("/api/error-detalle").param("code", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value(101))
                .andExpect(jsonPath("$.detalle").value("Parametro invalido"));

        mvc.perform(get("/api/error-detalle").param("code", "2"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.codigo").value(102))
                .andExpect(jsonPath("$.detalle").value("Acceso prohibido"));

        mvc.perform(get("/api/error-detalle"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_respuestaRedireccion() throws Exception {
        mvc.perform(get("/api/redirect"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/api/hello"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_respuestaCreadoSinUri() throws Exception {
        mvc.perform(get("/api/creado-vacio"))
                .andExpect(status().isCreated())
                .andExpect(header().doesNotExist("Location"))
                .andExpect(jsonPath("$").isEmpty());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_respuestaConCustomStatusAndBody() throws Exception {
        mvc.perform(get("/api/custom"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.error").value("entidad no procesable"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_respuestaHtml() throws Exception {
        mvc.perform(get("/api/html"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(org.springframework.http.MediaType.TEXT_HTML))
                .andExpect(content().string("<h1>Hola</h1>"));
    }
}
