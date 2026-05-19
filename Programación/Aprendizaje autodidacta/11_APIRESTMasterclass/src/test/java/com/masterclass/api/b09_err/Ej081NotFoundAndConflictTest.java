package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej081NotFoundAndConflictTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej081NotFoundAndConflict()).build();

    @Test
    void flujoCrearDuplicarLeer() throws Exception {
        mvc.perform(post("/api/users/ana")).andExpect(status().isCreated());
        mvc.perform(post("/api/users/ana")).andExpect(status().isConflict());
        mvc.perform(get("/api/users/ana")).andExpect(status().isOk());
        mvc.perform(get("/api/users/zoe")).andExpect(status().isNotFound());
    }
}
