package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej171SpringBootIntegrationTest {

    @Test
    void flujoAddYDel() {
        assertEquals(List.of("b"),
                Ej171SpringBootIntegration.ejecutarFlujo(List.of("a"), List.of("ADD:b", "DEL:a")));
    }

    @Test
    void estadoOrdenado() {
        assertEquals(List.of("a", "m", "z"),
                Ej171SpringBootIntegration.ejecutarFlujo(List.of(), List.of("ADD:z", "ADD:a", "ADD:m")));
    }

    @Test
    void comandoMalformado() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej171SpringBootIntegration.ejecutarFlujo(List.of(), List.of("BADCMD")));
    }

    @Test
    void nulls() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej171SpringBootIntegration.ejecutarFlujo(null, List.of()));
    }

    @Test
    void testRetoExtra01_crearComandoAdd() {
        // Crea comando de agregacion.
        assertEquals("ADD:x", Ej171SpringBootIntegration.crearComandoAdd("x"));
    }

    @Test
    void testRetoExtra02_crearComandoDel() {
        // Crea comando de eliminacion.
        assertEquals("DEL:x", Ej171SpringBootIntegration.crearComandoDel("x"));
    }

    @Test
    void testRetoExtra03_extraerAccion() {
        // Extrae la accion del comando.
        assertEquals("ADD", Ej171SpringBootIntegration.extraerAccion("ADD:x"));
    }

    @Test
    void testRetoExtra04_extraerArgumento() {
        // Extrae el argumento del comando.
        assertEquals("x", Ej171SpringBootIntegration.extraerArgumento("ADD:x"));
    }

    @Test
    void testRetoExtra05_esComandoValido() {
        // Comprueba si es un comando estructurado.
        assertTrue(Ej171SpringBootIntegration.esComandoValido("ADD:x"));
    }

    @Test
    void testRetoExtra06_tamanioInicial() {
        // Obtiene tamaño de la lista.
        assertEquals(2, Ej171SpringBootIntegration.tamanioInicial(java.util.List.of("a", "b")));
    }

    @Test
    void testRetoExtra07_listaContiene() {
        // Verifica si la lista contiene el valor.
        assertTrue(Ej171SpringBootIntegration.listaContiene(java.util.List.of("a"), "a"));
    }

    @Test
    void testRetoExtra08_combinarComandos() {
        // Combina dos listas de comandos.
        assertEquals(2, Ej171SpringBootIntegration.combinarComandos(java.util.List.of("1"), java.util.List.of("2")).size());
    }

    @Test
    void testRetoExtra09_filtrarComandosAdd() {
        // Filtra comandos ADD.
        assertEquals(1, Ej171SpringBootIntegration.filtrarComandosAdd(java.util.List.of("ADD:a", "DEL:b")).size());
    }

    @Test
    void testRetoExtra10_filtrarComandosDel() {
        // Filtra comandos DEL.
        assertEquals(1, Ej171SpringBootIntegration.filtrarComandosDel(java.util.List.of("ADD:a", "DEL:b")).size());
    }

}