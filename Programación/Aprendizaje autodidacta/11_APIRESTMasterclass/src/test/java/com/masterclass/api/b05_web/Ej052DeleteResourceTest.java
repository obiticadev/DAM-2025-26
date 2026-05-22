package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej052DeleteResourceTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej052DeleteResource()).build();

    @Test
    void borraExistente204() throws Exception {
        mvc.perform(delete("/api/items/5")).andExpect(status().isNoContent());
    }

    @Test
    void inexistente404() throws Exception {
        mvc.perform(delete("/api/items/0")).andExpect(status().isNotFound());
    }

    @Test
    void borrarFisico() throws Exception {
        mvc.perform(delete("/api/items/5/fisico"))
                .andExpect(status().isNoContent());
    }

    @Test
    void borrarLogico() throws Exception {
        mvc.perform(delete("/api/items/5/logico"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.activo").value(false));
    }

    @Test
    void borrarConFiltro() throws Exception {
        mvc.perform(delete("/api/items")
                        .param("categoria", "libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoria").value("libros"))
                .andExpect(jsonPath("$.borrados").value(5));
    }

    @Test
    void borrarConProteccionAdmin_noAutorizado() throws Exception {
        mvc.perform(delete("/api/items/5/protegido")
                        .header("Authorization", "wrong-token"))
                .andExpect(status().isForbidden());
    }

    @Test
    void borrarConProteccionAdmin_autorizado() throws Exception {
        mvc.perform(delete("/api/items/5/protegido")
                        .header("Authorization", "admin-token"))
                .andExpect(status().isNoContent());
    }

    @Test
    void borrarConDependencias_conflict() throws Exception {
        mvc.perform(delete("/api/items/5/dependencias")
                        .param("cascade", "false"))
                .andExpect(status().isConflict());
    }

    @Test
    void borrarConDependencias_cascade() throws Exception {
        mvc.perform(delete("/api/items/5/dependencias")
                        .param("cascade", "true"))
                .andExpect(status().isNoContent());
    }

    @Test
    void borrarRetornandoBackup() throws Exception {
        mvc.perform(delete("/api/items/5/backup"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    void borrarSeguro_locked() throws Exception {
        mvc.perform(delete("/api/items/99/seguro"))
                .andExpect(status().is(423));
    }

    @Test
    void borrarSeguro_exito() throws Exception {
        mvc.perform(delete("/api/items/5/seguro"))
                .andExpect(status().isNoContent());
    }

    @Test
    void borrarConPrecondicionFecha_fallo() throws Exception {
        mvc.perform(delete("/api/items/5/precondicion-fecha")
                        .header("X-Delete-Allowed-On", "AYER"))
                .andExpect(status().is(412));
    }

    @Test
    void borrarConPrecondicionFecha_exito() throws Exception {
        mvc.perform(delete("/api/items/5/precondicion-fecha")
                        .header("X-Delete-Allowed-On", "HOY"))
                .andExpect(status().isNoContent());
    }

    @Test
    void borrarMultiples() throws Exception {
        mvc.perform(delete("/api/items/batch")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content("[1,2,3]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.borrados[0]").value(1));
    }

    @Test
    void borrarVerificarCabeceraNoModified_primero() throws Exception {
        mvc.perform(delete("/api/items/5/idempotencia"))
                .andExpect(status().isNoContent())
                .andExpect(header().string("ETag", "\"borrado\""));
    }

    @Test
    void borrarVerificarCabeceraNoModified_segundo() throws Exception {
        mvc.perform(delete("/api/items/5/idempotencia")
                        .header("If-None-Match", "\"borrado\""))
                .andExpect(status().isNotFound());
    }
}
