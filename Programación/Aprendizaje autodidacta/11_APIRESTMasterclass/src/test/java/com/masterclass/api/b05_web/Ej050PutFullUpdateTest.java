package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej050PutFullUpdateTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej050PutFullUpdate()).build();

    @Test
    void reemplazaCompleto() throws Exception {
        mvc.perform(put("/api/items/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.nombre").value("nuevo"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void actualizarConOptimisticLock_exito() throws Exception {
        mvc.perform(put("/api/items/7/optimistic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":7,\"nombre\":\"test\",\"version\":5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(6));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void actualizarConOptimisticLock_fallo() throws Exception {
        mvc.perform(put("/api/items/7/optimistic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":7,\"nombre\":\"test\",\"version\":4}"))
                .andExpect(status().is(412));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void actualizarOcrear_crear() throws Exception {
        mvc.perform(put("/api/items/150/upsert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(150))
                .andExpect(jsonPath("$.nombre").value("nuevo"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void actualizarOcrear_actualizar() throws Exception {
        mvc.perform(put("/api/items/50/upsert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(50));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void actualizarConCabeceraConditional_faltaHeader() throws Exception {
        mvc.perform(put("/api/items/7/conditional")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"test\"}"))
                .andExpect(status().is(428));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void actualizarConCabeceraConditional_versionDiferente() throws Exception {
        mvc.perform(put("/api/items/7/conditional")
                        .header("If-Match", "\"v2\"")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"test\"}"))
                .andExpect(status().is(412));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void actualizarConCabeceraConditional_exito() throws Exception {
        mvc.perform(put("/api/items/7/conditional")
                        .header("If-Match", "\"v1\"")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("test"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void actualizarValidandoCampos_valido() throws Exception {
        mvc.perform(put("/api/items/7/valida-formato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Texto Valido\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void actualizarValidandoCampos_invalido() throws Exception {
        mvc.perform(put("/api/items/7/valida-formato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Invalido!!!\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void actualizarConHistorial() throws Exception {
        mvc.perform(put("/api/items/7/historial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void actualizarLanzaNoEncontrado_lanza() throws Exception {
        mvc.perform(put("/api/items/0/lanza-404")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"test\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void actualizarRetornandoSoloCabeceras() throws Exception {
        mvc.perform(put("/api/items/7/solo-cabeceras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"test\"}"))
                .andExpect(status().isNoContent())
                .andExpect(header().exists("X-Updated-At"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void actualizarSoloActivos_activo() throws Exception {
        mvc.perform(put("/api/items/7/estado-activo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"test\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void actualizarSoloActivos_inactivo() throws Exception {
        mvc.perform(put("/api/items/99/estado-activo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"test\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void actualizarBulk() throws Exception {
        mvc.perform(put("/api/items/bulk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":1,\"nombre\":\"a\"},{\"id\":2,\"nombre\":\"b\"}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("a"))
                .andExpect(jsonPath("$[1].nombre").value("b"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void actualizarValidandoIdCuerpo_invalido() throws Exception {
        mvc.perform(put("/api/items/7/verificar-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":8,\"nombre\":\"test\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void actualizarValidandoIdCuerpo_valido() throws Exception {
        mvc.perform(put("/api/items/7/verificar-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":7,\"nombre\":\"test\"}"))
                .andExpect(status().isOk());
    }
}
