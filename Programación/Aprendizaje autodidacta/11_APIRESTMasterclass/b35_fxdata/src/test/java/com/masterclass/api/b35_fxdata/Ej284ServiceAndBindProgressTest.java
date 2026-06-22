package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.ProgressBar;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(10)
class Ej284ServiceAndBindProgressTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    /** Arranca un Service en el hilo FX y espera su valor (patrón latch del addendum §1.6). */
    private static <V> V ejecutar(Service<V> servicio) throws Exception {
        AtomicReference<V> ref = new AtomicReference<>();
        CountDownLatch listo = new CountDownLatch(1);
        Platform.runLater(() -> {
            servicio.setOnSucceeded(e -> {
                ref.set(servicio.getValue());
                listo.countDown();
            });
            servicio.setOnFailed(e -> listo.countDown());
            servicio.start();
        });
        listo.await(5, TimeUnit.SECONDS);
        return ref.get();
    }

    private static Service<Integer> servicioConstante(int valor) {
        return new Service<>() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<>() {
                    @Override
                    protected Integer call() {
                        return valor;
                    }
                };
            }
        };
    }

    @Test
    void servicioSuma() throws Exception {
        assertEquals(15, ejecutar(Ej284ServiceAndBindProgress.servicioSuma(5)));
        assertEquals(1, ejecutar(Ej284ServiceAndBindProgress.servicioSuma(1))); // caso límite: n=1
    }

    @Test
    void vincularProgreso() {
        ProgressBar barra = new ProgressBar();
        SimpleDoubleProperty p = new SimpleDoubleProperty(0);
        assertTrue(Ej284ServiceAndBindProgress.vincularProgreso(barra, p));
        p.set(0.5); // al cambiar la property, la barra se mueve sola
        assertEquals(0.5, barra.getProgress(), 1e-9);
    }

    @Test
    void retoExtra01_estadoInicial() {
        assertEquals(Worker.State.READY, Ej284ServiceAndBindProgress.estadoInicial(servicioConstante(1)));
    }

    @Test
    void retoExtra02_ponerProgreso() {
        assertEquals(0.5, Ej284ServiceAndBindProgress.ponerProgreso(new ProgressBar(), 0.5), 1e-9);
    }

    @Test
    void retoExtra03_barraIndeterminada() {
        assertTrue(Ej284ServiceAndBindProgress.barraIndeterminada(new ProgressBar()) < 0);
    }

    @Test
    void retoExtra04_desvincular() {
        assertTrue(Ej284ServiceAndBindProgress.desvincular(new ProgressBar(), new SimpleDoubleProperty(0.3)));
    }

    @Test
    void retoExtra05_ejecutarServicio() throws Exception {
        assertEquals(7, Ej284ServiceAndBindProgress.ejecutarServicio(servicioConstante(7)));
    }

    @Test
    void retoExtra06_reutilizar() throws Exception {
        assertEquals(7, Ej284ServiceAndBindProgress.reutilizar(servicioConstante(7)));
    }

    @Test
    void retoExtra07_resetear() throws Exception {
        assertEquals(Worker.State.READY, Ej284ServiceAndBindProgress.resetear(servicioConstante(7)));
    }

    @Test
    void retoExtra08_porcentaje() {
        assertEquals("50%", Ej284ServiceAndBindProgress.porcentaje(0.5));
        assertEquals("100%", Ej284ServiceAndBindProgress.porcentaje(1.0));
    }

    @Test
    void retoExtra09_servicioDeValor() throws Exception {
        assertEquals("hola", Ej284ServiceAndBindProgress.ejecutarServicio(
                Ej284ServiceAndBindProgress.servicioDeValor("hola")));
    }

    @Test
    void retoExtra10_mensajeDeFalloServicio() throws Exception {
        assertEquals("boom", Ej284ServiceAndBindProgress.mensajeDeFalloServicio("boom"));
    }
}
