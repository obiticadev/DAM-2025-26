package com.masterclass.api.b24_boss;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class Ej199TaskTrackerCoreApiTest {

    @Test
    void testEsIdUuidValido() {
        assertTrue(Ej199TaskTrackerCoreApi.esIdUuidValido("d3b07384-d113-4956-bc7e-aa3000b21234"));
        assertFalse(Ej199TaskTrackerCoreApi.esIdUuidValido("not-a-uuid"));
        assertFalse(Ej199TaskTrackerCoreApi.esIdUuidValido(null));
    }

    @Test
    void testTieneOrphanRemovalHabilitado() {
        assertTrue(Ej199TaskTrackerCoreApi.tieneOrphanRemovalHabilitado("@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)"));
        assertTrue(Ej199TaskTrackerCoreApi.tieneOrphanRemovalHabilitado("orphanRemoval=true"));
        assertFalse(Ej199TaskTrackerCoreApi.tieneOrphanRemovalHabilitado("@OneToMany(cascade = CascadeType.ALL)"));
        assertFalse(Ej199TaskTrackerCoreApi.tieneOrphanRemovalHabilitado(null));
    }

    @Test
    void testEsConsultaOptimizadaEntityGraph() {
        assertTrue(Ej199TaskTrackerCoreApi.esConsultaOptimizadaEntityGraph("EntityGraph"));
        assertTrue(Ej199TaskTrackerCoreApi.esConsultaOptimizadaEntityGraph("org.springframework.data.jpa.repository.EntityGraph"));
        assertFalse(Ej199TaskTrackerCoreApi.esConsultaOptimizadaEntityGraph("Query"));
        assertFalse(Ej199TaskTrackerCoreApi.esConsultaOptimizadaEntityGraph(null));
    }

    @Test
    void testValidarPaginacion() {
        assertTrue(Ej199TaskTrackerCoreApi.validarPaginacion(0, 50));
        assertTrue(Ej199TaskTrackerCoreApi.validarPaginacion(5, 100));
        assertFalse(Ej199TaskTrackerCoreApi.validarPaginacion(-1, 20));
        assertFalse(Ej199TaskTrackerCoreApi.validarPaginacion(0, 101));
        assertFalse(Ej199TaskTrackerCoreApi.validarPaginacion(0, 0));
    }

    @Test
    void testEsBloqueoOptimistaActivo() {
        assertTrue(Ej199TaskTrackerCoreApi.esBloqueoOptimistaActivo(1L, 2L));
        assertFalse(Ej199TaskTrackerCoreApi.esBloqueoOptimistaActivo(3L, 3L));
        assertTrue(Ej199TaskTrackerCoreApi.esBloqueoOptimistaActivo(null, 1L));
    }

    @Test
    void testCrearProblemDetail() {
        String detail = Ej199TaskTrackerCoreApi.crearProblemDetail("Not Found", 404, "Task does not exist");
        assertTrue(detail.contains("\"title\":\"Not Found\""));
        assertTrue(detail.contains("\"status\":404"));
        assertTrue(detail.contains("\"detail\":\"Task does not exist\""));
        assertEquals("{}", Ej199TaskTrackerCoreApi.crearProblemDetail(null, 404, "Task"));
    }

    @Test
    void testMapearAProyectoDto() {
        String dto = Ej199TaskTrackerCoreApi.mapearAProyectoDto("uuid-123", "Task Tracker Project");
        assertEquals("ProyectoDTO[id=uuid-123, name=Task Tracker Project]", dto);
        assertEquals("null", Ej199TaskTrackerCoreApi.mapearAProyectoDto(null, "Task"));
    }

    @Test
    void testEsMetodoIdempotente() {
        assertTrue(Ej199TaskTrackerCoreApi.esMetodoIdempotente("GET"));
        assertTrue(Ej199TaskTrackerCoreApi.esMetodoIdempotente("PUT"));
        assertTrue(Ej199TaskTrackerCoreApi.esMetodoIdempotente("DELETE"));
        assertFalse(Ej199TaskTrackerCoreApi.esMetodoIdempotente("POST"));
        assertFalse(Ej199TaskTrackerCoreApi.esMetodoIdempotente("PATCH"));
        assertFalse(Ej199TaskTrackerCoreApi.esMetodoIdempotente(null));
    }

    @Test
    void testEsTareaValidaCrossField() {
        assertTrue(Ej199TaskTrackerCoreApi.esTareaValidaCrossField("PENDIENTE", null));
        assertTrue(Ej199TaskTrackerCoreApi.esTareaValidaCrossField("COMPLETADA", LocalDateTime.now()));
        assertFalse(Ej199TaskTrackerCoreApi.esTareaValidaCrossField("COMPLETADA", null));
        assertFalse(Ej199TaskTrackerCoreApi.esTareaValidaCrossField("COMPLETADA", LocalDateTime.now().plusDays(2)));
        assertFalse(Ej199TaskTrackerCoreApi.esTareaValidaCrossField(null, LocalDateTime.now()));
    }

    @Test
    void testEsTestE2EActivo() {
        assertTrue(Ej199TaskTrackerCoreApi.esTestE2EActivo("test", true));
        assertFalse(Ej199TaskTrackerCoreApi.esTestE2EActivo("prod", true));
        assertFalse(Ej199TaskTrackerCoreApi.esTestE2EActivo("test", false));
        assertFalse(Ej199TaskTrackerCoreApi.esTestE2EActivo(null, true));
    }
}
