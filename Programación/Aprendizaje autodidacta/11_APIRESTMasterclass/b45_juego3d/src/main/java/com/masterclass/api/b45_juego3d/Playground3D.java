package com.masterclass.api.b45_juego3d;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * Playground visual del bloque 45 (NO es un ejercicio: es el escaparate que ejecutas con
 * {@code mvn -pl b45_juego3d javafx:run}). Monta una escena 3D REAL: un cubo con
 * {@code PhongMaterial} y una esfera, iluminados por un {@code PointLight} y un {@code AmbientLight},
 * vistos por una {@code PerspectiveCamera}, girando con un {@code AnimationTimer}.
 *
 * <p>Demuestra en vivo los conceptos cuya matemática construyes en los ejercicios: la cámara en
 * perspectiva (Ej347), las transformaciones de rotación (Ej346) y los materiales/luces (Ej347, retos
 * 9 y 10). La lógica testeable vive en los {@code EjNNN}; esto es solo la vista.
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md}.
 */
public final class Playground3D extends Application {

    private static final double ANCHO = 640;
    private static final double ALTO = 480;

    @Override
    public void start(Stage stage) {
        // Cubo rojo con material Phong (difuso + especular: brilla donde le da la luz).
        Box cubo = new Box(120, 120, 120);
        PhongMaterial materialCubo = new PhongMaterial(Color.web("#e63946"));
        materialCubo.setSpecularColor(Color.WHITE);
        cubo.setMaterial(materialCubo);

        // Esfera azul, desplazada a un lado.
        Sphere esfera = new Sphere(60);
        esfera.setMaterial(new PhongMaterial(Color.web("#457b9d")));
        esfera.getTransforms().add(new Translate(160, 0, 0));

        // Luces: una puntual (cae con la distancia, Ej347 reto 9) y una ambiente tenue.
        PointLight luz = new PointLight(Color.WHITE);
        luz.getTransforms().add(new Translate(-200, -200, -300));
        AmbientLight ambiente = new AmbientLight(Color.web("#303030"));

        Group mundo = new Group(cubo, esfera, luz, ambiente);

        // Rotaciones que aplicaremos al cubo (los ejes X e Y giran el objeto).
        Rotate rotX = new Rotate(0, Rotate.X_AXIS);
        Rotate rotY = new Rotate(0, Rotate.Y_AXIS);
        cubo.getTransforms().addAll(rotX, rotY);

        // Cámara en perspectiva: lo lejano se ve pequeño (división por z, Ej347).
        PerspectiveCamera camara = new PerspectiveCamera(true);
        camara.setFieldOfView(45);
        camara.setNearClip(0.1);
        camara.setFarClip(2000);
        camara.getTransforms().add(new Translate(0, 0, -600));

        SubScene escena3D = new SubScene(mundo, ANCHO, ALTO, true, javafx.scene.SceneAntialiasing.BALANCED);
        escena3D.setFill(Color.web("#0b1021"));
        escena3D.setCamera(camara);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                rotX.setAngle(rotX.getAngle() + 0.4);
                rotY.setAngle(rotY.getAngle() + 0.7);
            }
        }.start();

        Scene escena = new Scene(new StackPane(escena3D), ANCHO, ALTO);
        stage.setScene(escena);
        stage.setTitle("b45 · Escena 3D (PerspectiveCamera + PhongMaterial + PointLight)");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
