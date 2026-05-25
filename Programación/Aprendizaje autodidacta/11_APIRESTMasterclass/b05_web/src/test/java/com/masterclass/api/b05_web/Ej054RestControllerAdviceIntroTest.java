package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej054RestControllerAdviceIntroTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej054RestControllerAdviceIntro()).build();

    private final MockMvc mvcConAdvice = MockMvcBuilders.standaloneSetup(new Ej054RestControllerAdviceIntro())
            .setControllerAdvice(new Ej054RestControllerAdviceIntro.GlobalExceptionHandler())
            .build();

    @Test
    void divisionOk() throws Exception {
        mvc.perform(get("/api/div").param("a", "10").param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void divisionPorCero400() throws Exception {
        mvc.perform(get("/api/div").param("a", "10").param("b", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("division por cero"));
    }

    @Test
    void globalAdviceAnnotation() {
        Class<?> clazz = Ej054RestControllerAdviceIntro.GlobalExceptionHandler.class;
        org.junit.jupiter.api.Assertions.assertTrue(clazz.isAnnotationPresent(org.springframework.web.bind.annotation.RestControllerAdvice.class));
    }

    @Test
    void manejarFormatoInvalido() throws Exception {
        mvcConAdvice.perform(get("/api/div").param("a", "no-es-un-numero").param("b", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Parametro invalido: a")));
    }

    @Test
    void manejarMissingParam() throws Exception {
        mvcConAdvice.perform(get("/api/div").param("b", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(org.hamcrest.Matchers.containsString("Parametro ausente: a")));
    }

    @Test
    void manejarEntidadExistente() throws Exception {
        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "conflicto"))
                .andExpect(status().isConflict());
    }

    @Test
    void manejarRuntimeExceptionGenerica() throws Exception {
        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "runtime"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.mensaje").exists())
                .andExpect(jsonPath("$.ruta").value("/api/simular-error"));
    }

    @Test
    void manejarHttpRequestMethodNotSupported() throws Exception {
        mvcConAdvice.perform(post("/api/div").param("a", "10").param("b", "2"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().string("Metodo no soportado"));
    }

    @Test
    void manejarAccesoDenegado() throws Exception {
        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "denegado"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Acceso prohibido:")));
    }

    @Test
    void manejarHttpMediaTypeNotSupported() {
        Ej054RestControllerAdviceIntro.GlobalExceptionHandler handler = new Ej054RestControllerAdviceIntro.GlobalExceptionHandler();
        org.springframework.http.ResponseEntity<String> response = handler.manejarHttpMediaTypeNotSupported(
                new org.springframework.web.HttpMediaTypeNotSupportedException("Unsupported")
        );
        org.junit.jupiter.api.Assertions.assertEquals(org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
        org.junit.jupiter.api.Assertions.assertEquals("Formato no soportado", response.getBody());
    }

    @Test
    void manejarServicioExterno() throws Exception {
        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "externo"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(header().string("Retry-After", "30"))
                .andExpect(content().string("Servicio temporalmente no disponible"));
    }

    @Test
    void simularError_excepcionesDiferentes() throws Exception {
        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "conflicto"))
                .andExpect(status().isConflict());

        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "denegado"))
                .andExpect(status().isForbidden());

        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "externo"))
                .andExpect(status().isServiceUnavailable());

        mvcConAdvice.perform(get("/api/simular-error").param("tipo", "runtime"))
                .andExpect(status().isInternalServerError());
    }
}

