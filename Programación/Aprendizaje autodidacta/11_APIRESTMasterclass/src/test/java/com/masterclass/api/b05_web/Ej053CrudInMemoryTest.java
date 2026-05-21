package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej053CrudInMemoryTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej053CrudInMemory()).build();

    @Test
    void cicloDeVidaCompleto() throws Exception {
        mvc.perform(post("/api/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"comprar\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        mvc.perform(get("/api/tareas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("comprar"));

        mvc.perform(get("/api/tareas")).andExpect(jsonPath("$.length()").value(1));

        mvc.perform(delete("/api/tareas/1")).andExpect(status().isNoContent());
        mvc.perform(get("/api/tareas/1")).andExpect(status().isNotFound());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void actualizarTarea_inexistente() throws Exception {
        mvc.perform(put("/api/tareas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"actualizar\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void parchearTarea_inexistente() throws Exception {
        mvc.perform(patch("/api/tareas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"parcheado\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void buscarPorTitulo() throws Exception {
        mvc.perform(get("/api/tareas/filtrar")
                        .param("q", "estudiar"))
                .andExpect(status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void limpiarTodo() throws Exception {
        mvc.perform(delete("/api/tareas"))
                .andExpect(status().isNoContent());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void contarTareas() throws Exception {
        mvc.perform(get("/api/tareas/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(0));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void crearVarias() throws Exception {
        mvc.perform(post("/api/tareas/bulk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"titulo\":\"tarea A\"},{\"titulo\":\"tarea B\"}]"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void duplicarTarea_inexistente() throws Exception {
        mvc.perform(post("/api/tareas/1/duplicar"))
                .andExpect(status().isNotFound());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void toggleCompletada_inexistente() throws Exception {
        mvc.perform(patch("/api/tareas/1/toggle"))
                .andExpect(status().isNotFound());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void listarPaginado() throws Exception {
        mvc.perform(get("/api/tareas/paginadas")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void obtenerPrimera_vacio() throws Exception {
        mvc.perform(get("/api/tareas/primera"))
                .andExpect(status().isNoContent());
    }
}
