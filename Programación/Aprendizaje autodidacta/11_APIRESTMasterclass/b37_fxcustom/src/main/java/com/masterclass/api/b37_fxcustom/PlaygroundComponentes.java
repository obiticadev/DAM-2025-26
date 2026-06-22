package com.masterclass.api.b37_fxcustom;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Playground visual del bloque 37 (NO es un ejercicio: es el escaparate que ejecutas con
 * {@code mvn -pl b37_fxcustom javafx:run}). Monta tres piezas que demuestran el bloque:
 *
 * <ul>
 *   <li>un <b>control compuesto</b> "campo etiquetado" (Ej293): Label + TextField + Label de error
 *       que cambia de color según el estado de validación;</li>
 *   <li>un <b>Canvas</b> (Ej295/296) donde se dibuja un polígono regular y una estrella usando los
 *       vértices que calcula la geometría pura del core;</li>
 *   <li>un <b>BarChart</b> (Ej297) alimentado con una serie construida por {@code serieXY}.</li>
 * </ul>
 *
 * <p>Usa los métodos {@code static} de los ejercicios. Hasta que los implementes devuelven su
 * centinela, así que la ventana se abre igual pero "sosa" (sin figuras, sin barras); en cuanto
 * vayas implementando los core, el Playground cobra vida.
 *
 * <p>Teoría: {@code teoria/37_JavaFX_Componentes_Canvas.md}.
 */
public final class PlaygroundComponentes extends Application {

    @Override
    public void start(Stage stage) {
        VBox raiz = new VBox(16);
        raiz.setPadding(new Insets(16));

        raiz.getChildren().add(campoEtiquetado("Email", true, "ana@mail.com"));
        raiz.getChildren().add(lienzoConFiguras());
        raiz.getChildren().add(graficoDeBarras());

        stage.setScene(new Scene(raiz, 560, 720));
        stage.setTitle("Bloque 37 · Componentes, Canvas y Charts");
        stage.show();
    }

    /** Control compuesto del Ej293: etiqueta (con asterisco si es obligatorio) + campo + error. */
    private VBox campoEtiquetado(String base, boolean obligatorio, String valor) {
        VBox caja = new VBox(4);
        Label etiqueta = new Label(Ej293CustomControlCompose.etiquetaMostrada(base, obligatorio));
        TextField campo = new TextField(valor);
        Label error = new Label();

        Runnable refrescar = () -> {
            String estado = Ej293CustomControlCompose.estadoCampo(campo.getText(), 3);
            error.setText(Ej293CustomControlCompose.mensajeError(estado, 3));
            error.setTextFill("valido".equals(estado) ? Color.GREEN : Color.CRIMSON);
        };
        campo.textProperty().addListener((o, a, b) -> refrescar.run());
        refrescar.run();

        caja.getChildren().addAll(etiqueta, campo, error);
        return caja;
    }

    /** Canvas del Ej295/296: dibuja un hexágono y una estrella a partir de la geometría del core. */
    private Canvas lienzoConFiguras() {
        Canvas canvas = new Canvas(520, 260);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#f4f4f8"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        pintarPoligono(gc, Ej295CanvasDrawing.verticesPoligonoRegular(140, 130, 90, 6), Color.STEELBLUE);
        pintarPoligono(gc, Ej295CanvasDrawing.verticesEstrella(380, 130, 90, 40, 5), Color.GOLD);
        return canvas;
    }

    private void pintarPoligono(GraphicsContext gc, List<double[]> vertices, Color color) {
        if (vertices.isEmpty()) {
            return; // core sin implementar: no hay nada que pintar todavía
        }
        double[] xs = new double[vertices.size()];
        double[] ys = new double[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            xs[i] = vertices.get(i)[0];
            ys[i] = vertices.get(i)[1];
        }
        gc.setFill(color);
        gc.fillPolygon(xs, ys, xs.length);
    }

    /** BarChart del Ej297 alimentado por la serie que calcula el core. */
    private BarChart<String, Number> graficoDeBarras() {
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        chart.setTitle("Serie de ejemplo (Ej297)");
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        for (double[] punto : Ej297ChartsBuiltIn.serieXY(new double[]{12, 28, 19, 35})) {
            serie.getData().add(new XYChart.Data<>(
                    Ej297ChartsBuiltIn.etiquetaCategoria((int) punto[0]), punto[1]));
        }
        chart.getData().add(serie);
        chart.setPrefHeight(240);
        return chart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
