package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej051PatchPartialUpdateTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej051PatchPartialUpdate()).build();

    @Test
    void soloCambiaNombre() throws Exception {
        mvc.perform(patch("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("nuevo"))
                .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void patchConDto() throws Exception {
        mvc.perform(patch("/api/items/1/dto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo-dto\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("nuevo-dto"));
    }

    @Test
    void patchColeccion_add() throws Exception {
        mvc.perform(patch("/api/items/1/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"addTag\":\"docker\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tags[2]").value("docker"));
    }

    @Test
    void patchColeccion_remove() throws Exception {
        mvc.perform(patch("/api/items/1/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"removeTag\":\"spring\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tags[0]").value("java"));
    }

    @Test
    void patchIncrementarContador() throws Exception {
        mvc.perform(patch("/api/items/1/incrementar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visitas").value(11));
    }

    @Test
    void patchConJsonPatch() throws Exception {
        mvc.perform(patch("/api/items/1/rfc6902")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"op\":\"replace\",\"path\":\"/nombre\",\"value\":\"cambiado\"}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("cambiado"));
    }

    @Test
    void patchValidarTipos_invalido() throws Exception {
        mvc.perform(patch("/api/items/1/valida-tipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activo\":\"no-booleano\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void patchValidarTipos_valido() throws Exception {
        mvc.perform(patch("/api/items/1/valida-tipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activo\":false}"))
                .andExpect(status().isOk());
    }

    @Test
    void patchNoPermitido_invalido() throws Exception {
        mvc.perform(patch("/api/items/1/readonly")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":2,\"nombre\":\"nuevo\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void patchNoPermitido_valido() throws Exception {
        mvc.perform(patch("/api/items/1/readonly")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void patchAuditoria() throws Exception {
        mvc.perform(patch("/api/items/1/auditoria")
                        .header("X-User", "obitica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modificadoPor").value("obitica"));
    }

    @Test
    void patchEstadoExclusivo_error() throws Exception {
        mvc.perform(patch("/api/items/1/estado-exclusivo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activo\":false,\"nombre\":\"nuevo\"}"))
                .andExpect(status().is(422));
    }

    @Test
    void patchEstadoExclusivo_valido() throws Exception {
        mvc.perform(patch("/api/items/1/estado-exclusivo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activo\":true,\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void patchLanzaConflicto_bloqueado() throws Exception {
        mvc.perform(patch("/api/items/99/bloqueado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void patchLanzaConflicto_libre() throws Exception {
        mvc.perform(patch("/api/items/1/bloqueado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void patchCondicionalEtag_faltaHeader() throws Exception {
        mvc.perform(patch("/api/items/1/conditional")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().is(428));
    }

    @Test
    void patchCondicionalEtag_wrongEtag() throws Exception {
        mvc.perform(patch("/api/items/1/conditional")
                        .header("If-Match", "\"v2\"")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().is(412));
    }

    @Test
    void patchCondicionalEtag_valido() throws Exception {
        mvc.perform(patch("/api/items/1/conditional")
                        .header("If-Match", "\"v1\"")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk());
    }
}
