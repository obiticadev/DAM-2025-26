package com.masterclass.api.b28_proc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 60, unit = TimeUnit.SECONDS)
class Ej232ProcessEnvAndDirTest {

    @Test
    void leerVariableEntorno() {
        assertEquals("valor123", Ej232ProcessEnvAndDir.leerVariableEntorno("MI_VAR", "valor123"));
    }

    @Test
    void procesoCorreEnDirectorio() {
        assertTrue(Ej232ProcessEnvAndDir.procesoCorreEnDirectorio());
    }

    @Test
    void retoExtra01_variableNoDefinidaEsNull() {
        assertTrue(Ej232ProcessEnvAndDir.variableNoDefinidaEsNull());
    }

    @Test
    void retoExtra02_heredaPathDelPadre() {
        assertTrue(Ej232ProcessEnvAndDir.heredaPathDelPadre());
    }

    @Test
    void retoExtra03_modificarEntornoNoAfectaAlPadre() {
        assertTrue(Ej232ProcessEnvAndDir.modificarEntornoNoAfectaAlPadre());
    }

    @Test
    void retoExtra04_eliminarVariableHeredada() {
        assertTrue(Ej232ProcessEnvAndDir.eliminarVariableHeredada());
    }

    @Test
    void retoExtra05_directorioPorDefectoEsDelPadre() {
        assertTrue(Ej232ProcessEnvAndDir.directorioPorDefectoEsDelPadre());
    }

    @Test
    void retoExtra06_environmentEsMapaNoVacio() {
        assertTrue(Ej232ProcessEnvAndDir.environmentEsMapaNoVacio());
    }

    @Test
    void retoExtra07_variableConEspacios() {
        assertEquals("hola mundo", Ej232ProcessEnvAndDir.variableConEspacios());
    }

    @Test
    void retoExtra08_dosVariablesIndependientes() {
        assertEquals("segundo", Ej232ProcessEnvAndDir.dosVariablesIndependientes());
    }

    @Test
    void retoExtra09_directoryDevuelveLoConfigurado() {
        assertTrue(Ej232ProcessEnvAndDir.directoryDevuelveLoConfigurado());
    }

    @Test
    void retoExtra10_inheritIOConDirectorioFunciona() {
        assertTrue(Ej232ProcessEnvAndDir.inheritIOConDirectorioFunciona());
    }
}
