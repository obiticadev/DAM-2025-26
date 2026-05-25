package com.masterclass.api.b20_obs;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej179ActuatorEndpointsTest {

    @Test
    void todosUp() {
        assertEquals("UP", Ej179ActuatorEndpoints.estadoAgregado(Map.of("db", "UP", "disk", "up ")));
    }

    @Test
    void unoDown() {
        assertEquals("DOWN", Ej179ActuatorEndpoints.estadoAgregado(Map.of("db", "UP", "disk", "DOWN")));
    }

    @Test
    void vacioUnknown() {
        assertEquals("UNKNOWN", Ej179ActuatorEndpoints.estadoAgregado(Map.of()));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class, () -> Ej179ActuatorEndpoints.estadoAgregado(null));
        Map<String, String> conNull = new HashMap<>();
        conNull.put("db", null);
        assertThrows(IllegalArgumentException.class, () -> Ej179ActuatorEndpoints.estadoAgregado(conNull));
    }

    @Test
    void testRetoExtra01_esEstadoUp() {
        // Valida si es UP.
        assertTrue(Ej179ActuatorEndpoints.esEstadoUp("UP"));
    }

    @Test
    void testRetoExtra02_esEstadoDown() {
        // Valida si es DOWN.
        assertTrue(Ej179ActuatorEndpoints.esEstadoDown("DOWN"));
    }

    @Test
    void testRetoExtra03_cantidadComponentes() {
        // Total componentes.
        assertEquals(2, Ej179ActuatorEndpoints.cantidadComponentes(java.util.Map.of("db", "UP", "ping", "UP")));
    }

    @Test
    void testRetoExtra04_contieneComponente() {
        // Busca existencia.
        assertTrue(Ej179ActuatorEndpoints.contieneComponente(java.util.Map.of("db", "UP"), "db"));
    }

    @Test
    void testRetoExtra05_obtenerEstadoComponente() {
        // Obtiene estado.
        assertEquals("UP", Ej179ActuatorEndpoints.obtenerEstadoComponente(java.util.Map.of("db", "UP"), "db"));
    }

    @Test
    void testRetoExtra06_inicializarAgregador() {
        // Crea un mapa limpio.
        assertNotNull(Ej179ActuatorEndpoints.inicializarAgregador());
    }

    @Test
    void testRetoExtra07_agregarComponente() {
        // Agrega componente.
        assertEquals(1, Ej179ActuatorEndpoints.agregarComponente(new java.util.HashMap<>(), "db", "UP").size());
    }

    @Test
    void testRetoExtra08_eliminarComponente() {
        // Elimina componente.
        assertEquals(0, Ej179ActuatorEndpoints.eliminarComponente(new java.util.HashMap<>(java.util.Map.of("db", "UP")), "db").size());
    }

    @Test
    void testRetoExtra09_todosConEstado() {
        // Verifica homogeneidad.
        assertTrue(Ej179ActuatorEndpoints.todosConEstado(java.util.Map.of("db", "UP", "ping", "UP"), "UP"));
    }

    @Test
    void testRetoExtra10_algunoConEstado() {
        // Verifica si hay algun caso.
        assertTrue(Ej179ActuatorEndpoints.algunoConEstado(java.util.Map.of("db", "DOWN", "ping", "UP"), "DOWN"));
    }

}