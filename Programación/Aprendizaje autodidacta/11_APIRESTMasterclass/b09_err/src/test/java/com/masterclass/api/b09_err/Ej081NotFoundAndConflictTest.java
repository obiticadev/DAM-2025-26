package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej081NotFoundAndConflictTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej081NotFoundAndConflict()).build();

    @Test
    void flujoCrearDuplicarLeer() throws Exception {
        mvc.perform(post("/api/users/ana")).andExpect(status().isCreated());
        mvc.perform(post("/api/users/ana")).andExpect(status().isConflict());
        mvc.perform(get("/api/users/ana")).andExpect(status().isOk());
        mvc.perform(get("/api/users/zoe")).andExpect(status().isNotFound());
    }

    @Test
    void testRetoExtra01_esRecursoAsociado() {
        // Verifica correspondencia de rutas de recursos secundarios.
        assertTrue(Ej081NotFoundAndConflict.esRecursoAsociado("/users/1/posts/3", 1L));
    }

    @Test
    void testRetoExtra02_esMensajeConflictoIdentificadores() {
        // Determina si el error apunta a choque de claves primarias.
        assertTrue(Ej081NotFoundAndConflict.esMensajeConflictoIdentificadores("id ya existe"));
    }

    @Test
    void testRetoExtra03_crearClaveDeRecurso() {
        // Genera una representacion clave-valor canonica.
        assertEquals("TIPO:123", Ej081NotFoundAndConflict.crearClaveDeRecurso("TIPO", 123L));
    }

    @Test
    void testRetoExtra04_esRutaControllerValida() {
        // Valida que el path comience con prefijo /api.
        assertTrue(Ej081NotFoundAndConflict.esRutaControllerValida("/api/v1/test"));
    }

    @Test
    void testRetoExtra05_extraerRecursoDeRuta() {
        // Determina el nombre del recurso a partir de la URL.
        assertEquals("users", Ej081NotFoundAndConflict.extraerRecursoDeRuta("/api/users/5"));
    }

    @Test
    void testRetoExtra06_esExcepcionDePersistenciaDirecta() {
        // Indica si la excepcion proviene del stack de hibernate direct.
        assertTrue(Ej081NotFoundAndConflict.esExcepcionDePersistenciaDirecta(new jakarta.persistence.EntityNotFoundException()));
    }

    @Test
    void testRetoExtra07_crearJsonConflicto() {
        // Genera un payload compacto de colision.
        assertTrue(Ej081NotFoundAndConflict.crearJsonConflicto("err", "5").contains("err"));
    }

    @Test
    void testRetoExtra08_esErrorDeVersionJpa() {
        // Determina si se detecta colision por concurrencia optimista.
        assertTrue(Ej081NotFoundAndConflict.esErrorDeVersionJpa(new jakarta.persistence.OptimisticLockException()));
    }

    @Test
    void testRetoExtra09_esConflictoLogico() {
        // Evalua si la operacion no es valida en el estado actual.
        assertTrue(Ej081NotFoundAndConflict.esConflictoLogico("CERRADO", "EDITAR"));
    }

    @Test
    void testRetoExtra10_construirMensajeNotFound() {
        // Crea la explicacion estandar de no hallado.
        assertEquals("recurso 'user' no hallado con clave '5'", Ej081NotFoundAndConflict.construirMensajeNotFound("user", "5"));
    }

}