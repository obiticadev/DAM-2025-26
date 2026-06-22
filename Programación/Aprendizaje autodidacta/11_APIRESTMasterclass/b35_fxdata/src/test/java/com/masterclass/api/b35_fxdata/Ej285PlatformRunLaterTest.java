package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import javafx.scene.control.Label;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(10)
class Ej285PlatformRunLaterTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void ejecutadoEnFx() {
        assertTrue(Ej285PlatformRunLater.ejecutadoEnFx(() -> {
        }));
    }

    @Test
    void actualizarEtiqueta() {
        Label etiqueta = new Label();
        assertEquals("hola", Ej285PlatformRunLater.actualizarEtiqueta(etiqueta, "hola"));
        assertEquals("", Ej285PlatformRunLater.actualizarEtiqueta(new Label(), "")); // caso límite: texto vacío
    }

    @Test
    void retoExtra01_esHiloDeAplicacion() {
        // el test corre en el hilo de prueba, NO en el FX
        assertFalse(Ej285PlatformRunLater.esHiloDeAplicacion());
    }

    @Test
    void retoExtra02_compruebaDentroDeFx() {
        assertTrue(Ej285PlatformRunLater.compruebaDentroDeFx());
    }

    @Test
    void retoExtra03_ejecutarYEsperar() {
        boolean[] corrio = {false};
        assertTrue(Ej285PlatformRunLater.ejecutarYEsperar(() -> corrio[0] = true));
        assertTrue(corrio[0]);
    }

    @Test
    void retoExtra04_calcularEnFx() {
        assertEquals(5, Ej285PlatformRunLater.calcularEnFx(() -> 2 + 3));
    }

    @Test
    void retoExtra05_ordenDeEjecucion() {
        assertEquals(List.of(0, 1, 2), Ej285PlatformRunLater.ordenDeEjecucion(3));
    }

    @Test
    void retoExtra06_actualizarDesdeOtroHilo() throws InterruptedException {
        assertEquals("listo", Ej285PlatformRunLater.actualizarDesdeOtroHilo(new Label(), "listo"));
    }

    @Test
    void retoExtra07_crearStageFallaFueraDeFx() {
        // desde el hilo de prueba (no-FX), crear un Stage debe lanzar IllegalStateException
        assertTrue(Ej285PlatformRunLater.crearStageFallaFueraDeFx());
    }

    @Test
    void retoExtra08_crearStageEnFx() {
        assertEquals("Hola", Ej285PlatformRunLater.crearStageEnFx("Hola"));
    }

    @Test
    void retoExtra09_sumarEnFx() {
        assertEquals(5, Ej285PlatformRunLater.sumarEnFx(2, 3));
    }

    @Test
    void retoExtra10_calcularEnFondoYMostrar() throws InterruptedException {
        assertEquals("16", Ej285PlatformRunLater.calcularEnFondoYMostrar(new Label(), 4));
    }
}
