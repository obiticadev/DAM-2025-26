package com.masterclass.api.b41_anim;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * Playground visual del bloque 41 (NO es un ejercicio: es el escaparate que ejecutas con
 * {@code mvn -pl b41_anim javafx:run}). Monta un game loop REAL con {@code AnimationTimer} sobre un
 * {@code Canvas}: una pelota se mueve, rebota en las paredes y la controlas con las flechas o WASD
 * moviendo una pala. Es un mini-Pong contra el muro.
 *
 * <p>Usa los métodos {@code static} de los ejercicios:
 * <ul>
 *   <li>{@code Ej320.deltaSegundos} para el dt independiente de los fps,</li>
 *   <li>{@code Ej321.nuevaPosicion} para mover la pelota y la pala,</li>
 *   <li>{@code Ej324.velocidadTrasPared} para el rebote en paredes,</li>
 *   <li>{@code Ej323.ejeVertical} para leer la entrada de teclado.</li>
 * </ul>
 *
 * <p>Hasta que implementes esos core devuelven su centinela, así que la ventana se abre pero la
 * pelota no se mueve (o se queda quieta). En cuanto los implementes, el juego cobra vida.
 *
 * <p>Teoría: {@code teoria/41_Animacion_Juegos.md}.
 */
public final class PlaygroundJuego extends Application {

    private static final double ANCHO = 640;
    private static final double ALTO = 400;
    private static final double RADIO = 10;
    private static final double PALA_ALTO = 80;
    private static final double PALA_ANCHO = 12;
    private static final double VEL_PALA = 320; // px/s

    private double pelotaX = ANCHO / 2;
    private double pelotaY = ALTO / 2;
    private double velX = 220;  // px/s
    private double velY = 160;  // px/s
    private double palaY = (ALTO - PALA_ALTO) / 2;
    private long nanosAnterior = 0;

    private final Set<KeyCode> teclas = new HashSet<>();

    @Override
    public void start(Stage stage) {
        Canvas lienzo = new Canvas(ANCHO, ALTO);
        GraphicsContext g = lienzo.getGraphicsContext2D();

        BorderPane raiz = new BorderPane(lienzo);
        Scene escena = new Scene(raiz);
        escena.setOnKeyPressed(e -> teclas.add(e.getCode()));
        escena.setOnKeyReleased(e -> teclas.remove(e.getCode()));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dt = Ej320AnimationTimerLoop.deltaSegundos(nanosAnterior, now);
                nanosAnterior = now;
                dt = Ej320AnimationTimerLoop.limitarDelta(dt, 0.05); // anti-tunneling (reto 3)
                actualizar(dt);
                pintar(g);
            }
        }.start();

        stage.setScene(escena);
        stage.setTitle("b41 · Mini-juego 2D (flechas / WASD para mover la pala)");
        stage.show();
    }

    private void actualizar(double dt) {
        // Entrada: eje vertical de la pala (reto 1 de Ej323).
        boolean arriba = teclas.contains(KeyCode.UP) || teclas.contains(KeyCode.W);
        boolean abajo = teclas.contains(KeyCode.DOWN) || teclas.contains(KeyCode.S);
        int eje = Ej323InputGameState.ejeVertical(arriba, abajo);
        if (eje != -99) { // si el core ya está implementado
            palaY = Ej321SpriteAndMovement.nuevaPosicion(palaY, eje * VEL_PALA, dt);
            palaY = Ej324MiniGame2D.clampPala(palaY, PALA_ALTO, ALTO);
        }

        // Física de la pelota (Ej321 + rebote de Ej324).
        pelotaX = Ej321SpriteAndMovement.nuevaPosicion(pelotaX, velX, dt);
        pelotaY = Ej321SpriteAndMovement.nuevaPosicion(pelotaY, velY, dt);
        velX = Ej324MiniGame2D.velocidadTrasPared(pelotaX, velX, ANCHO);
        velY = Ej324MiniGame2D.velocidadTrasPared(pelotaY, velY, ALTO);
    }

    private void pintar(GraphicsContext g) {
        g.setFill(Color.web("#0b1021"));
        g.fillRect(0, 0, ANCHO, ALTO);

        g.setFill(Color.web("#4f9dff"));
        g.fillRect(16, palaY, PALA_ANCHO, PALA_ALTO);

        g.setFill(Color.web("#ffd166"));
        g.fillOval(pelotaX - RADIO, pelotaY - RADIO, RADIO * 2, RADIO * 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
