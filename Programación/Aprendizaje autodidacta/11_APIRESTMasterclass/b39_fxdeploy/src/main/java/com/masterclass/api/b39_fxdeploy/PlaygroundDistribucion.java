package com.masterclass.api.b39_fxdeploy;

import java.util.prefs.Preferences;

import com.masterclass.api.b39_fxdeploy.Ej306IntegratedHelp.AcercaDe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Playground visual del bloque 39 (NO es un ejercicio: es el escaparate que ejecutas con
 * {@code mvn -pl b39_fxdeploy javafx:run}). Monta lo que el bloque construye en lógica:
 *
 * <ul>
 *   <li>un diálogo <b>"Acerca de"</b> con el texto que arma {@code textoAcercaDe} (Ej306);</li>
 *   <li>un <b>Hyperlink</b> a la documentación de la versión, validado con {@code esUrlValida};</li>
 *   <li>un <b>CheckBox</b> "modo oscuro" cuyo estado se PERSISTE con {@code Preferences} (Ej307):
 *       cierra y reabre la app y el ajuste sigue ahí;</li>
 *   <li>una etiqueta que avisa si {@code hayActualizacion} comparando la versión actual con la
 *       "última publicada" (Ej310).</li>
 * </ul>
 *
 * <p>Usa los métodos {@code static} de los ejercicios: hasta que los implementes devuelven su
 * centinela, así que el "Acerca de" sale vacío y el enlace no se valida; en cuanto implementes los
 * core, cobra vida. Los comandos {@code jlink}/{@code jpackage} (Ej308/309) no se ejecutan aquí: son
 * de terminal y se documentan en {@code teoria/39_Distribucion_Instaladores.md} y el README del bloque.
 *
 * <p>Teoría: {@code teoria/39_Distribucion_Instaladores.md}.
 */
public final class PlaygroundDistribucion extends Application {

    private static final AcercaDe INFO = new AcercaDe("Masterclass App", "1.0.0", 2026, "DAM");
    private static final String ULTIMA_PUBLICADA = "1.2.0";
    private static final Preferences PREFS = Preferences.userRoot().node("masterclass/b39/playground");

    @Override
    public void start(Stage stage) {
        VBox raiz = new VBox(12);
        raiz.setPadding(new Insets(16));

        Button acercaDe = new Button("Acerca de…");
        acercaDe.setOnAction(e -> mostrarAcercaDe());

        Hyperlink docs = new Hyperlink("Abrir documentación online");
        String url = Ej306IntegratedHelp.urlDocsVersion("https://docs.masterclass.app", INFO.version());
        docs.setOnAction(e -> {
            if (Ej306IntegratedHelp.esUrlValida(url)) {
                getHostServices().showDocument(url);
            }
        });

        CheckBox oscuro = new CheckBox("Modo oscuro (se recuerda al reabrir)");
        oscuro.setSelected(Ej307UserPreferences.leerBooleano(PREFS, "oscuro", false));
        oscuro.selectedProperty().addListener((obs, was, is) ->
                Ej307UserPreferences.guardarBooleano(PREFS, "oscuro", is));

        Label update = new Label(Ej310VersioningAndUpdate.hayActualizacion(INFO.version(), ULTIMA_PUBLICADA)
                ? "Hay una actualización disponible: v" + ULTIMA_PUBLICADA
                : "Tu versión está al día.");

        raiz.getChildren().addAll(
                new Label("Bloque 39 · Documentación, ayuda y distribución"),
                acercaDe, docs, oscuro, update);

        stage.setScene(new Scene(raiz, 480, 320));
        stage.setTitle(Ej306IntegratedHelp.tituloVentana(INFO));
        stage.show();
    }

    private void mostrarAcercaDe() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(Ej306IntegratedHelp.tituloVentana(INFO));
        a.setHeaderText(INFO.nombre());
        a.setContentText(Ej306IntegratedHelp.textoAcercaDe(INFO));
        a.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
