package com.masterclass.api.b36_fxstyle;

import java.util.Locale;
import java.util.Objects;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Playground visual del bloque 36 (NO es un ejercicio: es el escaparate que ejecutas con
 * {@code mvn -pl b36_fxstyle javafx:run}). Monta una ventanita que aplica una hoja de estilo
 * {@code .css}, alterna entre tema claro/oscuro EN CALIENTE (Ej289), traduce sus textos según el
 * {@code Locale} (Ej292) y muestra un tooltip de ayuda (Ej291).
 *
 * <p>Usa los métodos {@code static} de los ejercicios. Hasta que los implementes, esos métodos
 * devuelven su centinela (cadena vacía), así que la ventana se abre igual pero "sosa"; en cuanto
 * vayas implementando los core, el Playground cobra vida. Los textos caen al nombre de la clave si
 * la traducción aún devuelve "" (fallback defensivo, para que se vea algo desde el principio).
 *
 * <p>Teoría: {@code teoria/36_JavaFX_Estilo_Accesibilidad.md}.
 */
public final class PlaygroundEstilo extends Application {

    private Locale locale = Locale.forLanguageTag("es");
    private String tema = "claro";

    @Override
    public void start(Stage stage) {
        Label titulo = new Label(texto("app.titulo"));
        titulo.getStyleClass().add("label");

        TextField email = new TextField();
        email.setPromptText("email");
        Tooltip ayuda = new Tooltip(textoTooltip("email"));
        email.setTooltip(ayuda);

        Button guardar = new Button(texto("boton.guardar"));
        guardar.setId("boton-guardar");
        guardar.getStyleClass().add("boton-primario");

        Button cambiarTema = new Button("Tema: " + tema);
        Button cambiarIdioma = new Button("Idioma: " + locale.getLanguage());

        VBox raiz = new VBox(12, titulo, email, guardar, cambiarTema, cambiarIdioma);
        raiz.setAlignment(Pos.CENTER);
        raiz.setPadding(new Insets(24));

        Scene escena = new Scene(raiz, 360, 320);
        aplicarTema(escena);

        cambiarTema.setOnAction(e -> {
            tema = alternarTemaSeguro(tema);
            cambiarTema.setText("Tema: " + tema);
            aplicarTema(escena);
        });

        cambiarIdioma.setOnAction(e -> {
            locale = locale.getLanguage().equals("es")
                    ? Locale.forLanguageTag("en")
                    : Locale.forLanguageTag("es");
            // Re-traduce la pantalla (Ej292).
            titulo.setText(texto("app.titulo"));
            guardar.setText(texto("boton.guardar"));
            cambiarIdioma.setText("Idioma: " + locale.getLanguage());
        });

        stage.setTitle("b36 · Estilo, accesibilidad e i18n");
        stage.setScene(escena);
        stage.show();
    }

    /** Aplica la hoja base + la hoja del tema actual (Ej289). */
    private void aplicarTema(Scene escena) {
        escena.getStylesheets().clear();
        anadirHoja(escena, "/css/app.css");
        String hojaTema = Ej289ThemingAndVariables.hojaDeTema(tema);
        if (hojaTema != null && !hojaTema.isBlank()) {
            anadirHoja(escena, hojaTema);
        }
    }

    private void anadirHoja(Scene escena, String ruta) {
        var url = PlaygroundEstilo.class.getResource(ruta);
        if (url != null) {
            escena.getStylesheets().add(url.toExternalForm());
        }
    }

    /** Traduce con fallback a la clave si el core aún no está implementado (devuelve ""). */
    private String texto(String clave) {
        String t = Ej292Internationalization.traducir(clave, locale);
        return (t == null || t.isBlank()) ? clave : t;
    }

    private String textoTooltip(String campo) {
        String t = Ej291UsabilityFeedback.tooltipDe(campo);
        return (t == null || t.isBlank()) ? campo : t;
    }

    /** Alterna el tema; si el core no está implementado (devuelve ""), alterna a mano. */
    private String alternarTemaSeguro(String actual) {
        String siguiente = Ej289ThemingAndVariables.alternarTema(actual);
        if (siguiente == null || siguiente.isBlank()) {
            return actual.equals("claro") ? "oscuro" : "claro";
        }
        return Objects.requireNonNull(siguiente);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
