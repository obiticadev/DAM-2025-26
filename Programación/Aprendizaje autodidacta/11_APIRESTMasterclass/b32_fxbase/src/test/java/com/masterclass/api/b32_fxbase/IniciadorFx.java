package com.masterclass.api.b32_fxbase;

import javafx.application.Platform;
import java.util.concurrent.CountDownLatch;

/**
 * Ayudante de test: inicializa el toolkit de JavaFX UNA sola vez (headless con Monocle).
 *
 * <p>Construir nodos/controles (un {@code Label}, un {@code VBox}, sus skins…) necesita que el
 * toolkit esté arrancado. En producción lo arranca {@code Application.launch}; en los tests lo
 * arrancamos a mano con {@link Platform#startup}. La segunda llamada lanza
 * {@code IllegalStateException} ("Toolkit already initialized"), así que la ignoramos: por eso
 * varios test pueden llamar a {@link #iniciar()} sin pelearse.
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

    /** Ejecuta una acción en el FX Application Thread y espera a que termine. */
    static void enFx(Runnable accion) {
        try {
            CountDownLatch hecho = new CountDownLatch(1);
            Platform.runLater(() -> {
                try {
                    accion.run();
                } finally {
                    hecho.countDown();
                }
            });
            hecho.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
