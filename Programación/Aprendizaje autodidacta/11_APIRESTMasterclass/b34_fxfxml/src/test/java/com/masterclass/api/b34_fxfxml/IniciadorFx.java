package com.masterclass.api.b34_fxfxml;

import javafx.application.Platform;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Ayudante de test: inicializa el toolkit de JavaFX UNA sola vez (headless con Monocle).
 *
 * <p>Cargar un {@code .fxml} con {@code FXMLLoader}, construir controles y disparar eventos sobre
 * nodos necesita que el toolkit esté arrancado. En producción lo arranca {@code Application.launch};
 * en los tests lo arrancamos a mano con {@link Platform#startup}. La segunda llamada lanza
 * {@code IllegalStateException} ("Toolkit already initialized"), así que la ignoramos: por eso
 * varios test pueden llamar a {@link #iniciar()} sin pelearse.
 *
 * <p>La lógica pura del bloque (ViewModel, decisión de un diálogo, contadores de eventos sobre
 * objetos normales) NO necesita el toolkit, pero lo arrancamos igual en todos los tests del bloque
 * por uniformidad y porque varios ejercicios sí cargan FXML o crean nodos reales.
 */
final class IniciadorFx {

    private IniciadorFx() {
    }

    static void iniciar() {
        try {
            CountDownLatch listo = new CountDownLatch(1);
            Platform.startup(listo::countDown);
            listo.await();
        } catch (IllegalStateException yaArrancado) {
            // El toolkit ya estaba inicializado por otro test: perfecto, seguimos.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Ejecuta una acción en el FX Application Thread y espera a que termine, RE-LANZANDO en el hilo
     * del test cualquier excepción o fallo de aserción ocurrido dentro. Esto último es clave: sin
     * reenviar el error, un test que falla (centinela, {@code UnsupportedOperationException} de un
     * reto sin implementar o un {@code assert} roto) parecería pasar en verde. Lo usan los ejercicios
     * cuya operación toca el toolkit (crear un {@code Alert}, que exige el hilo de JavaFX).
     */
    static void enFx(Runnable accion) {
        try {
            AtomicReference<Throwable> error = new AtomicReference<>();
            CountDownLatch hecho = new CountDownLatch(1);
            Platform.runLater(() -> {
                try {
                    accion.run();
                } catch (Throwable t) {
                    error.set(t);
                } finally {
                    hecho.countDown();
                }
            });
            hecho.await();
            Throwable t = error.get();
            if (t instanceof RuntimeException re) {
                throw re;
            }
            if (t instanceof Error err) {
                throw err; // cubre AssertionError / AssertionFailedError de JUnit
            }
            if (t != null) {
                throw new RuntimeException(t);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
