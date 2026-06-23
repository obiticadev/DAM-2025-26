package com.masterclass.api.b40_media;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Playground visual del bloque 40 (NO es un ejercicio: es el escaparate que ejecutas con
 * {@code mvn -pl b40_media javafx:run}). Genera una imagen de prueba en código (un degradado de
 * color), le aplica los filtros del core (escala de grises + brillo) y la muestra en un
 * {@code Canvas}; un {@code Slider} controla el brillo y demuestra la máquina de estados del
 * reproductor de Ej314 con etiquetas.
 *
 * <p>Usa los métodos {@code static} de los ejercicios. Hasta que los implementes devuelven su
 * centinela, así que la ventana se abre igual pero "sosa" (sin imagen filtrada); en cuanto vayas
 * implementando los core, el Playground cobra vida.
 *
 * <p>Para reproducir audio/vídeo de verdad necesitas un fichero y {@code MediaPlayer}/
 * {@code MediaView}; el código está explicado en {@code teoria/40_Multimedia.md} (secciones 4-6).
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md}.
 */
public final class PlaygroundMultimedia extends Application {

    private static final int ANCHO = 256;
    private static final int ALTO = 160;

    @Override
    public void start(Stage stage) {
        int[][] original = imagenDegradado(ANCHO, ALTO);

        Canvas lienzo = new Canvas(ANCHO, ALTO);
        Label estado = new Label("Estado del reproductor: READY");

        Slider brillo = new Slider(-128, 128, 0);
        brillo.setShowTickLabels(true);
        Runnable repintar = () -> pintarFiltrada(lienzo, original, (int) brillo.getValue());
        brillo.valueProperty().addListener((o, a, b) -> repintar.run());
        repintar.run();

        // Demostración de la máquina de estados (Ej314) sin necesidad de audio real.
        HBox botones = new HBox(8);
        for (String accion : new String[]{"play", "pause", "stop"}) {
            javafx.scene.control.Button b = new javafx.scene.control.Button(accion);
            b.setOnAction(e -> {
                String actual = estado.getText().replace("Estado del reproductor: ", "");
                String nuevo = Ej314AudioPlayback.siguienteEstado(actual, accion);
                estado.setText("Estado del reproductor: " + (nuevo == null || nuevo.isEmpty() ? actual : nuevo));
            });
            botones.getChildren().add(b);
        }

        VBox raiz = new VBox(14,
                new Label("Imagen generada -> filtro grises + brillo (Ej312):"),
                lienzo,
                new Label("Brillo:"), brillo,
                new Label("Reproductor (máquina de estados Ej314):"), botones, estado);
        raiz.setPadding(new Insets(16));

        stage.setScene(new Scene(raiz, 360, 460));
        stage.setTitle("Bloque 40 · Multimedia: imagen, audio y vídeo");
        stage.show();
    }

    /** Crea una imagen ARGB con un degradado horizontal rojo->azul (datos de prueba). */
    private int[][] imagenDegradado(int ancho, int alto) {
        int[][] m = new int[alto][ancho];
        for (int f = 0; f < alto; f++) {
            for (int c = 0; c < ancho; c++) {
                int r = (int) (255.0 * c / (ancho - 1));
                int b = 255 - r;
                m[f][c] = Ej311ImageLoadSave.empaquetarARGB(255, r, 0, b);
            }
        }
        return m;
    }

    /** Aplica grises + brillo del core y vuelca el resultado en el Canvas. */
    private void pintarFiltrada(Canvas lienzo, int[][] argb, int brillo) {
        GraphicsContext gc = lienzo.getGraphicsContext2D();
        int[][] grises = Ej312ImageFilters.aGrises(argb);
        if (grises.length == 0) {
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
            gc.setFill(Color.DIMGRAY);
            gc.fillText("Implementa aGrises() para ver la imagen", 20, ALTO / 2.0);
            return;
        }
        int[][] ajustada = Ej312ImageFilters.ajustarBrillo(grises, brillo);
        int[][] fuente = ajustada.length == 0 ? grises : ajustada;

        WritableImage img = new WritableImage(ANCHO, ALTO);
        PixelWriter pw = img.getPixelWriter();
        for (int f = 0; f < ALTO; f++) {
            for (int c = 0; c < ANCHO; c++) {
                double v = fuente[f][c] / 255.0;
                pw.setColor(c, f, new Color(v, v, v, 1.0));
            }
        }
        gc.drawImage(img, 0, 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
