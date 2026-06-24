package com.masterclass.api.b45_juego3d;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * Playground del mini-juego 3D del bloque 45 (escaparate de {@code Ej350MiniGame3D}). Ejecútalo con
 * {@code mvn -pl b45_juego3d javafx:run} cambiando la {@code mainClass} del pom a esta clase, o desde
 * el IDE.
 *
 * <p>Mueves un cubo (el jugador) con las flechas/WASD por un plano 3D; una esfera (el ítem) se recoge
 * al acercarte (colisión de esfera, Ej348/Ej350) y suma un punto, reapareciendo en otro sitio. Usa
 * los core: {@code Ej350.mover}, {@code Ej350.puntuarSiRecoge}, {@code Ej350.clampPosicion} y
 * {@code Ej350.spawnPosicion}. Hasta que los implementes, el jugador no se moverá (los core devuelven
 * su centinela).
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md} (sección 7).
 */
public final class PlaygroundMiniGame3D extends Application {

    private static final double ANCHO = 720;
    private static final double ALTO = 520;
    private static final double LIMITE = 200;
    private static final double VEL = 180; // unidades/segundo
    private static final double RADIO_RECOGIDA = 45;

    private final double[][] spawns = {{120, 0, 0}, {-120, 0, 120}, {0, 0, -150}, {-150, 0, -150}};
    private int spawnIndice = 0;
    private double[] jugador = {0, 0, 0};
    private double[] item = spawns[0].clone();
    private int puntos = 0;
    private long nanosAnterior = 0;

    private final Set<KeyCode> teclas = new HashSet<>();

    @Override
    public void start(Stage stage) {
        Box cuboJugador = new Box(50, 50, 50);
        cuboJugador.setMaterial(new PhongMaterial(Color.web("#e63946")));
        Translate tJugador = new Translate();
        cuboJugador.getTransforms().add(tJugador);

        Sphere esferaItem = new Sphere(28);
        esferaItem.setMaterial(new PhongMaterial(Color.web("#ffd166")));
        Translate tItem = new Translate();
        esferaItem.getTransforms().add(tItem);

        Box suelo = new Box(2 * LIMITE + 100, 4, 2 * LIMITE + 100);
        suelo.setMaterial(new PhongMaterial(Color.web("#1d3557")));
        suelo.getTransforms().add(new Translate(0, 40, 0));

        PointLight luz = new PointLight(Color.WHITE);
        luz.getTransforms().add(new Translate(-150, -250, -200));
        Group mundo = new Group(cuboJugador, esferaItem, suelo, luz, new AmbientLight(Color.web("#404040")));

        PerspectiveCamera camara = new PerspectiveCamera(true);
        camara.setFieldOfView(50);
        camara.setNearClip(0.1);
        camara.setFarClip(3000);
        camara.getTransforms().add(new Translate(0, -250, -600));
        camara.setRotationAxis(javafx.scene.transform.Rotate.X_AXIS);
        camara.setRotate(-20);

        SubScene escena3D = new SubScene(mundo, ANCHO, ALTO, true, SceneAntialiasing.BALANCED);
        escena3D.setFill(Color.web("#0b1021"));
        escena3D.setCamera(camara);

        Text hud = new Text("Puntos: 0");
        hud.setFill(Color.WHITE);
        BorderPane raiz = new BorderPane(escena3D);
        raiz.setTop(hud);

        Scene escena = new Scene(raiz, ANCHO, ALTO);
        escena.setOnKeyPressed(e -> teclas.add(e.getCode()));
        escena.setOnKeyReleased(e -> teclas.remove(e.getCode()));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dt = nanosAnterior == 0 ? 0 : (now - nanosAnterior) / 1_000_000_000.0;
                nanosAnterior = now;
                actualizar(dt);
                tJugador.setX(jugador[0]);
                tJugador.setY(jugador[1]);
                tJugador.setZ(jugador[2]);
                tItem.setX(item[0]);
                tItem.setY(item[1]);
                tItem.setZ(item[2]);
                hud.setText("Puntos: " + puntos);
            }
        }.start();

        stage.setScene(escena);
        stage.setTitle("b45 · Mini-juego 3D (flechas/WASD para moverte, recoge las esferas)");
        stage.show();
    }

    private void actualizar(double dt) {
        double dx = 0;
        double dz = 0;
        if (teclas.contains(KeyCode.LEFT) || teclas.contains(KeyCode.A)) {
            dx -= 1;
        }
        if (teclas.contains(KeyCode.RIGHT) || teclas.contains(KeyCode.D)) {
            dx += 1;
        }
        if (teclas.contains(KeyCode.UP) || teclas.contains(KeyCode.W)) {
            dz += 1;
        }
        if (teclas.contains(KeyCode.DOWN) || teclas.contains(KeyCode.S)) {
            dz -= 1;
        }

        double[] movido = Ej350MiniGame3D.mover(jugador, new double[]{dx, 0, dz}, VEL, dt);
        if (movido != null) { // si el core ya está implementado
            double[] dentro = Ej350MiniGame3D.clampPosicion(movido, LIMITE);
            jugador = dentro != null ? dentro : movido;
        }

        int nuevos = Ej350MiniGame3D.puntuarSiRecoge(puntos, jugador, item, RADIO_RECOGIDA);
        if (nuevos > puntos) {
            puntos = nuevos;
            spawnIndice++;
            double[] sitio = Ej350MiniGame3D.spawnPosicion(spawnIndice, spawns);
            if (sitio != null) {
                item = sitio.clone();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
